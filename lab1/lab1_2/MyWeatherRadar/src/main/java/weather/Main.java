package weather;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import weather.ipma_client.CityForecast;
import weather.ipma_client.IpmaCityForecast;
import weather.ipma_client.IpmaService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;

public class Main {
    private static final String INTREGEX = "\\d+";
    private static final String USAGE = "Command line argument: City ID";
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());
    private static final org.apache.logging.log4j.Logger log4j2 = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log4j2.debug("Validating command line arguments.");
        if (args.length != 1 || !(args[0].matches(INTREGEX))) {
            // System.err.println(USAGE);
            log4j2.error("Invalid command line arguments.");
            System.exit(1);
        }
        log4j2.debug("Parsing city ID (arg 0).");
        int cityId = Integer.parseInt(args[0]);
        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        log4j2.debug("Creating retrofit instance loaded with the GSon lib.");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        log4j2.debug("Creating call.");
        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(cityId);

        try {
            log4j2.debug("Executing call.");
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            log4j2.debug("Checking if API response (forecast) is null.");
            if (forecast != null) {
                log4j2.debug("Forecast isn't null, logging information.");
                String owner = forecast.getOwner();
                String country = forecast.getCountry();
                String update = forecast.getDataUpdate();

                logger.info(owner + " forecast for city with ID " + cityId + ", " + country
                + " (updated " + update + ")");

                log4j2.debug("Logging information for every day in the forecast.");
                for (CityForecast f : forecast.getData()) {
                    int min = (int) Math.round(Double.parseDouble(f.getTMin()));
                    int max = (int) Math.round(Double.parseDouble(f.getTMax()));
                    int rain = (int) Math.round(Double.parseDouble(f.getPrecipitaProb()));
                    int wind = f.getClassWindSpeed();
                    LocalDate date = LocalDate.parse(f.getForecastDate());
                    String dateString = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
                    logger.info(
                            "\nDate: " + dateString
                            + "\nCoordinates: " + f.getLatitude() + ", " + f.getLongitude()
                            + "\nTemperature: " + min + "ºC - " + max + "ºC"
                            + "\nPrecipitation: " + rain + "%"
                            + "\nWind: " + wind + "km/h " + f.getPredWindDir()
                            + "\nWeather type ID: " + f.getIdWeatherType()
                    );
                }
            } else {
                log4j2.debug("Forecast is null, logging 'No results!'.");
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            log4j2.debug("Exception caught, printing stack trace.");
            ex.printStackTrace();
        }
    }
}

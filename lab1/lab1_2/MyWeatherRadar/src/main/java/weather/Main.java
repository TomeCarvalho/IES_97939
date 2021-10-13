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

public class Main {
    private static final String INTREGEX = "\\d+";
    private static final String USAGE = "Command line argument: City ID";
    private static final Logger logger = Logger.getLogger(WeatherStarter.class.getName());

    public static void main(String[] args) {
        if (args.length != 1 || !(args[0].matches(INTREGEX))) {
            System.err.println(USAGE);
            System.exit(1);
        }
        int cityId = Integer.parseInt(args[0]);
        // get a retrofit instance, loaded with the GSon lib to convert JSON into objects
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getForecastForACity(cityId);

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            if (forecast != null) {
                String owner = forecast.getOwner();
                String country = forecast.getCountry();
                String update = forecast.getDataUpdate();

                logger.info(owner + " forecast for city with ID " + cityId + ", " + country
                + " (updated " + update + ")");

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
                logger.info( "No results!");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

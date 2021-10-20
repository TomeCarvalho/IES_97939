package ipmaclient;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class GetForecast {
    public static String cityForecast(int cityId, boolean print) {
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

                StringBuilder sb = new StringBuilder(owner + " forecast for city with ID " + cityId + ", " + country
                        + " (updated " + update + ")\n");

                for (CityForecast f : forecast.getData()) {
                    int min = (int) Math.round(Double.parseDouble(f.getTMin()));
                    int max = (int) Math.round(Double.parseDouble(f.getTMax()));
                    int rain = (int) Math.round(Double.parseDouble(f.getPrecipitaProb()));
                    int wind = f.getClassWindSpeed();
                    LocalDate date = LocalDate.parse(f.getForecastDate());
                    String dateString = date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
                    sb.append("\nDate: ").append(dateString).append("\nCoordinates: ").append(f.getLatitude())
                            .append(", ").append(f.getLongitude()).append("\nTemperature: ").append(min)
                            .append("ºC - ").append(max).append("ºC").append("\nPrecipitation: ").append(rain)
                            .append("%").append("\nWind: ").append(wind).append("km/h ").append(f.getPredWindDir())
                            .append("\nWeather type ID: ").append(f.getIdWeatherType()).append("\n");
                }
                if (print)
                    System.out.println(sb);
                return sb.toString();
            } else {
                return "Error retrieving forecast (no results).";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error retrieving forecast (exception).";
        }
    }

    public static String cityForecast(int cityId) {
        return cityForecast(cityId, false);
    }
}

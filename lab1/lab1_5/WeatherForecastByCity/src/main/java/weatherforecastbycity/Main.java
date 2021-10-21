package weatherforecastbycity;

import ipmaclient.GetForecast;

public class Main {
    public static void main(String[] args) {
        GetForecast.cityForecast(args[0], true);
    }
}

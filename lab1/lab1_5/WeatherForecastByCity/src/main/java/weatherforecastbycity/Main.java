package weatherforecastbycity;

import ipmaclient.GetForecast;

public class Main {
    private static final String INTREGEX = "\\d+";
    private static final String USAGE = "Command line argument: City ID";

    public static void main(String[] args) {
        if (args.length != 1 || !(args[0].matches(INTREGEX))) {
            System.err.println("Invalid command line arguments.");
            System.exit(1);
        }
        int cityId = Integer.parseInt(args[0]);
        GetForecast.cityForecast(cityId, true);
    }
}

package com.ekansh.test.utils;

/**
 * Created by Ekansh Gautam on 2019-11-27
 */
public class MathUtil {


    private MathUtil() {
    }


    /**
     * This method calculates distance between two lat longs ({@code lat1},{@code lon1}) and ({@code lat2},{@code lon2}).
     * It returns distance in metres.
     */
    public static double distance(double lat1, double lat2, double lon1, double lon2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c * 1000; // convert to meters
    }

    public static double getMiles(Double metres) {
        return metres * 0.000621371192;
    }

}

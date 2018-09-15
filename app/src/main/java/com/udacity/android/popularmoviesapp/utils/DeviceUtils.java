package com.udacity.android.popularmoviesapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;

import java.util.Locale;

public class DeviceUtils {

    /*Code to check density of screen
     * https://stackoverflow.com/questions/3166501/getting-the-screen-density-programmatically-in-android
     * */
    public static String getDeviceDensity(Context context) {
        String deviceDensity;
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                deviceDensity = "ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                deviceDensity = "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                deviceDensity = "hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                deviceDensity = "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                deviceDensity = "xxhdpi";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                deviceDensity = "xxxhdpi";
                break;
            default:
                deviceDensity = "xxxhdpi";
        }
        return deviceDensity.toUpperCase();
    }

    public static String getLanguage(Context context) {
        return getLocale(context).toString().replace("_", "-");
    }

    /*Code to get Locale from phone
     *https://stackoverflow.com/questions/14389349/android-get-current-locale-not-default
     */
    public static Locale getLocale(Context context) {
        return context.getResources().getConfiguration().locale;
    }

    /*Code to check internet access
     * https://www.programcreek.com/java-api-examples/android.net.NetworkInfo
     * */
    public static boolean hasInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.isDefaultNetworkActive();
    }

    public static int numberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }

}

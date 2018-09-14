package com.udacity.android.popularmoviesapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;

import java.util.Locale;

public class DeviceUtils {

    public static String getDeviceDensity(Context context){
        String deviceDensity;
        switch (context.getResources().getDisplayMetrics().densityDpi) {
            case DisplayMetrics.DENSITY_LOW:
                deviceDensity =  "ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                deviceDensity =  "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                deviceDensity =  "hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                deviceDensity =  "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                deviceDensity = "xxhdpi";
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                deviceDensity =  "xxxhdpi";
                break;
            default:
                deviceDensity = "xxxhdpi";
        }
        return deviceDensity.toUpperCase();
    }

    public static String getLanguage(Context context){
        return getLocale(context).toString().replace("_", "-");
    }

    public static Locale getLocale(Context context){
        return context.getResources().getConfiguration().locale;
    }

    public static boolean hasInternet(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager)context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.isDefaultNetworkActive();
    }

}

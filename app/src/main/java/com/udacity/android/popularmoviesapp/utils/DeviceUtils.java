package com.udacity.android.popularmoviesapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DeviceUtils {

    public static String getDeviceDensity(Context context){
        String deviceDensity = "";
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

}

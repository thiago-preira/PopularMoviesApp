package com.udacity.android.popularmoviesapp.service;

import android.support.annotation.StringDef;
import android.util.DisplayMetrics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ImageSize {

    public static final String W92 = "w92";
    public static final String W154 = "w154";
    public static final String W185 = "w185";
    public static final String W342 = "w342";
    public static final String W500 = "w500";
    public static final String W780 ="w780";


    @StringDef({W92, W154, W185, W342, W500, W780})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Size {
    }

    public ImageSize(@Size String size) {}

    public static String getSizeByDensity(int density){
        switch (density){
            case DisplayMetrics.DENSITY_LOW:
                return W92;
            case DisplayMetrics.DENSITY_MEDIUM:
                return W154;
            case DisplayMetrics.DENSITY_HIGH:
                return W185;
            case DisplayMetrics.DENSITY_XHIGH:
                return W342;
            case DisplayMetrics.DENSITY_XXHIGH:
                return W500;
            case DisplayMetrics.DENSITY_XXXHIGH:
                return W780;
            default:
                return W780;
        }
    }
}

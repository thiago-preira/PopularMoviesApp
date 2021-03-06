package com.udacity.android.popularmoviesapp.utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpUtils {

    private HttpUtils() {
    }

    private static final String TAG = HttpUtils.class.getSimpleName();

    public static String fetchDataFrom(Uri uri) {
        try {
            URL url = buildUrl(uri);
            return getResponseFromHttpUrl(url);
        } catch (IOException e) {
            Log.e(TAG, String.format("Error fetching data from URI: %s", uri));
            return null;
        }
    }

    private static URL buildUrl(Uri uri) throws MalformedURLException {
        URL url = new URL(uri.toString());
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    /*Code from SUNSHINE APP
     *https://github.com/udacity/ud851-Sunshine
     * */
    private static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}

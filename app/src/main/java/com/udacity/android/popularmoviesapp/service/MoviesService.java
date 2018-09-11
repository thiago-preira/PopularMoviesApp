package com.udacity.android.popularmoviesapp.service;

import android.net.Uri;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.udacity.android.popularmoviesapp.BuildConfig;
import com.udacity.android.popularmoviesapp.domain.Movie;
import com.udacity.android.popularmoviesapp.domain.MovieResponse;
import com.udacity.android.popularmoviesapp.utils.HttpUtils;

import java.util.List;

public class MoviesService {

    private static final Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    private static final String TAG = MoviesService.class.getSimpleName();

    private static final String MOVIES_BASE_URL = "http://api.themoviedb.org/3/movie/%s";

    private static final String API_KEY_PARAM = "api_key";

    private static final String MOST_POPULAR = "popular";

    private static final String MOST_RATED = "top_rated";

    private static String movieApiKey = BuildConfig.MovieDBApiKey;

    private MoviesService() {
    }

    private static Uri buildUrl(String sortStrategy) {
        return Uri.parse(String.format(MOVIES_BASE_URL,sortStrategy))
                .buildUpon()
                .appendQueryParameter(API_KEY_PARAM, movieApiKey)
                .build();
    }

    public static List<Movie> popularMovies() {
        Uri popularMoviesUri = buildUrl(MOST_POPULAR);
        String mostPopularMoviesJson = HttpUtils.fetchDataFrom(popularMoviesUri);
        return parseJSON(mostPopularMoviesJson).getResults();
    }

    public static List<Movie> topRatedMovies() {
        Uri topRatedMoviesUri = buildUrl(MOST_RATED);
        String topRatedMoviesJson = HttpUtils.fetchDataFrom(topRatedMoviesUri);
        return parseJSON(topRatedMoviesJson).getResults();
    }

    private static MovieResponse parseJSON(String json) {
        return gson.fromJson(json, MovieResponse.class);
    }

    public enum ImagesSizes{
        LDPI("w92"),MDPI("w154"),HDPI("w185"), XHDPI("w342"),
        XXHDPI("w500"), XXXHDPI("w780");

        private String imageSize;
        ImagesSizes(String imageSize) {
            this.imageSize = imageSize;
        }

        public String getImageSize() {
            return imageSize;
        }
    }

}

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

    private static final String LANGUAGE_PARAM = "language";

    private static final String DEFAULT_LANGUAGE = "pt-BR";

    public static final String MOST_POPULAR = "popular";

    public static final String MOST_RATED = "top_rated";

    private static final String movieApiKey = BuildConfig.MovieDBApiKey;

    private MoviesService() {
    }

    private static Uri buildUrl(String sortStrategy, String locale) {
        return Uri.parse(String.format(MOVIES_BASE_URL, sortStrategy))
                .buildUpon()
                .appendQueryParameter(API_KEY_PARAM, movieApiKey)
                .appendQueryParameter(LANGUAGE_PARAM, locale)
                .build();
    }


    public static List<Movie> getMovies(String locale, String filter) {
        if(filter.equals(MOST_RATED)) {
            return topRatedMovies(locale);
        }else{
            return popularMovies(locale);
        }
    }

    private static List<Movie> popularMovies(String locale) {
        Uri popularMoviesUri = buildUrl(MOST_POPULAR, locale);
        String mostPopularMoviesJson = HttpUtils.fetchDataFrom(popularMoviesUri);
        return parseJSON(mostPopularMoviesJson).getResults();
    }

    public static List<Movie> popularMovies() {
        return popularMovies(DEFAULT_LANGUAGE);
    }

    private static List<Movie> topRatedMovies(String locale) {
        Uri topRatedMoviesUri = buildUrl(MOST_RATED, locale);
        String topRatedMoviesJson = HttpUtils.fetchDataFrom(topRatedMoviesUri);
        return parseJSON(topRatedMoviesJson).getResults();
    }

    private static MovieResponse parseJSON(String json) {
        return gson.fromJson(json, MovieResponse.class);
    }

}

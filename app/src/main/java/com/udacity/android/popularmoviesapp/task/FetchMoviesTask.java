package com.udacity.android.popularmoviesapp.task;

import android.os.AsyncTask;

import com.udacity.android.popularmoviesapp.domain.Movie;
import com.udacity.android.popularmoviesapp.service.MoviesService;

import java.util.List;

public class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

    private TaskCallback<Movie> taskCallback;

    public FetchMoviesTask(TaskCallback taskCallback) {
        this.taskCallback = taskCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        taskCallback.onTaskStart();
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        if (params.length == 0) {
            return MoviesService.popularMovies();
        }
        String locale = params[0];
        String filter = params[1];

        return MoviesService.getMovies(locale, MoviesService.Filter.valueOf(filter));
    }

    @Override
    protected void onPostExecute(List<Movie> moviesData) {
        taskCallback.onTaskComplete(moviesData);
    }
}
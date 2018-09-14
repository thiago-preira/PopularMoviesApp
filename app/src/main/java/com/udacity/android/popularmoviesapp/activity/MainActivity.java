package com.udacity.android.popularmoviesapp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.udacity.android.popularmoviesapp.MovieDetailActivity;
import com.udacity.android.popularmoviesapp.R;
import com.udacity.android.popularmoviesapp.adapter.MoviesAdapter;
import com.udacity.android.popularmoviesapp.domain.Movie;
import com.udacity.android.popularmoviesapp.service.MoviesService;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements MoviesAdapter.MoviesAdapterOnClickHandler{

    private static final int PORTRAIT_NUM_COLUMNS = 2;
    private static final int LANDSCAPE_NUM_COLUMNS = 4;

    private RecyclerView mMoviesRecyclerView;
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private MoviesAdapter mMoviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMoviesRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_movies);
        mErrorMessageDisplay = (TextView) findViewById(R.id.tv_error_message_display);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this, PORTRAIT_NUM_COLUMNS));
        }
        else{
            mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this, LANDSCAPE_NUM_COLUMNS));
        }



        mMoviesRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(this);
        mMoviesRecyclerView.setAdapter(mMoviesAdapter);
        loadMovieData();
    }

    private void loadMovieData() {
        showMoviesData();
        Locale current = getResources().getConfiguration().locale;
        new FetchMoviesTask().execute(current.toString().replace("_","-"));
    }

    private void showMoviesData() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mMoviesRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mMoviesRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
        Class destinationClass = MovieDetailActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra("Movie", movie);
        startActivity(intentToStartDetailActivity);
    }

    class FetchMoviesTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {
            if(params.length==0){
                return MoviesService.popularMovies();
            }
            String locale = params[0];
            return MoviesService.popularMovies(locale);
        }

        @Override
        protected void onPostExecute(List<Movie> moviesData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (moviesData != null) {
                showMoviesData();
                mMoviesAdapter.setMoviesData(moviesData);
            } else {
                showErrorMessage();
            }
        }
    }

}

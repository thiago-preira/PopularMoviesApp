package com.udacity.android.popularmoviesapp.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.android.popularmoviesapp.MovieDetailActivity;
import com.udacity.android.popularmoviesapp.R;
import com.udacity.android.popularmoviesapp.adapter.MoviesAdapter;
import com.udacity.android.popularmoviesapp.domain.Movie;
import com.udacity.android.popularmoviesapp.service.MoviesService;
import com.udacity.android.popularmoviesapp.task.FetchMoviesTask;
import com.udacity.android.popularmoviesapp.task.TaskCallback;
import com.udacity.android.popularmoviesapp.utils.DeviceUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements MoviesAdapter.MoviesAdapterOnClickHandler,
        TaskCallback<Movie>{

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

        int mNoOfColumns = DeviceUtils.numberOfColumns(this);
        mMoviesRecyclerView.setLayoutManager(new GridLayoutManager(this, mNoOfColumns));

        mMoviesRecyclerView.setHasFixedSize(true);

        mMoviesAdapter = new MoviesAdapter(this);
        mMoviesRecyclerView.setAdapter(mMoviesAdapter);
        loadMovieData(MoviesService.Filter.POPULAR);
    }


    private void loadMovieData(MoviesService.Filter filter) {
        Context context = getApplicationContext();
        if (DeviceUtils.hasInternet(context)) {
            showMoviesData();
            new FetchMoviesTask(MainActivity.this)
                    .execute(DeviceUtils.getLanguage(context), filter.name());
        } else {
            showErrorMessage();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.top_rated:
                mMoviesAdapter.setMoviesData(null);
                loadMovieData(MoviesService.Filter.TOP_RATED);
                break;
            case R.id.popular:
                mMoviesAdapter.setMoviesData(null);
                loadMovieData(MoviesService.Filter.POPULAR);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTaskStart() {
        mLoadingIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTaskComplete(List<Movie> result) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);
        if (result != null) {
            showMoviesData();
            mMoviesAdapter.setMoviesData(result);
        } else {
            showErrorMessage();
        }
    }
}

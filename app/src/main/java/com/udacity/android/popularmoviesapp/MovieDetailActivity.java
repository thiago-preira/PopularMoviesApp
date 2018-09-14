package com.udacity.android.popularmoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmoviesapp.domain.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView mMoviePosterImageView;
    private TextView mMovieTitle;
    private TextView mMovieOverview;
    private TextView mMovieVoteAverage;
    private TextView mMovieReleaseDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMoviePosterImageView = (ImageView) findViewById(R.id.iv_movie_poster_detail);
        mMovieTitle = (TextView) findViewById(R.id.tv_movie_title);
        mMovieOverview = (TextView) findViewById(R.id.tv_movie_overview);
        mMovieVoteAverage = (TextView) findViewById(R.id.tv_movie_vote_average);
        mMovieReleaseDate = (TextView) findViewById(R.id.tv_movie_release_date);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("Movie")) {
                Movie movie = (Movie) intentThatStartedThisActivity.getSerializableExtra("Movie");
                Picasso.with(this).load(movie.getPoster("w500")).into(mMoviePosterImageView);
                mMovieTitle.setText(movie.getTitle());
                mMovieOverview.setText(movie.getOverview());
                mMovieVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
                mMovieReleaseDate.setText(String.format("(%s)",movie.getReleaseDate().substring(0,4)));
            }
        }

    }
}

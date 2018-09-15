package com.udacity.android.popularmoviesapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmoviesapp.R;
import com.udacity.android.popularmoviesapp.domain.Movie;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.iv_movie_poster_detail)
    ImageView mMoviePosterImageView;
    @BindView(R.id.tv_movie_title)
    TextView mMovieTitle;
    @BindView(R.id.tv_movie_overview)
    TextView mMovieOverview;
    @BindView(R.id.tv_movie_vote_average)
    TextView mMovieVoteAverage;
    @BindView(R.id.tv_movie_release_date)
    TextView mMovieReleaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra("Movie")) {
                Movie movie = (Movie)  Parcels.unwrap(intentThatStartedThisActivity.getParcelableExtra("Movie"));
                Picasso.with(this).load(movie.getPoster("w500")).into(mMoviePosterImageView);
                mMovieTitle.setText(movie.getTitle());
                mMovieOverview.setText(movie.getOverview());
                mMovieVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
                mMovieReleaseDate.setText(String.format("(%s)", movie.getReleaseDate().substring(0, 4)));
            }
        }

    }
}

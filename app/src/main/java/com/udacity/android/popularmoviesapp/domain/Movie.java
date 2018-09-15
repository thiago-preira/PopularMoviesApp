package com.udacity.android.popularmoviesapp.domain;

import org.parceler.Parcel;

import java.util.Arrays;

@Parcel
public class Movie {
    float voteAverage;
    String title;
    String posterPath;
    String overview;
    String releaseDate;

    public Movie() {
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    @Override
    public String toString() {
        return "Movie{" +
                " voteAverage=" + voteAverage +
                ", title='" + title + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", overview='" + overview + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

    public String getPoster(String size) {
        return String.format("http://image.tmdb.org/t/p/%s/%s", size, posterPath);
    }
}

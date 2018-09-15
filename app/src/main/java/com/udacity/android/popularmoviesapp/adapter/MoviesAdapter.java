package com.udacity.android.popularmoviesapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.android.popularmoviesapp.R;
import com.udacity.android.popularmoviesapp.domain.Movie;
import com.udacity.android.popularmoviesapp.service.ImageSize;
import com.udacity.android.popularmoviesapp.utils.DeviceUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesAdapterViewHolder> {

    private List<Movie> mMoviesData;
    private final MoviesAdapterOnClickHandler mClickHandler;


    public interface MoviesAdapterOnClickHandler {
        void onClick(Movie movie);
    }

    public MoviesAdapter(MoviesAdapterOnClickHandler mClickHandler) {
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public MoviesAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.movie_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MoviesAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapterViewHolder holder, int position) {
        Movie movie = mMoviesData.get(position);
        Context context = holder.itemView.getContext();
        int deviceDensity = DeviceUtils.getDeviceDensity(context);
        String imageSize = ImageSize.getSizeByDensity(deviceDensity);
        Picasso.with(context).load(movie.getPoster(imageSize)).into(holder.mMoviePosterImageView);
    }

    @Override
    public int getItemCount() {
        if (mMoviesData == null) return 0;
        return mMoviesData.size();
    }

    public class MoviesAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_movie_poster)
        ImageView mMoviePosterImageView;

        public MoviesAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = mMoviesData.get(adapterPosition);
            mClickHandler.onClick(movie);
        }
    }

    public void setMoviesData(List<Movie> moviesData) {
        this.mMoviesData = moviesData;
        notifyDataSetChanged();
    }
}

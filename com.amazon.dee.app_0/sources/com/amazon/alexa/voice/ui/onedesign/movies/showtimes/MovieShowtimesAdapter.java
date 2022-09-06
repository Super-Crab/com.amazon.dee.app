package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes11.dex */
public final class MovieShowtimesAdapter extends RecyclerView.Adapter<MovieShowtimesTheaterViewHolder> {
    private final List<MovieShowtimesTheaterModel> items = new ArrayList();
    private OnMovieTheaterClickListener onMovieTheaterClickListener;

    /* loaded from: classes11.dex */
    public interface OnMovieTheaterClickListener {
        void onMovieTheaterClicked(MovieShowtimesTheaterModel movieShowtimesTheaterModel);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void set(Collection<? extends MovieShowtimesTheaterModel> collection) {
        this.items.clear();
        this.items.addAll(collection);
        notifyDataSetChanged();
    }

    public void setOnMovieTheaterClickListener(OnMovieTheaterClickListener onMovieTheaterClickListener) {
        this.onMovieTheaterClickListener = onMovieTheaterClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(MovieShowtimesTheaterViewHolder movieShowtimesTheaterViewHolder, int i) {
        movieShowtimesTheaterViewHolder.bind(this.items.get(i), this.onMovieTheaterClickListener);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public MovieShowtimesTheaterViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MovieShowtimesTheaterViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }
}

package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesAdapter;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class MovieShowtimesTheaterViewHolder extends RecyclerView.ViewHolder {
    private final TextView subTitleView;
    private final TextView titleView;

    public MovieShowtimesTheaterViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_movie_showtimes_theater, viewGroup, false));
        this.titleView = (TextView) this.itemView.findViewById(R.id.showtimesTheaterTitle);
        this.subTitleView = (TextView) this.itemView.findViewById(R.id.showtimesTheaterSubTitle);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.titleView, this.subTitleView);
    }

    public void bind(final MovieShowtimesTheaterModel movieShowtimesTheaterModel, final MovieShowtimesAdapter.OnMovieTheaterClickListener onMovieTheaterClickListener) {
        if (onMovieTheaterClickListener == null) {
            this.itemView.setOnClickListener(null);
        } else {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.-$$Lambda$MovieShowtimesTheaterViewHolder$mcERdS2VBkWXhmavwmASlMOhMBg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MovieShowtimesAdapter.OnMovieTheaterClickListener.this.onMovieTheaterClicked(movieShowtimesTheaterModel);
                }
            });
        }
        this.titleView.setText(movieShowtimesTheaterModel.getTitle());
        this.subTitleView.setText(movieShowtimesTheaterModel.getSubTitle());
    }
}

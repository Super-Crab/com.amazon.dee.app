package com.amazon.alexa.voice.ui.onedesign.movies.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListAdapter;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageLoader;
import com.amazon.alexa.voice.ui.onedesign.util.image.RoundedCornerImageLoader;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class MoviesListPosterViewHolder extends RecyclerView.ViewHolder {
    private final TextView descriptionView;
    private final TextView detailsView;
    private final ImageView imageView;
    private final ImageLoader imageViewLoader;
    private final TextView titleView;

    public MoviesListPosterViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_movies_list_poster, viewGroup, false));
        this.imageView = (ImageView) this.itemView.findViewById(R.id.image);
        this.titleView = (TextView) this.itemView.findViewById(R.id.posterTitle);
        this.descriptionView = (TextView) this.itemView.findViewById(R.id.description);
        this.detailsView = (TextView) this.itemView.findViewById(R.id.details);
        FontUtils.apply(Font.AMAZON_EMBER_MEDIUM, this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.detailsView, this.descriptionView);
        this.imageViewLoader = new RoundedCornerImageLoader(this.imageView);
    }

    public void bind(final MoviesListPosterModel moviesListPosterModel, final MoviesListAdapter.OnMoviePosterClickListener onMoviePosterClickListener) {
        this.imageViewLoader.unload();
        if (moviesListPosterModel.getImageUrl() == null) {
            this.imageView.setImageBitmap(null);
        } else {
            this.imageViewLoader.load(moviesListPosterModel.getImageUrl());
        }
        this.titleView.setText(moviesListPosterModel.getTitle());
        ViewUtils.setTextOrRemove(this.descriptionView, moviesListPosterModel.getRating(), moviesListPosterModel.getRatingContentDescription());
        ViewUtils.setTextOrRemove(this.detailsView, moviesListPosterModel.getReviewScore(), moviesListPosterModel.getReviewScoreContentDescription());
        if (onMoviePosterClickListener == null) {
            this.itemView.setOnClickListener(null);
        } else {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$MoviesListPosterViewHolder$-foevDcmEuDNEqFY-ZDxtlrsRHk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoviesListAdapter.OnMoviePosterClickListener.this.onMoviePosterClicked(moviesListPosterModel);
                }
            });
        }
    }
}

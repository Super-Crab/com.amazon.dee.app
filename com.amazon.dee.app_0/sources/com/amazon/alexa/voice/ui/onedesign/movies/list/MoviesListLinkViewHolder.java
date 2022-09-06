package com.amazon.alexa.voice.ui.onedesign.movies.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListAdapter;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class MoviesListLinkViewHolder extends RecyclerView.ViewHolder {
    private final TextView textView;

    public MoviesListLinkViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.voice_ui_od_movies_list_link, viewGroup, false));
        this.textView = (TextView) this.itemView.findViewById(R.id.text);
        FontUtils.apply(Font.AMAZON_EMBER_MEDIUM, this.textView);
    }

    public void bind(final MoviesListLinkModel moviesListLinkModel, final MoviesListAdapter.OnMovieLinkClickListener onMovieLinkClickListener) {
        this.textView.setText(moviesListLinkModel.getText());
        if (onMovieLinkClickListener == null) {
            this.itemView.setOnClickListener(null);
        } else {
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$MoviesListLinkViewHolder$LMCV_wo-7sPaaPXO1ciaJmTP100
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MoviesListAdapter.OnMovieLinkClickListener.this.onMovieLinkClicked(moviesListLinkModel);
                }
            });
        }
    }
}

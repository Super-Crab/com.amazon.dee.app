package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public abstract class MovieTheaterViewHolder<Model> extends RecyclerView.ViewHolder {
    public MovieTheaterViewHolder(View view) {
        super(view);
    }

    public abstract void bind(Model model);
}

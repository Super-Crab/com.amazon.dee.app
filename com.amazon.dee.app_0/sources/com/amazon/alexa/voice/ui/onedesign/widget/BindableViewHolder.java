package com.amazon.alexa.voice.ui.onedesign.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public abstract class BindableViewHolder<T> extends RecyclerView.ViewHolder {
    public BindableViewHolder(View view) {
        super(view);
    }

    public abstract void bind(T t);
}

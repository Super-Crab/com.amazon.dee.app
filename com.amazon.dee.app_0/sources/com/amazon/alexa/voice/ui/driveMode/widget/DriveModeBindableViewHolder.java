package com.amazon.alexa.voice.ui.driveMode.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes11.dex */
public abstract class DriveModeBindableViewHolder<T> extends RecyclerView.ViewHolder {
    public DriveModeBindableViewHolder(View view) {
        super(view);
    }

    public abstract void bind(T t, int i);
}

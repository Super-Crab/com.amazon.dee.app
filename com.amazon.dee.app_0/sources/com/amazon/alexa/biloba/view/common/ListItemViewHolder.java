package com.amazon.alexa.biloba.view.common;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.view.common.ListItemClickListener;
/* loaded from: classes6.dex */
public abstract class ListItemViewHolder<T, U extends ListItemClickListener<T>> extends RecyclerView.ViewHolder {
    public ListItemViewHolder(@NonNull View view) {
        super(view);
    }

    public abstract void bind(T t, U u);
}

package com.amazon.dee.app.ui.util;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
/* loaded from: classes12.dex */
public abstract class ViewDelegate<VH extends RecyclerView.ViewHolder> {
    public long getItemId(Object obj, int i) {
        return -1L;
    }

    public int getItemViewType() {
        return 0;
    }

    public abstract boolean isForItem(Object obj, int i);

    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    }

    public abstract void onBindViewHolder(VH vh, Object obj, int i);

    public abstract VH onCreateViewHolder(ViewGroup viewGroup);

    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    }
}

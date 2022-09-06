package com.amazon.alexa.biloba.view.common.recycler;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
/* loaded from: classes6.dex */
public class DiffCallback extends DiffUtil.ItemCallback<BaseRecyclerItem> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areContentsTheSame(@NonNull BaseRecyclerItem baseRecyclerItem, @NonNull BaseRecyclerItem baseRecyclerItem2) {
        return baseRecyclerItem.equals(baseRecyclerItem2);
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public boolean areItemsTheSame(@NonNull BaseRecyclerItem baseRecyclerItem, @NonNull BaseRecyclerItem baseRecyclerItem2) {
        return baseRecyclerItem.equals(baseRecyclerItem2);
    }
}

package com.amazon.alexa.biloba.view.common.recycler;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes6.dex */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding binding;

    public RecyclerItemViewHolder(@NonNull ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());
        this.binding = viewDataBinding;
    }

    public void bind(@NonNull BaseRecyclerItem baseRecyclerItem) {
        if (this.binding == null || baseRecyclerItem.getVariableId() == -1) {
            return;
        }
        this.binding.setVariable(baseRecyclerItem.getVariableId(), baseRecyclerItem);
        this.binding.executePendingBindings();
    }

    public RecyclerItemViewHolder(@NonNull View view) {
        super(view);
    }
}

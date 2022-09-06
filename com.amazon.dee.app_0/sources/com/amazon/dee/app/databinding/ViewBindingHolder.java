package com.amazon.dee.app.databinding;

import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes12.dex */
public class ViewBindingHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public final T binding;

    public ViewBindingHolder(T t) {
        super(t.getRoot());
        this.binding = t;
    }
}

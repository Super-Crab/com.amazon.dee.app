package com.amazon.alexa.biloba.view.common.recycler;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.ListAdapter;
import java.util.List;
/* loaded from: classes6.dex */
public class RecyclerViewAdapter extends ListAdapter<BaseRecyclerItem, RecyclerItemViewHolder> {
    private LayoutInflater inflater;

    public RecyclerViewAdapter() {
        super(new DiffCallback());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return getItem(i).getLayoutId();
    }

    public synchronized void updateItems(@NonNull List<BaseRecyclerItem> list) {
        submitList(list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerItemViewHolder recyclerItemViewHolder, int i) {
        recyclerItemViewHolder.bind(getItem(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public RecyclerItemViewHolder mo7499onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = this.inflater;
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        this.inflater = layoutInflater;
        ViewDataBinding inflate = DataBindingUtil.inflate(this.inflater, i, viewGroup, false);
        if (inflate != null) {
            return new RecyclerItemViewHolder(inflate);
        }
        return new RecyclerItemViewHolder(this.inflater.inflate(i, viewGroup, false));
    }
}

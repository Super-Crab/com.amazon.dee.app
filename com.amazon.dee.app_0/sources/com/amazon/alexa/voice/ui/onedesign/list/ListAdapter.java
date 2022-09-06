package com.amazon.alexa.voice.ui.onedesign.list;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class ListAdapter extends RecyclerView.Adapter<ListItemViewHolder> {
    private final List<ListItemModel> itemList = new ArrayList();

    public void add(ListItemModel listItemModel) {
        this.itemList.add(listItemModel);
        notifyItemInserted(this.itemList.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ListItemViewHolder listItemViewHolder, int i) {
        listItemViewHolder.bind(this.itemList.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public ListItemViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ListItemViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
    }
}

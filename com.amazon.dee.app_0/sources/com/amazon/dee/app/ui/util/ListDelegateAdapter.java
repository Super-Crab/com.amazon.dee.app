package com.amazon.dee.app.ui.util;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.List;
/* loaded from: classes12.dex */
public class ListDelegateAdapter extends DelegateAdapter {
    protected List<?> items;

    public ListDelegateAdapter(@NonNull List<?> list) {
        this.items = list;
    }

    @Override // com.amazon.dee.app.ui.util.DelegateAdapter
    public Object getItem(int i) {
        return this.items.get(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    public void setItems(List<?> list) {
        this.items = list;
        notifyDataSetChanged();
    }

    public ListDelegateAdapter() {
        this.items = Collections.emptyList();
    }
}

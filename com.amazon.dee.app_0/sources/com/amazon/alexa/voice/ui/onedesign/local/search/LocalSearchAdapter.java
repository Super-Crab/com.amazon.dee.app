package com.amazon.alexa.voice.ui.onedesign.local.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.space.EmptySpace;
import com.amazon.alexa.voice.ui.onedesign.space.EmptySpaceViewHolder;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class LocalSearchAdapter extends RecyclerView.Adapter<BindableViewHolder> implements DividerDecoration.DividerDecision {
    private static final int SEARCH_ITEM = 1;
    private static final int SPACE = 0;
    private final OnItemClickListener itemClickListener;
    private final List<Object> items = new ArrayList();
    private final Resources resources;

    /* loaded from: classes11.dex */
    public interface OnItemClickListener {
        void onItemClicked(LocalSearchItemModel localSearchItemModel);
    }

    public LocalSearchAdapter(Resources resources, OnItemClickListener onItemClickListener) {
        this.resources = resources;
        this.itemClickListener = onItemClickListener;
    }

    public void add(Object obj) {
        this.items.add(obj);
        notifyItemInserted(this.items.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Object obj = this.items.get(i);
        if (obj instanceof LocalSearchItemModel) {
            return 1;
        }
        if (!(obj instanceof EmptySpace)) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Unknown item type ")));
        }
        return 0;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration.DividerDecision
    public boolean shouldDrawDivider(int i) {
        return (getItemViewType(i) == 0 || i == getItemCount() - 1) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BindableViewHolder bindableViewHolder, int i) {
        bindableViewHolder.bind(this.items.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public BindableViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i != 0) {
            if (i == 1) {
                return new LocalSearchItemViewHolder(from, R.layout.voice_ui_od_local_search_item_single_data_source, viewGroup, this.itemClickListener, this.resources);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unknown view type ", i));
        }
        return new EmptySpaceViewHolder(from, R.layout.voice_ui_od_local_search_space, viewGroup);
    }
}

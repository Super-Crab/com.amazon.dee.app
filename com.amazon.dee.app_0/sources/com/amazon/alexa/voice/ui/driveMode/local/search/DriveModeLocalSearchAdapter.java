package com.amazon.alexa.voice.ui.driveMode.local.search;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.driveMode.R;
import com.amazon.alexa.voice.ui.driveMode.space.DriveModeEmptySpaceViewHolder;
import com.amazon.alexa.voice.ui.driveMode.space.EmptySpace;
import com.amazon.alexa.voice.ui.driveMode.widget.DriveModeBindableViewHolder;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchItemModel;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class DriveModeLocalSearchAdapter extends RecyclerView.Adapter<DriveModeBindableViewHolder> implements DividerDecoration.DividerDecision {
    private static final int ITEM_MIN_SIZE = 4;
    private static final int ITEM_SEARCH = 1;
    private static final int ITEM_SPACE = 0;
    private final OnItemClickListener itemClickListener;
    private final List<Object> items = new ArrayList();
    private final Resources resources;

    /* loaded from: classes11.dex */
    public interface OnItemClickListener {
        void onItemClicked(LocalSearchItemModel localSearchItemModel);
    }

    public DriveModeLocalSearchAdapter(Resources resources, OnItemClickListener onItemClickListener) {
        this.resources = resources;
        this.itemClickListener = onItemClickListener;
    }

    public void add(Object obj) {
        this.items.add(obj);
        notifyItemInserted(this.items.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Math.min(this.items.size(), 4);
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
        return (getItemViewType(i) == 0 || i == getItemCount()) ? false : true;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(DriveModeBindableViewHolder driveModeBindableViewHolder, int i) {
        driveModeBindableViewHolder.bind(this.items.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public DriveModeBindableViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i != 0) {
            if (i == 1) {
                return new DriveModeLocalSearchItemViewHolder(from, R.layout.voice_ui_drive_mode_local_search_item_single_data_source, viewGroup, this.itemClickListener, this.resources);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unknown view type ", i));
        }
        return new DriveModeEmptySpaceViewHolder(from, R.layout.voice_ui_drive_mode_local_search_space, viewGroup);
    }
}

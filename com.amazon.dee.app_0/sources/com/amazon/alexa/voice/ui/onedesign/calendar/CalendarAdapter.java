package com.amazon.alexa.voice.ui.onedesign.calendar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.space.EmptySpace;
import com.amazon.alexa.voice.ui.onedesign.space.EmptySpaceViewHolder;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class CalendarAdapter extends RecyclerView.Adapter<BindableViewHolder> {
    private static final int EVENT = 1;
    private static final int SEPARATOR = 3;
    private static final int SPACE = 2;
    private final OnEventClickListener mEventClickListener;
    private final List<Object> mItemList = new ArrayList();
    private final Resources mResources;

    /* loaded from: classes11.dex */
    public interface OnEventClickListener {
        void onEventClicked(CalendarEventModel calendarEventModel);
    }

    public CalendarAdapter(Resources resources, OnEventClickListener onEventClickListener) {
        this.mEventClickListener = onEventClickListener;
        this.mResources = resources;
    }

    public void add(Object obj) {
        this.mItemList.add(obj);
        notifyItemInserted(this.mItemList.size() - 1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mItemList.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        Object obj = this.mItemList.get(i);
        if (obj instanceof CalendarEventModel) {
            return 1;
        }
        if (obj instanceof CalendarSeparator) {
            return 3;
        }
        if (!(obj instanceof EmptySpace)) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Unknown type of an item ")));
        }
        return 2;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BindableViewHolder bindableViewHolder, int i) {
        bindableViewHolder.bind(this.mItemList.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public BindableViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i != 1) {
            if (i == 2) {
                return new EmptySpaceViewHolder(from, viewGroup);
            }
            if (i == 3) {
                return new CalendarSeparatorViewHolder(from, viewGroup);
            }
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unsupported type of an item ", i));
        }
        return new CalendarEventViewHolder(from, viewGroup, this.mEventClickListener, this.mResources);
    }
}

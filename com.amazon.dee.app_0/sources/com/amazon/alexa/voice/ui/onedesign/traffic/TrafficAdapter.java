package com.amazon.alexa.voice.ui.onedesign.traffic;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.widget.BindableViewHolder;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public final class TrafficAdapter extends RecyclerView.Adapter<BindableViewHolder> {
    private static final int PRIMARY = 0;
    private static final int SECONDARY = 1;
    private final List<Object> items = new ArrayList();

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
        if (obj instanceof TrafficRouteModel) {
            return !((TrafficRouteModel) obj).isPrimary();
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline44(obj, GeneratedOutlineSupport1.outline107("Unknown type of an item ")));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(BindableViewHolder bindableViewHolder, int i) {
        bindableViewHolder.bind(this.items.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder  reason: collision with other method in class */
    public BindableViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        boolean z = true;
        if (i != 0 && i != 1) {
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unknown view type ", i));
        }
        if (i != 0) {
            z = false;
        }
        return new TrafficViewHolder(from, viewGroup, z);
    }
}

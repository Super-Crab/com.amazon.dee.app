package com.amazon.dee.app.ui.util;

import android.util.SparseArray;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.dee.app.R;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
/* loaded from: classes12.dex */
public abstract class DelegateAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    SparseArray<ViewDelegate<? extends RecyclerView.ViewHolder>> delegates = new SparseArray<>();

    ViewDelegate<? extends RecyclerView.ViewHolder> getDelegateByPosition(int i) {
        Object item = getItem(i);
        int size = this.delegates.size();
        for (int i2 = 0; i2 < size; i2++) {
            ViewDelegate<? extends RecyclerView.ViewHolder> valueAt = this.delegates.valueAt(i2);
            if (valueAt.isForItem(item, i)) {
                return valueAt;
            }
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("No registered delegate was found for item at ", i));
    }

    ViewDelegate<? extends RecyclerView.ViewHolder> getDelegateByViewHolder(RecyclerView.ViewHolder viewHolder) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Delegate was not instantiated properly during use for ");
        outline107.append(viewHolder.getClass());
        return (ViewDelegate) Objects.requireNonNull((ViewDelegate) viewHolder.itemView.getTag(R.id.delegate), outline107.toString());
    }

    ViewDelegate<? extends RecyclerView.ViewHolder> getDelegateByViewType(int i) {
        ViewDelegate<? extends RecyclerView.ViewHolder> viewDelegate = this.delegates.get(i);
        return (ViewDelegate) Objects.requireNonNull(viewDelegate, "Delegate is not registered for view type: " + i);
    }

    public abstract Object getItem(int i);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return getDelegateByPosition(i).getItemId(getItem(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return getDelegateByPosition(i).getItemViewType();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        int size = this.delegates.size();
        for (int i = 0; i < size; i++) {
            this.delegates.valueAt(i).onAttachedToRecyclerView(recyclerView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        getDelegateByViewHolder(viewHolder).onBindViewHolder(viewHolder, getItem(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: onCreateViewHolder */
    public RecyclerView.ViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewDelegate<? extends RecyclerView.ViewHolder> delegateByViewType = getDelegateByViewType(i);
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) Objects.requireNonNull(delegateByViewType.onCreateViewHolder(viewGroup), "onCreateViewHolder() returned null");
        viewHolder.itemView.setTag(R.id.delegate, delegateByViewType);
        return viewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        int size = this.delegates.size();
        for (int i = 0; i < size; i++) {
            this.delegates.valueAt(i).onDetachedFromRecyclerView(recyclerView);
        }
    }

    public <T extends RecyclerView.ViewHolder> void registerDelegate(ViewDelegate<T> viewDelegate) {
        int itemViewType = viewDelegate.getItemViewType();
        if (this.delegates.get(itemViewType) == null) {
            this.delegates.put(itemViewType, viewDelegate);
            return;
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Delegate is already registered for same view type: ", itemViewType));
    }

    public <T extends RecyclerView.ViewHolder> void unregisterDelegate(ViewDelegate<T> viewDelegate) {
        int size = this.delegates.size();
        for (int i = 0; i < size; i++) {
            if (this.delegates.valueAt(i) == viewDelegate) {
                this.delegates.removeAt(i);
                return;
            }
        }
    }
}

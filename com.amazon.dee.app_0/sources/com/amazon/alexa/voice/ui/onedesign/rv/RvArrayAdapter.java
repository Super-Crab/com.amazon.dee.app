package com.amazon.alexa.voice.ui.onedesign.rv;

import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.onedesign.rv.RvViewHolder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/* loaded from: classes11.dex */
public abstract class RvArrayAdapter<M, VH extends RvViewHolder> extends RecyclerView.Adapter<VH> {
    protected final List<M> items = new ArrayList();
    protected final RvItemsSpecs<M> rvItemsSpecs = new RvItemsSpecs<>();

    public RvArrayAdapter(List<M> list) {
        addAll(list);
    }

    private boolean isIndexValid(int i) {
        return i >= 0 && i < getItemCount();
    }

    public void add(M m) {
        add((RvArrayAdapter<M, VH>) m, false);
    }

    public void addAll(List<M> list) {
        addAll((List) list, false);
    }

    public void clear() {
        clear(false);
    }

    public boolean contains(M m) {
        return m != null && this.items.contains(m);
    }

    public M getItem(int i) {
        if (isIndexValid(i)) {
            return this.items.get(i);
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.items.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public List<M> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public int indexOf(M m) {
        return this.items.indexOf(m);
    }

    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    public void remove(M m) {
        remove((RvArrayAdapter<M, VH>) m, false);
    }

    public void removeAll(List<M> list) {
        removeAll(list, false);
    }

    public void set(int i, M m) {
        set(i, m, false);
    }

    public void setItems(List<M> list) {
        setItems(list, false);
    }

    public void add(M m, boolean z) {
        if (m != null) {
            this.items.add(m);
            this.rvItemsSpecs.add(m);
            if (!z) {
                return;
            }
            notifyItemInserted(getItemCount() - 1);
        }
    }

    public void addAll(List<M> list, boolean z) {
        if (list != null) {
            this.items.addAll(list);
            this.rvItemsSpecs.addAll(list);
            if (!z) {
                return;
            }
            notifyItemRangeInserted(getItemCount() - list.size(), list.size());
        }
    }

    public void clear(boolean z) {
        this.items.clear();
        this.rvItemsSpecs.clear();
        if (z) {
            notifyDataSetChanged();
        }
    }

    public void remove(M m, boolean z) {
        if (m != null) {
            this.items.removeAll(Collections.singleton(m));
            if (!z) {
                return;
            }
            notifyDataSetChanged();
        }
    }

    public void removeAll(List<M> list, boolean z) {
        if (list != null) {
            this.items.removeAll(list);
            if (!z) {
                return;
            }
            notifyDataSetChanged();
        }
    }

    public void set(int i, M m, boolean z) {
        if (m == null || !isIndexValid(i)) {
            return;
        }
        this.items.set(i, m);
        this.rvItemsSpecs.add(m);
        if (!z) {
            return;
        }
        notifyItemChanged(i);
    }

    public void setItems(List<M> list, boolean z) {
        if (list != null) {
            clear();
            addAll(list);
            if (!z) {
                return;
            }
            notifyDataSetChanged();
        }
    }

    public void remove(int i) {
        remove(i, false);
    }

    public void add(int i, M m) {
        add(i, m, false);
    }

    public void addAll(int i, List<M> list) {
        addAll(i, list, false);
    }

    public void remove(int i, boolean z) {
        if (isIndexValid(i)) {
            this.items.remove(i);
            if (!z) {
                return;
            }
            notifyItemRemoved(i);
        }
    }

    public void add(int i, M m, boolean z) {
        if (m == null || !isIndexValid(i)) {
            return;
        }
        this.items.add(i, m);
        this.rvItemsSpecs.add(m);
        if (!z) {
            return;
        }
        notifyItemInserted(i);
    }

    public void addAll(int i, List<M> list, boolean z) {
        if (list == null || !isIndexValid(i)) {
            return;
        }
        this.items.addAll(i, list);
        this.rvItemsSpecs.addAll(list);
        if (!z) {
            return;
        }
        notifyItemRangeInserted(i, list.size());
    }
}

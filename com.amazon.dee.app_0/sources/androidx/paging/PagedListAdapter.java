package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.AsyncPagedListDiffer;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
/* loaded from: classes.dex */
public abstract class PagedListAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    final AsyncPagedListDiffer<T> mDiffer;
    private final AsyncPagedListDiffer.PagedListListener<T> mListener = new AsyncPagedListDiffer.PagedListListener<T>() { // from class: androidx.paging.PagedListAdapter.1
        @Override // androidx.paging.AsyncPagedListDiffer.PagedListListener
        public void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2) {
            PagedListAdapter.this.onCurrentListChanged(pagedList2);
            PagedListAdapter.this.onCurrentListChanged(pagedList, pagedList2);
        }
    };

    protected PagedListAdapter(@NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mDiffer = new AsyncPagedListDiffer<>(this, itemCallback);
        this.mDiffer.addPagedListListener(this.mListener);
    }

    @Nullable
    public PagedList<T> getCurrentList() {
        return this.mDiffer.getCurrentList();
    }

    @Nullable
    protected T getItem(int i) {
        return this.mDiffer.getItem(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mDiffer.getItemCount();
    }

    @Deprecated
    public void onCurrentListChanged(@Nullable PagedList<T> pagedList) {
    }

    public void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2) {
    }

    public void submitList(@Nullable PagedList<T> pagedList) {
        this.mDiffer.submitList(pagedList);
    }

    public void submitList(@Nullable PagedList<T> pagedList, @Nullable Runnable runnable) {
        this.mDiffer.submitList(pagedList, runnable);
    }

    protected PagedListAdapter(@NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mDiffer = new AsyncPagedListDiffer<>(new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.mDiffer.addPagedListListener(this.mListener);
    }
}

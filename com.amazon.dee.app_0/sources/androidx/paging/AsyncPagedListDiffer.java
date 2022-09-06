package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public class AsyncPagedListDiffer<T> {
    final AsyncDifferConfig<T> mConfig;
    private boolean mIsContiguous;
    int mMaxScheduledGeneration;
    private PagedList<T> mPagedList;
    private PagedList<T> mSnapshot;
    final ListUpdateCallback mUpdateCallback;
    Executor mMainThreadExecutor = ArchTaskExecutor.getMainThreadExecutor();
    private final List<PagedListListener<T>> mListeners = new CopyOnWriteArrayList();
    private PagedList.Callback mPagedListCallback = new PagedList.Callback() { // from class: androidx.paging.AsyncPagedListDiffer.1
        @Override // androidx.paging.PagedList.Callback
        public void onChanged(int i, int i2) {
            AsyncPagedListDiffer.this.mUpdateCallback.onChanged(i, i2, null);
        }

        @Override // androidx.paging.PagedList.Callback
        public void onInserted(int i, int i2) {
            AsyncPagedListDiffer.this.mUpdateCallback.onInserted(i, i2);
        }

        @Override // androidx.paging.PagedList.Callback
        public void onRemoved(int i, int i2) {
            AsyncPagedListDiffer.this.mUpdateCallback.onRemoved(i, i2);
        }
    };

    /* loaded from: classes.dex */
    public interface PagedListListener<T> {
        void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2);
    }

    public AsyncPagedListDiffer(@NonNull RecyclerView.Adapter adapter, @NonNull DiffUtil.ItemCallback<T> itemCallback) {
        this.mUpdateCallback = new AdapterListUpdateCallback(adapter);
        this.mConfig = new AsyncDifferConfig.Builder(itemCallback).build();
    }

    private void onCurrentListChanged(@Nullable PagedList<T> pagedList, @Nullable PagedList<T> pagedList2, @Nullable Runnable runnable) {
        for (PagedListListener<T> pagedListListener : this.mListeners) {
            pagedListListener.onCurrentListChanged(pagedList, pagedList2);
        }
        if (runnable != null) {
            runnable.run();
        }
    }

    public void addPagedListListener(@NonNull PagedListListener<T> pagedListListener) {
        this.mListeners.add(pagedListListener);
    }

    @Nullable
    public PagedList<T> getCurrentList() {
        PagedList<T> pagedList = this.mSnapshot;
        return pagedList != null ? pagedList : this.mPagedList;
    }

    @Nullable
    public T getItem(int i) {
        PagedList<T> pagedList = this.mPagedList;
        if (pagedList == null) {
            PagedList<T> pagedList2 = this.mSnapshot;
            if (pagedList2 != null) {
                return pagedList2.get(i);
            }
            throw new IndexOutOfBoundsException("Item count is zero, getItem() call is invalid");
        }
        pagedList.loadAround(i);
        return this.mPagedList.get(i);
    }

    public int getItemCount() {
        PagedList<T> pagedList = this.mPagedList;
        if (pagedList != null) {
            return pagedList.size();
        }
        PagedList<T> pagedList2 = this.mSnapshot;
        if (pagedList2 != null) {
            return pagedList2.size();
        }
        return 0;
    }

    void latchPagedList(@NonNull PagedList<T> pagedList, @NonNull PagedList<T> pagedList2, @NonNull DiffUtil.DiffResult diffResult, int i, @Nullable Runnable runnable) {
        PagedList<T> pagedList3 = this.mSnapshot;
        if (pagedList3 != null && this.mPagedList == null) {
            this.mPagedList = pagedList;
            this.mSnapshot = null;
            PagedStorageDiffHelper.dispatchDiff(this.mUpdateCallback, pagedList3.mStorage, pagedList.mStorage, diffResult);
            pagedList.addWeakCallback(pagedList2, this.mPagedListCallback);
            if (!this.mPagedList.isEmpty()) {
                int transformAnchorIndex = PagedStorageDiffHelper.transformAnchorIndex(diffResult, pagedList3.mStorage, pagedList2.mStorage, i);
                PagedList<T> pagedList4 = this.mPagedList;
                pagedList4.loadAround(Math.max(0, Math.min(pagedList4.size() - 1, transformAnchorIndex)));
            }
            onCurrentListChanged(pagedList3, this.mPagedList, runnable);
            return;
        }
        throw new IllegalStateException("must be in snapshot state to apply diff");
    }

    public void removePagedListListener(@NonNull PagedListListener<T> pagedListListener) {
        this.mListeners.remove(pagedListListener);
    }

    public void submitList(@Nullable PagedList<T> pagedList) {
        submitList(pagedList, null);
    }

    public void submitList(@Nullable final PagedList<T> pagedList, @Nullable final Runnable runnable) {
        if (pagedList != null) {
            if (this.mPagedList == null && this.mSnapshot == null) {
                this.mIsContiguous = pagedList.isContiguous();
            } else if (pagedList.isContiguous() != this.mIsContiguous) {
                throw new IllegalArgumentException("AsyncPagedListDiffer cannot handle both contiguous and non-contiguous lists.");
            }
        }
        final int i = this.mMaxScheduledGeneration + 1;
        this.mMaxScheduledGeneration = i;
        PagedList<T> pagedList2 = this.mPagedList;
        if (pagedList == pagedList2) {
            if (runnable == null) {
                return;
            }
            runnable.run();
            return;
        }
        PagedList<T> pagedList3 = this.mSnapshot;
        if (pagedList3 != null) {
            pagedList2 = pagedList3;
        }
        if (pagedList == null) {
            int itemCount = getItemCount();
            PagedList<T> pagedList4 = this.mPagedList;
            if (pagedList4 != null) {
                pagedList4.removeWeakCallback(this.mPagedListCallback);
                this.mPagedList = null;
            } else if (this.mSnapshot != null) {
                this.mSnapshot = null;
            }
            this.mUpdateCallback.onRemoved(0, itemCount);
            onCurrentListChanged(pagedList2, null, runnable);
        } else if (this.mPagedList == null && this.mSnapshot == null) {
            this.mPagedList = pagedList;
            pagedList.addWeakCallback(null, this.mPagedListCallback);
            this.mUpdateCallback.onInserted(0, pagedList.size());
            onCurrentListChanged(null, pagedList, runnable);
        } else {
            PagedList<T> pagedList5 = this.mPagedList;
            if (pagedList5 != null) {
                pagedList5.removeWeakCallback(this.mPagedListCallback);
                this.mSnapshot = (PagedList) this.mPagedList.snapshot();
                this.mPagedList = null;
            }
            final PagedList<T> pagedList6 = this.mSnapshot;
            if (pagedList6 != null && this.mPagedList == null) {
                final PagedList pagedList7 = (PagedList) pagedList.snapshot();
                this.mConfig.getBackgroundThreadExecutor().execute(new Runnable() { // from class: androidx.paging.AsyncPagedListDiffer.2
                    @Override // java.lang.Runnable
                    public void run() {
                        final DiffUtil.DiffResult computeDiff = PagedStorageDiffHelper.computeDiff(pagedList6.mStorage, pagedList7.mStorage, AsyncPagedListDiffer.this.mConfig.getDiffCallback());
                        AsyncPagedListDiffer.this.mMainThreadExecutor.execute(new Runnable() { // from class: androidx.paging.AsyncPagedListDiffer.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                                AsyncPagedListDiffer asyncPagedListDiffer = AsyncPagedListDiffer.this;
                                if (asyncPagedListDiffer.mMaxScheduledGeneration == i) {
                                    asyncPagedListDiffer.latchPagedList(pagedList, pagedList7, computeDiff, pagedList6.mLastLoad, runnable);
                                }
                            }
                        });
                    }
                });
                return;
            }
            throw new IllegalStateException("must be in snapshot state to diff");
        }
    }

    public AsyncPagedListDiffer(@NonNull ListUpdateCallback listUpdateCallback, @NonNull AsyncDifferConfig<T> asyncDifferConfig) {
        this.mUpdateCallback = listUpdateCallback;
        this.mConfig = asyncDifferConfig;
    }
}

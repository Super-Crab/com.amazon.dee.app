package androidx.paging;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.paging.PageResult;
import androidx.paging.PagedList;
import androidx.paging.PagedStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
class TiledPagedList<T> extends PagedList<T> implements PagedStorage.Callback {
    final PositionalDataSource<T> mDataSource;
    PageResult.Receiver<T> mReceiver;

    /* JADX INFO: Access modifiers changed from: package-private */
    @WorkerThread
    public TiledPagedList(@NonNull PositionalDataSource<T> positionalDataSource, @NonNull Executor executor, @NonNull Executor executor2, @Nullable PagedList.BoundaryCallback<T> boundaryCallback, @NonNull PagedList.Config config, int i) {
        super(new PagedStorage(), executor, executor2, boundaryCallback, config);
        this.mReceiver = new PageResult.Receiver<T>() { // from class: androidx.paging.TiledPagedList.1
            @Override // androidx.paging.PageResult.Receiver
            @AnyThread
            public void onPageResult(int i2, @NonNull PageResult<T> pageResult) {
                if (pageResult.isInvalid()) {
                    TiledPagedList.this.detach();
                } else if (TiledPagedList.this.isDetached()) {
                } else {
                    if (i2 != 0 && i2 != 3) {
                        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unexpected resultType", i2));
                    }
                    List<T> list = pageResult.page;
                    if (TiledPagedList.this.mStorage.getPageCount() == 0) {
                        TiledPagedList tiledPagedList = TiledPagedList.this;
                        tiledPagedList.mStorage.initAndSplit(pageResult.leadingNulls, list, pageResult.trailingNulls, pageResult.positionOffset, tiledPagedList.mConfig.pageSize, tiledPagedList);
                    } else {
                        TiledPagedList tiledPagedList2 = TiledPagedList.this;
                        tiledPagedList2.mStorage.tryInsertPageAndTrim(pageResult.positionOffset, list, tiledPagedList2.mLastLoad, tiledPagedList2.mConfig.maxSize, tiledPagedList2.mRequiredRemainder, tiledPagedList2);
                    }
                    TiledPagedList tiledPagedList3 = TiledPagedList.this;
                    if (tiledPagedList3.mBoundaryCallback == null) {
                        return;
                    }
                    boolean z = true;
                    boolean z2 = tiledPagedList3.mStorage.size() == 0;
                    boolean z3 = !z2 && pageResult.leadingNulls == 0 && pageResult.positionOffset == 0;
                    int size = TiledPagedList.this.size();
                    if (z2 || ((i2 != 0 || pageResult.trailingNulls != 0) && (i2 != 3 || pageResult.positionOffset + TiledPagedList.this.mConfig.pageSize < size))) {
                        z = false;
                    }
                    TiledPagedList.this.deferBoundaryCallbacks(z2, z3, z);
                }
            }
        };
        this.mDataSource = positionalDataSource;
        int i2 = this.mConfig.pageSize;
        this.mLastLoad = i;
        if (this.mDataSource.isInvalid()) {
            detach();
            return;
        }
        int max = Math.max(this.mConfig.initialLoadSizeHint / i2, 2) * i2;
        this.mDataSource.dispatchLoadInitial(true, Math.max(0, ((i - (max / 2)) / i2) * i2), max, i2, this.mMainThreadExecutor, this.mReceiver);
    }

    @Override // androidx.paging.PagedList
    protected void dispatchUpdatesSinceSnapshot(@NonNull PagedList<T> pagedList, @NonNull PagedList.Callback callback) {
        PagedStorage<T> pagedStorage = pagedList.mStorage;
        if (!pagedStorage.isEmpty() && this.mStorage.size() == pagedStorage.size()) {
            int i = this.mConfig.pageSize;
            int leadingNullCount = this.mStorage.getLeadingNullCount() / i;
            int pageCount = this.mStorage.getPageCount();
            int i2 = 0;
            while (i2 < pageCount) {
                int i3 = i2 + leadingNullCount;
                int i4 = 0;
                while (i4 < this.mStorage.getPageCount()) {
                    int i5 = i3 + i4;
                    if (!this.mStorage.hasPage(i, i5) || pagedStorage.hasPage(i, i5)) {
                        break;
                    }
                    i4++;
                }
                if (i4 > 0) {
                    callback.onChanged(i3 * i, i * i4);
                    i2 += i4 - 1;
                }
                i2++;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid snapshot provided - doesn't appear to be a snapshot of this PagedList");
    }

    @Override // androidx.paging.PagedList
    @NonNull
    public DataSource<?, T> getDataSource() {
        return this.mDataSource;
    }

    @Override // androidx.paging.PagedList
    @Nullable
    public Object getLastKey() {
        return Integer.valueOf(this.mLastLoad);
    }

    @Override // androidx.paging.PagedList
    boolean isContiguous() {
        return false;
    }

    @Override // androidx.paging.PagedList
    protected void loadAroundInternal(int i) {
        PagedStorage<T> pagedStorage = this.mStorage;
        PagedList.Config config = this.mConfig;
        pagedStorage.allocatePlaceholders(i, config.prefetchDistance, config.pageSize, this);
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onEmptyAppend() {
        throw new IllegalStateException("Contiguous callback on TiledPagedList");
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onEmptyPrepend() {
        throw new IllegalStateException("Contiguous callback on TiledPagedList");
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onInitialized(int i) {
        notifyInserted(0, i);
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPageAppended(int i, int i2, int i3) {
        throw new IllegalStateException("Contiguous callback on TiledPagedList");
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPageInserted(int i, int i2) {
        notifyChanged(i, i2);
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPagePlaceholderInserted(final int i) {
        this.mBackgroundThreadExecutor.execute(new Runnable() { // from class: androidx.paging.TiledPagedList.2
            @Override // java.lang.Runnable
            public void run() {
                if (TiledPagedList.this.isDetached()) {
                    return;
                }
                TiledPagedList tiledPagedList = TiledPagedList.this;
                int i2 = tiledPagedList.mConfig.pageSize;
                if (tiledPagedList.mDataSource.isInvalid()) {
                    TiledPagedList.this.detach();
                    return;
                }
                int i3 = i * i2;
                int min = Math.min(i2, TiledPagedList.this.mStorage.size() - i3);
                TiledPagedList tiledPagedList2 = TiledPagedList.this;
                tiledPagedList2.mDataSource.dispatchLoadRange(3, i3, min, tiledPagedList2.mMainThreadExecutor, tiledPagedList2.mReceiver);
            }
        });
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPagePrepended(int i, int i2, int i3) {
        throw new IllegalStateException("Contiguous callback on TiledPagedList");
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPagesRemoved(int i, int i2) {
        notifyRemoved(i, i2);
    }

    @Override // androidx.paging.PagedStorage.Callback
    public void onPagesSwappedToPlaceholder(int i, int i2) {
        notifyChanged(i, i2);
    }
}

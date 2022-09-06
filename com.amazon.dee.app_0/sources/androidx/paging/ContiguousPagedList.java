package androidx.paging;

import androidx.annotation.AnyThread;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageResult;
import androidx.paging.PagedList;
import androidx.paging.PagedStorage;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
class ContiguousPagedList<K, V> extends PagedList<V> implements PagedStorage.Callback {
    private static final int DONE_FETCHING = 2;
    private static final int FETCHING = 1;
    static final int LAST_LOAD_UNSPECIFIED = -1;
    private static final int READY_TO_FETCH = 0;
    int mAppendItemsRequested;
    int mAppendWorkerState;
    final ContiguousDataSource<K, V> mDataSource;
    int mPrependItemsRequested;
    int mPrependWorkerState;
    PageResult.Receiver<V> mReceiver;
    boolean mReplacePagesWithNulls;
    final boolean mShouldTrim;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    @interface FetchState {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ContiguousPagedList(@NonNull ContiguousDataSource<K, V> contiguousDataSource, @NonNull Executor executor, @NonNull Executor executor2, @Nullable PagedList.BoundaryCallback<V> boundaryCallback, @NonNull PagedList.Config config, @Nullable K k, int i) {
        super(new PagedStorage(), executor, executor2, boundaryCallback, config);
        boolean z = false;
        this.mPrependWorkerState = 0;
        this.mAppendWorkerState = 0;
        this.mPrependItemsRequested = 0;
        this.mAppendItemsRequested = 0;
        this.mReplacePagesWithNulls = false;
        this.mReceiver = new PageResult.Receiver<V>() { // from class: androidx.paging.ContiguousPagedList.1
            @Override // androidx.paging.PageResult.Receiver
            @AnyThread
            public void onPageResult(int i2, @NonNull PageResult<V> pageResult) {
                if (pageResult.isInvalid()) {
                    ContiguousPagedList.this.detach();
                } else if (ContiguousPagedList.this.isDetached()) {
                } else {
                    List<V> list = pageResult.page;
                    boolean z2 = true;
                    if (i2 == 0) {
                        ContiguousPagedList contiguousPagedList = ContiguousPagedList.this;
                        contiguousPagedList.mStorage.init(pageResult.leadingNulls, list, pageResult.trailingNulls, pageResult.positionOffset, contiguousPagedList);
                        ContiguousPagedList contiguousPagedList2 = ContiguousPagedList.this;
                        if (contiguousPagedList2.mLastLoad == -1) {
                            contiguousPagedList2.mLastLoad = (list.size() / 2) + pageResult.leadingNulls + pageResult.positionOffset;
                        }
                    } else {
                        ContiguousPagedList contiguousPagedList3 = ContiguousPagedList.this;
                        boolean z3 = contiguousPagedList3.mLastLoad > contiguousPagedList3.mStorage.getMiddleOfLoadedRange();
                        ContiguousPagedList contiguousPagedList4 = ContiguousPagedList.this;
                        boolean z4 = contiguousPagedList4.mShouldTrim && contiguousPagedList4.mStorage.shouldPreTrimNewPage(contiguousPagedList4.mConfig.maxSize, contiguousPagedList4.mRequiredRemainder, list.size());
                        if (i2 == 1) {
                            if (z4 && !z3) {
                                ContiguousPagedList contiguousPagedList5 = ContiguousPagedList.this;
                                contiguousPagedList5.mAppendItemsRequested = 0;
                                contiguousPagedList5.mAppendWorkerState = 0;
                            } else {
                                ContiguousPagedList contiguousPagedList6 = ContiguousPagedList.this;
                                contiguousPagedList6.mStorage.appendPage(list, contiguousPagedList6);
                            }
                        } else if (i2 != 2) {
                            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("unexpected resultType ", i2));
                        } else {
                            if (z4 && z3) {
                                ContiguousPagedList contiguousPagedList7 = ContiguousPagedList.this;
                                contiguousPagedList7.mPrependItemsRequested = 0;
                                contiguousPagedList7.mPrependWorkerState = 0;
                            } else {
                                ContiguousPagedList contiguousPagedList8 = ContiguousPagedList.this;
                                contiguousPagedList8.mStorage.prependPage(list, contiguousPagedList8);
                            }
                        }
                        ContiguousPagedList contiguousPagedList9 = ContiguousPagedList.this;
                        if (contiguousPagedList9.mShouldTrim) {
                            if (z3) {
                                if (contiguousPagedList9.mPrependWorkerState != 1 && contiguousPagedList9.mStorage.trimFromFront(contiguousPagedList9.mReplacePagesWithNulls, contiguousPagedList9.mConfig.maxSize, contiguousPagedList9.mRequiredRemainder, contiguousPagedList9)) {
                                    ContiguousPagedList.this.mPrependWorkerState = 0;
                                }
                            } else if (contiguousPagedList9.mAppendWorkerState != 1 && contiguousPagedList9.mStorage.trimFromEnd(contiguousPagedList9.mReplacePagesWithNulls, contiguousPagedList9.mConfig.maxSize, contiguousPagedList9.mRequiredRemainder, contiguousPagedList9)) {
                                ContiguousPagedList.this.mAppendWorkerState = 0;
                            }
                        }
                    }
                    ContiguousPagedList contiguousPagedList10 = ContiguousPagedList.this;
                    if (contiguousPagedList10.mBoundaryCallback == null) {
                        return;
                    }
                    boolean z5 = contiguousPagedList10.mStorage.size() == 0;
                    boolean z6 = !z5 && i2 == 2 && pageResult.page.size() == 0;
                    if (z5 || i2 != 1 || pageResult.page.size() != 0) {
                        z2 = false;
                    }
                    ContiguousPagedList.this.deferBoundaryCallbacks(z5, z6, z2);
                }
            }
        };
        this.mDataSource = contiguousDataSource;
        this.mLastLoad = i;
        if (this.mDataSource.isInvalid()) {
            detach();
        } else {
            ContiguousDataSource<K, V> contiguousDataSource2 = this.mDataSource;
            PagedList.Config config2 = this.mConfig;
            contiguousDataSource2.dispatchLoadInitial(k, config2.initialLoadSizeHint, config2.pageSize, config2.enablePlaceholders, this.mMainThreadExecutor, this.mReceiver);
        }
        if (this.mDataSource.supportsPageDropping() && this.mConfig.maxSize != Integer.MAX_VALUE) {
            z = true;
        }
        this.mShouldTrim = z;
    }

    static int getAppendItemsRequested(int i, int i2, int i3) {
        return ((i2 + i) + 1) - i3;
    }

    static int getPrependItemsRequested(int i, int i2, int i3) {
        return i - (i2 - i3);
    }

    @MainThread
    private void scheduleAppend() {
        if (this.mAppendWorkerState != 0) {
            return;
        }
        this.mAppendWorkerState = 1;
        final int leadingNullCount = ((this.mStorage.getLeadingNullCount() + this.mStorage.getStorageCount()) - 1) + this.mStorage.getPositionOffset();
        final Object lastLoadedItem = this.mStorage.getLastLoadedItem();
        this.mBackgroundThreadExecutor.execute(new Runnable() { // from class: androidx.paging.ContiguousPagedList.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (ContiguousPagedList.this.isDetached()) {
                    return;
                }
                if (ContiguousPagedList.this.mDataSource.isInvalid()) {
                    ContiguousPagedList.this.detach();
                    return;
                }
                ContiguousPagedList contiguousPagedList = ContiguousPagedList.this;
                contiguousPagedList.mDataSource.dispatchLoadAfter(leadingNullCount, lastLoadedItem, contiguousPagedList.mConfig.pageSize, contiguousPagedList.mMainThreadExecutor, contiguousPagedList.mReceiver);
            }
        });
    }

    @MainThread
    private void schedulePrepend() {
        if (this.mPrependWorkerState != 0) {
            return;
        }
        this.mPrependWorkerState = 1;
        final int leadingNullCount = this.mStorage.getLeadingNullCount() + this.mStorage.getPositionOffset();
        final Object firstLoadedItem = this.mStorage.getFirstLoadedItem();
        this.mBackgroundThreadExecutor.execute(new Runnable() { // from class: androidx.paging.ContiguousPagedList.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                if (ContiguousPagedList.this.isDetached()) {
                    return;
                }
                if (ContiguousPagedList.this.mDataSource.isInvalid()) {
                    ContiguousPagedList.this.detach();
                    return;
                }
                ContiguousPagedList contiguousPagedList = ContiguousPagedList.this;
                contiguousPagedList.mDataSource.dispatchLoadBefore(leadingNullCount, firstLoadedItem, contiguousPagedList.mConfig.pageSize, contiguousPagedList.mMainThreadExecutor, contiguousPagedList.mReceiver);
            }
        });
    }

    @Override // androidx.paging.PagedList
    @MainThread
    void dispatchUpdatesSinceSnapshot(@NonNull PagedList<V> pagedList, @NonNull PagedList.Callback callback) {
        PagedStorage<V> pagedStorage = pagedList.mStorage;
        int numberAppended = this.mStorage.getNumberAppended() - pagedStorage.getNumberAppended();
        int numberPrepended = this.mStorage.getNumberPrepended() - pagedStorage.getNumberPrepended();
        int trailingNullCount = pagedStorage.getTrailingNullCount();
        int leadingNullCount = pagedStorage.getLeadingNullCount();
        if (pagedStorage.isEmpty() || numberAppended < 0 || numberPrepended < 0 || this.mStorage.getTrailingNullCount() != Math.max(trailingNullCount - numberAppended, 0) || this.mStorage.getLeadingNullCount() != Math.max(leadingNullCount - numberPrepended, 0) || this.mStorage.getStorageCount() != pagedStorage.getStorageCount() + numberAppended + numberPrepended) {
            throw new IllegalArgumentException("Invalid snapshot provided - doesn't appear to be a snapshot of this PagedList");
        }
        if (numberAppended != 0) {
            int min = Math.min(trailingNullCount, numberAppended);
            int i = numberAppended - min;
            int leadingNullCount2 = pagedStorage.getLeadingNullCount() + pagedStorage.getStorageCount();
            if (min != 0) {
                callback.onChanged(leadingNullCount2, min);
            }
            if (i != 0) {
                callback.onInserted(leadingNullCount2 + min, i);
            }
        }
        if (numberPrepended == 0) {
            return;
        }
        int min2 = Math.min(leadingNullCount, numberPrepended);
        int i2 = numberPrepended - min2;
        if (min2 != 0) {
            callback.onChanged(leadingNullCount, min2);
        }
        if (i2 == 0) {
            return;
        }
        callback.onInserted(0, i2);
    }

    @Override // androidx.paging.PagedList
    @NonNull
    public DataSource<?, V> getDataSource() {
        return this.mDataSource;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.paging.PagedList
    @Nullable
    public Object getLastKey() {
        return this.mDataSource.mo191getKey(this.mLastLoad, this.mLastItem);
    }

    @Override // androidx.paging.PagedList
    boolean isContiguous() {
        return true;
    }

    @Override // androidx.paging.PagedList
    @MainThread
    protected void loadAroundInternal(int i) {
        int prependItemsRequested = getPrependItemsRequested(this.mConfig.prefetchDistance, i, this.mStorage.getLeadingNullCount());
        int appendItemsRequested = getAppendItemsRequested(this.mConfig.prefetchDistance, i, this.mStorage.getLeadingNullCount() + this.mStorage.getStorageCount());
        this.mPrependItemsRequested = Math.max(prependItemsRequested, this.mPrependItemsRequested);
        if (this.mPrependItemsRequested > 0) {
            schedulePrepend();
        }
        this.mAppendItemsRequested = Math.max(appendItemsRequested, this.mAppendItemsRequested);
        if (this.mAppendItemsRequested > 0) {
            scheduleAppend();
        }
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onEmptyAppend() {
        this.mAppendWorkerState = 2;
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onEmptyPrepend() {
        this.mPrependWorkerState = 2;
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onInitialized(int i) {
        boolean z = false;
        notifyInserted(0, i);
        if (this.mStorage.getLeadingNullCount() > 0 || this.mStorage.getTrailingNullCount() > 0) {
            z = true;
        }
        this.mReplacePagesWithNulls = z;
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onPageAppended(int i, int i2, int i3) {
        this.mAppendItemsRequested = (this.mAppendItemsRequested - i2) - i3;
        this.mAppendWorkerState = 0;
        if (this.mAppendItemsRequested > 0) {
            scheduleAppend();
        }
        notifyChanged(i, i2);
        notifyInserted(i + i2, i3);
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onPageInserted(int i, int i2) {
        throw new IllegalStateException("Tiled callback on ContiguousPagedList");
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onPagePlaceholderInserted(int i) {
        throw new IllegalStateException("Tiled callback on ContiguousPagedList");
    }

    @Override // androidx.paging.PagedStorage.Callback
    @MainThread
    public void onPagePrepended(int i, int i2, int i3) {
        this.mPrependItemsRequested = (this.mPrependItemsRequested - i2) - i3;
        this.mPrependWorkerState = 0;
        if (this.mPrependItemsRequested > 0) {
            schedulePrepend();
        }
        notifyChanged(i, i2);
        notifyInserted(0, i3);
        offsetAccessIndices(i3);
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

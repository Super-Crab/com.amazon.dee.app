package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
/* loaded from: classes.dex */
class SnapshotPagedList<T> extends PagedList<T> {
    private final boolean mContiguous;
    private final DataSource<?, T> mDataSource;
    private final Object mLastKey;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SnapshotPagedList(@NonNull PagedList<T> pagedList) {
        super(pagedList.mStorage.snapshot(), pagedList.mMainThreadExecutor, pagedList.mBackgroundThreadExecutor, null, pagedList.mConfig);
        this.mDataSource = pagedList.getDataSource();
        this.mContiguous = pagedList.isContiguous();
        this.mLastLoad = pagedList.mLastLoad;
        this.mLastKey = pagedList.getLastKey();
    }

    @Override // androidx.paging.PagedList
    void dispatchUpdatesSinceSnapshot(@NonNull PagedList<T> pagedList, @NonNull PagedList.Callback callback) {
    }

    @Override // androidx.paging.PagedList
    @NonNull
    public DataSource<?, T> getDataSource() {
        return this.mDataSource;
    }

    @Override // androidx.paging.PagedList
    @Nullable
    public Object getLastKey() {
        return this.mLastKey;
    }

    @Override // androidx.paging.PagedList
    boolean isContiguous() {
        return this.mContiguous;
    }

    @Override // androidx.paging.PagedList
    public boolean isDetached() {
        return true;
    }

    @Override // androidx.paging.PagedList
    public boolean isImmutable() {
        return true;
    }

    @Override // androidx.paging.PagedList
    void loadAroundInternal(int i) {
    }
}

package androidx.paging;

import androidx.annotation.AnyThread;
import androidx.annotation.IntRange;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public abstract class PagedList<T> extends AbstractList<T> {
    @NonNull
    final Executor mBackgroundThreadExecutor;
    @Nullable
    final BoundaryCallback<T> mBoundaryCallback;
    @NonNull
    final Config mConfig;
    @NonNull
    final Executor mMainThreadExecutor;
    final int mRequiredRemainder;
    @NonNull
    final PagedStorage<T> mStorage;
    int mLastLoad = 0;
    T mLastItem = null;
    boolean mBoundaryCallbackBeginDeferred = false;
    boolean mBoundaryCallbackEndDeferred = false;
    private int mLowestIndexAccessed = Integer.MAX_VALUE;
    private int mHighestIndexAccessed = Integer.MIN_VALUE;
    private final AtomicBoolean mDetached = new AtomicBoolean(false);
    private final ArrayList<WeakReference<Callback>> mCallbacks = new ArrayList<>();

    @MainThread
    /* loaded from: classes.dex */
    public static abstract class BoundaryCallback<T> {
        public void onItemAtEndLoaded(@NonNull T t) {
        }

        public void onItemAtFrontLoaded(@NonNull T t) {
        }

        public void onZeroItemsLoaded() {
        }
    }

    /* loaded from: classes.dex */
    public static abstract class Callback {
        public abstract void onChanged(int i, int i2);

        public abstract void onInserted(int i, int i2);

        public abstract void onRemoved(int i, int i2);
    }

    /* loaded from: classes.dex */
    public static class Config {
        public static final int MAX_SIZE_UNBOUNDED = Integer.MAX_VALUE;
        public final boolean enablePlaceholders;
        public final int initialLoadSizeHint;
        public final int maxSize;
        public final int pageSize;
        public final int prefetchDistance;

        /* loaded from: classes.dex */
        public static final class Builder {
            static final int DEFAULT_INITIAL_PAGE_MULTIPLIER = 3;
            private int mPageSize = -1;
            private int mPrefetchDistance = -1;
            private int mInitialLoadSizeHint = -1;
            private boolean mEnablePlaceholders = true;
            private int mMaxSize = Integer.MAX_VALUE;

            @NonNull
            public Config build() {
                if (this.mPrefetchDistance < 0) {
                    this.mPrefetchDistance = this.mPageSize;
                }
                if (this.mInitialLoadSizeHint < 0) {
                    this.mInitialLoadSizeHint = this.mPageSize * 3;
                }
                if (!this.mEnablePlaceholders && this.mPrefetchDistance == 0) {
                    throw new IllegalArgumentException("Placeholders and prefetch are the only ways to trigger loading of more data in the PagedList, so either placeholders must be enabled, or prefetch distance must be > 0.");
                }
                int i = this.mMaxSize;
                if (i != Integer.MAX_VALUE) {
                    if (i < (this.mPrefetchDistance * 2) + this.mPageSize) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Maximum size must be at least pageSize + 2*prefetchDist, pageSize=");
                        outline107.append(this.mPageSize);
                        outline107.append(", prefetchDist=");
                        outline107.append(this.mPrefetchDistance);
                        outline107.append(", maxSize=");
                        outline107.append(this.mMaxSize);
                        throw new IllegalArgumentException(outline107.toString());
                    }
                }
                return new Config(this.mPageSize, this.mPrefetchDistance, this.mEnablePlaceholders, this.mInitialLoadSizeHint, this.mMaxSize);
            }

            @NonNull
            public Builder setEnablePlaceholders(boolean z) {
                this.mEnablePlaceholders = z;
                return this;
            }

            @NonNull
            public Builder setInitialLoadSizeHint(@IntRange(from = 1) int i) {
                this.mInitialLoadSizeHint = i;
                return this;
            }

            @NonNull
            public Builder setMaxSize(@IntRange(from = 2) int i) {
                this.mMaxSize = i;
                return this;
            }

            @NonNull
            public Builder setPageSize(@IntRange(from = 1) int i) {
                if (i >= 1) {
                    this.mPageSize = i;
                    return this;
                }
                throw new IllegalArgumentException("Page size must be a positive number");
            }

            @NonNull
            public Builder setPrefetchDistance(@IntRange(from = 0) int i) {
                this.mPrefetchDistance = i;
                return this;
            }
        }

        Config(int i, int i2, boolean z, int i3, int i4) {
            this.pageSize = i;
            this.prefetchDistance = i2;
            this.enablePlaceholders = z;
            this.initialLoadSizeHint = i3;
            this.maxSize = i4;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PagedList(@NonNull PagedStorage<T> pagedStorage, @NonNull Executor executor, @NonNull Executor executor2, @Nullable BoundaryCallback<T> boundaryCallback, @NonNull Config config) {
        this.mStorage = pagedStorage;
        this.mMainThreadExecutor = executor;
        this.mBackgroundThreadExecutor = executor2;
        this.mBoundaryCallback = boundaryCallback;
        this.mConfig = config;
        Config config2 = this.mConfig;
        this.mRequiredRemainder = (config2.prefetchDistance * 2) + config2.pageSize;
    }

    @NonNull
    static <K, T> PagedList<T> create(@NonNull DataSource<K, T> dataSource, @NonNull Executor executor, @NonNull Executor executor2, @Nullable BoundaryCallback<T> boundaryCallback, @NonNull Config config, @Nullable K k) {
        if (!dataSource.isContiguous() && config.enablePlaceholders) {
            return new TiledPagedList((PositionalDataSource) dataSource, executor, executor2, boundaryCallback, config, k != null ? ((Integer) k).intValue() : 0);
        }
        int i = -1;
        if (!dataSource.isContiguous()) {
            dataSource = ((PositionalDataSource) dataSource).wrapAsContiguousWithoutPlaceholders();
            if (k != null) {
                i = ((Integer) k).intValue();
            }
        }
        return new ContiguousPagedList((ContiguousDataSource) dataSource, executor, executor2, boundaryCallback, config, k, i);
    }

    public void addWeakCallback(@Nullable List<T> list, @NonNull Callback callback) {
        if (list != null && list != this) {
            if (list.isEmpty()) {
                if (!this.mStorage.isEmpty()) {
                    callback.onInserted(0, this.mStorage.size());
                }
            } else {
                dispatchUpdatesSinceSnapshot((PagedList) list, callback);
            }
        }
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            if (this.mCallbacks.get(size).get() == null) {
                this.mCallbacks.remove(size);
            }
        }
        this.mCallbacks.add(new WeakReference<>(callback));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @AnyThread
    public void deferBoundaryCallbacks(final boolean z, final boolean z2, final boolean z3) {
        if (this.mBoundaryCallback != null) {
            if (this.mLowestIndexAccessed == Integer.MAX_VALUE) {
                this.mLowestIndexAccessed = this.mStorage.size();
            }
            if (this.mHighestIndexAccessed == Integer.MIN_VALUE) {
                this.mHighestIndexAccessed = 0;
            }
            if (!z && !z2 && !z3) {
                return;
            }
            this.mMainThreadExecutor.execute(new Runnable() { // from class: androidx.paging.PagedList.1
                @Override // java.lang.Runnable
                public void run() {
                    if (z) {
                        PagedList.this.mBoundaryCallback.onZeroItemsLoaded();
                    }
                    if (z2) {
                        PagedList.this.mBoundaryCallbackBeginDeferred = true;
                    }
                    if (z3) {
                        PagedList.this.mBoundaryCallbackEndDeferred = true;
                    }
                    PagedList.this.tryDispatchBoundaryCallbacks(false);
                }
            });
            return;
        }
        throw new IllegalStateException("Can't defer BoundaryCallback, no instance");
    }

    public void detach() {
        this.mDetached.set(true);
    }

    void dispatchBoundaryCallbacks(boolean z, boolean z2) {
        if (z) {
            this.mBoundaryCallback.onItemAtFrontLoaded(this.mStorage.getFirstLoadedItem());
        }
        if (z2) {
            this.mBoundaryCallback.onItemAtEndLoaded(this.mStorage.getLastLoadedItem());
        }
    }

    abstract void dispatchUpdatesSinceSnapshot(@NonNull PagedList<T> pagedList, @NonNull Callback callback);

    @Override // java.util.AbstractList, java.util.List
    @Nullable
    public T get(int i) {
        T t = this.mStorage.get(i);
        if (t != null) {
            this.mLastItem = t;
        }
        return t;
    }

    @NonNull
    public Config getConfig() {
        return this.mConfig;
    }

    @NonNull
    public abstract DataSource<?, T> getDataSource();

    @Nullable
    public abstract Object getLastKey();

    public int getLoadedCount() {
        return this.mStorage.getLoadedCount();
    }

    public int getPositionOffset() {
        return this.mStorage.getPositionOffset();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract boolean isContiguous();

    public boolean isDetached() {
        return this.mDetached.get();
    }

    public boolean isImmutable() {
        return isDetached();
    }

    public void loadAround(int i) {
        if (i >= 0 && i < size()) {
            this.mLastLoad = getPositionOffset() + i;
            loadAroundInternal(i);
            this.mLowestIndexAccessed = Math.min(this.mLowestIndexAccessed, i);
            this.mHighestIndexAccessed = Math.max(this.mHighestIndexAccessed, i);
            tryDispatchBoundaryCallbacks(true);
            return;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
        outline109.append(size());
        throw new IndexOutOfBoundsException(outline109.toString());
    }

    abstract void loadAroundInternal(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyChanged(int i, int i2) {
        if (i2 != 0) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                Callback callback = this.mCallbacks.get(size).get();
                if (callback != null) {
                    callback.onChanged(i, i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyInserted(int i, int i2) {
        if (i2 != 0) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                Callback callback = this.mCallbacks.get(size).get();
                if (callback != null) {
                    callback.onInserted(i, i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyRemoved(int i, int i2) {
        if (i2 != 0) {
            for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
                Callback callback = this.mCallbacks.get(size).get();
                if (callback != null) {
                    callback.onRemoved(i, i2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void offsetAccessIndices(int i) {
        this.mLastLoad += i;
        this.mLowestIndexAccessed += i;
        this.mHighestIndexAccessed += i;
    }

    public void removeWeakCallback(@NonNull Callback callback) {
        for (int size = this.mCallbacks.size() - 1; size >= 0; size--) {
            Callback callback2 = this.mCallbacks.get(size).get();
            if (callback2 == null || callback2 == callback) {
                this.mCallbacks.remove(size);
            }
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.mStorage.size();
    }

    @NonNull
    public List<T> snapshot() {
        return isImmutable() ? this : new SnapshotPagedList(this);
    }

    void tryDispatchBoundaryCallbacks(boolean z) {
        final boolean z2 = true;
        final boolean z3 = this.mBoundaryCallbackBeginDeferred && this.mLowestIndexAccessed <= this.mConfig.prefetchDistance;
        if (!this.mBoundaryCallbackEndDeferred || this.mHighestIndexAccessed < (size() - 1) - this.mConfig.prefetchDistance) {
            z2 = false;
        }
        if (z3 || z2) {
            if (z3) {
                this.mBoundaryCallbackBeginDeferred = false;
            }
            if (z2) {
                this.mBoundaryCallbackEndDeferred = false;
            }
            if (z) {
                this.mMainThreadExecutor.execute(new Runnable() { // from class: androidx.paging.PagedList.2
                    @Override // java.lang.Runnable
                    public void run() {
                        PagedList.this.dispatchBoundaryCallbacks(z3, z2);
                    }
                });
            } else {
                dispatchBoundaryCallbacks(z3, z2);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class Builder<Key, Value> {
        private BoundaryCallback mBoundaryCallback;
        private final Config mConfig;
        private final DataSource<Key, Value> mDataSource;
        private Executor mFetchExecutor;
        private Key mInitialKey;
        private Executor mNotifyExecutor;

        public Builder(@NonNull DataSource<Key, Value> dataSource, @NonNull Config config) {
            if (dataSource != null) {
                if (config != null) {
                    this.mDataSource = dataSource;
                    this.mConfig = config;
                    return;
                }
                throw new IllegalArgumentException("Config may not be null");
            }
            throw new IllegalArgumentException("DataSource may not be null");
        }

        @NonNull
        @WorkerThread
        public PagedList<Value> build() {
            Executor executor = this.mNotifyExecutor;
            if (executor != null) {
                Executor executor2 = this.mFetchExecutor;
                if (executor2 != null) {
                    return PagedList.create(this.mDataSource, executor, executor2, this.mBoundaryCallback, this.mConfig, this.mInitialKey);
                }
                throw new IllegalArgumentException("BackgroundThreadExecutor required");
            }
            throw new IllegalArgumentException("MainThreadExecutor required");
        }

        @NonNull
        public Builder<Key, Value> setBoundaryCallback(@Nullable BoundaryCallback boundaryCallback) {
            this.mBoundaryCallback = boundaryCallback;
            return this;
        }

        @NonNull
        public Builder<Key, Value> setFetchExecutor(@NonNull Executor executor) {
            this.mFetchExecutor = executor;
            return this;
        }

        @NonNull
        public Builder<Key, Value> setInitialKey(@Nullable Key key) {
            this.mInitialKey = key;
            return this;
        }

        @NonNull
        public Builder<Key, Value> setNotifyExecutor(@NonNull Executor executor) {
            this.mNotifyExecutor = executor;
            return this;
        }

        public Builder(@NonNull DataSource<Key, Value> dataSource, int i) {
            this(dataSource, new Config.Builder().setPageSize(i).build());
        }
    }
}

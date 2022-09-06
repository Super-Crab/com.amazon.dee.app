package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageResult;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class PositionalDataSource<T> extends DataSource<Integer, T> {

    /* loaded from: classes.dex */
    static class ContiguousWithoutPlaceholdersWrapper<Value> extends ContiguousDataSource<Integer, Value> {
        @NonNull
        final PositionalDataSource<Value> mSource;

        ContiguousWithoutPlaceholdersWrapper(@NonNull PositionalDataSource<Value> positionalDataSource) {
            this.mSource = positionalDataSource;
        }

        @Override // androidx.paging.DataSource
        public void addInvalidatedCallback(@NonNull DataSource.InvalidatedCallback invalidatedCallback) {
            this.mSource.addInvalidatedCallback(invalidatedCallback);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.paging.ContiguousDataSource
        public void dispatchLoadAfter(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
            this.mSource.dispatchLoadRange(1, i + 1, i2, executor, receiver);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.paging.ContiguousDataSource
        public void dispatchLoadBefore(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
            int i3 = i - 1;
            if (i3 < 0) {
                this.mSource.dispatchLoadRange(2, i3, 0, executor, receiver);
                return;
            }
            int min = Math.min(i2, i3 + 1);
            this.mSource.dispatchLoadRange(2, (i3 - min) + 1, min, executor, receiver);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.paging.ContiguousDataSource
        /* renamed from: getKey  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ Integer mo191getKey(int i, Object obj) {
            return mo191getKey(i, (int) obj);
        }

        @Override // androidx.paging.DataSource
        public void invalidate() {
            this.mSource.invalidate();
        }

        @Override // androidx.paging.DataSource
        public boolean isInvalid() {
            return this.mSource.isInvalid();
        }

        @Override // androidx.paging.DataSource
        @NonNull
        /* renamed from: map */
        public <ToValue> DataSource<Integer, ToValue> mo189map(@NonNull Function<Value, ToValue> function) {
            throw new UnsupportedOperationException("Inaccessible inner type doesn't support map op");
        }

        @Override // androidx.paging.DataSource
        @NonNull
        /* renamed from: mapByPage */
        public <ToValue> DataSource<Integer, ToValue> mo190mapByPage(@NonNull Function<List<Value>, List<ToValue>> function) {
            throw new UnsupportedOperationException("Inaccessible inner type doesn't support map op");
        }

        @Override // androidx.paging.DataSource
        public void removeInvalidatedCallback(@NonNull DataSource.InvalidatedCallback invalidatedCallback) {
            this.mSource.removeInvalidatedCallback(invalidatedCallback);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // androidx.paging.ContiguousDataSource
        public void dispatchLoadInitial(@Nullable Integer num, int i, int i2, boolean z, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
            this.mSource.dispatchLoadInitial(false, num == null ? 0 : num.intValue(), i, i2, executor, receiver);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.paging.ContiguousDataSource
        /* renamed from: getKey */
        public Integer mo191getKey(int i, Value value) {
            return Integer.valueOf(i);
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<T> {
        public abstract void onResult(@NonNull List<T> list, int i);

        public abstract void onResult(@NonNull List<T> list, int i, int i2);
    }

    /* loaded from: classes.dex */
    public static class LoadInitialParams {
        public final int pageSize;
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;
        public final int requestedStartPosition;

        public LoadInitialParams(int i, int i2, int i3, boolean z) {
            this.requestedStartPosition = i;
            this.requestedLoadSize = i2;
            this.pageSize = i3;
            this.placeholdersEnabled = z;
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LoadRangeCallback<T> {
        public abstract void onResult(@NonNull List<T> list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LoadRangeCallbackImpl<T> extends LoadRangeCallback<T> {
        private DataSource.LoadCallbackHelper<T> mCallbackHelper;
        private final int mPositionOffset;

        LoadRangeCallbackImpl(@NonNull PositionalDataSource positionalDataSource, int i, int i2, Executor executor, PageResult.Receiver<T> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(positionalDataSource, i, executor, receiver);
            this.mPositionOffset = i2;
        }

        @Override // androidx.paging.PositionalDataSource.LoadRangeCallback
        public void onResult(@NonNull List<T> list) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, this.mPositionOffset));
            }
        }
    }

    /* loaded from: classes.dex */
    public static class LoadRangeParams {
        public final int loadSize;
        public final int startPosition;

        public LoadRangeParams(int i, int i2) {
            this.startPosition = i;
            this.loadSize = i2;
        }
    }

    public static int computeInitialLoadPosition(@NonNull LoadInitialParams loadInitialParams, int i) {
        int i2 = loadInitialParams.requestedStartPosition;
        int i3 = loadInitialParams.requestedLoadSize;
        int i4 = loadInitialParams.pageSize;
        return Math.max(0, Math.min(((((i - i3) + i4) - 1) / i4) * i4, (i2 / i4) * i4));
    }

    public static int computeInitialLoadSize(@NonNull LoadInitialParams loadInitialParams, int i, int i2) {
        return Math.min(i2 - i, loadInitialParams.requestedLoadSize);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispatchLoadInitial(boolean z, int i, int i2, int i3, @NonNull Executor executor, @NonNull PageResult.Receiver<T> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, i3, receiver);
        loadInitial(new LoadInitialParams(i, i2, i3, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispatchLoadRange(int i, int i2, int i3, @NonNull Executor executor, @NonNull PageResult.Receiver<T> receiver) {
        LoadRangeCallbackImpl loadRangeCallbackImpl = new LoadRangeCallbackImpl(this, i, i2, executor, receiver);
        if (i3 == 0) {
            loadRangeCallbackImpl.onResult(Collections.emptyList());
        } else {
            loadRange(new LoadRangeParams(i2, i3), loadRangeCallbackImpl);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.DataSource
    public boolean isContiguous() {
        return false;
    }

    @WorkerThread
    public abstract void loadInitial(@NonNull LoadInitialParams loadInitialParams, @NonNull LoadInitialCallback<T> loadInitialCallback);

    @WorkerThread
    public abstract void loadRange(@NonNull LoadRangeParams loadRangeParams, @NonNull LoadRangeCallback<T> loadRangeCallback);

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public ContiguousDataSource<Integer, T> wrapAsContiguousWithoutPlaceholders() {
        return new ContiguousWithoutPlaceholdersWrapper(this);
    }

    @Override // androidx.paging.DataSource
    @NonNull
    /* renamed from: map  reason: collision with other method in class */
    public final <V> PositionalDataSource<V> mo189map(@NonNull Function<T, V> function) {
        return mo190mapByPage((Function) DataSource.createListFunction(function));
    }

    @Override // androidx.paging.DataSource
    @NonNull
    /* renamed from: mapByPage  reason: collision with other method in class */
    public final <V> PositionalDataSource<V> mo190mapByPage(@NonNull Function<List<T>, List<V>> function) {
        return new WrapperPositionalDataSource(this, function);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class LoadInitialCallbackImpl<T> extends LoadInitialCallback<T> {
        final DataSource.LoadCallbackHelper<T> mCallbackHelper;
        private final boolean mCountingEnabled;
        private final int mPageSize;

        LoadInitialCallbackImpl(@NonNull PositionalDataSource positionalDataSource, boolean z, int i, PageResult.Receiver<T> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(positionalDataSource, 0, null, receiver);
            this.mCountingEnabled = z;
            this.mPageSize = i;
            if (this.mPageSize >= 1) {
                return;
            }
            throw new IllegalArgumentException("Page size must be non-negative");
        }

        @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
        public void onResult(@NonNull List<T> list, int i, int i2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                if (list.size() + i != i2 && list.size() % this.mPageSize != 0) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PositionalDataSource requires initial load size to be a multiple of page size to support internal tiling. loadSize ");
                    outline107.append(list.size());
                    outline107.append(", position ");
                    outline107.append(i);
                    outline107.append(", totalCount ");
                    outline107.append(i2);
                    outline107.append(", pageSize ");
                    outline107.append(this.mPageSize);
                    throw new IllegalArgumentException(outline107.toString());
                } else if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i, (i2 - i) - list.size(), 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i));
                }
            }
        }

        @Override // androidx.paging.PositionalDataSource.LoadInitialCallback
        public void onResult(@NonNull List<T> list, int i) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                if (i >= 0) {
                    if (list.isEmpty() && i != 0) {
                        throw new IllegalArgumentException("Initial result cannot be empty if items are present in data set.");
                    }
                    if (!this.mCountingEnabled) {
                        this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i));
                        return;
                    }
                    throw new IllegalStateException("Placeholders requested, but totalCount not provided. Please call the three-parameter onResult method, or disable placeholders in the PagedList.Config");
                }
                throw new IllegalArgumentException("Position must be non-negative");
            }
        }
    }
}

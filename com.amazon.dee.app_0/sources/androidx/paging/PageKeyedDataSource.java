package androidx.paging;

import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageResult;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class PageKeyedDataSource<Key, Value> extends ContiguousDataSource<Key, Value> {
    private final Object mKeyLock = new Object();
    @Nullable
    @GuardedBy("mKeyLock")
    private Key mNextKey = null;
    @Nullable
    @GuardedBy("mKeyLock")
    private Key mPreviousKey = null;

    /* loaded from: classes.dex */
    public static abstract class LoadCallback<Key, Value> {
        public abstract void onResult(@NonNull List<Value> list, @Nullable Key key);
    }

    /* loaded from: classes.dex */
    static class LoadCallbackImpl<Key, Value> extends LoadCallback<Key, Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final PageKeyedDataSource<Key, Value> mDataSource;

        LoadCallbackImpl(@NonNull PageKeyedDataSource<Key, Value> pageKeyedDataSource, int i, @Nullable Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(pageKeyedDataSource, i, executor, receiver);
            this.mDataSource = pageKeyedDataSource;
        }

        @Override // androidx.paging.PageKeyedDataSource.LoadCallback
        public void onResult(@NonNull List<Value> list, @Nullable Key key) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                if (this.mCallbackHelper.mResultType == 1) {
                    this.mDataSource.setNextKey(key);
                } else {
                    this.mDataSource.setPreviousKey(key);
                }
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, 0));
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<Key, Value> {
        public abstract void onResult(@NonNull List<Value> list, int i, int i2, @Nullable Key key, @Nullable Key key2);

        public abstract void onResult(@NonNull List<Value> list, @Nullable Key key, @Nullable Key key2);
    }

    /* loaded from: classes.dex */
    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;

        public LoadInitialParams(int i, boolean z) {
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    /* loaded from: classes.dex */
    public static class LoadParams<Key> {
        @NonNull
        public final Key key;
        public final int requestedLoadSize;

        public LoadParams(@NonNull Key key, int i) {
            this.key = key;
            this.requestedLoadSize = i;
        }
    }

    @Nullable
    private Key getNextKey() {
        Key key;
        synchronized (this.mKeyLock) {
            key = this.mNextKey;
        }
        return key;
    }

    @Nullable
    private Key getPreviousKey() {
        Key key;
        synchronized (this.mKeyLock) {
            key = this.mPreviousKey;
        }
        return key;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public final void dispatchLoadAfter(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        Key nextKey = getNextKey();
        if (nextKey != null) {
            loadAfter(new LoadParams<>(nextKey, i2), new LoadCallbackImpl(this, 1, executor, receiver));
        } else {
            receiver.onPageResult(1, PageResult.getEmptyResult());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public final void dispatchLoadBefore(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        Key previousKey = getPreviousKey();
        if (previousKey != null) {
            loadBefore(new LoadParams<>(previousKey, i2), new LoadCallbackImpl(this, 2, executor, receiver));
        } else {
            receiver.onPageResult(2, PageResult.getEmptyResult());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public final void dispatchLoadInitial(@Nullable Key key, int i, int i2, boolean z, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, receiver);
        loadInitial(new LoadInitialParams<>(i, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    @Nullable
    /* renamed from: getKey */
    public final Key mo191getKey(int i, Value value) {
        return null;
    }

    void initKeys(@Nullable Key key, @Nullable Key key2) {
        synchronized (this.mKeyLock) {
            this.mPreviousKey = key;
            this.mNextKey = key2;
        }
    }

    public abstract void loadAfter(@NonNull LoadParams<Key> loadParams, @NonNull LoadCallback<Key, Value> loadCallback);

    public abstract void loadBefore(@NonNull LoadParams<Key> loadParams, @NonNull LoadCallback<Key, Value> loadCallback);

    public abstract void loadInitial(@NonNull LoadInitialParams<Key> loadInitialParams, @NonNull LoadInitialCallback<Key, Value> loadInitialCallback);

    void setNextKey(@Nullable Key key) {
        synchronized (this.mKeyLock) {
            this.mNextKey = key;
        }
    }

    void setPreviousKey(@Nullable Key key) {
        synchronized (this.mKeyLock) {
            this.mPreviousKey = key;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public boolean supportsPageDropping() {
        return false;
    }

    @Override // androidx.paging.DataSource
    @NonNull
    /* renamed from: map  reason: collision with other method in class */
    public final <ToValue> PageKeyedDataSource<Key, ToValue> mo189map(@NonNull Function<Value, ToValue> function) {
        return mo190mapByPage((Function) DataSource.createListFunction(function));
    }

    @Override // androidx.paging.DataSource
    @NonNull
    /* renamed from: mapByPage  reason: collision with other method in class */
    public final <ToValue> PageKeyedDataSource<Key, ToValue> mo190mapByPage(@NonNull Function<List<Value>, List<ToValue>> function) {
        return new WrapperPageKeyedDataSource(this, function);
    }

    /* loaded from: classes.dex */
    static class LoadInitialCallbackImpl<Key, Value> extends LoadInitialCallback<Key, Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final boolean mCountingEnabled;
        private final PageKeyedDataSource<Key, Value> mDataSource;

        LoadInitialCallbackImpl(@NonNull PageKeyedDataSource<Key, Value> pageKeyedDataSource, boolean z, @NonNull PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(pageKeyedDataSource, 0, null, receiver);
            this.mDataSource = pageKeyedDataSource;
            this.mCountingEnabled = z;
        }

        @Override // androidx.paging.PageKeyedDataSource.LoadInitialCallback
        public void onResult(@NonNull List<Value> list, int i, int i2, @Nullable Key key, @Nullable Key key2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                this.mDataSource.initKeys(key, key2);
                int size = (i2 - i) - list.size();
                if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i, size, 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i));
                }
            }
        }

        @Override // androidx.paging.PageKeyedDataSource.LoadInitialCallback
        public void onResult(@NonNull List<Value> list, @Nullable Key key, @Nullable Key key2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mDataSource.initKeys(key, key2);
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, 0));
            }
        }
    }
}

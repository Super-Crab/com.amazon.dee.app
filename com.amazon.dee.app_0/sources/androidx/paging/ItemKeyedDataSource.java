package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageResult;
import java.util.List;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
public abstract class ItemKeyedDataSource<Key, Value> extends ContiguousDataSource<Key, Value> {

    /* loaded from: classes.dex */
    public static abstract class LoadCallback<Value> {
        public abstract void onResult(@NonNull List<Value> list);
    }

    /* loaded from: classes.dex */
    static class LoadCallbackImpl<Value> extends LoadCallback<Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;

        LoadCallbackImpl(@NonNull ItemKeyedDataSource itemKeyedDataSource, int i, @Nullable Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(itemKeyedDataSource, i, executor, receiver);
        }

        @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
        public void onResult(@NonNull List<Value> list) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, 0));
            }
        }
    }

    /* loaded from: classes.dex */
    public static abstract class LoadInitialCallback<Value> extends LoadCallback<Value> {
        public abstract void onResult(@NonNull List<Value> list, int i, int i2);
    }

    /* loaded from: classes.dex */
    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        @Nullable
        public final Key requestedInitialKey;
        public final int requestedLoadSize;

        public LoadInitialParams(@Nullable Key key, int i, boolean z) {
            this.requestedInitialKey = key;
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

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public final void dispatchLoadAfter(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        loadAfter(new LoadParams<>(getKey(value), i2), new LoadCallbackImpl(this, 1, executor, receiver));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public final void dispatchLoadBefore(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        loadBefore(new LoadParams<>(getKey(value), i2), new LoadCallbackImpl(this, 2, executor, receiver));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    public final void dispatchLoadInitial(@Nullable Key key, int i, int i2, boolean z, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, receiver);
        loadInitial(new LoadInitialParams<>(key, i, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.ContiguousDataSource
    @Nullable
    /* renamed from: getKey */
    public final Key mo191getKey(int i, Value value) {
        if (value == null) {
            return null;
        }
        return getKey(value);
    }

    @NonNull
    public abstract Key getKey(@NonNull Value value);

    public abstract void loadAfter(@NonNull LoadParams<Key> loadParams, @NonNull LoadCallback<Value> loadCallback);

    public abstract void loadBefore(@NonNull LoadParams<Key> loadParams, @NonNull LoadCallback<Value> loadCallback);

    public abstract void loadInitial(@NonNull LoadInitialParams<Key> loadInitialParams, @NonNull LoadInitialCallback<Value> loadInitialCallback);

    @Override // androidx.paging.DataSource
    @NonNull
    /* renamed from: map  reason: collision with other method in class */
    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mo189map(@NonNull Function<Value, ToValue> function) {
        return mo190mapByPage((Function) DataSource.createListFunction(function));
    }

    @Override // androidx.paging.DataSource
    @NonNull
    /* renamed from: mapByPage  reason: collision with other method in class */
    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mo190mapByPage(@NonNull Function<List<Value>, List<ToValue>> function) {
        return new WrapperItemKeyedDataSource(this, function);
    }

    /* loaded from: classes.dex */
    static class LoadInitialCallbackImpl<Value> extends LoadInitialCallback<Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final boolean mCountingEnabled;

        LoadInitialCallbackImpl(@NonNull ItemKeyedDataSource itemKeyedDataSource, boolean z, @NonNull PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(itemKeyedDataSource, 0, null, receiver);
            this.mCountingEnabled = z;
        }

        @Override // androidx.paging.ItemKeyedDataSource.LoadInitialCallback
        public void onResult(@NonNull List<Value> list, int i, int i2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                int size = (i2 - i) - list.size();
                if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i, size, 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, i));
                }
            }
        }

        @Override // androidx.paging.ItemKeyedDataSource.LoadCallback
        public void onResult(@NonNull List<Value> list) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult<>(list, 0, 0, 0));
            }
        }
    }
}

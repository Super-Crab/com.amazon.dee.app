package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageResult;
import java.util.concurrent.Executor;
/* loaded from: classes.dex */
abstract class ContiguousDataSource<Key, Value> extends DataSource<Key, Value> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dispatchLoadAfter(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dispatchLoadBefore(int i, @NonNull Value value, int i2, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void dispatchLoadInitial(@Nullable Key key, int i, int i2, boolean z, @NonNull Executor executor, @NonNull PageResult.Receiver<Value> receiver);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: getKey */
    public abstract Key mo191getKey(int i, Value value);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.DataSource
    public boolean isContiguous() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean supportsPageDropping() {
        return true;
    }
}

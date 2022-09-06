package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.WorkerThread;
import androidx.paging.PositionalDataSource;
import java.util.Collections;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
@Deprecated
/* loaded from: classes.dex */
public abstract class TiledDataSource<T> extends PositionalDataSource<T> {
    @WorkerThread
    public abstract int countItems();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // androidx.paging.PositionalDataSource, androidx.paging.DataSource
    public boolean isContiguous() {
        return false;
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        int countItems = countItems();
        if (countItems == 0) {
            loadInitialCallback.onResult(Collections.emptyList(), 0, 0);
            return;
        }
        int computeInitialLoadPosition = PositionalDataSource.computeInitialLoadPosition(loadInitialParams, countItems);
        int computeInitialLoadSize = PositionalDataSource.computeInitialLoadSize(loadInitialParams, computeInitialLoadPosition, countItems);
        List<T> loadRange = loadRange(computeInitialLoadPosition, computeInitialLoadSize);
        if (loadRange != null && loadRange.size() == computeInitialLoadSize) {
            loadInitialCallback.onResult(loadRange, computeInitialLoadPosition, countItems);
        } else {
            invalidate();
        }
    }

    @Nullable
    @WorkerThread
    public abstract List<T> loadRange(int i, int i2);

    @Override // androidx.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        List<T> loadRange = loadRange(loadRangeParams.startPosition, loadRangeParams.loadSize);
        if (loadRange != null) {
            loadRangeCallback.onResult(loadRange);
        } else {
            invalidate();
        }
    }
}

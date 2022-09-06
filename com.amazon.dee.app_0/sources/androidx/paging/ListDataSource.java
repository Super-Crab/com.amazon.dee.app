package androidx.paging;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
class ListDataSource<T> extends PositionalDataSource<T> {
    private final List<T> mList;

    public ListDataSource(List<T> list) {
        this.mList = new ArrayList(list);
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadInitial(@NonNull PositionalDataSource.LoadInitialParams loadInitialParams, @NonNull PositionalDataSource.LoadInitialCallback<T> loadInitialCallback) {
        int size = this.mList.size();
        int computeInitialLoadPosition = PositionalDataSource.computeInitialLoadPosition(loadInitialParams, size);
        loadInitialCallback.onResult(this.mList.subList(computeInitialLoadPosition, PositionalDataSource.computeInitialLoadSize(loadInitialParams, computeInitialLoadPosition, size) + computeInitialLoadPosition), computeInitialLoadPosition, size);
    }

    @Override // androidx.paging.PositionalDataSource
    public void loadRange(@NonNull PositionalDataSource.LoadRangeParams loadRangeParams, @NonNull PositionalDataSource.LoadRangeCallback<T> loadRangeCallback) {
        List<T> list = this.mList;
        int i = loadRangeParams.startPosition;
        loadRangeCallback.onResult(list.subList(i, loadRangeParams.loadSize + i));
    }
}

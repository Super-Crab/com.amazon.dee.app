package com.amazon.alexa.biloba.view.common.recycler;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.biloba.utils.LogUtils;
/* loaded from: classes6.dex */
public abstract class RecyclerPaginationListener extends RecyclerView.OnScrollListener {
    private static final String TAG = "RecyclerPaginationListener";
    private LinearLayoutManager linearLayoutManager;
    private final int loadMoreItemThreshold;
    private boolean isLoading = false;
    private boolean hasMore = true;

    public RecyclerPaginationListener(LinearLayoutManager linearLayoutManager, int i) {
        this.linearLayoutManager = linearLayoutManager;
        this.loadMoreItemThreshold = i;
    }

    private boolean canLoadMore(int i, int i2, int i3) {
        return this.hasMore && !this.isLoading && i - i2 <= i3 + this.loadMoreItemThreshold;
    }

    public boolean getIsLoading() {
        return this.isLoading;
    }

    public abstract void onLoadMore();

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        super.onScrolled(recyclerView, i, i2);
        if (i2 < 0) {
            return;
        }
        int childCount = recyclerView.getChildCount();
        int itemCount = this.linearLayoutManager.getItemCount();
        int findFirstVisibleItemPosition = this.linearLayoutManager.findFirstVisibleItemPosition();
        synchronized (this) {
            if (canLoadMore(itemCount, childCount, findFirstVisibleItemPosition)) {
                LogUtils.i(TAG, "Read end of the list, loadMore");
                onLoadMore();
                this.isLoading = true;
            }
        }
    }

    public void setHasMore(boolean z) {
        this.hasMore = z;
    }

    public void setIsLoading(boolean z) {
        this.isLoading = z;
    }
}

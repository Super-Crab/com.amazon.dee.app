package androidx.paging;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
/* loaded from: classes.dex */
class PagedStorageDiffHelper {

    /* loaded from: classes.dex */
    private static class OffsettingListUpdateCallback implements ListUpdateCallback {
        private final ListUpdateCallback mCallback;
        private final int mOffset;

        OffsettingListUpdateCallback(int i, ListUpdateCallback listUpdateCallback) {
            this.mOffset = i;
            this.mCallback = listUpdateCallback;
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onChanged(int i, int i2, Object obj) {
            this.mCallback.onChanged(i + this.mOffset, i2, obj);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onInserted(int i, int i2) {
            this.mCallback.onInserted(i + this.mOffset, i2);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onMoved(int i, int i2) {
            ListUpdateCallback listUpdateCallback = this.mCallback;
            int i3 = this.mOffset;
            listUpdateCallback.onMoved(i + i3, i2 + i3);
        }

        @Override // androidx.recyclerview.widget.ListUpdateCallback
        public void onRemoved(int i, int i2) {
            this.mCallback.onRemoved(i + this.mOffset, i2);
        }
    }

    private PagedStorageDiffHelper() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> DiffUtil.DiffResult computeDiff(final PagedStorage<T> pagedStorage, final PagedStorage<T> pagedStorage2, final DiffUtil.ItemCallback<T> itemCallback) {
        final int computeLeadingNulls = pagedStorage.computeLeadingNulls();
        int computeLeadingNulls2 = pagedStorage2.computeLeadingNulls();
        final int size = (pagedStorage.size() - computeLeadingNulls) - pagedStorage.computeTrailingNulls();
        final int size2 = (pagedStorage2.size() - computeLeadingNulls2) - pagedStorage2.computeTrailingNulls();
        return DiffUtil.calculateDiff(new DiffUtil.Callback() { // from class: androidx.paging.PagedStorageDiffHelper.1
            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areContentsTheSame(int i, int i2) {
                Object obj = PagedStorage.this.get(i + computeLeadingNulls);
                PagedStorage pagedStorage3 = pagedStorage2;
                Object obj2 = pagedStorage3.get(i2 + pagedStorage3.getLeadingNullCount());
                if (obj == obj2) {
                    return true;
                }
                if (obj != null && obj2 != null) {
                    return itemCallback.areContentsTheSame(obj, obj2);
                }
                return false;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public boolean areItemsTheSame(int i, int i2) {
                Object obj = PagedStorage.this.get(i + computeLeadingNulls);
                PagedStorage pagedStorage3 = pagedStorage2;
                Object obj2 = pagedStorage3.get(i2 + pagedStorage3.getLeadingNullCount());
                if (obj == obj2) {
                    return true;
                }
                if (obj != null && obj2 != null) {
                    return itemCallback.areItemsTheSame(obj, obj2);
                }
                return false;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            @Nullable
            public Object getChangePayload(int i, int i2) {
                Object obj = PagedStorage.this.get(i + computeLeadingNulls);
                PagedStorage pagedStorage3 = pagedStorage2;
                Object obj2 = pagedStorage3.get(i2 + pagedStorage3.getLeadingNullCount());
                if (obj == null || obj2 == null) {
                    return null;
                }
                return itemCallback.getChangePayload(obj, obj2);
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getNewListSize() {
                return size2;
            }

            @Override // androidx.recyclerview.widget.DiffUtil.Callback
            public int getOldListSize() {
                return size;
            }
        }, true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> void dispatchDiff(ListUpdateCallback listUpdateCallback, PagedStorage<T> pagedStorage, PagedStorage<T> pagedStorage2, DiffUtil.DiffResult diffResult) {
        int computeTrailingNulls = pagedStorage.computeTrailingNulls();
        int computeTrailingNulls2 = pagedStorage2.computeTrailingNulls();
        int computeLeadingNulls = pagedStorage.computeLeadingNulls();
        int computeLeadingNulls2 = pagedStorage2.computeLeadingNulls();
        if (computeTrailingNulls == 0 && computeTrailingNulls2 == 0 && computeLeadingNulls == 0 && computeLeadingNulls2 == 0) {
            diffResult.dispatchUpdatesTo(listUpdateCallback);
            return;
        }
        if (computeTrailingNulls > computeTrailingNulls2) {
            int i = computeTrailingNulls - computeTrailingNulls2;
            listUpdateCallback.onRemoved(pagedStorage.size() - i, i);
        } else if (computeTrailingNulls < computeTrailingNulls2) {
            listUpdateCallback.onInserted(pagedStorage.size(), computeTrailingNulls2 - computeTrailingNulls);
        }
        if (computeLeadingNulls > computeLeadingNulls2) {
            listUpdateCallback.onRemoved(0, computeLeadingNulls - computeLeadingNulls2);
        } else if (computeLeadingNulls < computeLeadingNulls2) {
            listUpdateCallback.onInserted(0, computeLeadingNulls2 - computeLeadingNulls);
        }
        if (computeLeadingNulls2 != 0) {
            diffResult.dispatchUpdatesTo(new OffsettingListUpdateCallback(computeLeadingNulls2, listUpdateCallback));
        } else {
            diffResult.dispatchUpdatesTo(listUpdateCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int transformAnchorIndex(@NonNull DiffUtil.DiffResult diffResult, @NonNull PagedStorage pagedStorage, @NonNull PagedStorage pagedStorage2, int i) {
        int convertOldPositionToNew;
        int computeLeadingNulls = pagedStorage.computeLeadingNulls();
        int i2 = i - computeLeadingNulls;
        int size = (pagedStorage.size() - computeLeadingNulls) - pagedStorage.computeTrailingNulls();
        if (i2 >= 0 && i2 < size) {
            for (int i3 = 0; i3 < 30; i3++) {
                int i4 = ((i3 / 2) * (i3 % 2 == 1 ? -1 : 1)) + i2;
                if (i4 >= 0 && i4 < pagedStorage.getStorageCount() && (convertOldPositionToNew = diffResult.convertOldPositionToNew(i4)) != -1) {
                    return convertOldPositionToNew + pagedStorage2.getLeadingNullCount();
                }
            }
        }
        return Math.max(0, Math.min(i, pagedStorage2.size() - 1));
    }
}

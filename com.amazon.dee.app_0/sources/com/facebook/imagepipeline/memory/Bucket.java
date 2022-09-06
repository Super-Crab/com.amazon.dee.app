package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
@NotThreadSafe
@VisibleForTesting
/* loaded from: classes2.dex */
class Bucket<V> {
    private static final String TAG = "BUCKET";
    private final boolean mFixBucketsReinitialization;
    final Queue mFreeList;
    private int mInUseLength;
    public final int mItemSize;
    public final int mMaxLength;

    public Bucket(int itemSize, int maxLength, int inUseLength, boolean fixBucketsReinitialization) {
        boolean z = true;
        Preconditions.checkState(itemSize > 0);
        Preconditions.checkState(maxLength >= 0);
        Preconditions.checkState(inUseLength < 0 ? false : z);
        this.mItemSize = itemSize;
        this.mMaxLength = maxLength;
        this.mFreeList = new LinkedList();
        this.mInUseLength = inUseLength;
        this.mFixBucketsReinitialization = fixBucketsReinitialization;
    }

    void addToFreeList(V value) {
        this.mFreeList.add(value);
    }

    public void decrementInUseCount() {
        Preconditions.checkState(this.mInUseLength > 0);
        this.mInUseLength--;
    }

    @Nullable
    @Deprecated
    public V get() {
        V pop = pop();
        if (pop != null) {
            this.mInUseLength++;
        }
        return pop;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getFreeListSize() {
        return this.mFreeList.size();
    }

    public int getInUseCount() {
        return this.mInUseLength;
    }

    public void incrementInUseCount() {
        this.mInUseLength++;
    }

    public boolean isMaxLengthExceeded() {
        return this.mInUseLength + getFreeListSize() > this.mMaxLength;
    }

    @Nullable
    public V pop() {
        return (V) this.mFreeList.poll();
    }

    public void release(V value) {
        Preconditions.checkNotNull(value);
        boolean z = false;
        if (this.mFixBucketsReinitialization) {
            if (this.mInUseLength > 0) {
                z = true;
            }
            Preconditions.checkState(z);
            this.mInUseLength--;
            addToFreeList(value);
            return;
        }
        int i = this.mInUseLength;
        if (i > 0) {
            this.mInUseLength = i - 1;
            addToFreeList(value);
            return;
        }
        FLog.e(TAG, "Tried to release value %s from an empty bucket!", value);
    }
}

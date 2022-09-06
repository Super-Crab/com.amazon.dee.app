package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.infer.annotation.ThreadSafe;
import java.util.LinkedList;
import javax.annotation.Nullable;
@ThreadSafe
/* loaded from: classes2.dex */
public class BucketMap<T> {
    @Nullable
    @VisibleForTesting
    LinkedEntry<T> mHead;
    protected final SparseArray<LinkedEntry<T>> mMap = new SparseArray<>();
    @Nullable
    @VisibleForTesting
    LinkedEntry<T> mTail;

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    /* loaded from: classes2.dex */
    public static class LinkedEntry<I> {
        int key;
        @Nullable
        LinkedEntry<I> next;
        @Nullable
        LinkedEntry<I> prev;
        LinkedList<I> value;

        public String toString() {
            return GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("LinkedEntry(key: "), this.key, ")");
        }

        private LinkedEntry(@Nullable LinkedEntry<I> prev, int key, LinkedList<I> value, @Nullable LinkedEntry<I> next) {
            this.prev = prev;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private void maybePrune(LinkedEntry<T> bucket) {
        if (bucket == null || !bucket.value.isEmpty()) {
            return;
        }
        prune(bucket);
        this.mMap.remove(bucket.key);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void moveToFront(LinkedEntry<T> bucket) {
        if (this.mHead == bucket) {
            return;
        }
        prune(bucket);
        LinkedEntry linkedEntry = (LinkedEntry<T>) this.mHead;
        if (linkedEntry == null) {
            this.mHead = bucket;
            this.mTail = bucket;
            return;
        }
        bucket.next = linkedEntry;
        linkedEntry.prev = bucket;
        this.mHead = bucket;
    }

    private synchronized void prune(LinkedEntry<T> bucket) {
        LinkedEntry linkedEntry = (LinkedEntry<T>) bucket.prev;
        LinkedEntry linkedEntry2 = (LinkedEntry<T>) bucket.next;
        if (linkedEntry != null) {
            linkedEntry.next = linkedEntry2;
        }
        if (linkedEntry2 != null) {
            linkedEntry2.prev = linkedEntry;
        }
        bucket.prev = null;
        bucket.next = null;
        if (bucket == this.mHead) {
            this.mHead = linkedEntry2;
        }
        if (bucket == this.mTail) {
            this.mTail = linkedEntry;
        }
    }

    @Nullable
    public synchronized T acquire(int key) {
        LinkedEntry<T> linkedEntry = this.mMap.get(key);
        if (linkedEntry == null) {
            return null;
        }
        T pollFirst = linkedEntry.value.pollFirst();
        moveToFront(linkedEntry);
        return pollFirst;
    }

    public synchronized void release(int key, T value) {
        LinkedEntry<T> linkedEntry = this.mMap.get(key);
        if (linkedEntry == null) {
            linkedEntry = new LinkedEntry<>(null, key, new LinkedList(), null);
            this.mMap.put(key, linkedEntry);
        }
        linkedEntry.value.addLast(value);
        moveToFront(linkedEntry);
    }

    @Nullable
    public synchronized T removeFromEnd() {
        LinkedEntry<T> linkedEntry = this.mTail;
        if (linkedEntry == null) {
            return null;
        }
        T pollLast = linkedEntry.value.pollLast();
        maybePrune(linkedEntry);
        return pollLast;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public synchronized int valueCount() {
        int i;
        i = 0;
        for (LinkedEntry linkedEntry = this.mHead; linkedEntry != null; linkedEntry = linkedEntry.next) {
            LinkedList<I> linkedList = linkedEntry.value;
            if (linkedList != 0) {
                i += linkedList.size();
            }
        }
        return i;
    }
}

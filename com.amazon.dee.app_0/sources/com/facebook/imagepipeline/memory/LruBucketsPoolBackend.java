package com.facebook.imagepipeline.memory;

import androidx.annotation.VisibleForTesting;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public abstract class LruBucketsPoolBackend<T> implements PoolBackend<T> {
    private final Set<T> mCurrentItems = new HashSet();
    private final BucketMap<T> mMap = new BucketMap<>();

    @Nullable
    private T maybeRemoveFromCurrentItems(@Nullable T t) {
        if (t != null) {
            synchronized (this) {
                this.mCurrentItems.remove(t);
            }
        }
        return t;
    }

    @Override // com.facebook.imagepipeline.memory.PoolBackend
    @Nullable
    /* renamed from: get */
    public T mo6899get(int size) {
        return maybeRemoveFromCurrentItems(this.mMap.acquire(size));
    }

    @Override // com.facebook.imagepipeline.memory.PoolBackend
    @Nullable
    public T pop() {
        return maybeRemoveFromCurrentItems(this.mMap.removeFromEnd());
    }

    @Override // com.facebook.imagepipeline.memory.PoolBackend
    public void put(T item) {
        boolean add;
        synchronized (this) {
            add = this.mCurrentItems.add(item);
        }
        if (add) {
            this.mMap.release(getSize(item), item);
        }
    }

    @VisibleForTesting
    int valueCount() {
        return this.mMap.valueCount();
    }
}

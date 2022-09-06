package com.facebook.imagepipeline.cache;

import java.util.LinkedHashSet;
import javax.annotation.concurrent.ThreadSafe;
@ThreadSafe
/* loaded from: classes2.dex */
public class BoundedLinkedHashSet<E> {
    private LinkedHashSet<E> mLinkedHashSet;
    private int mMaxSize;

    public BoundedLinkedHashSet(final int maxSize) {
        this.mLinkedHashSet = new LinkedHashSet<>(maxSize);
        this.mMaxSize = maxSize;
    }

    public synchronized boolean add(E key) {
        if (this.mLinkedHashSet.size() == this.mMaxSize) {
            this.mLinkedHashSet.remove(this.mLinkedHashSet.iterator().next());
        }
        this.mLinkedHashSet.remove(key);
        return this.mLinkedHashSet.add(key);
    }

    public synchronized boolean contains(E o) {
        return this.mLinkedHashSet.contains(o);
    }
}

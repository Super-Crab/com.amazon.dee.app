package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableIterator;
/* loaded from: classes4.dex */
public class SingletonIterator<E> implements ResettableIterator<E> {
    private boolean beforeFirst;
    private E object;
    private final boolean removeAllowed;
    private boolean removed;

    public SingletonIterator(E e) {
        this(e, true);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.beforeFirst && !this.removed;
    }

    @Override // java.util.Iterator
    public E next() {
        if (this.beforeFirst && !this.removed) {
            this.beforeFirst = false;
            return this.object;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public void remove() {
        if (this.removeAllowed) {
            if (!this.removed && !this.beforeFirst) {
                this.object = null;
                this.removed = true;
                return;
            }
            throw new IllegalStateException();
        }
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        this.beforeFirst = true;
    }

    public SingletonIterator(E e, boolean z) {
        this.beforeFirst = true;
        this.removed = false;
        this.object = e;
        this.removeAllowed = z;
    }
}

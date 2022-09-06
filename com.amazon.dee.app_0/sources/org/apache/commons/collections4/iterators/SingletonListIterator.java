package org.apache.commons.collections4.iterators;

import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;
/* loaded from: classes4.dex */
public class SingletonListIterator<E> implements ResettableListIterator<E> {
    private E object;
    private boolean beforeFirst = true;
    private boolean nextCalled = false;
    private boolean removed = false;

    public SingletonListIterator(E e) {
        this.object = e;
    }

    @Override // java.util.ListIterator
    public void add(E e) {
        throw new UnsupportedOperationException("add() is not supported by this iterator");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.beforeFirst && !this.removed;
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    public boolean hasPrevious() {
        return !this.beforeFirst && !this.removed;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() {
        if (this.beforeFirst && !this.removed) {
            this.beforeFirst = false;
            this.nextCalled = true;
            return this.object;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return !this.beforeFirst ? 1 : 0;
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    /* renamed from: previous */
    public E mo12706previous() {
        if (!this.beforeFirst && !this.removed) {
            this.beforeFirst = true;
            return this.object;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.beforeFirst ? -1 : 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        if (this.nextCalled && !this.removed) {
            this.object = null;
            this.removed = true;
            return;
        }
        throw new IllegalStateException();
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        this.beforeFirst = true;
        this.nextCalled = false;
    }

    @Override // java.util.ListIterator
    public void set(E e) {
        if (this.nextCalled && !this.removed) {
            this.object = e;
            return;
        }
        throw new IllegalStateException();
    }
}

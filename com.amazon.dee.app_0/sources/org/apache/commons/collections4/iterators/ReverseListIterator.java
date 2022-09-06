package org.apache.commons.collections4.iterators;

import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.ResettableListIterator;
/* loaded from: classes4.dex */
public class ReverseListIterator<E> implements ResettableListIterator<E> {
    private ListIterator<E> iterator;
    private final List<E> list;
    private boolean validForUpdate = true;

    public ReverseListIterator(List<E> list) {
        if (list != null) {
            this.list = list;
            this.iterator = list.listIterator(list.size());
            return;
        }
        throw new NullPointerException("List must not be null.");
    }

    @Override // java.util.ListIterator
    public void add(E e) {
        if (this.validForUpdate) {
            this.validForUpdate = false;
            this.iterator.add(e);
            this.iterator.previous();
            return;
        }
        throw new IllegalStateException("Cannot add to list until next() or previous() called");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasPrevious();
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    public boolean hasPrevious() {
        return this.iterator.hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() {
        E previous = this.iterator.previous();
        this.validForUpdate = true;
        return previous;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return this.iterator.previousIndex();
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    /* renamed from: previous */
    public E mo12706previous() {
        E next = this.iterator.next();
        this.validForUpdate = true;
        return next;
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return this.iterator.nextIndex();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        if (this.validForUpdate) {
            this.iterator.remove();
            return;
        }
        throw new IllegalStateException("Cannot remove from list until next() or previous() called");
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        List<E> list = this.list;
        this.iterator = list.listIterator(list.size());
    }

    @Override // java.util.ListIterator
    public void set(E e) {
        if (this.validForUpdate) {
            this.iterator.set(e);
            return;
        }
        throw new IllegalStateException("Cannot set to list until next() or previous() called");
    }
}

package org.apache.commons.collections4.iterators;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;
/* loaded from: classes4.dex */
public class LoopingListIterator<E> implements ResettableListIterator<E> {
    private ListIterator<E> iterator;
    private final List<E> list;

    public LoopingListIterator(List<E> list) {
        if (list != null) {
            this.list = list;
            _reset();
            return;
        }
        throw new NullPointerException("The list must not be null");
    }

    private void _reset() {
        this.iterator = this.list.listIterator();
    }

    @Override // java.util.ListIterator
    public void add(E e) {
        this.iterator.add(e);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        return !this.list.isEmpty();
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    public boolean hasPrevious() {
        return !this.list.isEmpty();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() {
        if (!this.list.isEmpty()) {
            if (!this.iterator.hasNext()) {
                reset();
            }
            return this.iterator.next();
        }
        throw new NoSuchElementException("There are no elements for this iterator to loop on");
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        if (!this.list.isEmpty()) {
            if (this.iterator.hasNext()) {
                return this.iterator.nextIndex();
            }
            return 0;
        }
        throw new NoSuchElementException("There are no elements for this iterator to loop on");
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    /* renamed from: previous */
    public E mo12706previous() {
        if (!this.list.isEmpty()) {
            if (!this.iterator.hasPrevious()) {
                E e = null;
                while (this.iterator.hasNext()) {
                    e = this.iterator.next();
                }
                this.iterator.previous();
                return e;
            }
            return this.iterator.previous();
        }
        throw new NoSuchElementException("There are no elements for this iterator to loop on");
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        if (!this.list.isEmpty()) {
            if (!this.iterator.hasPrevious()) {
                return this.list.size() - 1;
            }
            return this.iterator.previousIndex();
        }
        throw new NoSuchElementException("There are no elements for this iterator to loop on");
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        _reset();
    }

    @Override // java.util.ListIterator
    public void set(E e) {
        this.iterator.set(e);
    }

    public int size() {
        return this.list.size();
    }
}

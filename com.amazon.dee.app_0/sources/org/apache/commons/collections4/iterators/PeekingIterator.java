package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes4.dex */
public class PeekingIterator<E> implements Iterator<E> {
    private final Iterator<? extends E> iterator;
    private E slot;
    private boolean exhausted = false;
    private boolean slotFilled = false;

    public PeekingIterator(Iterator<? extends E> it2) {
        this.iterator = it2;
    }

    private void fill() {
        if (this.exhausted || this.slotFilled) {
            return;
        }
        if (this.iterator.hasNext()) {
            this.slot = this.iterator.next();
            this.slotFilled = true;
            return;
        }
        this.exhausted = true;
        this.slot = null;
        this.slotFilled = false;
    }

    public static <E> PeekingIterator<E> peekingIterator(Iterator<? extends E> it2) {
        if (it2 != null) {
            if (it2 instanceof PeekingIterator) {
                return (PeekingIterator) it2;
            }
            return new PeekingIterator<>(it2);
        }
        throw new NullPointerException("Iterator must not be null");
    }

    public E element() {
        fill();
        if (!this.exhausted) {
            return this.slot;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (this.exhausted) {
            return false;
        }
        if (!this.slotFilled) {
            return this.iterator.hasNext();
        }
        return true;
    }

    @Override // java.util.Iterator
    public E next() {
        if (hasNext()) {
            E next = this.slotFilled ? this.slot : this.iterator.next();
            this.slot = null;
            this.slotFilled = false;
            return next;
        }
        throw new NoSuchElementException();
    }

    public E peek() {
        fill();
        if (this.exhausted) {
            return null;
        }
        return this.slot;
    }

    @Override // java.util.Iterator
    public void remove() {
        if (!this.slotFilled) {
            this.iterator.remove();
            return;
        }
        throw new IllegalStateException("peek() or element() called before remove()");
    }
}

package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
/* loaded from: classes4.dex */
public class PushbackIterator<E> implements Iterator<E> {
    private final Deque<E> items = new ArrayDeque();
    private final Iterator<? extends E> iterator;

    public PushbackIterator(Iterator<? extends E> it2) {
        this.iterator = it2;
    }

    public static <E> PushbackIterator<E> pushbackIterator(Iterator<? extends E> it2) {
        if (it2 != null) {
            if (it2 instanceof PushbackIterator) {
                return (PushbackIterator) it2;
            }
            return new PushbackIterator<>(it2);
        }
        throw new NullPointerException("Iterator must not be null");
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        if (!this.items.isEmpty()) {
            return true;
        }
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        return !this.items.isEmpty() ? this.items.pop() : this.iterator.next();
    }

    public void pushback(E e) {
        this.items.push(e);
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException();
    }
}

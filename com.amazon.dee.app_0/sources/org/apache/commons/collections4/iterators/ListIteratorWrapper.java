package org.apache.commons.collections4.iterators;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.ResettableListIterator;
/* loaded from: classes4.dex */
public class ListIteratorWrapper<E> implements ResettableListIterator<E> {
    private static final String CANNOT_REMOVE_MESSAGE = "Cannot remove element at index {0}.";
    private static final String UNSUPPORTED_OPERATION_MESSAGE = "ListIteratorWrapper does not support optional operations of ListIterator.";
    private final Iterator<? extends E> iterator;
    private boolean removeState;
    private final List<E> list = new ArrayList();
    private int currentIndex = 0;
    private int wrappedIteratorIndex = 0;

    public ListIteratorWrapper(Iterator<? extends E> it2) {
        if (it2 != null) {
            this.iterator = it2;
            return;
        }
        throw new NullPointerException("Iterator must not be null");
    }

    @Override // java.util.ListIterator
    public void add(E e) throws UnsupportedOperationException {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            ((ListIterator) it2).add(e);
            return;
        }
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public boolean hasNext() {
        if (this.currentIndex == this.wrappedIteratorIndex || (this.iterator instanceof ListIterator)) {
            return this.iterator.hasNext();
        }
        return true;
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    public boolean hasPrevious() {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            return ((ListIterator) it2).hasPrevious();
        }
        return this.currentIndex > 0;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public E next() throws NoSuchElementException {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            return it2.next();
        }
        int i = this.currentIndex;
        if (i < this.wrappedIteratorIndex) {
            this.currentIndex = i + 1;
            return this.list.get(this.currentIndex - 1);
        }
        E next = it2.next();
        this.list.add(next);
        this.currentIndex++;
        this.wrappedIteratorIndex++;
        this.removeState = true;
        return next;
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            return ((ListIterator) it2).nextIndex();
        }
        return this.currentIndex;
    }

    @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
    /* renamed from: previous */
    public E mo12706previous() throws NoSuchElementException {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            return (E) ((ListIterator) it2).previous();
        }
        int i = this.currentIndex;
        if (i != 0) {
            this.removeState = this.wrappedIteratorIndex == i;
            List<E> list = this.list;
            int i2 = this.currentIndex - 1;
            this.currentIndex = i2;
            return list.get(i2);
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            return ((ListIterator) it2).previousIndex();
        }
        return this.currentIndex - 1;
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public void remove() throws UnsupportedOperationException {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            it2.remove();
            return;
        }
        int i = this.currentIndex;
        if (i == this.wrappedIteratorIndex) {
            i--;
        }
        if (this.removeState && this.wrappedIteratorIndex - this.currentIndex <= 1) {
            this.iterator.remove();
            this.list.remove(i);
            this.currentIndex = i;
            this.wrappedIteratorIndex--;
            this.removeState = false;
            return;
        }
        throw new IllegalStateException(MessageFormat.format(CANNOT_REMOVE_MESSAGE, Integer.valueOf(i)));
    }

    @Override // org.apache.commons.collections4.ResettableIterator
    public void reset() {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            ListIterator listIterator = (ListIterator) it2;
            while (listIterator.previousIndex() >= 0) {
                listIterator.previous();
            }
            return;
        }
        this.currentIndex = 0;
    }

    @Override // java.util.ListIterator
    public void set(E e) throws UnsupportedOperationException {
        Iterator<? extends E> it2 = this.iterator;
        if (it2 instanceof ListIterator) {
            ((ListIterator) it2).set(e);
            return;
        }
        throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_MESSAGE);
    }
}

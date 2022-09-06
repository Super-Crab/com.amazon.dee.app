package org.apache.commons.collections4.iterators;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class ObjectGraphIterator<E> implements Iterator<E> {
    private Iterator<? extends E> currentIterator;
    private E currentValue;
    private boolean hasNext;
    private Iterator<? extends E> lastUsedIterator;
    private E root;
    private final Deque<Iterator<? extends E>> stack;
    private final Transformer<? super E, ? extends E> transformer;

    public ObjectGraphIterator(E e, Transformer<? super E, ? extends E> transformer) {
        this.stack = new ArrayDeque(8);
        this.hasNext = false;
        if (e instanceof Iterator) {
            this.currentIterator = (Iterator) e;
        } else {
            this.root = e;
        }
        this.transformer = transformer;
    }

    protected void findNext(E e) {
        if (e instanceof Iterator) {
            findNextByIterator((Iterator) e);
            return;
        }
        this.currentValue = e;
        this.hasNext = true;
    }

    protected void findNextByIterator(Iterator<? extends E> it2) {
        Iterator<? extends E> it3 = this.currentIterator;
        if (it2 != it3) {
            if (it3 != null) {
                this.stack.push(it3);
            }
            this.currentIterator = it2;
        }
        while (this.currentIterator.hasNext() && !this.hasNext) {
            E next = this.currentIterator.next();
            Transformer<? super E, ? extends E> transformer = this.transformer;
            if (transformer != null) {
                next = transformer.mo12738transform(next);
            }
            findNext(next);
        }
        if (this.hasNext || this.stack.isEmpty()) {
            return;
        }
        this.currentIterator = this.stack.pop();
        findNextByIterator(this.currentIterator);
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        updateCurrentIterator();
        return this.hasNext;
    }

    @Override // java.util.Iterator
    public E next() {
        updateCurrentIterator();
        if (this.hasNext) {
            this.lastUsedIterator = this.currentIterator;
            E e = this.currentValue;
            this.currentValue = null;
            this.hasNext = false;
            return e;
        }
        throw new NoSuchElementException("No more elements in the iteration");
    }

    @Override // java.util.Iterator
    public void remove() {
        Iterator<? extends E> it2 = this.lastUsedIterator;
        if (it2 != null) {
            it2.remove();
            this.lastUsedIterator = null;
            return;
        }
        throw new IllegalStateException("Iterator remove() cannot be called at this time");
    }

    protected void updateCurrentIterator() {
        if (this.hasNext) {
            return;
        }
        Iterator<? extends E> it2 = this.currentIterator;
        if (it2 == null) {
            E e = this.root;
            if (e == null) {
                return;
            }
            Transformer<? super E, ? extends E> transformer = this.transformer;
            if (transformer == null) {
                findNext(e);
            } else {
                findNext(transformer.mo12738transform(e));
            }
            this.root = null;
            return;
        }
        findNextByIterator(it2);
    }

    public ObjectGraphIterator(Iterator<? extends E> it2) {
        this.stack = new ArrayDeque(8);
        this.hasNext = false;
        this.currentIterator = it2;
        this.transformer = null;
    }
}

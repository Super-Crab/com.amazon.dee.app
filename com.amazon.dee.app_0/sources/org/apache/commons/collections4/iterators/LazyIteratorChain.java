package org.apache.commons.collections4.iterators;

import java.util.Iterator;
/* loaded from: classes4.dex */
public abstract class LazyIteratorChain<E> implements Iterator<E> {
    private int callCounter = 0;
    private boolean chainExhausted = false;
    private Iterator<? extends E> currentIterator = null;
    private Iterator<? extends E> lastUsedIterator = null;

    private void updateCurrentIterator() {
        int i = this.callCounter;
        if (i == 0) {
            int i2 = i + 1;
            this.callCounter = i2;
            this.currentIterator = nextIterator(i2);
            if (this.currentIterator == null) {
                this.currentIterator = EmptyIterator.emptyIterator();
                this.chainExhausted = true;
            }
            this.lastUsedIterator = this.currentIterator;
        }
        while (!this.currentIterator.hasNext() && !this.chainExhausted) {
            int i3 = this.callCounter + 1;
            this.callCounter = i3;
            Iterator<? extends E> nextIterator = nextIterator(i3);
            if (nextIterator != null) {
                this.currentIterator = nextIterator;
            } else {
                this.chainExhausted = true;
            }
        }
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        updateCurrentIterator();
        Iterator<? extends E> it2 = this.currentIterator;
        this.lastUsedIterator = it2;
        return it2.hasNext();
    }

    @Override // java.util.Iterator
    public E next() {
        updateCurrentIterator();
        Iterator<? extends E> it2 = this.currentIterator;
        this.lastUsedIterator = it2;
        return it2.next();
    }

    protected abstract Iterator<? extends E> nextIterator(int i);

    @Override // java.util.Iterator
    public void remove() {
        if (this.currentIterator == null) {
            updateCurrentIterator();
        }
        this.lastUsedIterator.remove();
    }
}

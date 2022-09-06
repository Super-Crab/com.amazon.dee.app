package org.apache.commons.collections4.iterators;

import java.util.Iterator;
/* loaded from: classes4.dex */
public abstract class AbstractUntypedIteratorDecorator<I, O> implements Iterator<O> {
    private final Iterator<I> iterator;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractUntypedIteratorDecorator(Iterator<I> it2) {
        if (it2 != null) {
            this.iterator = it2;
            return;
        }
        throw new NullPointerException("Iterator must not be null");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Iterator<I> getIterator() {
        return this.iterator;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }
}

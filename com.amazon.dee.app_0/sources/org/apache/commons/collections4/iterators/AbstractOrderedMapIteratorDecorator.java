package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;
/* loaded from: classes4.dex */
public class AbstractOrderedMapIteratorDecorator<K, V> implements OrderedMapIterator<K, V> {
    private final OrderedMapIterator<K, V> iterator;

    public AbstractOrderedMapIteratorDecorator(OrderedMapIterator<K, V> orderedMapIterator) {
        if (orderedMapIterator != null) {
            this.iterator = orderedMapIterator;
            return;
        }
        throw new NullPointerException("OrderedMapIterator must not be null");
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getKey */
    public K mo12681getKey() {
        return this.iterator.mo12681getKey();
    }

    protected OrderedMapIterator<K, V> getOrderedMapIterator() {
        return this.iterator;
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getValue */
    public V mo12682getValue() {
        return this.iterator.mo12682getValue();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
    public boolean hasPrevious() {
        return this.iterator.hasPrevious();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    /* renamed from: next */
    public K mo12683next() {
        return this.iterator.mo12683next();
    }

    @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
    /* renamed from: previous */
    public K mo12706previous() {
        return this.iterator.mo12706previous();
    }

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }

    @Override // org.apache.commons.collections4.MapIterator
    public V setValue(V v) {
        return this.iterator.setValue(v);
    }
}

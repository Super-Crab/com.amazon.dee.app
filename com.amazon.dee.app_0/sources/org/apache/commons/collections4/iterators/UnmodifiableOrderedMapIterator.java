package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;
/* loaded from: classes4.dex */
public final class UnmodifiableOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, Unmodifiable {
    private final OrderedMapIterator<? extends K, ? extends V> iterator;

    /* JADX WARN: Multi-variable type inference failed */
    private UnmodifiableOrderedMapIterator(OrderedMapIterator<K, ? extends V> orderedMapIterator) {
        this.iterator = orderedMapIterator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> OrderedMapIterator<K, V> unmodifiableOrderedMapIterator(OrderedMapIterator<K, ? extends V> orderedMapIterator) {
        if (orderedMapIterator != 0) {
            return orderedMapIterator instanceof Unmodifiable ? orderedMapIterator : new UnmodifiableOrderedMapIterator(orderedMapIterator);
        }
        throw new NullPointerException("OrderedMapIterator must not be null");
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getKey */
    public K mo12681getKey() {
        return this.iterator.mo12681getKey();
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
        throw new UnsupportedOperationException("remove() is not supported");
    }

    @Override // org.apache.commons.collections4.MapIterator
    public V setValue(V v) {
        throw new UnsupportedOperationException("setValue() is not supported");
    }
}

package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;
/* loaded from: classes4.dex */
public class AbstractMapIteratorDecorator<K, V> implements MapIterator<K, V> {
    private final MapIterator<K, V> iterator;

    public AbstractMapIteratorDecorator(MapIterator<K, V> mapIterator) {
        if (mapIterator != null) {
            this.iterator = mapIterator;
            return;
        }
        throw new NullPointerException("MapIterator must not be null");
    }

    @Override // org.apache.commons.collections4.MapIterator
    /* renamed from: getKey */
    public K mo12681getKey() {
        return this.iterator.mo12681getKey();
    }

    protected MapIterator<K, V> getMapIterator() {
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

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    /* renamed from: next */
    public K mo12683next() {
        return this.iterator.mo12683next();
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

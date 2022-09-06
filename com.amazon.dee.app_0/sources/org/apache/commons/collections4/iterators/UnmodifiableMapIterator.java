package org.apache.commons.collections4.iterators;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Unmodifiable;
/* loaded from: classes4.dex */
public final class UnmodifiableMapIterator<K, V> implements MapIterator<K, V>, Unmodifiable {
    private final MapIterator<? extends K, ? extends V> iterator;

    private UnmodifiableMapIterator(MapIterator<? extends K, ? extends V> mapIterator) {
        this.iterator = mapIterator;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(MapIterator<? extends K, ? extends V> mapIterator) {
        if (mapIterator != 0) {
            return mapIterator instanceof Unmodifiable ? mapIterator : new UnmodifiableMapIterator(mapIterator);
        }
        throw new NullPointerException("MapIterator must not be null");
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

    @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
    /* renamed from: next */
    public K mo12683next() {
        return this.iterator.mo12683next();
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

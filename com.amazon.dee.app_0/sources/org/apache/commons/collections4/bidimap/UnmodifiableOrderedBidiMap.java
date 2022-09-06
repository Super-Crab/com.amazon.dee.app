package org.apache.commons.collections4.bidimap;

import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public final class UnmodifiableOrderedBidiMap<K, V> extends AbstractOrderedBidiMapDecorator<K, V> implements Unmodifiable {
    private UnmodifiableOrderedBidiMap<V, K> inverse;

    private UnmodifiableOrderedBidiMap(OrderedBidiMap<? extends K, ? extends V> orderedBidiMap) {
        super(orderedBidiMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> OrderedBidiMap<K, V> unmodifiableOrderedBidiMap(OrderedBidiMap<? extends K, ? extends V> orderedBidiMap) {
        return orderedBidiMap instanceof Unmodifiable ? orderedBidiMap : new UnmodifiableOrderedBidiMap(orderedBidiMap);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableEntrySet.unmodifiableEntrySet(super.entrySet());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableOrderedBidiMap<>(mo12722decorated().mo12689inverseBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(super.keySet());
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.BidiMap
    /* renamed from: removeValue */
    public K mo12669removeValue(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator, org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap  reason: collision with other method in class */
    public OrderedBidiMap<V, K> mo12689inverseBidiMap() {
        return inverseOrderedBidiMap();
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator, org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return UnmodifiableOrderedMapIterator.unmodifiableOrderedMapIterator(mo12722decorated().mo12767mapIterator());
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values  reason: collision with other method in class */
    public Set<V> mo12691values() {
        return UnmodifiableSet.unmodifiableSet(super.mo12691values());
    }
}

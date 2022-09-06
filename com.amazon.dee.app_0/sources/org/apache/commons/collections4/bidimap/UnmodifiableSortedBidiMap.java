package org.apache.commons.collections4.bidimap;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableOrderedMapIterator;
import org.apache.commons.collections4.map.UnmodifiableEntrySet;
import org.apache.commons.collections4.map.UnmodifiableSortedMap;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public final class UnmodifiableSortedBidiMap<K, V> extends AbstractSortedBidiMapDecorator<K, V> implements Unmodifiable {
    private UnmodifiableSortedBidiMap<V, K> inverse;

    private UnmodifiableSortedBidiMap(SortedBidiMap<K, ? extends V> sortedBidiMap) {
        super(sortedBidiMap);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> SortedBidiMap<K, V> unmodifiableSortedBidiMap(SortedBidiMap<K, ? extends V> sortedBidiMap) {
        return sortedBidiMap instanceof Unmodifiable ? sortedBidiMap : new UnmodifiableSortedBidiMap(sortedBidiMap);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableEntrySet.unmodifiableEntrySet(super.entrySet());
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractSortedBidiMapDecorator, java.util.SortedMap
    public SortedMap<K, V> headMap(K k) {
        return UnmodifiableSortedMap.unmodifiableSortedMap(mo12722decorated().headMap(k));
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

    @Override // org.apache.commons.collections4.bidimap.AbstractSortedBidiMapDecorator, java.util.SortedMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return UnmodifiableSortedMap.unmodifiableSortedMap(mo12722decorated().subMap(k, k2));
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractSortedBidiMapDecorator, java.util.SortedMap
    public SortedMap<K, V> tailMap(K k) {
        return UnmodifiableSortedMap.unmodifiableSortedMap(mo12722decorated().tailMap(k));
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

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.bidimap.AbstractSortedBidiMapDecorator, org.apache.commons.collections4.bidimap.AbstractOrderedBidiMapDecorator, org.apache.commons.collections4.bidimap.AbstractBidiMapDecorator, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    public SortedBidiMap<V, K> mo12689inverseBidiMap() {
        if (this.inverse == null) {
            this.inverse = new UnmodifiableSortedBidiMap<>(mo12722decorated().mo12689inverseBidiMap());
            this.inverse.inverse = this;
        }
        return this.inverse;
    }
}

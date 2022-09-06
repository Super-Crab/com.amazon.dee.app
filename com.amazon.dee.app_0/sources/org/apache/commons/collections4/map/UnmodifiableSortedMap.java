package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public final class UnmodifiableSortedMap<K, V> extends AbstractSortedMapDecorator<K, V> implements Unmodifiable, Serializable {
    private static final long serialVersionUID = 5805344239827376360L;

    private UnmodifiableSortedMap(SortedMap<K, ? extends V> sortedMap) {
        super(sortedMap);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <K, V> SortedMap<K, V> unmodifiableSortedMap(SortedMap<K, ? extends V> sortedMap) {
        return sortedMap instanceof Unmodifiable ? sortedMap : new UnmodifiableSortedMap(sortedMap);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public Comparator<? super K> comparator() {
        return mo12722decorated().comparator();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableEntrySet.unmodifiableEntrySet(super.entrySet());
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap, org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        return mo12722decorated().firstKey();
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public SortedMap<K, V> headMap(K k) {
        return new UnmodifiableSortedMap(mo12722decorated().headMap(k));
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(super.keySet());
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap, org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        return mo12722decorated().lastKey();
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

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return new UnmodifiableSortedMap(mo12722decorated().subMap(k, k2));
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public SortedMap<K, V> tailMap(K k) {
        return new UnmodifiableSortedMap(mo12722decorated().tailMap(k));
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        return UnmodifiableCollection.unmodifiableCollection(super.mo12691values());
    }
}

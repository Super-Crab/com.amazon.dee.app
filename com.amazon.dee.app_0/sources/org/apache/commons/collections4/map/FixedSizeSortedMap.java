package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public class FixedSizeSortedMap<K, V> extends AbstractSortedMapDecorator<K, V> implements BoundedMap<K, V>, Serializable {
    private static final long serialVersionUID = 3126019624511683653L;

    protected FixedSizeSortedMap(SortedMap<K, V> sortedMap) {
        super(sortedMap);
    }

    public static <K, V> FixedSizeSortedMap<K, V> fixedSizeSortedMap(SortedMap<K, V> sortedMap) {
        return new FixedSizeSortedMap<>(sortedMap);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        throw new UnsupportedOperationException("Map is fixed size");
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return UnmodifiableSet.unmodifiableSet(this.map.entrySet());
    }

    protected SortedMap<K, V> getSortedMap() {
        return (SortedMap) this.map;
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public SortedMap<K, V> headMap(K k) {
        return new FixedSizeSortedMap(getSortedMap().headMap(k));
    }

    @Override // org.apache.commons.collections4.BoundedMap
    public boolean isFull() {
        return true;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return UnmodifiableSet.unmodifiableSet(this.map.keySet());
    }

    @Override // org.apache.commons.collections4.BoundedMap
    public int maxSize() {
        return size();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        if (this.map.containsKey(k)) {
            return this.map.put(k, v);
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        if (!CollectionUtils.isSubCollection(map.keySet(), keySet())) {
            this.map.putAll(map);
            return;
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size");
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        throw new UnsupportedOperationException("Map is fixed size");
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return new FixedSizeSortedMap(getSortedMap().subMap(k, k2));
    }

    @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
    public SortedMap<K, V> tailMap(K k) {
        return new FixedSizeSortedMap(getSortedMap().tailMap(k));
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        return UnmodifiableCollection.unmodifiableCollection(this.map.values());
    }
}

package org.apache.commons.collections4.set;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes4.dex */
public final class MapBackedSet<E, V> implements Set<E>, Serializable {
    private static final long serialVersionUID = 6723912213766056587L;
    private final V dummyValue;
    private final Map<E, ? super V> map;

    private MapBackedSet(Map<E, ? super V> map, V v) {
        if (map != null) {
            this.map = map;
            this.dummyValue = v;
            return;
        }
        throw new NullPointerException("The map must not be null");
    }

    public static <E, V> MapBackedSet<E, V> mapBackedSet(Map<E, ? super V> map) {
        return mapBackedSet(map, null);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E e) {
        int size = this.map.size();
        this.map.put(e, (V) this.dummyValue);
        return this.map.size() != size;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        int size = this.map.size();
        for (E e : collection) {
            this.map.put(e, (V) this.dummyValue);
        }
        return this.map.size() != size;
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.map.keySet().containsAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        return this.map.keySet().equals(obj);
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        return this.map.keySet().hashCode();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return this.map.keySet().iterator();
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        int size = this.map.size();
        this.map.remove(obj);
        return this.map.size() != size;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.map.keySet().removeAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.map.keySet().retainAll(collection);
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        return this.map.size();
    }

    @Override // java.util.Set, java.util.Collection
    public Object[] toArray() {
        return this.map.keySet().toArray();
    }

    public static <E, V> MapBackedSet<E, V> mapBackedSet(Map<E, ? super V> map, V v) {
        return new MapBackedSet<>(map, v);
    }

    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.map.keySet().toArray(tArr);
    }
}

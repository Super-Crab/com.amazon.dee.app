package org.apache.commons.collections4.map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.IterableSortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;
/* loaded from: classes4.dex */
public abstract class AbstractSortedMapDecorator<K, V> extends AbstractMapDecorator<K, V> implements IterableSortedMap<K, V> {

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class SortedMapIterator<K, V> extends EntrySetToMapIteratorAdapter<K, V> implements OrderedMapIterator<K, V> {
        protected SortedMapIterator(Set<Map.Entry<K, V>> set) {
            super(set);
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return ((ListIterator) this.iterator).hasPrevious();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            this.entry = (Map.Entry) ((ListIterator) this.iterator).previous();
            return mo12681getKey();
        }

        @Override // org.apache.commons.collections4.map.EntrySetToMapIteratorAdapter, org.apache.commons.collections4.ResettableIterator
        public synchronized void reset() {
            super.reset();
            this.iterator = new ListIteratorWrapper(this.iterator);
        }
    }

    protected AbstractSortedMapDecorator() {
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return mo12722decorated().comparator();
    }

    @Override // java.util.SortedMap, org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        return mo12722decorated().firstKey();
    }

    public SortedMap<K, V> headMap(K k) {
        return mo12722decorated().headMap(k);
    }

    @Override // java.util.SortedMap, org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        return mo12722decorated().lastKey();
    }

    public K nextKey(K k) {
        Iterator<K> it2 = tailMap(k).keySet().iterator();
        it2.next();
        if (it2.hasNext()) {
            return it2.next();
        }
        return null;
    }

    public K previousKey(K k) {
        SortedMap<K, V> headMap = headMap(k);
        if (headMap.isEmpty()) {
            return null;
        }
        return headMap.lastKey();
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return mo12722decorated().subMap(k, k2);
    }

    public SortedMap<K, V> tailMap(K k) {
        return mo12722decorated().tailMap(k);
    }

    public AbstractSortedMapDecorator(SortedMap<K, V> sortedMap) {
        super(sortedMap);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractMapDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public SortedMap<K, V> mo12722decorated() {
        return (SortedMap) super.mo12722decorated();
    }

    @Override // org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return new SortedMapIterator(entrySet());
    }
}

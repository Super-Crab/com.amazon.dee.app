package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedMap;
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class ForwardingNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V> {

    /* JADX INFO: Access modifiers changed from: protected */
    @Beta
    /* loaded from: classes3.dex */
    public class StandardDescendingMap extends Maps.DescendingMap<K, V> {
        public StandardDescendingMap() {
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        protected Iterator<Map.Entry<K, V>> entryIterator() {
            return new Iterator<Map.Entry<K, V>>() { // from class: com.google.common.collect.ForwardingNavigableMap.StandardDescendingMap.1
                private Map.Entry<K, V> nextOrNull;
                private Map.Entry<K, V> toRemove = null;

                {
                    this.nextOrNull = StandardDescendingMap.this.forward().lastEntry();
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return this.nextOrNull != null;
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(this.toRemove != null);
                    StandardDescendingMap.this.forward().remove(this.toRemove.getKey());
                    this.toRemove = null;
                }

                @Override // java.util.Iterator
                public Map.Entry<K, V> next() {
                    if (hasNext()) {
                        try {
                            return this.nextOrNull;
                        } finally {
                            this.toRemove = this.nextOrNull;
                            this.nextOrNull = StandardDescendingMap.this.forward().lowerEntry(this.nextOrNull.getKey());
                        }
                    }
                    throw new NoSuchElementException();
                }
            };
        }

        @Override // com.google.common.collect.Maps.DescendingMap
        NavigableMap<K, V> forward() {
            return ForwardingNavigableMap.this;
        }
    }

    @Beta
    /* loaded from: classes3.dex */
    protected class StandardNavigableKeySet extends Maps.NavigableKeySet<K, V> {
        public StandardNavigableKeySet(ForwardingNavigableMap forwardingNavigableMap) {
            super(forwardingNavigableMap);
        }
    }

    protected ForwardingNavigableMap() {
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> ceilingEntry(K k) {
        return mo8280delegate().ceilingEntry(k);
    }

    @Override // java.util.NavigableMap
    public K ceilingKey(K k) {
        return mo8280delegate().ceilingKey(k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedMap, com.google.common.collect.ForwardingMap, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract NavigableMap<K, V> mo8280delegate();

    @Override // java.util.NavigableMap
    public NavigableSet<K> descendingKeySet() {
        return mo8280delegate().descendingKeySet();
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> descendingMap() {
        return mo8280delegate().descendingMap();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> firstEntry() {
        return mo8280delegate().firstEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> floorEntry(K k) {
        return mo8280delegate().floorEntry(k);
    }

    @Override // java.util.NavigableMap
    public K floorKey(K k) {
        return mo8280delegate().floorKey(k);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> headMap(K k, boolean z) {
        return mo8280delegate().headMap(k, z);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> higherEntry(K k) {
        return mo8280delegate().higherEntry(k);
    }

    @Override // java.util.NavigableMap
    public K higherKey(K k) {
        return mo8280delegate().higherKey(k);
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lastEntry() {
        return mo8280delegate().lastEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> lowerEntry(K k) {
        return mo8280delegate().lowerEntry(k);
    }

    @Override // java.util.NavigableMap
    public K lowerKey(K k) {
        return mo8280delegate().lowerKey(k);
    }

    @Override // java.util.NavigableMap
    public NavigableSet<K> navigableKeySet() {
        return mo8280delegate().navigableKeySet();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollFirstEntry() {
        return mo8280delegate().pollFirstEntry();
    }

    @Override // java.util.NavigableMap
    public Map.Entry<K, V> pollLastEntry() {
        return mo8280delegate().pollLastEntry();
    }

    protected Map.Entry<K, V> standardCeilingEntry(K k) {
        return tailMap(k, true).firstEntry();
    }

    protected K standardCeilingKey(K k) {
        return (K) Maps.keyOrNull(ceilingEntry(k));
    }

    @Beta
    protected NavigableSet<K> standardDescendingKeySet() {
        return descendingMap().navigableKeySet();
    }

    protected Map.Entry<K, V> standardFirstEntry() {
        return (Map.Entry) Iterables.getFirst(entrySet(), null);
    }

    protected K standardFirstKey() {
        Map.Entry<K, V> firstEntry = firstEntry();
        if (firstEntry != null) {
            return firstEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    protected Map.Entry<K, V> standardFloorEntry(K k) {
        return headMap(k, true).lastEntry();
    }

    protected K standardFloorKey(K k) {
        return (K) Maps.keyOrNull(floorEntry(k));
    }

    protected SortedMap<K, V> standardHeadMap(K k) {
        return headMap(k, false);
    }

    protected Map.Entry<K, V> standardHigherEntry(K k) {
        return tailMap(k, false).firstEntry();
    }

    protected K standardHigherKey(K k) {
        return (K) Maps.keyOrNull(higherEntry(k));
    }

    protected Map.Entry<K, V> standardLastEntry() {
        return (Map.Entry) Iterables.getFirst(descendingMap().entrySet(), null);
    }

    protected K standardLastKey() {
        Map.Entry<K, V> lastEntry = lastEntry();
        if (lastEntry != null) {
            return lastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    protected Map.Entry<K, V> standardLowerEntry(K k) {
        return headMap(k, false).lastEntry();
    }

    protected K standardLowerKey(K k) {
        return (K) Maps.keyOrNull(lowerEntry(k));
    }

    protected Map.Entry<K, V> standardPollFirstEntry() {
        return (Map.Entry) Iterators.pollNext(entrySet().iterator());
    }

    protected Map.Entry<K, V> standardPollLastEntry() {
        return (Map.Entry) Iterators.pollNext(descendingMap().entrySet().iterator());
    }

    @Override // com.google.common.collect.ForwardingSortedMap
    protected SortedMap<K, V> standardSubMap(K k, K k2) {
        return subMap(k, true, k2, false);
    }

    protected SortedMap<K, V> standardTailMap(K k) {
        return tailMap(k, true);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> subMap(K k, boolean z, K k2, boolean z2) {
        return mo8280delegate().subMap(k, z, k2, z2);
    }

    @Override // java.util.NavigableMap
    public NavigableMap<K, V> tailMap(K k, boolean z) {
        return mo8280delegate().tailMap(k, z);
    }
}

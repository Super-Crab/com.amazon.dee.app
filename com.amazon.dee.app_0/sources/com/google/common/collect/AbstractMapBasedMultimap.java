package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.RandomAccess;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class AbstractMapBasedMultimap<K, V> extends AbstractMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 2447537837011683357L;
    private transient Map<K, Collection<V>> map;
    private transient int totalSize;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> submap;

        /* loaded from: classes3.dex */
        class AsMapEntries extends Maps.EntrySet<K, Collection<V>> {
            AsMapEntries() {
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean contains(Object obj) {
                return Collections2.safeContains(AsMap.this.submap.entrySet(), obj);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                return new AsMapIterator();
            }

            @Override // com.google.common.collect.Maps.EntrySet
            Map<K, Collection<V>> map() {
                return AsMap.this;
            }

            @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMapBasedMultimap.this.removeValuesForKey(((Map.Entry) obj).getKey());
                return true;
            }
        }

        /* loaded from: classes3.dex */
        class AsMapIterator implements Iterator<Map.Entry<K, Collection<V>>> {
            @NullableDecl
            Collection<V> collection;
            final Iterator<Map.Entry<K, Collection<V>>> delegateIterator;

            AsMapIterator() {
                this.delegateIterator = AsMap.this.submap.entrySet().iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public void remove() {
                CollectPreconditions.checkRemove(this.collection != null);
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, this.collection.size());
                this.collection.clear();
                this.collection = null;
            }

            @Override // java.util.Iterator
            public Map.Entry<K, Collection<V>> next() {
                Map.Entry<K, Collection<V>> next = this.delegateIterator.next();
                this.collection = next.getValue();
                return AsMap.this.wrapEntry(next);
            }
        }

        AsMap(Map<K, Collection<V>> map) {
            this.submap = map;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            if (this.submap == AbstractMapBasedMultimap.this.map) {
                AbstractMapBasedMultimap.this.clear();
            } else {
                Iterators.clear(new AsMapIterator());
            }
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(Object obj) {
            return Maps.safeContainsKey(this.submap, obj);
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        protected Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new AsMapEntries();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean equals(@NullableDecl Object obj) {
            return this == obj || this.submap.equals(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int hashCode() {
            return this.submap.hashCode();
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        /* renamed from: keySet */
        public Set<K> mo8042keySet() {
            return AbstractMapBasedMultimap.this.mo8105keySet();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public int size() {
            return this.submap.size();
        }

        @Override // java.util.AbstractMap
        public String toString() {
            return this.submap.toString();
        }

        Map.Entry<K, Collection<V>> wrapEntry(Map.Entry<K, Collection<V>> entry) {
            K key = entry.getKey();
            return Maps.immutableEntry(key, AbstractMapBasedMultimap.this.wrapCollection(key, entry.getValue()));
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(Object obj) {
            Collection<V> collection = (Collection) Maps.safeGet(this.submap, obj);
            if (collection == null) {
                return null;
            }
            return AbstractMapBasedMultimap.this.wrapCollection(obj, collection);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(Object obj) {
            Collection<V> remove = this.submap.remove(obj);
            if (remove == null) {
                return null;
            }
            Collection<V> mo8102createCollection = AbstractMapBasedMultimap.this.mo8102createCollection();
            mo8102createCollection.addAll(remove);
            AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, remove.size());
            remove.clear();
            return mo8102createCollection;
        }
    }

    /* loaded from: classes3.dex */
    private abstract class Itr<T> implements Iterator<T> {
        final Iterator<Map.Entry<K, Collection<V>>> keyIterator;
        @NullableDecl
        K key = null;
        @NullableDecl
        Collection<V> collection = null;
        Iterator<V> valueIterator = Iterators.emptyModifiableIterator();

        Itr() {
            this.keyIterator = (Iterator<Map.Entry<K, V>>) AbstractMapBasedMultimap.this.map.entrySet().iterator();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.keyIterator.hasNext() || this.valueIterator.hasNext();
        }

        @Override // java.util.Iterator
        public T next() {
            if (!this.valueIterator.hasNext()) {
                Map.Entry<K, Collection<V>> next = this.keyIterator.next();
                this.key = next.getKey();
                this.collection = next.getValue();
                this.valueIterator = this.collection.iterator();
            }
            return mo7546output(this.key, this.valueIterator.next());
        }

        /* renamed from: output */
        abstract T mo7546output(K k, V v);

        @Override // java.util.Iterator
        public void remove() {
            this.valueIterator.remove();
            if (this.collection.isEmpty()) {
                this.keyIterator.remove();
            }
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
        }
    }

    /* loaded from: classes3.dex */
    private class KeySet extends Maps.KeySet<K, Collection<V>> {
        KeySet(Map<K, Collection<V>> map) {
            super(map);
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            Iterators.clear(iterator());
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return mo7922map().keySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return this == obj || mo7922map().keySet().equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return mo7922map().keySet().hashCode();
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            final Iterator<Map.Entry<K, Collection<V>>> it2 = mo7922map().entrySet().iterator();
            return new Iterator<K>() { // from class: com.google.common.collect.AbstractMapBasedMultimap.KeySet.1
                @NullableDecl
                Map.Entry<K, Collection<V>> entry;

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it2.hasNext();
                }

                @Override // java.util.Iterator
                public K next() {
                    this.entry = (Map.Entry) it2.next();
                    return this.entry.getKey();
                }

                @Override // java.util.Iterator
                public void remove() {
                    CollectPreconditions.checkRemove(this.entry != null);
                    Collection<V> value = this.entry.getValue();
                    it2.remove();
                    AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, value.size());
                    value.clear();
                    this.entry = null;
                }
            };
        }

        @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            int i;
            Collection<V> remove = mo7922map().remove(obj);
            if (remove != null) {
                i = remove.size();
                remove.clear();
                AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, i);
            } else {
                i = 0;
            }
            return i > 0;
        }
    }

    /* loaded from: classes3.dex */
    class NavigableAsMap extends AbstractMapBasedMultimap<K, V>.SortedAsMap implements NavigableMap<K, Collection<V>> {
        NavigableAsMap(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> ceilingEntry(K k) {
            Map.Entry<K, Collection<V>> ceilingEntry = mo7553sortedMap().ceilingEntry(k);
            if (ceilingEntry == null) {
                return null;
            }
            return wrapEntry(ceilingEntry);
        }

        @Override // java.util.NavigableMap
        public K ceilingKey(K k) {
            return mo7553sortedMap().ceilingKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> descendingKeySet() {
            return descendingMap().navigableKeySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> descendingMap() {
            return new NavigableAsMap(mo7553sortedMap().descendingMap());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> firstEntry() {
            Map.Entry<K, Collection<V>> firstEntry = mo7553sortedMap().firstEntry();
            if (firstEntry == null) {
                return null;
            }
            return wrapEntry(firstEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> floorEntry(K k) {
            Map.Entry<K, Collection<V>> floorEntry = mo7553sortedMap().floorEntry(k);
            if (floorEntry == null) {
                return null;
            }
            return wrapEntry(floorEntry);
        }

        @Override // java.util.NavigableMap
        public K floorKey(K k) {
            return mo7553sortedMap().floorKey(k);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: headMap  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SortedMap mo7551headMap(Object obj) {
            return mo7551headMap((NavigableAsMap) obj);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> higherEntry(K k) {
            Map.Entry<K, Collection<V>> higherEntry = mo7553sortedMap().higherEntry(k);
            if (higherEntry == null) {
                return null;
            }
            return wrapEntry(higherEntry);
        }

        @Override // java.util.NavigableMap
        public K higherKey(K k) {
            return mo7553sortedMap().higherKey(k);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lastEntry() {
            Map.Entry<K, Collection<V>> lastEntry = mo7553sortedMap().lastEntry();
            if (lastEntry == null) {
                return null;
            }
            return wrapEntry(lastEntry);
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> lowerEntry(K k) {
            Map.Entry<K, Collection<V>> lowerEntry = mo7553sortedMap().lowerEntry(k);
            if (lowerEntry == null) {
                return null;
            }
            return wrapEntry(lowerEntry);
        }

        @Override // java.util.NavigableMap
        public K lowerKey(K k) {
            return mo7553sortedMap().lowerKey(k);
        }

        @Override // java.util.NavigableMap
        public NavigableSet<K> navigableKeySet() {
            return mo8042keySet();
        }

        Map.Entry<K, Collection<V>> pollAsMapEntry(Iterator<Map.Entry<K, Collection<V>>> it2) {
            if (!it2.hasNext()) {
                return null;
            }
            Map.Entry<K, Collection<V>> next = it2.next();
            Collection<V> mo8102createCollection = AbstractMapBasedMultimap.this.mo8102createCollection();
            mo8102createCollection.addAll(next.getValue());
            it2.remove();
            return Maps.immutableEntry(next.getKey(), AbstractMapBasedMultimap.this.mo7580unmodifiableCollectionSubclass(mo8102createCollection));
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollFirstEntry() {
            return pollAsMapEntry(entrySet().iterator());
        }

        @Override // java.util.NavigableMap
        public Map.Entry<K, Collection<V>> pollLastEntry() {
            return pollAsMapEntry(descendingMap().entrySet().iterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: tailMap  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SortedMap mo7555tailMap(Object obj) {
            return mo7555tailMap((NavigableAsMap) obj);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: headMap */
        public NavigableMap<K, Collection<V>> mo7551headMap(K k) {
            return headMap(k, false);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap
        /* renamed from: sortedMap */
        public NavigableMap<K, Collection<V>> mo7553sortedMap() {
            return (NavigableMap) super.mo7553sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: subMap */
        public NavigableMap<K, Collection<V>> mo7554subMap(K k, K k2) {
            return subMap(k, true, k2, false);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, java.util.SortedMap, java.util.NavigableMap
        /* renamed from: tailMap */
        public NavigableMap<K, Collection<V>> mo7555tailMap(K k) {
            return tailMap(k, true);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.Maps.ViewCachingAbstractMap
        /* renamed from: createKeySet */
        public NavigableSet<K> mo8041createKeySet() {
            return new NavigableKeySet(mo7553sortedMap());
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> headMap(K k, boolean z) {
            return new NavigableAsMap(mo7553sortedMap().headMap(k, z));
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedAsMap, com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        /* renamed from: keySet */
        public NavigableSet<K> mo8042keySet() {
            return (NavigableSet) super.mo8042keySet();
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> subMap(K k, boolean z, K k2, boolean z2) {
            return new NavigableAsMap(mo7553sortedMap().subMap(k, z, k2, z2));
        }

        @Override // java.util.NavigableMap
        public NavigableMap<K, Collection<V>> tailMap(K k, boolean z) {
            return new NavigableAsMap(mo7553sortedMap().tailMap(k, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class NavigableKeySet extends AbstractMapBasedMultimap<K, V>.SortedKeySet implements NavigableSet<K> {
        NavigableKeySet(NavigableMap<K, Collection<V>> navigableMap) {
            super(navigableMap);
        }

        @Override // java.util.NavigableSet
        public K ceiling(K k) {
            return mo7557sortedMap().ceilingKey(k);
        }

        @Override // java.util.NavigableSet
        public Iterator<K> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> descendingSet() {
            return new NavigableKeySet(mo7557sortedMap().descendingMap());
        }

        @Override // java.util.NavigableSet
        public K floor(K k) {
            return mo7557sortedMap().floorKey(k);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: headSet  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SortedSet mo7556headSet(Object obj) {
            return mo7556headSet((NavigableKeySet) obj);
        }

        @Override // java.util.NavigableSet
        public K higher(K k) {
            return mo7557sortedMap().higherKey(k);
        }

        @Override // java.util.NavigableSet
        public K lower(K k) {
            return mo7557sortedMap().lowerKey(k);
        }

        @Override // java.util.NavigableSet
        public K pollFirst() {
            return (K) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public K pollLast() {
            return (K) Iterators.pollNext(descendingIterator());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: tailSet  reason: collision with other method in class */
        public /* bridge */ /* synthetic */ SortedSet mo7559tailSet(Object obj) {
            return mo7559tailSet((NavigableKeySet) obj);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: headSet */
        public NavigableSet<K> mo7556headSet(K k) {
            return headSet(k, false);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet
        /* renamed from: sortedMap */
        public NavigableMap<K, Collection<V>> mo7557sortedMap() {
            return (NavigableMap) super.mo7557sortedMap();
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: subSet */
        public NavigableSet<K> mo7558subSet(K k, K k2) {
            return subSet(k, true, k2, false);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.SortedKeySet, java.util.SortedSet, java.util.NavigableSet
        /* renamed from: tailSet */
        public NavigableSet<K> mo7559tailSet(K k) {
            return tailSet(k, true);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> headSet(K k, boolean z) {
            return new NavigableKeySet(mo7557sortedMap().headMap(k, z));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> subSet(K k, boolean z, K k2, boolean z2) {
            return new NavigableKeySet(mo7557sortedMap().subMap(k, z, k2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<K> tailSet(K k, boolean z) {
            return new NavigableKeySet(mo7557sortedMap().tailMap(k, z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class RandomAccessWrappedList extends AbstractMapBasedMultimap<K, V>.WrappedList implements RandomAccess {
        RandomAccessWrappedList(@NullableDecl AbstractMapBasedMultimap abstractMapBasedMultimap, K k, @NullableDecl List<V> list, AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class SortedAsMap extends AbstractMapBasedMultimap<K, V>.AsMap implements SortedMap<K, Collection<V>> {
        @NullableDecl
        SortedSet<K> sortedKeySet;

        SortedAsMap(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @Override // java.util.SortedMap
        public Comparator<? super K> comparator() {
            return mo7553sortedMap().comparator();
        }

        @Override // java.util.SortedMap
        public K firstKey() {
            return mo7553sortedMap().firstKey();
        }

        /* renamed from: headMap */
        public SortedMap<K, Collection<V>> mo7551headMap(K k) {
            return new SortedAsMap(mo7553sortedMap().headMap(k));
        }

        @Override // java.util.SortedMap
        public K lastKey() {
            return mo7553sortedMap().lastKey();
        }

        /* renamed from: sortedMap */
        SortedMap<K, Collection<V>> mo7553sortedMap() {
            return (SortedMap) this.submap;
        }

        /* renamed from: subMap */
        public SortedMap<K, Collection<V>> mo7554subMap(K k, K k2) {
            return new SortedAsMap(mo7553sortedMap().subMap(k, k2));
        }

        /* renamed from: tailMap */
        public SortedMap<K, Collection<V>> mo7555tailMap(K k) {
            return new SortedAsMap(mo7553sortedMap().tailMap(k));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        /* renamed from: createKeySet  reason: collision with other method in class */
        public SortedSet<K> mo8041createKeySet() {
            return new SortedKeySet(mo7553sortedMap());
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.AsMap, com.google.common.collect.Maps.ViewCachingAbstractMap, java.util.AbstractMap, java.util.Map
        /* renamed from: keySet  reason: collision with other method in class */
        public SortedSet<K> mo8042keySet() {
            SortedSet<K> sortedSet = this.sortedKeySet;
            if (sortedSet == null) {
                SortedSet<K> mo8041createKeySet = mo8041createKeySet();
                this.sortedKeySet = mo8041createKeySet;
                return mo8041createKeySet;
            }
            return sortedSet;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class SortedKeySet extends AbstractMapBasedMultimap<K, V>.KeySet implements SortedSet<K> {
        SortedKeySet(SortedMap<K, Collection<V>> sortedMap) {
            super(sortedMap);
        }

        @Override // java.util.SortedSet
        public Comparator<? super K> comparator() {
            return mo7557sortedMap().comparator();
        }

        @Override // java.util.SortedSet
        public K first() {
            return mo7557sortedMap().firstKey();
        }

        /* renamed from: headSet */
        public SortedSet<K> mo7556headSet(K k) {
            return new SortedKeySet(mo7557sortedMap().headMap(k));
        }

        @Override // java.util.SortedSet
        public K last() {
            return mo7557sortedMap().lastKey();
        }

        /* renamed from: sortedMap */
        SortedMap<K, Collection<V>> mo7557sortedMap() {
            return (SortedMap) super.mo7922map();
        }

        /* renamed from: subSet */
        public SortedSet<K> mo7558subSet(K k, K k2) {
            return new SortedKeySet(mo7557sortedMap().subMap(k, k2));
        }

        /* renamed from: tailSet */
        public SortedSet<K> mo7559tailSet(K k) {
            return new SortedKeySet(mo7557sortedMap().tailMap(k));
        }
    }

    /* loaded from: classes3.dex */
    class WrappedNavigableSet extends AbstractMapBasedMultimap<K, V>.WrappedSortedSet implements NavigableSet<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedNavigableSet(@NullableDecl K k, NavigableSet<V> navigableSet, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, navigableSet, wrappedCollection);
        }

        private NavigableSet<V> wrap(NavigableSet<V> navigableSet) {
            return new WrappedNavigableSet(this.key, navigableSet, getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.NavigableSet
        public V ceiling(V v) {
            return mo7562getSortedSetDelegate().ceiling(v);
        }

        @Override // java.util.NavigableSet
        public Iterator<V> descendingIterator() {
            return new WrappedCollection.WrappedIterator(mo7562getSortedSetDelegate().descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> descendingSet() {
            return wrap(mo7562getSortedSetDelegate().descendingSet());
        }

        @Override // java.util.NavigableSet
        public V floor(V v) {
            return mo7562getSortedSetDelegate().floor(v);
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> headSet(V v, boolean z) {
            return wrap(mo7562getSortedSetDelegate().headSet(v, z));
        }

        @Override // java.util.NavigableSet
        public V higher(V v) {
            return mo7562getSortedSetDelegate().higher(v);
        }

        @Override // java.util.NavigableSet
        public V lower(V v) {
            return mo7562getSortedSetDelegate().lower(v);
        }

        @Override // java.util.NavigableSet
        public V pollFirst() {
            return (V) Iterators.pollNext(iterator());
        }

        @Override // java.util.NavigableSet
        public V pollLast() {
            return (V) Iterators.pollNext(descendingIterator());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> subSet(V v, boolean z, V v2, boolean z2) {
            return wrap(mo7562getSortedSetDelegate().subSet(v, z, v2, z2));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<V> tailSet(V v, boolean z) {
            return wrap(mo7562getSortedSetDelegate().tailSet(v, z));
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedSortedSet
        /* renamed from: getSortedSetDelegate */
        public NavigableSet<V> mo7562getSortedSetDelegate() {
            return (NavigableSet) super.mo7562getSortedSetDelegate();
        }
    }

    /* loaded from: classes3.dex */
    class WrappedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements Set<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedSet(@NullableDecl K k, Set<V> set) {
            super(k, set, null);
        }

        @Override // com.google.common.collect.AbstractMapBasedMultimap.WrappedCollection, java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAllImpl = Sets.removeAllImpl((Set) this.delegate, collection);
            if (removeAllImpl) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                removeIfEmpty();
            }
            return removeAllImpl;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class WrappedSortedSet extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements SortedSet<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedSortedSet(@NullableDecl K k, SortedSet<V> sortedSet, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, sortedSet, wrappedCollection);
        }

        @Override // java.util.SortedSet
        public Comparator<? super V> comparator() {
            return mo7562getSortedSetDelegate().comparator();
        }

        @Override // java.util.SortedSet
        public V first() {
            refreshIfEmpty();
            return mo7562getSortedSetDelegate().first();
        }

        /* renamed from: getSortedSetDelegate */
        SortedSet<V> mo7562getSortedSetDelegate() {
            return (SortedSet) getDelegate();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> headSet(V v) {
            refreshIfEmpty();
            return new WrappedSortedSet(getKey(), mo7562getSortedSetDelegate().headSet(v), getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.SortedSet
        public V last() {
            refreshIfEmpty();
            return mo7562getSortedSetDelegate().last();
        }

        @Override // java.util.SortedSet
        public SortedSet<V> subSet(V v, V v2) {
            refreshIfEmpty();
            return new WrappedSortedSet(getKey(), mo7562getSortedSetDelegate().subSet(v, v2), getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.SortedSet
        public SortedSet<V> tailSet(V v) {
            refreshIfEmpty();
            return new WrappedSortedSet(getKey(), mo7562getSortedSetDelegate().tailSet(v), getAncestor() == null ? this : getAncestor());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapBasedMultimap(Map<K, Collection<V>> map) {
        Preconditions.checkArgument(map.isEmpty());
        this.map = map;
    }

    static /* synthetic */ int access$208(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i + 1;
        return i;
    }

    static /* synthetic */ int access$210(AbstractMapBasedMultimap abstractMapBasedMultimap) {
        int i = abstractMapBasedMultimap.totalSize;
        abstractMapBasedMultimap.totalSize = i - 1;
        return i;
    }

    static /* synthetic */ int access$212(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.totalSize + i;
        abstractMapBasedMultimap.totalSize = i2;
        return i2;
    }

    static /* synthetic */ int access$220(AbstractMapBasedMultimap abstractMapBasedMultimap, int i) {
        int i2 = abstractMapBasedMultimap.totalSize - i;
        abstractMapBasedMultimap.totalSize = i2;
        return i2;
    }

    private Collection<V> getOrCreateCollection(@NullableDecl K k) {
        Collection<V> collection = this.map.get(k);
        if (collection == null) {
            Collection<V> createCollection = createCollection(k);
            this.map.put(k, createCollection);
            return createCollection;
        }
        return collection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> Iterator<E> iteratorOrListIterator(Collection<E> collection) {
        if (collection instanceof List) {
            return ((List) collection).listIterator();
        }
        return collection.iterator();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeValuesForKey(Object obj) {
        Collection collection = (Collection) Maps.safeRemove(this.map, obj);
        if (collection != null) {
            int size = collection.size();
            collection.clear();
            this.totalSize -= size;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: backingMap */
    public Map<K, Collection<V>> mo7571backingMap() {
        return this.map;
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        for (Collection<V> collection : this.map.values()) {
            collection.clear();
        }
        this.map.clear();
        this.totalSize = 0;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Map<K, Collection<V>> createAsMap() {
        return new AsMap(this.map);
    }

    /* renamed from: createCollection */
    abstract Collection<V> mo8102createCollection();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Collection<V> createCollection(@NullableDecl K k) {
        return mo8102createCollection();
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createEntries */
    Collection<Map.Entry<K, V>> mo7870createEntries() {
        if (this instanceof SetMultimap) {
            return new AbstractMultimap.EntrySet(this);
        }
        return new AbstractMultimap.Entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    Set<K> createKeySet() {
        return new KeySet(this.map);
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createKeys */
    Multiset<K> mo7748createKeys() {
        return new Multimaps.Keys(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Map<K, Collection<V>> createMaybeNavigableAsMap() {
        Map<K, Collection<V>> map = this.map;
        if (map instanceof NavigableMap) {
            return new NavigableAsMap((NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new SortedAsMap((SortedMap) map);
        }
        return new AsMap(map);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<K> createMaybeNavigableKeySet() {
        Map<K, Collection<V>> map = this.map;
        if (map instanceof NavigableMap) {
            return new NavigableKeySet((NavigableMap) map);
        }
        if (map instanceof SortedMap) {
            return new SortedKeySet((SortedMap) map);
        }
        return new KeySet(map);
    }

    /* renamed from: createUnmodifiableEmptyCollection */
    Collection<V> mo7576createUnmodifiableEmptyCollection() {
        return (Collection<V>) mo7580unmodifiableCollectionSubclass(mo8102createCollection());
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createValues */
    Collection<V> mo7871createValues() {
        return new AbstractMultimap.Values();
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    /* renamed from: entries */
    public Collection<Map.Entry<K, V>> mo8077entries() {
        return super.mo8077entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: entryIterator */
    Iterator<Map.Entry<K, V>> mo7751entryIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<Map.Entry<K, V>>(this) { // from class: com.google.common.collect.AbstractMapBasedMultimap.2
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            /* renamed from: output */
            /* bridge */ /* synthetic */ Object mo7546output(Object obj, Object obj2) {
                return mo7546output((AnonymousClass2) obj, obj2);
            }

            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            /* renamed from: output  reason: collision with other method in class */
            Map.Entry<K, V> mo7546output(K k, V v) {
                return Maps.immutableEntry(k, v);
            }
        };
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public Collection<V> mo8104get(@NullableDecl K k) {
        Collection<V> collection = this.map.get(k);
        if (collection == null) {
            collection = createCollection(k);
        }
        return wrapCollection(k, collection);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public boolean put(@NullableDecl K k, @NullableDecl V v) {
        Collection<V> collection = this.map.get(k);
        if (collection == null) {
            Collection<V> createCollection = createCollection(k);
            if (createCollection.add(v)) {
                this.totalSize++;
                this.map.put(k, createCollection);
                return true;
            }
            throw new AssertionError("New Collection violated the Collection spec");
        } else if (!collection.add(v)) {
            return false;
        } else {
            this.totalSize++;
            return true;
        }
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: removeAll */
    public Collection<V> mo8087removeAll(@NullableDecl Object obj) {
        Collection<V> remove = this.map.remove(obj);
        if (remove == null) {
            return mo7576createUnmodifiableEmptyCollection();
        }
        Collection mo8102createCollection = mo8102createCollection();
        mo8102createCollection.addAll(remove);
        this.totalSize -= remove.size();
        remove.clear();
        return (Collection<V>) mo7580unmodifiableCollectionSubclass(mo8102createCollection);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: replaceValues */
    public Collection<V> mo8088replaceValues(@NullableDecl K k, Iterable<? extends V> iterable) {
        Iterator<? extends V> it2 = iterable.iterator();
        if (!it2.hasNext()) {
            return mo8087removeAll(k);
        }
        Collection<V> orCreateCollection = getOrCreateCollection(k);
        Collection mo8102createCollection = mo8102createCollection();
        mo8102createCollection.addAll(orCreateCollection);
        this.totalSize -= orCreateCollection.size();
        orCreateCollection.clear();
        while (it2.hasNext()) {
            if (orCreateCollection.add(it2.next())) {
                this.totalSize++;
            }
        }
        return (Collection<V>) mo7580unmodifiableCollectionSubclass(mo8102createCollection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setMap(Map<K, Collection<V>> map) {
        this.map = map;
        this.totalSize = 0;
        for (Collection<V> collection : map.values()) {
            Preconditions.checkArgument(!collection.isEmpty());
            this.totalSize = collection.size() + this.totalSize;
        }
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return this.totalSize;
    }

    /* renamed from: unmodifiableCollectionSubclass */
    <E> Collection<E> mo7580unmodifiableCollectionSubclass(Collection<E> collection) {
        return Collections.unmodifiableCollection(collection);
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: valueIterator */
    Iterator<V> mo7757valueIterator() {
        return new AbstractMapBasedMultimap<K, V>.Itr<V>(this) { // from class: com.google.common.collect.AbstractMapBasedMultimap.1
            @Override // com.google.common.collect.AbstractMapBasedMultimap.Itr
            /* renamed from: output */
            V mo7546output(K k, V v) {
                return v;
            }
        };
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    /* renamed from: values */
    public Collection<V> mo7876values() {
        return super.mo7876values();
    }

    Collection<V> wrapCollection(@NullableDecl K k, Collection<V> collection) {
        return new WrappedCollection(k, collection, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final List<V> wrapList(@NullableDecl K k, List<V> list, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
        if (list instanceof RandomAccess) {
            return new RandomAccessWrappedList(this, k, list, wrappedCollection);
        }
        return new WrappedList(k, list, wrappedCollection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class WrappedCollection extends AbstractCollection<V> {
        @NullableDecl
        final AbstractMapBasedMultimap<K, V>.WrappedCollection ancestor;
        @NullableDecl
        final Collection<V> ancestorDelegate;
        Collection<V> delegate;
        @NullableDecl
        final K key;

        /* JADX INFO: Access modifiers changed from: package-private */
        public WrappedCollection(@NullableDecl K k, Collection<V> collection, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            this.key = k;
            this.delegate = collection;
            this.ancestor = wrappedCollection;
            this.ancestorDelegate = wrappedCollection == null ? null : wrappedCollection.getDelegate();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean add(V v) {
            refreshIfEmpty();
            boolean isEmpty = this.delegate.isEmpty();
            boolean add = this.delegate.add(v);
            if (add) {
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    addToMap();
                }
            }
            return add;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = this.delegate.addAll(collection);
            if (addAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                if (size == 0) {
                    addToMap();
                }
            }
            return addAll;
        }

        void addToMap() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection == null) {
                AbstractMapBasedMultimap.this.map.put(this.key, this.delegate);
            } else {
                wrappedCollection.addToMap();
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            int size = size();
            if (size == 0) {
                return;
            }
            this.delegate.clear();
            AbstractMapBasedMultimap.access$220(AbstractMapBasedMultimap.this, size);
            removeIfEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            refreshIfEmpty();
            return this.delegate.contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean containsAll(Collection<?> collection) {
            refreshIfEmpty();
            return this.delegate.containsAll(collection);
        }

        @Override // java.util.Collection
        public boolean equals(@NullableDecl Object obj) {
            if (obj == this) {
                return true;
            }
            refreshIfEmpty();
            return this.delegate.equals(obj);
        }

        AbstractMapBasedMultimap<K, V>.WrappedCollection getAncestor() {
            return this.ancestor;
        }

        Collection<V> getDelegate() {
            return this.delegate;
        }

        K getKey() {
            return this.key;
        }

        @Override // java.util.Collection
        public int hashCode() {
            refreshIfEmpty();
            return this.delegate.hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            refreshIfEmpty();
            return new WrappedIterator();
        }

        void refreshIfEmpty() {
            Collection<V> collection;
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.refreshIfEmpty();
                if (this.ancestor.getDelegate() != this.ancestorDelegate) {
                    throw new ConcurrentModificationException();
                }
            } else if (!this.delegate.isEmpty() || (collection = (Collection) AbstractMapBasedMultimap.this.map.get(this.key)) == null) {
            } else {
                this.delegate = collection;
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean remove(Object obj) {
            refreshIfEmpty();
            boolean remove = this.delegate.remove(obj);
            if (remove) {
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                removeIfEmpty();
            }
            return remove;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean removeAll = this.delegate.removeAll(collection);
            if (removeAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                removeIfEmpty();
            }
            return removeAll;
        }

        void removeIfEmpty() {
            AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection = this.ancestor;
            if (wrappedCollection != null) {
                wrappedCollection.removeIfEmpty();
            } else if (!this.delegate.isEmpty()) {
            } else {
                AbstractMapBasedMultimap.this.map.remove(this.key);
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean retainAll(Collection<?> collection) {
            Preconditions.checkNotNull(collection);
            int size = size();
            boolean retainAll = this.delegate.retainAll(collection);
            if (retainAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, this.delegate.size() - size);
                removeIfEmpty();
            }
            return retainAll;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            refreshIfEmpty();
            return this.delegate.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            refreshIfEmpty();
            return this.delegate.toString();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes3.dex */
        public class WrappedIterator implements Iterator<V> {
            final Iterator<V> delegateIterator;
            final Collection<V> originalDelegate;

            WrappedIterator() {
                this.originalDelegate = WrappedCollection.this.delegate;
                this.delegateIterator = AbstractMapBasedMultimap.iteratorOrListIterator(WrappedCollection.this.delegate);
            }

            Iterator<V> getDelegateIterator() {
                validateIterator();
                return this.delegateIterator;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                validateIterator();
                return this.delegateIterator.hasNext();
            }

            @Override // java.util.Iterator
            public V next() {
                validateIterator();
                return this.delegateIterator.next();
            }

            @Override // java.util.Iterator
            public void remove() {
                this.delegateIterator.remove();
                AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
                WrappedCollection.this.removeIfEmpty();
            }

            void validateIterator() {
                WrappedCollection.this.refreshIfEmpty();
                if (WrappedCollection.this.delegate == this.originalDelegate) {
                    return;
                }
                throw new ConcurrentModificationException();
            }

            WrappedIterator(Iterator<V> it2) {
                this.originalDelegate = WrappedCollection.this.delegate;
                this.delegateIterator = it2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class WrappedList extends AbstractMapBasedMultimap<K, V>.WrappedCollection implements List<V> {

        /* loaded from: classes3.dex */
        private class WrappedListIterator extends AbstractMapBasedMultimap<K, V>.WrappedCollection.WrappedIterator implements ListIterator<V> {
            WrappedListIterator() {
                super();
            }

            private ListIterator<V> getDelegateListIterator() {
                return (ListIterator) getDelegateIterator();
            }

            @Override // java.util.ListIterator
            public void add(V v) {
                boolean isEmpty = WrappedList.this.isEmpty();
                getDelegateListIterator().add(v);
                AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
                if (isEmpty) {
                    WrappedList.this.addToMap();
                }
            }

            @Override // java.util.ListIterator
            public boolean hasPrevious() {
                return getDelegateListIterator().hasPrevious();
            }

            @Override // java.util.ListIterator
            public int nextIndex() {
                return getDelegateListIterator().nextIndex();
            }

            @Override // java.util.ListIterator
            public V previous() {
                return getDelegateListIterator().previous();
            }

            @Override // java.util.ListIterator
            public int previousIndex() {
                return getDelegateListIterator().previousIndex();
            }

            @Override // java.util.ListIterator
            public void set(V v) {
                getDelegateListIterator().set(v);
            }

            public WrappedListIterator(int i) {
                super(WrappedList.this.getListDelegate().listIterator(i));
            }
        }

        WrappedList(@NullableDecl K k, List<V> list, @NullableDecl AbstractMapBasedMultimap<K, V>.WrappedCollection wrappedCollection) {
            super(k, list, wrappedCollection);
        }

        @Override // java.util.List
        public void add(int i, V v) {
            refreshIfEmpty();
            boolean isEmpty = getDelegate().isEmpty();
            getListDelegate().add(i, v);
            AbstractMapBasedMultimap.access$208(AbstractMapBasedMultimap.this);
            if (isEmpty) {
                addToMap();
            }
        }

        @Override // java.util.List
        public boolean addAll(int i, Collection<? extends V> collection) {
            if (collection.isEmpty()) {
                return false;
            }
            int size = size();
            boolean addAll = getListDelegate().addAll(i, collection);
            if (addAll) {
                AbstractMapBasedMultimap.access$212(AbstractMapBasedMultimap.this, getDelegate().size() - size);
                if (size == 0) {
                    addToMap();
                }
            }
            return addAll;
        }

        @Override // java.util.List
        public V get(int i) {
            refreshIfEmpty();
            return getListDelegate().get(i);
        }

        List<V> getListDelegate() {
            return (List) getDelegate();
        }

        @Override // java.util.List
        public int indexOf(Object obj) {
            refreshIfEmpty();
            return getListDelegate().indexOf(obj);
        }

        @Override // java.util.List
        public int lastIndexOf(Object obj) {
            refreshIfEmpty();
            return getListDelegate().lastIndexOf(obj);
        }

        @Override // java.util.List
        public ListIterator<V> listIterator() {
            refreshIfEmpty();
            return new WrappedListIterator();
        }

        @Override // java.util.List
        public V remove(int i) {
            refreshIfEmpty();
            V remove = getListDelegate().remove(i);
            AbstractMapBasedMultimap.access$210(AbstractMapBasedMultimap.this);
            removeIfEmpty();
            return remove;
        }

        @Override // java.util.List
        public V set(int i, V v) {
            refreshIfEmpty();
            return getListDelegate().set(i, v);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.util.List
        public List<V> subList(int i, int i2) {
            refreshIfEmpty();
            return AbstractMapBasedMultimap.this.wrapList(getKey(), getListDelegate().subList(i, i2), getAncestor() == null ? this : getAncestor());
        }

        @Override // java.util.List
        public ListIterator<V> listIterator(int i) {
            refreshIfEmpty();
            return new WrappedListIterator(i);
        }
    }
}

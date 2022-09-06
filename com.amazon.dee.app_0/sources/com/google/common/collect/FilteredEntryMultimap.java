package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public class FilteredEntryMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super Map.Entry<K, V>> predicate;
    final Multimap<K, V> unfiltered;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class AsMap extends Maps.ViewCachingAbstractMap<K, Collection<V>> {
        AsMap() {
        }

        @Override // java.util.AbstractMap, java.util.Map
        public void clear() {
            FilteredEntryMultimap.this.clear();
        }

        @Override // java.util.AbstractMap, java.util.Map
        public boolean containsKey(@NullableDecl Object obj) {
            return get(obj) != null;
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        Set<Map.Entry<K, Collection<V>>> createEntrySet() {
            return new Maps.EntrySet<K, Collection<V>>() { // from class: com.google.common.collect.FilteredEntryMultimap.AsMap.1EntrySetImpl
                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Map.Entry<K, Collection<V>>> iterator() {
                    return new AbstractIterator<Map.Entry<K, Collection<V>>>() { // from class: com.google.common.collect.FilteredEntryMultimap.AsMap.1EntrySetImpl.1
                        final Iterator<Map.Entry<K, Collection<V>>> backingIterator;

                        {
                            this.backingIterator = FilteredEntryMultimap.this.unfiltered.mo8101asMap().entrySet().iterator();
                        }

                        /* JADX INFO: Access modifiers changed from: protected */
                        @Override // com.google.common.collect.AbstractIterator
                        /* renamed from: computeNext  reason: collision with other method in class */
                        public Map.Entry<K, Collection<V>> mo8230computeNext() {
                            while (this.backingIterator.hasNext()) {
                                Map.Entry<K, Collection<V>> next = this.backingIterator.next();
                                K key = next.getKey();
                                Collection filterCollection = FilteredEntryMultimap.filterCollection(next.getValue(), new ValuePredicate(key));
                                if (!filterCollection.isEmpty()) {
                                    return Maps.immutableEntry(key, filterCollection);
                                }
                            }
                            return endOfData();
                        }
                    };
                }

                @Override // com.google.common.collect.Maps.EntrySet
                Map<K, Collection<V>> map() {
                    return AsMap.this;
                }

                @Override // com.google.common.collect.Maps.EntrySet, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.in(collection));
                }

                @Override // com.google.common.collect.Maps.EntrySet, com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Predicates.not(Predicates.in(collection)));
                }

                @Override // com.google.common.collect.Maps.EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return Iterators.size(iterator());
                }
            };
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        /* renamed from: createKeySet */
        Set<K> mo8041createKeySet() {
            return new Maps.KeySet<K, Collection<V>>() { // from class: com.google.common.collect.FilteredEntryMultimap.AsMap.1KeySetImpl
                @Override // com.google.common.collect.Maps.KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean remove(@NullableDecl Object obj) {
                    return AsMap.this.remove(obj) != null;
                }

                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.in(collection)));
                }

                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.keyPredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }
            };
        }

        @Override // com.google.common.collect.Maps.ViewCachingAbstractMap
        Collection<Collection<V>> createValues() {
            return new Maps.Values<K, Collection<V>>() { // from class: com.google.common.collect.FilteredEntryMultimap.AsMap.1ValuesImpl
                @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                public boolean remove(@NullableDecl Object obj) {
                    if (obj instanceof Collection) {
                        Collection collection = (Collection) obj;
                        Iterator<Map.Entry<K, Collection<V>>> it2 = FilteredEntryMultimap.this.unfiltered.mo8101asMap().entrySet().iterator();
                        while (it2.hasNext()) {
                            Map.Entry<K, Collection<V>> next = it2.next();
                            Collection filterCollection = FilteredEntryMultimap.filterCollection(next.getValue(), new ValuePredicate(next.getKey()));
                            if (!filterCollection.isEmpty() && collection.equals(filterCollection)) {
                                if (filterCollection.size() == next.getValue().size()) {
                                    it2.remove();
                                    return true;
                                }
                                filterCollection.clear();
                                return true;
                            }
                        }
                        return false;
                    }
                    return false;
                }

                @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                public boolean removeAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.in(collection)));
                }

                @Override // com.google.common.collect.Maps.Values, java.util.AbstractCollection, java.util.Collection
                public boolean retainAll(Collection<?> collection) {
                    return FilteredEntryMultimap.this.removeEntriesIf(Maps.valuePredicateOnEntries(Predicates.not(Predicates.in(collection))));
                }
            };
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> get(@NullableDecl Object obj) {
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.mo8101asMap().get(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> filterCollection = FilteredEntryMultimap.filterCollection(collection, new ValuePredicate(obj));
            if (!filterCollection.isEmpty()) {
                return filterCollection;
            }
            return null;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public Collection<V> remove(@NullableDecl Object obj) {
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.mo8101asMap().get(obj);
            if (collection == null) {
                return null;
            }
            ArrayList newArrayList = Lists.newArrayList();
            Iterator<V> it2 = collection.iterator();
            while (it2.hasNext()) {
                V next = it2.next();
                if (FilteredEntryMultimap.this.satisfies(obj, next)) {
                    it2.remove();
                    newArrayList.add(next);
                }
            }
            if (newArrayList.isEmpty()) {
                return null;
            }
            if (FilteredEntryMultimap.this.unfiltered instanceof SetMultimap) {
                return Collections.unmodifiableSet(Sets.newLinkedHashSet(newArrayList));
            }
            return Collections.unmodifiableList(newArrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class Keys extends Multimaps.Keys<K, V> {
        Keys() {
            super(FilteredEntryMultimap.this);
        }

        @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        /* renamed from: entrySet */
        public Set<Multiset.Entry<K>> mo7764entrySet() {
            return new Multisets.EntrySet<K>() { // from class: com.google.common.collect.FilteredEntryMultimap.Keys.1
                private boolean removeEntriesIf(final Predicate<? super Multiset.Entry<K>> predicate) {
                    return FilteredEntryMultimap.this.removeEntriesIf(new Predicate<Map.Entry<K, Collection<V>>>(this) { // from class: com.google.common.collect.FilteredEntryMultimap.Keys.1.1
                        @Override // com.google.common.base.Predicate
                        public /* bridge */ /* synthetic */ boolean apply(Object obj) {
                            return apply((Map.Entry) ((Map.Entry) obj));
                        }

                        public boolean apply(Map.Entry<K, Collection<V>> entry) {
                            return predicate.apply(Multisets.immutableEntry(entry.getKey(), entry.getValue().size()));
                        }
                    });
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public Iterator<Multiset.Entry<K>> iterator() {
                    return Keys.this.entryIterator();
                }

                @Override // com.google.common.collect.Multisets.EntrySet
                /* renamed from: multiset */
                Multiset<K> mo7596multiset() {
                    return Keys.this;
                }

                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean removeAll(Collection<?> collection) {
                    return removeEntriesIf(Predicates.in(collection));
                }

                @Override // com.google.common.collect.Sets.ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public boolean retainAll(Collection<?> collection) {
                    return removeEntriesIf(Predicates.not(Predicates.in(collection)));
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
                public int size() {
                    return FilteredEntryMultimap.this.mo8105keySet().size();
                }
            };
        }

        @Override // com.google.common.collect.Multimaps.Keys, com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
        public int remove(@NullableDecl Object obj, int i) {
            CollectPreconditions.checkNonnegative(i, "occurrences");
            if (i == 0) {
                return count(obj);
            }
            Collection<V> collection = FilteredEntryMultimap.this.unfiltered.mo8101asMap().get(obj);
            int i2 = 0;
            if (collection == null) {
                return 0;
            }
            Iterator<V> it2 = collection.iterator();
            while (it2.hasNext()) {
                if (FilteredEntryMultimap.this.satisfies(obj, it2.next()) && (i2 = i2 + 1) <= i) {
                    it2.remove();
                }
            }
            return i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public final class ValuePredicate implements Predicate<V> {
        private final K key;

        ValuePredicate(K k) {
            this.key = k;
        }

        @Override // com.google.common.base.Predicate
        public boolean apply(@NullableDecl V v) {
            return FilteredEntryMultimap.this.satisfies(this.key, v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteredEntryMultimap(Multimap<K, V> multimap, Predicate<? super Map.Entry<K, V>> predicate) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(multimap);
        this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
    }

    static <E> Collection<E> filterCollection(Collection<E> collection, Predicate<? super E> predicate) {
        if (collection instanceof Set) {
            return Sets.filter((Set) collection, predicate);
        }
        return Collections2.filter(collection, predicate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean satisfies(K k, V v) {
        return this.predicate.apply(Maps.immutableEntry(k, v));
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        mo8077entries().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return mo8101asMap().get(obj) != null;
    }

    @Override // com.google.common.collect.AbstractMultimap
    Map<K, Collection<V>> createAsMap() {
        return new AsMap();
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createEntries */
    Collection<Map.Entry<K, V>> mo7870createEntries() {
        return filterCollection(this.unfiltered.mo8077entries(), this.predicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    Set<K> createKeySet() {
        return mo8101asMap().keySet();
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createKeys */
    Multiset<K> mo7748createKeys() {
        return new Keys();
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createValues */
    Collection<V> mo7871createValues() {
        return new FilteredMultimapValues(this);
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: entryIterator */
    Iterator<Map.Entry<K, V>> mo7751entryIterator() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.FilteredMultimap
    public Predicate<? super Map.Entry<K, V>> entryPredicate() {
        return this.predicate;
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public Collection<V> mo8104get(K k) {
        return filterCollection(this.unfiltered.mo8104get(k), new ValuePredicate(k));
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: removeAll */
    public Collection<V> mo8087removeAll(@NullableDecl Object obj) {
        return (Collection) MoreObjects.firstNonNull(mo8101asMap().remove(obj), unmodifiableEmptyCollection());
    }

    boolean removeEntriesIf(Predicate<? super Map.Entry<K, Collection<V>>> predicate) {
        Iterator<Map.Entry<K, Collection<V>>> it2 = this.unfiltered.mo8101asMap().entrySet().iterator();
        boolean z = false;
        while (it2.hasNext()) {
            Map.Entry<K, Collection<V>> next = it2.next();
            K key = next.getKey();
            Collection filterCollection = filterCollection(next.getValue(), new ValuePredicate(key));
            if (!filterCollection.isEmpty() && predicate.apply(Maps.immutableEntry(key, filterCollection))) {
                if (filterCollection.size() == next.getValue().size()) {
                    it2.remove();
                } else {
                    filterCollection.clear();
                }
                z = true;
            }
        }
        return z;
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return mo8077entries().size();
    }

    @Override // com.google.common.collect.FilteredMultimap
    /* renamed from: unfiltered */
    public Multimap<K, V> mo7647unfiltered() {
        return this.unfiltered;
    }

    Collection<V> unmodifiableEmptyCollection() {
        if (this.unfiltered instanceof SetMultimap) {
            return Collections.emptySet();
        }
        return Collections.emptyList();
    }
}

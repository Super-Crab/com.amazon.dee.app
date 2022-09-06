package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public class FilteredKeyMultimap<K, V> extends AbstractMultimap<K, V> implements FilteredMultimap<K, V> {
    final Predicate<? super K> keyPredicate;
    final Multimap<K, V> unfiltered;

    /* loaded from: classes3.dex */
    static class AddRejectingList<K, V> extends ForwardingList<V> {
        final K key;

        AddRejectingList(K k) {
            this.key = k;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            add(0, v);
            return true;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            addAll(0, collection);
            return true;
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        public void add(int i, V v) {
            Preconditions.checkPositionIndex(i, 0);
            String valueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline29(valueOf.length() + 32, "Key does not satisfy predicate: ", valueOf));
        }

        @Override // com.google.common.collect.ForwardingList, java.util.List
        @CanIgnoreReturnValue
        public boolean addAll(int i, Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            Preconditions.checkPositionIndex(i, 0);
            String valueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline29(valueOf.length() + 32, "Key does not satisfy predicate: ", valueOf));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingList, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: delegate */
        public List<V> mo8280delegate() {
            return Collections.emptyList();
        }
    }

    /* loaded from: classes3.dex */
    class Entries extends ForwardingCollection<Map.Entry<K, V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Entries() {
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
        public boolean remove(@NullableDecl Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                if (FilteredKeyMultimap.this.unfiltered.containsKey(entry.getKey()) && FilteredKeyMultimap.this.keyPredicate.apply((Object) entry.getKey())) {
                    return FilteredKeyMultimap.this.unfiltered.remove(entry.getKey(), entry.getValue());
                }
                return false;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: delegate  reason: collision with other method in class */
        public Collection<Map.Entry<K, V>> mo8280delegate() {
            return Collections2.filter(FilteredKeyMultimap.this.unfiltered.mo8077entries(), FilteredKeyMultimap.this.entryPredicate());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteredKeyMultimap(Multimap<K, V> multimap, Predicate<? super K> predicate) {
        this.unfiltered = (Multimap) Preconditions.checkNotNull(multimap);
        this.keyPredicate = (Predicate) Preconditions.checkNotNull(predicate);
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        mo8105keySet().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        if (this.unfiltered.containsKey(obj)) {
            return this.keyPredicate.apply(obj);
        }
        return false;
    }

    @Override // com.google.common.collect.AbstractMultimap
    Map<K, Collection<V>> createAsMap() {
        return Maps.filterKeys(this.unfiltered.mo8101asMap(), this.keyPredicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createEntries */
    Collection<Map.Entry<K, V>> mo7870createEntries() {
        return new Entries();
    }

    @Override // com.google.common.collect.AbstractMultimap
    Set<K> createKeySet() {
        return Sets.filter(this.unfiltered.mo8105keySet(), this.keyPredicate);
    }

    @Override // com.google.common.collect.AbstractMultimap
    /* renamed from: createKeys */
    Multiset<K> mo7748createKeys() {
        return Multisets.filter(this.unfiltered.mo7754keys(), this.keyPredicate);
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
        return Maps.keyPredicateOnEntries(this.keyPredicate);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public Collection<V> mo8104get(K k) {
        if (this.keyPredicate.apply(k)) {
            return this.unfiltered.mo8104get(k);
        }
        if (this.unfiltered instanceof SetMultimap) {
            return new AddRejectingSet(k);
        }
        return new AddRejectingList(k);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: removeAll */
    public Collection<V> mo8087removeAll(Object obj) {
        return containsKey(obj) ? this.unfiltered.mo8087removeAll(obj) : unmodifiableEmptyCollection();
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        int i = 0;
        for (Collection<V> collection : mo8101asMap().values()) {
            i += collection.size();
        }
        return i;
    }

    /* renamed from: unfiltered */
    public Multimap<K, V> mo7647unfiltered() {
        return this.unfiltered;
    }

    Collection<V> unmodifiableEmptyCollection() {
        if (this.unfiltered instanceof SetMultimap) {
            return ImmutableSet.of();
        }
        return ImmutableList.of();
    }

    /* loaded from: classes3.dex */
    static class AddRejectingSet<K, V> extends ForwardingSet<V> {
        final K key;

        AddRejectingSet(K k) {
            this.key = k;
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
        public boolean add(V v) {
            String valueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline29(valueOf.length() + 32, "Key does not satisfy predicate: ", valueOf));
        }

        @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
        public boolean addAll(Collection<? extends V> collection) {
            Preconditions.checkNotNull(collection);
            String valueOf = String.valueOf(this.key);
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline29(valueOf.length() + 32, "Key does not satisfy predicate: ", valueOf));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
        /* renamed from: delegate */
        public Set<V> mo8280delegate() {
            return Collections.emptySet();
        }
    }
}

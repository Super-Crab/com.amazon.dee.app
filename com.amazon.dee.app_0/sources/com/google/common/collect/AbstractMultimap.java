package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multimaps;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
    @NullableDecl
    private transient Map<K, Collection<V>> asMap;
    @NullableDecl
    private transient Collection<Map.Entry<K, V>> entries;
    @NullableDecl
    private transient Set<K> keySet;
    @NullableDecl
    private transient Multiset<K> keys;
    @NullableDecl
    private transient Collection<V> values;

    /* loaded from: classes3.dex */
    class Entries extends Multimaps.Entries<K, V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Entries() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Map.Entry<K, V>> iterator() {
            return AbstractMultimap.this.mo7751entryIterator();
        }

        @Override // com.google.common.collect.Multimaps.Entries
        Multimap<K, V> multimap() {
            return AbstractMultimap.this;
        }
    }

    /* loaded from: classes3.dex */
    class EntrySet extends AbstractMultimap<K, V>.Entries implements Set<Map.Entry<K, V>> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EntrySet(AbstractMultimap abstractMultimap) {
            super();
        }

        @Override // java.util.Collection, java.util.Set
        public boolean equals(@NullableDecl Object obj) {
            return Sets.equalsImpl(this, obj);
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            return Sets.hashCodeImpl(this);
        }
    }

    /* loaded from: classes3.dex */
    class Values extends AbstractCollection<V> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Values() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            AbstractMultimap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(@NullableDecl Object obj) {
            return AbstractMultimap.this.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            return AbstractMultimap.this.mo7757valueIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return AbstractMultimap.this.size();
        }
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: asMap */
    public Map<K, Collection<V>> mo8101asMap() {
        Map<K, Collection<V>> map = this.asMap;
        if (map == null) {
            Map<K, Collection<V>> createAsMap = createAsMap();
            this.asMap = createAsMap;
            return createAsMap;
        }
        return map;
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Collection<V> collection = mo8101asMap().get(obj);
        return collection != null && collection.contains(obj2);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsValue(@NullableDecl Object obj) {
        for (Collection<V> collection : mo8101asMap().values()) {
            if (collection.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    abstract Map<K, Collection<V>> createAsMap();

    /* renamed from: createEntries */
    abstract Collection<Map.Entry<K, V>> mo7870createEntries();

    abstract Set<K> createKeySet();

    /* renamed from: createKeys */
    abstract Multiset<K> mo7748createKeys();

    /* renamed from: createValues */
    abstract Collection<V> mo7871createValues();

    @Override // com.google.common.collect.Multimap
    /* renamed from: entries */
    public Collection<Map.Entry<K, V>> mo8077entries() {
        Collection<Map.Entry<K, V>> collection = this.entries;
        if (collection == null) {
            Collection<Map.Entry<K, V>> mo7870createEntries = mo7870createEntries();
            this.entries = mo7870createEntries;
            return mo7870createEntries;
        }
        return collection;
    }

    /* renamed from: entryIterator */
    abstract Iterator<Map.Entry<K, V>> mo7751entryIterator();

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public boolean equals(@NullableDecl Object obj) {
        return Multimaps.equalsImpl(this, obj);
    }

    @Override // com.google.common.collect.Multimap
    public int hashCode() {
        return mo8101asMap().hashCode();
    }

    @Override // com.google.common.collect.Multimap
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // com.google.common.collect.Multimap
    /* renamed from: keySet */
    public Set<K> mo8105keySet() {
        Set<K> set = this.keySet;
        if (set == null) {
            Set<K> createKeySet = createKeySet();
            this.keySet = createKeySet;
            return createKeySet;
        }
        return set;
    }

    @Override // com.google.common.collect.Multimap
    /* renamed from: keys */
    public Multiset<K> mo7754keys() {
        Multiset<K> multiset = this.keys;
        if (multiset == null) {
            Multiset<K> mo7748createKeys = mo7748createKeys();
            this.keys = mo7748createKeys;
            return mo7748createKeys;
        }
        return multiset;
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(@NullableDecl K k, @NullableDecl V v) {
        return mo8104get(k).add(v);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(@NullableDecl K k, Iterable<? extends V> iterable) {
        Preconditions.checkNotNull(iterable);
        if (iterable instanceof Collection) {
            Collection<? extends V> collection = (Collection) iterable;
            return !collection.isEmpty() && mo8104get(k).addAll(collection);
        }
        Iterator<? extends V> it2 = iterable.iterator();
        return it2.hasNext() && Iterators.addAll(mo8104get(k), it2);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        Collection<V> collection = mo8101asMap().get(obj);
        return collection != null && collection.remove(obj2);
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    /* renamed from: replaceValues */
    public Collection<V> mo8088replaceValues(@NullableDecl K k, Iterable<? extends V> iterable) {
        Preconditions.checkNotNull(iterable);
        Collection<V> mo8087removeAll = mo8087removeAll(k);
        putAll(k, iterable);
        return mo8087removeAll;
    }

    public String toString() {
        return mo8101asMap().toString();
    }

    /* renamed from: valueIterator */
    Iterator<V> mo7757valueIterator() {
        return Maps.valueIterator(mo8077entries().iterator());
    }

    @Override // com.google.common.collect.Multimap
    /* renamed from: values */
    public Collection<V> mo7876values() {
        Collection<V> collection = this.values;
        if (collection == null) {
            Collection<V> mo7871createValues = mo7871createValues();
            this.values = mo7871createValues;
            return mo7871createValues;
        }
        return collection;
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        boolean z = false;
        for (Map.Entry<? extends K, ? extends V> entry : multimap.mo8077entries()) {
            z |= put(entry.getKey(), entry.getValue());
        }
        return z;
    }
}

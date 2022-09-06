package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public final class FilteredKeySetMultimap<K, V> extends FilteredKeyMultimap<K, V> implements FilteredSetMultimap<K, V> {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class EntrySet extends FilteredKeyMultimap<K, V>.Entries implements Set<Map.Entry<K, V>> {
        EntrySet(FilteredKeySetMultimap filteredKeySetMultimap) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteredKeySetMultimap(SetMultimap<K, V> setMultimap, Predicate<? super K> predicate) {
        super(setMultimap, predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo8104get(Object obj) {
        return mo8104get((FilteredKeySetMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ Collection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((FilteredKeySetMultimap<K, V>) obj, iterable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.AbstractMultimap
    /* renamed from: createEntries  reason: collision with other method in class */
    public Set<Map.Entry<K, V>> mo7870createEntries() {
        return new EntrySet(this);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    /* renamed from: entries  reason: collision with other method in class */
    public Set<Map.Entry<K, V>> mo8077entries() {
        return (Set) super.mo8077entries();
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public Set<V> mo8104get(K k) {
        return (Set) super.mo8104get((FilteredKeySetMultimap<K, V>) k);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: removeAll  reason: collision with other method in class */
    public Set<V> mo8087removeAll(Object obj) {
        return (Set) super.mo8087removeAll(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: replaceValues  reason: collision with other method in class */
    public Set<V> mo8088replaceValues(K k, Iterable<? extends V> iterable) {
        return (Set) super.mo8088replaceValues((FilteredKeySetMultimap<K, V>) k, (Iterable) iterable);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.FilteredMultimap
    /* renamed from: unfiltered  reason: collision with other method in class */
    public SetMultimap<K, V> mo7647unfiltered() {
        return (SetMultimap) this.unfiltered;
    }
}

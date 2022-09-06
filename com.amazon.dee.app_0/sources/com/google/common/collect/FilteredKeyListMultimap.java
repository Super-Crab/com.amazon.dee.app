package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public final class FilteredKeyListMultimap<K, V> extends FilteredKeyMultimap<K, V> implements ListMultimap<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public FilteredKeyListMultimap(ListMultimap<K, V> listMultimap, Predicate<? super K> predicate) {
        super(listMultimap, predicate);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo8104get(Object obj) {
        return mo8104get((FilteredKeyListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ Collection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((FilteredKeyListMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public List<V> mo8104get(K k) {
        return (List) super.mo8104get((FilteredKeyListMultimap<K, V>) k);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: removeAll  reason: collision with other method in class */
    public List<V> mo8087removeAll(@NullableDecl Object obj) {
        return (List) super.mo8087removeAll(obj);
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: replaceValues  reason: collision with other method in class */
    public List<V> mo8088replaceValues(K k, Iterable<? extends V> iterable) {
        return (List) super.mo8088replaceValues((FilteredKeyListMultimap<K, V>) k, (Iterable) iterable);
    }

    @Override // com.google.common.collect.FilteredKeyMultimap, com.google.common.collect.FilteredMultimap
    /* renamed from: unfiltered */
    public ListMultimap<K, V> mo7647unfiltered() {
        return (ListMultimap) super.mo7647unfiltered();
    }
}

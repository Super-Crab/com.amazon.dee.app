package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingListMultimap<K, V> extends ForwardingMultimap<K, V> implements ListMultimap<K, V> {
    protected ForwardingListMultimap() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract ListMultimap<K, V> mo8280delegate();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    public /* bridge */ /* synthetic */ Collection mo8104get(@NullableDecl Object obj) {
        return mo8104get((ForwardingListMultimap<K, V>) obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    /* renamed from: replaceValues */
    public /* bridge */ /* synthetic */ Collection mo8088replaceValues(Object obj, Iterable iterable) {
        return mo8088replaceValues((ForwardingListMultimap<K, V>) obj, iterable);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get  reason: collision with other method in class */
    public List<V> mo8104get(@NullableDecl K k) {
        return mo8280delegate().mo8104get((ListMultimap<K, V>) k);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    /* renamed from: removeAll  reason: collision with other method in class */
    public List<V> mo8087removeAll(@NullableDecl Object obj) {
        return mo8280delegate().mo8087removeAll(obj);
    }

    @Override // com.google.common.collect.ForwardingMultimap, com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    /* renamed from: replaceValues  reason: collision with other method in class */
    public List<V> mo8088replaceValues(K k, Iterable<? extends V> iterable) {
        return mo8280delegate().mo8088replaceValues((ListMultimap<K, V>) k, (Iterable) iterable);
    }
}

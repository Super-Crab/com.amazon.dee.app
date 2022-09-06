package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingMultimap<K, V> extends ForwardingObject implements Multimap<K, V> {
    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: asMap */
    public Map<K, Collection<V>> mo8101asMap() {
        return mo8280delegate().mo8101asMap();
    }

    @Override // com.google.common.collect.Multimap
    public void clear() {
        mo8280delegate().clear();
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsEntry(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return mo8280delegate().containsEntry(obj, obj2);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsKey(@NullableDecl Object obj) {
        return mo8280delegate().containsKey(obj);
    }

    @Override // com.google.common.collect.Multimap
    public boolean containsValue(@NullableDecl Object obj) {
        return mo8280delegate().containsValue(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract Multimap<K, V> mo8280delegate();

    @Override // com.google.common.collect.Multimap
    /* renamed from: entries */
    public Collection<Map.Entry<K, V>> mo8077entries() {
        return mo8280delegate().mo8077entries();
    }

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    public boolean equals(@NullableDecl Object obj) {
        return obj == this || mo8280delegate().equals(obj);
    }

    /* renamed from: get */
    public Collection<V> mo8104get(@NullableDecl K k) {
        return mo8280delegate().mo8104get(k);
    }

    @Override // com.google.common.collect.Multimap
    public int hashCode() {
        return mo8280delegate().hashCode();
    }

    @Override // com.google.common.collect.Multimap
    public boolean isEmpty() {
        return mo8280delegate().isEmpty();
    }

    @Override // com.google.common.collect.Multimap
    /* renamed from: keySet */
    public Set<K> mo8105keySet() {
        return mo8280delegate().mo8105keySet();
    }

    @Override // com.google.common.collect.Multimap
    /* renamed from: keys */
    public Multiset<K> mo7754keys() {
        return mo8280delegate().mo7754keys();
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean put(K k, V v) {
        return mo8280delegate().put(k, v);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(K k, Iterable<? extends V> iterable) {
        return mo8280delegate().putAll(k, iterable);
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean remove(@NullableDecl Object obj, @NullableDecl Object obj2) {
        return mo8280delegate().remove(obj, obj2);
    }

    @CanIgnoreReturnValue
    /* renamed from: removeAll */
    public Collection<V> mo8087removeAll(@NullableDecl Object obj) {
        return mo8280delegate().mo8087removeAll(obj);
    }

    @CanIgnoreReturnValue
    /* renamed from: replaceValues */
    public Collection<V> mo8088replaceValues(K k, Iterable<? extends V> iterable) {
        return mo8280delegate().mo8088replaceValues(k, iterable);
    }

    @Override // com.google.common.collect.Multimap
    public int size() {
        return mo8280delegate().size();
    }

    @Override // com.google.common.collect.Multimap
    /* renamed from: values */
    public Collection<V> mo7876values() {
        return mo8280delegate().mo7876values();
    }

    @Override // com.google.common.collect.Multimap
    @CanIgnoreReturnValue
    public boolean putAll(Multimap<? extends K, ? extends V> multimap) {
        return mo8280delegate().putAll(multimap);
    }
}

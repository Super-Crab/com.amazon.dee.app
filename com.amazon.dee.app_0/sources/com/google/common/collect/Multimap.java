package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.CompatibleWith;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ImmutableMultimap, HashMultimap, or another implementation")
@GwtCompatible
/* loaded from: classes3.dex */
public interface Multimap<K, V> {
    /* renamed from: asMap */
    Map<K, Collection<V>> mo8101asMap();

    void clear();

    boolean containsEntry(@NullableDecl @CompatibleWith("K") Object obj, @NullableDecl @CompatibleWith("V") Object obj2);

    boolean containsKey(@NullableDecl @CompatibleWith("K") Object obj);

    boolean containsValue(@NullableDecl @CompatibleWith("V") Object obj);

    /* renamed from: entries */
    Collection<Map.Entry<K, V>> mo8077entries();

    boolean equals(@NullableDecl Object obj);

    /* renamed from: get */
    Collection<V> mo8104get(@NullableDecl K k);

    int hashCode();

    boolean isEmpty();

    /* renamed from: keySet */
    Set<K> mo8105keySet();

    /* renamed from: keys */
    Multiset<K> mo7754keys();

    @CanIgnoreReturnValue
    boolean put(@NullableDecl K k, @NullableDecl V v);

    @CanIgnoreReturnValue
    boolean putAll(Multimap<? extends K, ? extends V> multimap);

    @CanIgnoreReturnValue
    boolean putAll(@NullableDecl K k, Iterable<? extends V> iterable);

    @CanIgnoreReturnValue
    boolean remove(@NullableDecl @CompatibleWith("K") Object obj, @NullableDecl @CompatibleWith("V") Object obj2);

    @CanIgnoreReturnValue
    /* renamed from: removeAll */
    Collection<V> mo8087removeAll(@NullableDecl @CompatibleWith("K") Object obj);

    @CanIgnoreReturnValue
    /* renamed from: replaceValues */
    Collection<V> mo8088replaceValues(@NullableDecl K k, Iterable<? extends V> iterable);

    int size();

    /* renamed from: values */
    Collection<V> mo7876values();
}

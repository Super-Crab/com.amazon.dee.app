package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public interface SetMultimap<K, V> extends Multimap<K, V> {
    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: asMap */
    Map<K, Collection<V>> mo8101asMap();

    @Override // com.google.common.collect.Multimap
    /* renamed from: entries */
    Set<Map.Entry<K, V>> mo8077entries();

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    boolean equals(@NullableDecl Object obj);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    /* renamed from: get */
    Set<V> mo8104get(@NullableDecl K k);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    /* renamed from: removeAll */
    Set<V> mo8087removeAll(@NullableDecl Object obj);

    @Override // com.google.common.collect.Multimap, com.google.common.collect.ListMultimap
    @CanIgnoreReturnValue
    /* renamed from: replaceValues */
    Set<V> mo8088replaceValues(K k, Iterable<? extends V> iterable);
}

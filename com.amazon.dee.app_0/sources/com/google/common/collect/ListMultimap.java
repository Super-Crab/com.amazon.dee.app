package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public interface ListMultimap<K, V> extends Multimap<K, V> {
    @Override // 
    /* renamed from: asMap */
    Map<K, Collection<V>> mo8101asMap();

    @Override // 
    boolean equals(@NullableDecl Object obj);

    @Override // 
    /* renamed from: get */
    List<V> mo8104get(@NullableDecl K k);

    @Override // 
    @CanIgnoreReturnValue
    /* renamed from: removeAll */
    List<V> mo8087removeAll(@NullableDecl Object obj);

    @Override // 
    @CanIgnoreReturnValue
    /* renamed from: replaceValues */
    List<V> mo8088replaceValues(K k, Iterable<? extends V> iterable);
}

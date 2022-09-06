package org.apache.commons.collections4;

import java.util.Set;
/* loaded from: classes4.dex */
public interface BidiMap<K, V> extends IterableMap<K, V> {
    /* renamed from: getKey */
    K mo12664getKey(Object obj);

    /* renamed from: inverseBidiMap */
    BidiMap<V, K> mo12689inverseBidiMap();

    @Override // java.util.Map, org.apache.commons.collections4.Put
    V put(K k, V v);

    /* renamed from: removeValue */
    K mo12669removeValue(Object obj);

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    Set<V> mo12691values();
}

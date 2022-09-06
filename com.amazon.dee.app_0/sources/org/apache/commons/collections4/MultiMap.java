package org.apache.commons.collections4;

import java.util.Collection;
@Deprecated
/* loaded from: classes4.dex */
public interface MultiMap<K, V> extends IterableMap<K, Object> {
    @Override // java.util.Map, org.apache.commons.collections4.Get
    boolean containsValue(Object obj);

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    Object mo12663get(Object obj);

    @Override // java.util.Map, org.apache.commons.collections4.Put
    Object put(K k, Object obj);

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    Object mo12668remove(Object obj);

    boolean removeMapping(K k, V v);

    @Override // java.util.Map, org.apache.commons.collections4.Get
    int size();

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    Collection<Object> mo12691values();
}

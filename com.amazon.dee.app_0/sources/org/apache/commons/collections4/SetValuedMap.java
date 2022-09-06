package org.apache.commons.collections4;

import java.util.Set;
/* loaded from: classes4.dex */
public interface SetValuedMap<K, V> extends MultiValuedMap<K, V> {
    @Override // org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get */
    Set<V> mo12740get(K k);

    @Override // org.apache.commons.collections4.MultiValuedMap
    /* renamed from: remove */
    Set<V> mo12741remove(Object obj);
}

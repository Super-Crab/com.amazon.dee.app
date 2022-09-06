package org.apache.commons.collections4;

import java.util.List;
/* loaded from: classes4.dex */
public interface ListValuedMap<K, V> extends MultiValuedMap<K, V> {
    @Override // org.apache.commons.collections4.MultiValuedMap
    /* renamed from: get */
    List<V> mo12740get(K k);

    @Override // org.apache.commons.collections4.MultiValuedMap
    /* renamed from: remove */
    List<V> mo12741remove(Object obj);
}

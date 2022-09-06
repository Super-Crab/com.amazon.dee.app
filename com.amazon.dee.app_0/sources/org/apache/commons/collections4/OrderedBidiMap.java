package org.apache.commons.collections4;
/* loaded from: classes4.dex */
public interface OrderedBidiMap<K, V> extends BidiMap<K, V>, OrderedMap<K, V> {
    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    OrderedBidiMap<V, K> mo12689inverseBidiMap();
}

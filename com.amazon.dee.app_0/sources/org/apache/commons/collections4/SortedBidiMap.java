package org.apache.commons.collections4;

import java.util.Comparator;
import java.util.SortedMap;
/* loaded from: classes4.dex */
public interface SortedBidiMap<K, V> extends OrderedBidiMap<K, V>, SortedMap<K, V> {
    @Override // org.apache.commons.collections4.OrderedBidiMap, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    SortedBidiMap<V, K> mo12689inverseBidiMap();

    Comparator<? super V> valueComparator();
}

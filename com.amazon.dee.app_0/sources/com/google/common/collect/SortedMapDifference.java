package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.MapDifference;
import java.util.SortedMap;
@GwtCompatible
/* loaded from: classes3.dex */
public interface SortedMapDifference<K, V> extends MapDifference<K, V> {
    @Override // com.google.common.collect.MapDifference
    /* renamed from: entriesDiffering */
    SortedMap<K, MapDifference.ValueDifference<V>> mo7923entriesDiffering();

    @Override // com.google.common.collect.MapDifference
    /* renamed from: entriesInCommon */
    SortedMap<K, V> mo7924entriesInCommon();

    @Override // com.google.common.collect.MapDifference
    /* renamed from: entriesOnlyOnLeft */
    SortedMap<K, V> mo7925entriesOnlyOnLeft();

    @Override // com.google.common.collect.MapDifference
    /* renamed from: entriesOnlyOnRight */
    SortedMap<K, V> mo7926entriesOnlyOnRight();
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.lang.Comparable;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ImmutableRangeMap or TreeRangeMap")
@Beta
@GwtIncompatible
/* loaded from: classes3.dex */
public interface RangeMap<K extends Comparable, V> {
    /* renamed from: asDescendingMapOfRanges */
    Map<Range<K>, V> mo7772asDescendingMapOfRanges();

    /* renamed from: asMapOfRanges */
    Map<Range<K>, V> mo7773asMapOfRanges();

    void clear();

    boolean equals(@NullableDecl Object obj);

    @NullableDecl
    V get(K k);

    @NullableDecl
    Map.Entry<Range<K>, V> getEntry(K k);

    int hashCode();

    void put(Range<K> range, V v);

    void putAll(RangeMap<K, V> rangeMap);

    void putCoalescing(Range<K> range, V v);

    void remove(Range<K> range);

    Range<K> span();

    /* renamed from: subRangeMap */
    RangeMap<K, V> mo7776subRangeMap(Range<K> range);

    String toString();
}

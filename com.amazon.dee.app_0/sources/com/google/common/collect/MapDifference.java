package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use Maps.difference")
@GwtCompatible
/* loaded from: classes3.dex */
public interface MapDifference<K, V> {

    @DoNotMock("Use Maps.difference")
    /* loaded from: classes3.dex */
    public interface ValueDifference<V> {
        boolean equals(@NullableDecl Object obj);

        int hashCode();

        V leftValue();

        V rightValue();
    }

    boolean areEqual();

    /* renamed from: entriesDiffering */
    Map<K, ValueDifference<V>> mo7923entriesDiffering();

    /* renamed from: entriesInCommon */
    Map<K, V> mo7924entriesInCommon();

    /* renamed from: entriesOnlyOnLeft */
    Map<K, V> mo7925entriesOnlyOnLeft();

    /* renamed from: entriesOnlyOnRight */
    Map<K, V> mo7926entriesOnlyOnRight();

    boolean equals(@NullableDecl Object obj);

    int hashCode();
}

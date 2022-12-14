package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.DoNotMock;
import java.lang.Comparable;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@DoNotMock("Use ImmutableRangeSet or TreeRangeSet")
@Beta
@GwtIncompatible
/* loaded from: classes3.dex */
public interface RangeSet<C extends Comparable> {
    void add(Range<C> range);

    void addAll(RangeSet<C> rangeSet);

    void addAll(Iterable<Range<C>> iterable);

    /* renamed from: asDescendingSetOfRanges */
    Set<Range<C>> mo7777asDescendingSetOfRanges();

    /* renamed from: asRanges */
    Set<Range<C>> mo7778asRanges();

    void clear();

    /* renamed from: complement */
    RangeSet<C> mo7779complement();

    boolean contains(C c);

    boolean encloses(Range<C> range);

    boolean enclosesAll(RangeSet<C> rangeSet);

    boolean enclosesAll(Iterable<Range<C>> iterable);

    boolean equals(@NullableDecl Object obj);

    int hashCode();

    boolean intersects(Range<C> range);

    boolean isEmpty();

    Range<C> rangeContaining(C c);

    void remove(Range<C> range);

    void removeAll(RangeSet<C> rangeSet);

    void removeAll(Iterable<Range<C>> iterable);

    Range<C> span();

    /* renamed from: subRangeSet */
    RangeSet<C> mo7780subRangeSet(Range<C> range);

    String toString();
}

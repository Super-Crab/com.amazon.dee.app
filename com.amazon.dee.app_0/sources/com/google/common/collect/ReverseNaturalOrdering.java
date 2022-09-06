package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
@GwtCompatible(serializable = true)
/* loaded from: classes3.dex */
final class ReverseNaturalOrdering extends Ordering<Comparable> implements Serializable {
    static final ReverseNaturalOrdering INSTANCE = new ReverseNaturalOrdering();
    private static final long serialVersionUID = 0;

    private ReverseNaturalOrdering() {
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: max  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo8013max(Iterable iterable) {
        return mo8013max((Iterable<Comparable>) iterable);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: min  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo8015min(Iterable iterable) {
        return mo8015min((Iterable<Comparable>) iterable);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends Comparable> Ordering<S> reverse() {
        return Ordering.natural();
    }

    public String toString() {
        return "Ordering.natural().reverse()";
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(Comparable comparable, Comparable comparable2) {
        Preconditions.checkNotNull(comparable);
        if (comparable == comparable2) {
            return 0;
        }
        return comparable2.compareTo(comparable);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: max  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo8014max(Iterator it2) {
        return mo8014max((Iterator<Comparable>) it2);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: min  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Object mo8016min(Iterator it2) {
        return mo8016min((Iterator<Comparable>) it2);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E max(E e, E e2) {
        return (E) NaturalOrdering.INSTANCE.min(e, e2);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E min(E e, E e2) {
        return (E) NaturalOrdering.INSTANCE.max(e, e2);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E max(E e, E e2, E e3, E... eArr) {
        return (E) NaturalOrdering.INSTANCE.min(e, e2, e3, eArr);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends Comparable> E min(E e, E e2, E e3, E... eArr) {
        return (E) NaturalOrdering.INSTANCE.max(e, e2, e3, eArr);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: max */
    public <E extends Comparable> E mo8014max(Iterator<E> it2) {
        return (E) NaturalOrdering.INSTANCE.mo8016min(it2);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: min */
    public <E extends Comparable> E mo8016min(Iterator<E> it2) {
        return (E) NaturalOrdering.INSTANCE.mo8014max(it2);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: max */
    public <E extends Comparable> E mo8013max(Iterable<E> iterable) {
        return (E) NaturalOrdering.INSTANCE.mo8015min(iterable);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: min */
    public <E extends Comparable> E mo8015min(Iterable<E> iterable) {
        return (E) NaturalOrdering.INSTANCE.mo8013max(iterable);
    }
}

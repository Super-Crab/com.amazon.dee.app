package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
public interface SortedMultiset<E> extends SortedMultisetBridge<E>, SortedIterable<E> {
    Comparator<? super E> comparator();

    /* renamed from: descendingMultiset */
    SortedMultiset<E> mo7833descendingMultiset();

    @Override // com.google.common.collect.SortedMultisetBridge, com.google.common.collect.Multiset
    /* renamed from: elementSet */
    NavigableSet<E> mo8127elementSet();

    @Override // com.google.common.collect.Multiset
    /* renamed from: entrySet */
    Set<Multiset.Entry<E>> mo7764entrySet();

    Multiset.Entry<E> firstEntry();

    /* renamed from: headMultiset */
    SortedMultiset<E> mo8006headMultiset(E e, BoundType boundType);

    @Override // com.google.common.collect.Multiset, java.util.Collection, java.lang.Iterable
    /* renamed from: iterator */
    Iterator<E> mo7986iterator();

    Multiset.Entry<E> lastEntry();

    Multiset.Entry<E> pollFirstEntry();

    Multiset.Entry<E> pollLastEntry();

    /* renamed from: subMultiset */
    SortedMultiset<E> mo7837subMultiset(E e, BoundType boundType, E e2, BoundType boundType2);

    /* renamed from: tailMultiset */
    SortedMultiset<E> mo8007tailMultiset(E e, BoundType boundType);
}

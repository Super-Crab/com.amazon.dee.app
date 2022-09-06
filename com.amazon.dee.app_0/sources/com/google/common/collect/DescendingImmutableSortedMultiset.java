package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtIncompatible
/* loaded from: classes3.dex */
public final class DescendingImmutableSortedMultiset<E> extends ImmutableSortedMultiset<E> {
    private final transient ImmutableSortedMultiset<E> forward;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DescendingImmutableSortedMultiset(ImmutableSortedMultiset<E> immutableSortedMultiset) {
        this.forward = immutableSortedMultiset;
    }

    @Override // com.google.common.collect.Multiset
    public int count(@NullableDecl Object obj) {
        return this.forward.count(obj);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return this.forward.lastEntry();
    }

    @Override // com.google.common.collect.ImmutableMultiset
    Multiset.Entry<E> getEntry(int i) {
        return this.forward.mo7764entrySet().asList().reverse().get(i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    /* renamed from: headMultiset  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ SortedMultiset mo8006headMultiset(Object obj, BoundType boundType) {
        return mo8006headMultiset((DescendingImmutableSortedMultiset<E>) obj, boundType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.ImmutableCollection
    public boolean isPartialView() {
        return this.forward.isPartialView();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return this.forward.firstEntry();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public int size() {
        return this.forward.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    /* renamed from: tailMultiset  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ SortedMultiset mo8007tailMultiset(Object obj, BoundType boundType) {
        return mo8007tailMultiset((DescendingImmutableSortedMultiset<E>) obj, boundType);
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    /* renamed from: descendingMultiset */
    public ImmutableSortedMultiset<E> mo7833descendingMultiset() {
        return this.forward;
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    /* renamed from: headMultiset */
    public ImmutableSortedMultiset<E> mo8006headMultiset(E e, BoundType boundType) {
        return this.forward.mo8007tailMultiset((ImmutableSortedMultiset<E>) e, boundType).mo7833descendingMultiset();
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.SortedMultiset
    /* renamed from: tailMultiset */
    public ImmutableSortedMultiset<E> mo8007tailMultiset(E e, BoundType boundType) {
        return this.forward.mo8006headMultiset((ImmutableSortedMultiset<E>) e, boundType).mo7833descendingMultiset();
    }

    @Override // com.google.common.collect.ImmutableSortedMultiset, com.google.common.collect.ImmutableMultiset, com.google.common.collect.Multiset
    /* renamed from: elementSet  reason: collision with other method in class */
    public ImmutableSortedSet<E> mo8127elementSet() {
        return this.forward.mo8127elementSet().descendingSet();
    }
}

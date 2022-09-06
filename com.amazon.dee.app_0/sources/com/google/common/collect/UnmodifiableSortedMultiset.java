package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import java.util.Comparator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
public final class UnmodifiableSortedMultiset<E> extends Multisets.UnmodifiableMultiset<E> implements SortedMultiset<E> {
    private static final long serialVersionUID = 0;
    @NullableDecl
    private transient UnmodifiableSortedMultiset<E> descendingMultiset;

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnmodifiableSortedMultiset(SortedMultiset<E> sortedMultiset) {
        super(sortedMultiset);
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return mo8280delegate().comparator();
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: descendingMultiset */
    public SortedMultiset<E> mo7833descendingMultiset() {
        UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset = this.descendingMultiset;
        if (unmodifiableSortedMultiset == null) {
            UnmodifiableSortedMultiset<E> unmodifiableSortedMultiset2 = new UnmodifiableSortedMultiset<>(mo8280delegate().mo7833descendingMultiset());
            unmodifiableSortedMultiset2.descendingMultiset = this;
            this.descendingMultiset = unmodifiableSortedMultiset2;
            return unmodifiableSortedMultiset2;
        }
        return unmodifiableSortedMultiset;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return mo8280delegate().firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: headMultiset */
    public SortedMultiset<E> mo8006headMultiset(E e, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(mo8280delegate().mo8006headMultiset(e, boundType));
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return mo8280delegate().lastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: subMultiset */
    public SortedMultiset<E> mo7837subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return Multisets.unmodifiableSortedMultiset(mo8280delegate().mo7837subMultiset(e, boundType, e2, boundType2));
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: tailMultiset */
    public SortedMultiset<E> mo8007tailMultiset(E e, BoundType boundType) {
        return Multisets.unmodifiableSortedMultiset(mo8280delegate().mo8007tailMultiset(e, boundType));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset
    /* renamed from: createElementSet */
    public NavigableSet<E> mo8124createElementSet() {
        return Sets.unmodifiableNavigableSet(mo8280delegate().mo8127elementSet());
    }

    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    /* renamed from: elementSet */
    public NavigableSet<E> mo8127elementSet() {
        return (NavigableSet) super.mo8127elementSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.Multisets.UnmodifiableMultiset, com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public SortedMultiset<E> mo8280delegate() {
        return (SortedMultiset) super.mo8280delegate();
    }
}

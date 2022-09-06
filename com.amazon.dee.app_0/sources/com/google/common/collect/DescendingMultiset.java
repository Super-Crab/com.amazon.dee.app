package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
abstract class DescendingMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {
    @NullableDecl
    private transient Comparator<? super E> comparator;
    @NullableDecl
    private transient NavigableSet<E> elementSet;
    @NullableDecl
    private transient Set<Multiset.Entry<E>> entrySet;

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        Comparator<? super E> comparator = this.comparator;
        if (comparator == null) {
            Ordering reverse = Ordering.from(forwardMultiset().comparator()).reverse();
            this.comparator = reverse;
            return reverse;
        }
        return comparator;
    }

    Set<Multiset.Entry<E>> createEntrySet() {
        return new Multisets.EntrySet<E>() { // from class: com.google.common.collect.DescendingMultiset.1EntrySetImpl
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<Multiset.Entry<E>> iterator() {
                return DescendingMultiset.this.entryIterator();
            }

            @Override // com.google.common.collect.Multisets.EntrySet
            /* renamed from: multiset */
            Multiset<E> mo7596multiset() {
                return DescendingMultiset.this;
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
            public int size() {
                return DescendingMultiset.this.forwardMultiset().mo7764entrySet().size();
            }
        };
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: descendingMultiset */
    public SortedMultiset<E> mo7833descendingMultiset() {
        return forwardMultiset();
    }

    abstract Iterator<Multiset.Entry<E>> entryIterator();

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    /* renamed from: entrySet */
    public Set<Multiset.Entry<E>> mo7764entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set == null) {
            Set<Multiset.Entry<E>> createEntrySet = createEntrySet();
            this.entrySet = createEntrySet;
            return createEntrySet;
        }
        return set;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return forwardMultiset().lastEntry();
    }

    abstract SortedMultiset<E> forwardMultiset();

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: headMultiset */
    public SortedMultiset<E> mo8006headMultiset(E e, BoundType boundType) {
        return forwardMultiset().mo8007tailMultiset(e, boundType).mo7833descendingMultiset();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator<E> iterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return forwardMultiset().firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        return forwardMultiset().pollLastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        return forwardMultiset().pollFirstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: subMultiset */
    public SortedMultiset<E> mo7837subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return forwardMultiset().mo7837subMultiset(e2, boundType2, e, boundType).mo7833descendingMultiset();
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: tailMultiset */
    public SortedMultiset<E> mo8007tailMultiset(E e, BoundType boundType) {
        return forwardMultiset().mo8006headMultiset(e, boundType).mo7833descendingMultiset();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public Object[] toArray() {
        return standardToArray();
    }

    @Override // com.google.common.collect.ForwardingObject
    public String toString() {
        return mo7764entrySet().toString();
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Set
    public <T> T[] toArray(T[] tArr) {
        return (T[]) standardToArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public Multiset<E> mo8280delegate() {
        return forwardMultiset();
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    /* renamed from: elementSet */
    public NavigableSet<E> mo8127elementSet() {
        NavigableSet<E> navigableSet = this.elementSet;
        if (navigableSet == null) {
            SortedMultisets.NavigableElementSet navigableElementSet = new SortedMultisets.NavigableElementSet(this);
            this.elementSet = navigableElementSet;
            return navigableElementSet;
        }
        return navigableSet;
    }
}

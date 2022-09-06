package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
abstract class AbstractSortedMultiset<E> extends AbstractMultiset<E> implements SortedMultiset<E> {
    @GwtTransient
    final Comparator<? super E> comparator;
    @NullableDecl
    private transient SortedMultiset<E> descendingMultiset;

    AbstractSortedMultiset() {
        this(Ordering.natural());
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return this.comparator;
    }

    SortedMultiset<E> createDescendingMultiset() {
        return new DescendingMultiset<E>() { // from class: com.google.common.collect.AbstractSortedMultiset.1DescendingMultisetImpl
            @Override // com.google.common.collect.DescendingMultiset
            Iterator<Multiset.Entry<E>> entryIterator() {
                return AbstractSortedMultiset.this.descendingEntryIterator();
            }

            @Override // com.google.common.collect.DescendingMultiset
            SortedMultiset<E> forwardMultiset() {
                return AbstractSortedMultiset.this;
            }

            @Override // com.google.common.collect.DescendingMultiset, com.google.common.collect.ForwardingCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public Iterator<E> iterator() {
                return AbstractSortedMultiset.this.descendingIterator();
            }
        };
    }

    abstract Iterator<Multiset.Entry<E>> descendingEntryIterator();

    Iterator<E> descendingIterator() {
        return Multisets.iteratorImpl(mo7833descendingMultiset());
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: descendingMultiset */
    public SortedMultiset<E> mo7833descendingMultiset() {
        SortedMultiset<E> sortedMultiset = this.descendingMultiset;
        if (sortedMultiset == null) {
            SortedMultiset<E> createDescendingMultiset = createDescendingMultiset();
            this.descendingMultiset = createDescendingMultiset;
            return createDescendingMultiset;
        }
        return sortedMultiset;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            return entryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        Iterator<Multiset.Entry<E>> descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            return descendingEntryIterator.next();
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        Iterator<Multiset.Entry<E>> entryIterator = entryIterator();
        if (entryIterator.hasNext()) {
            Multiset.Entry<E> next = entryIterator.next();
            Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.mo7624getElement(), next.getCount());
            entryIterator.remove();
            return immutableEntry;
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        Iterator<Multiset.Entry<E>> descendingEntryIterator = descendingEntryIterator();
        if (descendingEntryIterator.hasNext()) {
            Multiset.Entry<E> next = descendingEntryIterator.next();
            Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.mo7624getElement(), next.getCount());
            descendingEntryIterator.remove();
            return immutableEntry;
        }
        return null;
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: subMultiset */
    public SortedMultiset<E> mo7837subMultiset(@NullableDecl E e, BoundType boundType, @NullableDecl E e2, BoundType boundType2) {
        Preconditions.checkNotNull(boundType);
        Preconditions.checkNotNull(boundType2);
        return mo8007tailMultiset(e, boundType).mo8006headMultiset(e2, boundType2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractSortedMultiset(Comparator<? super E> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.common.collect.AbstractMultiset
    /* renamed from: createElementSet */
    public NavigableSet<E> mo7573createElementSet() {
        return new SortedMultisets.NavigableElementSet(this);
    }

    @Override // com.google.common.collect.AbstractMultiset, com.google.common.collect.Multiset
    /* renamed from: elementSet */
    public NavigableSet<E> mo8127elementSet() {
        return (NavigableSet) super.mo8127elementSet();
    }
}

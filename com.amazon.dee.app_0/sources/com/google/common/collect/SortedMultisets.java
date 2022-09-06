package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.j2objc.annotations.Weak;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.SortedSet;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
final class SortedMultisets {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static class ElementSet<E> extends Multisets.ElementSet<E> implements SortedSet<E> {
        @Weak
        private final SortedMultiset<E> multiset;

        ElementSet(SortedMultiset<E> sortedMultiset) {
            this.multiset = sortedMultiset;
        }

        @Override // java.util.SortedSet
        public Comparator<? super E> comparator() {
            return mo8035multiset().comparator();
        }

        @Override // java.util.SortedSet
        public E first() {
            return (E) SortedMultisets.getElementOrThrow(mo8035multiset().firstEntry());
        }

        @Override // java.util.SortedSet
        public SortedSet<E> headSet(E e) {
            return mo8035multiset().mo8006headMultiset(e, BoundType.OPEN).mo8127elementSet();
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return Multisets.elementIterator(mo8035multiset().mo7764entrySet().iterator());
        }

        @Override // java.util.SortedSet
        public E last() {
            return (E) SortedMultisets.getElementOrThrow(mo8035multiset().lastEntry());
        }

        @Override // java.util.SortedSet
        public SortedSet<E> subSet(E e, E e2) {
            return mo8035multiset().mo7837subMultiset(e, BoundType.CLOSED, e2, BoundType.OPEN).mo8127elementSet();
        }

        @Override // java.util.SortedSet
        public SortedSet<E> tailSet(E e) {
            return mo8035multiset().mo8007tailMultiset(e, BoundType.CLOSED).mo8127elementSet();
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.google.common.collect.Multisets.ElementSet
        /* renamed from: multiset  reason: collision with other method in class */
        public final SortedMultiset<E> mo8035multiset() {
            return this.multiset;
        }
    }

    @GwtIncompatible
    /* loaded from: classes3.dex */
    static class NavigableElementSet<E> extends ElementSet<E> implements NavigableSet<E> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public NavigableElementSet(SortedMultiset<E> sortedMultiset) {
            super(sortedMultiset);
        }

        @Override // java.util.NavigableSet
        public E ceiling(E e) {
            return (E) SortedMultisets.getElementOrNull(mo8035multiset().mo8007tailMultiset(e, BoundType.CLOSED).firstEntry());
        }

        @Override // java.util.NavigableSet
        public Iterator<E> descendingIterator() {
            return descendingSet().iterator();
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> descendingSet() {
            return new NavigableElementSet(mo8035multiset().mo7833descendingMultiset());
        }

        @Override // java.util.NavigableSet
        public E floor(E e) {
            return (E) SortedMultisets.getElementOrNull(mo8035multiset().mo8006headMultiset(e, BoundType.CLOSED).lastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> headSet(E e, boolean z) {
            return new NavigableElementSet(mo8035multiset().mo8006headMultiset(e, BoundType.forBoolean(z)));
        }

        @Override // java.util.NavigableSet
        public E higher(E e) {
            return (E) SortedMultisets.getElementOrNull(mo8035multiset().mo8007tailMultiset(e, BoundType.OPEN).firstEntry());
        }

        @Override // java.util.NavigableSet
        public E lower(E e) {
            return (E) SortedMultisets.getElementOrNull(mo8035multiset().mo8006headMultiset(e, BoundType.OPEN).lastEntry());
        }

        @Override // java.util.NavigableSet
        public E pollFirst() {
            return (E) SortedMultisets.getElementOrNull(mo8035multiset().pollFirstEntry());
        }

        @Override // java.util.NavigableSet
        public E pollLast() {
            return (E) SortedMultisets.getElementOrNull(mo8035multiset().pollLastEntry());
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
            return new NavigableElementSet(mo8035multiset().mo7837subMultiset(e, BoundType.forBoolean(z), e2, BoundType.forBoolean(z2)));
        }

        @Override // java.util.NavigableSet
        public NavigableSet<E> tailSet(E e, boolean z) {
            return new NavigableElementSet(mo8035multiset().mo8007tailMultiset(e, BoundType.forBoolean(z)));
        }
    }

    private SortedMultisets() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> E getElementOrNull(@NullableDecl Multiset.Entry<E> entry) {
        if (entry == null) {
            return null;
        }
        return entry.mo7624getElement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <E> E getElementOrThrow(Multiset.Entry<E> entry) {
        if (entry != null) {
            return entry.mo7624getElement();
        }
        throw new NoSuchElementException();
    }
}

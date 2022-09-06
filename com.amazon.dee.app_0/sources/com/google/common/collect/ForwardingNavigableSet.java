package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class ForwardingNavigableSet<E> extends ForwardingSortedSet<E> implements NavigableSet<E> {

    @Beta
    /* loaded from: classes3.dex */
    protected class StandardDescendingSet extends Sets.DescendingSet<E> {
        public StandardDescendingSet(ForwardingNavigableSet forwardingNavigableSet) {
            super(forwardingNavigableSet);
        }
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e) {
        return mo8280delegate().ceiling(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet, com.google.common.collect.ForwardingSet, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract NavigableSet<E> mo8280delegate();

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return mo8280delegate().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return mo8280delegate().descendingSet();
    }

    @Override // java.util.NavigableSet
    public E floor(E e) {
        return mo8280delegate().floor(e);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E e, boolean z) {
        return mo8280delegate().headSet(e, z);
    }

    @Override // java.util.NavigableSet
    public E higher(E e) {
        return mo8280delegate().higher(e);
    }

    @Override // java.util.NavigableSet
    public E lower(E e) {
        return mo8280delegate().lower(e);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return mo8280delegate().pollFirst();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return mo8280delegate().pollLast();
    }

    protected E standardCeiling(E e) {
        return (E) Iterators.getNext(tailSet(e, true).iterator(), null);
    }

    protected E standardFirst() {
        return iterator().next();
    }

    protected E standardFloor(E e) {
        return (E) Iterators.getNext(headSet(e, true).descendingIterator(), null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SortedSet<E> standardHeadSet(E e) {
        return headSet(e, false);
    }

    protected E standardHigher(E e) {
        return (E) Iterators.getNext(tailSet(e, false).iterator(), null);
    }

    protected E standardLast() {
        return descendingIterator().next();
    }

    protected E standardLower(E e) {
        return (E) Iterators.getNext(headSet(e, false).descendingIterator(), null);
    }

    protected E standardPollFirst() {
        return (E) Iterators.pollNext(iterator());
    }

    protected E standardPollLast() {
        return (E) Iterators.pollNext(descendingIterator());
    }

    @Beta
    protected NavigableSet<E> standardSubSet(E e, boolean z, E e2, boolean z2) {
        return tailSet(e, z).headSet(e2, z2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SortedSet<E> standardTailSet(E e) {
        return tailSet(e, true);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return mo8280delegate().subSet(e, z, e2, z2);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E e, boolean z) {
        return mo8280delegate().tailSet(e, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingSortedSet
    public SortedSet<E> standardSubSet(E e, E e2) {
        return subSet(e, true, e2, false);
    }
}

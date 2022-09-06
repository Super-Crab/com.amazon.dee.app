package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.Predicate;
/* loaded from: classes4.dex */
public class PredicatedNavigableSet<E> extends PredicatedSortedSet<E> implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528;

    protected PredicatedNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        super(navigableSet, predicate);
    }

    public static <E> PredicatedNavigableSet<E> predicatedNavigableSet(NavigableSet<E> navigableSet, Predicate<? super E> predicate) {
        return new PredicatedNavigableSet<>(navigableSet, predicate);
    }

    @Override // java.util.NavigableSet
    public E ceiling(E e) {
        return mo12761decorated().ceiling(e);
    }

    @Override // java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return mo12761decorated().descendingIterator();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return predicatedNavigableSet(mo12761decorated().descendingSet(), this.predicate);
    }

    @Override // java.util.NavigableSet
    public E floor(E e) {
        return mo12761decorated().floor(e);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E e, boolean z) {
        return predicatedNavigableSet(mo12761decorated().headSet(e, z), this.predicate);
    }

    @Override // java.util.NavigableSet
    public E higher(E e) {
        return mo12761decorated().higher(e);
    }

    @Override // java.util.NavigableSet
    public E lower(E e) {
        return mo12761decorated().lower(e);
    }

    @Override // java.util.NavigableSet
    public E pollFirst() {
        return mo12761decorated().pollFirst();
    }

    @Override // java.util.NavigableSet
    public E pollLast() {
        return mo12761decorated().pollLast();
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return predicatedNavigableSet(mo12761decorated().subSet(e, z, e2, z2), this.predicate);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E e, boolean z) {
        return predicatedNavigableSet(mo12761decorated().tailSet(e, z), this.predicate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.set.PredicatedSortedSet, org.apache.commons.collections4.set.PredicatedSet, org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public NavigableSet<E> mo12761decorated() {
        return (NavigableSet) super.mo12761decorated();
    }
}

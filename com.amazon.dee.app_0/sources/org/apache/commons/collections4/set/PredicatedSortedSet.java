package org.apache.commons.collections4.set;

import java.util.Comparator;
import java.util.SortedSet;
import org.apache.commons.collections4.Predicate;
/* loaded from: classes4.dex */
public class PredicatedSortedSet<E> extends PredicatedSet<E> implements SortedSet<E> {
    private static final long serialVersionUID = -9110948148132275052L;

    /* JADX INFO: Access modifiers changed from: protected */
    public PredicatedSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        super(sortedSet, predicate);
    }

    public static <E> PredicatedSortedSet<E> predicatedSortedSet(SortedSet<E> sortedSet, Predicate<? super E> predicate) {
        return new PredicatedSortedSet<>(sortedSet, predicate);
    }

    @Override // java.util.SortedSet
    public Comparator<? super E> comparator() {
        return mo12761decorated().comparator();
    }

    @Override // java.util.SortedSet
    public E first() {
        return mo12761decorated().first();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> headSet(E e) {
        return new PredicatedSortedSet(mo12761decorated().headSet(e), this.predicate);
    }

    @Override // java.util.SortedSet
    public E last() {
        return mo12761decorated().last();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(E e, E e2) {
        return new PredicatedSortedSet(mo12761decorated().subSet(e, e2), this.predicate);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(E e) {
        return new PredicatedSortedSet(mo12761decorated().tailSet(e), this.predicate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.set.PredicatedSet, org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated */
    public SortedSet<E> mo12761decorated() {
        return (SortedSet) super.mo12761decorated();
    }
}

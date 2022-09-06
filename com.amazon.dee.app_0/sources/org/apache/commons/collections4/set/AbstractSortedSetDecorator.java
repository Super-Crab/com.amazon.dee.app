package org.apache.commons.collections4.set;

import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
/* loaded from: classes4.dex */
public abstract class AbstractSortedSetDecorator<E> extends AbstractSetDecorator<E> implements SortedSet<E> {
    private static final long serialVersionUID = -3462240946294214398L;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSortedSetDecorator() {
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
        return mo12761decorated().headSet(e);
    }

    @Override // java.util.SortedSet
    public E last() {
        return mo12761decorated().last();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(E e, E e2) {
        return mo12761decorated().subSet(e, e2);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(E e) {
        return mo12761decorated().tailSet(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSortedSetDecorator(Set<E> set) {
        super(set);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.set.AbstractSetDecorator, org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated */
    public SortedSet<E> mo12761decorated() {
        return (SortedSet) super.mo12761decorated();
    }
}

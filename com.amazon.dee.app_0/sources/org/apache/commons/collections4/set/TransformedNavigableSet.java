package org.apache.commons.collections4.set;

import java.util.Iterator;
import java.util.NavigableSet;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class TransformedNavigableSet<E> extends TransformedSortedSet<E> implements NavigableSet<E> {
    private static final long serialVersionUID = 20150528;

    protected TransformedNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        super(navigableSet, transformer);
    }

    public static <E> TransformedNavigableSet<E> transformedNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        TransformedNavigableSet<E> transformedNavigableSet = new TransformedNavigableSet<>(navigableSet, transformer);
        if (navigableSet.size() > 0) {
            Object[] array = navigableSet.toArray();
            navigableSet.clear();
            for (Object obj : array) {
                transformedNavigableSet.mo12761decorated().add(transformer.mo12738transform(obj));
            }
        }
        return transformedNavigableSet;
    }

    public static <E> TransformedNavigableSet<E> transformingNavigableSet(NavigableSet<E> navigableSet, Transformer<? super E, ? extends E> transformer) {
        return new TransformedNavigableSet<>(navigableSet, transformer);
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
        return transformingNavigableSet(mo12761decorated().descendingSet(), this.transformer);
    }

    @Override // java.util.NavigableSet
    public E floor(E e) {
        return mo12761decorated().floor(e);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> headSet(E e, boolean z) {
        return transformingNavigableSet(mo12761decorated().headSet(e, z), this.transformer);
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
        return transformingNavigableSet(mo12761decorated().subSet(e, z, e2, z2), this.transformer);
    }

    @Override // java.util.NavigableSet
    public NavigableSet<E> tailSet(E e, boolean z) {
        return transformingNavigableSet(mo12761decorated().tailSet(e, z), this.transformer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public NavigableSet<E> mo12761decorated() {
        return (NavigableSet) super.mo12761decorated();
    }
}

package org.apache.commons.collections4.set;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
/* loaded from: classes4.dex */
public final class UnmodifiableNavigableSet<E> extends AbstractNavigableSetDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = 20150528;

    private UnmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        super(navigableSet);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setCollection((Collection) objectInputStream.readObject());
    }

    public static <E> NavigableSet<E> unmodifiableNavigableSet(NavigableSet<E> navigableSet) {
        return navigableSet instanceof Unmodifiable ? navigableSet : new UnmodifiableNavigableSet(navigableSet);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(mo12761decorated());
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.set.AbstractNavigableSetDecorator, java.util.NavigableSet
    public Iterator<E> descendingIterator() {
        return UnmodifiableIterator.unmodifiableIterator(mo12761decorated().descendingIterator());
    }

    @Override // org.apache.commons.collections4.set.AbstractNavigableSetDecorator, java.util.NavigableSet
    public NavigableSet<E> descendingSet() {
        return unmodifiableNavigableSet(mo12761decorated().descendingSet());
    }

    @Override // org.apache.commons.collections4.set.AbstractSortedSetDecorator, java.util.SortedSet
    public SortedSet<E> headSet(E e) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(mo12761decorated().headSet(e));
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return UnmodifiableIterator.unmodifiableIterator(mo12761decorated().iterator());
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.set.AbstractSortedSetDecorator, java.util.SortedSet
    public SortedSet<E> subSet(E e, E e2) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(mo12761decorated().subSet(e, e2));
    }

    @Override // org.apache.commons.collections4.set.AbstractSortedSetDecorator, java.util.SortedSet
    public SortedSet<E> tailSet(E e) {
        return UnmodifiableSortedSet.unmodifiableSortedSet(mo12761decorated().tailSet(e));
    }

    @Override // org.apache.commons.collections4.set.AbstractNavigableSetDecorator, java.util.NavigableSet
    public NavigableSet<E> headSet(E e, boolean z) {
        return unmodifiableNavigableSet(mo12761decorated().headSet(e, z));
    }

    @Override // org.apache.commons.collections4.set.AbstractNavigableSetDecorator, java.util.NavigableSet
    public NavigableSet<E> subSet(E e, boolean z, E e2, boolean z2) {
        return unmodifiableNavigableSet(mo12761decorated().subSet(e, z, e2, z2));
    }

    @Override // org.apache.commons.collections4.set.AbstractNavigableSetDecorator, java.util.NavigableSet
    public NavigableSet<E> tailSet(E e, boolean z) {
        return unmodifiableNavigableSet(mo12761decorated().tailSet(e, z));
    }
}

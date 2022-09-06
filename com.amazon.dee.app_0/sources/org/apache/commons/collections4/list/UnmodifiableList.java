package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
/* loaded from: classes4.dex */
public final class UnmodifiableList<E> extends AbstractSerializableListDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = 6595182819922443652L;

    public UnmodifiableList(List<? extends E> list) {
        super(list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> List<E> unmodifiableList(List<? extends E> list) {
        return list instanceof Unmodifiable ? list : new UnmodifiableList(list);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(Object obj) {
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

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return UnmodifiableIterator.unmodifiableIterator(mo12761decorated().iterator());
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public ListIterator<E> listIterator() {
        return UnmodifiableListIterator.umodifiableListIterator(mo12761decorated().listIterator());
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

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E set(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public List<E> subList(int i, int i2) {
        return new UnmodifiableList(mo12761decorated().subList(i, i2));
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public void add(int i, E e) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public ListIterator<E> listIterator(int i) {
        return UnmodifiableListIterator.umodifiableListIterator(mo12761decorated().listIterator(i));
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E remove(int i) {
        throw new UnsupportedOperationException();
    }
}

package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public final class UnmodifiableBag<E> extends AbstractBagDecorator<E> implements Unmodifiable {
    private static final long serialVersionUID = -1873799975157099624L;

    private UnmodifiableBag(Bag<? extends E> bag) {
        super(bag);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setCollection((Collection) objectInputStream.readObject());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> Bag<E> unmodifiableBag(Bag<? extends E> bag) {
        return bag instanceof Unmodifiable ? bag : new UnmodifiableBag(bag);
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

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return UnmodifiableIterator.unmodifiableIterator(mo12761decorated().mo12756iterator());
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

    @Override // org.apache.commons.collections4.bag.AbstractBagDecorator, org.apache.commons.collections4.Bag
    public Set<E> uniqueSet() {
        return UnmodifiableSet.unmodifiableSet(mo12761decorated().uniqueSet());
    }

    @Override // org.apache.commons.collections4.bag.AbstractBagDecorator, org.apache.commons.collections4.Bag
    public boolean add(E e, int i) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.bag.AbstractBagDecorator, org.apache.commons.collections4.Bag
    public boolean remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }
}

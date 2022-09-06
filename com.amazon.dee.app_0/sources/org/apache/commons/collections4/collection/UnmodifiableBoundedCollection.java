package org.apache.commons.collections4.collection;

import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
/* loaded from: classes4.dex */
public final class UnmodifiableBoundedCollection<E> extends AbstractCollectionDecorator<E> implements BoundedCollection<E>, Unmodifiable {
    private static final long serialVersionUID = -7112672385450340330L;

    private UnmodifiableBoundedCollection(BoundedCollection<? extends E> boundedCollection) {
        super(boundedCollection);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> BoundedCollection<E> unmodifiableBoundedCollection(BoundedCollection<? extends E> boundedCollection) {
        return boundedCollection instanceof Unmodifiable ? boundedCollection : new UnmodifiableBoundedCollection(boundedCollection);
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

    @Override // org.apache.commons.collections4.BoundedCollection
    public boolean isFull() {
        return mo12761decorated().isFull();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return UnmodifiableIterator.unmodifiableIterator(mo12761decorated().iterator());
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public int maxSize() {
        return mo12761decorated().maxSize();
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

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public BoundedCollection<E> mo12761decorated() {
        return (BoundedCollection) super.mo12761decorated();
    }

    public static <E> BoundedCollection<E> unmodifiableBoundedCollection(Collection<? extends E> collection) {
        if (collection != null) {
            for (int i = 0; i < 1000 && !(collection instanceof BoundedCollection); i++) {
                if (collection instanceof AbstractCollectionDecorator) {
                    collection = ((AbstractCollectionDecorator) collection).mo12761decorated();
                } else if (collection instanceof SynchronizedCollection) {
                    collection = ((SynchronizedCollection) collection).mo12751decorated();
                }
            }
            if (collection instanceof BoundedCollection) {
                return new UnmodifiableBoundedCollection((BoundedCollection) collection);
            }
            throw new IllegalArgumentException("Collection is not a bounded collection.");
        }
        throw new NullPointerException("Collection must not be null.");
    }
}

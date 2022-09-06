package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes4.dex */
public abstract class AbstractCollectionDecorator<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 6249888059822088500L;
    private Collection<E> collection;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCollectionDecorator() {
    }

    @Override // java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E e) {
        return mo12761decorated().add(e);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return mo12761decorated().addAll(collection);
    }

    @Override // java.util.Collection
    public void clear() {
        mo12761decorated().clear();
    }

    @Override // java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return mo12761decorated().contains(obj);
    }

    @Override // java.util.Collection, org.apache.commons.collections4.Bag
    public boolean containsAll(Collection<?> collection) {
        return mo12761decorated().containsAll(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: decorated */
    public Collection<E> mo12761decorated() {
        return this.collection;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return mo12761decorated().isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return mo12761decorated().iterator();
    }

    @Override // java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object obj) {
        return mo12761decorated().remove(obj);
    }

    @Override // java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> collection) {
        return mo12761decorated().removeAll(collection);
    }

    @Override // java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> collection) {
        return mo12761decorated().retainAll(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setCollection(Collection<E> collection) {
        this.collection = collection;
    }

    @Override // java.util.Collection
    public int size() {
        return mo12761decorated().size();
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        return mo12761decorated().toArray();
    }

    public String toString() {
        return mo12761decorated().toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractCollectionDecorator(Collection<E> collection) {
        if (collection != null) {
            this.collection = collection;
            return;
        }
        throw new NullPointerException("Collection must not be null.");
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) mo12761decorated().toArray(tArr);
    }
}

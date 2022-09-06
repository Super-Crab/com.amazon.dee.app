package org.apache.commons.collections4.collection;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes4.dex */
public class SynchronizedCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 2412805092710877986L;
    private final Collection<E> collection;
    protected final Object lock;

    /* JADX INFO: Access modifiers changed from: protected */
    public SynchronizedCollection(Collection<E> collection) {
        if (collection != null) {
            this.collection = collection;
            this.lock = this;
            return;
        }
        throw new NullPointerException("Collection must not be null.");
    }

    public static <T> SynchronizedCollection<T> synchronizedCollection(Collection<T> collection) {
        return new SynchronizedCollection<>(collection);
    }

    @Override // java.util.Collection
    public boolean add(E e) {
        boolean add;
        synchronized (this.lock) {
            add = mo12751decorated().add(e);
        }
        return add;
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        boolean addAll;
        synchronized (this.lock) {
            addAll = mo12751decorated().addAll(collection);
        }
        return addAll;
    }

    @Override // java.util.Collection
    public void clear() {
        synchronized (this.lock) {
            mo12751decorated().clear();
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        boolean contains;
        synchronized (this.lock) {
            contains = mo12751decorated().contains(obj);
        }
        return contains;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        boolean containsAll;
        synchronized (this.lock) {
            containsAll = mo12751decorated().containsAll(collection);
        }
        return containsAll;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: decorated */
    public Collection<E> mo12751decorated() {
        return this.collection;
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        synchronized (this.lock) {
            boolean z = true;
            try {
                if (obj == this) {
                    return true;
                }
                if (obj != this && !mo12751decorated().equals(obj)) {
                    z = false;
                }
                return z;
            } finally {
            }
        }
    }

    @Override // java.util.Collection
    public int hashCode() {
        int hashCode;
        synchronized (this.lock) {
            hashCode = mo12751decorated().hashCode();
        }
        return hashCode;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        boolean isEmpty;
        synchronized (this.lock) {
            isEmpty = mo12751decorated().isEmpty();
        }
        return isEmpty;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return mo12751decorated().iterator();
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        boolean remove;
        synchronized (this.lock) {
            remove = mo12751decorated().remove(obj);
        }
        return remove;
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean removeAll;
        synchronized (this.lock) {
            removeAll = mo12751decorated().removeAll(collection);
        }
        return removeAll;
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean retainAll;
        synchronized (this.lock) {
            retainAll = mo12751decorated().retainAll(collection);
        }
        return retainAll;
    }

    @Override // java.util.Collection
    public int size() {
        int size;
        synchronized (this.lock) {
            size = mo12751decorated().size();
        }
        return size;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] array;
        synchronized (this.lock) {
            array = mo12751decorated().toArray();
        }
        return array;
    }

    public String toString() {
        String obj;
        synchronized (this.lock) {
            obj = mo12751decorated().toString();
        }
        return obj;
    }

    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        T[] tArr2;
        synchronized (this.lock) {
            tArr2 = (T[]) mo12751decorated().toArray(tArr);
        }
        return tArr2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SynchronizedCollection(Collection<E> collection, Object obj) {
        if (collection != null) {
            if (obj != null) {
                this.collection = collection;
                this.lock = obj;
                return;
            }
            throw new NullPointerException("Lock must not be null.");
        }
        throw new NullPointerException("Collection must not be null.");
    }
}

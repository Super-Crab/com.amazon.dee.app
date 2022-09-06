package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.collection.SynchronizedCollection;
/* loaded from: classes4.dex */
public class SynchronizedMultiSet<E> extends SynchronizedCollection<E> implements MultiSet<E> {
    private static final long serialVersionUID = 20150629;

    /* loaded from: classes4.dex */
    static class SynchronizedSet<T> extends SynchronizedCollection<T> implements Set<T> {
        private static final long serialVersionUID = 20150629;

        SynchronizedSet(Set<T> set, Object obj) {
            super(set, obj);
        }
    }

    protected SynchronizedMultiSet(MultiSet<E> multiSet) {
        super(multiSet);
    }

    public static <E> SynchronizedMultiSet<E> synchronizedMultiSet(MultiSet<E> multiSet) {
        return new SynchronizedMultiSet<>(multiSet);
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int add(E e, int i) {
        int add;
        synchronized (this.lock) {
            add = mo12751decorated().add(e, i);
        }
        return add;
    }

    @Override // org.apache.commons.collections4.MultiSet
    public Set<MultiSet.Entry<E>> entrySet() {
        SynchronizedSet synchronizedSet;
        synchronized (this.lock) {
            synchronizedSet = new SynchronizedSet(mo12751decorated().entrySet(), this.lock);
        }
        return synchronizedSet;
    }

    @Override // org.apache.commons.collections4.collection.SynchronizedCollection, java.util.Collection
    public boolean equals(Object obj) {
        boolean equals;
        if (obj == this) {
            return true;
        }
        synchronized (this.lock) {
            equals = mo12751decorated().equals(obj);
        }
        return equals;
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int getCount(Object obj) {
        int count;
        synchronized (this.lock) {
            count = mo12751decorated().getCount(obj);
        }
        return count;
    }

    @Override // org.apache.commons.collections4.collection.SynchronizedCollection, java.util.Collection
    public int hashCode() {
        int hashCode;
        synchronized (this.lock) {
            hashCode = mo12751decorated().hashCode();
        }
        return hashCode;
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int remove(Object obj, int i) {
        int remove;
        synchronized (this.lock) {
            remove = mo12751decorated().remove(obj, i);
        }
        return remove;
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int setCount(E e, int i) {
        int count;
        synchronized (this.lock) {
            count = mo12751decorated().setCount(e, i);
        }
        return count;
    }

    @Override // org.apache.commons.collections4.MultiSet
    public Set<E> uniqueSet() {
        SynchronizedSet synchronizedSet;
        synchronized (this.lock) {
            synchronizedSet = new SynchronizedSet(mo12751decorated().uniqueSet(), this.lock);
        }
        return synchronizedSet;
    }

    protected SynchronizedMultiSet(MultiSet<E> multiSet, Object obj) {
        super(multiSet, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.SynchronizedCollection
    /* renamed from: decorated  reason: collision with other method in class */
    public MultiSet<E> mo12751decorated() {
        return (MultiSet) super.mo12751decorated();
    }
}

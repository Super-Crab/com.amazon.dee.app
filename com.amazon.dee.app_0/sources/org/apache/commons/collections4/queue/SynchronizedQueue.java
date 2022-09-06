package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.collection.SynchronizedCollection;
/* loaded from: classes4.dex */
public class SynchronizedQueue<E> extends SynchronizedCollection<E> implements Queue<E> {
    private static final long serialVersionUID = 1;

    protected SynchronizedQueue(Queue<E> queue) {
        super(queue);
    }

    public static <E> SynchronizedQueue<E> synchronizedQueue(Queue<E> queue) {
        return new SynchronizedQueue<>(queue);
    }

    @Override // java.util.Queue
    public E element() {
        E element;
        synchronized (this.lock) {
            element = mo12751decorated().element();
        }
        return element;
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

    @Override // org.apache.commons.collections4.collection.SynchronizedCollection, java.util.Collection
    public int hashCode() {
        int hashCode;
        synchronized (this.lock) {
            hashCode = mo12751decorated().hashCode();
        }
        return hashCode;
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        boolean offer;
        synchronized (this.lock) {
            offer = mo12751decorated().offer(e);
        }
        return offer;
    }

    @Override // java.util.Queue
    public E peek() {
        E peek;
        synchronized (this.lock) {
            peek = mo12751decorated().peek();
        }
        return peek;
    }

    @Override // java.util.Queue
    public E poll() {
        E poll;
        synchronized (this.lock) {
            poll = mo12751decorated().poll();
        }
        return poll;
    }

    @Override // java.util.Queue
    public E remove() {
        E remove;
        synchronized (this.lock) {
            remove = mo12751decorated().remove();
        }
        return remove;
    }

    protected SynchronizedQueue(Queue<E> queue, Object obj) {
        super(queue, obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.SynchronizedCollection
    /* renamed from: decorated  reason: collision with other method in class */
    public Queue<E> mo12751decorated() {
        return (Queue) super.mo12751decorated();
    }
}

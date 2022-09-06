package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
/* loaded from: classes4.dex */
public class PredicatedQueue<E> extends PredicatedCollection<E> implements Queue<E> {
    private static final long serialVersionUID = 2307609000539943581L;

    protected PredicatedQueue(Queue<E> queue, Predicate<? super E> predicate) {
        super(queue, predicate);
    }

    public static <E> PredicatedQueue<E> predicatedQueue(Queue<E> queue, Predicate<? super E> predicate) {
        return new PredicatedQueue<>(queue, predicate);
    }

    @Override // java.util.Queue
    public E element() {
        return mo12761decorated().element();
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        validate(e);
        return mo12761decorated().offer(e);
    }

    @Override // java.util.Queue
    public E peek() {
        return mo12761decorated().peek();
    }

    @Override // java.util.Queue
    public E poll() {
        return mo12761decorated().poll();
    }

    @Override // java.util.Queue
    public E remove() {
        return mo12761decorated().remove();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public Queue<E> mo12761decorated() {
        return (Queue) super.mo12761decorated();
    }
}

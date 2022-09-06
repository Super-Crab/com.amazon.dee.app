package org.apache.commons.collections4.queue;

import java.util.Queue;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
/* loaded from: classes4.dex */
public abstract class AbstractQueueDecorator<E> extends AbstractCollectionDecorator<E> implements Queue<E> {
    private static final long serialVersionUID = -2629815475789577029L;

    protected AbstractQueueDecorator() {
    }

    @Override // java.util.Queue
    public E element() {
        return mo12761decorated().element();
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
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
    public AbstractQueueDecorator(Queue<E> queue) {
        super(queue);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public Queue<E> mo12761decorated() {
        return (Queue) super.mo12761decorated();
    }
}

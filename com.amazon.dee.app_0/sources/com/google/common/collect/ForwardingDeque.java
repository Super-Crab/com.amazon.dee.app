package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Deque;
import java.util.Iterator;
@GwtIncompatible
/* loaded from: classes3.dex */
public abstract class ForwardingDeque<E> extends ForwardingQueue<E> implements Deque<E> {
    @Override // java.util.Deque
    public void addFirst(E e) {
        mo8280delegate().addFirst(e);
    }

    @Override // java.util.Deque
    public void addLast(E e) {
        mo8280delegate().addLast(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract Deque<E> mo8280delegate();

    @Override // java.util.Deque
    public Iterator<E> descendingIterator() {
        return mo8280delegate().descendingIterator();
    }

    @Override // java.util.Deque
    public E getFirst() {
        return mo8280delegate().getFirst();
    }

    @Override // java.util.Deque
    public E getLast() {
        return mo8280delegate().getLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean offerFirst(E e) {
        return mo8280delegate().offerFirst(e);
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean offerLast(E e) {
        return mo8280delegate().offerLast(e);
    }

    @Override // java.util.Deque
    public E peekFirst() {
        return mo8280delegate().peekFirst();
    }

    @Override // java.util.Deque
    public E peekLast() {
        return mo8280delegate().peekLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E pollFirst() {
        return mo8280delegate().pollFirst();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E pollLast() {
        return mo8280delegate().pollLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E pop() {
        return mo8280delegate().pop();
    }

    @Override // java.util.Deque
    public void push(E e) {
        mo8280delegate().push(e);
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E removeFirst() {
        return mo8280delegate().removeFirst();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean removeFirstOccurrence(Object obj) {
        return mo8280delegate().removeFirstOccurrence(obj);
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public E removeLast() {
        return mo8280delegate().removeLast();
    }

    @Override // java.util.Deque
    @CanIgnoreReturnValue
    public boolean removeLastOccurrence(Object obj) {
        return mo8280delegate().removeLastOccurrence(obj);
    }
}

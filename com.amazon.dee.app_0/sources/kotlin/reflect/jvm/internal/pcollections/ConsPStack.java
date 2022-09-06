package kotlin.reflect.jvm.internal.pcollections;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes4.dex */
final class ConsPStack<E> implements Iterable<E> {
    private static final ConsPStack<Object> EMPTY = new ConsPStack<>();
    final E first;
    final ConsPStack<E> rest;
    private final int size;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public static class Itr<E> implements Iterator<E> {
        private ConsPStack<E> next;

        public Itr(ConsPStack<E> consPStack) {
            this.next = consPStack;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return ((ConsPStack) this.next).size > 0;
        }

        @Override // java.util.Iterator
        public E next() {
            ConsPStack<E> consPStack = this.next;
            E e = consPStack.first;
            this.next = consPStack.rest;
            return e;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private ConsPStack() {
        this.size = 0;
        this.first = null;
        this.rest = null;
    }

    public static <E> ConsPStack<E> empty() {
        return (ConsPStack<E>) EMPTY;
    }

    private ConsPStack<E> minus(Object obj) {
        if (this.size == 0) {
            return this;
        }
        if (this.first.equals(obj)) {
            return this.rest;
        }
        ConsPStack<E> minus = this.rest.minus(obj);
        return minus == this.rest ? this : new ConsPStack<>(this.first, minus);
    }

    private ConsPStack<E> subList(int i) {
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException();
        }
        return i == 0 ? this : this.rest.subList(i - 1);
    }

    public E get(int i) {
        if (i >= 0 && i <= this.size) {
            try {
                return iterator(i).next();
            } catch (NoSuchElementException unused) {
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("Index: ", i));
            }
        }
        throw new IndexOutOfBoundsException();
    }

    @Override // java.lang.Iterable
    public Iterator<E> iterator() {
        return iterator(0);
    }

    public ConsPStack<E> plus(E e) {
        return new ConsPStack<>(e, this);
    }

    public int size() {
        return this.size;
    }

    private Iterator<E> iterator(int i) {
        return new Itr(subList(i));
    }

    private ConsPStack(E e, ConsPStack<E> consPStack) {
        this.first = e;
        this.rest = consPStack;
        this.size = consPStack.size + 1;
    }

    public ConsPStack<E> minus(int i) {
        return minus(get(i));
    }
}

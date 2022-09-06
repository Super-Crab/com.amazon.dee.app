package org.apache.commons.collections4.queue;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;
import org.apache.commons.collections4.BoundedCollection;
/* loaded from: classes4.dex */
public class CircularFifoQueue<E> extends AbstractCollection<E> implements Queue<E>, BoundedCollection<E>, Serializable {
    private static final long serialVersionUID = -8423413834657610406L;
    private transient E[] elements;
    private transient int end;
    private transient boolean full;
    private final int maxElements;
    private transient int start;

    public CircularFifoQueue() {
        this(32);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int decrement(int i) {
        int i2 = i - 1;
        return i2 < 0 ? this.maxElements - 1 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int increment(int i) {
        int i2 = i + 1;
        if (i2 >= this.maxElements) {
            return 0;
        }
        return i2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.elements = (E[]) new Object[this.maxElements];
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            this.elements[i] = objectInputStream.readObject();
        }
        this.start = 0;
        this.full = readInt == this.maxElements;
        if (this.full) {
            this.end = 0;
        } else {
            this.end = readInt;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator<E> it2 = iterator();
        while (it2.hasNext()) {
            objectOutputStream.writeObject(it2.next());
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Queue
    public boolean add(E e) {
        if (e != null) {
            if (isAtFullCapacity()) {
                remove();
            }
            E[] eArr = this.elements;
            int i = this.end;
            this.end = i + 1;
            eArr[i] = e;
            if (this.end >= this.maxElements) {
                this.end = 0;
            }
            if (this.end == this.start) {
                this.full = true;
            }
            return true;
        }
        throw new NullPointerException("Attempted to add null object to queue");
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public void clear() {
        this.full = false;
        this.start = 0;
        this.end = 0;
        Arrays.fill(this.elements, (Object) null);
    }

    @Override // java.util.Queue
    public E element() {
        if (!isEmpty()) {
            return peek();
        }
        throw new NoSuchElementException("queue is empty");
    }

    public E get(int i) {
        int size = size();
        if (i >= 0 && i < size) {
            return this.elements[(this.start + i) % this.maxElements];
        }
        throw new NoSuchElementException(String.format("The specified index (%1$d) is outside the available range [0, %2$d)", Integer.valueOf(i), Integer.valueOf(size)));
    }

    public boolean isAtFullCapacity() {
        return size() == this.maxElements;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public boolean isFull() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return new Iterator<E>() { // from class: org.apache.commons.collections4.queue.CircularFifoQueue.1
            private int index;
            private boolean isFirst;
            private int lastReturnedIndex = -1;

            {
                this.index = CircularFifoQueue.this.start;
                this.isFirst = CircularFifoQueue.this.full;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.isFirst || this.index != CircularFifoQueue.this.end;
            }

            @Override // java.util.Iterator
            public E next() {
                if (hasNext()) {
                    this.isFirst = false;
                    int i = this.index;
                    this.lastReturnedIndex = i;
                    this.index = CircularFifoQueue.this.increment(i);
                    return (E) CircularFifoQueue.this.elements[this.lastReturnedIndex];
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public void remove() {
                int i = this.lastReturnedIndex;
                if (i != -1) {
                    if (i == CircularFifoQueue.this.start) {
                        CircularFifoQueue.this.remove();
                        this.lastReturnedIndex = -1;
                        return;
                    }
                    int i2 = this.lastReturnedIndex + 1;
                    if (CircularFifoQueue.this.start >= this.lastReturnedIndex || i2 >= CircularFifoQueue.this.end) {
                        while (i2 != CircularFifoQueue.this.end) {
                            if (i2 >= CircularFifoQueue.this.maxElements) {
                                CircularFifoQueue.this.elements[i2 - 1] = CircularFifoQueue.this.elements[0];
                                i2 = 0;
                            } else {
                                CircularFifoQueue.this.elements[CircularFifoQueue.this.decrement(i2)] = CircularFifoQueue.this.elements[i2];
                                i2 = CircularFifoQueue.this.increment(i2);
                            }
                        }
                    } else {
                        System.arraycopy(CircularFifoQueue.this.elements, i2, CircularFifoQueue.this.elements, this.lastReturnedIndex, CircularFifoQueue.this.end - i2);
                    }
                    this.lastReturnedIndex = -1;
                    CircularFifoQueue circularFifoQueue = CircularFifoQueue.this;
                    circularFifoQueue.end = circularFifoQueue.decrement(circularFifoQueue.end);
                    CircularFifoQueue.this.elements[CircularFifoQueue.this.end] = null;
                    CircularFifoQueue.this.full = false;
                    this.index = CircularFifoQueue.this.decrement(this.index);
                    return;
                }
                throw new IllegalStateException();
            }
        };
    }

    @Override // org.apache.commons.collections4.BoundedCollection
    public int maxSize() {
        return this.maxElements;
    }

    @Override // java.util.Queue
    public boolean offer(E e) {
        return add(e);
    }

    @Override // java.util.Queue
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return this.elements[this.start];
    }

    @Override // java.util.Queue
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        return remove();
    }

    @Override // java.util.Queue
    public E remove() {
        if (!isEmpty()) {
            E[] eArr = this.elements;
            int i = this.start;
            E e = eArr[i];
            if (e != null) {
                this.start = i + 1;
                eArr[i] = null;
                if (this.start >= this.maxElements) {
                    this.start = 0;
                }
                this.full = false;
            }
            return e;
        }
        throw new NoSuchElementException("queue is empty");
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public int size() {
        int i = this.end;
        int i2 = this.start;
        if (i < i2) {
            return (this.maxElements - i2) + i;
        }
        if (i != i2) {
            return i - i2;
        }
        return this.full ? this.maxElements : 0;
    }

    public CircularFifoQueue(int i) {
        this.start = 0;
        this.end = 0;
        this.full = false;
        if (i > 0) {
            this.elements = (E[]) new Object[i];
            this.maxElements = this.elements.length;
            return;
        }
        throw new IllegalArgumentException("The size must be greater than 0");
    }

    public CircularFifoQueue(Collection<? extends E> collection) {
        this(collection.size());
        addAll(collection);
    }
}

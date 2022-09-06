package org.apache.commons.collections4.list;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.OrderedIterator;
/* loaded from: classes4.dex */
public abstract class AbstractLinkedList<E> implements List<E> {
    transient Node<E> header;
    transient int modCount;
    transient int size;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class LinkedListIterator<E> implements ListIterator<E>, OrderedIterator<E> {
        protected Node<E> current;
        protected int expectedModCount;
        protected Node<E> next;
        protected int nextIndex;
        protected final AbstractLinkedList<E> parent;

        /* JADX INFO: Access modifiers changed from: protected */
        public LinkedListIterator(AbstractLinkedList<E> abstractLinkedList, int i) throws IndexOutOfBoundsException {
            this.parent = abstractLinkedList;
            this.expectedModCount = abstractLinkedList.modCount;
            this.next = abstractLinkedList.getNode(i, true);
            this.nextIndex = i;
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            checkModCount();
            this.parent.addNodeBefore(this.next, e);
            this.current = null;
            this.nextIndex++;
            this.expectedModCount++;
        }

        protected void checkModCount() {
            if (this.parent.modCount == this.expectedModCount) {
                return;
            }
            throw new ConcurrentModificationException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Node<E> getLastNodeReturned() throws IllegalStateException {
            Node<E> node = this.current;
            if (node != null) {
                return node;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.next != this.parent.header;
        }

        @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return this.next.previous != this.parent.header;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            checkModCount();
            if (hasNext()) {
                E value = this.next.getValue();
                Node<E> node = this.next;
                this.current = node;
                this.next = node.next;
                this.nextIndex++;
                return value;
            }
            throw new NoSuchElementException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline107("No element at index "), this.nextIndex, "."));
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.nextIndex;
        }

        @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public E mo12706previous() {
            checkModCount();
            if (hasPrevious()) {
                this.next = this.next.previous;
                E value = this.next.getValue();
                this.current = this.next;
                this.nextIndex--;
                return value;
            }
            throw new NoSuchElementException("Already at start of list.");
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return nextIndex() - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public void remove() {
            checkModCount();
            Node<E> node = this.current;
            Node<E> node2 = this.next;
            if (node == node2) {
                this.next = node2.next;
                this.parent.removeNode(getLastNodeReturned());
            } else {
                this.parent.removeNode(getLastNodeReturned());
                this.nextIndex--;
            }
            this.current = null;
            this.expectedModCount++;
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            checkModCount();
            getLastNodeReturned().setValue(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class LinkedSubList<E> extends AbstractList<E> {
        int expectedModCount;
        int offset;
        AbstractLinkedList<E> parent;
        int size;

        protected LinkedSubList(AbstractLinkedList<E> abstractLinkedList, int i, int i2) {
            if (i >= 0) {
                if (i2 > abstractLinkedList.size()) {
                    throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("toIndex = ", i2));
                }
                if (i <= i2) {
                    this.parent = abstractLinkedList;
                    this.offset = i;
                    this.size = i2 - i;
                    this.expectedModCount = abstractLinkedList.modCount;
                    return;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline54("fromIndex(", i, ") > toIndex(", i2, ")"));
            }
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline49("fromIndex = ", i));
        }

        @Override // java.util.AbstractList, java.util.List
        public void add(int i, E e) {
            rangeCheck(i, this.size + 1);
            checkModCount();
            this.parent.add(i + this.offset, e);
            this.expectedModCount = this.parent.modCount;
            this.size++;
            ((AbstractList) this).modCount++;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean addAll(Collection<? extends E> collection) {
            return addAll(this.size, collection);
        }

        protected void checkModCount() {
            if (this.parent.modCount == this.expectedModCount) {
                return;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            checkModCount();
            Iterator<E> it2 = iterator();
            while (it2.hasNext()) {
                it2.next();
                it2.remove();
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public E get(int i) {
            rangeCheck(i, this.size);
            checkModCount();
            return this.parent.get(i + this.offset);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<E> iterator() {
            checkModCount();
            return this.parent.createSubListIterator(this);
        }

        @Override // java.util.AbstractList, java.util.List
        public ListIterator<E> listIterator(int i) {
            rangeCheck(i, this.size + 1);
            checkModCount();
            return this.parent.createSubListListIterator(this, i);
        }

        protected void rangeCheck(int i, int i2) {
            if (i < 0 || i >= i2) {
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline86(GeneratedOutlineSupport1.outline109("Index '", i, "' out of bounds for size '"), this.size, "'"));
            }
        }

        @Override // java.util.AbstractList, java.util.List
        public E remove(int i) {
            rangeCheck(i, this.size);
            checkModCount();
            E remove = this.parent.remove(i + this.offset);
            this.expectedModCount = this.parent.modCount;
            this.size--;
            ((AbstractList) this).modCount++;
            return remove;
        }

        @Override // java.util.AbstractList, java.util.List
        public E set(int i, E e) {
            rangeCheck(i, this.size);
            checkModCount();
            return this.parent.set(i + this.offset, e);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            checkModCount();
            return this.size;
        }

        @Override // java.util.AbstractList, java.util.List
        public List<E> subList(int i, int i2) {
            AbstractLinkedList<E> abstractLinkedList = this.parent;
            int i3 = this.offset;
            return new LinkedSubList(abstractLinkedList, i + i3, i2 + i3);
        }

        @Override // java.util.AbstractList, java.util.List
        public boolean addAll(int i, Collection<? extends E> collection) {
            rangeCheck(i, this.size + 1);
            int size = collection.size();
            if (size == 0) {
                return false;
            }
            checkModCount();
            this.parent.addAll(this.offset + i, collection);
            this.expectedModCount = this.parent.modCount;
            this.size += size;
            ((AbstractList) this).modCount++;
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class LinkedSubListIterator<E> extends LinkedListIterator<E> {
        protected final LinkedSubList<E> sub;

        protected LinkedSubListIterator(LinkedSubList<E> linkedSubList, int i) {
            super(linkedSubList.parent, i + linkedSubList.offset);
            this.sub = linkedSubList;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public void add(E e) {
            super.add(e);
            LinkedSubList<E> linkedSubList = this.sub;
            linkedSubList.expectedModCount = this.parent.modCount;
            linkedSubList.size++;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return nextIndex() < this.sub.size;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return previousIndex() >= 0;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator
        public int nextIndex() {
            return super.nextIndex() - this.sub.offset;
        }

        @Override // org.apache.commons.collections4.list.AbstractLinkedList.LinkedListIterator, java.util.ListIterator, java.util.Iterator
        public void remove() {
            LinkedSubList<E> linkedSubList;
            super.remove();
            this.sub.expectedModCount = this.parent.modCount;
            linkedSubList.size--;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLinkedList() {
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(this.size, collection);
    }

    public boolean addFirst(E e) {
        addNodeAfter(this.header, e);
        return true;
    }

    public boolean addLast(E e) {
        addNodeBefore(this.header, e);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addNode(Node<E> node, Node<E> node2) {
        node.next = node2;
        node.previous = node2.previous;
        node2.previous.next = node;
        node2.previous = node;
        this.size++;
        this.modCount++;
    }

    protected void addNodeAfter(Node<E> node, E e) {
        addNode(createNode(e), node.next);
    }

    protected void addNodeBefore(Node<E> node, E e) {
        addNode(createNode(e), node);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        removeAllNodes();
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it2 = collection.iterator();
        while (it2.hasNext()) {
            if (!contains(it2.next())) {
                return false;
            }
        }
        return true;
    }

    protected Node<E> createHeaderNode() {
        return new Node<>();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node<E> createNode(E e) {
        return new Node<>(e);
    }

    protected Iterator<E> createSubListIterator(LinkedSubList<E> linkedSubList) {
        return createSubListListIterator(linkedSubList, 0);
    }

    protected ListIterator<E> createSubListListIterator(LinkedSubList<E> linkedSubList, int i) {
        return new LinkedSubListIterator(linkedSubList, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        init();
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            add(objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(size());
        Iterator<E> it2 = iterator();
        while (it2.hasNext()) {
            objectOutputStream.writeObject(it2.next());
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        if (list.size() != size()) {
            return false;
        }
        ListIterator<E> listIterator = listIterator();
        ListIterator<E> listIterator2 = list.listIterator();
        while (listIterator.hasNext() && listIterator2.hasNext()) {
            E next = listIterator.next();
            E next2 = listIterator2.next();
            if (next == null) {
                if (next2 != null) {
                    return false;
                }
            } else if (!next.equals(next2)) {
                return false;
            }
        }
        return !listIterator.hasNext() && !listIterator2.hasNext();
    }

    @Override // java.util.List
    public E get(int i) {
        return getNode(i, false).getValue();
    }

    public E getFirst() {
        Node<E> node = this.header;
        Node<E> node2 = node.next;
        if (node2 != node) {
            return node2.getValue();
        }
        throw new NoSuchElementException();
    }

    public E getLast() {
        Node<E> node = this.header;
        Node<E> node2 = node.previous;
        if (node2 != node) {
            return node2.getValue();
        }
        throw new NoSuchElementException();
    }

    protected Node<E> getNode(int i, boolean z) throws IndexOutOfBoundsException {
        if (i >= 0) {
            if (!z && i == this.size) {
                throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline52("Couldn't get the node: index (", i, ") is the size of the list."));
            }
            int i2 = this.size;
            if (i <= i2) {
                if (i < i2 / 2) {
                    Node<E> node = this.header.next;
                    for (int i3 = 0; i3 < i; i3++) {
                        node = node.next;
                    }
                    return node;
                }
                Node<E> node2 = this.header;
                while (i2 > i) {
                    node2 = node2.previous;
                    i2--;
                }
                return node2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Couldn't get the node: index (");
            sb.append(i);
            sb.append(") greater than the size of the ");
            sb.append("list (");
            throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline86(sb, this.size, ")."));
        }
        throw new IndexOutOfBoundsException(GeneratedOutlineSupport1.outline52("Couldn't get the node: index (", i, ") less than zero."));
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        Iterator<E> it2 = iterator();
        int i = 1;
        while (it2.hasNext()) {
            E next = it2.next();
            i = (i * 31) + (next == null ? 0 : next.hashCode());
        }
        return i;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        int i = 0;
        for (Node<E> node = this.header.next; node != this.header; node = node.next) {
            if (isEqualValue(node.getValue(), obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void init() {
        this.header = createHeaderNode();
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return size() == 0;
    }

    protected boolean isEqualValue(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        return listIterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        int i = this.size - 1;
        Node<E> node = this.header;
        while (true) {
            node = node.previous;
            if (node != this.header) {
                if (isEqualValue(node.getValue(), obj)) {
                    return i;
                }
                i--;
            } else {
                return -1;
            }
        }
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return new LinkedListIterator(this, 0);
    }

    @Override // java.util.List
    public E remove(int i) {
        Node<E> node = getNode(i, false);
        E value = node.getValue();
        removeNode(node);
        return value;
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        Iterator<E> it2 = iterator();
        boolean z = false;
        while (it2.hasNext()) {
            if (collection.contains(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeAllNodes() {
        Node<E> node = this.header;
        node.next = node;
        node.previous = node;
        this.size = 0;
        this.modCount++;
    }

    public E removeFirst() {
        Node<E> node = this.header;
        Node<E> node2 = node.next;
        if (node2 != node) {
            E value = node2.getValue();
            removeNode(node2);
            return value;
        }
        throw new NoSuchElementException();
    }

    public E removeLast() {
        Node<E> node = this.header;
        Node<E> node2 = node.previous;
        if (node2 != node) {
            E value = node2.getValue();
            removeNode(node2);
            return value;
        }
        throw new NoSuchElementException();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeNode(Node<E> node) {
        Node<E> node2 = node.previous;
        node2.next = node.next;
        node.next.previous = node2;
        this.size--;
        this.modCount++;
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        Iterator<E> it2 = iterator();
        boolean z = false;
        while (it2.hasNext()) {
            if (!collection.contains(it2.next())) {
                it2.remove();
                z = true;
            }
        }
        return z;
    }

    @Override // java.util.List
    public E set(int i, E e) {
        Node<E> node = getNode(i, false);
        E value = node.getValue();
        updateNode(node, e);
        return value;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return new LinkedSubList(this, i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return toArray(new Object[this.size]);
    }

    public String toString() {
        if (size() == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(size() * 16);
        sb.append(JsonReaderKt.BEGIN_LIST);
        Iterator<E> it2 = iterator();
        boolean hasNext = it2.hasNext();
        while (hasNext) {
            Object next = it2.next();
            if (next == this) {
                next = "(this Collection)";
            }
            sb.append(next);
            hasNext = it2.hasNext();
            if (hasNext) {
                sb.append(", ");
            }
        }
        sb.append(JsonReaderKt.END_LIST);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateNode(Node<E> node, E e) {
        node.setValue(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLinkedList(Collection<? extends E> collection) {
        init();
        addAll(collection);
    }

    @Override // java.util.List
    public void add(int i, E e) {
        addNodeBefore(getNode(i, true), e);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        Node<E> node = getNode(i, true);
        for (E e : collection) {
            addNodeBefore(node, e);
        }
        return true;
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new LinkedListIterator(this, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        if (tArr.length < this.size) {
            tArr = (T[]) ((Object[]) Array.newInstance(tArr.getClass().getComponentType(), this.size));
        }
        int i = 0;
        Node<E> node = this.header.next;
        while (node != this.header) {
            tArr[i] = node.getValue();
            node = node.next;
            i++;
        }
        int length = tArr.length;
        int i2 = this.size;
        if (length > i2) {
            tArr[i2] = null;
        }
        return tArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Node<E> {
        protected Node<E> next;
        protected Node<E> previous;
        protected E value;

        protected Node() {
            this.previous = this;
            this.next = this;
        }

        protected Node<E> getNextNode() {
            return this.next;
        }

        protected Node<E> getPreviousNode() {
            return this.previous;
        }

        protected E getValue() {
            return this.value;
        }

        protected void setNextNode(Node<E> node) {
            this.next = node;
        }

        protected void setPreviousNode(Node<E> node) {
            this.previous = node;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setValue(E e) {
            this.value = e;
        }

        protected Node(E e) {
            this.value = e;
        }

        protected Node(Node<E> node, Node<E> node2, E e) {
            this.previous = node;
            this.next = node2;
            this.value = e;
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        Node<E> node = this.header;
        do {
            node = node.next;
            if (node == this.header) {
                return false;
            }
        } while (!isEqualValue(node.getValue(), obj));
        removeNode(node);
        return true;
    }
}

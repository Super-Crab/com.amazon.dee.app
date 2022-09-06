package org.apache.commons.collections4.list;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.AbstractList;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.OrderedIterator;
/* loaded from: classes4.dex */
public class TreeList<E> extends AbstractList<E> {
    private AVLNode<E> root;
    private int size;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class TreeListIterator<E> implements ListIterator<E>, OrderedIterator<E> {
        private AVLNode<E> current;
        private int currentIndex;
        private int expectedModCount;
        private AVLNode<E> next;
        private int nextIndex;
        private final TreeList<E> parent;

        protected TreeListIterator(TreeList<E> treeList, int i) throws IndexOutOfBoundsException {
            this.parent = treeList;
            this.expectedModCount = ((AbstractList) treeList).modCount;
            this.next = ((TreeList) treeList).root == null ? null : ((TreeList) treeList).root.get(i);
            this.nextIndex = i;
            this.currentIndex = -1;
        }

        @Override // java.util.ListIterator
        public void add(E e) {
            checkModCount();
            this.parent.add(this.nextIndex, e);
            this.current = null;
            this.currentIndex = -1;
            this.nextIndex++;
            this.expectedModCount++;
        }

        protected void checkModCount() {
            if (((AbstractList) this.parent).modCount == this.expectedModCount) {
                return;
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < this.parent.size();
        }

        @Override // java.util.ListIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return this.nextIndex > 0;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public E next() {
            checkModCount();
            if (hasNext()) {
                if (this.next == null) {
                    this.next = ((TreeList) this.parent).root.get(this.nextIndex);
                }
                E value = this.next.getValue();
                AVLNode<E> aVLNode = this.next;
                this.current = aVLNode;
                int i = this.nextIndex;
                this.nextIndex = i + 1;
                this.currentIndex = i;
                this.next = aVLNode.next();
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
                AVLNode<E> aVLNode = this.next;
                if (aVLNode == null) {
                    this.next = ((TreeList) this.parent).root.get(this.nextIndex - 1);
                } else {
                    this.next = aVLNode.previous();
                }
                E value = this.next.getValue();
                this.current = this.next;
                int i = this.nextIndex - 1;
                this.nextIndex = i;
                this.currentIndex = i;
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
            int i = this.currentIndex;
            if (i != -1) {
                this.parent.remove(i);
                int i2 = this.nextIndex;
                if (i2 != this.currentIndex) {
                    this.nextIndex = i2 - 1;
                }
                this.next = null;
                this.current = null;
                this.currentIndex = -1;
                this.expectedModCount++;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.ListIterator
        public void set(E e) {
            checkModCount();
            AVLNode<E> aVLNode = this.current;
            if (aVLNode != null) {
                aVLNode.setValue(e);
                return;
            }
            throw new IllegalStateException();
        }
    }

    public TreeList() {
    }

    private void checkInterval(int i, int i2, int i3) {
        if (i < i2 || i > i3) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Invalid index:", i, ", size=");
            outline109.append(size());
            throw new IndexOutOfBoundsException(outline109.toString());
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        ((AbstractList) this).modCount++;
        checkInterval(i, 0, size());
        AVLNode<E> aVLNode = this.root;
        if (aVLNode == null) {
            this.root = new AVLNode<>(i, e, null, null);
        } else {
            this.root = aVLNode.insert(i, e);
        }
        this.size++;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        if (collection.isEmpty()) {
            return false;
        }
        ((AbstractList) this).modCount = collection.size() + ((AbstractList) this).modCount;
        AVLNode<E> aVLNode = new AVLNode<>(collection);
        AVLNode<E> aVLNode2 = this.root;
        if (aVLNode2 != null) {
            aVLNode = aVLNode2.addAll(aVLNode, this.size);
        }
        this.root = aVLNode;
        this.size = collection.size() + this.size;
        return true;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public void clear() {
        ((AbstractList) this).modCount++;
        this.root = null;
        this.size = 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean contains(Object obj) {
        return indexOf(obj) >= 0;
    }

    @Override // java.util.AbstractList, java.util.List
    public E get(int i) {
        checkInterval(i, 0, size() - 1);
        return this.root.get(i).getValue();
    }

    @Override // java.util.AbstractList, java.util.List
    public int indexOf(Object obj) {
        AVLNode<E> aVLNode = this.root;
        if (aVLNode == null) {
            return -1;
        }
        return aVLNode.indexOf(obj, ((AVLNode) aVLNode).relativePosition);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator<E> iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public E remove(int i) {
        ((AbstractList) this).modCount++;
        checkInterval(i, 0, size() - 1);
        E e = get(i);
        this.root = this.root.remove(i);
        this.size--;
        return e;
    }

    @Override // java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        checkInterval(i, 0, size() - 1);
        AVLNode<E> aVLNode = this.root.get(i);
        E e2 = (E) ((AVLNode) aVLNode).value;
        aVLNode.setValue(e);
        return e2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        AVLNode<E> aVLNode = this.root;
        if (aVLNode != null) {
            aVLNode.toArray(objArr, ((AVLNode) aVLNode).relativePosition);
        }
        return objArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class AVLNode<E> {
        private int height;
        private AVLNode<E> left;
        private boolean leftIsPrevious;
        private int relativePosition;
        private AVLNode<E> right;
        private boolean rightIsNext;
        private E value;

        /* JADX INFO: Access modifiers changed from: private */
        public AVLNode<E> addAll(AVLNode<E> aVLNode, int i) {
            AVLNode<E> max = max();
            AVLNode<E> min = aVLNode.min();
            if (aVLNode.height > this.height) {
                AVLNode<E> removeMax = removeMax();
                ArrayDeque arrayDeque = new ArrayDeque();
                int i2 = aVLNode.relativePosition + i;
                int i3 = 0;
                AVLNode<E> aVLNode2 = aVLNode;
                while (aVLNode2 != null && aVLNode2.height > getHeight(removeMax)) {
                    arrayDeque.push(aVLNode2);
                    aVLNode2 = aVLNode2.left;
                    if (aVLNode2 != null) {
                        i3 = i2;
                        i2 = aVLNode2.relativePosition + i2;
                    } else {
                        i3 = i2;
                    }
                }
                max.setLeft(removeMax, null);
                max.setRight(aVLNode2, min);
                if (removeMax != null) {
                    removeMax.max().setRight(null, max);
                    removeMax.relativePosition -= i - 1;
                }
                if (aVLNode2 != null) {
                    aVLNode2.min().setLeft(null, max);
                    aVLNode2.relativePosition = (i2 - i) + 1;
                }
                max.relativePosition = (i - 1) - i3;
                aVLNode.relativePosition += i;
                while (!arrayDeque.isEmpty()) {
                    AVLNode aVLNode3 = (AVLNode) arrayDeque.pop();
                    aVLNode3.setLeft(max, null);
                    max = aVLNode3.balance();
                }
                return max;
            }
            AVLNode<E> removeMin = aVLNode.removeMin();
            ArrayDeque arrayDeque2 = new ArrayDeque();
            int i4 = 0;
            int i5 = this.relativePosition;
            AVLNode<E> aVLNode4 = this;
            while (aVLNode4 != null && aVLNode4.height > getHeight(removeMin)) {
                arrayDeque2.push(aVLNode4);
                aVLNode4 = aVLNode4.right;
                if (aVLNode4 != null) {
                    i4 = i5;
                    i5 = aVLNode4.relativePosition + i5;
                } else {
                    i4 = i5;
                }
            }
            min.setRight(removeMin, null);
            min.setLeft(aVLNode4, max);
            if (removeMin != null) {
                removeMin.min().setLeft(null, min);
                removeMin.relativePosition++;
            }
            if (aVLNode4 != null) {
                aVLNode4.max().setRight(null, min);
                aVLNode4.relativePosition = i5 - i;
            }
            min.relativePosition = i - i4;
            while (!arrayDeque2.isEmpty()) {
                AVLNode aVLNode5 = (AVLNode) arrayDeque2.pop();
                aVLNode5.setRight(min, null);
                min = aVLNode5.balance();
            }
            return min;
        }

        private AVLNode<E> balance() {
            int heightRightMinusLeft = heightRightMinusLeft();
            if (heightRightMinusLeft == -2) {
                if (this.left.heightRightMinusLeft() > 0) {
                    setLeft(this.left.rotateLeft(), null);
                }
                return rotateRight();
            } else if (heightRightMinusLeft == -1 || heightRightMinusLeft == 0 || heightRightMinusLeft == 1) {
                return this;
            } else {
                if (heightRightMinusLeft == 2) {
                    if (this.right.heightRightMinusLeft() < 0) {
                        setRight(this.right.rotateRight(), null);
                    }
                    return rotateLeft();
                }
                throw new RuntimeException("tree inconsistent!");
            }
        }

        private int getHeight(AVLNode<E> aVLNode) {
            if (aVLNode == null) {
                return -1;
            }
            return aVLNode.height;
        }

        private AVLNode<E> getLeftSubTree() {
            if (this.leftIsPrevious) {
                return null;
            }
            return this.left;
        }

        private int getOffset(AVLNode<E> aVLNode) {
            if (aVLNode == null) {
                return 0;
            }
            return aVLNode.relativePosition;
        }

        private AVLNode<E> getRightSubTree() {
            if (this.rightIsNext) {
                return null;
            }
            return this.right;
        }

        private int heightRightMinusLeft() {
            return getHeight(getRightSubTree()) - getHeight(getLeftSubTree());
        }

        private AVLNode<E> insertOnLeft(int i, E e) {
            if (getLeftSubTree() == null) {
                setLeft(new AVLNode<>(-1, e, this, this.left), null);
            } else {
                setLeft(this.left.insert(i, e), null);
            }
            int i2 = this.relativePosition;
            if (i2 >= 0) {
                this.relativePosition = i2 + 1;
            }
            AVLNode<E> balance = balance();
            recalcHeight();
            return balance;
        }

        private AVLNode<E> insertOnRight(int i, E e) {
            if (getRightSubTree() == null) {
                setRight(new AVLNode<>(1, e, this.right, this), null);
            } else {
                setRight(this.right.insert(i, e), null);
            }
            int i2 = this.relativePosition;
            if (i2 < 0) {
                this.relativePosition = i2 - 1;
            }
            AVLNode<E> balance = balance();
            recalcHeight();
            return balance;
        }

        private AVLNode<E> max() {
            return getRightSubTree() == null ? this : this.right.max();
        }

        private AVLNode<E> min() {
            return getLeftSubTree() == null ? this : this.left.min();
        }

        private void recalcHeight() {
            int i = -1;
            int i2 = getLeftSubTree() == null ? -1 : getLeftSubTree().height;
            if (getRightSubTree() != null) {
                i = getRightSubTree().height;
            }
            this.height = Math.max(i2, i) + 1;
        }

        private AVLNode<E> removeMax() {
            if (getRightSubTree() == null) {
                return removeSelf();
            }
            setRight(this.right.removeMax(), this.right.right);
            int i = this.relativePosition;
            if (i < 0) {
                this.relativePosition = i + 1;
            }
            recalcHeight();
            return balance();
        }

        private AVLNode<E> removeMin() {
            if (getLeftSubTree() == null) {
                return removeSelf();
            }
            setLeft(this.left.removeMin(), this.left.left);
            int i = this.relativePosition;
            if (i > 0) {
                this.relativePosition = i - 1;
            }
            recalcHeight();
            return balance();
        }

        private AVLNode<E> removeSelf() {
            if (getRightSubTree() == null && getLeftSubTree() == null) {
                return null;
            }
            if (getRightSubTree() == null) {
                int i = this.relativePosition;
                if (i > 0) {
                    this.left.relativePosition += i;
                }
                this.left.max().setRight(null, this.right);
                return this.left;
            }
            int i2 = 1;
            if (getLeftSubTree() == null) {
                AVLNode<E> aVLNode = this.right;
                int i3 = aVLNode.relativePosition;
                int i4 = this.relativePosition;
                if (i4 < 0) {
                    i2 = 0;
                }
                aVLNode.relativePosition = (i4 - i2) + i3;
                this.right.min().setLeft(null, this.left);
                return this.right;
            }
            if (heightRightMinusLeft() > 0) {
                AVLNode<E> min = this.right.min();
                this.value = min.value;
                if (this.leftIsPrevious) {
                    this.left = min.left;
                }
                this.right = this.right.removeMin();
                int i5 = this.relativePosition;
                if (i5 < 0) {
                    this.relativePosition = i5 + 1;
                }
            } else {
                AVLNode<E> max = this.left.max();
                this.value = max.value;
                if (this.rightIsNext) {
                    this.right = max.right;
                }
                AVLNode<E> aVLNode2 = this.left;
                AVLNode<E> aVLNode3 = aVLNode2.left;
                this.left = aVLNode2.removeMax();
                if (this.left == null) {
                    this.left = aVLNode3;
                    this.leftIsPrevious = true;
                }
                int i6 = this.relativePosition;
                if (i6 > 0) {
                    this.relativePosition = i6 - 1;
                }
            }
            recalcHeight();
            return this;
        }

        private AVLNode<E> rotateLeft() {
            AVLNode<E> aVLNode = this.right;
            AVLNode<E> leftSubTree = getRightSubTree().getLeftSubTree();
            int offset = this.relativePosition + getOffset(aVLNode);
            setRight(leftSubTree, aVLNode);
            aVLNode.setLeft(this, null);
            setOffset(aVLNode, offset);
            setOffset(this, -aVLNode.relativePosition);
            setOffset(leftSubTree, getOffset(aVLNode) + getOffset(leftSubTree));
            return aVLNode;
        }

        private AVLNode<E> rotateRight() {
            AVLNode<E> aVLNode = this.left;
            AVLNode<E> rightSubTree = getLeftSubTree().getRightSubTree();
            int offset = this.relativePosition + getOffset(aVLNode);
            setLeft(rightSubTree, aVLNode);
            aVLNode.setRight(this, null);
            setOffset(aVLNode, offset);
            setOffset(this, -aVLNode.relativePosition);
            setOffset(rightSubTree, getOffset(aVLNode) + getOffset(rightSubTree));
            return aVLNode;
        }

        private void setLeft(AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            this.leftIsPrevious = aVLNode == null;
            if (this.leftIsPrevious) {
                aVLNode = aVLNode2;
            }
            this.left = aVLNode;
            recalcHeight();
        }

        private int setOffset(AVLNode<E> aVLNode, int i) {
            if (aVLNode == null) {
                return 0;
            }
            int offset = getOffset(aVLNode);
            aVLNode.relativePosition = i;
            return offset;
        }

        private void setRight(AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            this.rightIsNext = aVLNode == null;
            if (this.rightIsNext) {
                aVLNode = aVLNode2;
            }
            this.right = aVLNode;
            recalcHeight();
        }

        AVLNode<E> get(int i) {
            int i2 = i - this.relativePosition;
            if (i2 == 0) {
                return this;
            }
            AVLNode<E> leftSubTree = i2 < 0 ? getLeftSubTree() : getRightSubTree();
            if (leftSubTree != null) {
                return leftSubTree.get(i2);
            }
            return null;
        }

        E getValue() {
            return this.value;
        }

        int indexOf(Object obj, int i) {
            if (getLeftSubTree() != null) {
                AVLNode<E> aVLNode = this.left;
                int indexOf = aVLNode.indexOf(obj, aVLNode.relativePosition + i);
                if (indexOf != -1) {
                    return indexOf;
                }
            }
            E e = this.value;
            if (e != null ? !e.equals(obj) : e != obj) {
                if (getRightSubTree() == null) {
                    return -1;
                }
                AVLNode<E> aVLNode2 = this.right;
                return aVLNode2.indexOf(obj, i + aVLNode2.relativePosition);
            }
            return i;
        }

        AVLNode<E> insert(int i, E e) {
            int i2 = i - this.relativePosition;
            if (i2 <= 0) {
                return insertOnLeft(i2, e);
            }
            return insertOnRight(i2, e);
        }

        AVLNode<E> next() {
            AVLNode<E> aVLNode;
            if (!this.rightIsNext && (aVLNode = this.right) != null) {
                return aVLNode.min();
            }
            return this.right;
        }

        AVLNode<E> previous() {
            AVLNode<E> aVLNode;
            if (!this.leftIsPrevious && (aVLNode = this.left) != null) {
                return aVLNode.max();
            }
            return this.left;
        }

        AVLNode<E> remove(int i) {
            int i2 = i - this.relativePosition;
            if (i2 == 0) {
                return removeSelf();
            }
            if (i2 > 0) {
                setRight(this.right.remove(i2), this.right.right);
                int i3 = this.relativePosition;
                if (i3 < 0) {
                    this.relativePosition = i3 + 1;
                }
            } else {
                setLeft(this.left.remove(i2), this.left.left);
                int i4 = this.relativePosition;
                if (i4 > 0) {
                    this.relativePosition = i4 - 1;
                }
            }
            recalcHeight();
            return balance();
        }

        void setValue(E e) {
            this.value = e;
        }

        void toArray(Object[] objArr, int i) {
            objArr[i] = this.value;
            if (getLeftSubTree() != null) {
                AVLNode<E> aVLNode = this.left;
                aVLNode.toArray(objArr, aVLNode.relativePosition + i);
            }
            if (getRightSubTree() != null) {
                AVLNode<E> aVLNode2 = this.right;
                aVLNode2.toArray(objArr, i + aVLNode2.relativePosition);
            }
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AVLNode(");
            outline107.append(this.relativePosition);
            outline107.append(JsonReaderKt.COMMA);
            boolean z = true;
            outline107.append(this.left != null);
            outline107.append(JsonReaderKt.COMMA);
            outline107.append(this.value);
            outline107.append(JsonReaderKt.COMMA);
            if (getRightSubTree() == null) {
                z = false;
            }
            outline107.append(z);
            outline107.append(", faedelung ");
            return GeneratedOutlineSupport1.outline97(outline107, this.rightIsNext, " )");
        }

        private AVLNode(int i, E e, AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            this.relativePosition = i;
            this.value = e;
            this.rightIsNext = true;
            this.leftIsPrevious = true;
            this.right = aVLNode;
            this.left = aVLNode2;
        }

        private AVLNode(Collection<? extends E> collection) {
            this(collection.iterator(), 0, collection.size() - 1, 0, null, null);
        }

        private AVLNode(Iterator<? extends E> it2, int i, int i2, int i3, AVLNode<E> aVLNode, AVLNode<E> aVLNode2) {
            int i4 = ((i2 - i) / 2) + i;
            if (i < i4) {
                this.left = new AVLNode<>(it2, i, i4 - 1, i4, aVLNode, this);
            } else {
                this.leftIsPrevious = true;
                this.left = aVLNode;
            }
            this.value = it2.next();
            this.relativePosition = i4 - i3;
            if (i4 < i2) {
                this.right = new AVLNode<>(it2, i4 + 1, i2, i4, this, aVLNode2);
            } else {
                this.rightIsNext = true;
                this.right = aVLNode2;
            }
            recalcHeight();
        }
    }

    public TreeList(Collection<? extends E> collection) {
        if (!collection.isEmpty()) {
            this.root = new AVLNode<>(collection);
            this.size = collection.size();
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public ListIterator<E> listIterator(int i) {
        checkInterval(i, 0, size());
        return new TreeListIterator(this, i);
    }
}

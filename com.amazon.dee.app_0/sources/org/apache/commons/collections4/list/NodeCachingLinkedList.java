package org.apache.commons.collections4.list;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import org.apache.commons.collections4.list.AbstractLinkedList;
/* loaded from: classes4.dex */
public class NodeCachingLinkedList<E> extends AbstractLinkedList<E> implements Serializable {
    private static final int DEFAULT_MAXIMUM_CACHE_SIZE = 20;
    private static final long serialVersionUID = 6897789178562232073L;
    private transient int cacheSize;
    private transient AbstractLinkedList.Node<E> firstCachedNode;
    private int maximumCacheSize;

    public NodeCachingLinkedList() {
        this(20);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        doReadObject(objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        doWriteObject(objectOutputStream);
    }

    protected void addNodeToCache(AbstractLinkedList.Node<E> node) {
        if (isCacheFull()) {
            return;
        }
        AbstractLinkedList.Node<E> node2 = this.firstCachedNode;
        node.previous = null;
        node.next = node2;
        node.setValue(null);
        this.firstCachedNode = node;
        this.cacheSize++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public AbstractLinkedList.Node<E> createNode(E e) {
        AbstractLinkedList.Node<E> nodeFromCache = getNodeFromCache();
        if (nodeFromCache == null) {
            return super.createNode(e);
        }
        nodeFromCache.setValue(e);
        return nodeFromCache;
    }

    protected int getMaximumCacheSize() {
        return this.maximumCacheSize;
    }

    protected AbstractLinkedList.Node<E> getNodeFromCache() {
        int i = this.cacheSize;
        if (i == 0) {
            return null;
        }
        AbstractLinkedList.Node<E> node = this.firstCachedNode;
        this.firstCachedNode = node.next;
        node.next = null;
        this.cacheSize = i - 1;
        return node;
    }

    protected boolean isCacheFull() {
        return this.cacheSize >= this.maximumCacheSize;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void removeAllNodes() {
        int min = Math.min(this.size, this.maximumCacheSize - this.cacheSize);
        AbstractLinkedList.Node<E> node = this.header.next;
        int i = 0;
        while (i < min) {
            AbstractLinkedList.Node<E> node2 = node.next;
            addNodeToCache(node);
            i++;
            node = node2;
        }
        super.removeAllNodes();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.list.AbstractLinkedList
    public void removeNode(AbstractLinkedList.Node<E> node) {
        super.removeNode(node);
        addNodeToCache(node);
    }

    protected void setMaximumCacheSize(int i) {
        this.maximumCacheSize = i;
        shrinkCacheToMaximumSize();
    }

    protected void shrinkCacheToMaximumSize() {
        while (this.cacheSize > this.maximumCacheSize) {
            getNodeFromCache();
        }
    }

    public NodeCachingLinkedList(Collection<? extends E> collection) {
        super(collection);
        this.maximumCacheSize = 20;
    }

    public NodeCachingLinkedList(int i) {
        this.maximumCacheSize = i;
        init();
    }
}

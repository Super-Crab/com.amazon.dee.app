package org.apache.commons.collections4.collection;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;
/* loaded from: classes4.dex */
public class CompositeCollection<E> implements Collection<E>, Serializable {
    private static final long serialVersionUID = 8417515734108306801L;
    private final List<Collection<E>> all = new ArrayList();
    private CollectionMutator<E> mutator;

    /* loaded from: classes4.dex */
    public interface CollectionMutator<E> extends Serializable {
        boolean add(CompositeCollection<E> compositeCollection, List<Collection<E>> list, E e);

        boolean addAll(CompositeCollection<E> compositeCollection, List<Collection<E>> list, Collection<? extends E> collection);

        boolean remove(CompositeCollection<E> compositeCollection, List<Collection<E>> list, Object obj);
    }

    public CompositeCollection() {
    }

    @Override // java.util.Collection
    public boolean add(E e) {
        CollectionMutator<E> collectionMutator = this.mutator;
        if (collectionMutator != null) {
            return collectionMutator.add(this, this.all, e);
        }
        throw new UnsupportedOperationException("add() is not supported on CompositeCollection without a CollectionMutator strategy");
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        CollectionMutator<E> collectionMutator = this.mutator;
        if (collectionMutator != null) {
            return collectionMutator.addAll(this, this.all, collection);
        }
        throw new UnsupportedOperationException("addAll() is not supported on CompositeCollection without a CollectionMutator strategy");
    }

    public void addComposited(Collection<E> collection) {
        this.all.add(collection);
    }

    @Override // java.util.Collection
    public void clear() {
        for (Collection<E> collection : this.all) {
            collection.clear();
        }
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        for (Collection<E> collection : this.all) {
            if (collection.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it2 = collection.iterator();
        while (it2.hasNext()) {
            if (!contains(it2.next())) {
                return false;
            }
        }
        return true;
    }

    public List<Collection<E>> getCollections() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    protected CollectionMutator<E> getMutator() {
        return this.mutator;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        for (Collection<E> collection : this.all) {
            if (!collection.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        if (this.all.isEmpty()) {
            return EmptyIterator.emptyIterator();
        }
        IteratorChain iteratorChain = new IteratorChain();
        for (Collection<E> collection : this.all) {
            iteratorChain.addIterator(collection.iterator());
        }
        return iteratorChain;
    }

    @Override // java.util.Collection
    public boolean remove(Object obj) {
        CollectionMutator<E> collectionMutator = this.mutator;
        if (collectionMutator != null) {
            return collectionMutator.remove(this, this.all, obj);
        }
        throw new UnsupportedOperationException("remove() is not supported on CompositeCollection without a CollectionMutator strategy");
    }

    @Override // java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        if (collection.size() == 0) {
            return false;
        }
        for (Collection<E> collection2 : this.all) {
            z |= collection2.removeAll(collection);
        }
        return z;
    }

    public void removeComposited(Collection<E> collection) {
        this.all.remove(collection);
    }

    @Override // java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (Collection<E> collection2 : this.all) {
            z |= collection2.retainAll(collection);
        }
        return z;
    }

    public void setMutator(CollectionMutator<E> collectionMutator) {
        this.mutator = collectionMutator;
    }

    @Override // java.util.Collection
    public int size() {
        int i = 0;
        for (Collection<E> collection : this.all) {
            i += collection.size();
        }
        return i;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        Iterator<E> it2 = iterator();
        int i = 0;
        while (it2.hasNext()) {
            objArr[i] = it2.next();
            i++;
        }
        return objArr;
    }

    public Collection<E> toCollection() {
        return new ArrayList(this);
    }

    public void addComposited(Collection<E> collection, Collection<E> collection2) {
        this.all.add(collection);
        this.all.add(collection2);
    }

    public CompositeCollection(Collection<E> collection) {
        addComposited(collection);
    }

    public void addComposited(Collection<E>... collectionArr) {
        this.all.addAll(Arrays.asList(collectionArr));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) GeneratedOutlineSupport1.outline26(tArr, size));
        }
        int i = 0;
        for (Collection<E> collection : this.all) {
            for (E e : collection) {
                tArr[i] = e;
                i++;
            }
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    public CompositeCollection(Collection<E> collection, Collection<E> collection2) {
        addComposited(collection, collection2);
    }

    public CompositeCollection(Collection<E>... collectionArr) {
        addComposited(collectionArr);
    }
}

package org.apache.commons.collections4.set;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.list.UnmodifiableList;
/* loaded from: classes4.dex */
public class CompositeSet<E> implements Set<E>, Serializable {
    private static final long serialVersionUID = 5185069727540378940L;
    private final List<Set<E>> all = new ArrayList();
    private SetMutator<E> mutator;

    /* loaded from: classes4.dex */
    public interface SetMutator<E> extends Serializable {
        boolean add(CompositeSet<E> compositeSet, List<Set<E>> list, E e);

        boolean addAll(CompositeSet<E> compositeSet, List<Set<E>> list, Collection<? extends E> collection);

        void resolveCollision(CompositeSet<E> compositeSet, Set<E> set, Set<E> set2, Collection<E> collection);
    }

    public CompositeSet() {
    }

    @Override // java.util.Set, java.util.Collection
    public boolean add(E e) {
        SetMutator<E> setMutator = this.mutator;
        if (setMutator != null) {
            return setMutator.add(this, this.all, e);
        }
        throw new UnsupportedOperationException("add() is not supported on CompositeSet without a SetMutator strategy");
    }

    @Override // java.util.Set, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        SetMutator<E> setMutator = this.mutator;
        if (setMutator != null) {
            return setMutator.addAll(this, this.all, collection);
        }
        throw new UnsupportedOperationException("addAll() is not supported on CompositeSet without a SetMutator strategy");
    }

    public synchronized void addComposited(Set<E> set) {
        for (Set<E> set2 : getSets()) {
            Collection<E> intersection = CollectionUtils.intersection(set2, set);
            if (intersection.size() > 0) {
                if (this.mutator != null) {
                    getMutator().resolveCollision(this, set2, set, intersection);
                    if (CollectionUtils.intersection(set2, set).size() > 0) {
                        throw new IllegalArgumentException("Attempt to add illegal entry unresolved by SetMutator.resolveCollision()");
                    }
                } else {
                    throw new UnsupportedOperationException("Collision adding composited set with no SetMutator set");
                }
            }
        }
        this.all.add(set);
    }

    @Override // java.util.Set, java.util.Collection
    public void clear() {
        for (Set<E> set : this.all) {
            set.clear();
        }
    }

    @Override // java.util.Set, java.util.Collection
    public boolean contains(Object obj) {
        for (Set<E> set : this.all) {
            if (set.contains(obj)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it2 = collection.iterator();
        while (it2.hasNext()) {
            if (!contains(it2.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean equals(Object obj) {
        if (obj instanceof Set) {
            Set set = (Set) obj;
            return set.size() == size() && set.containsAll(this);
        }
        return false;
    }

    protected SetMutator<E> getMutator() {
        return this.mutator;
    }

    public List<Set<E>> getSets() {
        return UnmodifiableList.unmodifiableList(this.all);
    }

    @Override // java.util.Set, java.util.Collection
    public int hashCode() {
        Iterator<E> it2 = iterator();
        int i = 0;
        while (it2.hasNext()) {
            E next = it2.next();
            i += next == null ? 0 : next.hashCode();
        }
        return i;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean isEmpty() {
        for (Set<E> set : this.all) {
            if (!set.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Set, java.util.Collection, java.lang.Iterable
    public Iterator<E> iterator() {
        if (this.all.isEmpty()) {
            return EmptyIterator.emptyIterator();
        }
        IteratorChain iteratorChain = new IteratorChain();
        for (Set<E> set : this.all) {
            iteratorChain.addIterator(set.iterator());
        }
        return iteratorChain;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean remove(Object obj) {
        for (Set<E> set : getSets()) {
            if (set.contains(obj)) {
                return set.remove(obj);
            }
        }
        return false;
    }

    @Override // java.util.Set, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        if (collection.size() == 0) {
            return false;
        }
        for (Set<E> set : this.all) {
            z |= set.removeAll(collection);
        }
        return z;
    }

    public void removeComposited(Set<E> set) {
        this.all.remove(set);
    }

    @Override // java.util.Set, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (Set<E> set : this.all) {
            z |= set.retainAll(collection);
        }
        return z;
    }

    public void setMutator(SetMutator<E> setMutator) {
        this.mutator = setMutator;
    }

    @Override // java.util.Set, java.util.Collection
    public int size() {
        int i = 0;
        for (Set<E> set : this.all) {
            i += set.size();
        }
        return i;
    }

    @Override // java.util.Set, java.util.Collection
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

    public Set<E> toSet() {
        return new HashSet(this);
    }

    public CompositeSet(Set<E> set) {
        addComposited(set);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Set, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) GeneratedOutlineSupport1.outline26(tArr, size));
        }
        int i = 0;
        for (Set<E> set : this.all) {
            for (E e : set) {
                tArr[i] = e;
                i++;
            }
        }
        if (tArr.length > size) {
            tArr[size] = null;
        }
        return tArr;
    }

    public CompositeSet(Set<E>... setArr) {
        addComposited(setArr);
    }

    public void addComposited(Set<E> set, Set<E> set2) {
        addComposited(set);
        addComposited(set2);
    }

    public void addComposited(Set<E>... setArr) {
        for (Set<E> set : setArr) {
            addComposited(set);
        }
    }
}

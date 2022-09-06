package org.apache.commons.collections4.set;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.functors.UniquePredicate;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.list.UnmodifiableList;
/* loaded from: classes4.dex */
public class ListOrderedSet<E> extends AbstractSerializableSetDecorator<E> {
    private static final long serialVersionUID = -228664372470420141L;
    private final List<E> setOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class OrderedSetIterator<E> extends AbstractIteratorDecorator<E> implements OrderedIterator<E> {
        private E last;
        private final Collection<E> set;

        @Override // org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return ((ListIterator) getIterator()).hasPrevious();
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next */
        public E mo12737next() {
            this.last = getIterator().next();
            return this.last;
        }

        @Override // org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public E mo12706previous() {
            this.last = (E) ((ListIterator) getIterator()).previous();
            return this.last;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            this.set.remove(this.last);
            getIterator().remove();
            this.last = null;
        }

        private OrderedSetIterator(ListIterator<E> listIterator, Collection<E> collection) {
            super(listIterator);
            this.set = collection;
        }
    }

    public ListOrderedSet() {
        super(new HashSet());
        this.setOrder = new ArrayList();
    }

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set, List<E> list) {
        if (set != null) {
            if (list != null) {
                if (set.size() <= 0 && list.size() <= 0) {
                    return new ListOrderedSet<>(set, list);
                }
                throw new IllegalArgumentException("Set and List must be empty");
            }
            throw new NullPointerException("List must not be null");
        }
        throw new NullPointerException("Set must not be null");
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E e) {
        if (mo12761decorated().add(e)) {
            this.setOrder.add(e);
            return true;
        }
        return false;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        boolean z = false;
        for (E e : collection) {
            z |= add(e);
        }
        return z;
    }

    public List<E> asList() {
        return UnmodifiableList.unmodifiableList(this.setOrder);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public void clear() {
        mo12761decorated().clear();
        this.setOrder.clear();
    }

    public E get(int i) {
        return this.setOrder.get(i);
    }

    public int indexOf(Object obj) {
        return this.setOrder.indexOf(obj);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object obj) {
        boolean remove = mo12761decorated().remove(obj);
        if (remove) {
            this.setOrder.remove(obj);
        }
        return remove;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> collection) {
        Iterator<?> it2 = collection.iterator();
        boolean z = false;
        while (it2.hasNext()) {
            z |= remove(it2.next());
        }
        return z;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> collection) {
        boolean retainAll = mo12761decorated().retainAll(collection);
        if (!retainAll) {
            return false;
        }
        if (mo12761decorated().size() == 0) {
            this.setOrder.clear();
        } else {
            Iterator<E> it2 = this.setOrder.iterator();
            while (it2.hasNext()) {
                if (!mo12761decorated().contains(it2.next())) {
                    it2.remove();
                }
            }
        }
        return retainAll;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public Object[] toArray() {
        return this.setOrder.toArray();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    public String toString() {
        return this.setOrder.toString();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator  reason: collision with other method in class */
    public OrderedIterator<E> mo12756iterator() {
        return new OrderedSetIterator(this.setOrder.listIterator(), mo12761decorated());
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.setOrder.toArray(tArr);
    }

    protected ListOrderedSet(Set<E> set) {
        super(set);
        this.setOrder = new ArrayList(set);
    }

    public void add(int i, E e) {
        if (!contains(e)) {
            mo12761decorated().add(e);
            this.setOrder.add(i, e);
        }
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        for (E e : collection) {
            if (!contains(e)) {
                mo12761decorated().add(e);
                arrayList.add(e);
                z = true;
            }
        }
        if (z) {
            this.setOrder.addAll(i, arrayList);
        }
        return z;
    }

    public E remove(int i) {
        E remove = this.setOrder.remove(i);
        remove(remove);
        return remove;
    }

    protected ListOrderedSet(Set<E> set, List<E> list) {
        super(set);
        if (list != null) {
            this.setOrder = list;
            return;
        }
        throw new NullPointerException("List must not be null");
    }

    public static <E> ListOrderedSet<E> listOrderedSet(Set<E> set) {
        return new ListOrderedSet<>(set);
    }

    public static <E> ListOrderedSet<E> listOrderedSet(List<E> list) {
        if (list != null) {
            CollectionUtils.filter(list, UniquePredicate.uniquePredicate());
            return new ListOrderedSet<>(new HashSet(list), list);
        }
        throw new NullPointerException("List must not be null");
    }
}

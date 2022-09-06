package org.apache.commons.collections4.list;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public class SetUniqueList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = 7196982186153478694L;
    private final Set<E> set;

    /* loaded from: classes4.dex */
    static class SetListIterator<E> extends AbstractIteratorDecorator<E> {
        private E last;
        private final Set<E> set;

        protected SetListIterator(Iterator<E> it2, Set<E> set) {
            super(it2);
            this.last = null;
            this.set = set;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next */
        public E mo12737next() {
            this.last = (E) super.mo12737next();
            return this.last;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            super.remove();
            this.set.remove(this.last);
            this.last = null;
        }
    }

    /* loaded from: classes4.dex */
    static class SetListListIterator<E> extends AbstractListIteratorDecorator<E> {
        private E last;
        private final Set<E> set;

        protected SetListListIterator(ListIterator<E> listIterator, Set<E> set) {
            super(listIterator);
            this.last = null;
            this.set = set;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator
        public void add(E e) {
            if (!this.set.contains(e)) {
                super.add(e);
                this.set.add(e);
            }
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator, java.util.Iterator
        public E next() {
            this.last = (E) super.next();
            return this.last;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator
        public E previous() {
            this.last = (E) super.previous();
            return this.last;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator, java.util.Iterator
        public void remove() {
            super.remove();
            this.set.remove(this.last);
            this.last = null;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator
        public void set(E e) {
            throw new UnsupportedOperationException("ListIterator does not support set");
        }
    }

    protected SetUniqueList(List<E> list, Set<E> set) {
        super(list);
        if (set != null) {
            this.set = set;
            return;
        }
        throw new NullPointerException("Set must not be null");
    }

    public static <E> SetUniqueList<E> setUniqueList(List<E> list) {
        if (list != null) {
            if (list.isEmpty()) {
                return new SetUniqueList<>(list, new HashSet());
            }
            ArrayList arrayList = new ArrayList(list);
            list.clear();
            SetUniqueList<E> setUniqueList = new SetUniqueList<>(list, new HashSet());
            setUniqueList.addAll(arrayList);
            return setUniqueList;
        }
        throw new NullPointerException("List must not be null");
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E e) {
        int size = size();
        add(size(), e);
        return size != size();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return addAll(size(), collection);
    }

    public Set<E> asSet() {
        return UnmodifiableSet.unmodifiableSet(this.set);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public void clear() {
        super.clear();
        this.set.clear();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        return this.set.contains(obj);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean containsAll(Collection<?> collection) {
        return this.set.containsAll(collection);
    }

    protected Set<E> createSetBasedOnList(Set<E> set, List<E> list) {
        Set<E> hashSet;
        if (set.getClass().equals(HashSet.class)) {
            hashSet = new HashSet<>(list.size());
        } else {
            try {
                hashSet = (Set) set.getClass().newInstance();
            } catch (IllegalAccessException unused) {
                hashSet = new HashSet<>();
            } catch (InstantiationException unused2) {
                hashSet = new HashSet<>();
            }
        }
        hashSet.addAll(list);
        return hashSet;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return new SetListIterator(super.mo12756iterator(), this.set);
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public ListIterator<E> listIterator() {
        return new SetListListIterator(super.listIterator(), this.set);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object obj) {
        boolean remove = this.set.remove(obj);
        if (remove) {
            super.remove(obj);
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
        boolean retainAll = this.set.retainAll(collection);
        if (!retainAll) {
            return false;
        }
        if (this.set.size() == 0) {
            super.clear();
        } else {
            super.retainAll(this.set);
        }
        return retainAll;
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E set(int i, E e) {
        int indexOf = indexOf(e);
        E e2 = (E) super.set(i, e);
        if (indexOf != -1 && indexOf != i) {
            super.remove(indexOf);
        }
        this.set.remove(e2);
        this.set.add(e);
        return e2;
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public List<E> subList(int i, int i2) {
        List<E> subList = super.subList(i, i2);
        return ListUtils.unmodifiableList(new SetUniqueList(subList, createSetBasedOnList(this.set, subList)));
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        ArrayList arrayList = new ArrayList();
        for (E e : collection) {
            if (this.set.add(e)) {
                arrayList.add(e);
            }
        }
        return super.addAll(i, arrayList);
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public ListIterator<E> listIterator(int i) {
        return new SetListListIterator(super.listIterator(i), this.set);
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public E remove(int i) {
        E e = (E) super.remove(i);
        this.set.remove(e);
        return e;
    }

    @Override // org.apache.commons.collections4.list.AbstractListDecorator, java.util.List
    public void add(int i, E e) {
        if (!this.set.contains(e)) {
            super.add(i, e);
            this.set.add(e);
        }
    }
}

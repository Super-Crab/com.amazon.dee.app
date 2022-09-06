package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
/* loaded from: classes4.dex */
public class PredicatedList<E> extends PredicatedCollection<E> implements List<E> {
    private static final long serialVersionUID = -5722039223898659102L;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public class PredicatedListIterator extends AbstractListIteratorDecorator<E> {
        protected PredicatedListIterator(ListIterator<E> listIterator) {
            super(listIterator);
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator
        public void add(E e) {
            PredicatedList.this.validate(e);
            getListIterator().add(e);
        }

        @Override // org.apache.commons.collections4.iterators.AbstractListIteratorDecorator, java.util.ListIterator
        public void set(E e) {
            PredicatedList.this.validate(e);
            getListIterator().set(e);
        }
    }

    protected PredicatedList(List<E> list, Predicate<? super E> predicate) {
        super(list, predicate);
    }

    public static <T> PredicatedList<T> predicatedList(List<T> list, Predicate<? super T> predicate) {
        return new PredicatedList<>(list, predicate);
    }

    @Override // java.util.List
    public void add(int i, E e) {
        validate(e);
        mo12761decorated().add(i, e);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        for (E e : collection) {
            validate(e);
        }
        return mo12761decorated().addAll(i, collection);
    }

    @Override // java.util.Collection, java.util.List
    public boolean equals(Object obj) {
        return obj == this || mo12761decorated().equals(obj);
    }

    @Override // java.util.List
    public E get(int i) {
        return mo12761decorated().get(i);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return mo12761decorated().hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return mo12761decorated().indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return mo12761decorated().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    public E remove(int i) {
        return mo12761decorated().remove(i);
    }

    @Override // java.util.List
    public E set(int i, E e) {
        validate(e);
        return mo12761decorated().set(i, e);
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return new PredicatedList(mo12761decorated().subList(i, i2), this.predicate);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public List<E> mo12761decorated() {
        return (List) super.mo12761decorated();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return new PredicatedListIterator(mo12761decorated().listIterator(i));
    }
}

package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
/* loaded from: classes4.dex */
public abstract class AbstractListDecorator<E> extends AbstractCollectionDecorator<E> implements List<E> {
    private static final long serialVersionUID = 4500739654952315623L;

    protected AbstractListDecorator() {
    }

    @Override // java.util.List
    public void add(int i, E e) {
        mo12761decorated().add(i, e);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
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
        return mo12761decorated().listIterator();
    }

    @Override // java.util.List
    public E remove(int i) {
        return mo12761decorated().remove(i);
    }

    @Override // java.util.List
    public E set(int i, E e) {
        return mo12761decorated().set(i, e);
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return mo12761decorated().subList(i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractListDecorator(List<E> list) {
        super(list);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public List<E> mo12761decorated() {
        return (List) super.mo12761decorated();
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return mo12761decorated().listIterator(i);
    }
}

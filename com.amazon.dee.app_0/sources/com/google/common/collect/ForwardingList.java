package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingList<E> extends ForwardingCollection<E> implements List<E> {
    public void add(int i, E e) {
        mo8280delegate().add(i, e);
    }

    @CanIgnoreReturnValue
    public boolean addAll(int i, Collection<? extends E> collection) {
        return mo8280delegate().addAll(i, collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract List<E> mo8280delegate();

    @Override // java.util.Collection, java.util.List
    public boolean equals(@NullableDecl Object obj) {
        return obj == this || mo8280delegate().equals(obj);
    }

    @Override // java.util.List
    public E get(int i) {
        return mo8280delegate().get(i);
    }

    @Override // java.util.Collection, java.util.List
    public int hashCode() {
        return mo8280delegate().hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return mo8280delegate().indexOf(obj);
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return mo8280delegate().lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator() {
        return mo8280delegate().listIterator();
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E remove(int i) {
        return mo8280delegate().remove(i);
    }

    @Override // java.util.List
    @CanIgnoreReturnValue
    public E set(int i, E e) {
        return mo8280delegate().set(i, e);
    }

    protected boolean standardAdd(E e) {
        add(size(), e);
        return true;
    }

    protected boolean standardAddAll(int i, Iterable<? extends E> iterable) {
        return Lists.addAllImpl(this, i, iterable);
    }

    @Beta
    protected boolean standardEquals(@NullableDecl Object obj) {
        return Lists.equalsImpl(this, obj);
    }

    @Beta
    protected int standardHashCode() {
        return Lists.hashCodeImpl(this);
    }

    protected int standardIndexOf(@NullableDecl Object obj) {
        return Lists.indexOfImpl(this, obj);
    }

    protected Iterator<E> standardIterator() {
        return listIterator();
    }

    protected int standardLastIndexOf(@NullableDecl Object obj) {
        return Lists.lastIndexOfImpl(this, obj);
    }

    protected ListIterator<E> standardListIterator() {
        return listIterator(0);
    }

    @Beta
    protected List<E> standardSubList(int i, int i2) {
        return Lists.subListImpl(this, i, i2);
    }

    @Override // java.util.List
    public List<E> subList(int i, int i2) {
        return mo8280delegate().subList(i, i2);
    }

    @Override // java.util.List
    public ListIterator<E> listIterator(int i) {
        return mo8280delegate().listIterator(i);
    }

    @Beta
    protected ListIterator<E> standardListIterator(int i) {
        return Lists.listIteratorImpl(this, i);
    }
}

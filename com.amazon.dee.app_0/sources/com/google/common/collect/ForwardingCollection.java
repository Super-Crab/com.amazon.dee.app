package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingCollection<E> extends ForwardingObject implements Collection<E> {
    @CanIgnoreReturnValue
    public boolean add(E e) {
        return mo8280delegate().add(e);
    }

    @CanIgnoreReturnValue
    public boolean addAll(Collection<? extends E> collection) {
        return mo8280delegate().addAll(collection);
    }

    public void clear() {
        mo8280delegate().clear();
    }

    public boolean contains(Object obj) {
        return mo8280delegate().contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return mo8280delegate().containsAll(collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract Collection<E> mo8280delegate();

    @Override // java.util.Collection
    public boolean isEmpty() {
        return mo8280delegate().isEmpty();
    }

    public Iterator<E> iterator() {
        return mo8280delegate().iterator();
    }

    @CanIgnoreReturnValue
    public boolean remove(Object obj) {
        return mo8280delegate().remove(obj);
    }

    @CanIgnoreReturnValue
    public boolean removeAll(Collection<?> collection) {
        return mo8280delegate().removeAll(collection);
    }

    @CanIgnoreReturnValue
    public boolean retainAll(Collection<?> collection) {
        return mo8280delegate().retainAll(collection);
    }

    @Override // java.util.Collection
    public int size() {
        return mo8280delegate().size();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean standardAddAll(Collection<? extends E> collection) {
        return Iterators.addAll(this, collection.iterator());
    }

    protected void standardClear() {
        Iterators.clear(iterator());
    }

    protected boolean standardContains(@NullableDecl Object obj) {
        return Iterators.contains(iterator(), obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean standardContainsAll(Collection<?> collection) {
        return Collections2.containsAllImpl(this, collection);
    }

    protected boolean standardIsEmpty() {
        return !iterator().hasNext();
    }

    protected boolean standardRemove(@NullableDecl Object obj) {
        Iterator<E> it2 = iterator();
        while (it2.hasNext()) {
            if (Objects.equal(it2.next(), obj)) {
                it2.remove();
                return true;
            }
        }
        return false;
    }

    protected boolean standardRemoveAll(Collection<?> collection) {
        return Iterators.removeAll(iterator(), collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean standardRetainAll(Collection<?> collection) {
        return Iterators.retainAll(iterator(), collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object[] standardToArray() {
        return toArray(new Object[size()]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String standardToString() {
        return Collections2.toStringImpl(this);
    }

    public Object[] toArray() {
        return mo8280delegate().toArray();
    }

    @CanIgnoreReturnValue
    public <T> T[] toArray(T[] tArr) {
        return (T[]) mo8280delegate().toArray(tArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <T> T[] standardToArray(T[] tArr) {
        return (T[]) ObjectArrays.toArrayImpl(this, tArr);
    }
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingMultiset<E> extends ForwardingCollection<E> implements Multiset<E> {

    @Beta
    /* loaded from: classes3.dex */
    protected class StandardElementSet extends Multisets.ElementSet<E> {
        public StandardElementSet() {
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return Multisets.elementIterator(mo8035multiset().mo7764entrySet().iterator());
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        /* renamed from: multiset */
        Multiset<E> mo8035multiset() {
            return ForwardingMultiset.this;
        }
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int add(E e, int i) {
        return mo8280delegate().add(e, i);
    }

    @Override // com.google.common.collect.Multiset
    public int count(Object obj) {
        return mo8280delegate().count(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract Multiset<E> mo8280delegate();

    /* renamed from: elementSet */
    public Set<E> mo8127elementSet() {
        return mo8280delegate().mo8127elementSet();
    }

    /* renamed from: entrySet */
    public Set<Multiset.Entry<E>> mo7764entrySet() {
        return mo8280delegate().mo7764entrySet();
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public boolean equals(@NullableDecl Object obj) {
        return obj == this || mo8280delegate().equals(obj);
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public int hashCode() {
        return mo8280delegate().hashCode();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int remove(Object obj, int i) {
        return mo8280delegate().remove(obj, i);
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public int setCount(E e, int i) {
        return mo8280delegate().setCount(e, i);
    }

    protected boolean standardAdd(E e) {
        add(e, 1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    @Beta
    public boolean standardAddAll(Collection<? extends E> collection) {
        return Multisets.addAllImpl(this, collection);
    }

    @Override // com.google.common.collect.ForwardingCollection
    protected void standardClear() {
        Iterators.clear(mo7764entrySet().iterator());
    }

    @Override // com.google.common.collect.ForwardingCollection
    protected boolean standardContains(@NullableDecl Object obj) {
        return count(obj) > 0;
    }

    @Beta
    protected int standardCount(@NullableDecl Object obj) {
        for (Multiset.Entry<E> entry : mo7764entrySet()) {
            if (Objects.equal(entry.mo7624getElement(), obj)) {
                return entry.getCount();
            }
        }
        return 0;
    }

    protected boolean standardEquals(@NullableDecl Object obj) {
        return Multisets.equalsImpl(this, obj);
    }

    protected int standardHashCode() {
        return mo7764entrySet().hashCode();
    }

    protected Iterator<E> standardIterator() {
        return Multisets.iteratorImpl(this);
    }

    @Override // com.google.common.collect.ForwardingCollection
    protected boolean standardRemove(Object obj) {
        return remove(obj, 1) > 0;
    }

    @Override // com.google.common.collect.ForwardingCollection
    protected boolean standardRemoveAll(Collection<?> collection) {
        return Multisets.removeAllImpl(this, collection);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public boolean standardRetainAll(Collection<?> collection) {
        return Multisets.retainAllImpl(this, collection);
    }

    protected int standardSetCount(E e, int i) {
        return Multisets.setCountImpl(this, e, i);
    }

    protected int standardSize() {
        return Multisets.linearTimeSizeImpl(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection
    public String standardToString() {
        return mo7764entrySet().toString();
    }

    @Override // com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public boolean setCount(E e, int i, int i2) {
        return mo8280delegate().setCount(e, i, i2);
    }

    protected boolean standardSetCount(E e, int i, int i2) {
        return Multisets.setCountImpl(this, e, i, i2);
    }
}

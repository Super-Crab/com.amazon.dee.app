package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class AbstractMultiset<E> extends AbstractCollection<E> implements Multiset<E> {
    @NullableDecl
    @LazyInit
    private transient Set<E> elementSet;
    @NullableDecl
    @LazyInit
    private transient Set<Multiset.Entry<E>> entrySet;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class ElementSet extends Multisets.ElementSet<E> {
        ElementSet() {
        }

        @Override // com.google.common.collect.Multisets.ElementSet, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<E> iterator() {
            return AbstractMultiset.this.elementIterator();
        }

        @Override // com.google.common.collect.Multisets.ElementSet
        /* renamed from: multiset */
        Multiset<E> mo8035multiset() {
            return AbstractMultiset.this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public class EntrySet extends Multisets.EntrySet<E> {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Multiset.Entry<E>> iterator() {
            return AbstractMultiset.this.entryIterator();
        }

        @Override // com.google.common.collect.Multisets.EntrySet
        /* renamed from: multiset */
        Multiset<E> mo7596multiset() {
            return AbstractMultiset.this;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return AbstractMultiset.this.distinctElements();
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean add(@NullableDecl E e) {
        add(e, 1);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    @CanIgnoreReturnValue
    public final boolean addAll(Collection<? extends E> collection) {
        return Multisets.addAllImpl(this, collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public abstract void clear();

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    public boolean contains(@NullableDecl Object obj) {
        return count(obj) > 0;
    }

    /* renamed from: createElementSet */
    Set<E> mo7573createElementSet() {
        return new ElementSet();
    }

    Set<Multiset.Entry<E>> createEntrySet() {
        return new EntrySet();
    }

    abstract int distinctElements();

    abstract Iterator<E> elementIterator();

    @Override // com.google.common.collect.Multiset
    /* renamed from: elementSet */
    public Set<E> mo8127elementSet() {
        Set<E> set = this.elementSet;
        if (set == null) {
            Set<E> mo7573createElementSet = mo7573createElementSet();
            this.elementSet = mo7573createElementSet;
            return mo7573createElementSet;
        }
        return set;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Iterator<Multiset.Entry<E>> entryIterator();

    @Override // com.google.common.collect.Multiset
    /* renamed from: entrySet */
    public Set<Multiset.Entry<E>> mo7764entrySet() {
        Set<Multiset.Entry<E>> set = this.entrySet;
        if (set == null) {
            Set<Multiset.Entry<E>> createEntrySet = createEntrySet();
            this.entrySet = createEntrySet;
            return createEntrySet;
        }
        return set;
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public final boolean equals(@NullableDecl Object obj) {
        return Multisets.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, com.google.common.collect.Multiset
    public final int hashCode() {
        return mo7764entrySet().hashCode();
    }

    @Override // java.util.AbstractCollection, java.util.Collection
    public boolean isEmpty() {
        return mo7764entrySet().isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean remove(@NullableDecl Object obj) {
        return remove(obj, 1) > 0;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean removeAll(Collection<?> collection) {
        return Multisets.removeAllImpl(this, collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, com.google.common.collect.Multiset
    @CanIgnoreReturnValue
    public final boolean retainAll(Collection<?> collection) {
        return Multisets.retainAllImpl(this, collection);
    }

    @CanIgnoreReturnValue
    public int setCount(@NullableDecl E e, int i) {
        return Multisets.setCountImpl(this, e, i);
    }

    @Override // java.util.AbstractCollection, com.google.common.collect.Multiset
    public final String toString() {
        return mo7764entrySet().toString();
    }

    @CanIgnoreReturnValue
    public int add(@NullableDecl E e, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public int remove(@NullableDecl Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    @CanIgnoreReturnValue
    public boolean setCount(@NullableDecl E e, int i, int i2) {
        return Multisets.setCountImpl(this, e, i, i2);
    }
}

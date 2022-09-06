package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.ListIterator;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingListIterator<E> extends ForwardingIterator<E> implements ListIterator<E> {
    protected ForwardingListIterator() {
    }

    @Override // java.util.ListIterator
    public void add(E e) {
        mo8280delegate().add(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingIterator, com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract ListIterator<E> mo8280delegate();

    @Override // java.util.ListIterator
    public boolean hasPrevious() {
        return mo8280delegate().hasPrevious();
    }

    @Override // java.util.ListIterator
    public int nextIndex() {
        return mo8280delegate().nextIndex();
    }

    @Override // java.util.ListIterator
    @CanIgnoreReturnValue
    public E previous() {
        return mo8280delegate().previous();
    }

    @Override // java.util.ListIterator
    public int previousIndex() {
        return mo8280delegate().previousIndex();
    }

    @Override // java.util.ListIterator
    public void set(E e) {
        mo8280delegate().set(e);
    }
}

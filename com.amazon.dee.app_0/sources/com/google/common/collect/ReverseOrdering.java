package com.google.common.collect;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.Iterator;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* JADX INFO: Access modifiers changed from: package-private */
@GwtCompatible(serializable = true)
/* loaded from: classes3.dex */
public final class ReverseOrdering<T> extends Ordering<T> implements Serializable {
    private static final long serialVersionUID = 0;
    final Ordering<? super T> forwardOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ReverseOrdering(Ordering<? super T> ordering) {
        this.forwardOrder = (Ordering) Preconditions.checkNotNull(ordering);
    }

    @Override // com.google.common.collect.Ordering, java.util.Comparator
    public int compare(T t, T t2) {
        return this.forwardOrder.compare(t2, t);
    }

    @Override // java.util.Comparator
    public boolean equals(@NullableDecl Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ReverseOrdering)) {
            return false;
        }
        return this.forwardOrder.equals(((ReverseOrdering) obj).forwardOrder);
    }

    public int hashCode() {
        return -this.forwardOrder.hashCode();
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(E e, E e2) {
        return (E) this.forwardOrder.min(e, e2);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(E e, E e2) {
        return (E) this.forwardOrder.max(e, e2);
    }

    @Override // com.google.common.collect.Ordering
    public <S extends T> Ordering<S> reverse() {
        return (Ordering<? super T>) this.forwardOrder;
    }

    public String toString() {
        String valueOf = String.valueOf(this.forwardOrder);
        return GeneratedOutlineSupport1.outline29(valueOf.length() + 10, valueOf, ".reverse()");
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E max(E e, E e2, E e3, E... eArr) {
        return (E) this.forwardOrder.min(e, e2, e3, eArr);
    }

    @Override // com.google.common.collect.Ordering
    public <E extends T> E min(E e, E e2, E e3, E... eArr) {
        return (E) this.forwardOrder.max(e, e2, e3, eArr);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: max */
    public <E extends T> E mo8014max(Iterator<E> it2) {
        return (E) this.forwardOrder.mo8016min(it2);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: min */
    public <E extends T> E mo8016min(Iterator<E> it2) {
        return (E) this.forwardOrder.mo8014max(it2);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: max */
    public <E extends T> E mo8013max(Iterable<E> iterable) {
        return (E) this.forwardOrder.mo8015min(iterable);
    }

    @Override // com.google.common.collect.Ordering
    /* renamed from: min */
    public <E extends T> E mo8015min(Iterable<E> iterable) {
        return (E) this.forwardOrder.mo8013max(iterable);
    }
}

package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Iterator;
@GwtCompatible
/* loaded from: classes3.dex */
public abstract class ForwardingIterator<T> extends ForwardingObject implements Iterator<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract Iterator<T> mo8280delegate();

    @Override // java.util.Iterator
    public boolean hasNext() {
        return mo8280delegate().hasNext();
    }

    @CanIgnoreReturnValue
    /* renamed from: next */
    public T mo7595next() {
        return mo8280delegate().next();
    }

    public void remove() {
        mo8280delegate().remove();
    }
}

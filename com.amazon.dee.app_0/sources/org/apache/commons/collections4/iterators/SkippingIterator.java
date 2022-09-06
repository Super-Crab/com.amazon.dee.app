package org.apache.commons.collections4.iterators;

import java.util.Iterator;
/* loaded from: classes4.dex */
public class SkippingIterator<E> extends AbstractIteratorDecorator<E> {
    private final long offset;
    private long pos;

    public SkippingIterator(Iterator<E> it2, long j) {
        super(it2);
        if (j >= 0) {
            this.offset = j;
            this.pos = 0L;
            init();
            return;
        }
        throw new IllegalArgumentException("Offset parameter must not be negative.");
    }

    private void init() {
        while (this.pos < this.offset && hasNext()) {
            mo12737next();
        }
    }

    @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
    /* renamed from: next */
    public E mo12737next() {
        E e = (E) super.mo12737next();
        this.pos++;
        return e;
    }

    @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
    public void remove() {
        if (this.pos > this.offset) {
            super.remove();
            return;
        }
        throw new IllegalStateException("remove() can not be called before calling next()");
    }
}

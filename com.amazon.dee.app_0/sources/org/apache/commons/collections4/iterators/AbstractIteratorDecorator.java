package org.apache.commons.collections4.iterators;

import java.util.Iterator;
/* loaded from: classes4.dex */
public abstract class AbstractIteratorDecorator<E> extends AbstractUntypedIteratorDecorator<E, E> {
    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractIteratorDecorator(Iterator<E> it2) {
        super(it2);
    }

    @Override // java.util.Iterator
    /* renamed from: next */
    public E mo12737next() {
        return getIterator().next();
    }
}

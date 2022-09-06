package org.apache.commons.collections4.iterators;

import java.util.Iterator;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class TransformIterator<I, O> implements Iterator<O> {
    private Iterator<? extends I> iterator;
    private Transformer<? super I, ? extends O> transformer;

    public TransformIterator() {
    }

    public Iterator<? extends I> getIterator() {
        return this.iterator;
    }

    public Transformer<? super I, ? extends O> getTransformer() {
        return this.transformer;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.iterator.hasNext();
    }

    @Override // java.util.Iterator
    public O next() {
        return transform(this.iterator.next());
    }

    @Override // java.util.Iterator
    public void remove() {
        this.iterator.remove();
    }

    public void setIterator(Iterator<? extends I> it2) {
        this.iterator = it2;
    }

    public void setTransformer(Transformer<? super I, ? extends O> transformer) {
        this.transformer = transformer;
    }

    protected O transform(I i) {
        return this.transformer.mo12738transform(i);
    }

    public TransformIterator(Iterator<? extends I> it2) {
        this.iterator = it2;
    }

    public TransformIterator(Iterator<? extends I> it2, Transformer<? super I, ? extends O> transformer) {
        this.iterator = it2;
        this.transformer = transformer;
    }
}

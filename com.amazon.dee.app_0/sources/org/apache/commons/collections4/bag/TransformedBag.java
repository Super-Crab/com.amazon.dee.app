package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.set.TransformedSet;
/* loaded from: classes4.dex */
public class TransformedBag<E> extends TransformedCollection<E> implements Bag<E> {
    private static final long serialVersionUID = 5421170911299074185L;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        super(bag, transformer);
    }

    public static <E> Bag<E> transformedBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        TransformedBag transformedBag = new TransformedBag(bag, transformer);
        if (bag.size() > 0) {
            Object[] array = bag.toArray();
            bag.clear();
            for (Object obj : array) {
                transformedBag.mo12761decorated().add(transformer.mo12738transform(obj));
            }
        }
        return transformedBag;
    }

    public static <E> Bag<E> transformingBag(Bag<E> bag, Transformer<? super E, ? extends E> transformer) {
        return new TransformedBag(bag, transformer);
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean add(E e, int i) {
        return getBag().add(transform((TransformedBag<E>) e), i);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return obj == this || mo12761decorated().equals(obj);
    }

    protected Bag<E> getBag() {
        return (Bag) mo12761decorated();
    }

    @Override // org.apache.commons.collections4.Bag
    public int getCount(Object obj) {
        return getBag().getCount(obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return mo12761decorated().hashCode();
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean remove(Object obj, int i) {
        return getBag().remove(obj, i);
    }

    @Override // org.apache.commons.collections4.Bag
    public Set<E> uniqueSet() {
        return TransformedSet.transformingSet(getBag().uniqueSet(), this.transformer);
    }
}

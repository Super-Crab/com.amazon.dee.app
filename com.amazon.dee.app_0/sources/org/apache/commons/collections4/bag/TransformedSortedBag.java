package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class TransformedSortedBag<E> extends TransformedBag<E> implements SortedBag<E> {
    private static final long serialVersionUID = -251737742649401930L;

    protected TransformedSortedBag(SortedBag<E> sortedBag, Transformer<? super E, ? extends E> transformer) {
        super(sortedBag, transformer);
    }

    public static <E> TransformedSortedBag<E> transformedSortedBag(SortedBag<E> sortedBag, Transformer<? super E, ? extends E> transformer) {
        TransformedSortedBag<E> transformedSortedBag = new TransformedSortedBag<>(sortedBag, transformer);
        if (sortedBag.size() > 0) {
            Object[] array = sortedBag.toArray();
            sortedBag.clear();
            for (Object obj : array) {
                transformedSortedBag.mo12761decorated().add(transformer.mo12738transform(obj));
            }
        }
        return transformedSortedBag;
    }

    public static <E> TransformedSortedBag<E> transformingSortedBag(SortedBag<E> sortedBag, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSortedBag<>(sortedBag, transformer);
    }

    @Override // org.apache.commons.collections4.SortedBag
    public Comparator<? super E> comparator() {
        return getSortedBag().comparator();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E first() {
        return getSortedBag().first();
    }

    protected SortedBag<E> getSortedBag() {
        return (SortedBag) mo12761decorated();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E last() {
        return getSortedBag().last();
    }
}

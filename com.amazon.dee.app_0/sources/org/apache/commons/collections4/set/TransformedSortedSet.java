package org.apache.commons.collections4.set;

import java.util.Comparator;
import java.util.SortedSet;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class TransformedSortedSet<E> extends TransformedSet<E> implements SortedSet<E> {
    private static final long serialVersionUID = -1675486811351124386L;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransformedSortedSet(SortedSet<E> sortedSet, Transformer<? super E, ? extends E> transformer) {
        super(sortedSet, transformer);
    }

    public static <E> TransformedSortedSet<E> transformedSortedSet(SortedSet<E> sortedSet, Transformer<? super E, ? extends E> transformer) {
        TransformedSortedSet<E> transformedSortedSet = new TransformedSortedSet<>(sortedSet, transformer);
        if (sortedSet.size() > 0) {
            Object[] array = sortedSet.toArray();
            sortedSet.clear();
            for (Object obj : array) {
                transformedSortedSet.mo12761decorated().add(transformer.mo12738transform(obj));
            }
        }
        return transformedSortedSet;
    }

    public static <E> TransformedSortedSet<E> transformingSortedSet(SortedSet<E> sortedSet, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSortedSet<>(sortedSet, transformer);
    }

    @Override // java.util.SortedSet
    public Comparator<? super E> comparator() {
        return getSortedSet().comparator();
    }

    @Override // java.util.SortedSet
    public E first() {
        return getSortedSet().first();
    }

    protected SortedSet<E> getSortedSet() {
        return (SortedSet) mo12761decorated();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> headSet(E e) {
        return new TransformedSortedSet(getSortedSet().headSet(e), this.transformer);
    }

    @Override // java.util.SortedSet
    public E last() {
        return getSortedSet().last();
    }

    @Override // java.util.SortedSet
    public SortedSet<E> subSet(E e, E e2) {
        return new TransformedSortedSet(getSortedSet().subSet(e, e2), this.transformer);
    }

    @Override // java.util.SortedSet
    public SortedSet<E> tailSet(E e) {
        return new TransformedSortedSet(getSortedSet().tailSet(e), this.transformer);
    }
}

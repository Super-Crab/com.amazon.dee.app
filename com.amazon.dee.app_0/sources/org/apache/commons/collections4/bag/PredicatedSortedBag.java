package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.SortedBag;
/* loaded from: classes4.dex */
public class PredicatedSortedBag<E> extends PredicatedBag<E> implements SortedBag<E> {
    private static final long serialVersionUID = 3448581314086406616L;

    protected PredicatedSortedBag(SortedBag<E> sortedBag, Predicate<? super E> predicate) {
        super(sortedBag, predicate);
    }

    public static <E> PredicatedSortedBag<E> predicatedSortedBag(SortedBag<E> sortedBag, Predicate<? super E> predicate) {
        return new PredicatedSortedBag<>(sortedBag, predicate);
    }

    @Override // org.apache.commons.collections4.SortedBag
    public Comparator<? super E> comparator() {
        return mo12761decorated().comparator();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E first() {
        return mo12761decorated().first();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E last() {
        return mo12761decorated().last();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.bag.PredicatedBag, org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated */
    public SortedBag<E> mo12761decorated() {
        return (SortedBag) super.mo12761decorated();
    }
}

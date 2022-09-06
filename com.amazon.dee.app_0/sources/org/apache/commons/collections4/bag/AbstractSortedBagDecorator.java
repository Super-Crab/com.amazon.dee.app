package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.SortedBag;
/* loaded from: classes4.dex */
public abstract class AbstractSortedBagDecorator<E> extends AbstractBagDecorator<E> implements SortedBag<E> {
    private static final long serialVersionUID = -8223473624050467718L;

    protected AbstractSortedBagDecorator() {
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
    public AbstractSortedBagDecorator(SortedBag<E> sortedBag) {
        super(sortedBag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.bag.AbstractBagDecorator, org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated */
    public SortedBag<E> mo12761decorated() {
        return (SortedBag) super.mo12761decorated();
    }
}

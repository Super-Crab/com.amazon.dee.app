package org.apache.commons.collections4.bag;

import java.util.Comparator;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.SortedBag;
/* loaded from: classes4.dex */
public class SynchronizedSortedBag<E> extends SynchronizedBag<E> implements SortedBag<E> {
    private static final long serialVersionUID = 722374056718497858L;

    protected SynchronizedSortedBag(SortedBag<E> sortedBag) {
        super(sortedBag);
    }

    public static <E> SynchronizedSortedBag<E> synchronizedSortedBag(SortedBag<E> sortedBag) {
        return new SynchronizedSortedBag<>(sortedBag);
    }

    @Override // org.apache.commons.collections4.SortedBag
    public synchronized Comparator<? super E> comparator() {
        Comparator<? super E> comparator;
        synchronized (this.lock) {
            comparator = getSortedBag().comparator();
        }
        return comparator;
    }

    @Override // org.apache.commons.collections4.SortedBag
    public synchronized E first() {
        E first;
        synchronized (this.lock) {
            first = getSortedBag().first();
        }
        return first;
    }

    protected SortedBag<E> getSortedBag() {
        return (SortedBag) mo12751decorated();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public synchronized E last() {
        E last;
        synchronized (this.lock) {
            last = getSortedBag().last();
        }
        return last;
    }

    protected SynchronizedSortedBag(Bag<E> bag, Object obj) {
        super(bag, obj);
    }
}

package com.google.common.collect;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.Multiset;
import com.google.common.collect.SortedMultisets;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
@Beta
@GwtCompatible(emulated = true)
/* loaded from: classes3.dex */
public abstract class ForwardingSortedMultiset<E> extends ForwardingMultiset<E> implements SortedMultiset<E> {

    /* loaded from: classes3.dex */
    protected abstract class StandardDescendingMultiset extends DescendingMultiset<E> {
        public StandardDescendingMultiset() {
        }

        @Override // com.google.common.collect.DescendingMultiset
        SortedMultiset<E> forwardMultiset() {
            return ForwardingSortedMultiset.this;
        }
    }

    /* loaded from: classes3.dex */
    protected class StandardElementSet extends SortedMultisets.NavigableElementSet<E> {
        public StandardElementSet(ForwardingSortedMultiset forwardingSortedMultiset) {
            super(forwardingSortedMultiset);
        }
    }

    protected ForwardingSortedMultiset() {
    }

    @Override // com.google.common.collect.SortedMultiset, com.google.common.collect.SortedIterable
    public Comparator<? super E> comparator() {
        return mo8280delegate().comparator();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract SortedMultiset<E> mo8280delegate();

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: descendingMultiset */
    public SortedMultiset<E> mo7833descendingMultiset() {
        return mo8280delegate().mo7833descendingMultiset();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> firstEntry() {
        return mo8280delegate().firstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: headMultiset */
    public SortedMultiset<E> mo8006headMultiset(E e, BoundType boundType) {
        return mo8280delegate().mo8006headMultiset(e, boundType);
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> lastEntry() {
        return mo8280delegate().lastEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollFirstEntry() {
        return mo8280delegate().pollFirstEntry();
    }

    @Override // com.google.common.collect.SortedMultiset
    public Multiset.Entry<E> pollLastEntry() {
        return mo8280delegate().pollLastEntry();
    }

    protected Multiset.Entry<E> standardFirstEntry() {
        Iterator<Multiset.Entry<E>> it2 = mo7764entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it2.next();
        return Multisets.immutableEntry(next.mo7624getElement(), next.getCount());
    }

    protected Multiset.Entry<E> standardLastEntry() {
        Iterator<Multiset.Entry<E>> it2 = mo7833descendingMultiset().mo7764entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it2.next();
        return Multisets.immutableEntry(next.mo7624getElement(), next.getCount());
    }

    protected Multiset.Entry<E> standardPollFirstEntry() {
        Iterator<Multiset.Entry<E>> it2 = mo7764entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it2.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.mo7624getElement(), next.getCount());
        it2.remove();
        return immutableEntry;
    }

    protected Multiset.Entry<E> standardPollLastEntry() {
        Iterator<Multiset.Entry<E>> it2 = mo7833descendingMultiset().mo7764entrySet().iterator();
        if (!it2.hasNext()) {
            return null;
        }
        Multiset.Entry<E> next = it2.next();
        Multiset.Entry<E> immutableEntry = Multisets.immutableEntry(next.mo7624getElement(), next.getCount());
        it2.remove();
        return immutableEntry;
    }

    protected SortedMultiset<E> standardSubMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return mo8007tailMultiset(e, boundType).mo8006headMultiset(e2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: subMultiset */
    public SortedMultiset<E> mo7837subMultiset(E e, BoundType boundType, E e2, BoundType boundType2) {
        return mo8280delegate().mo7837subMultiset(e, boundType, e2, boundType2);
    }

    @Override // com.google.common.collect.SortedMultiset
    /* renamed from: tailMultiset */
    public SortedMultiset<E> mo8007tailMultiset(E e, BoundType boundType) {
        return mo8280delegate().mo8007tailMultiset(e, boundType);
    }

    @Override // com.google.common.collect.ForwardingMultiset, com.google.common.collect.Multiset
    /* renamed from: elementSet */
    public NavigableSet<E> mo8127elementSet() {
        return mo8280delegate().mo8127elementSet();
    }
}

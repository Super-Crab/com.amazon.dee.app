package org.apache.commons.collections4.multiset;

import java.util.Set;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
/* loaded from: classes4.dex */
public class PredicatedMultiSet<E> extends PredicatedCollection<E> implements MultiSet<E> {
    private static final long serialVersionUID = 20150629;

    protected PredicatedMultiSet(MultiSet<E> multiSet, Predicate<? super E> predicate) {
        super(multiSet, predicate);
    }

    public static <E> PredicatedMultiSet<E> predicatedMultiSet(MultiSet<E> multiSet, Predicate<? super E> predicate) {
        return new PredicatedMultiSet<>(multiSet, predicate);
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int add(E e, int i) {
        validate(e);
        return mo12761decorated().add(e, i);
    }

    @Override // org.apache.commons.collections4.MultiSet
    public Set<MultiSet.Entry<E>> entrySet() {
        return mo12761decorated().entrySet();
    }

    @Override // java.util.Collection, org.apache.commons.collections4.MultiSet
    public boolean equals(Object obj) {
        return obj == this || mo12761decorated().equals(obj);
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int getCount(Object obj) {
        return mo12761decorated().getCount(obj);
    }

    @Override // java.util.Collection, org.apache.commons.collections4.MultiSet
    public int hashCode() {
        return mo12761decorated().hashCode();
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int remove(Object obj, int i) {
        return mo12761decorated().remove(obj, i);
    }

    @Override // org.apache.commons.collections4.MultiSet
    public int setCount(E e, int i) {
        validate(e);
        return mo12761decorated().setCount(e, i);
    }

    @Override // org.apache.commons.collections4.MultiSet
    public Set<E> uniqueSet() {
        return mo12761decorated().uniqueSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public MultiSet<E> mo12761decorated() {
        return (MultiSet) super.mo12761decorated();
    }
}

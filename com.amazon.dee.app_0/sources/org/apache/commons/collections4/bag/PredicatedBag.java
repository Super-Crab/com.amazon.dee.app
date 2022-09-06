package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
/* loaded from: classes4.dex */
public class PredicatedBag<E> extends PredicatedCollection<E> implements Bag<E> {
    private static final long serialVersionUID = -2575833140344736876L;

    /* JADX INFO: Access modifiers changed from: protected */
    public PredicatedBag(Bag<E> bag, Predicate<? super E> predicate) {
        super(bag, predicate);
    }

    public static <E> PredicatedBag<E> predicatedBag(Bag<E> bag, Predicate<? super E> predicate) {
        return new PredicatedBag<>(bag, predicate);
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean add(E e, int i) {
        validate(e);
        return mo12761decorated().add(e, i);
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        return obj == this || mo12761decorated().equals(obj);
    }

    @Override // org.apache.commons.collections4.Bag
    public int getCount(Object obj) {
        return mo12761decorated().getCount(obj);
    }

    @Override // java.util.Collection
    public int hashCode() {
        return mo12761decorated().hashCode();
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean remove(Object obj, int i) {
        return mo12761decorated().remove(obj, i);
    }

    @Override // org.apache.commons.collections4.Bag
    public Set<E> uniqueSet() {
        return mo12761decorated().uniqueSet();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public Bag<E> mo12761decorated() {
        return (Bag) super.mo12761decorated();
    }
}

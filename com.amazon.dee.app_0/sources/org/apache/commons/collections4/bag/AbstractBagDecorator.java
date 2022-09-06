package org.apache.commons.collections4.bag;

import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
/* loaded from: classes4.dex */
public abstract class AbstractBagDecorator<E> extends AbstractCollectionDecorator<E> implements Bag<E> {
    private static final long serialVersionUID = -3768146017343785417L;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractBagDecorator() {
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean add(E e, int i) {
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
    public AbstractBagDecorator(Bag<E> bag) {
        super(bag);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public Bag<E> mo12761decorated() {
        return (Bag) super.mo12761decorated();
    }
}

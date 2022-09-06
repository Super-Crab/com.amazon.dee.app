package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
/* loaded from: classes4.dex */
public abstract class AbstractSetDecorator<E> extends AbstractCollectionDecorator<E> implements Set<E> {
    private static final long serialVersionUID = -4678668309576958546L;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSetDecorator() {
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        return obj == this || mo12761decorated().equals(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return mo12761decorated().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSetDecorator(Set<E> set) {
        super(set);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public Set<E> mo12761decorated() {
        return (Set) super.mo12761decorated();
    }
}

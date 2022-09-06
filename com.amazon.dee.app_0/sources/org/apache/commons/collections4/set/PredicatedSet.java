package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.collection.PredicatedCollection;
/* loaded from: classes4.dex */
public class PredicatedSet<E> extends PredicatedCollection<E> implements Set<E> {
    private static final long serialVersionUID = -684521469108685117L;

    /* JADX INFO: Access modifiers changed from: protected */
    public PredicatedSet(Set<E> set, Predicate<? super E> predicate) {
        super(set, predicate);
    }

    public static <E> PredicatedSet<E> predicatedSet(Set<E> set, Predicate<? super E> predicate) {
        return new PredicatedSet<>(set, predicate);
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
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator
    /* renamed from: decorated  reason: collision with other method in class */
    public Set<E> mo12761decorated() {
        return (Set) super.mo12761decorated();
    }
}

package org.apache.commons.collections4.set;

import java.util.Set;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.collection.TransformedCollection;
/* loaded from: classes4.dex */
public class TransformedSet<E> extends TransformedCollection<E> implements Set<E> {
    private static final long serialVersionUID = 306127383500410386L;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransformedSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        super(set, transformer);
    }

    public static <E> Set<E> transformedSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        TransformedSet transformedSet = new TransformedSet(set, transformer);
        if (set.size() > 0) {
            Object[] array = set.toArray();
            set.clear();
            for (Object obj : array) {
                transformedSet.mo12761decorated().add(transformer.mo12738transform(obj));
            }
        }
        return transformedSet;
    }

    public static <E> TransformedSet<E> transformingSet(Set<E> set, Transformer<? super E, ? extends E> transformer) {
        return new TransformedSet<>(set, transformer);
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        return obj == this || mo12761decorated().equals(obj);
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return mo12761decorated().hashCode();
    }
}

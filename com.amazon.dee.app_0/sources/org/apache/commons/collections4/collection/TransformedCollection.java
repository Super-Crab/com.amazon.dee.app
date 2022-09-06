package org.apache.commons.collections4.collection;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.collections4.Transformer;
/* loaded from: classes4.dex */
public class TransformedCollection<E> extends AbstractCollectionDecorator<E> {
    private static final long serialVersionUID = 8692300188161871514L;
    protected final Transformer<? super E, ? extends E> transformer;

    /* JADX INFO: Access modifiers changed from: protected */
    public TransformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        super(collection);
        if (transformer != null) {
            this.transformer = transformer;
            return;
        }
        throw new NullPointerException("Transformer must not be null");
    }

    public static <E> TransformedCollection<E> transformedCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        TransformedCollection<E> transformedCollection = new TransformedCollection<>(collection, transformer);
        if (collection.size() > 0) {
            Object[] array = collection.toArray();
            collection.clear();
            for (Object obj : array) {
                transformedCollection.mo12761decorated().add(transformer.mo12738transform(obj));
            }
        }
        return transformedCollection;
    }

    public static <E> TransformedCollection<E> transformingCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        return new TransformedCollection<>(collection, transformer);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E e) {
        return mo12761decorated().add(transform((TransformedCollection<E>) e));
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        return mo12761decorated().addAll(transform((Collection) collection));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public E transform(E e) {
        return this.transformer.mo12738transform(e);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Collection<E> transform(Collection<? extends E> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (E e : collection) {
            arrayList.add(transform((TransformedCollection<E>) e));
        }
        return arrayList;
    }
}

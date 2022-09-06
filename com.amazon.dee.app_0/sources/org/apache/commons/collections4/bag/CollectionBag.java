package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.collections4.Bag;
/* loaded from: classes4.dex */
public final class CollectionBag<E> extends AbstractBagDecorator<E> {
    private static final long serialVersionUID = -2560033712679053143L;

    public CollectionBag(Bag<E> bag) {
        super(bag);
    }

    public static <E> Bag<E> collectionBag(Bag<E> bag) {
        return new CollectionBag(bag);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        setCollection((Collection) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(mo12761decorated());
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean add(E e) {
        return add(e, 1);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        while (true) {
            boolean z = false;
            for (E e : collection) {
                boolean add = add(e, 1);
                if (z || add) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean containsAll(Collection<?> collection) {
        Iterator<?> it2 = collection.iterator();
        while (it2.hasNext()) {
            if (!contains(it2.next())) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object obj) {
        return remove(obj, 1);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            while (true) {
                boolean z = false;
                for (Object obj : collection) {
                    boolean remove = remove(obj, getCount(obj));
                    if (z || remove) {
                        z = true;
                    }
                }
                return z;
            }
        }
        return mo12761decorated().removeAll(null);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> collection) {
        if (collection != null) {
            boolean z = false;
            Iterator<E> mo12756iterator = mo12756iterator();
            while (mo12756iterator.hasNext()) {
                if (!collection.contains(mo12756iterator.next())) {
                    mo12756iterator.remove();
                    z = true;
                }
            }
            return z;
        }
        return mo12761decorated().retainAll(null);
    }

    @Override // org.apache.commons.collections4.bag.AbstractBagDecorator, org.apache.commons.collections4.Bag
    public boolean add(E e, int i) {
        mo12761decorated().add(e, i);
        return true;
    }
}

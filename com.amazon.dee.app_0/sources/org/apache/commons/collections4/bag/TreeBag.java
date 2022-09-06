package org.apache.commons.collections4.bag;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.bag.AbstractMapBag;
/* loaded from: classes4.dex */
public class TreeBag<E> extends AbstractMapBag<E> implements SortedBag<E>, Serializable {
    private static final long serialVersionUID = -7740146511091606676L;

    public TreeBag() {
        super(new TreeMap());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        super.doReadObject(new TreeMap((Comparator) objectInputStream.readObject()), objectInputStream);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(comparator());
        super.doWriteObject(objectOutputStream);
    }

    @Override // org.apache.commons.collections4.bag.AbstractMapBag, org.apache.commons.collections4.Bag, java.util.Collection
    public boolean add(E e) {
        if (comparator() != null || (e instanceof Comparable)) {
            return super.add(e);
        }
        if (e == null) {
            throw new NullPointerException();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Objects of type ");
        outline107.append(e.getClass());
        outline107.append(" cannot be added to ");
        outline107.append("a naturally ordered TreeBag as it does not implement Comparable");
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // org.apache.commons.collections4.SortedBag
    public Comparator<? super E> comparator() {
        return mo12638getMap().comparator();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E first() {
        return mo12638getMap().firstKey();
    }

    @Override // org.apache.commons.collections4.SortedBag
    public E last() {
        return mo12638getMap().lastKey();
    }

    public TreeBag(Comparator<? super E> comparator) {
        super(new TreeMap(comparator));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.bag.AbstractMapBag
    /* renamed from: getMap  reason: collision with other method in class */
    public SortedMap<E, AbstractMapBag.MutableInteger> mo12638getMap() {
        return (SortedMap) super.mo12638getMap();
    }

    public TreeBag(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }
}

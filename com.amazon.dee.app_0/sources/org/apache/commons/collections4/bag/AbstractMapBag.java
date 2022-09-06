package org.apache.commons.collections4.bag;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;
/* loaded from: classes4.dex */
public abstract class AbstractMapBag<E> implements Bag<E> {
    private transient Map<E, MutableInteger> map;
    private transient int modCount;
    private int size;
    private transient Set<E> uniqueSet;

    /* loaded from: classes4.dex */
    static class BagIterator<E> implements Iterator<E> {
        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;
        private int itemCount;
        private final int mods;
        private final AbstractMapBag<E> parent;
        private Map.Entry<E, MutableInteger> current = null;
        private boolean canRemove = false;

        public BagIterator(AbstractMapBag<E> abstractMapBag) {
            this.parent = abstractMapBag;
            this.entryIterator = ((AbstractMapBag) abstractMapBag).map.entrySet().iterator();
            this.mods = ((AbstractMapBag) abstractMapBag).modCount;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        @Override // java.util.Iterator
        public E next() {
            if (((AbstractMapBag) this.parent).modCount == this.mods) {
                if (this.itemCount == 0) {
                    this.current = this.entryIterator.next();
                    this.itemCount = this.current.getValue().value;
                }
                this.canRemove = true;
                this.itemCount--;
                return this.current.getKey();
            }
            throw new ConcurrentModificationException();
        }

        @Override // java.util.Iterator
        public void remove() {
            if (((AbstractMapBag) this.parent).modCount == this.mods) {
                if (this.canRemove) {
                    MutableInteger value = this.current.getValue();
                    int i = value.value;
                    if (i > 1) {
                        value.value = i - 1;
                    } else {
                        this.entryIterator.remove();
                    }
                    AbstractMapBag.access$210(this.parent);
                    this.canRemove = false;
                    return;
                }
                throw new IllegalStateException();
            }
            throw new ConcurrentModificationException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class MutableInteger {
        protected int value;

        MutableInteger(int i) {
            this.value = i;
        }

        public boolean equals(Object obj) {
            return (obj instanceof MutableInteger) && ((MutableInteger) obj).value == this.value;
        }

        public int hashCode() {
            return this.value;
        }
    }

    protected AbstractMapBag() {
    }

    static /* synthetic */ int access$210(AbstractMapBag abstractMapBag) {
        int i = abstractMapBag.size;
        abstractMapBag.size = i - 1;
        return i;
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection
    public boolean add(E e) {
        return add(e, 1);
    }

    @Override // java.util.Collection
    public boolean addAll(Collection<? extends E> collection) {
        while (true) {
            boolean z = false;
            for (E e : collection) {
                boolean add = add(e);
                if (z || add) {
                    z = true;
                }
            }
            return z;
        }
    }

    @Override // java.util.Collection
    public void clear() {
        this.modCount++;
        this.map.clear();
        this.size = 0;
    }

    @Override // java.util.Collection
    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Bag) {
            return containsAll((Bag) collection);
        }
        return containsAll((Bag<?>) new HashBag(collection));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void doReadObject(Map<E, MutableInteger> map, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.map = map;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            int readInt2 = objectInputStream.readInt();
            map.put(readObject, new MutableInteger(readInt2));
            this.size += readInt2;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.map.size());
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(entry.getValue().value);
        }
    }

    @Override // java.util.Collection
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bag)) {
            return false;
        }
        Bag bag = (Bag) obj;
        if (bag.size() != size()) {
            return false;
        }
        for (E e : this.map.keySet()) {
            if (bag.getCount(e) != getCount(e)) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.commons.collections4.Bag
    public int getCount(Object obj) {
        MutableInteger mutableInteger = this.map.get(obj);
        if (mutableInteger != null) {
            return mutableInteger.value;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: getMap */
    public Map<E, MutableInteger> mo12638getMap() {
        return this.map;
    }

    @Override // java.util.Collection
    public int hashCode() {
        int i = 0;
        for (Map.Entry<E, MutableInteger> entry : this.map.entrySet()) {
            E key = entry.getKey();
            i += entry.getValue().value ^ (key == null ? 0 : key.hashCode());
        }
        return i;
    }

    @Override // java.util.Collection
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection, java.lang.Iterable
    /* renamed from: iterator */
    public Iterator<E> mo12756iterator() {
        return new BagIterator(this);
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection
    public boolean remove(Object obj) {
        MutableInteger mutableInteger = this.map.get(obj);
        if (mutableInteger == null) {
            return false;
        }
        this.modCount++;
        this.map.remove(obj);
        this.size -= mutableInteger.value;
        return true;
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        if (collection != null) {
            Iterator<?> it2 = collection.iterator();
            while (true) {
                boolean z = false;
                while (it2.hasNext()) {
                    boolean remove = remove(it2.next(), 1);
                    if (z || remove) {
                        z = true;
                    }
                }
                return z;
            }
        }
        return false;
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        if (collection instanceof Bag) {
            return retainAll((Bag) collection);
        }
        return retainAll((Bag<?>) new HashBag(collection));
    }

    @Override // org.apache.commons.collections4.Bag, java.util.Collection
    public int size() {
        return this.size;
    }

    @Override // java.util.Collection
    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        int i = 0;
        for (E e : this.map.keySet()) {
            int count = getCount(e);
            while (count > 0) {
                objArr[i] = e;
                count--;
                i++;
            }
        }
        return objArr;
    }

    public String toString() {
        if (size() == 0) {
            return "[]";
        }
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_LIST);
        Iterator<E> it2 = uniqueSet().iterator();
        while (it2.hasNext()) {
            E next = it2.next();
            outline104.append(getCount(next));
            outline104.append(JsonReaderKt.COLON);
            outline104.append(next);
            if (it2.hasNext()) {
                outline104.append(JsonReaderKt.COMMA);
            }
        }
        outline104.append(JsonReaderKt.END_LIST);
        return outline104.toString();
    }

    @Override // org.apache.commons.collections4.Bag
    public Set<E> uniqueSet() {
        if (this.uniqueSet == null) {
            this.uniqueSet = UnmodifiableSet.unmodifiableSet(this.map.keySet());
        }
        return this.uniqueSet;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractMapBag(Map<E, MutableInteger> map) {
        this.map = map;
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean add(E e, int i) {
        this.modCount++;
        if (i > 0) {
            MutableInteger mutableInteger = this.map.get(e);
            this.size += i;
            if (mutableInteger == null) {
                this.map.put(e, new MutableInteger(i));
                return true;
            }
            mutableInteger.value += i;
        }
        return false;
    }

    boolean containsAll(Bag<?> bag) {
        for (Object obj : bag.uniqueSet()) {
            if (getCount(obj) < bag.getCount(obj)) {
                return false;
            }
        }
        return true;
    }

    boolean retainAll(Bag<?> bag) {
        HashBag hashBag = new HashBag();
        for (E e : uniqueSet()) {
            int count = getCount(e);
            int count2 = bag.getCount(e);
            if (1 <= count2 && count2 <= count) {
                hashBag.add(e, count - count2);
            } else {
                hashBag.add(e, count);
            }
        }
        if (!hashBag.isEmpty()) {
            return removeAll(hashBag);
        }
        return false;
    }

    @Override // org.apache.commons.collections4.Bag
    public boolean remove(Object obj, int i) {
        MutableInteger mutableInteger = this.map.get(obj);
        if (mutableInteger != null && i > 0) {
            this.modCount++;
            int i2 = mutableInteger.value;
            if (i < i2) {
                mutableInteger.value = i2 - i;
                this.size -= i;
            } else {
                this.map.remove(obj);
                this.size -= mutableInteger.value;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        int size = size();
        if (tArr.length < size) {
            tArr = (T[]) ((Object[]) GeneratedOutlineSupport1.outline26(tArr, size));
        }
        int i = 0;
        for (E e : this.map.keySet()) {
            int count = getCount(e);
            while (count > 0) {
                tArr[i] = e;
                count--;
                i++;
            }
        }
        while (i < tArr.length) {
            tArr[i] = null;
            i++;
        }
        return tArr;
    }
}

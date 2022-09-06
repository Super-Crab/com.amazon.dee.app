package org.apache.commons.collections4.map;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.Unmodifiable;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;
/* loaded from: classes4.dex */
public final class UnmodifiableEntrySet<K, V> extends AbstractSetDecorator<Map.Entry<K, V>> implements Unmodifiable {
    private static final long serialVersionUID = 1678353579659253473L;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class UnmodifiableEntry extends AbstractMapEntryDecorator<K, V> {
        protected UnmodifiableEntry(Map.Entry<K, V> entry) {
            super(entry);
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator, java.util.Map.Entry
        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

    /* loaded from: classes4.dex */
    private class UnmodifiableEntrySetIterator extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        protected UnmodifiableEntrySetIterator(Iterator<Map.Entry<K, V>> it2) {
            super(it2);
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next  reason: collision with other method in class */
        public Map.Entry<K, V> mo12737next() {
            return new UnmodifiableEntry(getIterator().next());
        }
    }

    private UnmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
        super(set);
    }

    public static <K, V> Set<Map.Entry<K, V>> unmodifiableEntrySet(Set<Map.Entry<K, V>> set) {
        return set instanceof Unmodifiable ? set : new UnmodifiableEntrySet(set);
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public /* bridge */ /* synthetic */ boolean add(Object obj) {
        return add((Map.Entry) ((Map.Entry) obj));
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public boolean addAll(Collection<? extends Map.Entry<K, V>> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
    /* renamed from: iterator */
    public Iterator<Map.Entry<K, V>> mo12756iterator() {
        return new UnmodifiableEntrySetIterator(mo12761decorated().iterator());
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean removeAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
    public boolean retainAll(Collection<?> collection) {
        throw new UnsupportedOperationException();
    }

    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public Object[] toArray() {
        Object[] array = mo12761decorated().toArray();
        for (int i = 0; i < array.length; i++) {
            array[i] = new UnmodifiableEntry((Map.Entry) array[i]);
        }
        return array;
    }

    public boolean add(Map.Entry<K, V> entry) {
        throw new UnsupportedOperationException();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        T[] tArr2 = (T[]) mo12761decorated().toArray(tArr.length > 0 ? (Object[]) GeneratedOutlineSupport1.outline26(tArr, 0) : tArr);
        for (int i = 0; i < tArr2.length; i++) {
            tArr2[i] = new UnmodifiableEntry((Map.Entry) tArr2[i]);
        }
        if (tArr2.length > tArr.length) {
            return tArr2;
        }
        System.arraycopy(tArr2, 0, tArr, 0, tArr2.length);
        if (tArr.length > tArr2.length) {
            tArr[tArr2.length] = null;
        }
        return tArr;
    }
}

package org.apache.commons.collections4.map;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public abstract class AbstractInputCheckedMapDecorator<K, V> extends AbstractMapDecorator<K, V> {

    /* loaded from: classes4.dex */
    private class EntrySetIterator extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySetIterator(Iterator<Map.Entry<K, V>> it2, AbstractInputCheckedMapDecorator<K, V> abstractInputCheckedMapDecorator) {
            super(it2);
            this.parent = abstractInputCheckedMapDecorator;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next  reason: collision with other method in class */
        public Map.Entry<K, V> mo12737next() {
            return new MapEntry(getIterator().next(), this.parent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes4.dex */
    public class MapEntry extends AbstractMapEntryDecorator<K, V> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected MapEntry(Map.Entry<K, V> entry, AbstractInputCheckedMapDecorator<K, V> abstractInputCheckedMapDecorator) {
            super(entry);
            this.parent = abstractInputCheckedMapDecorator;
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator, java.util.Map.Entry
        public V setValue(V v) {
            return getMapEntry().setValue(this.parent.checkSetValue(v));
        }
    }

    protected AbstractInputCheckedMapDecorator() {
    }

    protected abstract V checkSetValue(V v);

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (isSetValueChecking()) {
            return new EntrySet(this.map.entrySet(), this);
        }
        return this.map.entrySet();
    }

    protected boolean isSetValueChecking() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractInputCheckedMapDecorator(Map<K, V> map) {
        super(map);
    }

    /* loaded from: classes4.dex */
    private class EntrySet extends AbstractSetDecorator<Map.Entry<K, V>> {
        private static final long serialVersionUID = 4354731610923110264L;
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySet(Set<Map.Entry<K, V>> set, AbstractInputCheckedMapDecorator<K, V> abstractInputCheckedMapDecorator) {
            super(set);
            this.parent = abstractInputCheckedMapDecorator;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        /* renamed from: iterator */
        public Iterator<Map.Entry<K, V>> mo12756iterator() {
            return new EntrySetIterator(mo12761decorated().iterator(), this.parent);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public Object[] toArray() {
            Object[] array = mo12761decorated().toArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = new MapEntry((Map.Entry) array[i], this.parent);
            }
            return array;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            T[] tArr2 = (T[]) mo12761decorated().toArray(tArr.length > 0 ? (Object[]) GeneratedOutlineSupport1.outline26(tArr, 0) : tArr);
            for (int i = 0; i < tArr2.length; i++) {
                tArr2[i] = new MapEntry((Map.Entry) tArr2[i], this.parent);
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
}

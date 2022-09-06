package org.apache.commons.collections4.bidimap;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.OrderedBidiMap;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.SortedBidiMap;
import org.apache.commons.collections4.map.AbstractSortedMapDecorator;
/* loaded from: classes4.dex */
public class DualTreeBidiMap<K, V> extends AbstractDualBidiMap<K, V> implements SortedBidiMap<K, V>, Serializable {
    private static final long serialVersionUID = 721969328361809L;
    private final Comparator<? super K> comparator;
    private final Comparator<? super V> valueComparator;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class BidiOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        private ListIterator<Map.Entry<K, V>> iterator;
        private Map.Entry<K, V> last = null;
        private final AbstractDualBidiMap<K, V> parent;

        protected BidiOrderedMapIterator(AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            this.parent = abstractDualBidiMap;
            this.iterator = new ArrayList(abstractDualBidiMap.entrySet()).listIterator();
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            Map.Entry<K, V> entry = this.last;
            if (entry != null) {
                return entry.getKey();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            Map.Entry<K, V> entry = this.last;
            if (entry != null) {
                return entry.getValue();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return this.iterator.hasPrevious();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            this.last = this.iterator.next();
            return this.last.getKey();
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            this.last = this.iterator.previous();
            return this.last.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            this.iterator.remove();
            this.parent.mo12668remove(this.last.getKey());
            this.last = null;
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.iterator = new ArrayList(this.parent.entrySet()).listIterator();
            this.last = null;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            if (this.last != null) {
                if (this.parent.reverseMap.containsKey(v) && this.parent.reverseMap.get(v) != this.last.getKey()) {
                    throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
                }
                V put = this.parent.put(this.last.getKey(), v);
                this.last.setValue(v);
                return put;
            }
            throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
        }

        public String toString() {
            if (this.last != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MapIterator[");
                outline107.append(mo12681getKey());
                outline107.append(Config.Compare.EQUAL_TO);
                outline107.append(mo12682getValue());
                outline107.append("]");
                return outline107.toString();
            }
            return "MapIterator[]";
        }
    }

    public DualTreeBidiMap() {
        super(new TreeMap(), new TreeMap());
        this.comparator = null;
        this.valueComparator = null;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.normalMap = new TreeMap(this.comparator);
        this.reverseMap = new TreeMap(this.valueComparator);
        putAll((Map) objectInputStream.readObject());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.normalMap);
    }

    @Override // java.util.SortedMap
    public Comparator<? super K> comparator() {
        return ((SortedMap) this.normalMap).comparator();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        return (K) ((SortedMap) this.normalMap).firstKey();
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> headMap(K k) {
        return new ViewMap(this, ((SortedMap) this.normalMap).headMap(k));
    }

    public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
        return mo12689inverseBidiMap();
    }

    public SortedBidiMap<V, K> inverseSortedBidiMap() {
        return mo12689inverseBidiMap();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        return (K) ((SortedMap) this.normalMap).lastKey();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(K k) {
        if (isEmpty()) {
            return null;
        }
        Map<K, V> map = this.normalMap;
        if (map instanceof OrderedMap) {
            return (K) ((OrderedMap) map).nextKey(k);
        }
        Iterator<K> it2 = ((SortedMap) map).tailMap(k).keySet().iterator();
        it2.next();
        if (!it2.hasNext()) {
            return null;
        }
        return it2.next();
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(K k) {
        if (isEmpty()) {
            return null;
        }
        Map<K, V> map = this.normalMap;
        if (map instanceof OrderedMap) {
            return (K) ((OrderedMap) map).previousKey(k);
        }
        SortedMap<K, V> headMap = ((SortedMap) map).headMap(k);
        if (!headMap.isEmpty()) {
            return headMap.lastKey();
        }
        return null;
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> subMap(K k, K k2) {
        return new ViewMap(this, ((SortedMap) this.normalMap).subMap(k, k2));
    }

    @Override // java.util.SortedMap
    public SortedMap<K, V> tailMap(K k) {
        return new ViewMap(this, ((SortedMap) this.normalMap).tailMap(k));
    }

    @Override // org.apache.commons.collections4.SortedBidiMap
    public Comparator<? super V> valueComparator() {
        return ((SortedMap) this.reverseMap).comparator();
    }

    /* loaded from: classes4.dex */
    protected static class ViewMap<K, V> extends AbstractSortedMapDecorator<K, V> {
        protected ViewMap(DualTreeBidiMap<K, V> dualTreeBidiMap, SortedMap<K, V> sortedMap) {
            super(new DualTreeBidiMap(sortedMap, dualTreeBidiMap.reverseMap, dualTreeBidiMap.inverseBidiMap));
        }

        @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
        public void clear() {
            Iterator<K> it2 = keySet().iterator();
            while (it2.hasNext()) {
                it2.next();
                it2.remove();
            }
        }

        @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
        public boolean containsValue(Object obj) {
            return mo12722decorated().normalMap.containsValue(obj);
        }

        @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
        public SortedMap<K, V> headMap(K k) {
            return new ViewMap(mo12722decorated(), super.headMap(k));
        }

        @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, org.apache.commons.collections4.OrderedMap
        public K nextKey(K k) {
            return mo12722decorated().nextKey(k);
        }

        @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, org.apache.commons.collections4.OrderedMap
        public K previousKey(K k) {
            return mo12722decorated().previousKey(k);
        }

        @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
        public SortedMap<K, V> subMap(K k, K k2) {
            return new ViewMap(mo12722decorated(), super.subMap(k, k2));
        }

        @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, java.util.SortedMap
        public SortedMap<K, V> tailMap(K k) {
            return new ViewMap(mo12722decorated(), super.tailMap(k));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.apache.commons.collections4.map.AbstractSortedMapDecorator, org.apache.commons.collections4.map.AbstractMapDecorator
        /* renamed from: decorated */
        public DualTreeBidiMap<K, V> mo12722decorated() {
            return (DualTreeBidiMap) super.mo12722decorated();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.bidimap.AbstractDualBidiMap
    /* renamed from: createBidiMap  reason: collision with other method in class */
    public DualTreeBidiMap<V, K> mo12649createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap) {
        return new DualTreeBidiMap<>(map, map2, bidiMap);
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractDualBidiMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return new BidiOrderedMapIterator(this);
    }

    @Override // org.apache.commons.collections4.bidimap.AbstractDualBidiMap, org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    public SortedBidiMap<V, K> mo12689inverseBidiMap() {
        return (SortedBidiMap) super.mo12689inverseBidiMap();
    }

    public DualTreeBidiMap(Map<? extends K, ? extends V> map) {
        super(new TreeMap(), new TreeMap());
        putAll(map);
        this.comparator = null;
        this.valueComparator = null;
    }

    public DualTreeBidiMap(Comparator<? super K> comparator, Comparator<? super V> comparator2) {
        super(new TreeMap(comparator), new TreeMap(comparator2));
        this.comparator = comparator;
        this.valueComparator = comparator2;
    }

    protected DualTreeBidiMap(Map<K, V> map, Map<V, K> map2, BidiMap<V, K> bidiMap) {
        super(map, map2, bidiMap);
        this.comparator = ((SortedMap) map).comparator();
        this.valueComparator = ((SortedMap) map2).comparator();
    }
}

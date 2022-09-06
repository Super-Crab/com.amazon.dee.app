package org.apache.commons.collections4.bidimap;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
/* loaded from: classes4.dex */
public abstract class AbstractDualBidiMap<K, V> implements BidiMap<K, V> {
    transient Set<Map.Entry<K, V>> entrySet;
    transient BidiMap<V, K> inverseBidiMap;
    transient Set<K> keySet;
    transient Map<K, V> normalMap;
    transient Map<V, K> reverseMap;
    transient Set<V> values;

    /* loaded from: classes4.dex */
    protected static class BidiMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
        protected Iterator<Map.Entry<K, V>> iterator;
        protected final AbstractDualBidiMap<K, V> parent;
        protected Map.Entry<K, V> last = null;
        protected boolean canRemove = false;

        protected BidiMapIterator(AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            this.parent = abstractDualBidiMap;
            this.iterator = abstractDualBidiMap.normalMap.entrySet().iterator();
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

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            this.last = this.iterator.next();
            this.canRemove = true;
            return this.last.getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            if (this.canRemove) {
                V value = this.last.getValue();
                this.iterator.remove();
                this.parent.reverseMap.remove(value);
                this.last = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.iterator = this.parent.normalMap.entrySet().iterator();
            this.last = null;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            if (this.last != null) {
                if (this.parent.reverseMap.containsKey(v) && this.parent.reverseMap.get(v) != this.last.getKey()) {
                    throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
                }
                return this.parent.put(this.last.getKey(), v);
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

    /* loaded from: classes4.dex */
    protected static class EntrySet<K, V> extends View<K, V, Map.Entry<K, V>> implements Set<Map.Entry<K, V>> {
        private static final long serialVersionUID = 4040410962603292348L;

        protected EntrySet(AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(abstractDualBidiMap.normalMap.entrySet(), abstractDualBidiMap);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        /* renamed from: iterator */
        public Iterator<Map.Entry<K, V>> mo12756iterator() {
            return this.parent.createEntrySetIterator(super.mo12756iterator());
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            if (this.parent.containsKey(key)) {
                V v = this.parent.normalMap.get(key);
                Object value = entry.getValue();
                if (v != null ? v.equals(value) : value == null) {
                    this.parent.normalMap.remove(key);
                    this.parent.reverseMap.remove(v);
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class EntrySetIterator<K, V> extends AbstractIteratorDecorator<Map.Entry<K, V>> {
        protected boolean canRemove;
        protected Map.Entry<K, V> last;
        protected final AbstractDualBidiMap<K, V> parent;

        protected EntrySetIterator(Iterator<Map.Entry<K, V>> it2, AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(it2);
            this.last = null;
            this.canRemove = false;
            this.parent = abstractDualBidiMap;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            if (this.canRemove) {
                V value = this.last.getValue();
                super.remove();
                this.parent.reverseMap.remove(value);
                this.last = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next  reason: collision with other method in class */
        public Map.Entry<K, V> mo12737next() {
            this.last = new MapEntry((Map.Entry) super.mo12737next(), this.parent);
            this.canRemove = true;
            return this.last;
        }
    }

    /* loaded from: classes4.dex */
    protected static class KeySet<K> extends View<K, Object, K> implements Set<K> {
        private static final long serialVersionUID = -7107935777385040694L;

        protected KeySet(AbstractDualBidiMap<K, ?> abstractDualBidiMap) {
            super(abstractDualBidiMap.normalMap.keySet(), abstractDualBidiMap);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.parent.normalMap.containsKey(obj);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        /* renamed from: iterator */
        public Iterator<K> mo12756iterator() {
            return this.parent.createKeySetIterator(super.mo12756iterator());
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean remove(Object obj) {
            if (this.parent.normalMap.containsKey(obj)) {
                this.parent.reverseMap.remove(this.parent.normalMap.remove(obj));
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class KeySetIterator<K> extends AbstractIteratorDecorator<K> {
        protected boolean canRemove;
        protected K lastKey;
        protected final AbstractDualBidiMap<K, ?> parent;

        protected KeySetIterator(Iterator<K> it2, AbstractDualBidiMap<K, ?> abstractDualBidiMap) {
            super(it2);
            this.lastKey = null;
            this.canRemove = false;
            this.parent = abstractDualBidiMap;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next */
        public K mo12737next() {
            this.lastKey = (K) super.mo12737next();
            this.canRemove = true;
            return this.lastKey;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            if (this.canRemove) {
                Object obj = this.parent.normalMap.get(this.lastKey);
                super.remove();
                this.parent.reverseMap.remove(obj);
                this.lastKey = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class MapEntry<K, V> extends AbstractMapEntryDecorator<K, V> {
        protected final AbstractDualBidiMap<K, V> parent;

        protected MapEntry(Map.Entry<K, V> entry, AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(entry);
            this.parent = abstractDualBidiMap;
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator, java.util.Map.Entry
        public V setValue(V v) {
            K mo12677getKey = mo12677getKey();
            if (this.parent.reverseMap.containsKey(v) && this.parent.reverseMap.get(v) != mo12677getKey) {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
            this.parent.put(mo12677getKey, v);
            return (V) super.setValue(v);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Values<V> extends View<Object, V, V> implements Set<V> {
        private static final long serialVersionUID = 4023777119829639864L;

        protected Values(AbstractDualBidiMap<?, V> abstractDualBidiMap) {
            super(abstractDualBidiMap.normalMap.values(), abstractDualBidiMap);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.parent.reverseMap.containsKey(obj);
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, java.lang.Iterable, org.apache.commons.collections4.Bag
        /* renamed from: iterator */
        public Iterator<V> mo12756iterator() {
            return this.parent.createValuesIterator(super.mo12756iterator());
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean remove(Object obj) {
            if (this.parent.reverseMap.containsKey(obj)) {
                this.parent.normalMap.remove(this.parent.reverseMap.remove(obj));
                return true;
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class ValuesIterator<V> extends AbstractIteratorDecorator<V> {
        protected boolean canRemove;
        protected V lastValue;
        protected final AbstractDualBidiMap<Object, V> parent;

        protected ValuesIterator(Iterator<V> it2, AbstractDualBidiMap<?, V> abstractDualBidiMap) {
            super(it2);
            this.lastValue = null;
            this.canRemove = false;
            this.parent = abstractDualBidiMap;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractIteratorDecorator, java.util.Iterator
        /* renamed from: next */
        public V mo12737next() {
            this.lastValue = (V) super.mo12737next();
            this.canRemove = true;
            return this.lastValue;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            if (this.canRemove) {
                super.remove();
                this.parent.reverseMap.remove(this.lastValue);
                this.lastValue = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    /* loaded from: classes4.dex */
    protected static abstract class View<K, V, E> extends AbstractCollectionDecorator<E> {
        private static final long serialVersionUID = 4621510560119690639L;
        protected final AbstractDualBidiMap<K, V> parent;

        protected View(Collection<E> collection, AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(collection);
            this.parent = abstractDualBidiMap;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.Collection
        public boolean equals(Object obj) {
            return obj == this || mo12761decorated().equals(obj);
        }

        @Override // java.util.Collection
        public int hashCode() {
            return mo12761decorated().hashCode();
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean removeAll(Collection<?> collection) {
            boolean z = false;
            if (!this.parent.isEmpty() && !collection.isEmpty()) {
                Iterator<?> it2 = collection.iterator();
                while (it2.hasNext()) {
                    z |= remove(it2.next());
                }
            }
            return z;
        }

        @Override // org.apache.commons.collections4.collection.AbstractCollectionDecorator, java.util.Collection, org.apache.commons.collections4.Bag
        public boolean retainAll(Collection<?> collection) {
            boolean z = false;
            if (this.parent.isEmpty()) {
                return false;
            }
            if (collection.isEmpty()) {
                this.parent.clear();
                return true;
            }
            Iterator<E> mo12756iterator = mo12756iterator();
            while (mo12756iterator.hasNext()) {
                if (!collection.contains(mo12756iterator.next())) {
                    mo12756iterator.remove();
                    z = true;
                }
            }
            return z;
        }
    }

    protected AbstractDualBidiMap() {
        this.inverseBidiMap = null;
        this.keySet = null;
        this.values = null;
        this.entrySet = null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        this.normalMap.clear();
        this.reverseMap.clear();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        return this.normalMap.containsKey(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        return this.reverseMap.containsKey(obj);
    }

    /* renamed from: createBidiMap */
    protected abstract BidiMap<V, K> mo12649createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap);

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator(Iterator<Map.Entry<K, V>> it2) {
        return new EntrySetIterator(it2, this);
    }

    protected Iterator<K> createKeySetIterator(Iterator<K> it2) {
        return new KeySetIterator(it2, this);
    }

    protected Iterator<V> createValuesIterator(Iterator<V> it2) {
        return new ValuesIterator(it2, this);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.normalMap.equals(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        return this.normalMap.get(obj);
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: getKey */
    public K mo12664getKey(Object obj) {
        return this.reverseMap.get(obj);
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.normalMap.hashCode();
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: inverseBidiMap */
    public BidiMap<V, K> mo12689inverseBidiMap() {
        if (this.inverseBidiMap == null) {
            this.inverseBidiMap = mo12649createBidiMap(this.reverseMap, this.normalMap, this);
        }
        return this.inverseBidiMap;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return this.normalMap.isEmpty();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet(this);
        }
        return this.keySet;
    }

    @Override // org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        return new BidiMapIterator(this);
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        if (this.normalMap.containsKey(k)) {
            this.reverseMap.remove(this.normalMap.get(k));
        }
        if (this.reverseMap.containsKey(v)) {
            this.normalMap.remove(this.reverseMap.get(v));
        }
        V put = this.normalMap.put(k, v);
        this.reverseMap.put(v, k);
        return put;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        if (this.normalMap.containsKey(obj)) {
            V remove = this.normalMap.remove(obj);
            this.reverseMap.remove(remove);
            return remove;
        }
        return null;
    }

    @Override // org.apache.commons.collections4.BidiMap
    /* renamed from: removeValue */
    public K mo12669removeValue(Object obj) {
        if (this.reverseMap.containsKey(obj)) {
            K remove = this.reverseMap.remove(obj);
            this.normalMap.remove(remove);
            return remove;
        }
        return null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return this.normalMap.size();
    }

    public String toString() {
        return this.normalMap.toString();
    }

    @Override // org.apache.commons.collections4.BidiMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values  reason: collision with other method in class */
    public Set<V> mo12691values() {
        if (this.values == null) {
            this.values = new Values(this);
        }
        return this.values;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDualBidiMap(Map<K, V> map, Map<V, K> map2) {
        this.inverseBidiMap = null;
        this.keySet = null;
        this.values = null;
        this.entrySet = null;
        this.normalMap = map;
        this.reverseMap = map2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractDualBidiMap(Map<K, V> map, Map<V, K> map2, BidiMap<V, K> bidiMap) {
        this.inverseBidiMap = null;
        this.keySet = null;
        this.values = null;
        this.entrySet = null;
        this.normalMap = map;
        this.reverseMap = map2;
        this.inverseBidiMap = bidiMap;
    }
}

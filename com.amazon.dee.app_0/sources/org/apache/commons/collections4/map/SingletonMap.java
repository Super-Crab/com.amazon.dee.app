package org.apache.commons.collections4.map;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.BoundedMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.keyvalue.TiedMapEntry;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class SingletonMap<K, V> implements OrderedMap<K, V>, BoundedMap<K, V>, KeyValue<K, V>, Serializable, Cloneable {
    private static final long serialVersionUID = -8931271118676803261L;
    private final K key;
    private V value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class SingletonMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        private final SingletonMap<K, V> parent;
        private boolean hasNext = true;
        private boolean canGetSet = false;

        SingletonMapIterator(SingletonMap<K, V> singletonMap) {
            this.parent = singletonMap;
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            if (this.canGetSet) {
                return this.parent.mo12677getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            if (this.canGetSet) {
                return this.parent.mo12678getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        public boolean hasPrevious() {
            return !this.hasNext;
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            if (this.hasNext) {
                this.hasNext = false;
                this.canGetSet = true;
                return this.parent.mo12677getKey();
            }
            throw new NoSuchElementException("No next() entry in the iteration");
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            if (!this.hasNext) {
                this.hasNext = true;
                return this.parent.mo12677getKey();
            }
            throw new NoSuchElementException("No previous() entry in the iteration");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.hasNext = true;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            if (this.canGetSet) {
                return this.parent.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public String toString() {
            if (this.hasNext) {
                return "Iterator[]";
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Iterator[");
            outline107.append(mo12681getKey());
            outline107.append(Config.Compare.EQUAL_TO);
            return GeneratedOutlineSupport1.outline88(outline107, mo12682getValue(), "]");
        }
    }

    /* loaded from: classes4.dex */
    static class SingletonValues<V> extends AbstractSet<V> implements Serializable {
        private static final long serialVersionUID = -3689524741863047872L;
        private final SingletonMap<?, V> parent;

        SingletonValues(SingletonMap<?, V> singletonMap) {
            this.parent = singletonMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<V> iterator() {
            return new SingletonIterator(this.parent.mo12678getValue(), false);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return 1;
        }
    }

    public SingletonMap() {
        this.key = null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        return isEqualKey(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        return isEqualValue(obj);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return Collections.singleton(new TiedMapEntry(this, mo12677getKey()));
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != 1) {
            return false;
        }
        Map.Entry<K, V> next = map.entrySet().iterator().next();
        return isEqualKey(next.getKey()) && isEqualValue(next.getValue());
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        return mo12677getKey();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        if (isEqualKey(obj)) {
            return this.value;
        }
        return null;
    }

    @Override // org.apache.commons.collections4.KeyValue
    /* renamed from: getKey */
    public K mo12677getKey() {
        return this.key;
    }

    @Override // org.apache.commons.collections4.KeyValue
    /* renamed from: getValue */
    public V mo12678getValue() {
        return this.value;
    }

    @Override // java.util.Map
    public int hashCode() {
        int i = 0;
        int hashCode = mo12677getKey() == null ? 0 : mo12677getKey().hashCode();
        if (mo12678getValue() != null) {
            i = mo12678getValue().hashCode();
        }
        return hashCode ^ i;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return false;
    }

    protected boolean isEqualKey(Object obj) {
        return obj == null ? mo12677getKey() == null : obj.equals(mo12677getKey());
    }

    protected boolean isEqualValue(Object obj) {
        return obj == null ? mo12678getValue() == null : obj.equals(mo12678getValue());
    }

    @Override // org.apache.commons.collections4.BoundedMap
    public boolean isFull() {
        return true;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return Collections.singleton(this.key);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        return mo12677getKey();
    }

    @Override // org.apache.commons.collections4.BoundedMap
    public int maxSize() {
        return 1;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(K k) {
        return null;
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(K k) {
        return null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        if (isEqualKey(k)) {
            return setValue(v);
        }
        throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size singleton");
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size != 0) {
            if (size == 1) {
                Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
                put(next.getKey(), next.getValue());
                return;
            }
            throw new IllegalArgumentException("The map size must be 0 or 1");
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        throw new UnsupportedOperationException();
    }

    public V setValue(V v) {
        V v2 = this.value;
        this.value = v;
        return v2;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        V v = (V) "(this Map)";
        sb.append(mo12677getKey() == this ? v : mo12677getKey());
        sb.append(Chars.EQ);
        V v2 = v;
        if (mo12678getValue() != this) {
            v2 = mo12678getValue();
        }
        sb.append(v2);
        sb.append(JsonReaderKt.END_OBJ);
        return sb.toString();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        return new SingletonValues(this);
    }

    public SingletonMap<K, V> clone() {
        try {
            return (SingletonMap) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    @Override // org.apache.commons.collections4.OrderedMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return new SingletonMapIterator(this);
    }

    public SingletonMap(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public SingletonMap(KeyValue<K, V> keyValue) {
        this.key = keyValue.mo12677getKey();
        this.value = keyValue.mo12678getValue();
    }

    public SingletonMap(Map.Entry<? extends K, ? extends V> entry) {
        this.key = entry.getKey();
        this.value = entry.getValue();
    }

    public SingletonMap(Map<? extends K, ? extends V> map) {
        if (map.size() == 1) {
            Map.Entry<? extends K, ? extends V> next = map.entrySet().iterator().next();
            this.key = next.getKey();
            this.value = next.getValue();
            return;
        }
        throw new IllegalArgumentException("The map size must be 1");
    }
}

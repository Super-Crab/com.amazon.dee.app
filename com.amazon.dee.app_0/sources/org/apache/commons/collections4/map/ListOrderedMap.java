package org.apache.commons.collections4.map;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.list.UnmodifiableList;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class ListOrderedMap<K, V> extends AbstractMapDecorator<K, V> implements OrderedMap<K, V>, Serializable {
    private static final long serialVersionUID = 2728177751851003750L;
    private final List<K> insertOrder;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class EntrySetView<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private Set<Map.Entry<K, V>> entrySet;
        private final List<K> insertOrder;
        private final ListOrderedMap<K, V> parent;

        public EntrySetView(ListOrderedMap<K, V> listOrderedMap, List<K> list) {
            this.parent = listOrderedMap;
            this.insertOrder = list;
        }

        private Set<Map.Entry<K, V>> getEntrySet() {
            if (this.entrySet == null) {
                this.entrySet = this.parent.mo12722decorated().entrySet();
            }
            return this.entrySet;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return getEntrySet().contains(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean containsAll(Collection<?> collection) {
            return getEntrySet().containsAll(collection);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            return getEntrySet().equals(obj);
        }

        @Override // java.util.AbstractSet, java.util.Collection, java.util.Set
        public int hashCode() {
            return getEntrySet().hashCode();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean isEmpty() {
            return this.parent.isEmpty();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return new ListOrderedIterator(this.parent, this.insertOrder);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if ((obj instanceof Map.Entry) && getEntrySet().contains(obj)) {
                this.parent.mo12668remove(((Map.Entry) obj).getKey());
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            return getEntrySet().toString();
        }
    }

    /* loaded from: classes4.dex */
    static class KeySetView<K> extends AbstractSet<K> {
        private final ListOrderedMap<K, Object> parent;

        KeySetView(ListOrderedMap<K, ?> listOrderedMap) {
            this.parent = listOrderedMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            return this.parent.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<K> iterator() {
            return new AbstractUntypedIteratorDecorator<Map.Entry<K, Object>, K>(this.parent.entrySet().iterator()) { // from class: org.apache.commons.collections4.map.ListOrderedMap.KeySetView.1
                @Override // java.util.Iterator
                public K next() {
                    return getIterator().next().getKey();
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes4.dex */
    static class ListOrderedIterator<K, V> extends AbstractUntypedIteratorDecorator<K, Map.Entry<K, V>> {
        private K last;
        private final ListOrderedMap<K, V> parent;

        ListOrderedIterator(ListOrderedMap<K, V> listOrderedMap, List<K> list) {
            super(list.iterator());
            this.last = null;
            this.parent = listOrderedMap;
        }

        @Override // org.apache.commons.collections4.iterators.AbstractUntypedIteratorDecorator, java.util.Iterator
        public void remove() {
            super.remove();
            this.parent.mo12722decorated().remove(this.last);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            this.last = getIterator().next();
            return new ListOrderedMapEntry(this.parent, this.last);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ListOrderedMapEntry<K, V> extends AbstractMapEntry<K, V> {
        private final ListOrderedMap<K, V> parent;

        ListOrderedMapEntry(ListOrderedMap<K, V> listOrderedMap, K k) {
            super(k, null);
            this.parent = listOrderedMap;
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractKeyValue, org.apache.commons.collections4.KeyValue
        /* renamed from: getValue */
        public V mo12678getValue() {
            return this.parent.mo12663get(mo12677getKey());
        }

        @Override // org.apache.commons.collections4.keyvalue.AbstractMapEntry, org.apache.commons.collections4.keyvalue.AbstractKeyValue, java.util.Map.Entry
        public V setValue(V v) {
            return this.parent.mo12722decorated().put(mo12677getKey(), v);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ListOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        private ListIterator<K> iterator;
        private final ListOrderedMap<K, V> parent;
        private K last = null;
        private boolean readable = false;

        ListOrderedMapIterator(ListOrderedMap<K, V> listOrderedMap) {
            this.parent = listOrderedMap;
            this.iterator = ((ListOrderedMap) listOrderedMap).insertOrder.listIterator();
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            if (this.readable) {
                return this.last;
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            if (this.readable) {
                return this.parent.mo12663get(this.last);
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
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
            this.readable = true;
            return this.last;
        }

        @Override // org.apache.commons.collections4.OrderedMapIterator, org.apache.commons.collections4.OrderedIterator
        /* renamed from: previous */
        public K mo12706previous() {
            this.last = this.iterator.previous();
            this.readable = true;
            return this.last;
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            if (this.readable) {
                this.iterator.remove();
                this.parent.map.remove(this.last);
                this.readable = false;
                return;
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.iterator = ((ListOrderedMap) this.parent).insertOrder.listIterator();
            this.last = null;
            this.readable = false;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            if (this.readable) {
                return this.parent.map.put(this.last, v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public String toString() {
            if (this.readable) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Iterator[");
                outline107.append(mo12681getKey());
                outline107.append(Config.Compare.EQUAL_TO);
                return GeneratedOutlineSupport1.outline88(outline107, mo12682getValue(), "]");
            }
            return "Iterator[]";
        }
    }

    /* loaded from: classes4.dex */
    static class ValuesView<V> extends AbstractList<V> {
        private final ListOrderedMap<Object, V> parent;

        ValuesView(ListOrderedMap<?, V> listOrderedMap) {
            this.parent = listOrderedMap;
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        @Override // java.util.AbstractList, java.util.List
        public V get(int i) {
            return this.parent.getValue(i);
        }

        @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
        public Iterator<V> iterator() {
            return new AbstractUntypedIteratorDecorator<Map.Entry<Object, V>, V>(this.parent.entrySet().iterator()) { // from class: org.apache.commons.collections4.map.ListOrderedMap.ValuesView.1
                @Override // java.util.Iterator
                public V next() {
                    return getIterator().next().getValue();
                }
            };
        }

        @Override // java.util.AbstractList, java.util.List
        public V remove(int i) {
            return this.parent.remove(i);
        }

        @Override // java.util.AbstractList, java.util.List
        public V set(int i, V v) {
            return this.parent.setValue(i, v);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public int size() {
            return this.parent.size();
        }
    }

    public ListOrderedMap() {
        this(new HashMap());
    }

    public static <K, V> ListOrderedMap<K, V> listOrderedMap(Map<K, V> map) {
        return new ListOrderedMap<>(map);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.map = (Map) objectInputStream.readObject();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeObject(this.map);
    }

    public List<K> asList() {
        return keyList();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        mo12722decorated().clear();
        this.insertOrder.clear();
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySetView(this, this.insertOrder);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: firstKey */
    public K mo12662firstKey() {
        if (size() != 0) {
            return this.insertOrder.get(0);
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K get(int i) {
        return this.insertOrder.get(i);
    }

    public V getValue(int i) {
        return mo12663get(this.insertOrder.get(i));
    }

    public int indexOf(Object obj) {
        return this.insertOrder.indexOf(obj);
    }

    public List<K> keyList() {
        return UnmodifiableList.unmodifiableList(this.insertOrder);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        return new KeySetView(this);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    /* renamed from: lastKey */
    public K mo12666lastKey() {
        if (size() != 0) {
            return this.insertOrder.get(size() - 1);
        }
        throw new NoSuchElementException("Map is empty");
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K nextKey(Object obj) {
        int indexOf = this.insertOrder.indexOf(obj);
        if (indexOf < 0 || indexOf >= size() - 1) {
            return null;
        }
        return this.insertOrder.get(indexOf + 1);
    }

    @Override // org.apache.commons.collections4.OrderedMap
    public K previousKey(Object obj) {
        int indexOf = this.insertOrder.indexOf(obj);
        if (indexOf > 0) {
            return this.insertOrder.get(indexOf - 1);
        }
        return null;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        if (mo12722decorated().containsKey(k)) {
            return mo12722decorated().put(k, v);
        }
        V put = mo12722decorated().put(k, v);
        this.insertOrder.add(k);
        return put;
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        if (mo12722decorated().containsKey(obj)) {
            V remove = mo12722decorated().remove(obj);
            this.insertOrder.remove(obj);
            return remove;
        }
        return null;
    }

    public V setValue(int i, V v) {
        return put(this.insertOrder.get(i), v);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator
    public String toString() {
        if (isEmpty()) {
            return "{}";
        }
        StringBuilder outline104 = GeneratedOutlineSupport1.outline104(JsonReaderKt.BEGIN_OBJ);
        boolean z = true;
        for (Map.Entry<K, V> entry : entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (z) {
                z = false;
            } else {
                outline104.append(", ");
            }
            if (key == this) {
                key = "(this Map)";
            }
            outline104.append(key);
            outline104.append(Chars.EQ);
            if (value == this) {
                value = "(this Map)";
            }
            outline104.append(value);
        }
        outline104.append(JsonReaderKt.END_OBJ);
        return outline104.toString();
    }

    public List<V> valueList() {
        return new ValuesView(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractMapDecorator, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        return new ValuesView(this);
    }

    protected ListOrderedMap(Map<K, V> map) {
        super(map);
        this.insertOrder = new ArrayList();
        this.insertOrder.addAll(mo12722decorated().keySet());
    }

    @Override // org.apache.commons.collections4.map.AbstractIterableMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator  reason: collision with other method in class */
    public OrderedMapIterator<K, V> mo12767mapIterator() {
        return new ListOrderedMapIterator(this);
    }

    public void putAll(int i, Map<? extends K, ? extends V> map) {
        if (i >= 0 && i <= this.insertOrder.size()) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                boolean containsKey = containsKey(entry.getKey());
                put(i, entry.getKey(), entry.getValue());
                if (containsKey) {
                    i = indexOf(entry.getKey());
                }
                i++;
            }
            return;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
        outline109.append(this.insertOrder.size());
        throw new IndexOutOfBoundsException(outline109.toString());
    }

    public V remove(int i) {
        return mo12668remove(get(i));
    }

    public V put(int i, K k, V v) {
        if (i >= 0 && i <= this.insertOrder.size()) {
            Map<K, V> mo12722decorated = mo12722decorated();
            if (mo12722decorated.containsKey(k)) {
                V remove = mo12722decorated.remove(k);
                int indexOf = this.insertOrder.indexOf(k);
                this.insertOrder.remove(indexOf);
                if (indexOf < i) {
                    i--;
                }
                this.insertOrder.add(i, k);
                mo12722decorated.put(k, v);
                return remove;
            }
            this.insertOrder.add(i, k);
            mo12722decorated.put(k, v);
            return null;
        }
        StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Index: ", i, ", Size: ");
        outline109.append(this.insertOrder.size());
        throw new IndexOutOfBoundsException(outline109.toString());
    }
}

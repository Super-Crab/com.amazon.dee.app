package org.apache.commons.collections4.map;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class Flat3Map<K, V> implements IterableMap<K, V>, Serializable, Cloneable {
    private static final long serialVersionUID = -6701087419741928296L;
    private transient AbstractHashedMap<K, V> delegateMap;
    private transient int hash1;
    private transient int hash2;
    private transient int hash3;
    private transient K key1;
    private transient K key2;
    private transient K key3;
    private transient int size;
    private transient V value1;
    private transient V value2;
    private transient V value3;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static abstract class EntryIterator<K, V> {
        private final Flat3Map<K, V> parent;
        private int nextIndex = 0;
        private FlatMapEntry<K, V> currentEntry = null;

        public EntryIterator(Flat3Map<K, V> flat3Map) {
            this.parent = flat3Map;
        }

        public boolean hasNext() {
            return this.nextIndex < ((Flat3Map) this.parent).size;
        }

        public Map.Entry<K, V> nextEntry() {
            if (hasNext()) {
                Flat3Map<K, V> flat3Map = this.parent;
                int i = this.nextIndex + 1;
                this.nextIndex = i;
                this.currentEntry = new FlatMapEntry<>(flat3Map, i);
                return this.currentEntry;
            }
            throw new NoSuchElementException("No next() entry in the iteration");
        }

        public void remove() {
            FlatMapEntry<K, V> flatMapEntry = this.currentEntry;
            if (flatMapEntry != null) {
                flatMapEntry.setRemoved(true);
                this.parent.mo12668remove(this.currentEntry.getKey());
                this.nextIndex--;
                this.currentEntry = null;
                return;
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }
    }

    /* loaded from: classes4.dex */
    static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private final Flat3Map<K, V> parent;

        EntrySet(Flat3Map<K, V> flat3Map) {
            this.parent = flat3Map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            if (((Flat3Map) this.parent).delegateMap != null) {
                return ((Flat3Map) this.parent).delegateMap.entrySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new EntrySetIterator(this.parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Object key = ((Map.Entry) obj).getKey();
            boolean containsKey = this.parent.containsKey(key);
            this.parent.mo12668remove(key);
            return containsKey;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes4.dex */
    static class EntrySetIterator<K, V> extends EntryIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        EntrySetIterator(Flat3Map<K, V> flat3Map) {
            super(flat3Map);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class FlatMapEntry<K, V> implements Map.Entry<K, V> {
        private final int index;
        private final Flat3Map<K, V> parent;
        private volatile boolean removed = false;

        public FlatMapEntry(Flat3Map<K, V> flat3Map, int i) {
            this.parent = flat3Map;
            this.index = i;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (!this.removed && (obj instanceof Map.Entry)) {
                Map.Entry entry = (Map.Entry) obj;
                K key = getKey();
                V value = getValue();
                if (key == null) {
                    if (entry.getKey() != null) {
                        return false;
                    }
                } else if (!key.equals(entry.getKey())) {
                    return false;
                }
                Object value2 = entry.getValue();
                if (value == null) {
                    if (value2 != null) {
                        return false;
                    }
                } else if (!value.equals(value2)) {
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public K getKey() {
            if (!this.removed) {
                int i = this.index;
                if (i == 1) {
                    return (K) ((Flat3Map) this.parent).key1;
                }
                if (i == 2) {
                    return (K) ((Flat3Map) this.parent).key2;
                }
                if (i == 3) {
                    return (K) ((Flat3Map) this.parent).key3;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                outline107.append(this.index);
                throw new IllegalStateException(outline107.toString());
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // java.util.Map.Entry
        public V getValue() {
            if (!this.removed) {
                int i = this.index;
                if (i == 1) {
                    return (V) ((Flat3Map) this.parent).value1;
                }
                if (i == 2) {
                    return (V) ((Flat3Map) this.parent).value2;
                }
                if (i == 3) {
                    return (V) ((Flat3Map) this.parent).value3;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                outline107.append(this.index);
                throw new IllegalStateException(outline107.toString());
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int i = 0;
            if (this.removed) {
                return 0;
            }
            K key = getKey();
            V value = getValue();
            int hashCode = key == null ? 0 : key.hashCode();
            if (value != null) {
                i = value.hashCode();
            }
            return hashCode ^ i;
        }

        void setRemoved(boolean z) {
            this.removed = z;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            if (!this.removed) {
                V value = getValue();
                int i = this.index;
                if (i == 1) {
                    ((Flat3Map) this.parent).value1 = v;
                } else if (i == 2) {
                    ((Flat3Map) this.parent).value2 = v;
                } else if (i == 3) {
                    ((Flat3Map) this.parent).value3 = v;
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                    outline107.append(this.index);
                    throw new IllegalStateException(outline107.toString());
                }
                return value;
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public String toString() {
            if (!this.removed) {
                return getKey() + Config.Compare.EQUAL_TO + getValue();
            }
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class FlatMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
        private final Flat3Map<K, V> parent;
        private int nextIndex = 0;
        private boolean canRemove = false;

        FlatMapIterator(Flat3Map<K, V> flat3Map) {
            this.parent = flat3Map;
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            if (this.canRemove) {
                int i = this.nextIndex;
                if (i == 1) {
                    return (K) ((Flat3Map) this.parent).key1;
                }
                if (i == 2) {
                    return (K) ((Flat3Map) this.parent).key2;
                }
                if (i == 3) {
                    return (K) ((Flat3Map) this.parent).key3;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                outline107.append(this.nextIndex);
                throw new IllegalStateException(outline107.toString());
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            if (this.canRemove) {
                int i = this.nextIndex;
                if (i == 1) {
                    return (V) ((Flat3Map) this.parent).value1;
                }
                if (i == 2) {
                    return (V) ((Flat3Map) this.parent).value2;
                }
                if (i == 3) {
                    return (V) ((Flat3Map) this.parent).value3;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                outline107.append(this.nextIndex);
                throw new IllegalStateException(outline107.toString());
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public boolean hasNext() {
            return this.nextIndex < ((Flat3Map) this.parent).size;
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            if (hasNext()) {
                this.canRemove = true;
                this.nextIndex++;
                return mo12681getKey();
            }
            throw new NoSuchElementException("No next() entry in the iteration");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        public void remove() {
            if (this.canRemove) {
                this.parent.mo12668remove(mo12681getKey());
                this.nextIndex--;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("remove() can only be called once after next()");
        }

        @Override // org.apache.commons.collections4.ResettableIterator
        public void reset() {
            this.nextIndex = 0;
            this.canRemove = false;
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            if (this.canRemove) {
                V mo12682getValue = mo12682getValue();
                int i = this.nextIndex;
                if (i == 1) {
                    ((Flat3Map) this.parent).value1 = v;
                } else if (i == 2) {
                    ((Flat3Map) this.parent).value2 = v;
                } else if (i == 3) {
                    ((Flat3Map) this.parent).value3 = v;
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                    outline107.append(this.nextIndex);
                    throw new IllegalStateException(outline107.toString());
                }
                return mo12682getValue;
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }

        public String toString() {
            if (this.canRemove) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Iterator[");
                outline107.append(mo12681getKey());
                outline107.append(Config.Compare.EQUAL_TO);
                return GeneratedOutlineSupport1.outline88(outline107, mo12682getValue(), "]");
            }
            return "Iterator[]";
        }
    }

    /* loaded from: classes4.dex */
    static class KeySet<K> extends AbstractSet<K> {
        private final Flat3Map<K, ?> parent;

        KeySet(Flat3Map<K, ?> flat3Map) {
            this.parent = flat3Map;
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
            if (((Flat3Map) this.parent).delegateMap != null) {
                return ((Flat3Map) this.parent).delegateMap.keySet().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new KeySetIterator(this.parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            boolean containsKey = this.parent.containsKey(obj);
            this.parent.mo12668remove(obj);
            return containsKey;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes4.dex */
    static class KeySetIterator<K> extends EntryIterator<K, Object> implements Iterator<K> {
        KeySetIterator(Flat3Map<K, ?> flat3Map) {
            super(flat3Map);
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().getKey();
        }
    }

    /* loaded from: classes4.dex */
    static class Values<V> extends AbstractCollection<V> {
        private final Flat3Map<?, V> parent;

        Values(Flat3Map<?, V> flat3Map) {
            this.parent = flat3Map;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public boolean contains(Object obj) {
            return this.parent.containsValue(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<V> iterator() {
            if (((Flat3Map) this.parent).delegateMap != null) {
                return ((Flat3Map) this.parent).delegateMap.mo12691values().iterator();
            }
            if (this.parent.size() == 0) {
                return EmptyIterator.emptyIterator();
            }
            return new ValuesIterator(this.parent);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.parent.size();
        }
    }

    /* loaded from: classes4.dex */
    static class ValuesIterator<V> extends EntryIterator<Object, V> implements Iterator<V> {
        ValuesIterator(Flat3Map<?, V> flat3Map) {
            super(flat3Map);
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().getValue();
        }
    }

    public Flat3Map() {
    }

    private void convertToMap() {
        this.delegateMap = createDelegateMap();
        int i = this.size;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.delegateMap.put(this.key3, this.value3);
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                        outline107.append(this.size);
                        throw new IllegalStateException(outline107.toString());
                    }
                }
                this.delegateMap.put(this.key2, this.value2);
            }
            this.delegateMap.put(this.key1, this.value1);
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt > 3) {
            this.delegateMap = createDelegateMap();
        }
        while (readInt > 0) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
            readInt--;
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        MapIterator<K, V> mo12767mapIterator = mo12767mapIterator();
        while (mo12767mapIterator.hasNext()) {
            objectOutputStream.writeObject(mo12767mapIterator.mo12683next());
            objectOutputStream.writeObject(mo12767mapIterator.mo12682getValue());
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            abstractHashedMap.clear();
            this.delegateMap = null;
            return;
        }
        this.size = 0;
        this.hash3 = 0;
        this.hash2 = 0;
        this.hash1 = 0;
        this.key3 = null;
        this.key2 = null;
        this.key1 = null;
        this.value3 = null;
        this.value2 = null;
        this.value1 = null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.containsKey(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    if (this.key3 == null) {
                        return true;
                    }
                }
                if (this.key2 == null) {
                    return true;
                }
            }
            return this.key1 == null;
        } else if (this.size <= 0) {
            return false;
        } else {
            int hashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return false;
                    }
                    if (this.hash3 == hashCode && obj.equals(this.key3)) {
                        return true;
                    }
                }
                if (this.hash2 == hashCode && obj.equals(this.key2)) {
                    return true;
                }
            }
            return this.hash1 == hashCode && obj.equals(this.key1);
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.containsValue(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return false;
                    }
                    if (this.value3 == null) {
                        return true;
                    }
                }
                if (this.value2 == null) {
                    return true;
                }
            }
            return this.value1 == null;
        }
        int i2 = this.size;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    return false;
                }
                if (obj.equals(this.value3)) {
                    return true;
                }
            }
            if (obj.equals(this.value2)) {
                return true;
            }
        }
        return obj.equals(this.value1);
    }

    protected AbstractHashedMap<K, V> createDelegateMap() {
        return new HashedMap();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.entrySet();
        }
        return new EntrySet(this);
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.equals(obj);
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (this.size != map.size()) {
            return false;
        }
        int i = this.size;
        if (i > 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (!map.containsKey(this.key3)) {
                            return false;
                        }
                        Object obj2 = map.get(this.key3);
                        V v = this.value3;
                        if (v != null ? !v.equals(obj2) : obj2 != null) {
                            return false;
                        }
                    }
                }
                if (!map.containsKey(this.key2)) {
                    return false;
                }
                Object obj3 = map.get(this.key2);
                V v2 = this.value2;
                if (v2 != null ? !v2.equals(obj3) : obj3 != null) {
                    return false;
                }
            }
            if (!map.containsKey(this.key1)) {
                return false;
            }
            Object obj4 = map.get(this.key1);
            V v3 = this.value1;
            if (v3 != null ? !v3.equals(obj4) : obj4 != null) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.mo12663get(obj);
        }
        if (obj == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    if (this.key3 == null) {
                        return this.value3;
                    }
                }
                if (this.key2 == null) {
                    return this.value2;
                }
            }
            if (this.key1 != null) {
                return null;
            }
            return this.value1;
        } else if (this.size <= 0) {
            return null;
        } else {
            int hashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        return null;
                    }
                    if (this.hash3 == hashCode && obj.equals(this.key3)) {
                        return this.value3;
                    }
                }
                if (this.hash2 == hashCode && obj.equals(this.key2)) {
                    return this.value2;
                }
            }
            if (this.hash1 == hashCode && obj.equals(this.key1)) {
                return this.value1;
            }
            return null;
        }
    }

    @Override // java.util.Map
    public int hashCode() {
        int i;
        int i2;
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.hashCode();
        }
        int i3 = this.size;
        int i4 = 0;
        if (i3 == 0) {
            return 0;
        }
        if (i3 != 1) {
            if (i3 == 2) {
                i2 = 0;
            } else if (i3 == 3) {
                int i5 = this.hash3;
                V v = this.value3;
                i2 = (i5 ^ (v == null ? 0 : v.hashCode())) + 0;
            } else {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                outline107.append(this.size);
                throw new IllegalStateException(outline107.toString());
            }
            int i6 = this.hash2;
            V v2 = this.value2;
            i = i2 + (i6 ^ (v2 == null ? 0 : v2.hashCode()));
        } else {
            i = 0;
        }
        int i7 = this.hash1;
        V v3 = this.value1;
        if (v3 != null) {
            i4 = v3.hashCode();
        }
        return (i4 ^ i7) + i;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.keySet();
        }
        return new KeySet(this);
    }

    @Override // org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.mo12767mapIterator();
        }
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new FlatMapIterator(this);
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.put(k, v);
        }
        if (k == null) {
            int i = this.size;
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        if (this.key3 == null) {
                            V v2 = this.value3;
                            this.value3 = v;
                            return v2;
                        }
                    }
                }
                if (this.key2 == null) {
                    V v3 = this.value2;
                    this.value2 = v;
                    return v3;
                }
            }
            if (this.key1 == null) {
                V v4 = this.value1;
                this.value1 = v;
                return v4;
            }
        } else if (this.size > 0) {
            int hashCode = k.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        if (this.hash3 == hashCode && k.equals(this.key3)) {
                            V v5 = this.value3;
                            this.value3 = v;
                            return v5;
                        }
                    }
                }
                if (this.hash2 == hashCode && k.equals(this.key2)) {
                    V v6 = this.value2;
                    this.value2 = v;
                    return v6;
                }
            }
            if (this.hash1 == hashCode && k.equals(this.key1)) {
                V v7 = this.value1;
                this.value1 = v;
                return v7;
            }
        }
        int i3 = this.size;
        int i4 = 0;
        if (i3 == 0) {
            if (k != null) {
                i4 = k.hashCode();
            }
            this.hash1 = i4;
            this.key1 = k;
            this.value1 = v;
        } else if (i3 == 1) {
            if (k != null) {
                i4 = k.hashCode();
            }
            this.hash2 = i4;
            this.key2 = k;
            this.value2 = v;
        } else if (i3 != 2) {
            convertToMap();
            this.delegateMap.put(k, v);
            return null;
        } else {
            if (k != null) {
                i4 = k.hashCode();
            }
            this.hash3 = i4;
            this.key3 = k;
            this.value3 = v;
        }
        this.size++;
        return null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            abstractHashedMap.putAll(map);
        } else if (size < 4) {
            for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
                put(entry.getKey(), entry.getValue());
            }
        } else {
            convertToMap();
            this.delegateMap.putAll(map);
        }
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.mo12668remove(obj);
        }
        int i = this.size;
        if (i == 0) {
            return null;
        }
        if (obj == null) {
            if (i != 1) {
                if (i == 2) {
                    K k = this.key2;
                    if (k == null) {
                        V v = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return v;
                    } else if (this.key1 != null) {
                        return null;
                    } else {
                        V v2 = this.value1;
                        this.hash1 = this.hash2;
                        this.key1 = k;
                        this.value1 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return v2;
                    }
                } else if (i == 3) {
                    K k2 = this.key3;
                    if (k2 == null) {
                        V v3 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v3;
                    } else if (this.key2 == null) {
                        V v4 = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = k2;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v4;
                    } else if (this.key1 != null) {
                        return null;
                    } else {
                        V v5 = this.value1;
                        this.hash1 = this.hash3;
                        this.key1 = k2;
                        this.value1 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v5;
                    }
                }
            } else if (this.key1 == null) {
                V v6 = this.value1;
                this.hash1 = 0;
                this.key1 = null;
                this.value1 = null;
                this.size = 0;
                return v6;
            }
        } else if (i > 0) {
            int hashCode = obj.hashCode();
            int i2 = this.size;
            if (i2 != 1) {
                if (i2 == 2) {
                    if (this.hash2 == hashCode && obj.equals(this.key2)) {
                        V v7 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return v7;
                    } else if (this.hash1 != hashCode || !obj.equals(this.key1)) {
                        return null;
                    } else {
                        V v8 = this.value1;
                        this.hash1 = this.hash2;
                        this.key1 = this.key2;
                        this.value1 = this.value2;
                        this.hash2 = 0;
                        this.key2 = null;
                        this.value2 = null;
                        this.size = 1;
                        return v8;
                    }
                } else if (i2 == 3) {
                    if (this.hash3 == hashCode && obj.equals(this.key3)) {
                        V v9 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v9;
                    } else if (this.hash2 == hashCode && obj.equals(this.key2)) {
                        V v10 = this.value2;
                        this.hash2 = this.hash3;
                        this.key2 = this.key3;
                        this.value2 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v10;
                    } else if (this.hash1 != hashCode || !obj.equals(this.key1)) {
                        return null;
                    } else {
                        V v11 = this.value1;
                        this.hash1 = this.hash3;
                        this.key1 = this.key3;
                        this.value1 = this.value3;
                        this.hash3 = 0;
                        this.key3 = null;
                        this.value3 = null;
                        this.size = 2;
                        return v11;
                    }
                }
            } else if (this.hash1 == hashCode && obj.equals(this.key1)) {
                V v12 = this.value1;
                this.hash1 = 0;
                this.key1 = null;
                this.value1 = null;
                this.size = 0;
                return v12;
            }
        }
        return null;
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.size();
        }
        return this.size;
    }

    public String toString() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.toString();
        }
        if (this.size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(128);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        int i = this.size;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    K k = this.key3;
                    if (k == this) {
                        k = "(this Map)";
                    }
                    sb.append(k);
                    sb.append(Chars.EQ);
                    V v = this.value3;
                    if (v == this) {
                        v = "(this Map)";
                    }
                    sb.append(v);
                    sb.append(JsonReaderKt.COMMA);
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Invalid map index: ");
                    outline107.append(this.size);
                    throw new IllegalStateException(outline107.toString());
                }
            }
            K k2 = this.key2;
            if (k2 == this) {
                k2 = "(this Map)";
            }
            sb.append(k2);
            sb.append(Chars.EQ);
            V v2 = this.value2;
            if (v2 == this) {
                v2 = "(this Map)";
            }
            sb.append(v2);
            sb.append(JsonReaderKt.COMMA);
        }
        K k3 = this.key1;
        if (k3 == this) {
            k3 = "(this Map)";
        }
        sb.append(k3);
        sb.append(Chars.EQ);
        V v3 = this.value1;
        if (v3 == this) {
            v3 = "(this Map)";
        }
        sb.append(v3);
        sb.append(JsonReaderKt.END_OBJ);
        return sb.toString();
    }

    @Override // java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        AbstractHashedMap<K, V> abstractHashedMap = this.delegateMap;
        if (abstractHashedMap != null) {
            return abstractHashedMap.mo12691values();
        }
        return new Values(this);
    }

    public Flat3Map(Map<? extends K, ? extends V> map) {
        putAll(map);
    }

    public Flat3Map<K, V> clone() {
        try {
            Flat3Map<K, V> flat3Map = (Flat3Map) super.clone();
            if (flat3Map.delegateMap != null) {
                flat3Map.delegateMap = flat3Map.delegateMap.mo12718clone();
            }
            return flat3Map;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }
}

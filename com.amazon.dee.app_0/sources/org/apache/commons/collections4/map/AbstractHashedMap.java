package org.apache.commons.collections4.map;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.commons.collections4.IterableMap;
import org.apache.commons.collections4.KeyValue;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes4.dex */
public class AbstractHashedMap<K, V> extends AbstractMap<K, V> implements IterableMap<K, V> {
    protected static final int DEFAULT_CAPACITY = 16;
    protected static final float DEFAULT_LOAD_FACTOR = 0.75f;
    protected static final int DEFAULT_THRESHOLD = 12;
    protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
    protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
    protected static final int MAXIMUM_CAPACITY = 1073741824;
    protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
    protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
    protected static final Object NULL = new Object();
    protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
    protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
    transient HashEntry<K, V>[] data;
    transient EntrySet<K, V> entrySet;
    transient KeySet<K> keySet;
    transient float loadFactor;
    transient int modCount;
    transient int size;
    transient int threshold;
    transient Values<V> values;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class EntrySet<K, V> extends AbstractSet<Map.Entry<K, V>> {
        private final AbstractHashedMap<K, V> parent;

        /* JADX INFO: Access modifiers changed from: protected */
        public EntrySet(AbstractHashedMap<K, V> abstractHashedMap) {
            this.parent = abstractHashedMap;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public void clear() {
            this.parent.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean contains(Object obj) {
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                HashEntry<K, V> mo12703getEntry = this.parent.mo12703getEntry(entry.getKey());
                return mo12703getEntry != null && mo12703getEntry.equals(entry);
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public Iterator<Map.Entry<K, V>> iterator() {
            return this.parent.createEntrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public boolean remove(Object obj) {
            if ((obj instanceof Map.Entry) && contains(obj)) {
                this.parent.mo12668remove(((Map.Entry) obj).getKey());
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public int size() {
            return this.parent.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class EntrySetIterator<K, V> extends HashIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        protected EntrySetIterator(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return super.nextEntry();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class HashEntry<K, V> implements Map.Entry<K, V>, KeyValue<K, V> {
        protected int hashCode;
        protected Object key;
        protected HashEntry<K, V> next;
        protected Object value;

        /* JADX INFO: Access modifiers changed from: protected */
        public HashEntry(HashEntry<K, V> hashEntry, int i, Object obj, V v) {
            this.next = hashEntry;
            this.hashCode = i;
            this.key = obj;
            this.value = v;
        }

        @Override // java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (mo12677getKey() != null ? mo12677getKey().equals(entry.getKey()) : entry.getKey() == null) {
                if (mo12678getValue() == null) {
                    if (entry.getValue() == null) {
                        return true;
                    }
                } else if (mo12678getValue().equals(entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        /* renamed from: getKey */
        public K mo12677getKey() {
            K k = (K) this.key;
            if (k == AbstractHashedMap.NULL) {
                return null;
            }
            return k;
        }

        @Override // java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        /* renamed from: getValue */
        public V mo12678getValue() {
            return (V) this.value;
        }

        @Override // java.util.Map.Entry
        public int hashCode() {
            int i = 0;
            int hashCode = mo12677getKey() == null ? 0 : mo12677getKey().hashCode();
            if (mo12678getValue() != null) {
                i = mo12678getValue().hashCode();
            }
            return hashCode ^ i;
        }

        @Override // java.util.Map.Entry
        public V setValue(V v) {
            V v2 = (V) this.value;
            this.value = v;
            return v2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(mo12677getKey());
            sb.append(Chars.EQ);
            sb.append(mo12678getValue());
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static abstract class HashIterator<K, V> {
        private int expectedModCount;
        private int hashIndex;
        private HashEntry<K, V> last;
        private HashEntry<K, V> next;
        private final AbstractHashedMap<K, V> parent;

        protected HashIterator(AbstractHashedMap<K, V> abstractHashedMap) {
            this.parent = abstractHashedMap;
            HashEntry<K, V>[] hashEntryArr = abstractHashedMap.data;
            int length = hashEntryArr.length;
            HashEntry<K, V> hashEntry = null;
            while (length > 0 && hashEntry == null) {
                length--;
                hashEntry = hashEntryArr[length];
            }
            this.next = hashEntry;
            this.hashIndex = length;
            this.expectedModCount = abstractHashedMap.modCount;
        }

        protected HashEntry<K, V> currentEntry() {
            return this.last;
        }

        public boolean hasNext() {
            return this.next != null;
        }

        protected HashEntry<K, V> nextEntry() {
            AbstractHashedMap<K, V> abstractHashedMap = this.parent;
            if (abstractHashedMap.modCount == this.expectedModCount) {
                HashEntry<K, V> hashEntry = this.next;
                if (hashEntry != null) {
                    HashEntry<K, V>[] hashEntryArr = abstractHashedMap.data;
                    int i = this.hashIndex;
                    HashEntry<K, V> hashEntry2 = hashEntry.next;
                    while (hashEntry2 == null && i > 0) {
                        i--;
                        hashEntry2 = hashEntryArr[i];
                    }
                    this.next = hashEntry2;
                    this.hashIndex = i;
                    this.last = hashEntry;
                    return hashEntry;
                }
                throw new NoSuchElementException(AbstractHashedMap.NO_NEXT_ENTRY);
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            HashEntry<K, V> hashEntry = this.last;
            if (hashEntry != null) {
                AbstractHashedMap<K, V> abstractHashedMap = this.parent;
                if (abstractHashedMap.modCount == this.expectedModCount) {
                    abstractHashedMap.mo12668remove(hashEntry.mo12677getKey());
                    this.last = null;
                    this.expectedModCount = this.parent.modCount;
                    return;
                }
                throw new ConcurrentModificationException();
            }
            throw new IllegalStateException(AbstractHashedMap.REMOVE_INVALID);
        }

        public String toString() {
            if (this.last != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Iterator[");
                outline107.append(this.last.mo12677getKey());
                outline107.append(Config.Compare.EQUAL_TO);
                outline107.append(this.last.mo12678getValue());
                outline107.append("]");
                return outline107.toString();
            }
            return "Iterator[]";
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class HashMapIterator<K, V> extends HashIterator<K, V> implements MapIterator<K, V> {
        protected HashMapIterator(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            HashEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.mo12677getKey();
            }
            throw new IllegalStateException(AbstractHashedMap.GETKEY_INVALID);
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            HashEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.mo12678getValue();
            }
            throw new IllegalStateException(AbstractHashedMap.GETVALUE_INVALID);
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            return super.nextEntry().mo12677getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            HashEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException(AbstractHashedMap.SETVALUE_INVALID);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class KeySet<K> extends AbstractSet<K> {
        private final AbstractHashedMap<K, ?> parent;

        /* JADX INFO: Access modifiers changed from: protected */
        public KeySet(AbstractHashedMap<K, ?> abstractHashedMap) {
            this.parent = abstractHashedMap;
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
            return this.parent.createKeySetIterator();
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

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class KeySetIterator<K> extends HashIterator<K, Object> implements Iterator<K> {
        protected KeySetIterator(AbstractHashedMap<K, ?> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // java.util.Iterator
        public K next() {
            return super.nextEntry().mo12677getKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class Values<V> extends AbstractCollection<V> {
        private final AbstractHashedMap<?, V> parent;

        /* JADX INFO: Access modifiers changed from: protected */
        public Values(AbstractHashedMap<?, V> abstractHashedMap) {
            this.parent = abstractHashedMap;
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
            return this.parent.createValuesIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.parent.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class ValuesIterator<V> extends HashIterator<Object, V> implements Iterator<V> {
        protected ValuesIterator(AbstractHashedMap<?, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // java.util.Iterator
        public V next() {
            return super.nextEntry().mo12678getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHashedMap() {
    }

    private void _putAll(Map<? extends K, ? extends V> map) {
        int size = map.size();
        if (size == 0) {
            return;
        }
        ensureCapacity(calculateNewCapacity((int) (((this.size + size) / this.loadFactor) + 1.0f)));
        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    protected void addEntry(HashEntry<K, V> hashEntry, int i) {
        this.data[i] = hashEntry;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addMapping(int i, int i2, K k, V v) {
        this.modCount++;
        addEntry(mo12709createEntry(this.data[i], i2, k, v), i);
        this.size++;
        checkCapacity();
    }

    protected int calculateNewCapacity(int i) {
        if (i > 1073741824) {
            return 1073741824;
        }
        int i2 = 1;
        while (i2 < i) {
            i2 <<= 1;
        }
        if (i2 <= 1073741824) {
            return i2;
        }
        return 1073741824;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int calculateThreshold(int i, float f) {
        return (int) (i * f);
    }

    protected void checkCapacity() {
        int length;
        if (this.size < this.threshold || (length = this.data.length * 2) > 1073741824) {
            return;
        }
        ensureCapacity(length);
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        this.modCount++;
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (int length = hashEntryArr.length - 1; length >= 0; length--) {
            hashEntryArr[length] = null;
        }
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (HashEntry<K, V> hashEntry = hashEntryArr[hashIndex(hash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        HashEntry<K, V>[] hashEntryArr;
        HashEntry<K, V>[] hashEntryArr2;
        if (obj == null) {
            for (HashEntry<K, V> hashEntry : this.data) {
                for (; hashEntry != null; hashEntry = hashEntry.next) {
                    if (hashEntry.mo12678getValue() == null) {
                        return true;
                    }
                }
            }
        } else {
            for (HashEntry<K, V> hashEntry2 : this.data) {
                for (; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
                    if (isEqualValue(obj, hashEntry2.mo12678getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Object convertKey(Object obj) {
        return obj == null ? NULL : obj;
    }

    /* renamed from: createEntry */
    protected HashEntry<K, V> mo12709createEntry(HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new HashEntry<>(hashEntry, i, convertKey(k), v);
    }

    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        if (size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new EntrySetIterator(this);
    }

    protected Iterator<K> createKeySetIterator() {
        if (size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new KeySetIterator(this);
    }

    protected Iterator<V> createValuesIterator() {
        if (size() == 0) {
            return EmptyIterator.emptyIterator();
        }
        return new ValuesIterator(this);
    }

    protected void destroyEntry(HashEntry<K, V> hashEntry) {
        hashEntry.next = null;
        hashEntry.key = null;
        hashEntry.value = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.loadFactor = objectInputStream.readFloat();
        int readInt = objectInputStream.readInt();
        int readInt2 = objectInputStream.readInt();
        init();
        this.threshold = calculateThreshold(readInt, this.loadFactor);
        this.data = new HashEntry[readInt];
        for (int i = 0; i < readInt2; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeFloat(this.loadFactor);
        objectOutputStream.writeInt(this.data.length);
        objectOutputStream.writeInt(this.size);
        MapIterator<K, V> mo12767mapIterator = mo12767mapIterator();
        while (mo12767mapIterator.hasNext()) {
            objectOutputStream.writeObject(mo12767mapIterator.mo12683next());
            objectOutputStream.writeObject(mo12767mapIterator.mo12682getValue());
        }
    }

    protected void ensureCapacity(int i) {
        HashEntry<K, V>[] hashEntryArr = this.data;
        int length = hashEntryArr.length;
        if (i <= length) {
            return;
        }
        if (this.size == 0) {
            this.threshold = calculateThreshold(i, this.loadFactor);
            this.data = new HashEntry[i];
            return;
        }
        HashEntry<K, V>[] hashEntryArr2 = new HashEntry[i];
        this.modCount++;
        for (int i2 = length - 1; i2 >= 0; i2--) {
            HashEntry<K, V> hashEntry = hashEntryArr[i2];
            if (hashEntry != null) {
                hashEntryArr[i2] = null;
                while (true) {
                    HashEntry<K, V> hashEntry2 = hashEntry.next;
                    int hashIndex = hashIndex(hashEntry.hashCode, i);
                    hashEntry.next = hashEntryArr2[hashIndex];
                    hashEntryArr2[hashIndex] = hashEntry;
                    if (hashEntry2 == null) {
                        break;
                    }
                    hashEntry = hashEntry2;
                }
            }
        }
        this.threshold = calculateThreshold(i, this.loadFactor);
        this.data = hashEntryArr2;
    }

    protected int entryHashCode(HashEntry<K, V> hashEntry) {
        return hashEntry.hashCode;
    }

    protected K entryKey(HashEntry<K, V> hashEntry) {
        return hashEntry.mo12677getKey();
    }

    protected HashEntry<K, V> entryNext(HashEntry<K, V> hashEntry) {
        return hashEntry.next;
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet<>(this);
        }
        return this.entrySet;
    }

    protected V entryValue(HashEntry<K, V> hashEntry) {
        return hashEntry.mo12678getValue();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        Map map = (Map) obj;
        if (map.size() != size()) {
            return false;
        }
        MapIterator<K, V> mo12767mapIterator = mo12767mapIterator();
        while (mo12767mapIterator.hasNext()) {
            try {
                K mo12683next = mo12767mapIterator.mo12683next();
                V mo12682getValue = mo12767mapIterator.mo12682getValue();
                if (mo12682getValue == null) {
                    if (map.get(mo12683next) != null || !map.containsKey(mo12683next)) {
                        return false;
                    }
                } else if (!mo12682getValue.equals(map.get(mo12683next))) {
                    return false;
                }
            } catch (ClassCastException | NullPointerException unused) {
                return false;
            }
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (HashEntry<K, V> hashEntry = hashEntryArr[hashIndex(hash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                return hashEntry.mo12678getValue();
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: getEntry */
    public HashEntry<K, V> mo12703getEntry(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        HashEntry<K, V>[] hashEntryArr = this.data;
        for (HashEntry<K, V> hashEntry = hashEntryArr[hashIndex(hash, hashEntryArr.length)]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                return hashEntry;
            }
        }
        return null;
    }

    protected int hash(Object obj) {
        int hashCode = obj.hashCode();
        int i = hashCode + (~(hashCode << 9));
        int i2 = i ^ (i >>> 14);
        int i3 = i2 + (i2 << 4);
        return i3 ^ (i3 >>> 10);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public int hashCode() {
        Iterator<Map.Entry<K, V>> createEntrySetIterator = createEntrySetIterator();
        int i = 0;
        while (createEntrySetIterator.hasNext()) {
            i += createEntrySetIterator.next().hashCode();
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int hashIndex(int i, int i2) {
        return i & (i2 - 1);
    }

    protected void init() {
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        return this.size == 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEqualKey(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isEqualValue(Object obj, Object obj2) {
        return obj == obj2 || obj.equals(obj2);
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet<>(this);
        }
        return this.keySet;
    }

    @Override // org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        if (this.size == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new HashMapIterator(this);
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        Object convertKey = convertKey(k);
        int hash = hash(convertKey);
        int hashIndex = hashIndex(hash, this.data.length);
        for (HashEntry<K, V> hashEntry = this.data[hashIndex]; hashEntry != null; hashEntry = hashEntry.next) {
            if (hashEntry.hashCode == hash && isEqualKey(convertKey, hashEntry.key)) {
                V mo12678getValue = hashEntry.mo12678getValue();
                updateEntry(hashEntry, v);
                return mo12678getValue;
            }
        }
        addMapping(hashIndex, hash, k, v);
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public void putAll(Map<? extends K, ? extends V> map) {
        _putAll(map);
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        Object convertKey = convertKey(obj);
        int hash = hash(convertKey);
        int hashIndex = hashIndex(hash, this.data.length);
        HashEntry<K, V> hashEntry = null;
        for (HashEntry<K, V> hashEntry2 = this.data[hashIndex]; hashEntry2 != null; hashEntry2 = hashEntry2.next) {
            if (hashEntry2.hashCode == hash && isEqualKey(convertKey, hashEntry2.key)) {
                V mo12678getValue = hashEntry2.mo12678getValue();
                removeMapping(hashEntry2, hashIndex, hashEntry);
                return mo12678getValue;
            }
            hashEntry = hashEntry2;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeEntry(HashEntry<K, V> hashEntry, int i, HashEntry<K, V> hashEntry2) {
        if (hashEntry2 == null) {
            this.data[i] = hashEntry.next;
        } else {
            hashEntry2.next = hashEntry.next;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeMapping(HashEntry<K, V> hashEntry, int i, HashEntry<K, V> hashEntry2) {
        this.modCount++;
        removeEntry(hashEntry, i, hashEntry2);
        this.size--;
        destroyEntry(hashEntry);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reuseEntry(HashEntry<K, V> hashEntry, int i, int i2, K k, V v) {
        hashEntry.next = this.data[i];
        hashEntry.hashCode = i2;
        hashEntry.key = k;
        hashEntry.value = v;
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        return this.size;
    }

    @Override // java.util.AbstractMap
    public String toString() {
        if (size() == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(size() * 32);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        MapIterator<K, V> mo12767mapIterator = mo12767mapIterator();
        boolean hasNext = mo12767mapIterator.hasNext();
        while (hasNext) {
            K mo12683next = mo12767mapIterator.mo12683next();
            V mo12682getValue = mo12767mapIterator.mo12682getValue();
            if (mo12683next == this) {
                mo12683next = "(this Map)";
            }
            sb.append(mo12683next);
            sb.append(Chars.EQ);
            if (mo12682getValue == this) {
                mo12682getValue = "(this Map)";
            }
            sb.append(mo12682getValue);
            hasNext = mo12767mapIterator.hasNext();
            if (hasNext) {
                sb.append(JsonReaderKt.COMMA);
                sb.append(Chars.SPACE);
            }
        }
        sb.append(JsonReaderKt.END_OBJ);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void updateEntry(HashEntry<K, V> hashEntry, V v) {
        hashEntry.setValue(v);
    }

    @Override // java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        if (this.values == null) {
            this.values = new Values<>(this);
        }
        return this.values;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHashedMap(int i, float f, int i2) {
        this.loadFactor = f;
        this.data = new HashEntry[i];
        this.threshold = i2;
        init();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // java.util.AbstractMap
    /* renamed from: clone  reason: collision with other method in class */
    public AbstractHashedMap<K, V> mo12718clone() {
        try {
            AbstractHashedMap<K, V> abstractHashedMap = (AbstractHashedMap) super.clone();
            abstractHashedMap.data = new HashEntry[this.data.length];
            abstractHashedMap.entrySet = null;
            abstractHashedMap.keySet = null;
            abstractHashedMap.values = null;
            abstractHashedMap.modCount = 0;
            abstractHashedMap.size = 0;
            abstractHashedMap.init();
            abstractHashedMap.putAll(this);
            return abstractHashedMap;
        } catch (CloneNotSupportedException unused) {
            throw new InternalError();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHashedMap(int i) {
        this(i, 0.75f);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHashedMap(int i, float f) {
        if (i >= 0) {
            if (f > 0.0f && !Float.isNaN(f)) {
                this.loadFactor = f;
                int calculateNewCapacity = calculateNewCapacity(i);
                this.threshold = calculateThreshold(calculateNewCapacity, f);
                this.data = new HashEntry[calculateNewCapacity];
                init();
                return;
            }
            throw new IllegalArgumentException("Load factor must be greater than 0");
        }
        throw new IllegalArgumentException("Initial capacity must be a non negative number");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractHashedMap(Map<? extends K, ? extends V> map) {
        this(Math.max(map.size() * 2, 16), 0.75f);
        _putAll(map);
    }
}

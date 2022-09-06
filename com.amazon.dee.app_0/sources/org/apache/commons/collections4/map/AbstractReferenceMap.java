package org.apache.commons.collections4.map;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.keyvalue.DefaultMapEntry;
import org.apache.commons.collections4.map.AbstractHashedMap;
/* loaded from: classes4.dex */
public abstract class AbstractReferenceMap<K, V> extends AbstractHashedMap<K, V> {
    private ReferenceStrength keyType;
    private boolean purgeValues;
    private transient ReferenceQueue<Object> queue;
    private ReferenceStrength valueType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ReferenceBaseIterator<K, V> {
        K currentKey;
        V currentValue;
        ReferenceEntry<K, V> entry;
        int expectedModCount;
        int index;
        K nextKey;
        V nextValue;
        final AbstractReferenceMap<K, V> parent;
        ReferenceEntry<K, V> previous;

        public ReferenceBaseIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            this.parent = abstractReferenceMap;
            this.index = abstractReferenceMap.size() != 0 ? abstractReferenceMap.data.length : 0;
            this.expectedModCount = abstractReferenceMap.modCount;
        }

        private void checkMod() {
            if (this.parent.modCount == this.expectedModCount) {
                return;
            }
            throw new ConcurrentModificationException();
        }

        private boolean nextNull() {
            return this.nextKey == null || this.nextValue == null;
        }

        protected ReferenceEntry<K, V> currentEntry() {
            checkMod();
            return this.previous;
        }

        public boolean hasNext() {
            checkMod();
            while (nextNull()) {
                ReferenceEntry<K, V> referenceEntry = this.entry;
                int i = this.index;
                while (referenceEntry == null && i > 0) {
                    i--;
                    referenceEntry = (ReferenceEntry) this.parent.data[i];
                }
                this.entry = referenceEntry;
                this.index = i;
                if (referenceEntry == null) {
                    this.currentKey = null;
                    this.currentValue = null;
                    return false;
                }
                this.nextKey = referenceEntry.mo12677getKey();
                this.nextValue = referenceEntry.mo12678getValue();
                if (nextNull()) {
                    this.entry = this.entry.next();
                }
            }
            return true;
        }

        protected ReferenceEntry<K, V> nextEntry() {
            checkMod();
            if (nextNull() && !hasNext()) {
                throw new NoSuchElementException();
            }
            ReferenceEntry<K, V> referenceEntry = this.entry;
            this.previous = referenceEntry;
            this.entry = referenceEntry.next();
            this.currentKey = this.nextKey;
            this.currentValue = this.nextValue;
            this.nextKey = null;
            this.nextValue = null;
            return this.previous;
        }

        public void remove() {
            checkMod();
            if (this.previous != null) {
                this.parent.mo12668remove(this.currentKey);
                this.previous = null;
                this.currentKey = null;
                this.currentValue = null;
                this.expectedModCount = this.parent.modCount;
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes4.dex */
    public static class ReferenceEntry<K, V> extends AbstractHashedMap.HashEntry<K, V> {
        private final AbstractReferenceMap<K, V> parent;

        public ReferenceEntry(AbstractReferenceMap<K, V> abstractReferenceMap, AbstractHashedMap.HashEntry<K, V> hashEntry, int i, K k, V v) {
            super(hashEntry, i, null, null);
            this.parent = abstractReferenceMap;
            this.key = toReference(((AbstractReferenceMap) abstractReferenceMap).keyType, k, i);
            this.value = toReference(((AbstractReferenceMap) abstractReferenceMap).valueType, v, i);
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry
        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (key == null || value == null) {
                return false;
            }
            return this.parent.isEqualKey(key, this.key) && this.parent.isEqualValue(value, mo12678getValue());
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        /* renamed from: getKey */
        public K mo12677getKey() {
            return ((AbstractReferenceMap) this.parent).keyType == ReferenceStrength.HARD ? (K) this.key : (K) ((Reference) this.key).get();
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry, org.apache.commons.collections4.KeyValue
        /* renamed from: getValue */
        public V mo12678getValue() {
            return ((AbstractReferenceMap) this.parent).valueType == ReferenceStrength.HARD ? (V) this.value : (V) ((Reference) this.value).get();
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry
        public int hashCode() {
            return this.parent.hashEntry(mo12677getKey(), mo12678getValue());
        }

        protected ReferenceEntry<K, V> next() {
            return (ReferenceEntry) this.next;
        }

        boolean purge(Reference<?> reference) {
            boolean z = true;
            if (!(((AbstractReferenceMap) this.parent).keyType != ReferenceStrength.HARD && this.key == reference) && (((AbstractReferenceMap) this.parent).valueType == ReferenceStrength.HARD || this.value != reference)) {
                z = false;
            }
            if (z) {
                if (((AbstractReferenceMap) this.parent).keyType != ReferenceStrength.HARD) {
                    ((Reference) this.key).clear();
                }
                if (((AbstractReferenceMap) this.parent).valueType == ReferenceStrength.HARD) {
                    if (((AbstractReferenceMap) this.parent).purgeValues) {
                        this.value = null;
                    }
                } else {
                    ((Reference) this.value).clear();
                }
            }
            return z;
        }

        @Override // org.apache.commons.collections4.map.AbstractHashedMap.HashEntry, java.util.Map.Entry
        public V setValue(V v) {
            V mo12678getValue = mo12678getValue();
            if (((AbstractReferenceMap) this.parent).valueType != ReferenceStrength.HARD) {
                ((Reference) this.value).clear();
            }
            this.value = toReference(((AbstractReferenceMap) this.parent).valueType, v, this.hashCode);
            return mo12678getValue;
        }

        protected <T> Object toReference(ReferenceStrength referenceStrength, T t, int i) {
            if (referenceStrength == ReferenceStrength.HARD) {
                return t;
            }
            if (referenceStrength == ReferenceStrength.SOFT) {
                return new SoftRef(i, t, ((AbstractReferenceMap) this.parent).queue);
            }
            if (referenceStrength == ReferenceStrength.WEAK) {
                return new WeakRef(i, t, ((AbstractReferenceMap) this.parent).queue);
            }
            throw new Error();
        }
    }

    /* loaded from: classes4.dex */
    static class ReferenceEntrySet<K, V> extends AbstractHashedMap.EntrySet<K, V> {
        protected ReferenceEntrySet(AbstractHashedMap<K, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator<Map.Entry<K, V>> it2 = iterator();
            while (it2.hasNext()) {
                arrayList.add(new DefaultMapEntry(it2.next()));
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* loaded from: classes4.dex */
    static class ReferenceEntrySetIterator<K, V> extends ReferenceBaseIterator<K, V> implements Iterator<Map.Entry<K, V>> {
        public ReferenceEntrySetIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        @Override // java.util.Iterator
        public Map.Entry<K, V> next() {
            return nextEntry();
        }
    }

    /* loaded from: classes4.dex */
    static class ReferenceKeySet<K> extends AbstractHashedMap.KeySet<K> {
        protected ReferenceKeySet(AbstractHashedMap<K, ?> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator<K> it2 = iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next());
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* loaded from: classes4.dex */
    static class ReferenceKeySetIterator<K> extends ReferenceBaseIterator<K, Object> implements Iterator<K> {
        ReferenceKeySetIterator(AbstractReferenceMap<K, ?> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        @Override // java.util.Iterator
        public K next() {
            return nextEntry().mo12677getKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class ReferenceMapIterator<K, V> extends ReferenceBaseIterator<K, V> implements MapIterator<K, V> {
        protected ReferenceMapIterator(AbstractReferenceMap<K, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getKey */
        public K mo12681getKey() {
            ReferenceEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.mo12677getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator
        /* renamed from: getValue */
        public V mo12682getValue() {
            ReferenceEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.mo12678getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        @Override // org.apache.commons.collections4.MapIterator, java.util.Iterator
        /* renamed from: next */
        public K mo12683next() {
            return nextEntry().mo12677getKey();
        }

        @Override // org.apache.commons.collections4.MapIterator
        public V setValue(V v) {
            ReferenceEntry<K, V> currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }
    }

    /* loaded from: classes4.dex */
    public enum ReferenceStrength {
        HARD(0),
        SOFT(1),
        WEAK(2);
        
        public final int value;

        ReferenceStrength(int i) {
            this.value = i;
        }

        public static ReferenceStrength resolve(int i) {
            if (i != 0) {
                if (i == 1) {
                    return SOFT;
                }
                if (i == 2) {
                    return WEAK;
                }
                throw new IllegalArgumentException();
            }
            return HARD;
        }
    }

    /* loaded from: classes4.dex */
    static class ReferenceValues<V> extends AbstractHashedMap.Values<V> {
        protected ReferenceValues(AbstractHashedMap<?, V> abstractHashedMap) {
            super(abstractHashedMap);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public Object[] toArray() {
            return toArray(new Object[size()]);
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public <T> T[] toArray(T[] tArr) {
            ArrayList arrayList = new ArrayList(size());
            Iterator<V> it2 = iterator();
            while (it2.hasNext()) {
                arrayList.add(it2.next());
            }
            return (T[]) arrayList.toArray(tArr);
        }
    }

    /* loaded from: classes4.dex */
    static class ReferenceValuesIterator<V> extends ReferenceBaseIterator<Object, V> implements Iterator<V> {
        ReferenceValuesIterator(AbstractReferenceMap<?, V> abstractReferenceMap) {
            super(abstractReferenceMap);
        }

        @Override // java.util.Iterator
        public V next() {
            return nextEntry().mo12678getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class SoftRef<T> extends SoftReference<T> {
        private final int hash;

        public SoftRef(int i, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.hash = i;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes4.dex */
    public static class WeakRef<T> extends WeakReference<T> {
        private final int hash;

        public WeakRef(int i, T t, ReferenceQueue<? super T> referenceQueue) {
            super(t, referenceQueue);
            this.hash = i;
        }

        public int hashCode() {
            return this.hash;
        }
    }

    protected AbstractReferenceMap() {
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public void clear() {
        super.clear();
        do {
        } while (this.queue.poll() != null);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsKey(Object obj) {
        purgeBeforeRead();
        AbstractHashedMap.HashEntry<K, V> mo12703getEntry = mo12703getEntry(obj);
        return (mo12703getEntry == null || mo12703getEntry.getValue() == null) ? false : true;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean containsValue(Object obj) {
        purgeBeforeRead();
        if (obj == null) {
            return false;
        }
        return super.containsValue(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    /* renamed from: createEntry */
    protected /* bridge */ /* synthetic */ AbstractHashedMap.HashEntry mo12709createEntry(AbstractHashedMap.HashEntry hashEntry, int i, Object obj, Object obj2) {
        return mo12709createEntry((AbstractHashedMap.HashEntry<int, Object>) hashEntry, i, (int) obj, obj2);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<Map.Entry<K, V>> createEntrySetIterator() {
        return new ReferenceEntrySetIterator(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<K> createKeySetIterator() {
        return new ReferenceKeySetIterator(this);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected Iterator<V> createValuesIterator() {
        return new ReferenceValuesIterator(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.keyType = ReferenceStrength.resolve(objectInputStream.readInt());
        this.valueType = ReferenceStrength.resolve(objectInputStream.readInt());
        this.purgeValues = objectInputStream.readBoolean();
        this.loadFactor = objectInputStream.readFloat();
        int readInt = objectInputStream.readInt();
        init();
        this.data = new AbstractHashedMap.HashEntry[readInt];
        this.threshold = calculateThreshold(this.data.length, this.loadFactor);
        while (true) {
            Object readObject = objectInputStream.readObject();
            if (readObject == null) {
                return;
            }
            put(readObject, objectInputStream.readObject());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.keyType.value);
        objectOutputStream.writeInt(this.valueType.value);
        objectOutputStream.writeBoolean(this.purgeValues);
        objectOutputStream.writeFloat(this.loadFactor);
        objectOutputStream.writeInt(this.data.length);
        MapIterator<K, V> mo12767mapIterator = mo12767mapIterator();
        while (mo12767mapIterator.hasNext()) {
            objectOutputStream.writeObject(mo12767mapIterator.mo12683next());
            objectOutputStream.writeObject(mo12767mapIterator.mo12682getValue());
        }
        objectOutputStream.writeObject(null);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<Map.Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new ReferenceEntrySet(this);
        }
        return this.entrySet;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: get */
    public V mo12663get(Object obj) {
        purgeBeforeRead();
        AbstractHashedMap.HashEntry<K, V> mo12703getEntry = mo12703getEntry(obj);
        if (mo12703getEntry == null) {
            return null;
        }
        return mo12703getEntry.getValue();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    /* renamed from: getEntry */
    public AbstractHashedMap.HashEntry<K, V> mo12703getEntry(Object obj) {
        if (obj == null) {
            return null;
        }
        return super.mo12703getEntry(obj);
    }

    protected int hashEntry(Object obj, Object obj2) {
        int i = 0;
        int hashCode = obj == null ? 0 : obj.hashCode();
        if (obj2 != null) {
            i = obj2.hashCode();
        }
        return hashCode ^ i;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    protected void init() {
        this.queue = new ReferenceQueue<>();
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public boolean isEmpty() {
        purgeBeforeRead();
        return super.isEmpty();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    public boolean isEqualKey(Object obj, Object obj2) {
        if (this.keyType != ReferenceStrength.HARD) {
            obj2 = ((Reference) obj2).get();
        }
        return obj == obj2 || obj.equals(obj2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isKeyType(ReferenceStrength referenceStrength) {
        return this.keyType == referenceStrength;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new ReferenceKeySet(this);
        }
        return this.keySet;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, org.apache.commons.collections4.IterableGet
    /* renamed from: mapIterator */
    public MapIterator<K, V> mo12767mapIterator() {
        return new ReferenceMapIterator(this);
    }

    protected void purge() {
        Reference<? extends Object> poll = this.queue.poll();
        while (poll != null) {
            purge(poll);
            poll = this.queue.poll();
        }
    }

    protected void purgeBeforeRead() {
        purge();
    }

    protected void purgeBeforeWrite() {
        purge();
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Put
    public V put(K k, V v) {
        if (k != null) {
            if (v != null) {
                purgeBeforeWrite();
                return (V) super.put(k, v);
            }
            throw new NullPointerException("null values not allowed");
        }
        throw new NullPointerException("null keys not allowed");
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: remove */
    public V mo12668remove(Object obj) {
        if (obj == null) {
            return null;
        }
        purgeBeforeWrite();
        return (V) super.mo12668remove(obj);
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    public int size() {
        purgeBeforeRead();
        return super.size();
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap, java.util.AbstractMap, java.util.Map, org.apache.commons.collections4.Get
    /* renamed from: values */
    public Collection<V> mo12691values() {
        if (this.values == null) {
            this.values = new ReferenceValues(this);
        }
        return this.values;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractReferenceMap(ReferenceStrength referenceStrength, ReferenceStrength referenceStrength2, int i, float f, boolean z) {
        super(i, f);
        this.keyType = referenceStrength;
        this.valueType = referenceStrength2;
        this.purgeValues = z;
    }

    @Override // org.apache.commons.collections4.map.AbstractHashedMap
    /* renamed from: createEntry  reason: collision with other method in class */
    protected ReferenceEntry<K, V> mo12709createEntry(AbstractHashedMap.HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new ReferenceEntry<>(this, hashEntry, i, k, v);
    }

    protected void purge(Reference<?> reference) {
        int hashIndex = hashIndex(reference.hashCode(), this.data.length);
        AbstractHashedMap.HashEntry<K, V> hashEntry = this.data[hashIndex];
        AbstractHashedMap.HashEntry<K, V> hashEntry2 = null;
        while (true) {
            AbstractHashedMap.HashEntry<K, V> hashEntry3 = hashEntry2;
            hashEntry2 = hashEntry;
            if (hashEntry2 != null) {
                if (((ReferenceEntry) hashEntry2).purge(reference)) {
                    if (hashEntry3 == null) {
                        this.data[hashIndex] = hashEntry2.next;
                    } else {
                        hashEntry3.next = hashEntry2.next;
                    }
                    this.size--;
                    return;
                }
                hashEntry = hashEntry2.next;
            } else {
                return;
            }
        }
    }
}

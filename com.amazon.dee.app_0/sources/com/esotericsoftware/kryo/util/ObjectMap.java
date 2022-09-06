package com.esotericsoftware.kryo.util;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class ObjectMap<K, V> {
    private static final int PRIME1 = -1105259343;
    private static final int PRIME2 = -1262997959;
    private static final int PRIME3 = -825114047;
    static Random random = new Random();
    int capacity;
    private int hashShift;
    K[] keyTable;
    private float loadFactor;
    private int mask;
    private int pushIterations;
    public int size;
    private int stashCapacity;
    int stashSize;
    private int threshold;
    V[] valueTable;

    /* loaded from: classes2.dex */
    public static class Entries<K, V> extends MapIterator<K, V> implements Iterable<Entry<K, V>>, Iterator<Entry<K, V>> {
        Entry<K, V> entry;

        public Entries(ObjectMap<K, V> objectMap) {
            super(objectMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.lang.Iterable
        public Iterator<Entry<K, V>> iterator() {
            return this;
        }

        @Override // com.esotericsoftware.kryo.util.ObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.ObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        @Override // java.util.Iterator
        /* renamed from: next */
        public Entry<K, V> mo6851next() {
            if (this.hasNext) {
                ObjectMap<K, V> objectMap = this.map;
                K[] kArr = objectMap.keyTable;
                Entry<K, V> entry = this.entry;
                int i = this.nextIndex;
                entry.key = kArr[i];
                entry.value = objectMap.valueTable[i];
                this.currentIndex = i;
                advance();
                return this.entry;
            }
            throw new NoSuchElementException();
        }
    }

    /* loaded from: classes2.dex */
    public static class Entry<K, V> {
        public K key;
        public V value;

        public String toString() {
            return this.key + Config.Compare.EQUAL_TO + this.value;
        }
    }

    /* loaded from: classes2.dex */
    public static class Keys<K> extends MapIterator<K, Object> implements Iterable<K>, Iterator<K> {
        public Keys(ObjectMap<K, ?> objectMap) {
            super(objectMap);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.lang.Iterable
        public Iterator<K> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public K next() {
            if (this.hasNext) {
                K[] kArr = this.map.keyTable;
                int i = this.nextIndex;
                K k = kArr[i];
                this.currentIndex = i;
                advance();
                return k;
            }
            throw new NoSuchElementException();
        }

        @Override // com.esotericsoftware.kryo.util.ObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.ObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public ArrayList<K> toArray() {
            ArrayList<K> arrayList = new ArrayList<>(this.map.size);
            while (this.hasNext) {
                arrayList.add(next());
            }
            return arrayList;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MapIterator<K, V> {
        int currentIndex;
        public boolean hasNext;
        final ObjectMap<K, V> map;
        int nextIndex;

        public MapIterator(ObjectMap<K, V> objectMap) {
            this.map = objectMap;
            reset();
        }

        void advance() {
            this.hasNext = false;
            ObjectMap<K, V> objectMap = this.map;
            K[] kArr = objectMap.keyTable;
            int i = objectMap.capacity + objectMap.stashSize;
            do {
                int i2 = this.nextIndex + 1;
                this.nextIndex = i2;
                if (i2 >= i) {
                    return;
                }
            } while (kArr[this.nextIndex] == null);
            this.hasNext = true;
        }

        public void remove() {
            int i = this.currentIndex;
            if (i >= 0) {
                ObjectMap<K, V> objectMap = this.map;
                if (i >= objectMap.capacity) {
                    objectMap.removeStashIndex(i);
                    this.nextIndex = this.currentIndex - 1;
                    advance();
                } else {
                    objectMap.keyTable[i] = null;
                    objectMap.valueTable[i] = null;
                }
                this.currentIndex = -1;
                ObjectMap<K, V> objectMap2 = this.map;
                objectMap2.size--;
                return;
            }
            throw new IllegalStateException("next must be called before remove.");
        }

        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = -1;
            advance();
        }
    }

    public ObjectMap() {
        this(32, 0.8f);
    }

    private boolean containsKeyStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (k.equals(kArr[i])) {
                return true;
            }
            i++;
        }
        return false;
    }

    private V getStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (k.equals(kArr[i])) {
                return this.valueTable[i];
            }
            i++;
        }
        return null;
    }

    private int hash2(int i) {
        int i2 = i * PRIME2;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    private int hash3(int i) {
        int i2 = i * PRIME3;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    public static int nextPowerOfTwo(int i) {
        if (i == 0) {
            return 1;
        }
        int i2 = i - 1;
        int i3 = i2 | (i2 >> 1);
        int i4 = i3 | (i3 >> 2);
        int i5 = i4 | (i4 >> 4);
        int i6 = i5 | (i5 >> 8);
        return (i6 | (i6 >> 16)) + 1;
    }

    private void push(K k, V v, int i, K k2, int i2, K k3, int i3, K k4) {
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i4 = this.mask;
        int i5 = this.pushIterations;
        K k5 = k;
        V v2 = v;
        int i6 = i;
        K k6 = k2;
        int i7 = i2;
        K k7 = k3;
        int i8 = i3;
        int i9 = 0;
        K k8 = k4;
        do {
            int nextInt = random.nextInt(3);
            if (nextInt == 0) {
                V v3 = vArr[i6];
                kArr[i6] = k5;
                vArr[i6] = v2;
                v2 = v3;
                k5 = k6;
            } else if (nextInt != 1) {
                V v4 = vArr[i8];
                kArr[i8] = k5;
                vArr[i8] = v2;
                k5 = k8;
                v2 = v4;
            } else {
                V v5 = vArr[i7];
                kArr[i7] = k5;
                vArr[i7] = v2;
                v2 = v5;
                k5 = k7;
            }
            int hashCode = k5.hashCode();
            i6 = hashCode & i4;
            k6 = kArr[i6];
            if (k6 == null) {
                kArr[i6] = k5;
                vArr[i6] = v2;
                int i10 = this.size;
                this.size = i10 + 1;
                if (i10 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i7 = hash2(hashCode);
            k7 = kArr[i7];
            if (k7 == null) {
                kArr[i7] = k5;
                vArr[i7] = v2;
                int i11 = this.size;
                this.size = i11 + 1;
                if (i11 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i8 = hash3(hashCode);
            k8 = kArr[i8];
            if (k8 == null) {
                kArr[i8] = k5;
                vArr[i8] = v2;
                int i12 = this.size;
                this.size = i12 + 1;
                if (i12 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i9++;
        } while (i9 != i5);
        putStash(k5, v2);
    }

    private void putResize(K k, V v) {
        int hashCode = k.hashCode();
        int i = hashCode & this.mask;
        K[] kArr = this.keyTable;
        K k2 = kArr[i];
        if (k2 == null) {
            kArr[i] = k;
            this.valueTable[i] = v;
            int i2 = this.size;
            this.size = i2 + 1;
            if (i2 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        int hash2 = hash2(hashCode);
        K[] kArr2 = this.keyTable;
        K k3 = kArr2[hash2];
        if (k3 == null) {
            kArr2[hash2] = k;
            this.valueTable[hash2] = v;
            int i3 = this.size;
            this.size = i3 + 1;
            if (i3 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        int hash3 = hash3(hashCode);
        K[] kArr3 = this.keyTable;
        K k4 = kArr3[hash3];
        if (k4 == null) {
            kArr3[hash3] = k;
            this.valueTable[hash3] = v;
            int i4 = this.size;
            this.size = i4 + 1;
            if (i4 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        push(k, v, i, k2, hash2, k3, hash3, k4);
    }

    private void putStash(K k, V v) {
        int i = this.stashSize;
        if (i == this.stashCapacity) {
            resize(this.capacity << 1);
            put_internal(k, v);
            return;
        }
        int i2 = this.capacity + i;
        this.keyTable[i2] = k;
        this.valueTable[i2] = v;
        this.stashSize = i + 1;
        this.size++;
    }

    private V put_internal(K k, V v) {
        K[] kArr = this.keyTable;
        int hashCode = k.hashCode();
        int i = hashCode & this.mask;
        K k2 = kArr[i];
        if (k.equals(k2)) {
            V[] vArr = this.valueTable;
            V v2 = vArr[i];
            vArr[i] = v;
            return v2;
        }
        int hash2 = hash2(hashCode);
        K k3 = kArr[hash2];
        if (k.equals(k3)) {
            V[] vArr2 = this.valueTable;
            V v3 = vArr2[hash2];
            vArr2[hash2] = v;
            return v3;
        }
        int hash3 = hash3(hashCode);
        K k4 = kArr[hash3];
        if (k.equals(k4)) {
            V[] vArr3 = this.valueTable;
            V v4 = vArr3[hash3];
            vArr3[hash3] = v;
            return v4;
        }
        int i2 = this.capacity;
        int i3 = this.stashSize + i2;
        while (i2 < i3) {
            if (k.equals(kArr[i2])) {
                V[] vArr4 = this.valueTable;
                V v5 = vArr4[i2];
                vArr4[i2] = v;
                return v5;
            }
            i2++;
        }
        if (k2 == null) {
            kArr[i] = k;
            this.valueTable[i] = v;
            int i4 = this.size;
            this.size = i4 + 1;
            if (i4 >= this.threshold) {
                resize(this.capacity << 1);
            }
            return null;
        } else if (k3 == null) {
            kArr[hash2] = k;
            this.valueTable[hash2] = v;
            int i5 = this.size;
            this.size = i5 + 1;
            if (i5 >= this.threshold) {
                resize(this.capacity << 1);
            }
            return null;
        } else if (k4 == null) {
            kArr[hash3] = k;
            this.valueTable[hash3] = v;
            int i6 = this.size;
            this.size = i6 + 1;
            if (i6 >= this.threshold) {
                resize(this.capacity << 1);
            }
            return null;
        } else {
            push(k, v, i, k2, hash2, k3, hash3, k4);
            return null;
        }
    }

    private void resize(int i) {
        int i2 = this.capacity + this.stashSize;
        this.capacity = i;
        this.threshold = (int) (i * this.loadFactor);
        this.mask = i - 1;
        this.hashShift = 31 - Integer.numberOfTrailingZeros(i);
        double d = i;
        this.stashCapacity = Math.max(3, ((int) Math.ceil(Math.log(d))) * 2);
        this.pushIterations = Math.max(Math.min(i, 8), ((int) Math.sqrt(d)) / 8);
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i3 = this.stashCapacity;
        this.keyTable = (K[]) new Object[i + i3];
        this.valueTable = (V[]) new Object[i + i3];
        int i4 = this.size;
        this.size = 0;
        this.stashSize = 0;
        if (i4 > 0) {
            for (int i5 = 0; i5 < i2; i5++) {
                K k = kArr[i5];
                if (k != null) {
                    putResize(k, vArr[i5]);
                }
            }
        }
    }

    public void clear(int i) {
        if (this.capacity <= i) {
            clear();
            return;
        }
        this.size = 0;
        resize(i);
    }

    public boolean containsKey(K k) {
        int hashCode = k.hashCode();
        if (!k.equals(this.keyTable[this.mask & hashCode])) {
            if (k.equals(this.keyTable[hash2(hashCode)])) {
                return true;
            }
            if (k.equals(this.keyTable[hash3(hashCode)])) {
                return true;
            }
            return containsKeyStash(k);
        }
        return true;
    }

    public boolean containsValue(Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            K[] kArr = this.keyTable;
            int i = this.capacity + this.stashSize;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    return false;
                }
                if (kArr[i2] != null && vArr[i2] == null) {
                    return true;
                }
                i = i2;
            }
        } else if (z) {
            int i3 = this.capacity + this.stashSize;
            while (true) {
                int i4 = i3 - 1;
                if (i3 <= 0) {
                    return false;
                }
                if (vArr[i4] == obj) {
                    return true;
                }
                i3 = i4;
            }
        } else {
            int i5 = this.capacity + this.stashSize;
            while (true) {
                int i6 = i5 - 1;
                if (i5 <= 0) {
                    return false;
                }
                if (obj.equals(vArr[i6])) {
                    return true;
                }
                i5 = i6;
            }
        }
    }

    public void ensureCapacity(int i) {
        int i2 = this.size + i;
        if (i2 >= this.threshold) {
            resize(nextPowerOfTwo((int) (i2 / this.loadFactor)));
        }
    }

    public Entries<K, V> entries() {
        return new Entries<>(this);
    }

    public K findKey(Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            K[] kArr = this.keyTable;
            int i = this.capacity + this.stashSize;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    return null;
                }
                if (kArr[i2] != null && vArr[i2] == null) {
                    return kArr[i2];
                }
                i = i2;
            }
        } else if (z) {
            int i3 = this.capacity + this.stashSize;
            while (true) {
                int i4 = i3 - 1;
                if (i3 <= 0) {
                    return null;
                }
                if (vArr[i4] == obj) {
                    return this.keyTable[i4];
                }
                i3 = i4;
            }
        } else {
            int i5 = this.capacity + this.stashSize;
            while (true) {
                int i6 = i5 - 1;
                if (i5 <= 0) {
                    return null;
                }
                if (obj.equals(vArr[i6])) {
                    return this.keyTable[i6];
                }
                i5 = i6;
            }
        }
    }

    public V get(K k) {
        int hashCode = k.hashCode();
        int i = this.mask & hashCode;
        if (!k.equals(this.keyTable[i])) {
            i = hash2(hashCode);
            if (!k.equals(this.keyTable[i])) {
                i = hash3(hashCode);
                if (!k.equals(this.keyTable[i])) {
                    return getStash(k);
                }
            }
        }
        return this.valueTable[i];
    }

    public Keys<K> keys() {
        return new Keys<>(this);
    }

    public V put(K k, V v) {
        if (k != null) {
            return put_internal(k, v);
        }
        throw new IllegalArgumentException("key cannot be null.");
    }

    public void putAll(ObjectMap<K, V> objectMap) {
        ensureCapacity(objectMap.size);
        Iterator<Entry<K, V>> it2 = objectMap.entries().iterator();
        while (it2.hasNext()) {
            Entry<K, V> next = it2.next();
            put(next.key, next.value);
        }
    }

    public V remove(K k) {
        int hashCode = k.hashCode();
        int i = this.mask & hashCode;
        if (k.equals(this.keyTable[i])) {
            this.keyTable[i] = null;
            V[] vArr = this.valueTable;
            V v = vArr[i];
            vArr[i] = null;
            this.size--;
            return v;
        }
        int hash2 = hash2(hashCode);
        if (k.equals(this.keyTable[hash2])) {
            this.keyTable[hash2] = null;
            V[] vArr2 = this.valueTable;
            V v2 = vArr2[hash2];
            vArr2[hash2] = null;
            this.size--;
            return v2;
        }
        int hash3 = hash3(hashCode);
        if (k.equals(this.keyTable[hash3])) {
            this.keyTable[hash3] = null;
            V[] vArr3 = this.valueTable;
            V v3 = vArr3[hash3];
            vArr3[hash3] = null;
            this.size--;
            return v3;
        }
        return removeStash(k);
    }

    V removeStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (k.equals(kArr[i])) {
                V v = this.valueTable[i];
                removeStashIndex(i);
                this.size--;
                return v;
            }
            i++;
        }
        return null;
    }

    void removeStashIndex(int i) {
        this.stashSize--;
        int i2 = this.capacity + this.stashSize;
        if (i < i2) {
            K[] kArr = this.keyTable;
            kArr[i] = kArr[i2];
            V[] vArr = this.valueTable;
            vArr[i] = vArr[i2];
            vArr[i2] = null;
            return;
        }
        this.valueTable[i] = null;
    }

    public void shrink(int i) {
        if (i >= 0) {
            int i2 = this.size;
            if (i2 > i) {
                i = i2;
            }
            if (this.capacity <= i) {
                return;
            }
            resize(nextPowerOfTwo(i));
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("maximumCapacity must be >= 0: ", i));
    }

    public String toString() {
        int i;
        if (this.size == 0) {
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int length = kArr.length;
        while (true) {
            i = length - 1;
            if (length > 0) {
                K k = kArr[i];
                if (k != null) {
                    sb.append(k);
                    sb.append(Chars.EQ);
                    sb.append(vArr[i]);
                    break;
                }
                length = i;
            } else {
                break;
            }
        }
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                K k2 = kArr[i2];
                if (k2 != null) {
                    sb.append(", ");
                    sb.append(k2);
                    sb.append(Chars.EQ);
                    sb.append(vArr[i2]);
                }
                i = i2;
            } else {
                sb.append(JsonReaderKt.END_OBJ);
                return sb.toString();
            }
        }
    }

    public Values<V> values() {
        return new Values<>(this);
    }

    public ObjectMap(int i) {
        this(i, 0.8f);
    }

    /* loaded from: classes2.dex */
    public static class Values<V> extends MapIterator<Object, V> implements Iterable<V>, Iterator<V> {
        public Values(ObjectMap<?, V> objectMap) {
            super(objectMap);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.lang.Iterable
        public Iterator<V> iterator() {
            return this;
        }

        @Override // java.util.Iterator
        public V next() {
            if (this.hasNext) {
                V[] vArr = this.map.valueTable;
                int i = this.nextIndex;
                V v = vArr[i];
                this.currentIndex = i;
                advance();
                return v;
            }
            throw new NoSuchElementException();
        }

        @Override // com.esotericsoftware.kryo.util.ObjectMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.ObjectMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public ArrayList<V> toArray() {
            ArrayList<V> arrayList = new ArrayList<>(this.map.size);
            while (this.hasNext) {
                arrayList.add(next());
            }
            return arrayList;
        }

        public void toArray(ArrayList<V> arrayList) {
            while (this.hasNext) {
                arrayList.add(next());
            }
        }
    }

    public ObjectMap(int i, float f) {
        if (i >= 0) {
            if (i <= 1073741824) {
                this.capacity = nextPowerOfTwo(i);
                if (f > 0.0f) {
                    this.loadFactor = f;
                    int i2 = this.capacity;
                    this.threshold = (int) (i2 * f);
                    this.mask = i2 - 1;
                    this.hashShift = 31 - Integer.numberOfTrailingZeros(i2);
                    this.stashCapacity = Math.max(3, ((int) Math.ceil(Math.log(this.capacity))) * 2);
                    this.pushIterations = Math.max(Math.min(this.capacity, 8), ((int) Math.sqrt(this.capacity)) / 8);
                    this.keyTable = (K[]) new Object[this.capacity + this.stashCapacity];
                    this.valueTable = (V[]) new Object[this.keyTable.length];
                    return;
                }
                throw new IllegalArgumentException("loadFactor must be > 0: " + f);
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("initialCapacity is too large: ", i));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("initialCapacity must be >= 0: ", i));
    }

    private V getStash(K k, V v) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (k.equals(kArr[i])) {
                return this.valueTable[i];
            }
            i++;
        }
        return v;
    }

    public void clear() {
        K[] kArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i = this.capacity + this.stashSize;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                kArr[i2] = null;
                vArr[i2] = null;
                i = i2;
            } else {
                this.size = 0;
                this.stashSize = 0;
                return;
            }
        }
    }

    public V get(K k, V v) {
        int hashCode = k.hashCode();
        int i = this.mask & hashCode;
        if (!k.equals(this.keyTable[i])) {
            i = hash2(hashCode);
            if (!k.equals(this.keyTable[i])) {
                i = hash3(hashCode);
                if (!k.equals(this.keyTable[i])) {
                    return getStash(k, v);
                }
            }
        }
        return this.valueTable[i];
    }

    public ObjectMap(ObjectMap<? extends K, ? extends V> objectMap) {
        this(objectMap.capacity, objectMap.loadFactor);
        this.stashSize = objectMap.stashSize;
        Object[] objArr = objectMap.keyTable;
        System.arraycopy(objArr, 0, this.keyTable, 0, objArr.length);
        Object[] objArr2 = objectMap.valueTable;
        System.arraycopy(objArr2, 0, this.valueTable, 0, objArr2.length);
        this.size = objectMap.size;
    }
}

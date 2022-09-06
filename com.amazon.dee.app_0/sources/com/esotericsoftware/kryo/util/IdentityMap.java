package com.esotericsoftware.kryo.util;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class IdentityMap<K, V> {
    private static final int PRIME1 = -1105259343;
    private static final int PRIME2 = -1262997959;
    private static final int PRIME3 = -825114047;
    int capacity;
    private Entries entries;
    private int hashShift;
    K[] keyTable;
    private Keys keys;
    private float loadFactor;
    private int mask;
    private int pushIterations;
    public int size;
    private int stashCapacity;
    int stashSize;
    private int threshold;
    V[] valueTable;
    private Values values;

    /* loaded from: classes2.dex */
    public static class Entries<K, V> extends MapIterator<K, V> implements Iterable<Entry<K, V>>, Iterator<Entry<K, V>> {
        private Entry<K, V> entry;

        public Entries(IdentityMap<K, V> identityMap) {
            super(identityMap);
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

        @Override // com.esotericsoftware.kryo.util.IdentityMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.IdentityMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        @Override // java.util.Iterator
        /* renamed from: next */
        public Entry<K, V> mo6849next() {
            if (this.hasNext) {
                IdentityMap<K, V> identityMap = this.map;
                K[] kArr = identityMap.keyTable;
                Entry<K, V> entry = this.entry;
                int i = this.nextIndex;
                entry.key = kArr[i];
                entry.value = identityMap.valueTable[i];
                this.currentIndex = i;
                findNextIndex();
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
        public Keys(IdentityMap<K, ?> identityMap) {
            super(identityMap);
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
            K[] kArr = this.map.keyTable;
            int i = this.nextIndex;
            K k = kArr[i];
            this.currentIndex = i;
            findNextIndex();
            return k;
        }

        @Override // com.esotericsoftware.kryo.util.IdentityMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.IdentityMap.MapIterator
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
        final IdentityMap<K, V> map;
        int nextIndex;

        public MapIterator(IdentityMap<K, V> identityMap) {
            this.map = identityMap;
            reset();
        }

        void findNextIndex() {
            this.hasNext = false;
            IdentityMap<K, V> identityMap = this.map;
            K[] kArr = identityMap.keyTable;
            int i = identityMap.capacity + identityMap.stashSize;
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
                IdentityMap<K, V> identityMap = this.map;
                if (i >= identityMap.capacity) {
                    identityMap.removeStashIndex(i);
                    this.nextIndex = this.currentIndex - 1;
                    findNextIndex();
                } else {
                    identityMap.keyTable[i] = null;
                    identityMap.valueTable[i] = null;
                }
                this.currentIndex = -1;
                IdentityMap<K, V> identityMap2 = this.map;
                identityMap2.size--;
                return;
            }
            throw new IllegalStateException("next must be called before remove.");
        }

        public void reset() {
            this.currentIndex = -1;
            this.nextIndex = -1;
            findNextIndex();
        }
    }

    public IdentityMap() {
        this(32, 0.8f);
    }

    private boolean containsKeyStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (kArr[i] == k) {
                return true;
            }
            i++;
        }
        return false;
    }

    private V getStash(K k, V v) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (kArr[i] == k) {
                return this.valueTable[i];
            }
            i++;
        }
        return v;
    }

    private int hash2(int i) {
        int i2 = i * PRIME2;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    private int hash3(int i) {
        int i2 = i * PRIME3;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
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
            int nextInt = ObjectMap.random.nextInt(3);
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
            int identityHashCode = System.identityHashCode(k5);
            i6 = identityHashCode & i4;
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
            i7 = hash2(identityHashCode);
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
            i8 = hash3(identityHashCode);
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
        int identityHashCode = System.identityHashCode(k);
        int i = identityHashCode & this.mask;
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
        int hash2 = hash2(identityHashCode);
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
        int hash3 = hash3(identityHashCode);
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
            put(k, v);
            return;
        }
        int i2 = this.capacity + i;
        this.keyTable[i2] = k;
        this.valueTable[i2] = v;
        this.stashSize = i + 1;
        this.size++;
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
        int identityHashCode = System.identityHashCode(k);
        if (k != this.keyTable[this.mask & identityHashCode]) {
            if (k == this.keyTable[hash2(identityHashCode)]) {
                return true;
            }
            if (k == this.keyTable[hash3(identityHashCode)]) {
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
            resize(ObjectMap.nextPowerOfTwo((int) (i2 / this.loadFactor)));
        }
    }

    public Entries<K, V> entries() {
        Entries entries = this.entries;
        if (entries == null) {
            this.entries = new Entries(this);
        } else {
            entries.reset();
        }
        return this.entries;
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
        int identityHashCode = System.identityHashCode(k);
        int i = this.mask & identityHashCode;
        if (k != this.keyTable[i]) {
            i = hash2(identityHashCode);
            if (k != this.keyTable[i]) {
                i = hash3(identityHashCode);
                if (k != this.keyTable[i]) {
                    return getStash(k, null);
                }
            }
        }
        return this.valueTable[i];
    }

    public Keys<K> keys() {
        Keys keys = this.keys;
        if (keys == null) {
            this.keys = new Keys(this);
        } else {
            keys.reset();
        }
        return this.keys;
    }

    public V put(K k, V v) {
        if (k != null) {
            K[] kArr = this.keyTable;
            int identityHashCode = System.identityHashCode(k);
            int i = identityHashCode & this.mask;
            K k2 = kArr[i];
            if (k2 == k) {
                V[] vArr = this.valueTable;
                V v2 = vArr[i];
                vArr[i] = v;
                return v2;
            }
            int hash2 = hash2(identityHashCode);
            K k3 = kArr[hash2];
            if (k3 == k) {
                V[] vArr2 = this.valueTable;
                V v3 = vArr2[hash2];
                vArr2[hash2] = v;
                return v3;
            }
            int hash3 = hash3(identityHashCode);
            K k4 = kArr[hash3];
            if (k4 == k) {
                V[] vArr3 = this.valueTable;
                V v4 = vArr3[hash3];
                vArr3[hash3] = v;
                return v4;
            }
            int i2 = this.capacity;
            int i3 = this.stashSize + i2;
            while (i2 < i3) {
                if (kArr[i2] == k) {
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
        throw new IllegalArgumentException("key cannot be null.");
    }

    public V remove(K k) {
        int identityHashCode = System.identityHashCode(k);
        int i = this.mask & identityHashCode;
        K[] kArr = this.keyTable;
        if (kArr[i] == k) {
            kArr[i] = null;
            V[] vArr = this.valueTable;
            V v = vArr[i];
            vArr[i] = null;
            this.size--;
            return v;
        }
        int hash2 = hash2(identityHashCode);
        K[] kArr2 = this.keyTable;
        if (kArr2[hash2] == k) {
            kArr2[hash2] = null;
            V[] vArr2 = this.valueTable;
            V v2 = vArr2[hash2];
            vArr2[hash2] = null;
            this.size--;
            return v2;
        }
        int hash3 = hash3(identityHashCode);
        K[] kArr3 = this.keyTable;
        if (kArr3[hash3] == k) {
            kArr3[hash3] = null;
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
            if (kArr[i] == k) {
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
            resize(ObjectMap.nextPowerOfTwo(i));
            return;
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline49("maximumCapacity must be >= 0: ", i));
    }

    public String toString() {
        int i;
        if (this.size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(JsonReaderKt.BEGIN_LIST);
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
                sb.append(JsonReaderKt.END_LIST);
                return sb.toString();
            }
        }
    }

    public Values<V> values() {
        Values values = this.values;
        if (values == null) {
            this.values = new Values(this);
        } else {
            values.reset();
        }
        return this.values;
    }

    public IdentityMap(int i) {
        this(i, 0.8f);
    }

    /* loaded from: classes2.dex */
    public static class Values<V> extends MapIterator<Object, V> implements Iterable<V>, Iterator<V> {
        public Values(IdentityMap<?, V> identityMap) {
            super(identityMap);
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
            V[] vArr = this.map.valueTable;
            int i = this.nextIndex;
            V v = vArr[i];
            this.currentIndex = i;
            findNextIndex();
            return v;
        }

        @Override // com.esotericsoftware.kryo.util.IdentityMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.IdentityMap.MapIterator
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

    public IdentityMap(int i, float f) {
        if (i >= 0) {
            if (this.capacity <= 1073741824) {
                this.capacity = ObjectMap.nextPowerOfTwo(i);
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
        int identityHashCode = System.identityHashCode(k);
        int i = this.mask & identityHashCode;
        if (k != this.keyTable[i]) {
            i = hash2(identityHashCode);
            if (k != this.keyTable[i]) {
                i = hash3(identityHashCode);
                if (k != this.keyTable[i]) {
                    return getStash(k, v);
                }
            }
        }
        return this.valueTable[i];
    }
}

package com.esotericsoftware.kryo.util;

import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
/* loaded from: classes2.dex */
public class IntMap<V> {
    private static final int EMPTY = 0;
    private static final int PRIME1 = -1105259343;
    private static final int PRIME2 = -1262997959;
    private static final int PRIME3 = -825114047;
    int capacity;
    boolean hasZeroValue;
    private int hashShift;
    int[] keyTable;
    private float loadFactor;
    private int mask;
    private int pushIterations;
    public int size;
    private int stashCapacity;
    int stashSize;
    private int threshold;
    V[] valueTable;
    V zeroValue;

    /* loaded from: classes2.dex */
    public static class Entries<V> extends MapIterator<V> implements Iterable<Entry<V>>, Iterator<Entry<V>> {
        private Entry<V> entry;

        public Entries(IntMap intMap) {
            super(intMap);
            this.entry = new Entry<>();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.hasNext;
        }

        @Override // java.lang.Iterable
        public Iterator<Entry<V>> iterator() {
            return this;
        }

        @Override // com.esotericsoftware.kryo.util.IntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.IntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        @Override // java.util.Iterator
        /* renamed from: next */
        public Entry<V> mo6850next() {
            if (this.hasNext) {
                IntMap<V> intMap = this.map;
                int[] iArr = intMap.keyTable;
                int i = this.nextIndex;
                if (i == -1) {
                    Entry<V> entry = this.entry;
                    entry.key = 0;
                    entry.value = intMap.zeroValue;
                } else {
                    Entry<V> entry2 = this.entry;
                    entry2.key = iArr[i];
                    entry2.value = intMap.valueTable[i];
                }
                this.currentIndex = this.nextIndex;
                findNextIndex();
                return this.entry;
            }
            throw new NoSuchElementException();
        }
    }

    /* loaded from: classes2.dex */
    public static class Entry<V> {
        public int key;
        public V value;

        public String toString() {
            return this.key + Config.Compare.EQUAL_TO + this.value;
        }
    }

    /* loaded from: classes2.dex */
    public static class Keys extends MapIterator {
        public Keys(IntMap intMap) {
            super(intMap);
        }

        public int next() {
            if (this.hasNext) {
                int i = this.nextIndex;
                int i2 = i == -1 ? 0 : this.map.keyTable[i];
                this.currentIndex = this.nextIndex;
                findNextIndex();
                return i2;
            }
            throw new NoSuchElementException();
        }

        @Override // com.esotericsoftware.kryo.util.IntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.IntMap.MapIterator
        public /* bridge */ /* synthetic */ void reset() {
            super.reset();
        }

        public IntArray toArray() {
            IntArray intArray = new IntArray(true, this.map.size);
            while (this.hasNext) {
                intArray.add(next());
            }
            return intArray;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class MapIterator<V> {
        static final int INDEX_ILLEGAL = -2;
        static final int INDEX_ZERO = -1;
        int currentIndex;
        public boolean hasNext;
        final IntMap<V> map;
        int nextIndex;

        public MapIterator(IntMap<V> intMap) {
            this.map = intMap;
            reset();
        }

        void findNextIndex() {
            this.hasNext = false;
            IntMap<V> intMap = this.map;
            int[] iArr = intMap.keyTable;
            int i = intMap.capacity + intMap.stashSize;
            do {
                int i2 = this.nextIndex + 1;
                this.nextIndex = i2;
                if (i2 >= i) {
                    return;
                }
            } while (iArr[this.nextIndex] == 0);
            this.hasNext = true;
        }

        public void remove() {
            if (this.currentIndex == -1) {
                IntMap<V> intMap = this.map;
                if (intMap.hasZeroValue) {
                    intMap.zeroValue = null;
                    intMap.hasZeroValue = false;
                    this.currentIndex = -2;
                    IntMap<V> intMap2 = this.map;
                    intMap2.size--;
                    return;
                }
            }
            int i = this.currentIndex;
            if (i >= 0) {
                IntMap<V> intMap3 = this.map;
                if (i >= intMap3.capacity) {
                    intMap3.removeStashIndex(i);
                    this.nextIndex = this.currentIndex - 1;
                    findNextIndex();
                } else {
                    intMap3.keyTable[i] = 0;
                    intMap3.valueTable[i] = null;
                }
                this.currentIndex = -2;
                IntMap<V> intMap22 = this.map;
                intMap22.size--;
                return;
            }
            throw new IllegalStateException("next must be called before remove.");
        }

        public void reset() {
            this.currentIndex = -2;
            this.nextIndex = -1;
            if (this.map.hasZeroValue) {
                this.hasNext = true;
            } else {
                findNextIndex();
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Values<V> extends MapIterator<V> implements Iterable<V>, Iterator<V> {
        public Values(IntMap<V> intMap) {
            super(intMap);
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
            V v;
            if (this.hasNext) {
                int i = this.nextIndex;
                if (i == -1) {
                    v = this.map.zeroValue;
                } else {
                    v = this.map.valueTable[i];
                }
                this.currentIndex = this.nextIndex;
                findNextIndex();
                return v;
            }
            throw new NoSuchElementException();
        }

        @Override // com.esotericsoftware.kryo.util.IntMap.MapIterator, java.util.Iterator
        public /* bridge */ /* synthetic */ void remove() {
            super.remove();
        }

        @Override // com.esotericsoftware.kryo.util.IntMap.MapIterator
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
    }

    public IntMap() {
        this(32, 0.8f);
    }

    private boolean containsKeyStash(int i) {
        int[] iArr = this.keyTable;
        int i2 = this.capacity;
        int i3 = this.stashSize + i2;
        while (i2 < i3) {
            if (iArr[i2] == i) {
                return true;
            }
            i2++;
        }
        return false;
    }

    private V getStash(int i, V v) {
        int[] iArr = this.keyTable;
        int i2 = this.capacity;
        int i3 = this.stashSize + i2;
        while (i2 < i3) {
            if (iArr[i2] == i) {
                return this.valueTable[i2];
            }
            i2++;
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

    private void push(int i, V v, int i2, int i3, int i4, int i5, int i6, int i7) {
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i8 = this.mask;
        int i9 = this.pushIterations;
        int i10 = 0;
        do {
            int nextInt = ObjectMap.random.nextInt(3);
            if (nextInt == 0) {
                V v2 = vArr[i2];
                iArr[i2] = i;
                vArr[i2] = v;
                i = i3;
                v = v2;
            } else if (nextInt != 1) {
                V v3 = vArr[i6];
                iArr[i6] = i;
                vArr[i6] = v;
                v = v3;
                i = i7;
            } else {
                V v4 = vArr[i4];
                iArr[i4] = i;
                vArr[i4] = v;
                v = v4;
                i = i5;
            }
            i2 = i & i8;
            i3 = iArr[i2];
            if (i3 == 0) {
                iArr[i2] = i;
                vArr[i2] = v;
                int i11 = this.size;
                this.size = i11 + 1;
                if (i11 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i4 = hash2(i);
            i5 = iArr[i4];
            if (i5 == 0) {
                iArr[i4] = i;
                vArr[i4] = v;
                int i12 = this.size;
                this.size = i12 + 1;
                if (i12 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i6 = hash3(i);
            i7 = iArr[i6];
            if (i7 == 0) {
                iArr[i6] = i;
                vArr[i6] = v;
                int i13 = this.size;
                this.size = i13 + 1;
                if (i13 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i10++;
        } while (i10 != i9);
        putStash(i, v);
    }

    private void putResize(int i, V v) {
        if (i == 0) {
            this.zeroValue = v;
            this.hasZeroValue = true;
            return;
        }
        int i2 = i & this.mask;
        int[] iArr = this.keyTable;
        int i3 = iArr[i2];
        if (i3 == 0) {
            iArr[i2] = i;
            this.valueTable[i2] = v;
            int i4 = this.size;
            this.size = i4 + 1;
            if (i4 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        int hash2 = hash2(i);
        int[] iArr2 = this.keyTable;
        int i5 = iArr2[hash2];
        if (i5 == 0) {
            iArr2[hash2] = i;
            this.valueTable[hash2] = v;
            int i6 = this.size;
            this.size = i6 + 1;
            if (i6 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        int hash3 = hash3(i);
        int[] iArr3 = this.keyTable;
        int i7 = iArr3[hash3];
        if (i7 == 0) {
            iArr3[hash3] = i;
            this.valueTable[hash3] = v;
            int i8 = this.size;
            this.size = i8 + 1;
            if (i8 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        push(i, v, i2, i3, hash2, i5, hash3, i7);
    }

    private void putStash(int i, V v) {
        int i2 = this.stashSize;
        if (i2 == this.stashCapacity) {
            resize(this.capacity << 1);
            put(i, v);
            return;
        }
        int i3 = this.capacity + i2;
        this.keyTable[i3] = i;
        this.valueTable[i3] = v;
        this.stashSize = i2 + 1;
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
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i3 = this.stashCapacity;
        this.keyTable = new int[i + i3];
        this.valueTable = (V[]) new Object[i + i3];
        int i4 = this.size;
        this.size = this.hasZeroValue ? 1 : 0;
        this.stashSize = 0;
        if (i4 > 0) {
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = iArr[i5];
                if (i6 != 0) {
                    putResize(i6, vArr[i5]);
                }
            }
        }
    }

    public void clear(int i) {
        if (this.capacity <= i) {
            clear();
            return;
        }
        this.zeroValue = null;
        this.hasZeroValue = false;
        this.size = 0;
        resize(i);
    }

    public boolean containsKey(int i) {
        if (i == 0) {
            return this.hasZeroValue;
        }
        if (this.keyTable[this.mask & i] == i) {
            return true;
        }
        if (this.keyTable[hash2(i)] == i) {
            return true;
        }
        if (this.keyTable[hash3(i)] == i) {
            return true;
        }
        return containsKeyStash(i);
    }

    public boolean containsValue(Object obj, boolean z) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            if (this.hasZeroValue && this.zeroValue == null) {
                return true;
            }
            int[] iArr = this.keyTable;
            int i = this.capacity + this.stashSize;
            while (true) {
                int i2 = i - 1;
                if (i <= 0) {
                    return false;
                }
                if (iArr[i2] != 0 && vArr[i2] == null) {
                    return true;
                }
                i = i2;
            }
        } else if (z) {
            if (obj == this.zeroValue) {
                return true;
            }
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
        } else if (this.hasZeroValue && obj.equals(this.zeroValue)) {
            return true;
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

    public Entries<V> entries() {
        return new Entries<>(this);
    }

    public int findKey(Object obj, boolean z, int i) {
        V[] vArr = this.valueTable;
        if (obj == null) {
            if (this.hasZeroValue && this.zeroValue == null) {
                return 0;
            }
            int[] iArr = this.keyTable;
            int i2 = this.capacity + this.stashSize;
            while (true) {
                int i3 = i2 - 1;
                if (i2 <= 0) {
                    break;
                } else if (iArr[i3] != 0 && vArr[i3] == null) {
                    return iArr[i3];
                } else {
                    i2 = i3;
                }
            }
        } else if (z) {
            if (obj != this.zeroValue) {
                int i4 = this.capacity + this.stashSize;
                while (true) {
                    int i5 = i4 - 1;
                    if (i4 <= 0) {
                        break;
                    } else if (vArr[i5] == obj) {
                        return this.keyTable[i5];
                    } else {
                        i4 = i5;
                    }
                }
            } else {
                return 0;
            }
        } else if (this.hasZeroValue && obj.equals(this.zeroValue)) {
            return 0;
        } else {
            int i6 = this.capacity + this.stashSize;
            while (true) {
                int i7 = i6 - 1;
                if (i6 <= 0) {
                    break;
                } else if (obj.equals(vArr[i7])) {
                    return this.keyTable[i7];
                } else {
                    i6 = i7;
                }
            }
        }
        return i;
    }

    public V get(int i) {
        if (i == 0) {
            if (this.hasZeroValue) {
                return this.zeroValue;
            }
            return null;
        }
        int i2 = this.mask & i;
        if (this.keyTable[i2] != i) {
            i2 = hash2(i);
            if (this.keyTable[i2] != i) {
                i2 = hash3(i);
                if (this.keyTable[i2] != i) {
                    return getStash(i, null);
                }
            }
        }
        return this.valueTable[i2];
    }

    public Keys keys() {
        return new Keys(this);
    }

    public V put(int i, V v) {
        if (i == 0) {
            V v2 = this.zeroValue;
            this.zeroValue = v;
            if (!this.hasZeroValue) {
                this.hasZeroValue = true;
                this.size++;
            }
            return v2;
        }
        int[] iArr = this.keyTable;
        int i2 = i & this.mask;
        int i3 = iArr[i2];
        if (i3 == i) {
            V[] vArr = this.valueTable;
            V v3 = vArr[i2];
            vArr[i2] = v;
            return v3;
        }
        int hash2 = hash2(i);
        int i4 = iArr[hash2];
        if (i4 == i) {
            V[] vArr2 = this.valueTable;
            V v4 = vArr2[hash2];
            vArr2[hash2] = v;
            return v4;
        }
        int hash3 = hash3(i);
        int i5 = iArr[hash3];
        if (i5 == i) {
            V[] vArr3 = this.valueTable;
            V v5 = vArr3[hash3];
            vArr3[hash3] = v;
            return v5;
        }
        int i6 = this.capacity;
        int i7 = this.stashSize + i6;
        while (i6 < i7) {
            if (iArr[i6] == i) {
                V[] vArr4 = this.valueTable;
                V v6 = vArr4[i6];
                vArr4[i6] = v;
                return v6;
            }
            i6++;
        }
        if (i3 == 0) {
            iArr[i2] = i;
            this.valueTable[i2] = v;
            int i8 = this.size;
            this.size = i8 + 1;
            if (i8 >= this.threshold) {
                resize(this.capacity << 1);
            }
            return null;
        } else if (i4 == 0) {
            iArr[hash2] = i;
            this.valueTable[hash2] = v;
            int i9 = this.size;
            this.size = i9 + 1;
            if (i9 >= this.threshold) {
                resize(this.capacity << 1);
            }
            return null;
        } else if (i5 == 0) {
            iArr[hash3] = i;
            this.valueTable[hash3] = v;
            int i10 = this.size;
            this.size = i10 + 1;
            if (i10 >= this.threshold) {
                resize(this.capacity << 1);
            }
            return null;
        } else {
            push(i, v, i2, i3, hash2, i4, hash3, i5);
            return null;
        }
    }

    public void putAll(IntMap<V> intMap) {
        Iterator<Entry<V>> it2 = intMap.entries().iterator();
        while (it2.hasNext()) {
            Entry<V> next = it2.next();
            put(next.key, next.value);
        }
    }

    public V remove(int i) {
        if (i == 0) {
            if (!this.hasZeroValue) {
                return null;
            }
            V v = this.zeroValue;
            this.zeroValue = null;
            this.hasZeroValue = false;
            this.size--;
            return v;
        }
        int i2 = this.mask & i;
        int[] iArr = this.keyTable;
        if (iArr[i2] == i) {
            iArr[i2] = 0;
            V[] vArr = this.valueTable;
            V v2 = vArr[i2];
            vArr[i2] = null;
            this.size--;
            return v2;
        }
        int hash2 = hash2(i);
        int[] iArr2 = this.keyTable;
        if (iArr2[hash2] == i) {
            iArr2[hash2] = 0;
            V[] vArr2 = this.valueTable;
            V v3 = vArr2[hash2];
            vArr2[hash2] = null;
            this.size--;
            return v3;
        }
        int hash3 = hash3(i);
        int[] iArr3 = this.keyTable;
        if (iArr3[hash3] == i) {
            iArr3[hash3] = 0;
            V[] vArr3 = this.valueTable;
            V v4 = vArr3[hash3];
            vArr3[hash3] = null;
            this.size--;
            return v4;
        }
        return removeStash(i);
    }

    V removeStash(int i) {
        int[] iArr = this.keyTable;
        int i2 = this.capacity;
        int i3 = this.stashSize + i2;
        while (i2 < i3) {
            if (iArr[i2] == i) {
                V v = this.valueTable[i2];
                removeStashIndex(i2);
                this.size--;
                return v;
            }
            i2++;
        }
        return null;
    }

    void removeStashIndex(int i) {
        this.stashSize--;
        int i2 = this.capacity + this.stashSize;
        if (i < i2) {
            int[] iArr = this.keyTable;
            iArr[i] = iArr[i2];
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

    /* JADX WARN: Removed duplicated region for block: B:18:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0059  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x003e -> B:16:0x003f). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String toString() {
        /*
            r7 = this;
            int r0 = r7.size
            if (r0 != 0) goto L7
            java.lang.String r0 = "[]"
            return r0
        L7:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r1 = 32
            r0.<init>(r1)
            r1 = 91
            r0.append(r1)
            int[] r1 = r7.keyTable
            V[] r2 = r7.valueTable
            int r3 = r1.length
            boolean r4 = r7.hasZeroValue
            r5 = 61
            if (r4 == 0) goto L29
            java.lang.String r4 = "0="
            r0.append(r4)
            V r4 = r7.zeroValue
            r0.append(r4)
            goto L3f
        L29:
            int r4 = r3 + (-1)
            if (r3 <= 0) goto L3e
            r3 = r1[r4]
            if (r3 != 0) goto L33
            r3 = r4
            goto L29
        L33:
            r0.append(r3)
            r0.append(r5)
            r3 = r2[r4]
            r0.append(r3)
        L3e:
            r3 = r4
        L3f:
            int r4 = r3 + (-1)
            if (r3 <= 0) goto L59
            r3 = r1[r4]
            if (r3 != 0) goto L48
            goto L3e
        L48:
            java.lang.String r6 = ", "
            r0.append(r6)
            r0.append(r3)
            r0.append(r5)
            r3 = r2[r4]
            r0.append(r3)
            goto L3e
        L59:
            r1 = 93
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.esotericsoftware.kryo.util.IntMap.toString():java.lang.String");
    }

    public Values<V> values() {
        return new Values<>(this);
    }

    public IntMap(int i) {
        this(i, 0.8f);
    }

    public IntMap(int i, float f) {
        if (i >= 0) {
            if (i <= 1073741824) {
                this.capacity = ObjectMap.nextPowerOfTwo(i);
                if (f > 0.0f) {
                    this.loadFactor = f;
                    int i2 = this.capacity;
                    this.threshold = (int) (i2 * f);
                    this.mask = i2 - 1;
                    this.hashShift = 31 - Integer.numberOfTrailingZeros(i2);
                    this.stashCapacity = Math.max(3, ((int) Math.ceil(Math.log(this.capacity))) * 2);
                    this.pushIterations = Math.max(Math.min(this.capacity, 8), ((int) Math.sqrt(this.capacity)) / 8);
                    this.keyTable = new int[this.capacity + this.stashCapacity];
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
        int[] iArr = this.keyTable;
        V[] vArr = this.valueTable;
        int i = this.capacity + this.stashSize;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                iArr[i2] = 0;
                vArr[i2] = null;
                i = i2;
            } else {
                this.size = 0;
                this.stashSize = 0;
                this.zeroValue = null;
                this.hasZeroValue = false;
                return;
            }
        }
    }

    public V get(int i, V v) {
        if (i == 0) {
            return !this.hasZeroValue ? v : this.zeroValue;
        }
        int i2 = this.mask & i;
        if (this.keyTable[i2] != i) {
            i2 = hash2(i);
            if (this.keyTable[i2] != i) {
                i2 = hash3(i);
                if (this.keyTable[i2] != i) {
                    return getStash(i, v);
                }
            }
        }
        return this.valueTable[i2];
    }

    public IntMap(IntMap<? extends V> intMap) {
        this(intMap.capacity, intMap.loadFactor);
        this.stashSize = intMap.stashSize;
        int[] iArr = intMap.keyTable;
        System.arraycopy(iArr, 0, this.keyTable, 0, iArr.length);
        Object[] objArr = intMap.valueTable;
        System.arraycopy(objArr, 0, this.valueTable, 0, objArr.length);
        this.size = intMap.size;
        this.zeroValue = intMap.zeroValue;
        this.hasZeroValue = intMap.hasZeroValue;
    }
}

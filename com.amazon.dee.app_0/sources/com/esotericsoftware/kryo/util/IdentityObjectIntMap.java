package com.esotericsoftware.kryo.util;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes2.dex */
public class IdentityObjectIntMap<K> {
    private static final int PRIME1 = -1105259343;
    private static final int PRIME2 = -1262997959;
    private static final int PRIME3 = -825114047;
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
    int[] valueTable;

    public IdentityObjectIntMap() {
        this(32, 0.8f);
    }

    private boolean containsKeyStash(K k) {
        K[] kArr = this.keyTable;
        int i = this.capacity;
        int i2 = this.stashSize + i;
        while (i < i2) {
            if (k == kArr[i]) {
                return true;
            }
            i++;
        }
        return false;
    }

    private int getAndIncrementStash(K k, int i, int i2) {
        K[] kArr = this.keyTable;
        int i3 = this.capacity;
        int i4 = this.stashSize + i3;
        while (i3 < i4) {
            if (k == kArr[i3]) {
                int[] iArr = this.valueTable;
                int i5 = iArr[i3];
                iArr[i3] = i2 + i5;
                return i5;
            }
            i3++;
        }
        put(k, i2 + i);
        return i;
    }

    private int getStash(K k, int i) {
        K[] kArr = this.keyTable;
        int i2 = this.capacity;
        int i3 = this.stashSize + i2;
        while (i2 < i3) {
            if (k == kArr[i2]) {
                return this.valueTable[i2];
            }
            i2++;
        }
        return i;
    }

    private int hash2(int i) {
        int i2 = i * PRIME2;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    private int hash3(int i) {
        int i2 = i * PRIME3;
        return (i2 ^ (i2 >>> this.hashShift)) & this.mask;
    }

    private void push(K k, int i, int i2, K k2, int i3, K k3, int i4, K k4) {
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int i5 = this.mask;
        int i6 = this.pushIterations;
        K k5 = k;
        int i7 = i;
        int i8 = i2;
        K k6 = k2;
        int i9 = i3;
        K k7 = k3;
        int i10 = i4;
        int i11 = 0;
        K k8 = k4;
        do {
            int nextInt = ObjectMap.random.nextInt(3);
            if (nextInt == 0) {
                int i12 = iArr[i8];
                kArr[i8] = k5;
                iArr[i8] = i7;
                i7 = i12;
                k5 = k6;
            } else if (nextInt != 1) {
                int i13 = iArr[i10];
                kArr[i10] = k5;
                iArr[i10] = i7;
                k5 = k8;
                i7 = i13;
            } else {
                int i14 = iArr[i9];
                kArr[i9] = k5;
                iArr[i9] = i7;
                i7 = i14;
                k5 = k7;
            }
            int identityHashCode = System.identityHashCode(k5);
            i8 = identityHashCode & i5;
            k6 = kArr[i8];
            if (k6 == null) {
                kArr[i8] = k5;
                iArr[i8] = i7;
                int i15 = this.size;
                this.size = i15 + 1;
                if (i15 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i9 = hash2(identityHashCode);
            k7 = kArr[i9];
            if (k7 == null) {
                kArr[i9] = k5;
                iArr[i9] = i7;
                int i16 = this.size;
                this.size = i16 + 1;
                if (i16 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i10 = hash3(identityHashCode);
            k8 = kArr[i10];
            if (k8 == null) {
                kArr[i10] = k5;
                iArr[i10] = i7;
                int i17 = this.size;
                this.size = i17 + 1;
                if (i17 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            }
            i11++;
        } while (i11 != i6);
        putStash(k5, i7);
    }

    private void putResize(K k, int i) {
        int identityHashCode = System.identityHashCode(k);
        int i2 = identityHashCode & this.mask;
        K[] kArr = this.keyTable;
        K k2 = kArr[i2];
        if (k2 == null) {
            kArr[i2] = k;
            this.valueTable[i2] = i;
            int i3 = this.size;
            this.size = i3 + 1;
            if (i3 < this.threshold) {
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
            this.valueTable[hash2] = i;
            int i4 = this.size;
            this.size = i4 + 1;
            if (i4 < this.threshold) {
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
            this.valueTable[hash3] = i;
            int i5 = this.size;
            this.size = i5 + 1;
            if (i5 < this.threshold) {
                return;
            }
            resize(this.capacity << 1);
            return;
        }
        push(k, i, i2, k2, hash2, k3, hash3, k4);
    }

    private void putStash(K k, int i) {
        int i2 = this.stashSize;
        if (i2 == this.stashCapacity) {
            resize(this.capacity << 1);
            put(k, i);
            return;
        }
        int i3 = this.capacity + i2;
        this.keyTable[i3] = k;
        this.valueTable[i3] = i;
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
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int i3 = this.stashCapacity;
        this.keyTable = (K[]) new Object[i + i3];
        this.valueTable = new int[i + i3];
        int i4 = this.size;
        this.size = 0;
        this.stashSize = 0;
        if (i4 > 0) {
            for (int i5 = 0; i5 < i2; i5++) {
                K k = kArr[i5];
                if (k != null) {
                    putResize(k, iArr[i5]);
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

    public boolean containsValue(int i) {
        int[] iArr = this.valueTable;
        int i2 = this.capacity + this.stashSize;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                if (iArr[i3] == i) {
                    return true;
                }
                i2 = i3;
            } else {
                return false;
            }
        }
    }

    public void ensureCapacity(int i) {
        int i2 = this.size + i;
        if (i2 >= this.threshold) {
            resize(ObjectMap.nextPowerOfTwo((int) (i2 / this.loadFactor)));
        }
    }

    public K findKey(int i) {
        int[] iArr = this.valueTable;
        int i2 = this.capacity + this.stashSize;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                if (iArr[i3] == i) {
                    return this.keyTable[i3];
                }
                i2 = i3;
            } else {
                return null;
            }
        }
    }

    public int get(K k, int i) {
        int identityHashCode = System.identityHashCode(k);
        int i2 = this.mask & identityHashCode;
        if (k != this.keyTable[i2]) {
            i2 = hash2(identityHashCode);
            if (k != this.keyTable[i2]) {
                i2 = hash3(identityHashCode);
                if (k != this.keyTable[i2]) {
                    return getStash(k, i);
                }
            }
        }
        return this.valueTable[i2];
    }

    public int getAndIncrement(K k, int i, int i2) {
        int identityHashCode = System.identityHashCode(k);
        int i3 = this.mask & identityHashCode;
        if (k != this.keyTable[i3]) {
            i3 = hash2(identityHashCode);
            if (k != this.keyTable[i3]) {
                i3 = hash3(identityHashCode);
                if (k != this.keyTable[i3]) {
                    return getAndIncrementStash(k, i, i2);
                }
            }
        }
        int[] iArr = this.valueTable;
        int i4 = iArr[i3];
        iArr[i3] = i2 + i4;
        return i4;
    }

    public void put(K k, int i) {
        if (k != null) {
            K[] kArr = this.keyTable;
            int identityHashCode = System.identityHashCode(k);
            int i2 = identityHashCode & this.mask;
            K k2 = kArr[i2];
            if (k == k2) {
                this.valueTable[i2] = i;
                return;
            }
            int hash2 = hash2(identityHashCode);
            K k3 = kArr[hash2];
            if (k == k3) {
                this.valueTable[hash2] = i;
                return;
            }
            int hash3 = hash3(identityHashCode);
            K k4 = kArr[hash3];
            if (k == k4) {
                this.valueTable[hash3] = i;
                return;
            }
            int i3 = this.capacity;
            int i4 = this.stashSize + i3;
            while (i3 < i4) {
                if (kArr[i3] == k) {
                    this.valueTable[i3] = i;
                    return;
                }
                i3++;
            }
            if (k2 == null) {
                kArr[i2] = k;
                this.valueTable[i2] = i;
                int i5 = this.size;
                this.size = i5 + 1;
                if (i5 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            } else if (k3 == null) {
                kArr[hash2] = k;
                this.valueTable[hash2] = i;
                int i6 = this.size;
                this.size = i6 + 1;
                if (i6 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            } else if (k4 == null) {
                kArr[hash3] = k;
                this.valueTable[hash3] = i;
                int i7 = this.size;
                this.size = i7 + 1;
                if (i7 < this.threshold) {
                    return;
                }
                resize(this.capacity << 1);
                return;
            } else {
                push(k, i, i2, k2, hash2, k3, hash3, k4);
                return;
            }
        }
        throw new IllegalArgumentException("key cannot be null.");
    }

    public int remove(K k, int i) {
        int identityHashCode = System.identityHashCode(k);
        int i2 = this.mask & identityHashCode;
        K[] kArr = this.keyTable;
        if (k == kArr[i2]) {
            kArr[i2] = null;
            this.size--;
            return this.valueTable[i2];
        }
        int hash2 = hash2(identityHashCode);
        K[] kArr2 = this.keyTable;
        if (k == kArr2[hash2]) {
            kArr2[hash2] = null;
            this.size--;
            return this.valueTable[hash2];
        }
        int hash3 = hash3(identityHashCode);
        K[] kArr3 = this.keyTable;
        if (k == kArr3[hash3]) {
            kArr3[hash3] = null;
            this.size--;
            return this.valueTable[hash3];
        }
        return removeStash(k, i);
    }

    int removeStash(K k, int i) {
        K[] kArr = this.keyTable;
        int i2 = this.capacity;
        int i3 = this.stashSize + i2;
        while (i2 < i3) {
            if (k == kArr[i2]) {
                int i4 = this.valueTable[i2];
                removeStashIndex(i2);
                this.size--;
                return i4;
            }
            i2++;
        }
        return i;
    }

    void removeStashIndex(int i) {
        this.stashSize--;
        int i2 = this.capacity + this.stashSize;
        if (i < i2) {
            K[] kArr = this.keyTable;
            kArr[i] = kArr[i2];
            int[] iArr = this.valueTable;
            iArr[i] = iArr[i2];
        }
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
            return "{}";
        }
        StringBuilder sb = new StringBuilder(32);
        sb.append(JsonReaderKt.BEGIN_OBJ);
        K[] kArr = this.keyTable;
        int[] iArr = this.valueTable;
        int length = kArr.length;
        while (true) {
            i = length - 1;
            if (length > 0) {
                K k = kArr[i];
                if (k != null) {
                    sb.append(k);
                    sb.append(Chars.EQ);
                    sb.append(iArr[i]);
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
                    sb.append(iArr[i2]);
                }
                i = i2;
            } else {
                sb.append(JsonReaderKt.END_OBJ);
                return sb.toString();
            }
        }
    }

    public IdentityObjectIntMap(int i) {
        this(i, 0.8f);
    }

    public IdentityObjectIntMap(int i, float f) {
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
                    this.valueTable = new int[this.keyTable.length];
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
        int i = this.capacity + this.stashSize;
        while (true) {
            int i2 = i - 1;
            if (i > 0) {
                kArr[i2] = null;
                i = i2;
            } else {
                this.size = 0;
                this.stashSize = 0;
                return;
            }
        }
    }
}

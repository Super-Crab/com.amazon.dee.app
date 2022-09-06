package org.apache.oro.util;
/* loaded from: classes4.dex */
public final class CacheLRU extends GenericCache {
    private int __head;
    private int[] __next;
    private int[] __prev;
    private int __tail;

    public CacheLRU() {
        this(20);
    }

    public CacheLRU(int i) {
        super(i);
        int i2 = 0;
        this.__head = 0;
        this.__tail = 0;
        GenericCacheEntry[] genericCacheEntryArr = this._cache;
        this.__next = new int[genericCacheEntryArr.length];
        this.__prev = new int[genericCacheEntryArr.length];
        while (true) {
            int[] iArr = this.__next;
            if (i2 < iArr.length) {
                this.__prev[i2] = -1;
                iArr[i2] = -1;
                i2++;
            } else {
                return;
            }
        }
    }

    private void __moveToFront(int i) {
        if (this.__head != i) {
            int[] iArr = this.__next;
            int i2 = iArr[i];
            int[] iArr2 = this.__prev;
            int i3 = iArr2[i];
            iArr[i3] = i2;
            if (i2 >= 0) {
                iArr2[i2] = i3;
            } else {
                this.__tail = i3;
            }
            int[] iArr3 = this.__prev;
            iArr3[i] = -1;
            int[] iArr4 = this.__next;
            int i4 = this.__head;
            iArr4[i] = i4;
            iArr3[i4] = i;
            this.__head = i;
        }
    }

    @Override // org.apache.oro.util.GenericCache, org.apache.oro.util.Cache
    public final synchronized void addElement(Object obj, Object obj2) {
        Object obj3 = this._table.get(obj);
        if (obj3 != null) {
            GenericCacheEntry genericCacheEntry = (GenericCacheEntry) obj3;
            genericCacheEntry._value = obj2;
            genericCacheEntry._key = obj;
            __moveToFront(genericCacheEntry._index);
            return;
        }
        if (!isFull()) {
            if (this._numEntries > 0) {
                this.__prev[this._numEntries] = this.__tail;
                this.__next[this._numEntries] = -1;
                __moveToFront(this._numEntries);
            }
            this._numEntries++;
        } else {
            this._table.remove(this._cache[this.__tail]._key);
            __moveToFront(this.__tail);
        }
        this._cache[this.__head]._value = obj2;
        this._cache[this.__head]._key = obj;
        this._table.put(obj, this._cache[this.__head]);
    }

    @Override // org.apache.oro.util.GenericCache, org.apache.oro.util.Cache
    public synchronized Object getElement(Object obj) {
        Object obj2 = this._table.get(obj);
        if (obj2 != null) {
            GenericCacheEntry genericCacheEntry = (GenericCacheEntry) obj2;
            __moveToFront(genericCacheEntry._index);
            return genericCacheEntry._value;
        }
        return null;
    }
}

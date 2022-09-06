package org.apache.oro.util;
/* loaded from: classes4.dex */
public final class CacheFIFO extends GenericCache {
    private int __curent;

    public CacheFIFO() {
        this(20);
    }

    public CacheFIFO(int i) {
        super(i);
        this.__curent = 0;
    }

    @Override // org.apache.oro.util.GenericCache, org.apache.oro.util.Cache
    public final synchronized void addElement(Object obj, Object obj2) {
        int i;
        Object obj3 = this._table.get(obj);
        if (obj3 != null) {
            GenericCacheEntry genericCacheEntry = (GenericCacheEntry) obj3;
            genericCacheEntry._value = obj2;
            genericCacheEntry._key = obj;
            return;
        }
        if (!isFull()) {
            i = this._numEntries;
            this._numEntries++;
        } else {
            i = this.__curent;
            int i2 = this.__curent + 1;
            this.__curent = i2;
            if (i2 >= this._cache.length) {
                this.__curent = 0;
            }
            this._table.remove(this._cache[i]._key);
        }
        this._cache[i]._value = obj2;
        this._cache[i]._key = obj;
        this._table.put(obj, this._cache[i]);
    }
}

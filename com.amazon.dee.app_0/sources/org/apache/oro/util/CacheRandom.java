package org.apache.oro.util;

import java.util.Random;
/* loaded from: classes4.dex */
public final class CacheRandom extends GenericCache {
    private Random __random;

    public CacheRandom() {
        this(20);
    }

    public CacheRandom(int i) {
        super(i);
        this.__random = new Random(System.currentTimeMillis());
    }

    @Override // org.apache.oro.util.GenericCache, org.apache.oro.util.Cache
    public final synchronized void addElement(Object obj, Object obj2) {
        int length;
        Object obj3 = this._table.get(obj);
        if (obj3 != null) {
            GenericCacheEntry genericCacheEntry = (GenericCacheEntry) obj3;
            genericCacheEntry._value = obj2;
            genericCacheEntry._key = obj;
            return;
        }
        if (!isFull()) {
            length = this._numEntries;
            this._numEntries++;
        } else {
            length = (int) (this._cache.length * this.__random.nextFloat());
            this._table.remove(this._cache[length]._key);
        }
        this._cache[length]._value = obj2;
        this._cache[length]._key = obj;
        this._table.put(obj, this._cache[length]);
    }
}

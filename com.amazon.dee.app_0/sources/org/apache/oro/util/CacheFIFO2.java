package org.apache.oro.util;
/* loaded from: classes4.dex */
public final class CacheFIFO2 extends GenericCache {
    private int __current;
    private boolean[] __tryAgain;

    public CacheFIFO2() {
        this(20);
    }

    public CacheFIFO2(int i) {
        super(i);
        this.__current = 0;
        this.__tryAgain = new boolean[this._cache.length];
    }

    @Override // org.apache.oro.util.GenericCache, org.apache.oro.util.Cache
    public final synchronized void addElement(Object obj, Object obj2) {
        int i;
        Object obj3 = this._table.get(obj);
        if (obj3 != null) {
            GenericCacheEntry genericCacheEntry = (GenericCacheEntry) obj3;
            genericCacheEntry._value = obj2;
            genericCacheEntry._key = obj;
            this.__tryAgain[genericCacheEntry._index] = true;
            return;
        }
        if (!isFull()) {
            i = this._numEntries;
            this._numEntries++;
        } else {
            i = this.__current;
            while (this.__tryAgain[i]) {
                this.__tryAgain[i] = false;
                i++;
                if (i >= this.__tryAgain.length) {
                    i = 0;
                }
            }
            this.__current = i + 1;
            if (this.__current >= this._cache.length) {
                this.__current = 0;
            }
            this._table.remove(this._cache[i]._key);
        }
        this._cache[i]._value = obj2;
        this._cache[i]._key = obj;
        this._table.put(obj, this._cache[i]);
    }

    @Override // org.apache.oro.util.GenericCache, org.apache.oro.util.Cache
    public synchronized Object getElement(Object obj) {
        Object obj2 = this._table.get(obj);
        if (obj2 != null) {
            GenericCacheEntry genericCacheEntry = (GenericCacheEntry) obj2;
            this.__tryAgain[genericCacheEntry._index] = true;
            return genericCacheEntry._value;
        }
        return null;
    }
}

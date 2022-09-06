package org.apache.oro.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
/* loaded from: classes4.dex */
public abstract class GenericCache implements Cache, Serializable {
    public static final int DEFAULT_CAPACITY = 20;
    GenericCacheEntry[] _cache;
    int _numEntries = 0;
    HashMap _table;

    /* JADX INFO: Access modifiers changed from: package-private */
    public GenericCache(int i) {
        this._table = new HashMap(i);
        this._cache = new GenericCacheEntry[i];
        while (true) {
            i--;
            if (i >= 0) {
                this._cache[i] = new GenericCacheEntry(i);
            } else {
                return;
            }
        }
    }

    @Override // org.apache.oro.util.Cache
    public abstract void addElement(Object obj, Object obj2);

    @Override // org.apache.oro.util.Cache
    public final int capacity() {
        return this._cache.length;
    }

    @Override // org.apache.oro.util.Cache
    public synchronized Object getElement(Object obj) {
        Object obj2 = this._table.get(obj);
        if (obj2 != null) {
            return ((GenericCacheEntry) obj2)._value;
        }
        return null;
    }

    public final boolean isFull() {
        return this._numEntries >= this._cache.length;
    }

    public final Iterator keys() {
        return this._table.keySet().iterator();
    }

    @Override // org.apache.oro.util.Cache
    public final int size() {
        return this._numEntries;
    }
}

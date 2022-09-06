package com.amazon.alexa;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/* compiled from: CacheMap.java */
/* loaded from: classes.dex */
public final class IWd<K, V> {
    public final int zQM = 3;
    public final Map<K, V> BIo = new HashMap();
    public final Deque<K> zZm = new LinkedList();

    public synchronized V BIo(K k) {
        return this.BIo.get(k);
    }

    public synchronized boolean zZm(K k) {
        return this.BIo.containsKey(k);
    }

    public synchronized Map.Entry<K, V> BIo() {
        K peekLast = this.zZm.peekLast();
        for (Map.Entry<K, V> entry : this.BIo.entrySet()) {
            if (entry.getKey().equals(peekLast)) {
                return entry;
            }
        }
        return null;
    }

    public synchronized void zZm(K k, V v) {
        if (this.zZm.contains(k)) {
            this.zZm.remove(k);
        }
        this.zZm.add(k);
        this.BIo.put(k, v);
        if (this.BIo.size() > this.zQM) {
            this.BIo.remove(this.zZm.poll());
        }
    }

    public synchronized void zZm() {
        this.BIo.clear();
        this.zZm.clear();
    }
}

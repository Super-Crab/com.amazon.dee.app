package com.amazon.alexa;

import com.amazon.alexa.api.ExtendedClient;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* compiled from: ClientListenerContainer.java */
/* loaded from: classes.dex */
public class Shr<T> implements Iterable<T> {
    public final Object zZm = new Object();
    public final Map<ExtendedClient, Set<T>> BIo = new HashMap();

    public ExtendedClient BIo(T t) {
        synchronized (this.zZm) {
            for (Map.Entry<ExtendedClient, Set<T>> entry : this.BIo.entrySet()) {
                if (entry.getValue().contains(t)) {
                    return entry.getKey();
                }
            }
            return null;
        }
    }

    public void clear() {
        synchronized (this.zZm) {
            this.BIo.clear();
        }
    }

    public boolean isEmpty() {
        boolean z;
        synchronized (this.zZm) {
            z = size() == 0;
        }
        return z;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        Iterator<T> it2;
        synchronized (this.zZm) {
            it2 = zZm().iterator();
        }
        return it2;
    }

    public ExtendedClient remove(T t) {
        ExtendedClient extendedClient;
        Set<T> set;
        synchronized (this.zZm) {
            Iterator<Map.Entry<ExtendedClient, Set<T>>> it2 = this.BIo.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    extendedClient = null;
                    break;
                }
                Map.Entry<ExtendedClient, Set<T>> next = it2.next();
                if (next.getValue().contains(t)) {
                    extendedClient = next.getKey();
                    break;
                }
            }
            if (extendedClient != null) {
                if (this.BIo.get(extendedClient) == null) {
                    set = Collections.emptySet();
                } else {
                    set = this.BIo.get(extendedClient);
                }
                if (set.size() == 1) {
                    this.BIo.remove(extendedClient);
                } else {
                    set.remove(t);
                }
                return extendedClient;
            }
            return null;
        }
    }

    public int size() {
        int size;
        synchronized (this.zZm) {
            size = zZm().size();
        }
        return size;
    }

    public Set<T> zZm(ExtendedClient extendedClient) {
        synchronized (this.zZm) {
            if (this.BIo.get(extendedClient) == null) {
                return Collections.emptySet();
            }
            HashSet hashSet = new HashSet();
            hashSet.addAll(this.BIo.get(extendedClient));
            return hashSet;
        }
    }

    public Set<T> BIo(ExtendedClient extendedClient) {
        synchronized (this.zZm) {
            if (this.BIo.get(extendedClient) == null) {
                return Collections.emptySet();
            }
            return this.BIo.remove(extendedClient);
        }
    }

    public void zZm(ExtendedClient extendedClient, T t) {
        synchronized (this.zZm) {
            if (!this.BIo.containsKey(extendedClient)) {
                this.BIo.put(extendedClient, new HashSet());
            }
            this.BIo.get(extendedClient).add(t);
        }
    }

    public Set<T> zZm() {
        HashSet hashSet;
        synchronized (this.zZm) {
            hashSet = new HashSet();
            for (Set<T> set : this.BIo.values()) {
                hashSet.addAll(set);
            }
        }
        return hashSet;
    }

    public boolean zZm(T t) {
        synchronized (this.zZm) {
            for (Set<T> set : this.BIo.values()) {
                if (set.contains(t)) {
                    return true;
                }
            }
            return false;
        }
    }
}

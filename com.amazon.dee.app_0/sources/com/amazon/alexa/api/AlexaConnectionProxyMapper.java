package com.amazon.alexa.api;

import android.util.Log;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public class AlexaConnectionProxyMapper<K, V> {
    private static final String TAG = "AlexaConnectionProxyMapper";
    private final Map<K, V> mapping = new ConcurrentHashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.mapping.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean containsKey(K k) {
        return this.mapping.containsKey(k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Collection<Map.Entry<K, V>> entrySet() {
        return this.mapping.entrySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public V get(K k) {
        return this.mapping.get(k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Set<K> keySet() {
        return this.mapping.keySet();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final V put(K k, V v) {
        Preconditions.notNull(k, "key is null");
        Preconditions.notNull(v, "value is null");
        return this.mapping.put(k, v);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public V remove(K k) {
        return this.mapping.remove(k);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final <T> T sync(Callable<T> callable) {
        T call;
        synchronized (this.mapping) {
            try {
                try {
                    call = callable.call();
                } catch (Exception unused) {
                    Log.e(TAG, "failed to finish operation");
                    return null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return call;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void sync(Runnable runnable) {
        synchronized (this.mapping) {
            runnable.run();
        }
    }
}

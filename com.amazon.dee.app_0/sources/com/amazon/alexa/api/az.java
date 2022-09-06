package com.amazon.alexa.api;

import java.util.Map;
import java.util.concurrent.Callable;
/* loaded from: classes6.dex */
abstract class az<K, V> extends AlexaConnectionProxyMapper<K, V> {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.api.AlexaConnectionProxyMapper
    public void clear() {
        sync(new Runnable() { // from class: com.amazon.alexa.api.az.3
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.lang.Runnable
            public void run() {
                for (Map.Entry entry : az.super.entrySet()) {
                    az.this.clearValue(entry.getKey(), entry.getValue());
                }
                az.super.clear();
            }
        });
    }

    protected abstract void clearValue(K k, V v);

    /* renamed from: createValueFor */
    protected abstract V mo846createValueFor(K k);

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.api.AlexaConnectionProxyMapper
    public V get(final K k) {
        return (V) sync((Callable<V>) new Callable<V>() { // from class: com.amazon.alexa.api.az.1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public V call() {
                if (!az.this.containsKey(k)) {
                    az azVar = az.this;
                    Object obj = k;
                    azVar.put(obj, azVar.mo846createValueFor(obj));
                }
                return (V) az.super.get(k);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.api.AlexaConnectionProxyMapper
    public V remove(final K k) {
        return (V) sync((Callable<V>) new Callable<V>() { // from class: com.amazon.alexa.api.az.2
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public V call() {
                V v = (V) az.super.remove(k);
                az.this.clearValue(k, v);
                return v;
            }
        });
    }
}

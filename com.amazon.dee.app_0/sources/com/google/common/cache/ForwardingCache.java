package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingObject;
import com.google.common.collect.ImmutableMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingCache<K, V> extends ForwardingObject implements Cache<K, V> {

    /* loaded from: classes2.dex */
    public static abstract class SimpleForwardingCache<K, V> extends ForwardingCache<K, V> {
        private final Cache<K, V> delegate;

        protected SimpleForwardingCache(Cache<K, V> cache) {
            this.delegate = (Cache) Preconditions.checkNotNull(cache);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        /* renamed from: delegate */
        public final Cache<K, V> mo8280delegate() {
            return this.delegate;
        }
    }

    @Override // com.google.common.cache.Cache
    public ConcurrentMap<K, V> asMap() {
        return mo8280delegate().asMap();
    }

    @Override // com.google.common.cache.Cache
    public void cleanUp() {
        mo8280delegate().cleanUp();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingObject
    /* renamed from: delegate */
    public abstract Cache<K, V> mo8280delegate();

    @Override // com.google.common.cache.Cache
    public V get(K k, Callable<? extends V> callable) throws ExecutionException {
        return mo8280delegate().get(k, callable);
    }

    @Override // com.google.common.cache.Cache
    public ImmutableMap<K, V> getAllPresent(Iterable<?> iterable) {
        return mo8280delegate().getAllPresent(iterable);
    }

    @Override // com.google.common.cache.Cache
    @NullableDecl
    public V getIfPresent(Object obj) {
        return mo8280delegate().getIfPresent(obj);
    }

    @Override // com.google.common.cache.Cache
    public void invalidate(Object obj) {
        mo8280delegate().invalidate(obj);
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll(Iterable<?> iterable) {
        mo8280delegate().invalidateAll(iterable);
    }

    @Override // com.google.common.cache.Cache
    public void put(K k, V v) {
        mo8280delegate().put(k, v);
    }

    @Override // com.google.common.cache.Cache
    public void putAll(Map<? extends K, ? extends V> map) {
        mo8280delegate().putAll(map);
    }

    @Override // com.google.common.cache.Cache
    public long size() {
        return mo8280delegate().size();
    }

    @Override // com.google.common.cache.Cache
    public CacheStats stats() {
        return mo8280delegate().stats();
    }

    @Override // com.google.common.cache.Cache
    public void invalidateAll() {
        mo8280delegate().invalidateAll();
    }
}

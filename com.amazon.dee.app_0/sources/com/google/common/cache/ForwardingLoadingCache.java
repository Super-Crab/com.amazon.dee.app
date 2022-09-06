package com.google.common.cache;

import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import java.util.concurrent.ExecutionException;
@GwtIncompatible
/* loaded from: classes2.dex */
public abstract class ForwardingLoadingCache<K, V> extends ForwardingCache<K, V> implements LoadingCache<K, V> {
    protected ForwardingLoadingCache() {
    }

    @Override // com.google.common.cache.LoadingCache, com.google.common.base.Function
    /* renamed from: apply */
    public V mo8172apply(K k) {
        return mo8280delegate().mo8172apply(k);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
    /* renamed from: delegate  reason: collision with other method in class */
    public abstract LoadingCache<K, V> mo8280delegate();

    @Override // com.google.common.cache.LoadingCache
    public V get(K k) throws ExecutionException {
        return mo8280delegate().get(k);
    }

    @Override // com.google.common.cache.LoadingCache
    public ImmutableMap<K, V> getAll(Iterable<? extends K> iterable) throws ExecutionException {
        return mo8280delegate().getAll(iterable);
    }

    @Override // com.google.common.cache.LoadingCache
    public V getUnchecked(K k) {
        return mo8280delegate().getUnchecked(k);
    }

    @Override // com.google.common.cache.LoadingCache
    public void refresh(K k) {
        mo8280delegate().refresh(k);
    }

    /* loaded from: classes2.dex */
    public static abstract class SimpleForwardingLoadingCache<K, V> extends ForwardingLoadingCache<K, V> {
        private final LoadingCache<K, V> delegate;

        protected SimpleForwardingLoadingCache(LoadingCache<K, V> loadingCache) {
            this.delegate = (LoadingCache) Preconditions.checkNotNull(loadingCache);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.cache.ForwardingLoadingCache, com.google.common.cache.ForwardingCache, com.google.common.collect.ForwardingObject
        /* renamed from: delegate  reason: collision with other method in class */
        public final LoadingCache<K, V> mo8280delegate() {
            return this.delegate;
        }
    }
}

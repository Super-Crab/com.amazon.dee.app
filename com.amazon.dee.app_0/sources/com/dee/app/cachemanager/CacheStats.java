package com.dee.app.cachemanager;

import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes9.dex */
public class CacheStats implements CacheStatistics {
    private AtomicInteger missCount = new AtomicInteger();
    private AtomicInteger hitCount = new AtomicInteger();
    private AtomicInteger evictionCount = new AtomicInteger();
    private AtomicInteger putCount = new AtomicInteger();
    private AtomicInteger errorCount = new AtomicInteger();

    public void clearStats() {
        this.putCount.set(0);
        this.evictionCount.set(0);
        this.hitCount.set(0);
        this.missCount.set(0);
        this.errorCount.set(0);
    }

    @Override // com.dee.app.cachemanager.CacheStatistics
    public int getErrorCount() {
        return this.errorCount.get();
    }

    @Override // com.dee.app.cachemanager.CacheStatistics
    public int getEvictionCount() {
        return this.evictionCount.get();
    }

    @Override // com.dee.app.cachemanager.CacheStatistics
    public int getHitCount() {
        return this.hitCount.get();
    }

    @Override // com.dee.app.cachemanager.CacheStatistics
    public int getMissCount() {
        return this.missCount.get();
    }

    @Override // com.dee.app.cachemanager.CacheStatistics
    public int getPutCount() {
        return this.putCount.get();
    }

    public void incrementErrorCount() {
        this.errorCount.incrementAndGet();
    }

    public void incrementEvictionCount() {
        this.evictionCount.incrementAndGet();
    }

    public void incrementHitCount() {
        this.hitCount.incrementAndGet();
    }

    public void incrementMissCount() {
        this.missCount.incrementAndGet();
    }

    public void incrementPutCount() {
        this.putCount.incrementAndGet();
    }
}

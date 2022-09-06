package com.dee.app.cachemanager;
/* loaded from: classes9.dex */
public interface CacheStatistics {
    int getErrorCount();

    int getEvictionCount();

    int getHitCount();

    int getMissCount();

    int getPutCount();
}

package com.dee.app.cachemanager;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class AppDataCacheEntry implements Serializable {
    public static final int MEMORY_EVICTION_TIER_ONE = 0;
    public static final int MEMORY_EVICTION_TIER_TWO = 1;
    private String data;
    private boolean encrypt;
    private int evictionTier;
    private final long modificationDate;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface EvictionTier {
    }

    private AppDataCacheEntry() {
        this.encrypt = true;
        this.modificationDate = System.currentTimeMillis();
    }

    public boolean encrypt() {
        return this.encrypt;
    }

    public String getData() {
        return this.data;
    }

    public int getEvictionTier() {
        return this.evictionTier;
    }

    public long getModificationDate() {
        return this.modificationDate;
    }

    public void setData(String str) {
        this.data = str;
    }

    public AppDataCacheEntry(String str, int i, boolean z) {
        this.encrypt = true;
        this.evictionTier = i;
        this.encrypt = z;
        this.modificationDate = System.currentTimeMillis();
        this.data = str;
    }
}

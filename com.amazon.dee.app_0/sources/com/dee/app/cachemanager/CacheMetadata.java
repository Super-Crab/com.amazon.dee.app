package com.dee.app.cachemanager;

import androidx.annotation.Nullable;
/* loaded from: classes9.dex */
public class CacheMetadata {
    public static final CacheMetadata EMPTY = new CacheMetadata(0, null);
    @Nullable
    public final String module;
    public final int priority;

    public CacheMetadata(int i, @Nullable String str) {
        this.priority = i;
        this.module = str;
    }

    public CacheMetadata(@Nullable String str) {
        this.priority = 0;
        this.module = str;
    }

    public CacheMetadata(int i) {
        this.priority = i;
        this.module = null;
    }
}

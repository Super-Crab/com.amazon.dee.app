package com.amazon.clouddrive.configuration;

import android.os.SystemClock;
/* loaded from: classes11.dex */
public class SimpleEndpointsCache implements EndpointsCache {
    private static long CACHE_EXPIRY_MS = 86400000;
    private Endpoints mEndpoints;
    private long mUptimeMillisWhenCached;

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public boolean cacheHasExpired() {
        return this.mEndpoints == null || this.mUptimeMillisWhenCached + CACHE_EXPIRY_MS <= SystemClock.uptimeMillis();
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void clear() {
        this.mEndpoints = null;
        this.mUptimeMillisWhenCached = 0L;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public Endpoints getEndpoints() {
        if (cacheHasExpired()) {
            clear();
        }
        return this.mEndpoints;
    }

    @Override // com.amazon.clouddrive.configuration.EndpointsCache
    public void setEndpoints(Endpoints endpoints) {
        this.mUptimeMillisWhenCached = SystemClock.uptimeMillis();
        this.mEndpoints = endpoints;
    }
}

package com.amazon.clouddrive.configuration;
/* loaded from: classes11.dex */
public interface EndpointsCache {
    boolean cacheHasExpired();

    void clear();

    Endpoints getEndpoints();

    void setEndpoints(Endpoints endpoints);
}

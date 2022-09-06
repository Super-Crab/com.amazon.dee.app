package com.amazon.whisperjoin.credentiallocker;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
class WifiEndpointResponse {
    private final String endpoint;
    private final long refreshAfterSeconds;

    public WifiEndpointResponse(String str, long j) {
        this.endpoint = str;
        this.refreshAfterSeconds = j;
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiEndpointResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiEndpointResponse)) {
            return false;
        }
        WifiEndpointResponse wifiEndpointResponse = (WifiEndpointResponse) obj;
        if (!wifiEndpointResponse.canEqual(this)) {
            return false;
        }
        String endpoint = getEndpoint();
        String endpoint2 = wifiEndpointResponse.getEndpoint();
        if (endpoint != null ? !endpoint.equals(endpoint2) : endpoint2 != null) {
            return false;
        }
        return getRefreshAfterSeconds() == wifiEndpointResponse.getRefreshAfterSeconds();
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public long getRefreshAfterSeconds() {
        return this.refreshAfterSeconds;
    }

    public int hashCode() {
        String endpoint = getEndpoint();
        int hashCode = endpoint == null ? 43 : endpoint.hashCode();
        long refreshAfterSeconds = getRefreshAfterSeconds();
        return ((hashCode + 59) * 59) + ((int) ((refreshAfterSeconds >>> 32) ^ refreshAfterSeconds));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiEndpointResponse(endpoint=");
        outline107.append(getEndpoint());
        outline107.append(", refreshAfterSeconds=");
        return GeneratedOutlineSupport1.outline87(outline107, getRefreshAfterSeconds(), ")");
    }
}

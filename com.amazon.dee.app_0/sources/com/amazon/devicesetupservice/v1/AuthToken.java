package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class AuthToken implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.AuthToken");
    private String marketplaceId;
    private long maxAgeSeconds;
    private String token;
    private String ubid;

    public boolean equals(Object obj) {
        if (!(obj instanceof AuthToken)) {
            return false;
        }
        AuthToken authToken = (AuthToken) obj;
        return Helper.equals(this.token, authToken.token) && Helper.equals(this.ubid, authToken.ubid) && Helper.equals(Long.valueOf(this.maxAgeSeconds), Long.valueOf(authToken.maxAgeSeconds)) && Helper.equals(this.marketplaceId, authToken.marketplaceId);
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public long getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public String getToken() {
        return this.token;
    }

    public String getUbid() {
        return this.ubid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.token, this.ubid, Long.valueOf(this.maxAgeSeconds), this.marketplaceId);
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMaxAgeSeconds(long j) {
        this.maxAgeSeconds = j;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setUbid(String str) {
        this.ubid = str;
    }
}

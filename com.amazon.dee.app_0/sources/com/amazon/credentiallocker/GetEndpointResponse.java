package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetEndpointResponse implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.GetEndpointResponse");
    private String endpoint;
    private long refreshAfterSeconds;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetEndpointResponse)) {
            return false;
        }
        GetEndpointResponse getEndpointResponse = (GetEndpointResponse) obj;
        return Helper.equals(Long.valueOf(this.refreshAfterSeconds), Long.valueOf(getEndpointResponse.refreshAfterSeconds)) && Helper.equals(this.endpoint, getEndpointResponse.endpoint);
    }

    public String getEndpoint() {
        return this.endpoint;
    }

    public long getRefreshAfterSeconds() {
        return this.refreshAfterSeconds;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Long.valueOf(this.refreshAfterSeconds), this.endpoint);
    }

    public void setEndpoint(String str) {
        this.endpoint = str;
    }

    public void setRefreshAfterSeconds(long j) {
        this.refreshAfterSeconds = j;
    }
}

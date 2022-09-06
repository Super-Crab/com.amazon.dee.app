package com.amazon.communication.directorservice;
/* loaded from: classes12.dex */
public class ResultCacheValue {
    private long mLastWriteMillis;
    private GetEndpointResponse mResponse;

    private ResultCacheValue(GetEndpointResponse getEndpointResponse, long j) {
        this.mResponse = getEndpointResponse;
        this.mLastWriteMillis = j;
    }

    public static ResultCacheValue newValue(GetEndpointResponse getEndpointResponse, long j) {
        return new ResultCacheValue(getEndpointResponse, j);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ResultCacheValue.class != obj.getClass()) {
            return false;
        }
        ResultCacheValue resultCacheValue = (ResultCacheValue) obj;
        if (this.mLastWriteMillis != resultCacheValue.mLastWriteMillis) {
            return false;
        }
        GetEndpointResponse getEndpointResponse = this.mResponse;
        if (getEndpointResponse == null) {
            if (resultCacheValue.mResponse != null) {
                return false;
            }
        } else if (!getEndpointResponse.equals(resultCacheValue.mResponse)) {
            return false;
        }
        return true;
    }

    public long getLastWriteMillis() {
        return this.mLastWriteMillis;
    }

    public GetEndpointResponse getResponse() {
        return this.mResponse;
    }

    public int hashCode() {
        long j = this.mLastWriteMillis;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        GetEndpointResponse getEndpointResponse = this.mResponse;
        return i + (getEndpointResponse == null ? 0 : getEndpointResponse.hashCode());
    }
}

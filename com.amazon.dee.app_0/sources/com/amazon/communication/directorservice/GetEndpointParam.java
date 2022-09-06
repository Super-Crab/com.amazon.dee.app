package com.amazon.communication.directorservice;

import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public class GetEndpointParam {
    private final String mBaseUrl;
    private final String mLocation;
    private final GetEndpointParamKey mLocationParamKey;
    private final String mPurpose;
    private final String mServiceName;

    /* loaded from: classes12.dex */
    public static class Builder {
        private String mDirectorUrl = null;
        private String mServiceName = null;
        private GetEndpointParamKey mLocationParamKey = null;
        private String mLocation = null;
        private String mPurpose = null;

        public GetEndpointParam build() {
            FailFast.expectNotNull(this.mDirectorUrl, "Must set director url");
            FailFast.expectNotNull(this.mServiceName, "Must set service name");
            FailFast.expectNotNull(this.mLocationParamKey, "Must set either marketplace or region");
            FailFast.expectNotNull(this.mLocation, "Must set location");
            FailFast.expectNotNull(this.mPurpose, "Must set purpose");
            return new GetEndpointParam(this.mDirectorUrl, this.mServiceName, this.mLocationParamKey, this.mLocation, this.mPurpose);
        }

        public Builder setDirectorUrl(String str) {
            this.mDirectorUrl = str;
            return this;
        }

        public Builder setMarketplace(String str) {
            this.mLocationParamKey = GetEndpointParamKey.MARKETPLACE;
            this.mLocation = str;
            return this;
        }

        public Builder setPurpose(String str) {
            this.mPurpose = str;
            return this;
        }

        public Builder setRegion(String str) {
            this.mLocationParamKey = GetEndpointParamKey.REGION;
            this.mLocation = str;
            return this;
        }

        public Builder setServiceName(String str) {
            this.mServiceName = str;
            return this;
        }
    }

    /* loaded from: classes12.dex */
    public enum GetEndpointParamKey {
        MARKETPLACE("marketplace"),
        REGION("region"),
        PURPOSE("purpose");
        
        private final String mStringValue;

        GetEndpointParamKey(String str) {
            this.mStringValue = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.mStringValue;
        }
    }

    public String getBaseUrl() {
        return this.mBaseUrl;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public GetEndpointParamKey getLocationParamKey() {
        return this.mLocationParamKey;
    }

    public String getPurpose() {
        return this.mPurpose;
    }

    public String getServiceName() {
        return this.mServiceName;
    }

    public String toString() {
        return String.format("[baseUrl = %s, serviceName = %s, locationParamKey = %s, location = %s, purpose = %s]", this.mBaseUrl, this.mServiceName, this.mLocationParamKey, this.mLocation, this.mPurpose);
    }

    private GetEndpointParam(String str, String str2, GetEndpointParamKey getEndpointParamKey, String str3, String str4) {
        this.mBaseUrl = str;
        this.mServiceName = str2;
        this.mLocationParamKey = getEndpointParamKey;
        this.mLocation = str3;
        this.mPurpose = str4;
    }
}

package com.amazon.whisperjoin.devicesetupserviceandroidclient.data;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class ProvisioneeSetupStatus {
    private final AuthMaterialIdentifier mAuthMaterialIdentifier;
    private final String mErrorCode;
    private final String mLastUpdatedTime;
    private final AuthMaterialIdentifier mProvisioneeAuthMaterialIdentifier;
    private final BarcodeIdentifier mProvisioneeBarcodeIdentifier;
    private final LegacyIdentifierData mProvisionerInformation;
    private final String mProvisioningMethod;
    private final String mProvisioningState;
    private final String mProvisioningStatus;

    /* loaded from: classes13.dex */
    public static class Builder {
        private AuthMaterialIdentifier mAuthMaterialIdentifier;
        private String mErrorCode;
        private String mLastUpdatedTime;
        private AuthMaterialIdentifier mProvisioneeAuthMaterialIdentifier;
        private BarcodeIdentifier mProvisioneeBarcodeIdentifier;
        private LegacyIdentifierData mProvisionerInformation;
        private String mProvisioningMethod;
        private String mProvisioningState;
        private String mProvisioningStatus;

        public ProvisioneeSetupStatus build() {
            return new ProvisioneeSetupStatus(this);
        }

        public Builder setAuthMaterialIdentifier(AuthMaterialIdentifier authMaterialIdentifier) {
            this.mAuthMaterialIdentifier = authMaterialIdentifier;
            return this;
        }

        public Builder setErrorCode(String str) {
            this.mErrorCode = str;
            return this;
        }

        public Builder setLastUpdatedTime(String str) {
            this.mLastUpdatedTime = str;
            return this;
        }

        public Builder setProvisioneeAuthMaterialIdentifier(AuthMaterialIdentifier authMaterialIdentifier) {
            this.mProvisioneeAuthMaterialIdentifier = authMaterialIdentifier;
            return this;
        }

        public Builder setProvisioneeBarcodeIdentifier(BarcodeIdentifier barcodeIdentifier) {
            this.mProvisioneeBarcodeIdentifier = barcodeIdentifier;
            return this;
        }

        public Builder setProvisionerInformation(LegacyIdentifierData legacyIdentifierData) {
            this.mProvisionerInformation = legacyIdentifierData;
            return this;
        }

        public Builder setProvisioningMethod(String str) {
            this.mProvisioningMethod = str;
            return this;
        }

        public Builder setProvisioningState(String str) {
            this.mProvisioningState = str;
            return this;
        }

        public Builder setProvisioningStatus(String str) {
            this.mProvisioningStatus = str;
            return this;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisioneeSetupStatus.class != obj.getClass()) {
            return false;
        }
        ProvisioneeSetupStatus provisioneeSetupStatus = (ProvisioneeSetupStatus) obj;
        return Objects.equal(this.mAuthMaterialIdentifier, provisioneeSetupStatus.mAuthMaterialIdentifier) && Objects.equal(this.mProvisionerInformation, provisioneeSetupStatus.mProvisionerInformation) && Objects.equal(this.mProvisioneeAuthMaterialIdentifier, provisioneeSetupStatus.mProvisioneeAuthMaterialIdentifier) && Objects.equal(this.mProvisioneeBarcodeIdentifier, provisioneeSetupStatus.mProvisioneeBarcodeIdentifier) && Objects.equal(this.mErrorCode, provisioneeSetupStatus.mErrorCode) && Objects.equal(this.mLastUpdatedTime, provisioneeSetupStatus.mLastUpdatedTime) && Objects.equal(this.mProvisioningMethod, provisioneeSetupStatus.mProvisioningMethod) && Objects.equal(this.mProvisioningState, provisioneeSetupStatus.mProvisioningState) && Objects.equal(this.mProvisioningStatus, provisioneeSetupStatus.mProvisioningStatus);
    }

    public AuthMaterialIdentifier getAuthMaterialIdentifier() {
        return this.mAuthMaterialIdentifier;
    }

    public String getErrorCode() {
        return this.mErrorCode;
    }

    public String getLastUpdatedTime() {
        return this.mLastUpdatedTime;
    }

    public AuthMaterialIdentifier getProvisioneeAuthMaterialIdentifier() {
        return this.mProvisioneeAuthMaterialIdentifier;
    }

    public BarcodeIdentifier getProvisioneeBarcodeIdentifier() {
        return this.mProvisioneeBarcodeIdentifier;
    }

    public LegacyIdentifierData getProvisionerInformation() {
        return this.mProvisionerInformation;
    }

    public String getProvisioningMethod() {
        return this.mProvisioningMethod;
    }

    public String getProvisioningState() {
        return this.mProvisioningState;
    }

    public String getProvisioningStatus() {
        return this.mProvisioningStatus;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAuthMaterialIdentifier, this.mProvisionerInformation, this.mProvisioneeAuthMaterialIdentifier, this.mProvisioneeBarcodeIdentifier, this.mErrorCode, this.mLastUpdatedTime, this.mProvisioningMethod, this.mProvisioningState, this.mProvisioningStatus);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAuthMaterialIdentifier", this.mAuthMaterialIdentifier).add("mProvisionerInformation", this.mProvisionerInformation).add("mProvisioneeAuthMaterialIdentifier", this.mProvisioneeAuthMaterialIdentifier).add("mProvisioneeBarcodeIdentifier", this.mProvisioneeBarcodeIdentifier).add("mErrorCode", this.mErrorCode).add("mLastUpdatedTime", this.mLastUpdatedTime).add("mProvisioningMethod", this.mProvisioningMethod).add("mProvisioningState", this.mProvisioningState).add("mProvisioningStatus", this.mProvisioningStatus).toString();
    }

    private ProvisioneeSetupStatus(Builder builder) {
        this.mAuthMaterialIdentifier = builder.mAuthMaterialIdentifier;
        this.mProvisionerInformation = builder.mProvisionerInformation;
        this.mProvisioneeAuthMaterialIdentifier = builder.mProvisioneeAuthMaterialIdentifier;
        this.mProvisioneeBarcodeIdentifier = builder.mProvisioneeBarcodeIdentifier;
        this.mErrorCode = builder.mErrorCode;
        this.mLastUpdatedTime = builder.mLastUpdatedTime;
        this.mProvisioningMethod = builder.mProvisioningMethod;
        this.mProvisioningState = builder.mProvisioningState;
        this.mProvisioningStatus = builder.mProvisioningStatus;
    }
}

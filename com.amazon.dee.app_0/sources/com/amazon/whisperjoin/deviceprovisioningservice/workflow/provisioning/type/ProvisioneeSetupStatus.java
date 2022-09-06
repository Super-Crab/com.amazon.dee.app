package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class ProvisioneeSetupStatus implements Parcelable {
    public static final Parcelable.Creator<ProvisioneeSetupStatus> CREATOR = new Parcelable.Creator<ProvisioneeSetupStatus>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioneeSetupStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisioneeSetupStatus mo5694createFromParcel(Parcel parcel) {
            return new ProvisioneeSetupStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisioneeSetupStatus[] mo5695newArray(int i) {
            return new ProvisioneeSetupStatus[i];
        }
    };
    private final AuthMaterialIdentifier mAuthMaterialIdentifier;
    private final String mErrorCode;
    private final String mLastUpdatedTime;
    private final AuthMaterialIdentifier mProvisioneeAuthMaterialIdentifier;
    private final BarcodeIdentifier mProvisioneeBarcodeIdentifier;
    private final LegacyIdentifier mProvisionerInformation;
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
        private LegacyIdentifier mProvisionerInformation;
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

        public Builder setProvisionerInformation(LegacyIdentifier legacyIdentifier) {
            this.mProvisionerInformation = legacyIdentifier;
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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
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

    public LegacyIdentifier getProvisionerInformation() {
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

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAuthMaterialIdentifier, i);
        parcel.writeParcelable(this.mProvisionerInformation, i);
        parcel.writeParcelable(this.mProvisioneeAuthMaterialIdentifier, i);
        parcel.writeParcelable(this.mProvisioneeBarcodeIdentifier, i);
        parcel.writeString(this.mErrorCode);
        parcel.writeString(this.mLastUpdatedTime);
        parcel.writeString(this.mProvisioningMethod);
        parcel.writeString(this.mProvisioningState);
        parcel.writeString(this.mProvisioningStatus);
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

    private ProvisioneeSetupStatus(Parcel parcel) {
        this.mAuthMaterialIdentifier = (AuthMaterialIdentifier) parcel.readParcelable(AuthMaterialIdentifier.class.getClassLoader());
        this.mProvisionerInformation = (LegacyIdentifier) parcel.readParcelable(LegacyIdentifier.class.getClassLoader());
        this.mProvisioneeAuthMaterialIdentifier = (AuthMaterialIdentifier) parcel.readParcelable(AuthMaterialIdentifier.class.getClassLoader());
        this.mProvisioneeBarcodeIdentifier = (BarcodeIdentifier) parcel.readParcelable(BarcodeIdentifier.class.getClassLoader());
        this.mErrorCode = parcel.readString();
        this.mLastUpdatedTime = parcel.readString();
        this.mProvisioningMethod = parcel.readString();
        this.mProvisioningState = parcel.readString();
        this.mProvisioningStatus = parcel.readString();
    }
}

package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class AuthMaterialIdentifier implements Parcelable {
    public static final Parcelable.Creator<AuthMaterialIdentifier> CREATOR = new Parcelable.Creator<AuthMaterialIdentifier>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AuthMaterialIdentifier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AuthMaterialIdentifier mo5681createFromParcel(Parcel parcel) {
            return new AuthMaterialIdentifier(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AuthMaterialIdentifier[] mo5682newArray(int i) {
            return new AuthMaterialIdentifier[i];
        }
    };
    private final String mAuthMaterialIndex;
    private final String mProductIndex;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mAuthMaterialIndex;
        private String mProductIndex;

        public AuthMaterialIdentifier build() {
            boolean z = true;
            Preconditions.checkArgument(this.mAuthMaterialIndex != null);
            if (this.mProductIndex == null) {
                z = false;
            }
            Preconditions.checkArgument(z);
            return new AuthMaterialIdentifier(this);
        }

        public Builder setAuthMaterialIndex(String str) {
            this.mAuthMaterialIndex = str;
            return this;
        }

        public Builder setProductIndex(String str) {
            this.mProductIndex = str;
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
        if (obj == null || AuthMaterialIdentifier.class != obj.getClass()) {
            return false;
        }
        AuthMaterialIdentifier authMaterialIdentifier = (AuthMaterialIdentifier) obj;
        return Objects.equal(this.mAuthMaterialIndex, authMaterialIdentifier.mAuthMaterialIndex) && Objects.equal(this.mProductIndex, authMaterialIdentifier.mProductIndex);
    }

    public String getAuthMaterialIndex() {
        return this.mAuthMaterialIndex;
    }

    public String getProductIndex() {
        return this.mProductIndex;
    }

    public int hashCode() {
        return Objects.hashCode(this.mAuthMaterialIndex, this.mProductIndex);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mAuthMaterialIndex", this.mAuthMaterialIndex).add("mProductIndex", this.mProductIndex).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAuthMaterialIndex);
        parcel.writeString(this.mProductIndex);
    }

    protected AuthMaterialIdentifier(Builder builder) {
        this.mAuthMaterialIndex = builder.mAuthMaterialIndex;
        this.mProductIndex = builder.mProductIndex;
    }

    private AuthMaterialIdentifier(Parcel parcel) {
        this.mAuthMaterialIndex = parcel.readString();
        this.mProductIndex = parcel.readString();
    }
}

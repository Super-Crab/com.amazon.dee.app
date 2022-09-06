package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class LegacyIdentifier implements Parcelable {
    public static final Parcelable.Creator<LegacyIdentifier> CREATOR = new Parcelable.Creator<LegacyIdentifier>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.LegacyIdentifier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public LegacyIdentifier mo5692createFromParcel(Parcel parcel) {
            return new LegacyIdentifier(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public LegacyIdentifier[] mo5693newArray(int i) {
            return new LegacyIdentifier[i];
        }
    };
    private final String mDeviceType;
    private final String mDsn;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mDeviceType;
        private String mDsn;

        public LegacyIdentifier build() {
            return new LegacyIdentifier(this);
        }

        public Builder setDeviceType(String str) {
            this.mDeviceType = str;
            return this;
        }

        public Builder setDsn(String str) {
            this.mDsn = str;
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
        if (obj == null || LegacyIdentifier.class != obj.getClass()) {
            return false;
        }
        LegacyIdentifier legacyIdentifier = (LegacyIdentifier) obj;
        return Objects.equal(this.mDsn, legacyIdentifier.mDsn) && Objects.equal(this.mDeviceType, legacyIdentifier.mDeviceType);
    }

    public String getDeviceType() {
        return this.mDeviceType;
    }

    public String getDsn() {
        return this.mDsn;
    }

    public int hashCode() {
        return Objects.hashCode(this.mDsn, this.mDeviceType);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mDsn", this.mDsn).add("mDeviceType", this.mDeviceType).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDsn);
        parcel.writeString(this.mDeviceType);
    }

    protected LegacyIdentifier(Builder builder) {
        this.mDsn = builder.mDsn;
        this.mDeviceType = builder.mDeviceType;
    }

    private LegacyIdentifier(Parcel parcel) {
        this.mDsn = parcel.readString();
        this.mDeviceType = parcel.readString();
    }
}

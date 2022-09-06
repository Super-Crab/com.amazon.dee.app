package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import javax.annotation.concurrent.Immutable;
@Immutable
/* loaded from: classes13.dex */
public class BarcodeIdentifier implements Parcelable {
    public static final Parcelable.Creator<BarcodeIdentifier> CREATOR = new Parcelable.Creator<BarcodeIdentifier>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.BarcodeIdentifier.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public BarcodeIdentifier mo5687createFromParcel(Parcel parcel) {
            return new BarcodeIdentifier(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public BarcodeIdentifier[] mo5688newArray(int i) {
            return new BarcodeIdentifier[i];
        }
    };
    private final String mBarcodeData;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mBarcodeData;

        public BarcodeIdentifier build() {
            Preconditions.checkArgument(this.mBarcodeData != null);
            return new BarcodeIdentifier(this);
        }

        public Builder setBarcodeData(String str) {
            this.mBarcodeData = str;
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
        if (obj != null && BarcodeIdentifier.class == obj.getClass()) {
            return Objects.equal(this.mBarcodeData, ((BarcodeIdentifier) obj).mBarcodeData);
        }
        return false;
    }

    public String getBarcodeData() {
        return this.mBarcodeData;
    }

    public int hashCode() {
        return Objects.hashCode(this.mBarcodeData);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mBarcodeDatra", this.mBarcodeData).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mBarcodeData);
    }

    protected BarcodeIdentifier(Builder builder) {
        this.mBarcodeData = builder.mBarcodeData;
    }

    private BarcodeIdentifier(Parcel parcel) {
        this.mBarcodeData = parcel.readString();
    }
}

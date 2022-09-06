package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes13.dex */
public class ProvisioningFailure implements Parcelable {
    public static final Parcelable.Creator<ProvisioningFailure> CREATOR = new Parcelable.Creator<ProvisioningFailure>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.provisioning.ProvisioningFailure.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ProvisioningFailure mo5443createFromParcel(Parcel parcel) {
            if (parcel != null) {
                int readInt = parcel.readInt();
                if (parcel.readInt() == 1) {
                    return new ProvisioningFailure(readInt, parcel.readString());
                }
                return new ProvisioningFailure(readInt);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ProvisioningFailure[] mo5444newArray(int i) {
            if (i >= 0) {
                return new ProvisioningFailure[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }
    };
    private final int mErrorCode;
    private final String mMessage;

    public ProvisioningFailure(int i) {
        this(i, null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisioningFailure.class != obj.getClass()) {
            return false;
        }
        ProvisioningFailure provisioningFailure = (ProvisioningFailure) obj;
        return this.mErrorCode == provisioningFailure.mErrorCode && Objects.equal(this.mMessage, provisioningFailure.mMessage);
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.mErrorCode), this.mMessage);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mErrorCode", this.mErrorCode).add("mMessage", this.mMessage).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(this.mErrorCode);
            if (this.mMessage != null) {
                parcel.writeInt(1);
                parcel.writeString(this.mMessage);
                return;
            }
            parcel.writeInt(0);
            return;
        }
        throw new IllegalArgumentException("parcel cannot be null.");
    }

    public ProvisioningFailure(int i, String str) {
        this.mErrorCode = i;
        this.mMessage = str;
    }
}

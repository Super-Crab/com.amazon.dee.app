package com.amazon.communication;

import amazon.communication.connection.ConnectionPolicy;
import amazon.communication.connection.Priority;
import android.os.Parcel;
import android.os.Parcelable;
import com.dp.utils.FailFast;
/* loaded from: classes12.dex */
public class ParcelableConnectionPolicy extends SimpleConnectionPolicy implements Parcelable {
    public static final Parcelable.Creator<ParcelableConnectionPolicy> CREATOR = new Parcelable.Creator<ParcelableConnectionPolicy>() { // from class: com.amazon.communication.ParcelableConnectionPolicy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ParcelableConnectionPolicy mo3281createFromParcel(Parcel parcel) {
            ParcelableConnectionPolicy parcelableConnectionPolicy = new ParcelableConnectionPolicy();
            parcelableConnectionPolicy.setIsRoamingAllowed(parcel.readByte() != 0);
            parcelableConnectionPolicy.setIsShortLived(parcel.readByte() != 0);
            parcelableConnectionPolicy.setIsLowLatencyNecessary(parcel.readByte() != 0);
            parcelableConnectionPolicy.setIsRequestResponseOnly(parcel.readByte() != 0);
            int readInt = parcel.readInt();
            if (readInt == ConnectionPolicy.CompressionOption.ALLOWED.ordinal()) {
                parcelableConnectionPolicy.setCompressionOption(ConnectionPolicy.CompressionOption.ALLOWED);
            } else if (readInt == ConnectionPolicy.CompressionOption.NOT_ALLOWED.ordinal()) {
                parcelableConnectionPolicy.setCompressionOption(ConnectionPolicy.CompressionOption.NOT_ALLOWED);
            } else if (readInt == ConnectionPolicy.CompressionOption.REQUIRED.ordinal()) {
                parcelableConnectionPolicy.setCompressionOption(ConnectionPolicy.CompressionOption.REQUIRED);
            } else {
                FailFast.expectTrue(false, "Unknown ordinal value for CompressionOption ", Integer.valueOf(readInt));
            }
            parcelableConnectionPolicy.setIsClearText(parcel.readByte() != 0);
            parcelableConnectionPolicy.setIsWiFiNecessary(parcel.readByte() != 0);
            parcelableConnectionPolicy.setIsInstanceLocked(parcel.readByte() != 0);
            int readInt2 = parcel.readInt();
            if (readInt2 == Priority.PRIORITY_HIGH.ordinal()) {
                parcelableConnectionPolicy.setPriority(Priority.PRIORITY_HIGH);
            } else if (readInt2 == Priority.PRIORITY_NORMAL.ordinal()) {
                parcelableConnectionPolicy.setPriority(Priority.PRIORITY_NORMAL);
            } else if (readInt2 == Priority.PRIORITY_LOW.ordinal()) {
                parcelableConnectionPolicy.setPriority(Priority.PRIORITY_LOW);
            } else {
                FailFast.expectTrue(false, "Unknown ordinal value for Priority ", Integer.valueOf(readInt2));
            }
            return parcelableConnectionPolicy;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ParcelableConnectionPolicy[] mo3282newArray(int i) {
            return new ParcelableConnectionPolicy[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsRoamingAllowed ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsShortLived ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsLowLatencyNecessary ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsRequestResponseOnly ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mCompressionOption.ordinal());
        parcel.writeByte(this.mIsClearText ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsWiFiNecessary ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mIsInstanceLocked ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.mPriority.ordinal());
    }
}

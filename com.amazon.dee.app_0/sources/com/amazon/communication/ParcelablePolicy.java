package com.amazon.communication;

import amazon.communication.connection.CompressionOption;
import amazon.communication.connection.KeepAlive;
import amazon.communication.connection.Policy;
import amazon.communication.connection.Priority;
import amazon.communication.connection.Purpose;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public class ParcelablePolicy implements Parcelable {
    public static final Parcelable.Creator<ParcelablePolicy> CREATOR = new Parcelable.Creator<ParcelablePolicy>() { // from class: com.amazon.communication.ParcelablePolicy.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ParcelablePolicy mo3285createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            boolean z = false;
            if (readInt == 1) {
                CompressionOption valueOf = CompressionOption.valueOf(parcel.readString());
                Priority valueOf2 = Priority.valueOf(parcel.readString());
                boolean z2 = parcel.readByte() != 0;
                boolean z3 = parcel.readByte() != 0;
                boolean z4 = parcel.readByte() != 0;
                boolean z5 = parcel.readByte() != 0;
                boolean z6 = parcel.readByte() != 0;
                if (parcel.readByte() != 0) {
                    z = true;
                }
                return new ParcelablePolicy(new Policy.Builder().setCompressionOption(valueOf).setPriority(valueOf2).setIsLowLatencyNecessary(z2).setIsRequestResponseOnly(z3).setIsClearText(z4).setIsWiFiNecessary(z5).setIsAnonymousCredentialsAllowed(z6).setIsDedicated(z).setPurpose(new Purpose(parcel.readString())).build());
            } else if (readInt == 2) {
                CompressionOption valueOf3 = CompressionOption.valueOf(parcel.readString());
                Priority valueOf4 = Priority.valueOf(parcel.readString());
                boolean z7 = parcel.readByte() != 0;
                boolean z8 = parcel.readByte() != 0;
                boolean z9 = parcel.readByte() != 0;
                boolean z10 = parcel.readByte() != 0;
                boolean z11 = parcel.readByte() != 0;
                boolean z12 = parcel.readByte() != 0;
                String readString = parcel.readString();
                if (parcel.readByte() != 0) {
                    z = true;
                }
                Policy.Builder keepAlive = new Policy.Builder().setCompressionOption(valueOf3).setPriority(valueOf4).setIsLowLatencyNecessary(z7).setIsRequestResponseOnly(z8).setIsClearText(z9).setIsWiFiNecessary(z10).setIsAnonymousCredentialsAllowed(z11).setIsDedicated(z12).setReconnectOnFailure(z).setKeepAlive(KeepAlive.valueOf(parcel.readString()));
                if (!"".equals(readString)) {
                    keepAlive.setPurpose(new Purpose(readString));
                }
                return new ParcelablePolicy(keepAlive.build());
            } else {
                throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Unrecognized version: ", readInt));
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ParcelablePolicy[] mo3286newArray(int i) {
            return new ParcelablePolicy[i];
        }
    };
    private static final int VERSION = 2;
    private final Policy mPolicy;

    public ParcelablePolicy(Policy policy) {
        if (policy != null) {
            this.mPolicy = policy;
            return;
        }
        throw new IllegalArgumentException("policy must not be null");
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Policy getPolicy() {
        return this.mPolicy;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(2);
        parcel.writeString(this.mPolicy.getCompressionOption().toString());
        parcel.writeString(this.mPolicy.getPriority().toString());
        parcel.writeByte(this.mPolicy.isLowLatencyNecessary() ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mPolicy.isRequestResponseOnly() ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mPolicy.isClearText() ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mPolicy.isWiFiNecessary() ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mPolicy.isAnonymousCredentialsAllowed() ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.mPolicy.isDedicated() ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mPolicy.getPurpose() == null ? "" : this.mPolicy.getPurpose().getPurpose());
        parcel.writeByte(this.mPolicy.getReconnectOnFailure() ? (byte) 1 : (byte) 0);
        parcel.writeString(this.mPolicy.getKeepAlive().toString());
    }
}

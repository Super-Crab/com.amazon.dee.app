package com.amazon.communication.ir;

import amazon.communication.identity.IRServiceEndpoint;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes12.dex */
public class ParcelableIRServiceEndpoint implements Parcelable, IRServiceEndpoint {
    public static final Parcelable.Creator<ParcelableIRServiceEndpoint> CREATOR = new Creator();
    private final IRServiceEndpoint mEndpoint;

    /* loaded from: classes12.dex */
    static class Creator implements Parcelable.Creator<ParcelableIRServiceEndpoint> {
        Creator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ParcelableIRServiceEndpoint mo3294createFromParcel(Parcel parcel) {
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            IRServiceEndpoint.DirectConnection parse = IRServiceEndpoint.DirectConnection.parse(parcel.readString());
            IRServiceEndpoint.DataCompression parse2 = IRServiceEndpoint.DataCompression.parse(parcel.readString());
            IRServiceEndpoint.ClearTextConnection parse3 = IRServiceEndpoint.ClearTextConnection.parse(parcel.readString());
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            return new ParcelableIRServiceEndpoint(new IRServiceEndpointImpl(readString, readString2, parcel.readString(), readString3, parse, parse2, parse3, readInt, readInt2 == -1 ? null : Integer.valueOf(readInt2), readInt3 == -1 ? null : Integer.valueOf(readInt3)));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ParcelableIRServiceEndpoint[] mo3295newArray(int i) {
            return new ParcelableIRServiceEndpoint[i];
        }
    }

    public ParcelableIRServiceEndpoint(IRServiceEndpoint iRServiceEndpoint) {
        this.mEndpoint = iRServiceEndpoint;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public IRServiceEndpoint.ClearTextConnection getClearTextConnection() {
        return this.mEndpoint.getClearTextConnection();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public IRServiceEndpoint.DataCompression getDataCompression() {
        return this.mEndpoint.getDataCompression();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public IRServiceEndpoint.DirectConnection getDirectConnection() {
        return this.mEndpoint.getDirectConnection();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getDirectorServiceName() {
        return this.mEndpoint.getDirectorServiceName();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getDomain() {
        return this.mEndpoint.getDomain();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getHostname() {
        return this.mEndpoint.getHostname();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public Integer getPort() {
        return this.mEndpoint.getPort();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String getRealm() {
        return this.mEndpoint.getRealm();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public Integer getSecurePort() {
        return this.mEndpoint.getSecurePort();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public int getTimeout() {
        return this.mEndpoint.getTimeout();
    }

    @Override // amazon.communication.identity.IRServiceEndpoint
    public String toResolvedUri(IRServiceEndpoint.Scheme scheme) {
        return this.mEndpoint.toResolvedUri(scheme);
    }

    public String toString() {
        return this.mEndpoint.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mEndpoint.getHostname());
        parcel.writeString(this.mEndpoint.getDomain());
        parcel.writeString(this.mEndpoint.getDirectorServiceName());
        parcel.writeString(this.mEndpoint.getDirectConnection().toString());
        parcel.writeString(this.mEndpoint.getDataCompression().toString());
        parcel.writeString(this.mEndpoint.getClearTextConnection().toString());
        parcel.writeInt(this.mEndpoint.getTimeout());
        int i2 = -1;
        parcel.writeInt(this.mEndpoint.getPort() == null ? -1 : this.mEndpoint.getPort().intValue());
        if (this.mEndpoint.getSecurePort() != null) {
            i2 = this.mEndpoint.getSecurePort().intValue();
        }
        parcel.writeInt(i2);
        parcel.writeString(this.mEndpoint.getRealm());
    }
}

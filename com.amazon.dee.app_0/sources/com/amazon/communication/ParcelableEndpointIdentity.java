package com.amazon.communication;

import amazon.communication.identity.EndpointIdentity;
import amazon.communication.identity.EndpointIdentityFactory;
import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes12.dex */
public class ParcelableEndpointIdentity extends EndpointIdentity implements Parcelable {
    public static final Parcelable.Creator<ParcelableEndpointIdentity> CREATOR = new Creator();
    private EndpointIdentity mIdentity;

    /* loaded from: classes12.dex */
    static class Creator implements Parcelable.Creator<ParcelableEndpointIdentity> {
        Creator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public ParcelableEndpointIdentity mo3283createFromParcel(Parcel parcel) {
            return new ParcelableEndpointIdentity(EndpointIdentityFactory.createFromUrn(parcel.readString()));
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public ParcelableEndpointIdentity[] mo3284newArray(int i) {
            return new ParcelableEndpointIdentity[i];
        }
    }

    public ParcelableEndpointIdentity(EndpointIdentity endpointIdentity) {
        this.mIdentity = endpointIdentity;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // amazon.communication.identity.EndpointIdentity
    public EndpointIdentity.Type getType() {
        return this.mIdentity.getType();
    }

    @Override // amazon.communication.identity.EndpointIdentity
    public String toLogSafeString() {
        return this.mIdentity.toLogSafeString();
    }

    @Override // amazon.communication.identity.EndpointIdentity
    public String toString() {
        return this.mIdentity.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIdentity.toString());
    }
}

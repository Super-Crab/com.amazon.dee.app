package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes6.dex */
public enum ClientRole implements Parcelable {
    USER,
    INACTIVITY_REPORT,
    WAKE_UP;
    
    public static final Parcelable.Creator<ClientRole> CREATOR = new Parcelable.Creator<ClientRole>() { // from class: com.amazon.alexa.api.ClientRole.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ClientRole createFromParcel(Parcel parcel) {
            return (ClientRole) parcel.readSerializable();
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ClientRole[] newArray(int i) {
            return new ClientRole[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this);
    }
}

package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscViewDirection implements Parcelable {
    LOCAL_VIEW,
    REMOTE_VIEW;
    
    public static final Parcelable.Creator<RtcscViewDirection> CREATOR = new Parcelable.Creator<RtcscViewDirection>() { // from class: com.amazon.rtcsc.interfaces.RtcscViewDirection.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscViewDirection mo4526createFromParcel(Parcel parcel) {
            return RtcscViewDirection.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscViewDirection[] mo4527newArray(int i) {
            return new RtcscViewDirection[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}

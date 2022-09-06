package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscSide implements Parcelable {
    LOCAL,
    REMOTE;
    
    public static final Parcelable.Creator<RtcscSide> CREATOR = new Parcelable.Creator<RtcscSide>() { // from class: com.amazon.rtcsc.interfaces.RtcscSide.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscSide mo4522createFromParcel(Parcel parcel) {
            return RtcscSide.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscSide[] mo4523newArray(int i) {
            return new RtcscSide[i];
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

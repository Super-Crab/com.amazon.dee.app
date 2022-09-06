package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscMediaType implements Parcelable {
    AUDIO,
    VIDEO,
    DATA;
    
    public static final Parcelable.Creator<RtcscMediaType> CREATOR = new Parcelable.Creator<RtcscMediaType>() { // from class: com.amazon.rtcsc.interfaces.RtcscMediaType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscMediaType mo4516createFromParcel(Parcel parcel) {
            return RtcscMediaType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscMediaType[] mo4517newArray(int i) {
            return new RtcscMediaType[i];
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

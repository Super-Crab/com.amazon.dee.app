package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscScalingType implements Parcelable {
    SCALE_ASPECT_FIT,
    SCALE_ASPECT_FILL,
    SCALE_ASPECT_BALANCED;
    
    public static final Parcelable.Creator<RtcscScalingType> CREATOR = new Parcelable.Creator<RtcscScalingType>() { // from class: com.amazon.rtcsc.interfaces.RtcscScalingType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscScalingType mo4518createFromParcel(Parcel parcel) {
            return RtcscScalingType.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscScalingType[] mo4519newArray(int i) {
            return new RtcscScalingType[i];
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

package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscVideoEffect implements Parcelable {
    NO_EFFECT,
    FROST_EFFECT,
    UNKNOWN_EFFECT;
    
    public static final Parcelable.Creator<RtcscVideoEffect> CREATOR = new Parcelable.Creator<RtcscVideoEffect>() { // from class: com.amazon.rtcsc.interfaces.RtcscVideoEffect.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscVideoEffect mo4524createFromParcel(Parcel parcel) {
            try {
                return RtcscVideoEffect.valueOf(parcel.readString());
            } catch (IllegalArgumentException unused) {
                return RtcscVideoEffect.UNKNOWN_EFFECT;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscVideoEffect[] mo4525newArray(int i) {
            return new RtcscVideoEffect[i];
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

package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscSessionState implements Parcelable {
    SESSION_STATE_UNKNOWN,
    SESSION_PREPARING,
    SESSION_ACTIVE,
    SESSION_PAUSED,
    SESSION_FINISHING;
    
    public static final Parcelable.Creator<RtcscSessionState> CREATOR = new Parcelable.Creator<RtcscSessionState>() { // from class: com.amazon.rtcsc.interfaces.RtcscSessionState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscSessionState mo4520createFromParcel(Parcel parcel) {
            try {
                return RtcscSessionState.valueOf(parcel.readString());
            } catch (IllegalArgumentException unused) {
                return RtcscSessionState.SESSION_STATE_UNKNOWN;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscSessionState[] mo4521newArray(int i) {
            return new RtcscSessionState[i];
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

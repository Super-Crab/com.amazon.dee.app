package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscDataChannelState implements Parcelable {
    CONNECTING,
    OPEN,
    CLOSING,
    CLOSED;
    
    public static final Parcelable.Creator<RtcscDataChannelState> CREATOR = new Parcelable.Creator<RtcscDataChannelState>() { // from class: com.amazon.rtcsc.interfaces.RtcscDataChannelState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscDataChannelState mo4510createFromParcel(Parcel parcel) {
            return RtcscDataChannelState.valueOf(parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscDataChannelState[] mo4511newArray(int i) {
            return new RtcscDataChannelState[i];
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

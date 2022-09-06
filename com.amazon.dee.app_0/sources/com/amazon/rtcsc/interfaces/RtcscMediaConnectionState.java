package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscMediaConnectionState implements Parcelable {
    MEDIA_CONNECTING,
    MEDIA_CONNECTED,
    MEDIA_DISCONNECTED,
    UNKNOWN_MEDIA_CONNECTION_STATE;
    
    public static final Parcelable.Creator<RtcscMediaConnectionState> CREATOR = new Parcelable.Creator<RtcscMediaConnectionState>() { // from class: com.amazon.rtcsc.interfaces.RtcscMediaConnectionState.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscMediaConnectionState mo4514createFromParcel(Parcel parcel) {
            try {
                return RtcscMediaConnectionState.valueOf(parcel.readString());
            } catch (IllegalArgumentException unused) {
                return RtcscMediaConnectionState.UNKNOWN_MEDIA_CONNECTION_STATE;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscMediaConnectionState[] mo4515newArray(int i) {
            return new RtcscMediaConnectionState[i];
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

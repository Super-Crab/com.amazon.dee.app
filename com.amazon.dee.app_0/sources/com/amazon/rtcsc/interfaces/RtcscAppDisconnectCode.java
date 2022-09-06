package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscAppDisconnectCode implements Parcelable {
    USER_TERMINATED_SESSION,
    USER_DECLINED_SESSION,
    APPCLIENT_NOT_FOUND,
    APPCLIENT_START_FAILED,
    PERMISSION_CHECKS_FAILED,
    DEVICE_UNAVAILABLE,
    CAMERA_UNAVAILABLE,
    HIGHER_PRIORITY_SESSION_ONGOING,
    HIGHER_PRIORITY_SESSION_INTERRUPTED,
    SESSION_DOMAIN_ERROR;
    
    public static final Parcelable.Creator<RtcscAppDisconnectCode> CREATOR = new Parcelable.Creator<RtcscAppDisconnectCode>() { // from class: com.amazon.rtcsc.interfaces.RtcscAppDisconnectCode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscAppDisconnectCode mo4498createFromParcel(Parcel parcel) {
            try {
                return RtcscAppDisconnectCode.valueOf(parcel.readString());
            } catch (IllegalArgumentException unused) {
                return RtcscAppDisconnectCode.PERMISSION_CHECKS_FAILED;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscAppDisconnectCode[] mo4499newArray(int i) {
            return new RtcscAppDisconnectCode[i];
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

package com.amazon.rtcsc.interfaces;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes13.dex */
public enum RtcscErrorCode implements Parcelable {
    SUCCESS,
    NOT_INITIALIZED,
    ALREADY_INITIALIZED,
    NULL_LISTENER,
    UNKNOWN_DIRECTIVE,
    DIRECTIVE_PARSE_ERROR,
    INITIALIZE_FAILED,
    LISTENER_REGISTRATION_FAILED,
    SESSION_ID_NOT_FOUND,
    LISTENER_PREVIOUSLY_REGISTERED,
    LISTENER_NOT_FOUND,
    NULL_METRICS_PUBLISHER_ADAPTER,
    METRICS_PUBLISHER_ADAPTER_PREVIOUSLY_REGISTERED,
    EMPTY_APP_IDENTIFIER,
    INVALID_DEVICE_CONFIGURATION,
    INTERNAL_SESSION_ERROR,
    CAMERA_NOT_AVAILABLE,
    MEDIA_CONNECTION_FAILED,
    MEDIA_CONNECTION_LOST,
    SESSION_TIMEOUT,
    VIDEO_EFFECT_FAILED,
    REMOTE_ERROR,
    NULL_CLIENT_HANDLER,
    EMPTY_SESSION_ID,
    API_NOT_AVAILABLE;
    
    public static final Parcelable.Creator<RtcscErrorCode> CREATOR = new Parcelable.Creator<RtcscErrorCode>() { // from class: com.amazon.rtcsc.interfaces.RtcscErrorCode.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public RtcscErrorCode mo4512createFromParcel(Parcel parcel) {
            try {
                return RtcscErrorCode.valueOf(parcel.readString());
            } catch (IllegalArgumentException unused) {
                return RtcscErrorCode.INTERNAL_SESSION_ERROR;
            }
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public RtcscErrorCode[] mo4513newArray(int i) {
            return new RtcscErrorCode[i];
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

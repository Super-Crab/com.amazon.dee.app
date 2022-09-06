package com.amazon.comms.calling.service;
/* loaded from: classes11.dex */
public enum ErrorCode {
    ClientFailed(400),
    ServerError(500),
    Unknown(900),
    RegistrarUnreachable(901),
    LostConnection(902),
    ConfigurationChanged(903),
    SIPInternalError(904),
    RegistrationNotFound(905),
    RegistrationFailed(906),
    MediaConnectionFailed(908);
    
    private final int value;

    ErrorCode(int i) {
        this.value = i;
    }

    public static boolean isCallError(ErrorCode errorCode) {
        return errorCode == Unknown || errorCode == RegistrationNotFound || isMediaError(errorCode);
    }

    public static boolean isMediaError(ErrorCode errorCode) {
        return errorCode == LostConnection || errorCode == MediaConnectionFailed;
    }

    public int getValue() {
        return this.value;
    }
}

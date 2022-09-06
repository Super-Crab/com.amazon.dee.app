package com.amazon.alexa.location.networking.als.models;
/* loaded from: classes9.dex */
public enum Code {
    A101("APP_ERROR"),
    A102("INVALID_PERMISSIONS"),
    A103("OS_GEOFENCE_LIMIT_REACHED"),
    A200("SUCCESS"),
    S201("CONNECTION_ERROR"),
    S202("TIMEOUT");
    
    String errorCode;

    Code(String str) {
        this.errorCode = str;
    }

    public String getValue() {
        return this.errorCode;
    }
}

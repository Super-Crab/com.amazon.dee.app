package com.amazon.alexa.location.networking;
/* loaded from: classes9.dex */
public class LocationException extends Exception {
    private LocationErrorCode errorCode;

    public LocationException(LocationErrorCode locationErrorCode, String str) {
        this(locationErrorCode, str, null);
    }

    public LocationErrorCode getErrorCode() {
        return this.errorCode;
    }

    public String getErrorCodeAsString() {
        return String.valueOf(this.errorCode.getValue());
    }

    public LocationException(LocationErrorCode locationErrorCode, String str, Throwable th) {
        super(str, th);
        this.errorCode = locationErrorCode;
    }
}

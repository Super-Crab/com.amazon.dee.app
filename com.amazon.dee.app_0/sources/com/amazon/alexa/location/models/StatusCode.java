package com.amazon.alexa.location.models;
/* loaded from: classes9.dex */
public class StatusCode {
    private Code code;
    private String message;

    public StatusCode(String str, Code code) {
        this.message = str;
        this.code = code;
    }

    public boolean equals(Object obj) {
        Code code;
        Code code2;
        if (obj == this) {
            return true;
        }
        if (obj == null || !(obj instanceof GeoFenceStatus)) {
            return false;
        }
        StatusCode statusCode = (StatusCode) obj;
        String str = this.message;
        String str2 = statusCode.message;
        return (str == str2 || str.equals(str2)) && ((code = this.code) == (code2 = statusCode.code) || code.equals(code2));
    }

    public Code getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String str = this.message;
        int i = 0;
        int hashCode = ((str != null ? str.hashCode() : 0) + 31) * 31;
        Code code = this.code;
        if (code != null) {
            i = code.hashCode();
        }
        return hashCode + i;
    }
}

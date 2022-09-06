package com.amazon.rtcsc.android.typedapi.types;

import lombok.NonNull;
/* loaded from: classes13.dex */
public class Error {
    @NonNull
    private String code;
    @NonNull
    private String message;

    public Error(@NonNull String str, @NonNull String str2) {
        if (str != null) {
            if (str2 == null) {
                throw new NullPointerException("message");
            }
            this.code = str;
            this.message = str2;
            return;
        }
        throw new NullPointerException("code");
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Error;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Error)) {
            return false;
        }
        Error error = (Error) obj;
        if (!error.canEqual(this)) {
            return false;
        }
        String code = getCode();
        String code2 = error.getCode();
        if (code != null ? !code.equals(code2) : code2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = error.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    @NonNull
    public String getCode() {
        return this.code;
    }

    @NonNull
    public String getMessage() {
        return this.message;
    }

    public int hashCode() {
        String code = getCode();
        int i = 43;
        int hashCode = code == null ? 43 : code.hashCode();
        String message = getMessage();
        int i2 = (hashCode + 59) * 59;
        if (message != null) {
            i = message.hashCode();
        }
        return i2 + i;
    }
}

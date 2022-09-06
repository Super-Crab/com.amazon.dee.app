package com.google.i18n.phonenumbers;

import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes3.dex */
public class NumberParseException extends Exception {
    private ErrorType errorType;
    private String message;

    /* loaded from: classes3.dex */
    public enum ErrorType {
        INVALID_COUNTRY_CODE,
        NOT_A_NUMBER,
        TOO_SHORT_AFTER_IDD,
        TOO_SHORT_NSN,
        TOO_LONG
    }

    public NumberParseException(ErrorType errorType, String str) {
        super(str);
        this.message = str;
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    @Override // java.lang.Throwable
    public String toString() {
        String valueOf = String.valueOf(this.errorType);
        String valueOf2 = String.valueOf(this.message);
        return GeneratedOutlineSupport1.outline93(new StringBuilder(valueOf2.length() + valueOf.length() + 14), "Error type: ", valueOf, ". ", valueOf2);
    }
}

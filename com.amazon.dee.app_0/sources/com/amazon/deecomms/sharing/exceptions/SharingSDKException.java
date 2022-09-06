package com.amazon.deecomms.sharing.exceptions;
/* loaded from: classes12.dex */
public class SharingSDKException extends Exception {
    final String code;
    final String message;

    public SharingSDKException(String str, String str2) {
        super(str);
        this.message = str;
        this.code = str2;
    }

    public String getCode() {
        return this.code;
    }

    public String getErrorMessage() {
        return this.message;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    public SharingSDKException(String str) {
        super(str);
        this.message = str;
        this.code = str;
    }
}

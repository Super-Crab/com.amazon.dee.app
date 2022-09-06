package com.amazon.deecomms.sharing.exceptions;
/* loaded from: classes12.dex */
public class InvalidCommsUserSDKException extends SharingSDKException {
    static final String CODE = "2012";
    final String reason;

    public InvalidCommsUserSDKException(String str) {
        super(str);
        this.reason = str;
    }

    @Override // com.amazon.deecomms.sharing.exceptions.SharingSDKException
    public String getCode() {
        return CODE;
    }

    @Override // com.amazon.deecomms.sharing.exceptions.SharingSDKException
    public String getErrorMessage() {
        return this.reason;
    }

    @Override // com.amazon.deecomms.sharing.exceptions.SharingSDKException, java.lang.Throwable
    public String getMessage() {
        return CODE;
    }
}

package com.amazon.alexa.sharing.api.exceptions;

import java.util.HashMap;
/* loaded from: classes10.dex */
public class AlexaSharingSDKException extends AlexaSharingException {
    static final String TYPE = "SharingSDKException";
    final String code;
    final String message;

    public AlexaSharingSDKException(String str, String str2) {
        super(str);
        this.message = str;
        this.code = str2;
    }

    @Override // com.amazon.alexa.sharing.api.exceptions.AlexaSharingException
    public String getCode() {
        return this.code;
    }

    @Override // com.amazon.alexa.sharing.api.exceptions.AlexaSharingException
    public String getErrorMessage() {
        return this.message;
    }

    @Override // com.amazon.alexa.sharing.api.exceptions.AlexaSharingException, java.lang.Throwable
    public String getMessage() {
        return this.message;
    }

    @Override // com.amazon.alexa.sharing.api.exceptions.AlexaSharingException
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("code", this.code);
        hashMap.put("message", this.message);
        hashMap.put("type", TYPE);
        return hashMap;
    }

    public AlexaSharingSDKException(String str) {
        super(str);
        this.message = str;
        this.code = str;
    }
}

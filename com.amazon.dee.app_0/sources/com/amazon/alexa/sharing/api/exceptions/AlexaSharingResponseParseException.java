package com.amazon.alexa.sharing.api.exceptions;

import androidx.annotation.NonNull;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class AlexaSharingResponseParseException extends AlexaSharingException {
    static final String TYPE = "ResponseParseException";
    @NonNull
    final String endpointKey;
    @NonNull
    final String requestId;
    @NonNull
    final String type;

    public AlexaSharingResponseParseException(@NonNull BridgeError bridgeError, @NonNull String str, @NonNull String str2) {
        super(bridgeError.message(), bridgeError.code());
        this.endpointKey = str;
        this.requestId = str2;
        this.type = TYPE;
    }

    @NonNull
    public String getEndpointKey() {
        return this.endpointKey;
    }

    @Override // com.amazon.alexa.sharing.api.exceptions.AlexaSharingException
    public HashMap<String, String> getMetadata() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", TYPE);
        hashMap.put("message", getErrorMessage());
        hashMap.put("code", getCode());
        hashMap.put("endpointKey", getEndpointKey());
        hashMap.put("requestId", getRequestId());
        return hashMap;
    }

    @NonNull
    public String getRequestId() {
        return this.requestId;
    }

    public AlexaSharingResponseParseException(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull String str4) {
        super(str, str2);
        this.endpointKey = str3;
        this.requestId = str4;
        this.type = TYPE;
    }
}

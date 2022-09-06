package com.amazon.alexa.sharing.api.exceptions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commsnetworking.api.INetworkResponse;
import java.util.HashMap;
/* loaded from: classes10.dex */
public class AlexaSharingNetworkException extends AlexaSharingException {
    static final String TYPE = "NetworkException";
    @NonNull
    final String endpointKey;
    @NonNull
    final String requestId;
    @Nullable
    final transient INetworkResponse response;
    @NonNull
    final String responseCode;
    @Nullable
    final String responseId;
    @NonNull
    final String type;

    public AlexaSharingNetworkException(@NonNull String str, @NonNull String str2, @NonNull String str3, @NonNull INetworkResponse iNetworkResponse) {
        super(str, str2);
        this.endpointKey = str3;
        this.requestId = iNetworkResponse.getAmazonRequestID();
        this.responseId = iNetworkResponse.getAmazonRID();
        this.response = iNetworkResponse;
        this.responseCode = Integer.toString(iNetworkResponse.getStatusCode());
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
        hashMap.put("responseId", getResponseId());
        hashMap.put("responseCode", getResponseCode());
        return hashMap;
    }

    @NonNull
    public String getRequestId() {
        return this.requestId;
    }

    @Nullable
    public INetworkResponse getResponse() {
        return this.response;
    }

    @Nullable
    public String getResponseCode() {
        return this.responseCode;
    }

    @Nullable
    public String getResponseId() {
        return this.responseId;
    }
}

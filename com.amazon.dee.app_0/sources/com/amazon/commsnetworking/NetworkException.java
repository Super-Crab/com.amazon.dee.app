package com.amazon.commsnetworking;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.commsnetworking.api.INetworkRequest;
import com.amazon.commsnetworking.api.INetworkResponse;
/* loaded from: classes12.dex */
public class NetworkException extends Exception {
    @NonNull
    private INetworkRequest request;
    @Nullable
    private INetworkResponse response;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public NetworkException(@androidx.annotation.NonNull com.amazon.commsnetworking.api.INetworkRequest r3, @androidx.annotation.NonNull com.amazon.commsnetworking.api.INetworkResponse r4) {
        /*
            r2 = this;
            java.lang.String r0 = "Unexpected code "
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            int r1 = r4.getStatusCode()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            r2.request = r3
            r2.response = r4
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.commsnetworking.NetworkException.<init>(com.amazon.commsnetworking.api.INetworkRequest, com.amazon.commsnetworking.api.INetworkResponse):void");
    }

    public INetworkRequest getRequest() {
        return this.request;
    }

    public INetworkResponse getResponse() {
        return this.response;
    }

    public Integer getStatusCode() {
        INetworkResponse iNetworkResponse = this.response;
        if (iNetworkResponse == null) {
            return null;
        }
        return Integer.valueOf(iNetworkResponse.getStatusCode());
    }

    public NetworkException(@NonNull INetworkRequest iNetworkRequest, @NonNull Exception exc) {
        super(exc);
        this.request = iNetworkRequest;
    }
}

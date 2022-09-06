package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ValidateWifiSyncAuthTokenOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.ValidateWifiSyncAuthTokenOutput");
    private String endpointToUse;
    private Boolean validToken;

    public boolean equals(Object obj) {
        if (!(obj instanceof ValidateWifiSyncAuthTokenOutput)) {
            return false;
        }
        ValidateWifiSyncAuthTokenOutput validateWifiSyncAuthTokenOutput = (ValidateWifiSyncAuthTokenOutput) obj;
        return Helper.equals(this.validToken, validateWifiSyncAuthTokenOutput.validToken) && Helper.equals(this.endpointToUse, validateWifiSyncAuthTokenOutput.endpointToUse);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.validToken, this.endpointToUse);
    }

    public Boolean isValidToken() {
        return this.validToken;
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setValidToken(Boolean bool) {
        this.validToken = bool;
    }
}

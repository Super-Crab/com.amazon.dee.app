package com.amazon.devicesetupservice.pwsync.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetWifiSyncAuthTokenOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.pwsync.v1.GetWifiSyncAuthTokenOutput");
    private String endpointToUse;
    private String wifiSyncAuthToken;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiSyncAuthTokenOutput)) {
            return false;
        }
        GetWifiSyncAuthTokenOutput getWifiSyncAuthTokenOutput = (GetWifiSyncAuthTokenOutput) obj;
        return Helper.equals(this.endpointToUse, getWifiSyncAuthTokenOutput.endpointToUse) && Helper.equals(this.wifiSyncAuthToken, getWifiSyncAuthTokenOutput.wifiSyncAuthToken);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public String getWifiSyncAuthToken() {
        return this.wifiSyncAuthToken;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.endpointToUse, this.wifiSyncAuthToken);
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setWifiSyncAuthToken(String str) {
        this.wifiSyncAuthToken = str;
    }
}

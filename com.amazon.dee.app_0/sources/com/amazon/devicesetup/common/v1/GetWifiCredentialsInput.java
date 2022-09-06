package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetWifiCredentialsInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.GetWifiCredentialsInput");
    private DeviceDetails deviceDetails;
    private String nonce;
    private int sequenceNumber;
    private String sessionId;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiCredentialsInput)) {
            return false;
        }
        GetWifiCredentialsInput getWifiCredentialsInput = (GetWifiCredentialsInput) obj;
        return Helper.equals(this.nonce, getWifiCredentialsInput.nonce) && Helper.equals(this.sessionId, getWifiCredentialsInput.sessionId) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(getWifiCredentialsInput.sequenceNumber)) && Helper.equals(this.deviceDetails, getWifiCredentialsInput.deviceDetails);
    }

    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    public String getNonce() {
        return this.nonce;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.nonce, this.sessionId, Integer.valueOf(this.sequenceNumber), this.deviceDetails);
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}

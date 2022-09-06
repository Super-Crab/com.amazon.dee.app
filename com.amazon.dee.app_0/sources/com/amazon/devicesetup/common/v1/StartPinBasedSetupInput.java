package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartPinBasedSetupInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.StartPinBasedSetupInput");
    private DeviceDetails deviceDetails;
    private String hashedPin;
    private String nonce;
    private String sessionId;

    public boolean equals(Object obj) {
        if (!(obj instanceof StartPinBasedSetupInput)) {
            return false;
        }
        StartPinBasedSetupInput startPinBasedSetupInput = (StartPinBasedSetupInput) obj;
        return Helper.equals(this.nonce, startPinBasedSetupInput.nonce) && Helper.equals(this.deviceDetails, startPinBasedSetupInput.deviceDetails) && Helper.equals(this.sessionId, startPinBasedSetupInput.sessionId) && Helper.equals(this.hashedPin, startPinBasedSetupInput.hashedPin);
    }

    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    public String getHashedPin() {
        return this.hashedPin;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.nonce, this.deviceDetails, this.sessionId, this.hashedPin);
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public void setHashedPin(String str) {
        this.hashedPin = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }
}

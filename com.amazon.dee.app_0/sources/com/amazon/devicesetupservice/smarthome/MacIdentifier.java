package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class MacIdentifier implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.MacIdentifier");
    private String macAddress;
    private String protocolType;

    public boolean equals(Object obj) {
        if (!(obj instanceof MacIdentifier)) {
            return false;
        }
        MacIdentifier macIdentifier = (MacIdentifier) obj;
        return Helper.equals(this.protocolType, macIdentifier.protocolType) && Helper.equals(this.macAddress, macIdentifier.macAddress);
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.protocolType, this.macAddress);
    }

    public void setMacAddress(String str) {
        this.macAddress = str;
    }

    public void setProtocolType(String str) {
        this.protocolType = str;
    }
}

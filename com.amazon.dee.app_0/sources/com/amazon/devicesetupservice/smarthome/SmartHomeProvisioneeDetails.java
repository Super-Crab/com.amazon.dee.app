package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.ProvisioneeDetails;
/* loaded from: classes12.dex */
public class SmartHomeProvisioneeDetails extends ProvisioneeDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.SmartHomeProvisioneeDetails");
    private String mac;
    private int rssi;

    @Override // com.amazon.devicesetupservice.ProvisioneeDetails
    public boolean equals(Object obj) {
        if (!(obj instanceof SmartHomeProvisioneeDetails)) {
            return false;
        }
        SmartHomeProvisioneeDetails smartHomeProvisioneeDetails = (SmartHomeProvisioneeDetails) obj;
        return super.equals(obj) && Helper.equals(this.mac, smartHomeProvisioneeDetails.mac) && Helper.equals(Integer.valueOf(this.rssi), Integer.valueOf(smartHomeProvisioneeDetails.rssi));
    }

    public String getMac() {
        return this.mac;
    }

    public int getRssi() {
        return this.rssi;
    }

    @Override // com.amazon.devicesetupservice.ProvisioneeDetails
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.mac, Integer.valueOf(this.rssi));
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }
}

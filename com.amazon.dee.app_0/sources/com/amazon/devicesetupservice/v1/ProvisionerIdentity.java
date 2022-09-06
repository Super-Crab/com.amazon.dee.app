package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ProvisionerIdentity implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ProvisionerIdentity");
    private String customerId;
    private String deviceSerial;
    private String deviceType;

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisionerIdentity)) {
            return false;
        }
        ProvisionerIdentity provisionerIdentity = (ProvisionerIdentity) obj;
        return Helper.equals(this.deviceType, provisionerIdentity.deviceType) && Helper.equals(this.deviceSerial, provisionerIdentity.deviceSerial) && Helper.equals(this.customerId, provisionerIdentity.customerId);
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getDeviceSerial() {
        return this.deviceSerial;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.deviceType, this.deviceSerial, this.customerId);
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setDeviceSerial(String str) {
        this.deviceSerial = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }
}

package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ProvisionerNetwork implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.ProvisionerNetwork");
    private String securityProtocol;
    private String ssid;

    public boolean equals(Object obj) {
        if (!(obj instanceof ProvisionerNetwork)) {
            return false;
        }
        ProvisionerNetwork provisionerNetwork = (ProvisionerNetwork) obj;
        return Helper.equals(this.securityProtocol, provisionerNetwork.securityProtocol) && Helper.equals(this.ssid, provisionerNetwork.ssid);
    }

    public String getSecurityProtocol() {
        return this.securityProtocol;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.securityProtocol, this.ssid);
    }

    public void setSecurityProtocol(String str) {
        this.securityProtocol = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }
}

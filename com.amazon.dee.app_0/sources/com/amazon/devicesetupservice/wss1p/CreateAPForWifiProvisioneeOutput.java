package com.amazon.devicesetupservice.wss1p;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class CreateAPForWifiProvisioneeOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wss1p.CreateAPForWifiProvisioneeOutput");
    private String blacklistTimeout;
    private boolean canProceed;
    private String deviceId;
    private String endpointToUse;
    private List<String> hostPortList;
    private String passphrase;
    private String ssid;
    private String timeout;

    public boolean equals(Object obj) {
        if (!(obj instanceof CreateAPForWifiProvisioneeOutput)) {
            return false;
        }
        CreateAPForWifiProvisioneeOutput createAPForWifiProvisioneeOutput = (CreateAPForWifiProvisioneeOutput) obj;
        return Helper.equals(this.ssid, createAPForWifiProvisioneeOutput.ssid) && Helper.equals(this.hostPortList, createAPForWifiProvisioneeOutput.hostPortList) && Helper.equals(this.endpointToUse, createAPForWifiProvisioneeOutput.endpointToUse) && Helper.equals(this.deviceId, createAPForWifiProvisioneeOutput.deviceId) && Helper.equals(this.passphrase, createAPForWifiProvisioneeOutput.passphrase) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(createAPForWifiProvisioneeOutput.canProceed)) && Helper.equals(this.timeout, createAPForWifiProvisioneeOutput.timeout) && Helper.equals(this.blacklistTimeout, createAPForWifiProvisioneeOutput.blacklistTimeout);
    }

    public String getBlacklistTimeout() {
        return this.blacklistTimeout;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public List<String> getHostPortList() {
        return this.hostPortList;
    }

    public String getPassphrase() {
        return this.passphrase;
    }

    public String getSsid() {
        return this.ssid;
    }

    public String getTimeout() {
        return this.timeout;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.ssid, this.hostPortList, this.endpointToUse, this.deviceId, this.passphrase, Boolean.valueOf(this.canProceed), this.timeout, this.blacklistTimeout);
    }

    public boolean isCanProceed() {
        return this.canProceed;
    }

    public void setBlacklistTimeout(String str) {
        this.blacklistTimeout = str;
    }

    public void setCanProceed(boolean z) {
        this.canProceed = z;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setHostPortList(List<String> list) {
        this.hostPortList = list;
    }

    public void setPassphrase(String str) {
        this.passphrase = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public void setTimeout(String str) {
        this.timeout = str;
    }
}

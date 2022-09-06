package com.amazon.devicesetupservice.wss1p;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.v1.AuthenticatedInput;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
/* loaded from: classes12.dex */
public class CreateAPForWifiProvisioneeInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wss1p.CreateAPForWifiProvisioneeInput");
    private int activeConnectionsCount;
    private DiscoveredDevice discoveredDevice;
    private ProvisionerInfo provisionerInfo;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof CreateAPForWifiProvisioneeInput)) {
            return false;
        }
        CreateAPForWifiProvisioneeInput createAPForWifiProvisioneeInput = (CreateAPForWifiProvisioneeInput) obj;
        return super.equals(obj) && Helper.equals(Integer.valueOf(this.activeConnectionsCount), Integer.valueOf(createAPForWifiProvisioneeInput.activeConnectionsCount)) && Helper.equals(this.discoveredDevice, createAPForWifiProvisioneeInput.discoveredDevice) && Helper.equals(this.provisionerInfo, createAPForWifiProvisioneeInput.provisionerInfo);
    }

    public int getActiveConnectionsCount() {
        return this.activeConnectionsCount;
    }

    public DiscoveredDevice getDiscoveredDevice() {
        return this.discoveredDevice;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.provisionerInfo;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Integer.valueOf(this.activeConnectionsCount), this.discoveredDevice, this.provisionerInfo);
    }

    public void setActiveConnectionsCount(int i) {
        this.activeConnectionsCount = i;
    }

    public void setDiscoveredDevice(DiscoveredDevice discoveredDevice) {
        this.discoveredDevice = discoveredDevice;
    }

    public void setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.provisionerInfo = provisionerInfo;
    }
}

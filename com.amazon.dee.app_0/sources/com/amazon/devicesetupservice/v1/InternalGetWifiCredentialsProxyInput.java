package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalGetWifiCredentialsProxyInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalGetWifiCredentialsProxyInput");
    private GetWifiCredentialsProxyInputData getWifiCredentialsProxyInputData;
    private ProvisionerIdentity provisionerIdentity;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalGetWifiCredentialsProxyInput)) {
            return false;
        }
        InternalGetWifiCredentialsProxyInput internalGetWifiCredentialsProxyInput = (InternalGetWifiCredentialsProxyInput) obj;
        return Helper.equals(this.provisionerIdentity, internalGetWifiCredentialsProxyInput.provisionerIdentity) && Helper.equals(this.getWifiCredentialsProxyInputData, internalGetWifiCredentialsProxyInput.getWifiCredentialsProxyInputData);
    }

    public GetWifiCredentialsProxyInputData getGetWifiCredentialsProxyInputData() {
        return this.getWifiCredentialsProxyInputData;
    }

    public ProvisionerIdentity getProvisionerIdentity() {
        return this.provisionerIdentity;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.provisionerIdentity, this.getWifiCredentialsProxyInputData);
    }

    public void setGetWifiCredentialsProxyInputData(GetWifiCredentialsProxyInputData getWifiCredentialsProxyInputData) {
        this.getWifiCredentialsProxyInputData = getWifiCredentialsProxyInputData;
    }

    public void setProvisionerIdentity(ProvisionerIdentity provisionerIdentity) {
        this.provisionerIdentity = provisionerIdentity;
    }
}

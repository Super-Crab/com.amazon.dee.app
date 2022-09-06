package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.v1.AuthenticatedInput;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
import java.util.List;
/* loaded from: classes12.dex */
public class GetCustomerDevicesCredentialsInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsInput");
    private List<CredentialRequest> credentialRequests;
    private ProvisionerInfo provisionerInfo;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof GetCustomerDevicesCredentialsInput)) {
            return false;
        }
        GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput = (GetCustomerDevicesCredentialsInput) obj;
        return super.equals(obj) && Helper.equals(this.provisionerInfo, getCustomerDevicesCredentialsInput.provisionerInfo) && Helper.equals(this.credentialRequests, getCustomerDevicesCredentialsInput.credentialRequests);
    }

    public List<CredentialRequest> getCredentialRequests() {
        return this.credentialRequests;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.provisionerInfo;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.provisionerInfo, this.credentialRequests);
    }

    public void setCredentialRequests(List<CredentialRequest> list) {
        this.credentialRequests = list;
    }

    public void setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.provisionerInfo = provisionerInfo;
    }
}

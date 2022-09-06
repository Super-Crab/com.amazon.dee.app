package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class CredentialRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.CredentialRequest");
    private List<String> authMaterialIdList;
    private ChipProvisioneeDetails chipProvisioneeDetails;
    private String protocolType;

    public boolean equals(Object obj) {
        if (!(obj instanceof CredentialRequest)) {
            return false;
        }
        CredentialRequest credentialRequest = (CredentialRequest) obj;
        return Helper.equals(this.chipProvisioneeDetails, credentialRequest.chipProvisioneeDetails) && Helper.equals(this.authMaterialIdList, credentialRequest.authMaterialIdList) && Helper.equals(this.protocolType, credentialRequest.protocolType);
    }

    public List<String> getAuthMaterialIdList() {
        return this.authMaterialIdList;
    }

    public ChipProvisioneeDetails getChipProvisioneeDetails() {
        return this.chipProvisioneeDetails;
    }

    public String getProtocolType() {
        return this.protocolType;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.chipProvisioneeDetails, this.authMaterialIdList, this.protocolType);
    }

    public void setAuthMaterialIdList(List<String> list) {
        this.authMaterialIdList = list;
    }

    public void setChipProvisioneeDetails(ChipProvisioneeDetails chipProvisioneeDetails) {
        this.chipProvisioneeDetails = chipProvisioneeDetails;
    }

    public void setProtocolType(String str) {
        this.protocolType = str;
    }
}

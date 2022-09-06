package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class GetCustomerProvisioneesSetupStatusOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetCustomerProvisioneesSetupStatusOutput");
    private String endpointToUse;
    private List<ProvisioneeSetupInformation> provisioneeSetupInformationList;
    private String searchIntervalUsed;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetCustomerProvisioneesSetupStatusOutput)) {
            return false;
        }
        GetCustomerProvisioneesSetupStatusOutput getCustomerProvisioneesSetupStatusOutput = (GetCustomerProvisioneesSetupStatusOutput) obj;
        return Helper.equals(this.searchIntervalUsed, getCustomerProvisioneesSetupStatusOutput.searchIntervalUsed) && Helper.equals(this.endpointToUse, getCustomerProvisioneesSetupStatusOutput.endpointToUse) && Helper.equals(this.provisioneeSetupInformationList, getCustomerProvisioneesSetupStatusOutput.provisioneeSetupInformationList);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public List<ProvisioneeSetupInformation> getProvisioneeSetupInformationList() {
        return this.provisioneeSetupInformationList;
    }

    public String getSearchIntervalUsed() {
        return this.searchIntervalUsed;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.searchIntervalUsed, this.endpointToUse, this.provisioneeSetupInformationList);
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setProvisioneeSetupInformationList(List<ProvisioneeSetupInformation> list) {
        this.provisioneeSetupInformationList = list;
    }

    public void setSearchIntervalUsed(String str) {
        this.searchIntervalUsed = str;
    }
}

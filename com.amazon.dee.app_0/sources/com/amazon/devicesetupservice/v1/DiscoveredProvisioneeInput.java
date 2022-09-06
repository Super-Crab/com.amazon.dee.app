package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryInputParameters;
import com.amazon.devicesetupservice.ProvisioneeDetails;
import com.amazon.devicesetupservice.ProvisionerDetails;
/* loaded from: classes12.dex */
public class DiscoveredProvisioneeInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.DiscoveredProvisioneeInput");
    private String accessToken;
    private DiscoveryInputParameters discoveryInputParameters;
    private ProvisioneeDetails provisioneeDetails;
    private ProvisionerDetails provisionerDetails;

    public boolean equals(Object obj) {
        if (!(obj instanceof DiscoveredProvisioneeInput)) {
            return false;
        }
        DiscoveredProvisioneeInput discoveredProvisioneeInput = (DiscoveredProvisioneeInput) obj;
        return Helper.equals(this.provisionerDetails, discoveredProvisioneeInput.provisionerDetails) && Helper.equals(this.discoveryInputParameters, discoveredProvisioneeInput.discoveryInputParameters) && Helper.equals(this.accessToken, discoveredProvisioneeInput.accessToken) && Helper.equals(this.provisioneeDetails, discoveredProvisioneeInput.provisioneeDetails);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public DiscoveryInputParameters getDiscoveryInputParameters() {
        return this.discoveryInputParameters;
    }

    public ProvisioneeDetails getProvisioneeDetails() {
        return this.provisioneeDetails;
    }

    public ProvisionerDetails getProvisionerDetails() {
        return this.provisionerDetails;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.provisionerDetails, this.discoveryInputParameters, this.accessToken, this.provisioneeDetails);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setDiscoveryInputParameters(DiscoveryInputParameters discoveryInputParameters) {
        this.discoveryInputParameters = discoveryInputParameters;
    }

    public void setProvisioneeDetails(ProvisioneeDetails provisioneeDetails) {
        this.provisioneeDetails = provisioneeDetails;
    }

    public void setProvisionerDetails(ProvisionerDetails provisionerDetails) {
        this.provisionerDetails = provisionerDetails;
    }
}

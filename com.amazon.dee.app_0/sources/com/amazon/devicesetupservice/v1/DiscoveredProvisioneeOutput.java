package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetupservice.DiscoveryOutputParameters;
/* loaded from: classes12.dex */
public class DiscoveredProvisioneeOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.DiscoveredProvisioneeOutput");
    private boolean canProceed;
    private DiscoveryOutputParameters discoveryOutputParameters;
    private String endpointToUse;
    private String provisioneeReportUrl;
    private String provisionerReportUrl;
    private String waitTime;

    public boolean equals(Object obj) {
        if (!(obj instanceof DiscoveredProvisioneeOutput)) {
            return false;
        }
        DiscoveredProvisioneeOutput discoveredProvisioneeOutput = (DiscoveredProvisioneeOutput) obj;
        return Helper.equals(this.provisioneeReportUrl, discoveredProvisioneeOutput.provisioneeReportUrl) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(discoveredProvisioneeOutput.canProceed)) && Helper.equals(this.endpointToUse, discoveredProvisioneeOutput.endpointToUse) && Helper.equals(this.discoveryOutputParameters, discoveredProvisioneeOutput.discoveryOutputParameters) && Helper.equals(this.provisionerReportUrl, discoveredProvisioneeOutput.provisionerReportUrl) && Helper.equals(this.waitTime, discoveredProvisioneeOutput.waitTime);
    }

    public DiscoveryOutputParameters getDiscoveryOutputParameters() {
        return this.discoveryOutputParameters;
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public String getProvisioneeReportUrl() {
        return this.provisioneeReportUrl;
    }

    public String getProvisionerReportUrl() {
        return this.provisionerReportUrl;
    }

    public String getWaitTime() {
        return this.waitTime;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.provisioneeReportUrl, Boolean.valueOf(this.canProceed), this.endpointToUse, this.discoveryOutputParameters, this.provisionerReportUrl, this.waitTime);
    }

    public boolean isCanProceed() {
        return this.canProceed;
    }

    public void setCanProceed(boolean z) {
        this.canProceed = z;
    }

    public void setDiscoveryOutputParameters(DiscoveryOutputParameters discoveryOutputParameters) {
        this.discoveryOutputParameters = discoveryOutputParameters;
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setProvisioneeReportUrl(String str) {
        this.provisioneeReportUrl = str;
    }

    public void setProvisionerReportUrl(String str) {
        this.provisionerReportUrl = str;
    }

    public void setWaitTime(String str) {
        this.waitTime = str;
    }
}

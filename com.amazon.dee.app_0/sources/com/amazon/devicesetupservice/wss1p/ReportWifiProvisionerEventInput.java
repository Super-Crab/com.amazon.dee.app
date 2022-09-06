package com.amazon.devicesetupservice.wss1p;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.ErrorInfo;
import com.amazon.devicesetupservice.v1.AuthenticatedInput;
import com.amazon.devicesetupservice.v1.ProvisionerInfo;
/* loaded from: classes12.dex */
public class ReportWifiProvisionerEventInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wss1p.ReportWifiProvisionerEventInput");
    private String accessPointSsid;
    private ErrorInfo errorInfo;
    private String eventType;
    private DiscoveredDevice probeRequest;
    private ProvisionerInfo provisionerInfo;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportWifiProvisionerEventInput)) {
            return false;
        }
        ReportWifiProvisionerEventInput reportWifiProvisionerEventInput = (ReportWifiProvisionerEventInput) obj;
        return super.equals(obj) && Helper.equals(this.provisionerInfo, reportWifiProvisionerEventInput.provisionerInfo) && Helper.equals(this.errorInfo, reportWifiProvisionerEventInput.errorInfo) && Helper.equals(this.accessPointSsid, reportWifiProvisionerEventInput.accessPointSsid) && Helper.equals(this.probeRequest, reportWifiProvisionerEventInput.probeRequest) && Helper.equals(this.eventType, reportWifiProvisionerEventInput.eventType);
    }

    public String getAccessPointSsid() {
        return this.accessPointSsid;
    }

    public ErrorInfo getErrorInfo() {
        return this.errorInfo;
    }

    public String getEventType() {
        return this.eventType;
    }

    public DiscoveredDevice getProbeRequest() {
        return this.probeRequest;
    }

    public ProvisionerInfo getProvisionerInfo() {
        return this.provisionerInfo;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.provisionerInfo, this.errorInfo, this.accessPointSsid, this.probeRequest, this.eventType);
    }

    public void setAccessPointSsid(String str) {
        this.accessPointSsid = str;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }

    public void setEventType(String str) {
        this.eventType = str;
    }

    public void setProbeRequest(DiscoveredDevice discoveredDevice) {
        this.probeRequest = discoveredDevice;
    }

    public void setProvisionerInfo(ProvisionerInfo provisionerInfo) {
        this.provisionerInfo = provisionerInfo;
    }
}

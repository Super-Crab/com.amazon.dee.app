package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class ReportInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.ReportInput");
    private String currentProvisioningState;
    private DeviceDetails deviceDetails;
    private String nonce;
    private String registrationState;
    private int sequenceNumber;
    private String sessionId;
    private String stateTransitionResult;
    private List<WifiReportData> wifiNetworkInfoList;

    public boolean equals(Object obj) {
        if (!(obj instanceof ReportInput)) {
            return false;
        }
        ReportInput reportInput = (ReportInput) obj;
        return Helper.equals(this.nonce, reportInput.nonce) && Helper.equals(this.registrationState, reportInput.registrationState) && Helper.equals(this.stateTransitionResult, reportInput.stateTransitionResult) && Helper.equals(this.deviceDetails, reportInput.deviceDetails) && Helper.equals(this.sessionId, reportInput.sessionId) && Helper.equals(this.currentProvisioningState, reportInput.currentProvisioningState) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(reportInput.sequenceNumber)) && Helper.equals(this.wifiNetworkInfoList, reportInput.wifiNetworkInfoList);
    }

    public String getCurrentProvisioningState() {
        return this.currentProvisioningState;
    }

    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getRegistrationState() {
        return this.registrationState;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getStateTransitionResult() {
        return this.stateTransitionResult;
    }

    public List<WifiReportData> getWifiNetworkInfoList() {
        return this.wifiNetworkInfoList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.nonce, this.registrationState, this.stateTransitionResult, this.deviceDetails, this.sessionId, this.currentProvisioningState, Integer.valueOf(this.sequenceNumber), this.wifiNetworkInfoList);
    }

    public void setCurrentProvisioningState(String str) {
        this.currentProvisioningState = str;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setRegistrationState(String str) {
        this.registrationState = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setStateTransitionResult(String str) {
        this.stateTransitionResult = str;
    }

    public void setWifiNetworkInfoList(List<WifiReportData> list) {
        this.wifiNetworkInfoList = list;
    }
}

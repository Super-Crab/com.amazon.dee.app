package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class WifiReportData implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.WifiReportData");
    private ErrorInfo errorDetails;
    private String securityProtocol;
    private String ssid;
    private String wifiConnectionState;

    public boolean equals(Object obj) {
        if (!(obj instanceof WifiReportData)) {
            return false;
        }
        WifiReportData wifiReportData = (WifiReportData) obj;
        return Helper.equals(this.securityProtocol, wifiReportData.securityProtocol) && Helper.equals(this.wifiConnectionState, wifiReportData.wifiConnectionState) && Helper.equals(this.ssid, wifiReportData.ssid) && Helper.equals(this.errorDetails, wifiReportData.errorDetails);
    }

    public ErrorInfo getErrorDetails() {
        return this.errorDetails;
    }

    public String getSecurityProtocol() {
        return this.securityProtocol;
    }

    public String getSsid() {
        return this.ssid;
    }

    public String getWifiConnectionState() {
        return this.wifiConnectionState;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.securityProtocol, this.wifiConnectionState, this.ssid, this.errorDetails);
    }

    public void setErrorDetails(ErrorInfo errorInfo) {
        this.errorDetails = errorInfo;
    }

    public void setSecurityProtocol(String str) {
        this.securityProtocol = str;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public void setWifiConnectionState(String str) {
        this.wifiConnectionState = str;
    }
}

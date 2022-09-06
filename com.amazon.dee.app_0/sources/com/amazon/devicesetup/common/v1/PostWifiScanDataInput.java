package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class PostWifiScanDataInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.PostWifiScanDataInput");
    private DeviceDetails deviceDetails;
    private String nonce;
    private int sequenceNumber;
    private String sessionId;
    private boolean shouldReturnUnknownScanData;
    private List<WifiScanData> wifiScanDataList;

    public boolean equals(Object obj) {
        if (!(obj instanceof PostWifiScanDataInput)) {
            return false;
        }
        PostWifiScanDataInput postWifiScanDataInput = (PostWifiScanDataInput) obj;
        return Helper.equals(this.nonce, postWifiScanDataInput.nonce) && Helper.equals(this.wifiScanDataList, postWifiScanDataInput.wifiScanDataList) && Helper.equals(this.deviceDetails, postWifiScanDataInput.deviceDetails) && Helper.equals(this.sessionId, postWifiScanDataInput.sessionId) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(postWifiScanDataInput.sequenceNumber)) && Helper.equals(Boolean.valueOf(this.shouldReturnUnknownScanData), Boolean.valueOf(postWifiScanDataInput.shouldReturnUnknownScanData));
    }

    public DeviceDetails getDeviceDetails() {
        return this.deviceDetails;
    }

    public String getNonce() {
        return this.nonce;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public List<WifiScanData> getWifiScanDataList() {
        return this.wifiScanDataList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.nonce, this.wifiScanDataList, this.deviceDetails, this.sessionId, Integer.valueOf(this.sequenceNumber), Boolean.valueOf(this.shouldReturnUnknownScanData));
    }

    public boolean isShouldReturnUnknownScanData() {
        return this.shouldReturnUnknownScanData;
    }

    public void setDeviceDetails(DeviceDetails deviceDetails) {
        this.deviceDetails = deviceDetails;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setShouldReturnUnknownScanData(boolean z) {
        this.shouldReturnUnknownScanData = z;
    }

    public void setWifiScanDataList(List<WifiScanData> list) {
        this.wifiScanDataList = list;
    }
}

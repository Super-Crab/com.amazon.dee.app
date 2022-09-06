package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class PostWifiScanDataOutput extends SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.PostWifiScanDataOutput");
    private boolean allCredentialsFound;
    private boolean canProceed;
    private String nextProvisioningState;
    private String nonce;
    private String reason;
    private int sequenceNumber;
    private String sessionId;
    private int totalCredentialsFound;
    private List<WifiScanData> unknownScanDataList;
    private String waitTime;

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof PostWifiScanDataOutput)) {
            return false;
        }
        PostWifiScanDataOutput postWifiScanDataOutput = (PostWifiScanDataOutput) obj;
        return super.equals(obj) && Helper.equals(this.nonce, postWifiScanDataOutput.nonce) && Helper.equals(Boolean.valueOf(this.allCredentialsFound), Boolean.valueOf(postWifiScanDataOutput.allCredentialsFound)) && Helper.equals(this.nextProvisioningState, postWifiScanDataOutput.nextProvisioningState) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(postWifiScanDataOutput.canProceed)) && Helper.equals(this.unknownScanDataList, postWifiScanDataOutput.unknownScanDataList) && Helper.equals(this.waitTime, postWifiScanDataOutput.waitTime) && Helper.equals(this.reason, postWifiScanDataOutput.reason) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(postWifiScanDataOutput.sequenceNumber)) && Helper.equals(Integer.valueOf(this.totalCredentialsFound), Integer.valueOf(postWifiScanDataOutput.totalCredentialsFound)) && Helper.equals(this.sessionId, postWifiScanDataOutput.sessionId);
    }

    public String getNextProvisioningState() {
        return this.nextProvisioningState;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getReason() {
        return this.reason;
    }

    public int getSequenceNumber() {
        return this.sequenceNumber;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public int getTotalCredentialsFound() {
        return this.totalCredentialsFound;
    }

    public List<WifiScanData> getUnknownScanDataList() {
        return this.unknownScanDataList;
    }

    public String getWaitTime() {
        return this.waitTime;
    }

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.nonce, Boolean.valueOf(this.allCredentialsFound), this.nextProvisioningState, Boolean.valueOf(this.canProceed), this.unknownScanDataList, this.waitTime, this.reason, Integer.valueOf(this.sequenceNumber), Integer.valueOf(this.totalCredentialsFound), this.sessionId);
    }

    public boolean isAllCredentialsFound() {
        return this.allCredentialsFound;
    }

    public boolean isCanProceed() {
        return this.canProceed;
    }

    public void setAllCredentialsFound(boolean z) {
        this.allCredentialsFound = z;
    }

    public void setCanProceed(boolean z) {
        this.canProceed = z;
    }

    public void setNextProvisioningState(String str) {
        this.nextProvisioningState = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public void setSequenceNumber(int i) {
        this.sequenceNumber = i;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setTotalCredentialsFound(int i) {
        this.totalCredentialsFound = i;
    }

    public void setUnknownScanDataList(List<WifiScanData> list) {
        this.unknownScanDataList = list;
    }

    public void setWaitTime(String str) {
        this.waitTime = str;
    }
}

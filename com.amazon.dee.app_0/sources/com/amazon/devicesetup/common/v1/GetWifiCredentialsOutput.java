package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class GetWifiCredentialsOutput extends SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.GetWifiCredentialsOutput");
    private boolean allCredentialsReturned;
    private boolean canProceed;
    private String nextProvisioningState;
    private String nonce;
    private String reason;
    private int sequenceNumber;
    private String sessionId;
    private String waitTime;
    private List<WifiCredentials> wifiCredentialsList;

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof GetWifiCredentialsOutput)) {
            return false;
        }
        GetWifiCredentialsOutput getWifiCredentialsOutput = (GetWifiCredentialsOutput) obj;
        return super.equals(obj) && Helper.equals(this.sessionId, getWifiCredentialsOutput.sessionId) && Helper.equals(Integer.valueOf(this.sequenceNumber), Integer.valueOf(getWifiCredentialsOutput.sequenceNumber)) && Helper.equals(this.wifiCredentialsList, getWifiCredentialsOutput.wifiCredentialsList) && Helper.equals(this.nonce, getWifiCredentialsOutput.nonce) && Helper.equals(this.waitTime, getWifiCredentialsOutput.waitTime) && Helper.equals(Boolean.valueOf(this.allCredentialsReturned), Boolean.valueOf(getWifiCredentialsOutput.allCredentialsReturned)) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(getWifiCredentialsOutput.canProceed)) && Helper.equals(this.reason, getWifiCredentialsOutput.reason) && Helper.equals(this.nextProvisioningState, getWifiCredentialsOutput.nextProvisioningState);
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

    public String getWaitTime() {
        return this.waitTime;
    }

    public List<WifiCredentials> getWifiCredentialsList() {
        return this.wifiCredentialsList;
    }

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.sessionId, Integer.valueOf(this.sequenceNumber), this.wifiCredentialsList, this.nonce, this.waitTime, Boolean.valueOf(this.allCredentialsReturned), Boolean.valueOf(this.canProceed), this.reason, this.nextProvisioningState);
    }

    public boolean isAllCredentialsReturned() {
        return this.allCredentialsReturned;
    }

    public boolean isCanProceed() {
        return this.canProceed;
    }

    public void setAllCredentialsReturned(boolean z) {
        this.allCredentialsReturned = z;
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

    public void setWaitTime(String str) {
        this.waitTime = str;
    }

    public void setWifiCredentialsList(List<WifiCredentials> list) {
        this.wifiCredentialsList = list;
    }
}

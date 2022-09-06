package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartPinBasedSetupOutput extends SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.StartPinBasedSetupOutput");
    private boolean canProceed;
    private String nextProvisioningState;
    private String nonce;
    private String reason;
    private String sessionId;
    private String waitTime;

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof StartPinBasedSetupOutput)) {
            return false;
        }
        StartPinBasedSetupOutput startPinBasedSetupOutput = (StartPinBasedSetupOutput) obj;
        return super.equals(obj) && Helper.equals(this.nextProvisioningState, startPinBasedSetupOutput.nextProvisioningState) && Helper.equals(this.waitTime, startPinBasedSetupOutput.waitTime) && Helper.equals(this.sessionId, startPinBasedSetupOutput.sessionId) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(startPinBasedSetupOutput.canProceed)) && Helper.equals(this.reason, startPinBasedSetupOutput.reason) && Helper.equals(this.nonce, startPinBasedSetupOutput.nonce);
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

    public String getSessionId() {
        return this.sessionId;
    }

    public String getWaitTime() {
        return this.waitTime;
    }

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.nextProvisioningState, this.waitTime, this.sessionId, Boolean.valueOf(this.canProceed), this.reason, this.nonce);
    }

    public boolean isCanProceed() {
        return this.canProceed;
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

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setWaitTime(String str) {
        this.waitTime = str;
    }
}

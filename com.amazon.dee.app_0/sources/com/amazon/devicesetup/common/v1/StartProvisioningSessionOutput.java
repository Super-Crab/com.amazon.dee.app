package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartProvisioningSessionOutput extends SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.StartProvisioningSessionOutput");
    private boolean canProceed;
    private String nextProvisioningState;
    private String nonce;
    private String reason;
    private String salt;
    private String sessionId;
    private String waitTime;

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof StartProvisioningSessionOutput)) {
            return false;
        }
        StartProvisioningSessionOutput startProvisioningSessionOutput = (StartProvisioningSessionOutput) obj;
        return super.equals(obj) && Helper.equals(this.sessionId, startProvisioningSessionOutput.sessionId) && Helper.equals(this.waitTime, startProvisioningSessionOutput.waitTime) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(startProvisioningSessionOutput.canProceed)) && Helper.equals(this.salt, startProvisioningSessionOutput.salt) && Helper.equals(this.nextProvisioningState, startProvisioningSessionOutput.nextProvisioningState) && Helper.equals(this.nonce, startProvisioningSessionOutput.nonce) && Helper.equals(this.reason, startProvisioningSessionOutput.reason);
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

    public String getSalt() {
        return this.salt;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getWaitTime() {
        return this.waitTime;
    }

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.sessionId, this.waitTime, Boolean.valueOf(this.canProceed), this.salt, this.nextProvisioningState, this.nonce, this.reason);
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

    public void setSalt(String str) {
        this.salt = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setWaitTime(String str) {
        this.waitTime = str;
    }
}

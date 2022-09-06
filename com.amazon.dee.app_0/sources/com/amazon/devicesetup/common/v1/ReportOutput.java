package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ReportOutput extends SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.ReportOutput");
    private boolean canProceed;
    private String nextProvisioningState;
    private String nonce;
    private String reason;
    private String sessionId;
    private String waitTime;

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof ReportOutput)) {
            return false;
        }
        ReportOutput reportOutput = (ReportOutput) obj;
        return super.equals(obj) && Helper.equals(this.sessionId, reportOutput.sessionId) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(reportOutput.canProceed)) && Helper.equals(this.reason, reportOutput.reason) && Helper.equals(this.nextProvisioningState, reportOutput.nextProvisioningState) && Helper.equals(this.nonce, reportOutput.nonce) && Helper.equals(this.waitTime, reportOutput.waitTime);
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
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.sessionId, Boolean.valueOf(this.canProceed), this.reason, this.nextProvisioningState, this.nonce, this.waitTime);
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

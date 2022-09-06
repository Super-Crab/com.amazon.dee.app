package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.Map;
/* loaded from: classes12.dex */
public class ComputeConfigurationOutput extends SignedOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.ComputeConfigurationOutput");
    private boolean canProceed;
    private Map<String, String> configuration;
    private String nextProvisioningState;
    private String nonce;
    private String reason;
    private String sessionId;
    private String waitTime;

    @Override // com.amazon.devicesetup.common.v1.SignedOutput
    public boolean equals(Object obj) {
        if (!(obj instanceof ComputeConfigurationOutput)) {
            return false;
        }
        ComputeConfigurationOutput computeConfigurationOutput = (ComputeConfigurationOutput) obj;
        return super.equals(obj) && Helper.equals(this.configuration, computeConfigurationOutput.configuration) && Helper.equals(this.waitTime, computeConfigurationOutput.waitTime) && Helper.equals(this.sessionId, computeConfigurationOutput.sessionId) && Helper.equals(this.reason, computeConfigurationOutput.reason) && Helper.equals(this.nonce, computeConfigurationOutput.nonce) && Helper.equals(this.nextProvisioningState, computeConfigurationOutput.nextProvisioningState) && Helper.equals(Boolean.valueOf(this.canProceed), Boolean.valueOf(computeConfigurationOutput.canProceed));
    }

    public Map<String, String> getConfiguration() {
        return this.configuration;
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
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.configuration, this.waitTime, this.sessionId, this.reason, this.nonce, this.nextProvisioningState, Boolean.valueOf(this.canProceed));
    }

    public boolean isCanProceed() {
        return this.canProceed;
    }

    public void setCanProceed(boolean z) {
        this.canProceed = z;
    }

    public void setConfiguration(Map<String, String> map) {
        this.configuration = map;
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

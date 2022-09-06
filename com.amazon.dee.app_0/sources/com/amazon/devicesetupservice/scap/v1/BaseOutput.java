package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class BaseOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BaseOutput");
    private String nonce;
    private String sessionId;
    private String sessionState;
    private String signature;

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseOutput)) {
            return false;
        }
        BaseOutput baseOutput = (BaseOutput) obj;
        return Helper.equals(this.signature, baseOutput.signature) && Helper.equals(this.sessionId, baseOutput.sessionId) && Helper.equals(this.sessionState, baseOutput.sessionState) && Helper.equals(this.nonce, baseOutput.nonce);
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getSessionState() {
        return this.sessionState;
    }

    public String getSignature() {
        return this.signature;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.signature, this.sessionId, this.sessionState, this.nonce);
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setSessionId(String str) {
        this.sessionId = str;
    }

    public void setSessionState(String str) {
        this.sessionState = str;
    }

    public void setSignature(String str) {
        this.signature = str;
    }
}

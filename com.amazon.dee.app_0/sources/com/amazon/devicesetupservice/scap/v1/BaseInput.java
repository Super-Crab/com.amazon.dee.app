package com.amazon.devicesetupservice.scap.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public abstract class BaseInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.scap.v1.BaseInput");
    private String accessToken;
    private String nonce;
    private String response;
    private String sessionState;

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseInput)) {
            return false;
        }
        BaseInput baseInput = (BaseInput) obj;
        return Helper.equals(this.accessToken, baseInput.accessToken) && Helper.equals(this.sessionState, baseInput.sessionState) && Helper.equals(this.nonce, baseInput.nonce) && Helper.equals(this.response, baseInput.response);
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public String getNonce() {
        return this.nonce;
    }

    public String getResponse() {
        return this.response;
    }

    public String getSessionState() {
        return this.sessionState;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.accessToken, this.sessionState, this.nonce, this.response);
    }

    public void setAccessToken(String str) {
        this.accessToken = str;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }

    public void setResponse(String str) {
        this.response = str;
    }

    public void setSessionState(String str) {
        this.sessionState = str;
    }
}

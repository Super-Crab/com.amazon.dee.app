package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class FinalizeEcdheAuthenticationSessionOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.FinalizeEcdheAuthenticationSessionOutput");
    private String endpointToUse;
    private ByteBuffer sessionKey;

    public boolean equals(Object obj) {
        if (!(obj instanceof FinalizeEcdheAuthenticationSessionOutput)) {
            return false;
        }
        FinalizeEcdheAuthenticationSessionOutput finalizeEcdheAuthenticationSessionOutput = (FinalizeEcdheAuthenticationSessionOutput) obj;
        return Helper.equals(this.endpointToUse, finalizeEcdheAuthenticationSessionOutput.endpointToUse) && Helper.equals(this.sessionKey, finalizeEcdheAuthenticationSessionOutput.sessionKey);
    }

    public String getEndpointToUse() {
        return this.endpointToUse;
    }

    public ByteBuffer getSessionKey() {
        return this.sessionKey;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.endpointToUse, this.sessionKey);
    }

    public void setEndpointToUse(String str) {
        this.endpointToUse = str;
    }

    public void setSessionKey(ByteBuffer byteBuffer) {
        this.sessionKey = byteBuffer;
    }
}

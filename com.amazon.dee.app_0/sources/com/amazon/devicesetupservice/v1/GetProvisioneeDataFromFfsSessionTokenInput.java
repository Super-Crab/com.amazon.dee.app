package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class GetProvisioneeDataFromFfsSessionTokenInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.GetProvisioneeDataFromFfsSessionTokenInput");
    private String signature;
    private ByteBuffer signedPayload;
    private String token;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetProvisioneeDataFromFfsSessionTokenInput)) {
            return false;
        }
        GetProvisioneeDataFromFfsSessionTokenInput getProvisioneeDataFromFfsSessionTokenInput = (GetProvisioneeDataFromFfsSessionTokenInput) obj;
        return Helper.equals(this.signature, getProvisioneeDataFromFfsSessionTokenInput.signature) && Helper.equals(this.token, getProvisioneeDataFromFfsSessionTokenInput.token) && Helper.equals(this.signedPayload, getProvisioneeDataFromFfsSessionTokenInput.signedPayload);
    }

    public String getSignature() {
        return this.signature;
    }

    public ByteBuffer getSignedPayload() {
        return this.signedPayload;
    }

    public String getToken() {
        return this.token;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.signature, this.token, this.signedPayload);
    }

    public void setSignature(String str) {
        this.signature = str;
    }

    public void setSignedPayload(ByteBuffer byteBuffer) {
        this.signedPayload = byteBuffer;
    }

    public void setToken(String str) {
        this.token = str;
    }
}

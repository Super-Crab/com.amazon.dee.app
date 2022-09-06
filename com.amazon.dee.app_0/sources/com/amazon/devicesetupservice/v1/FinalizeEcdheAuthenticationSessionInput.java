package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class FinalizeEcdheAuthenticationSessionInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.FinalizeEcdheAuthenticationSessionInput");
    private String continuationToken;
    private ByteBuffer payload;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof FinalizeEcdheAuthenticationSessionInput)) {
            return false;
        }
        FinalizeEcdheAuthenticationSessionInput finalizeEcdheAuthenticationSessionInput = (FinalizeEcdheAuthenticationSessionInput) obj;
        return super.equals(obj) && Helper.equals(this.payload, finalizeEcdheAuthenticationSessionInput.payload) && Helper.equals(this.continuationToken, finalizeEcdheAuthenticationSessionInput.continuationToken);
    }

    public String getContinuationToken() {
        return this.continuationToken;
    }

    public ByteBuffer getPayload() {
        return this.payload;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.payload, this.continuationToken);
    }

    public void setContinuationToken(String str) {
        this.continuationToken = str;
    }

    public void setPayload(ByteBuffer byteBuffer) {
        this.payload = byteBuffer;
    }
}

package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetEndpointRequest extends CredentialLockerBaseRequest implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.GetEndpointRequest");
    private String customerId;

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof GetEndpointRequest)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.customerId, ((GetEndpointRequest) obj).customerId);
    }

    public String getCustomerId() {
        return this.customerId;
    }

    @Override // com.amazon.credentiallocker.CredentialLockerBaseRequest
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.customerId);
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }
}

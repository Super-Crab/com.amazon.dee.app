package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class RecordDevicePossessionIntentInput extends AuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.RecordDevicePossessionIntentInput");
    private String customerId;
    private String productId;
    private String publicKey;

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof RecordDevicePossessionIntentInput)) {
            return false;
        }
        RecordDevicePossessionIntentInput recordDevicePossessionIntentInput = (RecordDevicePossessionIntentInput) obj;
        return super.equals(obj) && Helper.equals(this.publicKey, recordDevicePossessionIntentInput.publicKey) && Helper.equals(this.customerId, recordDevicePossessionIntentInput.customerId) && Helper.equals(this.productId, recordDevicePossessionIntentInput.productId);
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    @Override // com.amazon.devicesetupservice.v1.AuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.publicKey, this.customerId, this.productId);
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }
}

package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class InternalGetCustomerProvisioneeZeroTouchSetupStatusInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalGetCustomerProvisioneeZeroTouchSetupStatusInput");
    private AuthMaterialIdentifier authMaterialIdentifier;
    private String customerId;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalGetCustomerProvisioneeZeroTouchSetupStatusInput)) {
            return false;
        }
        InternalGetCustomerProvisioneeZeroTouchSetupStatusInput internalGetCustomerProvisioneeZeroTouchSetupStatusInput = (InternalGetCustomerProvisioneeZeroTouchSetupStatusInput) obj;
        return Helper.equals(this.customerId, internalGetCustomerProvisioneeZeroTouchSetupStatusInput.customerId) && Helper.equals(this.authMaterialIdentifier, internalGetCustomerProvisioneeZeroTouchSetupStatusInput.authMaterialIdentifier);
    }

    public AuthMaterialIdentifier getAuthMaterialIdentifier() {
        return this.authMaterialIdentifier;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.customerId, this.authMaterialIdentifier);
    }

    public void setAuthMaterialIdentifier(AuthMaterialIdentifier authMaterialIdentifier) {
        this.authMaterialIdentifier = authMaterialIdentifier;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }
}

package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class ConfirmFfsSessionTokenIsStillValidOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.ConfirmFfsSessionTokenIsStillValidOutput");
    private String customerId;
    private String publicKey;

    public boolean equals(Object obj) {
        if (!(obj instanceof ConfirmFfsSessionTokenIsStillValidOutput)) {
            return false;
        }
        ConfirmFfsSessionTokenIsStillValidOutput confirmFfsSessionTokenIsStillValidOutput = (ConfirmFfsSessionTokenIsStillValidOutput) obj;
        return Helper.equals(this.customerId, confirmFfsSessionTokenIsStillValidOutput.customerId) && Helper.equals(this.publicKey, confirmFfsSessionTokenIsStillValidOutput.publicKey);
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.customerId, this.publicKey);
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setPublicKey(String str) {
        this.publicKey = str;
    }
}

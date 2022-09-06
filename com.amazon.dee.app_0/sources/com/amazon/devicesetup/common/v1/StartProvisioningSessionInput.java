package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartProvisioningSessionInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.StartProvisioningSessionInput");
    private String nonce;

    public boolean equals(Object obj) {
        if (!(obj instanceof StartProvisioningSessionInput)) {
            return false;
        }
        return Helper.equals(this.nonce, ((StartProvisioningSessionInput) obj).nonce);
    }

    public String getNonce() {
        return this.nonce;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.nonce);
    }

    public void setNonce(String str) {
        this.nonce = str;
    }
}

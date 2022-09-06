package com.amazon.devicesetup.common.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class StartProvisioningSessionV2Input implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetup.common.v1.StartProvisioningSessionV2Input");
    private AccessPoint accessPoint;
    private String nonce;

    public boolean equals(Object obj) {
        if (!(obj instanceof StartProvisioningSessionV2Input)) {
            return false;
        }
        StartProvisioningSessionV2Input startProvisioningSessionV2Input = (StartProvisioningSessionV2Input) obj;
        return Helper.equals(this.nonce, startProvisioningSessionV2Input.nonce) && Helper.equals(this.accessPoint, startProvisioningSessionV2Input.accessPoint);
    }

    public AccessPoint getAccessPoint() {
        return this.accessPoint;
    }

    public String getNonce() {
        return this.nonce;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.nonce, this.accessPoint);
    }

    public void setAccessPoint(AccessPoint accessPoint) {
        this.accessPoint = accessPoint;
    }

    public void setNonce(String str) {
        this.nonce = str;
    }
}

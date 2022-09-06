package com.amazon.credentiallocker;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetUserMetadataResponse implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.credentiallocker.GetUserMetadataResponse");
    private int wifiCredentialCount;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetUserMetadataResponse)) {
            return false;
        }
        return Helper.equals(Integer.valueOf(this.wifiCredentialCount), Integer.valueOf(((GetUserMetadataResponse) obj).wifiCredentialCount));
    }

    public int getWifiCredentialCount() {
        return this.wifiCredentialCount;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), Integer.valueOf(this.wifiCredentialCount));
    }

    public void setWifiCredentialCount(int i) {
        this.wifiCredentialCount = i;
    }
}

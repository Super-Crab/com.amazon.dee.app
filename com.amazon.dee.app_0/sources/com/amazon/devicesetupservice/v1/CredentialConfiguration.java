package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class CredentialConfiguration implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.CredentialConfiguration");
    private String privateSharedKey;
    private WepKeyConfiguration wepKeyConfiguration;

    public boolean equals(Object obj) {
        if (!(obj instanceof CredentialConfiguration)) {
            return false;
        }
        CredentialConfiguration credentialConfiguration = (CredentialConfiguration) obj;
        return Helper.equals(this.wepKeyConfiguration, credentialConfiguration.wepKeyConfiguration) && Helper.equals(this.privateSharedKey, credentialConfiguration.privateSharedKey);
    }

    public String getPrivateSharedKey() {
        return this.privateSharedKey;
    }

    public WepKeyConfiguration getWepKeyConfiguration() {
        return this.wepKeyConfiguration;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.wepKeyConfiguration, this.privateSharedKey);
    }

    public void setPrivateSharedKey(String str) {
        this.privateSharedKey = str;
    }

    public void setWepKeyConfiguration(WepKeyConfiguration wepKeyConfiguration) {
        this.wepKeyConfiguration = wepKeyConfiguration;
    }
}

package com.amazon.devicesetupservice.v1;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.WifiCredentials;
import java.util.List;
/* loaded from: classes12.dex */
public class InternalGetWifiCredentialsProxyOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.v1.InternalGetWifiCredentialsProxyOutput");
    private List<WifiCredentials> wifiCredentialsList;

    public boolean equals(Object obj) {
        if (!(obj instanceof InternalGetWifiCredentialsProxyOutput)) {
            return false;
        }
        return Helper.equals(this.wifiCredentialsList, ((InternalGetWifiCredentialsProxyOutput) obj).wifiCredentialsList);
    }

    public List<WifiCredentials> getWifiCredentialsList() {
        return this.wifiCredentialsList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.wifiCredentialsList);
    }

    public void setWifiCredentialsList(List<WifiCredentials> list) {
        this.wifiCredentialsList = list;
    }
}

package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.util.List;
/* loaded from: classes12.dex */
public class GetCustomerDevicesCredentialsOutput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsOutput");
    private List<SmartHomeCredential> credentialsList;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetCustomerDevicesCredentialsOutput)) {
            return false;
        }
        return Helper.equals(this.credentialsList, ((GetCustomerDevicesCredentialsOutput) obj).credentialsList);
    }

    public List<SmartHomeCredential> getCredentialsList() {
        return this.credentialsList;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.credentialsList);
    }

    public void setCredentialsList(List<SmartHomeCredential> list) {
        this.credentialsList = list;
    }
}

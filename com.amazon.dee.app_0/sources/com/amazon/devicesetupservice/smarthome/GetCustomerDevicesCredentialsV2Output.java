package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
/* loaded from: classes12.dex */
public class GetCustomerDevicesCredentialsV2Output implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsV2Output");
    private SmartHomeDeviceCredentials credentials;
    private String version;

    public boolean equals(Object obj) {
        if (!(obj instanceof GetCustomerDevicesCredentialsV2Output)) {
            return false;
        }
        GetCustomerDevicesCredentialsV2Output getCustomerDevicesCredentialsV2Output = (GetCustomerDevicesCredentialsV2Output) obj;
        return Helper.equals(this.version, getCustomerDevicesCredentialsV2Output.version) && Helper.equals(this.credentials, getCustomerDevicesCredentialsV2Output.credentials);
    }

    public SmartHomeDeviceCredentials getCredentials() {
        return this.credentials;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.version, this.credentials);
    }

    public void setCredentials(SmartHomeDeviceCredentials smartHomeDeviceCredentials) {
        this.credentials = smartHomeDeviceCredentials;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}

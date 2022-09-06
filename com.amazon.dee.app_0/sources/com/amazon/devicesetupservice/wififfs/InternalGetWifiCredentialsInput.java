package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.GetWifiCredentialsInput;
/* loaded from: classes12.dex */
public class InternalGetWifiCredentialsInput extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalGetWifiCredentialsInput");
    private GetWifiCredentialsInput getWifiCredentialsInputData;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalGetWifiCredentialsInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.getWifiCredentialsInputData, ((InternalGetWifiCredentialsInput) obj).getWifiCredentialsInputData);
    }

    public GetWifiCredentialsInput getGetWifiCredentialsInputData() {
        return this.getWifiCredentialsInputData;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.getWifiCredentialsInputData);
    }

    public void setGetWifiCredentialsInputData(GetWifiCredentialsInput getWifiCredentialsInput) {
        this.getWifiCredentialsInputData = getWifiCredentialsInput;
    }
}

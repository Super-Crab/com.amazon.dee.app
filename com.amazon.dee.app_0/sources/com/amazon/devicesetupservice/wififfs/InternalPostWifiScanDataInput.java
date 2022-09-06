package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.PostWifiScanDataInput;
/* loaded from: classes12.dex */
public class InternalPostWifiScanDataInput extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalPostWifiScanDataInput");
    private PostWifiScanDataInput postWifiScanDataInput;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalPostWifiScanDataInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.postWifiScanDataInput, ((InternalPostWifiScanDataInput) obj).postWifiScanDataInput);
    }

    public PostWifiScanDataInput getPostWifiScanDataInput() {
        return this.postWifiScanDataInput;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.postWifiScanDataInput);
    }

    public void setPostWifiScanDataInput(PostWifiScanDataInput postWifiScanDataInput) {
        this.postWifiScanDataInput = postWifiScanDataInput;
    }
}

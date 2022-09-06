package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.StartProvisioningSessionV2Input;
/* loaded from: classes12.dex */
public class InternalStartProvisioningSessionV2Input extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalStartProvisioningSessionV2Input");
    private StartProvisioningSessionV2Input startProvisioningSessionInputData;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalStartProvisioningSessionV2Input)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.startProvisioningSessionInputData, ((InternalStartProvisioningSessionV2Input) obj).startProvisioningSessionInputData);
    }

    public StartProvisioningSessionV2Input getStartProvisioningSessionInputData() {
        return this.startProvisioningSessionInputData;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.startProvisioningSessionInputData);
    }

    public void setStartProvisioningSessionInputData(StartProvisioningSessionV2Input startProvisioningSessionV2Input) {
        this.startProvisioningSessionInputData = startProvisioningSessionV2Input;
    }
}

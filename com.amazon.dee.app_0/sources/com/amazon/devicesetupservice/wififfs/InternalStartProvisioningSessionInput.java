package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.StartProvisioningSessionInput;
/* loaded from: classes12.dex */
public class InternalStartProvisioningSessionInput extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalStartProvisioningSessionInput");
    private StartProvisioningSessionInput startProvisioningSessionInputData;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalStartProvisioningSessionInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.startProvisioningSessionInputData, ((InternalStartProvisioningSessionInput) obj).startProvisioningSessionInputData);
    }

    public StartProvisioningSessionInput getStartProvisioningSessionInputData() {
        return this.startProvisioningSessionInputData;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.startProvisioningSessionInputData);
    }

    public void setStartProvisioningSessionInputData(StartProvisioningSessionInput startProvisioningSessionInput) {
        this.startProvisioningSessionInputData = startProvisioningSessionInput;
    }
}

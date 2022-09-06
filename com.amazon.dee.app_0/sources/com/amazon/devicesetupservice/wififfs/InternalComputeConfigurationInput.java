package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.ComputeConfigurationInput;
/* loaded from: classes12.dex */
public class InternalComputeConfigurationInput extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalComputeConfigurationInput");
    private ComputeConfigurationInput computeConfigurationInputData;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalComputeConfigurationInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.computeConfigurationInputData, ((InternalComputeConfigurationInput) obj).computeConfigurationInputData);
    }

    public ComputeConfigurationInput getComputeConfigurationInputData() {
        return this.computeConfigurationInputData;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.computeConfigurationInputData);
    }

    public void setComputeConfigurationInputData(ComputeConfigurationInput computeConfigurationInput) {
        this.computeConfigurationInputData = computeConfigurationInput;
    }
}

package com.amazon.devicesetupservice.wififfs;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import com.amazon.devicesetup.common.v1.StartPinBasedSetupInput;
/* loaded from: classes12.dex */
public class InternalStartPinBasedSetupInput extends DeviceAuthenticatedInput implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.wififfs.InternalStartPinBasedSetupInput");
    private StartPinBasedSetupInput startPinBasedSetupInputData;

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public boolean equals(Object obj) {
        if (!(obj instanceof InternalStartPinBasedSetupInput)) {
            return false;
        }
        return super.equals(obj) && Helper.equals(this.startPinBasedSetupInputData, ((InternalStartPinBasedSetupInput) obj).startPinBasedSetupInputData);
    }

    public StartPinBasedSetupInput getStartPinBasedSetupInputData() {
        return this.startPinBasedSetupInputData;
    }

    @Override // com.amazon.devicesetupservice.wififfs.DeviceAuthenticatedInput
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.startPinBasedSetupInputData);
    }

    public void setStartPinBasedSetupInputData(StartPinBasedSetupInput startPinBasedSetupInput) {
        this.startPinBasedSetupInputData = startPinBasedSetupInput;
    }
}

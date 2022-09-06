package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation;

import com.amazon.whisperjoin.common.sharedtypes.devices.WhisperJoinPeripheralDeviceDetails;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisioningMethod;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.functions.Predicate;
/* loaded from: classes13.dex */
public class WJDeviceSetupModeSupportedPredicate implements Predicate<WhisperJoinPeripheralDeviceDetails> {
    private final boolean mHasBarcode;
    private final ProvisioningMethod mProvisioningMethod;

    public WJDeviceSetupModeSupportedPredicate(ProvisioningMethod provisioningMethod, boolean z) {
        this.mProvisioningMethod = provisioningMethod;
        this.mHasBarcode = z;
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public boolean test(@NonNull WhisperJoinPeripheralDeviceDetails whisperJoinPeripheralDeviceDetails) throws Exception {
        if (this.mProvisioningMethod == ProvisioningMethod.MANUAL && this.mHasBarcode) {
            return whisperJoinPeripheralDeviceDetails.getSupportsPin();
        }
        if (this.mProvisioningMethod == ProvisioningMethod.MANUAL) {
            return whisperJoinPeripheralDeviceDetails.getSupportsUnauthenticatedEcdhe();
        }
        return whisperJoinPeripheralDeviceDetails.getSupportsAuthenticatedEcdhe();
    }
}

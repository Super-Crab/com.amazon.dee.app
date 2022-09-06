package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.error.BluetoothLowEnergyNotSupportedException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.BluetoothNotEnabledException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.NoUserLoggedInException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.PreconditionFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.error.RequiredLocationPermissionsForScanningNotGrantedException;
/* loaded from: classes13.dex */
public class PreconditionFailureWJErrorMapper implements WJErrorMapper<PreconditionFailureException> {
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(PreconditionFailureException preconditionFailureException) {
        if (preconditionFailureException instanceof NoUserLoggedInException) {
            return WJErrorFactory.Precondition.noUserLoggedIn();
        }
        if (preconditionFailureException instanceof BluetoothLowEnergyNotSupportedException) {
            return WJErrorFactory.Precondition.bluetoothLowEnergyNotSupported();
        }
        if (preconditionFailureException instanceof BluetoothNotEnabledException) {
            return WJErrorFactory.Precondition.bluetoothNotEnabled();
        }
        if (preconditionFailureException instanceof RequiredLocationPermissionsForScanningNotGrantedException) {
            return WJErrorFactory.Precondition.requiredPermissionsNotGranted();
        }
        throw new IllegalStateException("Unhandled ProvisioningFailureException", preconditionFailureException);
    }
}

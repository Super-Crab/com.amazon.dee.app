package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.error.ProvisioningNotGranted;
/* loaded from: classes13.dex */
public class ProvisioningNotGrantedWJErrorMapper implements WJErrorMapper<ProvisioningNotGranted> {
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(ProvisioningNotGranted provisioningNotGranted) {
        return WJErrorFactory.Connection.startProvisioningRequestFailed();
    }
}

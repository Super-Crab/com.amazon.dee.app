package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenFailedValidationException;
/* loaded from: classes13.dex */
public class WiFiSyncAuthTokenFailedValidationWJErrorMapper implements WJErrorMapper<WiFiSyncAuthTokenFailedValidationException> {
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(WiFiSyncAuthTokenFailedValidationException wiFiSyncAuthTokenFailedValidationException) {
        return WJErrorFactory.Provisioning.wifiSyncAuthTokenFailedValidation();
    }
}

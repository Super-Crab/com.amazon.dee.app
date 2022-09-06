package com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes;

import com.amazon.whisperjoin.common.sharedtypes.error.WJError;
import com.amazon.whisperjoin.common.sharedtypes.error.WJErrorFactory;
import com.amazon.whisperjoin.deviceprovisioningservice.error.WiFiSyncAuthTokenNotFoundException;
/* loaded from: classes13.dex */
public class WiFiSyncAuthTokenNotFoundWJErrorMapper implements WJErrorMapper<WiFiSyncAuthTokenNotFoundException> {
    @Override // com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper
    public WJError map(WiFiSyncAuthTokenNotFoundException wiFiSyncAuthTokenNotFoundException) {
        return WJErrorFactory.Provisioning.wifiSyncAuthTokenNotFound();
    }
}

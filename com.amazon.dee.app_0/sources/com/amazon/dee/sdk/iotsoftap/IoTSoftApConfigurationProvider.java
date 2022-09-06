package com.amazon.dee.sdk.iotsoftap;

import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
/* loaded from: classes12.dex */
public interface IoTSoftApConfigurationProvider {
    ProvisioningData getProvisioningData();

    RegistrationTokenProvider getRegistrationTokenProvider();
}

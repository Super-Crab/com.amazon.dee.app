package com.amazon.whisperjoin.softap.actions;

import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import rx.Single;
/* loaded from: classes13.dex */
public interface SoftApOperationFactory {
    Single<DeviceDetails> getDeviceDetails();

    Single<String> getPublicKey();

    Single<Void> saveProvisioningData(ProvisioningData provisioningData);

    Single<Void> secureProvisionDevice(byte[] bArr);

    Single<Void> secureSaveRegistrationToken(byte[] bArr);

    Single<Void> sendPublicKey(String str, int i);
}

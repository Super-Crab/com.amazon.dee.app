package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.operation.provisioningverification;

import com.amazon.whisperjoin.deviceprovisioningservice.device.WJProvisionee;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.ProvisionableConfiguration;
import io.reactivex.rxjava3.core.Completable;
/* loaded from: classes13.dex */
public interface ProvisioningVerificationMethod {
    Completable verify(WJProvisionee wJProvisionee, ProvisionableConfiguration provisionableConfiguration);
}

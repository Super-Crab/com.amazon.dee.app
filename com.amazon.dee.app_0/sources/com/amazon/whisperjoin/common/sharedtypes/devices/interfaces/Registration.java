package com.amazon.whisperjoin.common.sharedtypes.devices.interfaces;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationDetails;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration.CBLRegistrationRequest;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface Registration {
    Future<Void> clearRegistrationConfigurations();

    Future<CBLRegistrationDetails> getRegistrationTokenStatus();

    Future<Void> setRegistrationToken(CBLRegistrationRequest cBLRegistrationRequest);
}

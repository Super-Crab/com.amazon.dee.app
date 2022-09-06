package com.amazon.whisperjoin.softap.serializer;

import com.amazon.whisperjoin.provisioning.registration.RegistrationToken;
/* loaded from: classes13.dex */
public interface RegistrationTokenSerializer extends ByteSerializer<RegistrationToken> {
    byte[] serialize(RegistrationToken registrationToken);
}

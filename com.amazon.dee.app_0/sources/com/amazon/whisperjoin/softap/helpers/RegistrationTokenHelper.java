package com.amazon.whisperjoin.softap.helpers;

import com.amazon.whisperjoin.provisioning.registration.RegistrationToken;
/* loaded from: classes13.dex */
public class RegistrationTokenHelper {
    public static RegistrationToken toWhisperjoinRegistrationToken(com.amazon.whisperjoin.softap.pojos.RegistrationToken registrationToken) {
        return new RegistrationToken(registrationToken.getToken(), 0L);
    }
}

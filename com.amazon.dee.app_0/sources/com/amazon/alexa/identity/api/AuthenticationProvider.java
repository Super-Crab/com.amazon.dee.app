package com.amazon.alexa.identity.api;

import io.reactivex.rxjava3.core.Single;
/* loaded from: classes9.dex */
public interface AuthenticationProvider {
    Single<String> generateCBLRegistrationToken();
}

package com.amazon.dee.sdk.iotsoftap;

import io.reactivex.rxjava3.core.Single;
@FunctionalInterface
/* loaded from: classes12.dex */
public interface RegistrationTokenProvider {
    Single<String> get();
}

package com.amazon.alexa.handsfree.metrics.utils;

import com.amazon.alexa.identity.api.IdentityService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class IdentityServiceManager_Factory implements Factory<IdentityServiceManager> {
    private final Provider<IdentityService> identityServiceProvider;

    public IdentityServiceManager_Factory(Provider<IdentityService> provider) {
        this.identityServiceProvider = provider;
    }

    public static IdentityServiceManager_Factory create(Provider<IdentityService> provider) {
        return new IdentityServiceManager_Factory(provider);
    }

    public static IdentityServiceManager newIdentityServiceManager(IdentityService identityService) {
        return new IdentityServiceManager(identityService);
    }

    public static IdentityServiceManager provideInstance(Provider<IdentityService> provider) {
        return new IdentityServiceManager(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public IdentityServiceManager mo10268get() {
        return provideInstance(this.identityServiceProvider);
    }
}

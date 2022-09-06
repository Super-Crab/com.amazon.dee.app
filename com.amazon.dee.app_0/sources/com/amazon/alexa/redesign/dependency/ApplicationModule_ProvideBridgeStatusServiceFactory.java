package com.amazon.alexa.redesign.dependency;

import com.amazon.alexa.elements.api.BridgeStatusService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes10.dex */
public final class ApplicationModule_ProvideBridgeStatusServiceFactory implements Factory<BridgeStatusService> {
    private final ApplicationModule module;

    public ApplicationModule_ProvideBridgeStatusServiceFactory(ApplicationModule applicationModule) {
        this.module = applicationModule;
    }

    public static ApplicationModule_ProvideBridgeStatusServiceFactory create(ApplicationModule applicationModule) {
        return new ApplicationModule_ProvideBridgeStatusServiceFactory(applicationModule);
    }

    public static BridgeStatusService provideInstance(ApplicationModule applicationModule) {
        return proxyProvideBridgeStatusService(applicationModule);
    }

    public static BridgeStatusService proxyProvideBridgeStatusService(ApplicationModule applicationModule) {
        return (BridgeStatusService) Preconditions.checkNotNull(applicationModule.provideBridgeStatusService(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public BridgeStatusService mo10268get() {
        return provideInstance(this.module);
    }
}

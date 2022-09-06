package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.deecomms.core.decoupling.AlexaCommsServiceWrapper;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class CommsModule_ProvidesAlexaCommsServiceFactory implements Factory<AlexaCommsServiceWrapper> {
    private final Provider<Context> contextLazyProvider;
    private final Provider<DeviceInformation> deviceInformationLazyProvider;
    private final Provider<String> deviceNameTemplateProvider;
    private final Provider<EventBus> eventBusLazyProvider;
    private final Provider<IdentityService> identityServiceLazyProvider;
    private final Provider<MAPAccountManager> mapAccountManagerLazyProvider;
    private final Provider<MetricsService> metricsServiceLazyProvider;
    private final CommsModule module;

    public CommsModule_ProvidesAlexaCommsServiceFactory(CommsModule commsModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<MetricsService> provider4, Provider<MAPAccountManager> provider5, Provider<String> provider6, Provider<DeviceInformation> provider7) {
        this.module = commsModule;
        this.contextLazyProvider = provider;
        this.identityServiceLazyProvider = provider2;
        this.eventBusLazyProvider = provider3;
        this.metricsServiceLazyProvider = provider4;
        this.mapAccountManagerLazyProvider = provider5;
        this.deviceNameTemplateProvider = provider6;
        this.deviceInformationLazyProvider = provider7;
    }

    public static CommsModule_ProvidesAlexaCommsServiceFactory create(CommsModule commsModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<MetricsService> provider4, Provider<MAPAccountManager> provider5, Provider<String> provider6, Provider<DeviceInformation> provider7) {
        return new CommsModule_ProvidesAlexaCommsServiceFactory(commsModule, provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static AlexaCommsServiceWrapper provideInstance(CommsModule commsModule, Provider<Context> provider, Provider<IdentityService> provider2, Provider<EventBus> provider3, Provider<MetricsService> provider4, Provider<MAPAccountManager> provider5, Provider<String> provider6, Provider<DeviceInformation> provider7) {
        return proxyProvidesAlexaCommsService(commsModule, DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5), provider6.mo10268get(), DoubleCheck.lazy(provider7));
    }

    public static AlexaCommsServiceWrapper proxyProvidesAlexaCommsService(CommsModule commsModule, Lazy<Context> lazy, Lazy<IdentityService> lazy2, Lazy<EventBus> lazy3, Lazy<MetricsService> lazy4, Lazy<MAPAccountManager> lazy5, String str, Lazy<DeviceInformation> lazy6) {
        return (AlexaCommsServiceWrapper) Preconditions.checkNotNull(commsModule.providesAlexaCommsService(lazy, lazy2, lazy3, lazy4, lazy5, str, lazy6), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public AlexaCommsServiceWrapper mo10268get() {
        return provideInstance(this.module, this.contextLazyProvider, this.identityServiceLazyProvider, this.eventBusLazyProvider, this.metricsServiceLazyProvider, this.mapAccountManagerLazyProvider, this.deviceNameTemplateProvider, this.deviceInformationLazyProvider);
    }
}

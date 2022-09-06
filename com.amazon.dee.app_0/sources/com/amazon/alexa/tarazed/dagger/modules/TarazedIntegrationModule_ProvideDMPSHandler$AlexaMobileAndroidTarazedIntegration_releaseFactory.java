package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.tarazed.dmps.DMPSHandler;
import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.dee.app.http.CoralService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<DMPSHandler> {
    private final Provider<CoralService> coralServiceProvider;
    private final Provider<DeviceInformation> deviceInfoProvider;
    private final Provider<PersistentStorage> dmpsPersistentStorageProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<EventBus> eventBusProvider;
    private final Provider<EventBusHandler> handlerProvider;
    private final Provider<IdentityService> identityServiceProvider;
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule, Provider<DeviceInformation> provider, Provider<IdentityService> provider2, Provider<CoralService> provider3, Provider<PersistentStorage> provider4, Provider<EventBus> provider5, Provider<EventBusHandler> provider6, Provider<EnvironmentService> provider7, Provider<TarazedSessionLogger> provider8, Provider<TarazedMetricsHelper> provider9) {
        this.module = tarazedIntegrationModule;
        this.deviceInfoProvider = provider;
        this.identityServiceProvider = provider2;
        this.coralServiceProvider = provider3;
        this.dmpsPersistentStorageProvider = provider4;
        this.eventBusProvider = provider5;
        this.handlerProvider = provider6;
        this.environmentServiceProvider = provider7;
        this.loggerProvider = provider8;
        this.metricsHelperProvider = provider9;
    }

    public static TarazedIntegrationModule_ProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule, Provider<DeviceInformation> provider, Provider<IdentityService> provider2, Provider<CoralService> provider3, Provider<PersistentStorage> provider4, Provider<EventBus> provider5, Provider<EventBusHandler> provider6, Provider<EnvironmentService> provider7, Provider<TarazedSessionLogger> provider8, Provider<TarazedMetricsHelper> provider9) {
        return new TarazedIntegrationModule_ProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule, provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }

    public static DMPSHandler provideInstance(TarazedIntegrationModule tarazedIntegrationModule, Provider<DeviceInformation> provider, Provider<IdentityService> provider2, Provider<CoralService> provider3, Provider<PersistentStorage> provider4, Provider<EventBus> provider5, Provider<EventBusHandler> provider6, Provider<EnvironmentService> provider7, Provider<TarazedSessionLogger> provider8, Provider<TarazedMetricsHelper> provider9) {
        return proxyProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule, provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get(), provider6.mo10268get(), provider7.mo10268get(), provider8.mo10268get(), provider9.mo10268get());
    }

    public static DMPSHandler proxyProvideDMPSHandler$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule, DeviceInformation deviceInformation, IdentityService identityService, CoralService coralService, PersistentStorage persistentStorage, EventBus eventBus, EventBusHandler eventBusHandler, EnvironmentService environmentService, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return (DMPSHandler) Preconditions.checkNotNull(tarazedIntegrationModule.provideDMPSHandler$AlexaMobileAndroidTarazedIntegration_release(deviceInformation, identityService, coralService, persistentStorage, eventBus, eventBusHandler, environmentService, tarazedSessionLogger, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public DMPSHandler mo10268get() {
        return provideInstance(this.module, this.deviceInfoProvider, this.identityServiceProvider, this.coralServiceProvider, this.dmpsPersistentStorageProvider, this.eventBusProvider, this.handlerProvider, this.environmentServiceProvider, this.loggerProvider, this.metricsHelperProvider);
    }
}

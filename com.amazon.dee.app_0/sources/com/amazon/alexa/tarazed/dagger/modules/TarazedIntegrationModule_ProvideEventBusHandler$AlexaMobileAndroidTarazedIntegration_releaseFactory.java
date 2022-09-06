package com.amazon.alexa.tarazed.dagger.modules;

import com.amazon.alexa.tarazed.eventbus.EventBusHandler;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes10.dex */
public final class TarazedIntegrationModule_ProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory implements Factory<EventBusHandler> {
    private final Provider<TarazedSessionLogger> loggerProvider;
    private final Provider<TarazedMetricsHelper> metricsHelperProvider;
    private final TarazedIntegrationModule module;

    public TarazedIntegrationModule_ProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory(TarazedIntegrationModule tarazedIntegrationModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        this.module = tarazedIntegrationModule;
        this.loggerProvider = provider;
        this.metricsHelperProvider = provider2;
    }

    public static TarazedIntegrationModule_ProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory create(TarazedIntegrationModule tarazedIntegrationModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        return new TarazedIntegrationModule_ProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_releaseFactory(tarazedIntegrationModule, provider, provider2);
    }

    public static EventBusHandler provideInstance(TarazedIntegrationModule tarazedIntegrationModule, Provider<TarazedSessionLogger> provider, Provider<TarazedMetricsHelper> provider2) {
        return proxyProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_release(tarazedIntegrationModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static EventBusHandler proxyProvideEventBusHandler$AlexaMobileAndroidTarazedIntegration_release(TarazedIntegrationModule tarazedIntegrationModule, TarazedSessionLogger tarazedSessionLogger, TarazedMetricsHelper tarazedMetricsHelper) {
        return (EventBusHandler) Preconditions.checkNotNull(tarazedIntegrationModule.provideEventBusHandler$AlexaMobileAndroidTarazedIntegration_release(tarazedSessionLogger, tarazedMetricsHelper), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public EventBusHandler mo10268get() {
        return provideInstance(this.module, this.loggerProvider, this.metricsHelperProvider);
    }
}

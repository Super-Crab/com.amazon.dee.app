package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.crashreporting.CrashReporter;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.MobilyticsEventFactory;
import com.amazon.alexa.preload.attribution.PreloadAttributionManager;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class MobilyticsMetricsConnector_Factory implements Factory<MobilyticsMetricsConnector> {
    private final Provider<String> androidIdProvider;
    private final Provider<ClientConfiguration> clientConfigurationProvider;
    private final Provider<CrashReporter> crashReporterProvider;
    private final Provider<MobilyticsEventFactory> mobilyticsEventFactoryProvider;
    private final Provider<Mobilytics> mobilyticsProvider;
    private final Provider<MobilyticsUserProviderImpl> mobilyticsUserProvider;
    private final Provider<PreloadAttributionManager> preloadAttributionManagerLazyProvider;

    public MobilyticsMetricsConnector_Factory(Provider<PreloadAttributionManager> provider, Provider<Mobilytics> provider2, Provider<MobilyticsEventFactory> provider3, Provider<CrashReporter> provider4, Provider<String> provider5, Provider<ClientConfiguration> provider6, Provider<MobilyticsUserProviderImpl> provider7) {
        this.preloadAttributionManagerLazyProvider = provider;
        this.mobilyticsProvider = provider2;
        this.mobilyticsEventFactoryProvider = provider3;
        this.crashReporterProvider = provider4;
        this.androidIdProvider = provider5;
        this.clientConfigurationProvider = provider6;
        this.mobilyticsUserProvider = provider7;
    }

    public static MobilyticsMetricsConnector_Factory create(Provider<PreloadAttributionManager> provider, Provider<Mobilytics> provider2, Provider<MobilyticsEventFactory> provider3, Provider<CrashReporter> provider4, Provider<String> provider5, Provider<ClientConfiguration> provider6, Provider<MobilyticsUserProviderImpl> provider7) {
        return new MobilyticsMetricsConnector_Factory(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }

    public static MobilyticsMetricsConnector newMobilyticsMetricsConnector(Lazy<PreloadAttributionManager> lazy, Lazy<Mobilytics> lazy2, Lazy<MobilyticsEventFactory> lazy3, CrashReporter crashReporter, String str, Lazy<ClientConfiguration> lazy4, MobilyticsUserProviderImpl mobilyticsUserProviderImpl) {
        return new MobilyticsMetricsConnector(lazy, lazy2, lazy3, crashReporter, str, lazy4, mobilyticsUserProviderImpl);
    }

    public static MobilyticsMetricsConnector provideInstance(Provider<PreloadAttributionManager> provider, Provider<Mobilytics> provider2, Provider<MobilyticsEventFactory> provider3, Provider<CrashReporter> provider4, Provider<String> provider5, Provider<ClientConfiguration> provider6, Provider<MobilyticsUserProviderImpl> provider7) {
        return new MobilyticsMetricsConnector(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), provider4.mo10268get(), provider5.mo10268get(), DoubleCheck.lazy(provider6), provider7.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MobilyticsMetricsConnector mo10268get() {
        return provideInstance(this.preloadAttributionManagerLazyProvider, this.mobilyticsProvider, this.mobilyticsEventFactoryProvider, this.crashReporterProvider, this.androidIdProvider, this.clientConfigurationProvider, this.mobilyticsUserProvider);
    }
}

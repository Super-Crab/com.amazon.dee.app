package com.amazon.alexa.biloba.view.dashboard;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.RemoteAssistHelper;
import com.amazon.alexa.biloba.view.emergencyHelpline.EmergencyHelplineRoutingHelper;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CardTransformer_Factory implements Factory<CardTransformer> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsHandler> commsHandlerProvider;
    private final Provider<EmergencyHelplineRoutingHelper> emergencyHelplineRoutingHelperProvider;
    private final Provider<RemoteAssistHelper> remoteAssistHelperProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public CardTransformer_Factory(Provider<CareActorsStore> provider, Provider<RoutingService> provider2, Provider<EmergencyHelplineRoutingHelper> provider3, Provider<RemoteAssistHelper> provider4, Provider<CommsHandler> provider5, Provider<BilobaMetricsService> provider6) {
        this.careActorsStoreProvider = provider;
        this.routingServiceProvider = provider2;
        this.emergencyHelplineRoutingHelperProvider = provider3;
        this.remoteAssistHelperProvider = provider4;
        this.commsHandlerProvider = provider5;
        this.bilobaMetricsServiceProvider = provider6;
    }

    public static CardTransformer_Factory create(Provider<CareActorsStore> provider, Provider<RoutingService> provider2, Provider<EmergencyHelplineRoutingHelper> provider3, Provider<RemoteAssistHelper> provider4, Provider<CommsHandler> provider5, Provider<BilobaMetricsService> provider6) {
        return new CardTransformer_Factory(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static CardTransformer newCardTransformer(Lazy<CareActorsStore> lazy, Lazy<RoutingService> lazy2, Lazy<EmergencyHelplineRoutingHelper> lazy3, Lazy<RemoteAssistHelper> lazy4, Lazy<CommsHandler> lazy5) {
        return new CardTransformer(lazy, lazy2, lazy3, lazy4, lazy5);
    }

    public static CardTransformer provideInstance(Provider<CareActorsStore> provider, Provider<RoutingService> provider2, Provider<EmergencyHelplineRoutingHelper> provider3, Provider<RemoteAssistHelper> provider4, Provider<CommsHandler> provider5, Provider<BilobaMetricsService> provider6) {
        CardTransformer cardTransformer = new CardTransformer(DoubleCheck.lazy(provider), DoubleCheck.lazy(provider2), DoubleCheck.lazy(provider3), DoubleCheck.lazy(provider4), DoubleCheck.lazy(provider5));
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(cardTransformer, provider6.mo10268get());
        return cardTransformer;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CardTransformer mo10268get() {
        return provideInstance(this.careActorsStoreProvider, this.routingServiceProvider, this.emergencyHelplineRoutingHelperProvider, this.remoteAssistHelperProvider, this.commsHandlerProvider, this.bilobaMetricsServiceProvider);
    }
}

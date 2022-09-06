package com.amazon.alexa.biloba.utils;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.CommsHandler;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CommsHelper_MembersInjector implements MembersInjector<CommsHelper> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsHandler> commsHandlerProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public CommsHelper_MembersInjector(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<CommsHandler> provider4) {
        this.bilobaMetricsServiceProvider = provider;
        this.careActorsStoreProvider = provider2;
        this.routingServiceProvider = provider3;
        this.commsHandlerProvider = provider4;
    }

    public static MembersInjector<CommsHelper> create(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<CommsHandler> provider4) {
        return new CommsHelper_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectCareActorsStore(CommsHelper commsHelper, Lazy<CareActorsStore> lazy) {
        commsHelper.careActorsStore = lazy;
    }

    public static void injectCommsHandler(CommsHelper commsHelper, Lazy<CommsHandler> lazy) {
        commsHelper.commsHandler = lazy;
    }

    public static void injectRoutingService(CommsHelper commsHelper, Lazy<RoutingService> lazy) {
        commsHelper.routingService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(CommsHelper commsHelper) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(commsHelper, this.bilobaMetricsServiceProvider.mo10268get());
        injectCareActorsStore(commsHelper, DoubleCheck.lazy(this.careActorsStoreProvider));
        injectRoutingService(commsHelper, DoubleCheck.lazy(this.routingServiceProvider));
        injectCommsHandler(commsHelper, DoubleCheck.lazy(this.commsHandlerProvider));
    }
}

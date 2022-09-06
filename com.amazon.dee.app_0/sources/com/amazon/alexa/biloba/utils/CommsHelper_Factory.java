package com.amazon.alexa.biloba.utils;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.CommsHandler;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class CommsHelper_Factory implements Factory<CommsHelper> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CareActorsStore> careActorsStoreProvider;
    private final Provider<CommsHandler> commsHandlerProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public CommsHelper_Factory(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<CommsHandler> provider4) {
        this.bilobaMetricsServiceProvider = provider;
        this.careActorsStoreProvider = provider2;
        this.routingServiceProvider = provider3;
        this.commsHandlerProvider = provider4;
    }

    public static CommsHelper_Factory create(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<CommsHandler> provider4) {
        return new CommsHelper_Factory(provider, provider2, provider3, provider4);
    }

    public static CommsHelper newCommsHelper() {
        return new CommsHelper();
    }

    public static CommsHelper provideInstance(Provider<BilobaMetricsService> provider, Provider<CareActorsStore> provider2, Provider<RoutingService> provider3, Provider<CommsHandler> provider4) {
        CommsHelper commsHelper = new CommsHelper();
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(commsHelper, provider.mo10268get());
        CommsHelper_MembersInjector.injectCareActorsStore(commsHelper, DoubleCheck.lazy(provider2));
        CommsHelper_MembersInjector.injectRoutingService(commsHelper, DoubleCheck.lazy(provider3));
        CommsHelper_MembersInjector.injectCommsHandler(commsHelper, DoubleCheck.lazy(provider4));
        return commsHelper;
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public CommsHelper mo10268get() {
        return provideInstance(this.bilobaMetricsServiceProvider, this.careActorsStoreProvider, this.routingServiceProvider, this.commsHandlerProvider);
    }
}

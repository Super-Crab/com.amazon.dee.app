package com.amazon.alexa.biloba.view.startup;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.routing.DeferredRoutingHelper;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class StartupView_MembersInjector implements MembersInjector<StartupView> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<DeferredRoutingHelper> deferredRoutingHelperProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UrlHelper> urlHelperProvider;

    public StartupView_MembersInjector(Provider<BilobaMetricsService> provider, Provider<RoutingService> provider2, Provider<DeferredRoutingHelper> provider3, Provider<UrlHelper> provider4) {
        this.bilobaMetricsServiceProvider = provider;
        this.routingServiceProvider = provider2;
        this.deferredRoutingHelperProvider = provider3;
        this.urlHelperProvider = provider4;
    }

    public static MembersInjector<StartupView> create(Provider<BilobaMetricsService> provider, Provider<RoutingService> provider2, Provider<DeferredRoutingHelper> provider3, Provider<UrlHelper> provider4) {
        return new StartupView_MembersInjector(provider, provider2, provider3, provider4);
    }

    public static void injectDeferredRoutingHelper(StartupView startupView, Lazy<DeferredRoutingHelper> lazy) {
        startupView.deferredRoutingHelper = lazy;
    }

    public static void injectRoutingService(StartupView startupView, Lazy<RoutingService> lazy) {
        startupView.routingService = lazy;
    }

    public static void injectUrlHelper(StartupView startupView, Lazy<UrlHelper> lazy) {
        startupView.urlHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(StartupView startupView) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(startupView, this.bilobaMetricsServiceProvider.mo10268get());
        injectRoutingService(startupView, DoubleCheck.lazy(this.routingServiceProvider));
        injectDeferredRoutingHelper(startupView, DoubleCheck.lazy(this.deferredRoutingHelperProvider));
        injectUrlHelper(startupView, DoubleCheck.lazy(this.urlHelperProvider));
    }
}

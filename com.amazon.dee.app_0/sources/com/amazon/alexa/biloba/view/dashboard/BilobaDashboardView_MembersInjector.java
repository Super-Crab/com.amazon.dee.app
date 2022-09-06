package com.amazon.alexa.biloba.view.dashboard;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.utils.CommsHelper;
import com.amazon.alexa.biloba.utils.RemoteAssistHelper;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class BilobaDashboardView_MembersInjector implements MembersInjector<BilobaDashboardView> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<CoachMarkFactory> coachMarkFactoryProvider;
    private final Provider<CommsHelper> commsHelperProvider;
    private final Provider<RemoteAssistHelper> remoteAssistHelperProvider;
    private final Provider<RoutingService> routingServiceProvider;
    private final Provider<UrlHelper> urlHelperProvider;

    public BilobaDashboardView_MembersInjector(Provider<BilobaMetricsService> provider, Provider<CommsHelper> provider2, Provider<RoutingService> provider3, Provider<CoachMarkFactory> provider4, Provider<RemoteAssistHelper> provider5, Provider<UrlHelper> provider6) {
        this.bilobaMetricsServiceProvider = provider;
        this.commsHelperProvider = provider2;
        this.routingServiceProvider = provider3;
        this.coachMarkFactoryProvider = provider4;
        this.remoteAssistHelperProvider = provider5;
        this.urlHelperProvider = provider6;
    }

    public static MembersInjector<BilobaDashboardView> create(Provider<BilobaMetricsService> provider, Provider<CommsHelper> provider2, Provider<RoutingService> provider3, Provider<CoachMarkFactory> provider4, Provider<RemoteAssistHelper> provider5, Provider<UrlHelper> provider6) {
        return new BilobaDashboardView_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6);
    }

    public static void injectCoachMarkFactory(BilobaDashboardView bilobaDashboardView, Lazy<CoachMarkFactory> lazy) {
        bilobaDashboardView.coachMarkFactory = lazy;
    }

    public static void injectCommsHelper(BilobaDashboardView bilobaDashboardView, Lazy<CommsHelper> lazy) {
        bilobaDashboardView.commsHelper = lazy;
    }

    public static void injectRemoteAssistHelper(BilobaDashboardView bilobaDashboardView, Lazy<RemoteAssistHelper> lazy) {
        bilobaDashboardView.remoteAssistHelper = lazy;
    }

    public static void injectRoutingService(BilobaDashboardView bilobaDashboardView, Lazy<RoutingService> lazy) {
        bilobaDashboardView.routingService = lazy;
    }

    public static void injectUrlHelper(BilobaDashboardView bilobaDashboardView, Lazy<UrlHelper> lazy) {
        bilobaDashboardView.urlHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BilobaDashboardView bilobaDashboardView) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(bilobaDashboardView, this.bilobaMetricsServiceProvider.mo10268get());
        injectCommsHelper(bilobaDashboardView, DoubleCheck.lazy(this.commsHelperProvider));
        injectRoutingService(bilobaDashboardView, DoubleCheck.lazy(this.routingServiceProvider));
        injectCoachMarkFactory(bilobaDashboardView, DoubleCheck.lazy(this.coachMarkFactoryProvider));
        injectRemoteAssistHelper(bilobaDashboardView, DoubleCheck.lazy(this.remoteAssistHelperProvider));
        injectUrlHelper(bilobaDashboardView, DoubleCheck.lazy(this.urlHelperProvider));
    }
}

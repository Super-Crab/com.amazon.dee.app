package com.amazon.alexa.biloba.view.confirmation;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class ConfirmationViewModel_MembersInjector implements MembersInjector<ConfirmationViewModel> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;
    private final Provider<RoutingService> routingServiceProvider;

    public ConfirmationViewModel_MembersInjector(Provider<BilobaMetricsService> provider, Provider<EnvironmentService> provider2, Provider<RoutingService> provider3) {
        this.bilobaMetricsServiceProvider = provider;
        this.environmentServiceProvider = provider2;
        this.routingServiceProvider = provider3;
    }

    public static MembersInjector<ConfirmationViewModel> create(Provider<BilobaMetricsService> provider, Provider<EnvironmentService> provider2, Provider<RoutingService> provider3) {
        return new ConfirmationViewModel_MembersInjector(provider, provider2, provider3);
    }

    public static void injectBilobaMetricsService(ConfirmationViewModel confirmationViewModel, Lazy<BilobaMetricsService> lazy) {
        confirmationViewModel.bilobaMetricsService = lazy;
    }

    public static void injectEnvironmentService(ConfirmationViewModel confirmationViewModel, Lazy<EnvironmentService> lazy) {
        confirmationViewModel.environmentService = lazy;
    }

    public static void injectRoutingService(ConfirmationViewModel confirmationViewModel, Lazy<RoutingService> lazy) {
        confirmationViewModel.routingService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ConfirmationViewModel confirmationViewModel) {
        injectBilobaMetricsService(confirmationViewModel, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectEnvironmentService(confirmationViewModel, DoubleCheck.lazy(this.environmentServiceProvider));
        injectRoutingService(confirmationViewModel, DoubleCheck.lazy(this.routingServiceProvider));
    }
}

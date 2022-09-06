package com.amazon.alexa.biloba.view.gettingStartedV3;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class GettingStartedViewModelV3_MembersInjector implements MembersInjector<GettingStartedViewModelV3> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<EnvironmentService> environmentServiceProvider;

    public GettingStartedViewModelV3_MembersInjector(Provider<BilobaMetricsService> provider, Provider<EnvironmentService> provider2) {
        this.bilobaMetricsServiceProvider = provider;
        this.environmentServiceProvider = provider2;
    }

    public static MembersInjector<GettingStartedViewModelV3> create(Provider<BilobaMetricsService> provider, Provider<EnvironmentService> provider2) {
        return new GettingStartedViewModelV3_MembersInjector(provider, provider2);
    }

    public static void injectBilobaMetricsService(GettingStartedViewModelV3 gettingStartedViewModelV3, Lazy<BilobaMetricsService> lazy) {
        gettingStartedViewModelV3.bilobaMetricsService = lazy;
    }

    public static void injectEnvironmentService(GettingStartedViewModelV3 gettingStartedViewModelV3, Lazy<EnvironmentService> lazy) {
        gettingStartedViewModelV3.environmentService = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(GettingStartedViewModelV3 gettingStartedViewModelV3) {
        injectBilobaMetricsService(gettingStartedViewModelV3, DoubleCheck.lazy(this.bilobaMetricsServiceProvider));
        injectEnvironmentService(gettingStartedViewModelV3, DoubleCheck.lazy(this.environmentServiceProvider));
    }
}

package com.amazon.alexa.biloba.metrics;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class BilobaViewWithMetrics_MembersInjector implements MembersInjector<BilobaViewWithMetrics> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;

    public BilobaViewWithMetrics_MembersInjector(Provider<BilobaMetricsService> provider) {
        this.bilobaMetricsServiceProvider = provider;
    }

    public static MembersInjector<BilobaViewWithMetrics> create(Provider<BilobaMetricsService> provider) {
        return new BilobaViewWithMetrics_MembersInjector(provider);
    }

    public static void injectBilobaMetricsService(BilobaViewWithMetrics bilobaViewWithMetrics, BilobaMetricsService bilobaMetricsService) {
        bilobaViewWithMetrics.bilobaMetricsService = bilobaMetricsService;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BilobaViewWithMetrics bilobaViewWithMetrics) {
        injectBilobaMetricsService(bilobaViewWithMetrics, this.bilobaMetricsServiceProvider.mo10268get());
    }
}

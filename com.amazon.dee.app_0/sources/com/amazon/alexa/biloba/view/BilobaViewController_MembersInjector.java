package com.amazon.alexa.biloba.view;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class BilobaViewController_MembersInjector implements MembersInjector<BilobaViewController> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;

    public BilobaViewController_MembersInjector(Provider<BilobaMetricsService> provider) {
        this.bilobaMetricsServiceProvider = provider;
    }

    public static MembersInjector<BilobaViewController> create(Provider<BilobaMetricsService> provider) {
        return new BilobaViewController_MembersInjector(provider);
    }

    @Override // dagger.MembersInjector
    public void injectMembers(BilobaViewController bilobaViewController) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(bilobaViewController, this.bilobaMetricsServiceProvider.mo10268get());
    }
}

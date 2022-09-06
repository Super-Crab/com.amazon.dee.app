package com.amazon.alexa.biloba.view.comms;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.utils.UrlHelper;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class EmergencyView_MembersInjector implements MembersInjector<EmergencyView> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<UrlHelper> urlHelperProvider;

    public EmergencyView_MembersInjector(Provider<BilobaMetricsService> provider, Provider<UrlHelper> provider2) {
        this.bilobaMetricsServiceProvider = provider;
        this.urlHelperProvider = provider2;
    }

    public static MembersInjector<EmergencyView> create(Provider<BilobaMetricsService> provider, Provider<UrlHelper> provider2) {
        return new EmergencyView_MembersInjector(provider, provider2);
    }

    public static void injectUrlHelper(EmergencyView emergencyView, Lazy<UrlHelper> lazy) {
        emergencyView.urlHelper = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EmergencyView emergencyView) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(emergencyView, this.bilobaMetricsServiceProvider.mo10268get());
        injectUrlHelper(emergencyView, DoubleCheck.lazy(this.urlHelperProvider));
    }
}

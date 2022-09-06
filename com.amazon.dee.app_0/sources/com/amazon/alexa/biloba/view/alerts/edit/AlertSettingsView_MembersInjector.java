package com.amazon.alexa.biloba.view.alerts.edit;

import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics_MembersInjector;
import com.amazon.alexa.biloba.view.BilobaViewModelFactory;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;
/* loaded from: classes6.dex */
public final class AlertSettingsView_MembersInjector implements MembersInjector<AlertSettingsView> {
    private final Provider<BilobaMetricsService> bilobaMetricsServiceProvider;
    private final Provider<BilobaViewModelFactory> viewModelFactoryProvider;

    public AlertSettingsView_MembersInjector(Provider<BilobaMetricsService> provider, Provider<BilobaViewModelFactory> provider2) {
        this.bilobaMetricsServiceProvider = provider;
        this.viewModelFactoryProvider = provider2;
    }

    public static MembersInjector<AlertSettingsView> create(Provider<BilobaMetricsService> provider, Provider<BilobaViewModelFactory> provider2) {
        return new AlertSettingsView_MembersInjector(provider, provider2);
    }

    public static void injectViewModelFactory(AlertSettingsView alertSettingsView, Lazy<BilobaViewModelFactory> lazy) {
        alertSettingsView.viewModelFactory = lazy;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(AlertSettingsView alertSettingsView) {
        BilobaViewWithMetrics_MembersInjector.injectBilobaMetricsService(alertSettingsView, this.bilobaMetricsServiceProvider.mo10268get());
        injectViewModelFactory(alertSettingsView, DoubleCheck.lazy(this.viewModelFactoryProvider));
    }
}

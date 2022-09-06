package com.amazon.dee.app.dependencies;

import android.app.Activity;
import com.amazon.dee.app.ui.util.LocationPermissionMetricHelper;
import com.dee.app.metrics.MetricsService;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class MainModule_ProvideLocationPermissionMetricHelperFactory implements Factory<LocationPermissionMetricHelper> {
    private final Provider<Activity> activityProvider;
    private final Provider<MetricsService> metricsServiceProvider;
    private final MainModule module;

    public MainModule_ProvideLocationPermissionMetricHelperFactory(MainModule mainModule, Provider<Activity> provider, Provider<MetricsService> provider2) {
        this.module = mainModule;
        this.activityProvider = provider;
        this.metricsServiceProvider = provider2;
    }

    public static MainModule_ProvideLocationPermissionMetricHelperFactory create(MainModule mainModule, Provider<Activity> provider, Provider<MetricsService> provider2) {
        return new MainModule_ProvideLocationPermissionMetricHelperFactory(mainModule, provider, provider2);
    }

    public static LocationPermissionMetricHelper provideInstance(MainModule mainModule, Provider<Activity> provider, Provider<MetricsService> provider2) {
        return proxyProvideLocationPermissionMetricHelper(mainModule, provider.mo10268get(), provider2.mo10268get());
    }

    public static LocationPermissionMetricHelper proxyProvideLocationPermissionMetricHelper(MainModule mainModule, Activity activity, MetricsService metricsService) {
        return (LocationPermissionMetricHelper) Preconditions.checkNotNull(mainModule.provideLocationPermissionMetricHelper(activity, metricsService), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationPermissionMetricHelper mo10268get() {
        return provideInstance(this.module, this.activityProvider, this.metricsServiceProvider);
    }
}

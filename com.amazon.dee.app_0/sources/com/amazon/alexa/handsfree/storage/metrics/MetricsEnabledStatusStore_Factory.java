package com.amazon.alexa.handsfree.storage.metrics;

import com.amazon.alexa.handsfree.storage.metrics.ProcessMetricsCacheService;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class MetricsEnabledStatusStore_Factory implements Factory<MetricsEnabledStatusStore> {
    private final Provider<ProcessMetricsCacheService.ServiceHelper> processMetricsCacheServiceHelperProvider;

    public MetricsEnabledStatusStore_Factory(Provider<ProcessMetricsCacheService.ServiceHelper> provider) {
        this.processMetricsCacheServiceHelperProvider = provider;
    }

    public static MetricsEnabledStatusStore_Factory create(Provider<ProcessMetricsCacheService.ServiceHelper> provider) {
        return new MetricsEnabledStatusStore_Factory(provider);
    }

    public static MetricsEnabledStatusStore newMetricsEnabledStatusStore(ProcessMetricsCacheService.ServiceHelper serviceHelper) {
        return new MetricsEnabledStatusStore(serviceHelper);
    }

    public static MetricsEnabledStatusStore provideInstance(Provider<ProcessMetricsCacheService.ServiceHelper> provider) {
        return new MetricsEnabledStatusStore(provider.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public MetricsEnabledStatusStore mo10268get() {
        return provideInstance(this.processMetricsCacheServiceHelperProvider);
    }
}

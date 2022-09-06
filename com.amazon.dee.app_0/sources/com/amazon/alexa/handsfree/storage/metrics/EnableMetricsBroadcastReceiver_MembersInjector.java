package com.amazon.alexa.handsfree.storage.metrics;

import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class EnableMetricsBroadcastReceiver_MembersInjector implements MembersInjector<EnableMetricsBroadcastReceiver> {
    private final Provider<MetricsEnabledStatusStore> mMetricsEnabledStatusStoreProvider;

    public EnableMetricsBroadcastReceiver_MembersInjector(Provider<MetricsEnabledStatusStore> provider) {
        this.mMetricsEnabledStatusStoreProvider = provider;
    }

    public static MembersInjector<EnableMetricsBroadcastReceiver> create(Provider<MetricsEnabledStatusStore> provider) {
        return new EnableMetricsBroadcastReceiver_MembersInjector(provider);
    }

    public static void injectMMetricsEnabledStatusStore(EnableMetricsBroadcastReceiver enableMetricsBroadcastReceiver, MetricsEnabledStatusStore metricsEnabledStatusStore) {
        enableMetricsBroadcastReceiver.mMetricsEnabledStatusStore = metricsEnabledStatusStore;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(EnableMetricsBroadcastReceiver enableMetricsBroadcastReceiver) {
        injectMMetricsEnabledStatusStore(enableMetricsBroadcastReceiver, this.mMetricsEnabledStatusStoreProvider.mo10268get());
    }
}

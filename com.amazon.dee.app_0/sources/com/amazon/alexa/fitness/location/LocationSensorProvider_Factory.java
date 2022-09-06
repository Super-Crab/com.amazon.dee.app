package com.amazon.alexa.fitness.location;

import com.amazon.alexa.fitness.api.LocationService;
import com.amazon.alexa.fitness.api.UserPreferenceStore;
import com.amazon.alexa.fitness.logs.ILog;
import com.amazon.alexa.fitness.metrics.MetricsAggregator;
import com.amazon.alexa.fitness.sdk.AfxMessageProcessor;
import dagger.internal.Factory;
import javax.inject.Provider;
/* loaded from: classes8.dex */
public final class LocationSensorProvider_Factory implements Factory<LocationSensorProvider> {
    private final Provider<AfxMessageProcessor> afxMessageProcessorProvider;
    private final Provider<LocationService> locationServiceProvider;
    private final Provider<ILog> logProvider;
    private final Provider<MetricsAggregator> metricsAggregatorProvider;
    private final Provider<UserPreferenceStore> userPreferenceStoreProvider;

    public LocationSensorProvider_Factory(Provider<AfxMessageProcessor> provider, Provider<LocationService> provider2, Provider<MetricsAggregator> provider3, Provider<UserPreferenceStore> provider4, Provider<ILog> provider5) {
        this.afxMessageProcessorProvider = provider;
        this.locationServiceProvider = provider2;
        this.metricsAggregatorProvider = provider3;
        this.userPreferenceStoreProvider = provider4;
        this.logProvider = provider5;
    }

    public static LocationSensorProvider_Factory create(Provider<AfxMessageProcessor> provider, Provider<LocationService> provider2, Provider<MetricsAggregator> provider3, Provider<UserPreferenceStore> provider4, Provider<ILog> provider5) {
        return new LocationSensorProvider_Factory(provider, provider2, provider3, provider4, provider5);
    }

    public static LocationSensorProvider newLocationSensorProvider(AfxMessageProcessor afxMessageProcessor, LocationService locationService, MetricsAggregator metricsAggregator, UserPreferenceStore userPreferenceStore, ILog iLog) {
        return new LocationSensorProvider(afxMessageProcessor, locationService, metricsAggregator, userPreferenceStore, iLog);
    }

    public static LocationSensorProvider provideInstance(Provider<AfxMessageProcessor> provider, Provider<LocationService> provider2, Provider<MetricsAggregator> provider3, Provider<UserPreferenceStore> provider4, Provider<ILog> provider5) {
        return new LocationSensorProvider(provider.mo10268get(), provider2.mo10268get(), provider3.mo10268get(), provider4.mo10268get(), provider5.mo10268get());
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public LocationSensorProvider mo10268get() {
        return provideInstance(this.afxMessageProcessorProvider, this.locationServiceProvider, this.metricsAggregatorProvider, this.userPreferenceStoreProvider, this.logProvider);
    }
}

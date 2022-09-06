package com.amazon.alexa.accessorykit.factory;

import android.content.Context;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.davs.DefaultDavsClient;
import com.amazon.alexa.accessory.engagement.DeviceEngagementMetrics;
import com.amazon.alexa.accessory.engagement.DeviceEngagementMetricsTriggers;
import com.amazon.alexa.accessory.frames.TopContact;
import com.amazon.alexa.accessory.internal.EndpointProvider;
import com.amazon.alexa.accessory.internal.UnavailableNonHfpCallController;
import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.MetricsSupplier;
import com.amazon.alexa.accessory.notificationpublisher.FocusFilter;
import com.amazon.alexa.accessory.persistence.device.DeviceDatabase;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessorykit.findmy.AccessoryGooglePlayLocationProvider;
import com.amazon.alexa.accessorykit.interprocess.feature.InterprocessFeatureChecker;
import com.amazon.alexa.accessorykit.metrics.MetricRecorder;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.MobilyticsFactory;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import dagger.Lazy;
import okhttp3.OkHttpClient;
/* loaded from: classes6.dex */
public class DefaultDependenciesProvider implements DependenciesProvider {
    static /* synthetic */ Mobilytics lambda$createAccessoryMetricsService$0(Mobilytics mobilytics) {
        return mobilytics;
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public AccessoryGooglePlayLocationProvider createAccessoryGooglePlayLocationProvider(Context context) {
        return new AccessoryGooglePlayLocationProvider(context);
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public AccessoryMetricsService createAccessoryMetricsService(final Mobilytics mobilytics) {
        return createAccessoryMetricsService(new Lazy() { // from class: com.amazon.alexa.accessorykit.factory.-$$Lambda$DefaultDependenciesProvider$iqXCqX2LzbmMX2zKXMhU1AnrxSk
            @Override // dagger.Lazy
            /* renamed from: get */
            public final Object mo358get() {
                return Mobilytics.this;
            }
        });
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public DavsClient createDavsClient(Context context, UserSupplier userSupplier, EndpointProvider endpointProvider, DeviceAccountSupplier deviceAccountSupplier) {
        return new DefaultDavsClient(context, userSupplier, endpointProvider, deviceAccountSupplier);
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public DeviceSupplier createDeviceSupplier(Context context) {
        return new DeviceDatabase(context);
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public FocusFilter createFocusFilter() {
        return new FocusFilter();
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public InterprocessFeatureChecker createInterProcessFeatureChecker(Context context) {
        return new InterprocessFeatureChecker(context);
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public Mobilytics createMobilytics(MobilyticsConfiguration.Builder builder) {
        return MobilyticsFactory.getMobilytics(builder.build());
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public NonHfpCallController createNonHfpCallController() {
        return new UnavailableNonHfpCallController();
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public TopContact createTopContact() {
        return new TopContact();
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public DeviceEngagementMetricsTriggers createTriggerDeviceEngagementMetrics(Context context, NetworkStatusMonitor networkStatusMonitor, SessionSupplier sessionSupplier, MetricsSupplier metricsSupplier) {
        return DeviceEngagementMetrics.triggersFrom(context, networkStatusMonitor, sessionSupplier, metricsSupplier);
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public void initFocusFilterDependencies(Context context, Lazy<OkHttpClient> lazy, Lazy<Cache<AppDataCacheEntry>> lazy2) {
        FocusFilter.initDependencies(context, lazy, lazy2);
    }

    @Override // com.amazon.alexa.accessorykit.factory.DependenciesProvider
    public AccessoryMetricsService createAccessoryMetricsService(Lazy<Mobilytics> lazy) {
        return new MetricRecorder(lazy);
    }
}

package com.amazon.alexa.accessorykit.factory;

import android.content.Context;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.UserSupplier;
import com.amazon.alexa.accessory.davs.DavsClient;
import com.amazon.alexa.accessory.engagement.DeviceEngagementMetricsTriggers;
import com.amazon.alexa.accessory.frames.TopContact;
import com.amazon.alexa.accessory.internal.EndpointProvider;
import com.amazon.alexa.accessory.internal.monitor.NetworkStatusMonitor;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessory.metrics.MetricsSupplier;
import com.amazon.alexa.accessory.notificationpublisher.FocusFilter;
import com.amazon.alexa.accessory.registration.deviceaccount.DeviceAccountSupplier;
import com.amazon.alexa.accessory.repositories.device.DeviceSupplier;
import com.amazon.alexa.accessory.repositories.nonhfpcalling.NonHfpCallController;
import com.amazon.alexa.accessorykit.findmy.AccessoryGooglePlayLocationProvider;
import com.amazon.alexa.accessorykit.interprocess.feature.InterprocessFeatureChecker;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.dee.app.cachemanager.AppDataCacheEntry;
import com.dee.app.cachemanager.Cache;
import dagger.Lazy;
import okhttp3.OkHttpClient;
/* loaded from: classes6.dex */
public interface DependenciesProvider {
    AccessoryGooglePlayLocationProvider createAccessoryGooglePlayLocationProvider(Context context);

    AccessoryMetricsService createAccessoryMetricsService(Mobilytics mobilytics);

    AccessoryMetricsService createAccessoryMetricsService(Lazy<Mobilytics> lazy);

    DavsClient createDavsClient(Context context, UserSupplier userSupplier, EndpointProvider endpointProvider, DeviceAccountSupplier deviceAccountSupplier);

    DeviceSupplier createDeviceSupplier(Context context);

    FocusFilter createFocusFilter();

    InterprocessFeatureChecker createInterProcessFeatureChecker(Context context);

    Mobilytics createMobilytics(MobilyticsConfiguration.Builder builder);

    NonHfpCallController createNonHfpCallController();

    TopContact createTopContact();

    DeviceEngagementMetricsTriggers createTriggerDeviceEngagementMetrics(Context context, NetworkStatusMonitor networkStatusMonitor, SessionSupplier sessionSupplier, MetricsSupplier metricsSupplier);

    void initFocusFilterDependencies(Context context, Lazy<OkHttpClient> lazy, Lazy<Cache<AppDataCacheEntry>> lazy2);
}

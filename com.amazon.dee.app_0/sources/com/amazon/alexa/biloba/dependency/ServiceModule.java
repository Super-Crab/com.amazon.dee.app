package com.amazon.alexa.biloba.dependency;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.biloba.generated.network.AppSchedulerProvider;
import com.amazon.alexa.biloba.generated.network.SchedulerProvider;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.service.BilobaFrontEndService;
import com.amazon.alexa.biloba.service.BilobaFrontEndServiceUrlResolver;
import com.amazon.alexa.biloba.service.BilobaPersonIdProvider;
import com.amazon.alexa.biloba.service.BilobaUrlResolver;
import com.amazon.alexa.biloba.service.DefaultBilobaFrontEndService;
import com.amazon.alexa.biloba.service.FrontEndServiceRequest;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.PersonIdProvider;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class ServiceModule {
    @Provides
    @Singleton
    public BilobaFrontEndService provideBilobaFrontEndService(FrontEndServiceRequest frontEndServiceRequest) {
        return new DefaultBilobaFrontEndService(frontEndServiceRequest);
    }

    @Provides
    @Singleton
    public BilobaFrontEndServiceUrlResolver provideBilobaFrontEndServiceUrlResolver() {
        return new BilobaFrontEndServiceUrlResolver();
    }

    @Provides
    @Singleton
    public BilobaMetricsService provideBilobaMetricsService(Mobilytics mobilytics, Lazy<EnvironmentService> lazy) {
        return new BilobaMetricsService(mobilytics, lazy);
    }

    @Provides
    @Singleton
    public BilobaUrlResolver provideBilobaUrlResolver(EnvironmentService environmentService) {
        return new BilobaUrlResolver(environmentService);
    }

    @Provides
    @Singleton
    public FrontEndServiceRequest provideFrontEndServiceRequests(BilobaFrontEndServiceUrlResolver bilobaFrontEndServiceUrlResolver) {
        return new FrontEndServiceRequest(bilobaFrontEndServiceUrlResolver);
    }

    @Provides
    @Singleton
    public PersonIdProvider providePersonIdProvider(IdentityService identityService, BilobaMetricsService bilobaMetricsService) {
        return new BilobaPersonIdProvider(identityService, bilobaMetricsService);
    }

    @Provides
    @Singleton
    public RemoteManagementInactivityHandler provideRemoteManagementInactivityHandler(Lazy<EventBus> lazy, Lazy<IdentityService> lazy2, Lazy<MainActivityLifecycleObserverRegistrar> lazy3, Lazy<RoutingService> lazy4, Lazy<CareActorsStore> lazy5) {
        return new RemoteManagementInactivityHandler(lazy, lazy2, lazy3, lazy4, lazy5, new Handler(Looper.getMainLooper()));
    }

    @Provides
    @Singleton
    public SchedulerProvider provideScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    public UrlHelper provideUrlHelper(Lazy<EnvironmentService> lazy, Lazy<CareActorsStore> lazy2, Lazy<FeatureServiceV2> lazy3) {
        return new UrlHelper(lazy, lazy2, lazy3);
    }
}

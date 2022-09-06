package com.amazon.alexa.biloba.dependency;

import com.amazon.alexa.biloba.view.BilobaViewModelFactory;
import com.amazon.alexa.crashreporting.api.CrashMetadata;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.imageloader.api.ImageLoader;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.network.api.HttpClient;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.latencyinfra.LatencyInfra;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.api.ElementLocalStorage;
import com.dee.app.http.CoralService;
import com.dee.app.http.HttpCoralService;
import com.dee.app.metrics.MetricsServiceV2;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public final class ApplicationModule {
    @Provides
    @Singleton
    public CoachMarkFactory provideCoachMarkFactory() {
        return (CoachMarkFactory) GeneratedOutlineSupport1.outline20(CoachMarkFactory.class);
    }

    @Provides
    @Singleton
    public CoralService provideCoralService() {
        CoralService coralService = (CoralService) GeneratedOutlineSupport1.outline20(CoralService.class);
        if (coralService instanceof HttpCoralService) {
            ((HttpCoralService) coralService).addRequestInterceptor(new AcceptLanguageRequestInterceptor());
        }
        return coralService;
    }

    @Provides
    @Singleton
    public CrashMetadata provideCrashMetadata() {
        return (CrashMetadata) GeneratedOutlineSupport1.outline20(CrashMetadata.class);
    }

    @Provides
    @Singleton
    public CrashReporter provideCrashReporter() {
        return (CrashReporter) GeneratedOutlineSupport1.outline20(CrashReporter.class);
    }

    @Provides
    @Singleton
    public DeviceInformation provideDeviceInformation() {
        return (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
    }

    @Provides
    @Singleton
    public ElementLocalStorage provideElementLocalStorage() {
        return (ElementLocalStorage) GeneratedOutlineSupport1.outline20(ElementLocalStorage.class);
    }

    @Provides
    @Singleton
    public EnvironmentService provideEnvironmentService() {
        return (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);
    }

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return (EventBus) GeneratedOutlineSupport1.outline20(EventBus.class);
    }

    @Provides
    @Singleton
    public FeatureServiceV2 provideFeatureServiceV2() {
        return (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
    }

    @Provides
    @Singleton
    public HttpClient provideHttpClient() {
        return (HttpClient) GeneratedOutlineSupport1.outline20(HttpClient.class);
    }

    @Provides
    @Singleton
    public IdentityService provideIdentityService() {
        return (IdentityService) GeneratedOutlineSupport1.outline20(IdentityService.class);
    }

    @Provides
    @Singleton
    public ImageLoader provideImageLoader() {
        return (ImageLoader) GeneratedOutlineSupport1.outline20(ImageLoader.class);
    }

    @Provides
    @Singleton
    public LatencyInfra provideLatencyInfra() {
        return (LatencyInfra) GeneratedOutlineSupport1.outline20(LatencyInfra.class);
    }

    @Provides
    @Singleton
    public MainActivityLifecycleObserverRegistrar provideMainActivityLifecycleObserverRegistrar() {
        return (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline20(MainActivityLifecycleObserverRegistrar.class);
    }

    @Provides
    @Singleton
    public MetricsServiceV2 provideMetricsService() {
        return (MetricsServiceV2) GeneratedOutlineSupport1.outline20(MetricsServiceV2.class);
    }

    @Provides
    @Singleton
    public Mobilytics provideMobilytics() {
        return (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }

    @Provides
    public NetworkService provideNetworkService() {
        return (NetworkService) GeneratedOutlineSupport1.outline20(NetworkService.class);
    }

    @Provides
    @Singleton
    public PersistentStorage.Factory providePersistentStorageFactory() {
        return (PersistentStorage.Factory) GeneratedOutlineSupport1.outline20(PersistentStorage.Factory.class);
    }

    @Provides
    @Singleton
    public RoutingService provideRoutingService() {
        return (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    }

    @Provides
    @Singleton
    public ExecutorService provideShortLivedTaskExecutor() {
        return ((TaskManager) GeneratedOutlineSupport1.outline20(TaskManager.class)).getExecutor(0);
    }

    @Provides
    @Singleton
    public BilobaViewModelFactory provideViewModelFactory() {
        return BilobaViewModelFactory.getInstance();
    }
}

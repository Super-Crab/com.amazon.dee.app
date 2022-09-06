package com.amazon.alexa.voice.app;

import android.content.Context;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.AccountUpgradeService;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.lifecycle.ApplicationLifecycleService;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.storage.PersistentStorage;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.regulator.Component;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public final class ApplicationModule {
    private final Component applicationDependencies;

    public ApplicationModule(Component component) {
        this.applicationDependencies = component;
    }

    @Provides
    @Singleton
    public static FeatureServiceV2 provideFeatureServiceV2() {
        return (FeatureServiceV2) GeneratedOutlineSupport1.outline20(FeatureServiceV2.class);
    }

    @Provides
    @Singleton
    public AccountUpgradeService provideAccountUpgradeService() {
        return (AccountUpgradeService) this.applicationDependencies.get(AccountUpgradeService.class);
    }

    @Provides
    @Singleton
    public ApplicationLifecycleService provideApplicationLifecycleService() {
        return (ApplicationLifecycleService) this.applicationDependencies.get(ApplicationLifecycleService.class);
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return (Context) this.applicationDependencies.get(Context.class);
    }

    @Provides
    @Singleton
    public DeviceInformation provideDeviceInformation() {
        return (DeviceInformation) this.applicationDependencies.get(DeviceInformation.class);
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
    public IdentityService provideIdentityService() {
        return (IdentityService) this.applicationDependencies.get(IdentityService.class);
    }

    @Provides
    @Singleton
    public LatencyReportingDelegate provideLatencyReportingDelegate() {
        return (LatencyReportingDelegate) this.applicationDependencies.get(LatencyReportingDelegate.class);
    }

    @Provides
    @Singleton
    public MainActivityLifecycleObserverRegistrar provideMainActivityLifecycleObserverRegistrar() {
        return (MainActivityLifecycleObserverRegistrar) GeneratedOutlineSupport1.outline20(MainActivityLifecycleObserverRegistrar.class);
    }

    @Provides
    @Singleton
    public Mobilytics provideMobilytics() {
        return (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }

    @Provides
    @Singleton
    public NetworkService provideNetworkService() {
        return (NetworkService) this.applicationDependencies.get(NetworkService.class);
    }

    @Provides
    @Singleton
    public PersistentStorage.Factory providePersistentStorageFactory() {
        return (PersistentStorage.Factory) this.applicationDependencies.get(PersistentStorage.Factory.class);
    }

    @Provides
    @Singleton
    public RoutingService provideRoutingService() {
        return (RoutingService) GeneratedOutlineSupport1.outline20(RoutingService.class);
    }
}

package com.amazon.alexa.redesign.dependency;

import android.content.Context;
import com.amazon.alexa.elements.api.BridgeStatusService;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.lifecycle.MainActivityLifecycleObserverRegistrar;
import com.amazon.alexa.protocols.network.NetworkService;
import com.amazon.alexa.protocols.usermessage.UserMessageService;
import com.amazon.alexa.redesign.LatencyReportingDelegate;
import com.amazon.alexa.redesign.header.HeaderNavigationDelegate;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.alexa.tasks.api.TaskManager;
import com.amazon.alexa.voice.model.VoiceService;
import com.amazon.latencyinfra.LatencyInfra;
import com.amazon.regulator.Component;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import com.dee.app.metrics.MetricsServiceV2;
import com.facebook.react.ReactInstanceManager;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
@Module
/* loaded from: classes10.dex */
public final class ApplicationModule {
    private final Component component;

    public ApplicationModule(Component component) {
        this.component = component;
    }

    @Provides
    @Singleton
    public BridgeStatusService provideBridgeStatusService() {
        return (BridgeStatusService) this.component.get(BridgeStatusService.class);
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return (Context) this.component.get(Context.class);
    }

    @Provides
    @Singleton
    public CoralService provideCoralService() {
        return (CoralService) GeneratedOutlineSupport1.outline20(CoralService.class);
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
    public HeaderNavigationDelegate provideHeaderNavigationDelegate() {
        return (HeaderNavigationDelegate) this.component.get(HeaderNavigationDelegate.class);
    }

    @Provides
    @Singleton
    public IdentityService provideIdentityService() {
        return (IdentityService) this.component.get(IdentityService.class);
    }

    @Provides
    @Singleton
    public LatencyInfra provideLatencyInfra() {
        return (LatencyInfra) GeneratedOutlineSupport1.outline20(LatencyInfra.class);
    }

    @Provides
    @Singleton
    public LatencyReportingDelegate provideLatencyReportingDelegate() {
        return (LatencyReportingDelegate) this.component.get(LatencyReportingDelegate.class);
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
    public ReactInstanceManager provideReactInstanceManager() {
        return (ReactInstanceManager) this.component.get(ReactInstanceManager.class);
    }

    @Provides
    @Singleton
    public RoutingService provideRoutingService() {
        return (RoutingService) this.component.get(RoutingService.class);
    }

    @Provides
    @Singleton
    public ExecutorService provideShortLivedTaskExecutor() {
        return ((TaskManager) GeneratedOutlineSupport1.outline20(TaskManager.class)).getExecutor(0);
    }

    @Provides
    @Singleton
    public UserMessageService provideUserMessageService() {
        return (UserMessageService) this.component.get(UserMessageService.class);
    }

    @Provides
    @Singleton
    public VoiceService provideVoiceService() {
        return (VoiceService) this.component.get(VoiceService.class);
    }
}

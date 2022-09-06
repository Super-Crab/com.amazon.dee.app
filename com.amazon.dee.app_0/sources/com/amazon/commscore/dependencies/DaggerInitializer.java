package com.amazon.commscore.dependencies;

import android.content.Context;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.commscore.commsbridge.dependencies.CommsBridgeServiceModule;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule;
import com.amazon.commscore.featureflag.dependencies.FeatureFlagModule;
import com.amazon.commscore.metrics.dependencies.MetricServiceModule;
import dagger.Lazy;
/* loaded from: classes12.dex */
public final class DaggerInitializer {
    private static CommsCoreComponent commsCoreComponent;

    private DaggerInitializer() {
    }

    protected static void cleanup() {
        commsCoreComponent = null;
    }

    public static CommsCoreComponent get() {
        CommsCoreComponent commsCoreComponent2 = commsCoreComponent;
        if (commsCoreComponent2 != null) {
            return commsCoreComponent2;
        }
        throw new IllegalStateException(" CommsCoreComponent must be initialized before use. Call initialize(...).");
    }

    public static void initialize(Context context, Lazy<IdentityService> lazy, Lazy<Mobilytics> lazy2, Lazy<EventBus> lazy3, Lazy<EnvironmentService> lazy4) {
        if (commsCoreComponent == null) {
            MetricServiceModule metricServiceModule = new MetricServiceModule(lazy2);
            commsCoreComponent = DaggerCommsCoreComponent.builder().applicationModule(new ApplicationModule(context)).featureFlagModule(new FeatureFlagModule(lazy)).metricServiceModule(metricServiceModule).commsIdentityModule(new CommsIdentityModule(lazy, lazy4)).commsBridgeServiceModule(new CommsBridgeServiceModule(lazy3, metricServiceModule.providesMetricsService())).alexaModule(new AlexaModule(lazy3)).build();
        }
    }
}

package com.amazon.commscore.dependencies;

import android.content.Context;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.featureflag.AlexaCommsCoreFeatureService;
import com.amazon.commscore.api.identity.AlexaCommsCoreIdentityService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.commsbridge.dependencies.CommsBridgeServiceModule;
import com.amazon.commscore.commsidentity.dependencies.CommsIdentityModule;
import com.amazon.commscore.dependencies.annotation.ApplicationContext;
import com.amazon.commscore.featureflag.dependencies.FeatureFlagModule;
import com.amazon.commscore.metrics.dependencies.MetricServiceModule;
import dagger.Component;
import javax.inject.Singleton;
@Component(modules = {ApplicationModule.class, FeatureFlagModule.class, MetricServiceModule.class, CommsIdentityModule.class, CommsBridgeServiceModule.class, AlexaModule.class})
@Singleton
/* loaded from: classes12.dex */
public interface CommsCoreComponent {
    CommsBridgeService getCommsBridgeService();

    @ApplicationContext
    Context getContext();

    AlexaCommsCoreFeatureService getFeatureService();

    AlexaCommsCoreIdentityService getIdentityService();

    AlexaCommsCoreMetricsService getMetricsService();
}

package com.amazon.commscore.metrics.dependencies;

import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.metrics.DefaultAlexaCommsCoreMetricsService;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class MetricServiceModule {
    private Lazy<Mobilytics> mobilyticsLazy;

    public MetricServiceModule(Lazy<Mobilytics> lazy) {
        this.mobilyticsLazy = lazy;
    }

    @Provides
    @Singleton
    public AlexaCommsCoreMetricsService providesMetricsService() {
        return new DefaultAlexaCommsCoreMetricsService(this.mobilyticsLazy);
    }
}

package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.alertsca.metrics.service.AlertsDefaultMetricsService;
import com.amazon.alexa.alertsca.metrics.service.MetricsService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public final class MetricsModule {
    @Provides
    @Singleton
    public MetricsService provideMetricsService(Mobilytics mobilytics) {
        return new AlertsDefaultMetricsService(mobilytics);
    }

    @Provides
    @Singleton
    public Mobilytics provideMobilytics() {
        return (Mobilytics) GeneratedOutlineSupport1.outline20(Mobilytics.class);
    }
}

package com.amazon.commscore.commsbridge.dependencies;

import androidx.annotation.NonNull;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.commscore.api.commsbridge.CommsBridgeService;
import com.amazon.commscore.api.metrics.AlexaCommsCoreMetricsService;
import com.amazon.commscore.commsbridge.CommsBridgeServiceImpl;
import dagger.Lazy;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes12.dex */
public class CommsBridgeServiceModule {
    private Lazy<EventBus> eventBusLazy;
    private AlexaCommsCoreMetricsService metricsService;

    public CommsBridgeServiceModule(@NonNull Lazy<EventBus> lazy, AlexaCommsCoreMetricsService alexaCommsCoreMetricsService) {
        this.eventBusLazy = lazy;
        this.metricsService = alexaCommsCoreMetricsService;
    }

    @Provides
    @Singleton
    public CommsBridgeService providesCommsBridgeService() {
        return new CommsBridgeServiceImpl(this.eventBusLazy.mo358get(), this.metricsService);
    }
}

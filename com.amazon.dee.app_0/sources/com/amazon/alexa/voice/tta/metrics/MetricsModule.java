package com.amazon.alexa.voice.tta.metrics;

import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes11.dex */
public class MetricsModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public EventClock providesEventClock() {
        return EventTime.DefaultClock;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public MetricEventProcessingService providesMetricEventProcessingService(TtaUiEventProcessor ttaUiEventProcessor, TtaIngressEventProcessor ttaIngressEventProcessor, TtaUplEventProcessor ttaUplEventProcessor, TtaUiEventWithTypeProcessor ttaUiEventWithTypeProcessor, SimbaEventProcessor simbaEventProcessor, TtaUiEventWithCountProcessor ttaUiEventWithCountProcessor) {
        return new MetricEventProcessingService($$Lambda$IjuVGjLmqj_Yb55snHAn3jUnsvE.INSTANCE, ttaUiEventProcessor, ttaIngressEventProcessor, ttaUplEventProcessor, ttaUiEventWithTypeProcessor, simbaEventProcessor, ttaUiEventWithCountProcessor);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public SimbaEventProcessor providesSimbaEventProcessor(UiEventReporter uiEventReporter) {
        return new SimbaEventProcessor(uiEventReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaUiEventWithTypeProcessor providesTtaEventProcessor(UiEventReporter uiEventReporter) {
        return new TtaUiEventWithTypeProcessor(uiEventReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaEventSender providesTtaEventSender(MetricEventProcessingService metricEventProcessingService) {
        return new DefaultEventSender(metricEventProcessingService);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaIngressEventProcessor providesTtaIngressEventProcessor(UiEventReporter uiEventReporter) {
        return new TtaIngressEventProcessor(uiEventReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaUiEventProcessor providesTtaUiEventProcessor(UiEventReporter uiEventReporter) {
        return new TtaUiEventProcessor(uiEventReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaUiEventWithCountProcessor providesTtaUiEventWithDataProcessor(UiEventReporter uiEventReporter) {
        return new TtaUiEventWithCountProcessor(uiEventReporter);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public TtaUplEventProcessor providesTtaUplEventProcessor(UiEventReporter uiEventReporter) {
        return new TtaUplEventProcessor(uiEventReporter);
    }
}

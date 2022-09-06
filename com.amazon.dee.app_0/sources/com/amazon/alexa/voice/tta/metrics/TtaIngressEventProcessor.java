package com.amazon.alexa.voice.tta.metrics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor;
import com.amazon.alexa.voice.tta.metrics.LatencyTimer;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import java.util.Locale;
/* loaded from: classes11.dex */
public class TtaIngressEventProcessor implements MetricEventProcessor {
    private static final String TAG = "TtaIngressEventProcessor";
    final AggregateEventProcessor innerProcessor;
    private final UiEventReporter sdkEventReporter;
    private final LatencyTimer<TtaIngressEvent, TtaUiEvent> timer = new LatencyTimer<>(new LatencyTimer.RecordLatency() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$TtaIngressEventProcessor$yEsubiYmyNikpk8btw4soEh3hMo
        @Override // com.amazon.alexa.voice.tta.metrics.LatencyTimer.RecordLatency
        public final void recordLatency(Object obj, EventTime eventTime, Object obj2, EventTime eventTime2) {
            TtaIngressEventProcessor.this.record((TtaIngressEvent) obj, eventTime, (TtaUiEvent) obj2, eventTime2);
        }
    });
    private static final UiEventName LAUNCH_UI_EVENT_NAME = UiEventName.VOX_TTA_LAUNCHED;
    private static final UiEventName LATENCY_UI_EVENT_NAME = UiEventName.VOX_TTA_VISIBLE_LATENCY;

    public TtaIngressEventProcessor(@NonNull UiEventReporter uiEventReporter) {
        AggregateEventProcessor.Builder<U> hasType = new AggregateEventProcessor(new MetricEventProcessor[0]).whenEvent().hasType(TtaIngressEvent.class);
        final LatencyTimer<TtaIngressEvent, TtaUiEvent> latencyTimer = this.timer;
        latencyTimer.getClass();
        AggregateEventProcessor.Builder isEqualTo = hasType.thenDo(new AggregateEventProcessor.Builder.OnEvent() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$UjyqcDYaooYR7XPttaUwC1J3nnA
            @Override // com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor.Builder.OnEvent
            public final void onEvent(Object obj, EventTime eventTime) {
                LatencyTimer.this.begin((TtaIngressEvent) obj, eventTime);
            }
        }).whenEvent().isEqualTo(TtaUiEvent.TYPING_SCREEN_VISIBLE);
        LatencyTimer<TtaIngressEvent, TtaUiEvent> latencyTimer2 = this.timer;
        latencyTimer2.getClass();
        this.innerProcessor = isEqualTo.thenDo(new $$Lambda$o4ftQieWudvnDVyyl6Mj71efwzU(latencyTimer2)).whenEvent().isEqualTo(MetricsLifecycleEvent.EVENT_SERVICE_STOP).thenDo(new AggregateEventProcessor.Builder.OnEvent() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$TtaIngressEventProcessor$ujcOAotc-OcErSr7oxyyvspJ2gw
            @Override // com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor.Builder.OnEvent
            public final void onEvent(Object obj, EventTime eventTime) {
                TtaIngressEventProcessor.this.onReset((MetricsLifecycleEvent) obj, eventTime);
            }
        });
        this.sdkEventReporter = uiEventReporter;
    }

    @NonNull
    private String metricSource(@NonNull TtaIngressEvent ttaIngressEvent) {
        return ttaIngressEvent.getReferrer().toUpperCase(Locale.ENGLISH);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReset(@NonNull TtaEvent ttaEvent, @Nullable EventTime eventTime) {
        this.timer.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void record(@NonNull TtaIngressEvent ttaIngressEvent, @NonNull EventTime eventTime, @NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime2) {
        String metricSource = metricSource(ttaIngressEvent);
        recordLaunch(metricSource);
        recordLatency(metricSource, eventTime, eventTime2);
    }

    private void recordLatency(@NonNull String str, @NonNull EventTime eventTime, @NonNull EventTime eventTime2) {
        long differenceMilliseconds = eventTime2.differenceMilliseconds(eventTime);
        String.format("Recording metric %s:%s with %d ms of latency", LATENCY_UI_EVENT_NAME.name(), str, Long.valueOf(differenceMilliseconds));
        Bundle bundle = new Bundle();
        bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), differenceMilliseconds);
        bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), str);
        this.sdkEventReporter.sendEvent(LATENCY_UI_EVENT_NAME, bundle);
    }

    private void recordLaunch(@NonNull String str) {
        Bundle bundle = new Bundle();
        String.format("Recording metric %s:%s", LAUNCH_UI_EVENT_NAME.name(), str);
        bundle.putString(AlexaMetadataBundleKey.SOURCE.name(), str);
        this.sdkEventReporter.sendEvent(LAUNCH_UI_EVENT_NAME, bundle);
    }

    @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
    public boolean processEvent(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime) {
        return this.innerProcessor.processEvent(ttaEvent, eventTime);
    }
}

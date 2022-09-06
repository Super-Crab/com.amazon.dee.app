package com.amazon.alexa.voice.tta.metrics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaMetadataBundleKey;
import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor;
import com.amazon.alexa.voice.tta.metrics.LatencyTimer;
import com.amazon.alexa.voice.tta.sdk.UiEventReporter;
import com.amazon.alexa.voice.ui.onedesign.tta.metrics.TtaUiEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
/* loaded from: classes11.dex */
public class TtaUplEventProcessor implements MetricEventProcessor {
    private static final String TAG = "TtaUplEventProcessor";
    private static final UiEventName UPL_LATENCY_EVENT_NAME = UiEventName.VOX_TTA_RESPONSE_MESSAGE_RENDER_LATENCY;
    private final AggregateEventProcessor innerProcessor;
    private final UiEventReporter sdkEventReporter;
    private final LatencyTimer<TtaUiEvent, TtaUiEvent> timer = new LatencyTimer<>(new LatencyTimer.RecordLatency() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$TtaUplEventProcessor$jdqiR1nMJ2smnxsO9XpDFxpj3Ac
        @Override // com.amazon.alexa.voice.tta.metrics.LatencyTimer.RecordLatency
        public final void recordLatency(Object obj, EventTime eventTime, Object obj2, EventTime eventTime2) {
            TtaUplEventProcessor.this.recordLatency((TtaUiEvent) obj, eventTime, (TtaUiEvent) obj2, eventTime2);
        }
    });

    public TtaUplEventProcessor(@NonNull UiEventReporter uiEventReporter) {
        AggregateEventProcessor.Builder isEqualTo = new AggregateEventProcessor(new MetricEventProcessor[0]).whenEvent().isEqualTo(TtaUiEvent.MESSAGE_SENT);
        final LatencyTimer<TtaUiEvent, TtaUiEvent> latencyTimer = this.timer;
        latencyTimer.getClass();
        AggregateEventProcessor.Builder isEqualTo2 = isEqualTo.thenDo(new AggregateEventProcessor.Builder.OnEvent() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$HDfFkNjQcUkFgIDg24ljZNjymYM
            @Override // com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor.Builder.OnEvent
            public final void onEvent(Object obj, EventTime eventTime) {
                LatencyTimer.this.begin((TtaUiEvent) obj, eventTime);
            }
        }).whenEvent().isEqualTo(TtaUiEvent.RESPONSE_MESSAGE_RENDER);
        LatencyTimer<TtaUiEvent, TtaUiEvent> latencyTimer2 = this.timer;
        latencyTimer2.getClass();
        this.innerProcessor = isEqualTo2.thenDo(new $$Lambda$o4ftQieWudvnDVyyl6Mj71efwzU(latencyTimer2)).whenEvent().isOneOf(TtaConversationEvent.MESSAGE_REFRESH_SHOWN, TtaInternalEvent.SCREEN_LAUNCHED, TtaInternalEvent.ACTIVITY_RESUMED, TtaInternalEvent.ACTIVITY_PAUSED, MetricsLifecycleEvent.EVENT_SERVICE_STOP).thenDo(new AggregateEventProcessor.Builder.OnEvent() { // from class: com.amazon.alexa.voice.tta.metrics.-$$Lambda$TtaUplEventProcessor$rsTHsMyMii8WCFWa6Lwv5IhbU10
            @Override // com.amazon.alexa.voice.tta.metrics.AggregateEventProcessor.Builder.OnEvent
            public final void onEvent(Object obj, EventTime eventTime) {
                TtaUplEventProcessor.this.onReset((TtaEvent) obj, eventTime);
            }
        });
        this.sdkEventReporter = uiEventReporter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onReset(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime) {
        this.timer.reset();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordLatency(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime, @NonNull TtaEvent ttaEvent2, @NonNull EventTime eventTime2) {
        long differenceMilliseconds = eventTime2.differenceMilliseconds(eventTime);
        String.format("Recording metric %s with %d ms of latency", UPL_LATENCY_EVENT_NAME.name(), Long.valueOf(differenceMilliseconds));
        Bundle bundle = new Bundle();
        bundle.putLong(AlexaMetadataBundleKey.LATENCY_REALTIME_MS.name(), differenceMilliseconds);
        this.sdkEventReporter.sendEvent(UPL_LATENCY_EVENT_NAME, bundle);
    }

    @Override // com.amazon.alexa.voice.tta.metrics.MetricEventProcessor
    public boolean processEvent(@NonNull TtaEvent ttaEvent, @NonNull EventTime eventTime) {
        return this.innerProcessor.processEvent(ttaEvent, eventTime);
    }
}

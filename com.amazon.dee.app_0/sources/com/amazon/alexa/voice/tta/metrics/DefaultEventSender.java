package com.amazon.alexa.voice.tta.metrics;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEvent;
import com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes11.dex */
public class DefaultEventSender implements TtaEventSender {
    private static final String TAG = "DefaultEventSender";
    private final MetricEventProcessingService metricEventProcessingService;

    public DefaultEventSender(MetricEventProcessingService metricEventProcessingService) {
        this.metricEventProcessingService = metricEventProcessingService;
    }

    @Override // com.amazon.alexa.voice.ui.tta.metrics.TtaEventSender
    public void sendEvent(@NonNull TtaEvent ttaEvent) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("sendEvent: ");
        outline107.append(ttaEvent.getName());
        outline107.toString();
        this.metricEventProcessingService.processEvent(ttaEvent, EventTime.now());
    }
}

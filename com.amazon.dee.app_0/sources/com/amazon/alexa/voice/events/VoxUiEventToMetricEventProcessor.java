package com.amazon.alexa.voice.events;

import com.amazon.alexa.api.UiEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
/* loaded from: classes11.dex */
public class VoxUiEventToMetricEventProcessor implements VoxUiEventProcessor {
    private final VoxMetricEventProcessingService metricEventProcessor;

    /* JADX INFO: Access modifiers changed from: package-private */
    public VoxUiEventToMetricEventProcessor(VoxMetricEventProcessingService voxMetricEventProcessingService) {
        this.metricEventProcessor = voxMetricEventProcessingService;
    }

    @Override // com.amazon.alexa.voice.events.VoxUiEventProcessor
    public void process(VoxUiEvent voxUiEvent) {
        if (UiEventName.ALEXA_UI_SHOWN.name().equals(voxUiEvent.getName())) {
            this.metricEventProcessor.process(VoxMetricEvent.create(VoxMetricEventName.SCRIM_SHOWN, voxUiEvent.getTimestamp()));
        }
    }
}

package com.amazon.deecomms.alexa.voice;

import androidx.annotation.NonNull;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaEvent;
import com.amazon.deecomms.alexa.voice.usecase.EventSenderUseCase;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsService;
import com.amazon.deecomms.perms.VoicePermissionsAuthority;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class EventSenderImpl implements IEventSender {
    private EventSenderUseCase mEventSenderUseCase;
    private MetricsService mMetricsService;
    private VoicePermissionsAuthority mVoicePermissionsAuthority;

    @Inject
    public EventSenderImpl(EventSenderUseCase eventSenderUseCase, VoicePermissionsAuthority voicePermissionsAuthority, MetricsService metricsService) {
        this.mEventSenderUseCase = eventSenderUseCase;
        this.mVoicePermissionsAuthority = voicePermissionsAuthority;
        this.mMetricsService = metricsService;
    }

    private void recordOperationalMetric(String str) {
        CounterMetric counterMetric = new CounterMetric(CommsMetric.MetricType.Operational, str);
        counterMetric.setCounter(Double.valueOf(1.0d));
        this.mMetricsService.recordCounterMetric(counterMetric);
    }

    @Override // com.amazon.deecomms.alexa.voice.IEventSender
    public void sendEvent(@NonNull AlexaEvent alexaEvent) {
        sendEvent(alexaEvent, false, null);
    }

    @Override // com.amazon.deecomms.alexa.voice.IEventSender
    public void sendEvent(@NonNull AlexaEvent alexaEvent, boolean z) {
        sendEvent(alexaEvent, z, null);
    }

    @Override // com.amazon.deecomms.alexa.voice.IEventSender
    public void sendEvent(@NonNull AlexaEvent alexaEvent, boolean z, AlexaApiCallbacks alexaApiCallbacks) {
        if (alexaEvent != null && alexaEvent.getAlexaHeader().getNamespace() != null && alexaEvent.getAlexaHeader().getName() != null) {
            if (!this.mVoicePermissionsAuthority.hasMinimumPermissions()) {
                recordOperationalMetric(MetricKeys.OCCURRENCE_START_VOICE_FROM_GUI_FAILURE);
                recordOperationalMetric(MetricKeys.VOX_LAUNCH_FAILURE_NO_PERMISSIONS);
                return;
            }
            this.mEventSenderUseCase.send(alexaEvent, z, alexaApiCallbacks);
            recordOperationalMetric(MetricKeys.OCCURRENCE_START_VOICE_FROM_GUI_SUCCESS);
            return;
        }
        throw new IllegalArgumentException("Event, namespace or name cannot be null");
    }
}

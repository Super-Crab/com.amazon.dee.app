package com.amazon.alexa.voice.metrics.service;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsTimer;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.amazon.alexa.voice.metrics.VoiceMetricsTimer;
import com.amazon.alexa.voice.ui.onedesign.util.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
/* loaded from: classes11.dex */
public class VoxDefaultMetricsService implements MetricsService {
    @VisibleForTesting
    static final String SUB_COMPONENT = "VoxExp";
    private static final String TAG = "VoxDefaultMetricsService";
    private final Mobilytics mobilytics;
    @VisibleForTesting
    final ConcurrentMap<String, VoiceMetricsTimer> timerEvents = new ConcurrentHashMap();

    public VoxDefaultMetricsService(Mobilytics mobilytics) {
        this.mobilytics = mobilytics;
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void cancelTimer(String str) {
        VoiceMetricsTimer remove = this.timerEvents.remove(str);
        if (remove != null) {
            remove.invalidateEvent();
        }
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordError(String str, String str2, String str3, Map<String, Object> map) {
        this.mobilytics.recordOccurrence(GeneratedOutlineSupport1.outline72("Error.", str), true, str3, SUB_COMPONENT, "8eb5778d-5d99-45c4-b507-55fa502bbb43");
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordEvent(String str, String str2, Map<String, Object> map) {
        this.mobilytics.recordOccurrence(str, true, str2, SUB_COMPONENT, "8eb5778d-5d99-45c4-b507-55fa502bbb43");
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordEventMetricWithClickInteraction(String str, String str2, String str3, String str4) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.createUserInteractionEvent(str, "click", str2, SUB_COMPONENT, "8eb5778d-5d99-45c4-b507-55fa502bbb43");
        createUserInteractionEvent.setInteractionDetails(new ClickInteractionDetails(str4, str3));
        this.mobilytics.recordUserInteractionEvent(createUserInteractionEvent);
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map) {
        this.mobilytics.recordOccurrence(str, z, str2, SUB_COMPONENT, "8eb5778d-5d99-45c4-b507-55fa502bbb43");
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordPercentOccurrence(String str, String str2, boolean z, Map<String, Object> map) {
        this.mobilytics.recordPercentOccurrence(str, z, str2, SUB_COMPONENT, "8eb5778d-5d99-45c4-b507-55fa502bbb43");
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordTimer(String str) {
        VoiceMetricsTimer voiceMetricsTimer = this.timerEvents.get(str);
        if (voiceMetricsTimer != null) {
            recordTimer(voiceMetricsTimer);
        }
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public MetricsTimer startTimer(String str, String str2, Map<String, Object> map) {
        VoiceMetricsTimer voiceMetricsTimer = new VoiceMetricsTimer(str, str2, null);
        this.timerEvents.put(str, voiceMetricsTimer);
        return voiceMetricsTimer;
    }

    @Override // com.amazon.alexa.voice.metrics.service.MetricsService
    public void recordTimer(@NonNull MetricsTimer metricsTimer) {
        metricsTimer.finishTimer();
        Logger.verbose(TAG + " recordTimer timer:" + metricsTimer.getEventName() + " duration:" + metricsTimer.getElapsedTime());
        this.timerEvents.remove(metricsTimer.getEventName());
        if (metricsTimer.isInvalidated()) {
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        MobilyticsMetricsTimer createTimer = this.mobilytics.createTimer(metricsTimer.getEventName(), metricsTimer.getComponentName(), SUB_COMPONENT, "8eb5778d-5d99-45c4-b507-55fa502bbb43");
        createTimer.finishTimer(Long.valueOf(metricsTimer.getElapsedTime() + currentTimeMillis));
        this.mobilytics.recordTimer(createTimer);
    }
}

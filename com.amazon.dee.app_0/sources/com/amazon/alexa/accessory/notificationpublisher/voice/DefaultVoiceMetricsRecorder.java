package com.amazon.alexa.accessory.notificationpublisher.voice;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.metrics.MetricsRecorder;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder;
import java.util.Map;
/* loaded from: classes.dex */
public class DefaultVoiceMetricsRecorder implements ExternalNotificationsMetricsRecorder {
    private final MetricsRecorder metricsRecorder;

    public DefaultVoiceMetricsRecorder(MetricsRecorder metricsRecorder) {
        this.metricsRecorder = metricsRecorder;
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder
    public void recordCounter(String str) {
        this.metricsRecorder.recordCounter(str);
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder
    public void recordCounter(String str, double d) {
        this.metricsRecorder.recordCounter(str, d);
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder
    public void recordCounter(String str, @Nullable Map<String, Object> map) {
        this.metricsRecorder.recordCounter(str, map);
    }
}

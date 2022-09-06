package com.amazon.alexa.accessory.notificationpublisher.voice.ampd;

import androidx.annotation.Nullable;
import com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder;
import java.util.Map;
/* loaded from: classes.dex */
public class NoOpVoiceMetricsRecorder implements ExternalNotificationsMetricsRecorder {
    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder
    public void recordCounter(String str) {
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder
    public void recordCounter(String str, double d) {
    }

    @Override // com.amazon.alexa.externalnotifications.capability.ExternalNotificationsMetricsRecorder
    public void recordCounter(String str, @Nullable Map<String, Object> map) {
    }
}

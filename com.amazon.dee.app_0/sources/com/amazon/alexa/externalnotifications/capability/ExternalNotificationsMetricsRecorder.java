package com.amazon.alexa.externalnotifications.capability;

import androidx.annotation.Nullable;
import java.util.Map;
/* loaded from: classes7.dex */
public interface ExternalNotificationsMetricsRecorder {
    void recordCounter(String str);

    void recordCounter(String str, double d);

    void recordCounter(String str, @Nullable Map<String, Object> map);
}

package com.amazon.alexa.accessory.metrics;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.Logger;
import java.util.Map;
/* loaded from: classes.dex */
public interface AccessoryMetricsService {
    default void createTimer(@NonNull String str, @NonNull String str2, @NonNull String str3, @Nullable String str4) {
        Logger.d("AccessoryMetricsService default implementation of createTimer.");
    }

    void recordCounter(String str, String str2, double d, Map<String, Object> map);

    void recordCriticalEvent(String str, String str2, String str3, Throwable th);

    void recordOccurrence(String str, String str2, boolean z, Map<String, Object> map);

    void recordTime(String str, String str2, long j, Map<String, Object> map);

    default void recordTimer(@NonNull String str) {
        Logger.d("AccessoryMetricsService default implementation of recordTimer.");
    }

    void recordWarningEvent(String str, String str2, String str3, Throwable th);
}

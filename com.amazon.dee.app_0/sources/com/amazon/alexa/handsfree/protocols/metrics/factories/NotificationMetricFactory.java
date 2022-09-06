package com.amazon.alexa.handsfree.protocols.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
/* loaded from: classes8.dex */
public interface NotificationMetricFactory {
    Metric buildNotificationMetadataMetric(@NonNull String str, @NonNull String str2);

    @NonNull
    Metric buildNotificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3);
}

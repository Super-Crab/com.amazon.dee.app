package com.amazon.alexa.handsfree.metrics.factories;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsCounterWrapper;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.factories.NotificationMetricFactory;
/* loaded from: classes8.dex */
class MobilyticsNotificationMetricFactory implements NotificationMetricFactory {
    static final String NOTIFICATION_LABEL = "NOTIFICATION";
    private static final String NOTIFICATION_METADATA = "NOTIFICATION_METADATA";
    private static final String NOTIFICATION_METADATA_SUB_COMPONENT = "Metadata";
    private static final String NOTIFICATION_SHOWN_EVENT_TYPE = "NOTIFICATION_SHOWN_EVENT_TYPE";

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.NotificationMetricFactory
    public Metric buildNotificationMetadataMetric(@NonNull String str, @NonNull String str2) {
        return new MobilyticsMetricsCounterWrapper(NOTIFICATION_METADATA, NOTIFICATION_METADATA_SUB_COMPONENT).withContentId2(str).withContentType2(str2);
    }

    @Override // com.amazon.alexa.handsfree.protocols.metrics.factories.NotificationMetricFactory
    @NonNull
    public Metric buildNotificationMetric(@NonNull String str, @NonNull String str2, @NonNull String str3) {
        return new MobilyticsUserInteractionEventWrapper(NOTIFICATION_SHOWN_EVENT_TYPE, "view", str2, str).withContentType2(str3);
    }
}

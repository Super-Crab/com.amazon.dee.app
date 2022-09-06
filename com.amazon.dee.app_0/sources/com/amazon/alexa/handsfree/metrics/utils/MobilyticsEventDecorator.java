package com.amazon.alexa.handsfree.metrics.utils;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.mobilytics.MobilyticsMetadataProvider;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
/* loaded from: classes8.dex */
public class MobilyticsEventDecorator {
    private final String mAndroidId;
    private final AttributionTagProvider mAttributionTagProvider;
    private final MobilyticsMetadataProvider mMobilyticsMetadataProvider;

    public MobilyticsEventDecorator(@NonNull AttributionTagProvider attributionTagProvider, @NonNull MobilyticsMetadataProvider mobilyticsMetadataProvider, @NonNull String str) {
        this.mAttributionTagProvider = attributionTagProvider;
        this.mMobilyticsMetadataProvider = mobilyticsMetadataProvider;
        this.mAndroidId = str;
    }

    public void decorate(@NonNull Metric metric, @NonNull String str) {
        if (metric instanceof DefaultMobilyticsEvent) {
            DefaultMobilyticsEvent defaultMobilyticsEvent = (DefaultMobilyticsEvent) metric;
            defaultMobilyticsEvent.withSourceContext(str);
            defaultMobilyticsEvent.withEventMetadata(this.mMobilyticsMetadataProvider.getMobilyticsMetadata(defaultMobilyticsEvent));
            defaultMobilyticsEvent.withContentProvider(this.mAttributionTagProvider.getAttributionTag());
            defaultMobilyticsEvent.withContentVersion(this.mAndroidId);
        }
    }
}

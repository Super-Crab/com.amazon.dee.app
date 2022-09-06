package com.amazon.alexa.handsfree.metrics.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.metrics.caching.deserializers.MobilyticsMetricsCounterWrapperDeserializer;
import com.amazon.alexa.handsfree.metrics.caching.serializers.MobilyticsOperationalEventSerializer;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.metadata.AMPDMetadata;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonDeserialize(using = MobilyticsMetricsCounterWrapperDeserializer.class)
@JsonSerialize(using = MobilyticsOperationalEventSerializer.class)
/* loaded from: classes8.dex */
public class MobilyticsMetricsCounterWrapper extends DefaultMobilyticsMetricsCounter implements Metric, AMPDMetadataProvider {
    private final transient AMPDMetadata mAMPDMetadata;

    public MobilyticsMetricsCounterWrapper(@NonNull String str, @Nullable String str2) {
        super(str, MobilyticsPmetFactory.PMET_METHOD_NAME, str2, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID);
        this.mAMPDMetadata = new AMPDMetadata();
    }

    @Override // com.amazon.alexa.handsfree.metrics.events.AMPDMetadataProvider
    public AMPDMetadata getAMPDMetadata() {
        return this.mAMPDMetadata;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    /* renamed from: withContentId */
    public DefaultMobilyticsOperationalEvent withContentId2(@Nullable String str) {
        super.withContentId(str);
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    /* renamed from: withContentType */
    public DefaultMobilyticsOperationalEvent withContentType2(@Nullable String str) {
        super.withContentType(str);
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent
    /* renamed from: withEventNumericValue */
    public MobilyticsMetricsCounterWrapper mo1484withEventNumericValue(@NonNull Long l) {
        super.mo1484withEventNumericValue(l);
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    /* renamed from: withInputType */
    public DefaultMobilyticsOperationalEvent withInputType2(@Nullable String str) {
        super.withInputType(str);
        return this;
    }

    @Override // com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent
    /* renamed from: withSourceContext */
    public DefaultMobilyticsOperationalEvent withSourceContext2(@Nullable String str) {
        super.withSourceContext(str);
        return this;
    }
}

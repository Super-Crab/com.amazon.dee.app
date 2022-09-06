package com.amazon.alexa.handsfree.metrics.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.handsfree.metrics.caching.deserializers.MobilyticsMetricsTimerWrappperDeserializer;
import com.amazon.alexa.handsfree.metrics.caching.serializers.MobilyticsOperationalEventSerializer;
import com.amazon.alexa.handsfree.metrics.utils.MobilyticsPmetFactory;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsTimer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
@JsonDeserialize(using = MobilyticsMetricsTimerWrappperDeserializer.class)
@JsonSerialize(using = MobilyticsOperationalEventSerializer.class)
/* loaded from: classes8.dex */
public class MobilyticsMetricsTimerWrapper extends DefaultMobilyticsMetricsTimer implements Metric {
    public MobilyticsMetricsTimerWrapper(@NonNull String str, @Nullable String str2, long j, boolean z) {
        super(str, MobilyticsPmetFactory.PMET_METHOD_NAME, str2, j, z, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID);
    }

    @Override // com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent, com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent
    @NonNull
    @JsonProperty(JsonFields.EVENT_NUMERIC_VALUE)
    public Long getEventNumericValue() {
        return super.getEventNumericValue();
    }

    public MobilyticsMetricsTimerWrapper(@NonNull String str, @Nullable String str2, long j) {
        super(str, MobilyticsPmetFactory.PMET_METHOD_NAME, str2, j, false, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID);
    }

    public MobilyticsMetricsTimerWrapper(@NonNull String str, @Nullable String str2) {
        super(str, MobilyticsPmetFactory.PMET_METHOD_NAME, str2, OwnerIdentifier.ALEXA_APP_HANDS_FREE_ANDROID);
    }
}

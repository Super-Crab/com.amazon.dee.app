package com.amazon.alexa.handsfree.metrics.caching.deserializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.fasterxml.jackson.databind.JsonNode;
/* loaded from: classes8.dex */
public abstract class MobilyticsOperationalEventDeserializer<T extends DefaultMobilyticsOperationalEvent> extends MobilyticsEventDeserializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public MobilyticsOperationalEventDeserializer(@NonNull Class<T> cls) {
        super(cls);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.amazon.alexa.handsfree.metrics.caching.deserializers.MobilyticsEventDeserializer
    public /* bridge */ /* synthetic */ void setEventAttributes(@NonNull MobilyticsEvent mobilyticsEvent, @NonNull JsonNode jsonNode) {
        setEventAttributes((MobilyticsOperationalEventDeserializer<T>) ((DefaultMobilyticsOperationalEvent) mobilyticsEvent), jsonNode);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setEventAttributes(@NonNull T t, @NonNull JsonNode jsonNode) {
        super.setEventAttributes((MobilyticsOperationalEventDeserializer<T>) t, jsonNode);
        t.mo1484withEventNumericValue(Long.valueOf(jsonNode.findValue(JsonFields.EVENT_NUMERIC_VALUE).asLong()));
    }
}

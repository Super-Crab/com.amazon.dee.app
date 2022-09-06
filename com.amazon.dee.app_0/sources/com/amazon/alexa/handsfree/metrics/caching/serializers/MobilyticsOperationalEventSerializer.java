package com.amazon.alexa.handsfree.metrics.caching.serializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsOperationalEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
/* loaded from: classes8.dex */
public class MobilyticsOperationalEventSerializer extends MobilyticsEventSerializer<MobilyticsOperationalEvent> {
    private MobilyticsOperationalEventSerializer() {
        super(MobilyticsOperationalEvent.class);
    }

    @Override // com.amazon.alexa.handsfree.metrics.caching.serializers.MobilyticsEventSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@NonNull MobilyticsOperationalEvent mobilyticsOperationalEvent, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider) throws IOException {
        super.serialize((MobilyticsOperationalEventSerializer) mobilyticsOperationalEvent, jsonGenerator, serializerProvider);
        jsonGenerator.writeNumberField(JsonFields.EVENT_NUMERIC_VALUE, mobilyticsOperationalEvent.getEventNumericValue().longValue());
    }
}

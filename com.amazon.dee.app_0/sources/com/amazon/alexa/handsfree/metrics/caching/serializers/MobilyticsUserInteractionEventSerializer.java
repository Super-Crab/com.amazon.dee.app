package com.amazon.alexa.handsfree.metrics.caching.serializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
/* loaded from: classes8.dex */
public class MobilyticsUserInteractionEventSerializer extends MobilyticsEventSerializer<MobilyticsUserInteractionEvent> {
    private MobilyticsUserInteractionEventSerializer() {
        super(MobilyticsUserInteractionEvent.class);
    }

    @Override // com.amazon.alexa.handsfree.metrics.caching.serializers.MobilyticsEventSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@NonNull MobilyticsUserInteractionEvent mobilyticsUserInteractionEvent, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider) throws IOException {
        super.serialize((MobilyticsUserInteractionEventSerializer) mobilyticsUserInteractionEvent, jsonGenerator, serializerProvider);
        jsonGenerator.writeStringField("interactionType", mobilyticsUserInteractionEvent.getInteractionType());
        jsonGenerator.writeObjectField(JsonFields.INTERACTION_DETAILS, mobilyticsUserInteractionEvent.getInteractionDetails());
    }
}

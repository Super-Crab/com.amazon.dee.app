package com.amazon.alexa.handsfree.metrics.caching.serializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
/* loaded from: classes8.dex */
public class ClickInteractionDetailsSerializer extends StdSerializer<ClickInteractionDetails> {
    public ClickInteractionDetailsSerializer() {
        super(ClickInteractionDetails.class);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public void serialize(@NonNull ClickInteractionDetails clickInteractionDetails, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStringField(JsonFields.ACTION_TYPE, clickInteractionDetails.getActionType());
        jsonGenerator.writeStringField(JsonFields.ELEMENT_TYPE, clickInteractionDetails.getElementType());
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(@NonNull ClickInteractionDetails clickInteractionDetails, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider, @NonNull TypeSerializer typeSerializer) throws IOException {
        typeSerializer.writeTypePrefixForObject(clickInteractionDetails, jsonGenerator);
        serialize(clickInteractionDetails, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForObject(clickInteractionDetails, jsonGenerator);
    }
}

package com.amazon.alexa.handsfree.metrics.caching.deserializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.ClickInteractionDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
/* loaded from: classes8.dex */
public class ClickInteractionDetailsDeserializer extends StdDeserializer<ClickInteractionDetails> {
    public ClickInteractionDetailsDeserializer() {
        super(ClickInteractionDetails.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public ClickInteractionDetails mo7111deserialize(@NonNull JsonParser jsonParser, @NonNull DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
        return new ClickInteractionDetails(jsonNode.findValue(JsonFields.ELEMENT_TYPE).asText(null), jsonNode.findValue(JsonFields.ACTION_TYPE).asText(null));
    }
}

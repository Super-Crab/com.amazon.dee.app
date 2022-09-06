package com.amazon.alexa.handsfree.metrics.caching.deserializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.mobilytics.event.userinteraction.interactionDetails.InteractionDetails;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
/* loaded from: classes8.dex */
public class MobilyticsUserInteractionEventWrapperDeserializer extends MobilyticsEventDeserializer<MobilyticsUserInteractionEventWrapper> {
    private MobilyticsUserInteractionEventWrapperDeserializer() {
        super(MobilyticsUserInteractionEventWrapper.class);
    }

    private InteractionDetails parseInteractionDetails(@NonNull JsonNode jsonNode, @NonNull ObjectCodec objectCodec) throws JsonProcessingException {
        return (InteractionDetails) objectCodec.treeToValue(jsonNode.findValue(JsonFields.INTERACTION_DETAILS), InteractionDetails.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public MobilyticsUserInteractionEventWrapper mo7111deserialize(@NonNull JsonParser jsonParser, @NonNull DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
        MobilyticsUserInteractionEventWrapper mobilyticsUserInteractionEventWrapper = new MobilyticsUserInteractionEventWrapper(getEventName(jsonNode), jsonNode.findValue("interactionType").asText(), getComponent(jsonNode), getSubComponent(jsonNode));
        setEventAttributes(mobilyticsUserInteractionEventWrapper, jsonNode);
        mobilyticsUserInteractionEventWrapper.mo1485withInteractionDetails(parseInteractionDetails(jsonNode, jsonParser.getCodec()));
        return mobilyticsUserInteractionEventWrapper;
    }
}

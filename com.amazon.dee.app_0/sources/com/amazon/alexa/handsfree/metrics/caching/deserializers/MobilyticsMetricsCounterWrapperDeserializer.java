package com.amazon.alexa.handsfree.metrics.caching.deserializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsCounterWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
/* loaded from: classes8.dex */
public class MobilyticsMetricsCounterWrapperDeserializer extends MobilyticsOperationalEventDeserializer<MobilyticsMetricsCounterWrapper> {
    private MobilyticsMetricsCounterWrapperDeserializer() {
        super(MobilyticsMetricsCounterWrapper.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public MobilyticsMetricsCounterWrapper mo7111deserialize(@NonNull JsonParser jsonParser, @NonNull DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
        MobilyticsMetricsCounterWrapper mobilyticsMetricsCounterWrapper = new MobilyticsMetricsCounterWrapper(getEventName(jsonNode), getSubComponent(jsonNode));
        setEventAttributes((MobilyticsMetricsCounterWrapperDeserializer) mobilyticsMetricsCounterWrapper, jsonNode);
        return mobilyticsMetricsCounterWrapper;
    }
}

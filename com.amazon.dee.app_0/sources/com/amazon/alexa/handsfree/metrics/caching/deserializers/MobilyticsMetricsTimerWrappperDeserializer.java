package com.amazon.alexa.handsfree.metrics.caching.deserializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsMetricsTimerWrapper;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
/* loaded from: classes8.dex */
public class MobilyticsMetricsTimerWrappperDeserializer extends MobilyticsOperationalEventDeserializer<MobilyticsMetricsTimerWrapper> {
    private MobilyticsMetricsTimerWrappperDeserializer() {
        super(MobilyticsMetricsTimerWrapper.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    /* renamed from: deserialize */
    public MobilyticsMetricsTimerWrapper mo7111deserialize(@NonNull JsonParser jsonParser, @NonNull DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = (JsonNode) jsonParser.readValueAsTree();
        MobilyticsMetricsTimerWrapper mobilyticsMetricsTimerWrapper = new MobilyticsMetricsTimerWrapper(getEventName(jsonNode), getSubComponent(jsonNode));
        setEventAttributes((MobilyticsMetricsTimerWrappperDeserializer) mobilyticsMetricsTimerWrapper, jsonNode);
        return mobilyticsMetricsTimerWrapper;
    }
}

package com.amazon.alexa.handsfree.metrics.caching.deserializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
/* loaded from: classes8.dex */
public abstract class MobilyticsEventDeserializer<T extends MobilyticsEvent> extends StdDeserializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public MobilyticsEventDeserializer(@NonNull Class<T> cls) {
        super((Class<?>) cls);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getComponent(@NonNull JsonNode jsonNode) {
        return jsonNode.findValue(JsonFields.COMPONENT).asText();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getEventName(@NonNull JsonNode jsonNode) {
        return jsonNode.findValue(JsonFields.EVENT_NAME).asText();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getSubComponent(@NonNull JsonNode jsonNode) {
        return jsonNode.findValue("subComponent").asText();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void setEventAttributes(@NonNull T t, @NonNull JsonNode jsonNode) {
        t.setEventId(jsonNode.findValue("eventId").asText());
        t.setEventTimestamp(jsonNode.findValue(JsonFields.EVENT_TIMESTAMP).asLong());
        t.setContentId(jsonNode.findValue("contentId").asText(null));
        t.setContentType(jsonNode.findValue("contentType").asText(null));
        t.setSourceContext(jsonNode.findValue("sourceContext").asText(null));
    }
}

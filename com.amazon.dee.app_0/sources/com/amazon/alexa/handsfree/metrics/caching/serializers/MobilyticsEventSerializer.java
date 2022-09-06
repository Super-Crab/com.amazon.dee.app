package com.amazon.alexa.handsfree.metrics.caching.serializers;

import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
/* loaded from: classes8.dex */
public abstract class MobilyticsEventSerializer<T extends MobilyticsEvent> extends StdSerializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public MobilyticsEventSerializer(@NonNull Class<T> cls) {
        super(cls);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public /* bridge */ /* synthetic */ void serialize(@NonNull Object obj, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider) throws IOException {
        serialize((MobilyticsEventSerializer<T>) ((MobilyticsEvent) obj), jsonGenerator, serializerProvider);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public /* bridge */ /* synthetic */ void serializeWithType(@NonNull Object obj, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider, @NonNull TypeSerializer typeSerializer) throws IOException {
        serializeWithType((MobilyticsEventSerializer<T>) ((MobilyticsEvent) obj), jsonGenerator, serializerProvider, typeSerializer);
    }

    public void serialize(@NonNull T t, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStringField(JsonFields.COMPONENT, t.getComponent());
        jsonGenerator.writeStringField(JsonFields.EVENT_NAME, t.getEventName());
        jsonGenerator.writeStringField("subComponent", t.getSubComponent());
        jsonGenerator.writeNumberField(JsonFields.EVENT_TIMESTAMP, t.getEventTimestamp());
        jsonGenerator.writeStringField("eventId", t.getEventId());
        jsonGenerator.writeStringField("contentType", t.getContentType());
        jsonGenerator.writeStringField("contentId", t.getContentId());
        jsonGenerator.writeStringField("sourceContext", t.getSourceContext());
    }

    public void serializeWithType(@NonNull T t, @NonNull JsonGenerator jsonGenerator, @NonNull SerializerProvider serializerProvider, @NonNull TypeSerializer typeSerializer) throws IOException {
        typeSerializer.writeTypePrefixForObject(t, jsonGenerator);
        serialize((MobilyticsEventSerializer<T>) t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForObject(t, jsonGenerator);
    }
}

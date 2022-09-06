package com.amazon.alexa.statereporting.lib.model.serialization.adapter;

import com.amazon.alexa.statereporting.lib.model.api.event.ChangeReportEventPayload;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Type;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class ChangeReportEventPayloadAdapter<PropertyKey> implements JsonDeserializer<ChangeReportEventPayload<PropertyKey>> {
    private final Class<? extends ChangeReportEventPayload> payloadClass;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ChangeReportEventPayloadAdapter(Class<? extends ChangeReportEventPayload> cls) {
        this.payloadClass = cls;
    }

    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public ChangeReportEventPayload<PropertyKey> mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return (ChangeReportEventPayload) jsonDeserializationContext.deserialize(jsonElement, this.payloadClass);
    }
}

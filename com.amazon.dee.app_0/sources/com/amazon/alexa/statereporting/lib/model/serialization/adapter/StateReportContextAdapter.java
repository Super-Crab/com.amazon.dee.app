package com.amazon.alexa.statereporting.lib.model.serialization.adapter;

import com.amazon.alexa.statereporting.lib.model.api.context.StateReportContext;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Type;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class StateReportContextAdapter<PropertyKey> implements JsonDeserializer<StateReportContext<PropertyKey>> {
    private final Class<? extends StateReportContext> stateReportContextClass;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public StateReportContextAdapter(Class<? extends StateReportContext> cls) {
        this.stateReportContextClass = cls;
    }

    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public StateReportContext<PropertyKey> mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return (StateReportContext) jsonDeserializationContext.deserialize(jsonElement, this.stateReportContextClass);
    }
}

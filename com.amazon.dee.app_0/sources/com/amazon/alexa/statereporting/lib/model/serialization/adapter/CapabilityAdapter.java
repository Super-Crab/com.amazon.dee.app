package com.amazon.alexa.statereporting.lib.model.serialization.adapter;

import com.amazon.alexa.statereporting.lib.model.api.discovery.Capability;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Type;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class CapabilityAdapter<PropertyKey, Configuration> implements JsonDeserializer<Capability<PropertyKey, Configuration>> {
    private final Class<? extends Capability> capabilityClass;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public CapabilityAdapter(Class<? extends Capability> cls) {
        this.capabilityClass = cls;
    }

    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public Capability<PropertyKey, Configuration> mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return (Capability) jsonDeserializationContext.deserialize(jsonElement, this.capabilityClass);
    }
}

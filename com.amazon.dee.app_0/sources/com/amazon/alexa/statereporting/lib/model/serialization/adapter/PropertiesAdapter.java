package com.amazon.alexa.statereporting.lib.model.serialization.adapter;

import com.amazon.alexa.statereporting.lib.model.api.discovery.Properties;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Type;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class PropertiesAdapter<PropertyKey> implements JsonDeserializer<Properties<PropertyKey>> {
    private final Class<? extends Properties> propertiesClass;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PropertiesAdapter(Class<? extends Properties> cls) {
        this.propertiesClass = cls;
    }

    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public Properties<PropertyKey> mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return (Properties) jsonDeserializationContext.deserialize(jsonElement, this.propertiesClass);
    }
}

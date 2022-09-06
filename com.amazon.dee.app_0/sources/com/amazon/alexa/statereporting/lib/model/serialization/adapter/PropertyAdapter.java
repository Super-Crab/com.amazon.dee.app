package com.amazon.alexa.statereporting.lib.model.serialization.adapter;

import com.amazon.alexa.statereporting.lib.model.api.Property;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.lang.reflect.Type;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class PropertyAdapter<PropertyKey> implements JsonDeserializer<Property<PropertyKey, ?, ?>> {
    private static final Gson GSON = new Gson();
    private static final String PROPERTY_NAME_FIELD = "name";
    private final Class<? extends Property> propertyClass;
    private final PropertyToPropertyClass<PropertyKey> propertyClassSupplier;
    private final Class<? extends PropertyKey> propertyKeyClass;

    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface PropertyToPropertyClass<PropertyKey> {
        Class<? extends Property> getPropertyClass(PropertyKey propertykey);
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public PropertyAdapter(Class<? extends PropertyKey> cls, Class<? extends Property> cls2, PropertyToPropertyClass<PropertyKey> propertyToPropertyClass) {
        this.propertyKeyClass = cls;
        this.propertyClass = cls2;
        this.propertyClassSupplier = propertyToPropertyClass;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.gson.JsonDeserializer
    /* renamed from: deserialize */
    public Property<PropertyKey, ?, ?> mo5482deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return (Property) jsonDeserializationContext.deserialize(jsonElement, this.propertyClassSupplier.getPropertyClass(GSON.fromJson(jsonElement.getAsJsonObject().get("name"), (Class<Object>) this.propertyKeyClass)));
    }
}

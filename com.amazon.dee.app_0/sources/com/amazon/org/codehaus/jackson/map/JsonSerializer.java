package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class JsonSerializer<T> {

    /* loaded from: classes13.dex */
    public static abstract class None extends JsonSerializer<Object> {
    }

    public Class<T> handledType() {
        return null;
    }

    public boolean isUnwrappingSerializer() {
        return false;
    }

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException;

    public void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        serialize(t, jsonGenerator, serializerProvider);
    }

    public JsonSerializer<T> unwrappingSerializer() {
        return this;
    }
}

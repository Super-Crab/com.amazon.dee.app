package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonParser;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import java.io.IOException;
/* loaded from: classes13.dex */
public abstract class JsonDeserializer<T> {

    /* loaded from: classes13.dex */
    public static abstract class None extends JsonDeserializer<Object> {
    }

    /* renamed from: deserialize */
    public abstract T mo4206deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException;

    public T deserialize(JsonParser jsonParser, DeserializationContext deserializationContext, T t) throws IOException, JsonProcessingException {
        throw new UnsupportedOperationException();
    }

    /* renamed from: deserializeWithType */
    public Object mo4196deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    public T getEmptyValue() {
        return getNullValue();
    }

    public T getNullValue() {
        return null;
    }

    public JsonDeserializer<T> unwrappingDeserializer() {
        return this;
    }
}

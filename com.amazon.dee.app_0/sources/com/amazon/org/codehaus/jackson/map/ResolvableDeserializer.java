package com.amazon.org.codehaus.jackson.map;
/* loaded from: classes13.dex */
public interface ResolvableDeserializer {
    void resolve(DeserializationConfig deserializationConfig, DeserializerProvider deserializerProvider) throws JsonMappingException;
}

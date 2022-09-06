package com.amazon.org.codehaus.jackson.map;
/* loaded from: classes13.dex */
public interface ContextualKeyDeserializer {
    KeyDeserializer createContextual(DeserializationConfig deserializationConfig, BeanProperty beanProperty) throws JsonMappingException;
}

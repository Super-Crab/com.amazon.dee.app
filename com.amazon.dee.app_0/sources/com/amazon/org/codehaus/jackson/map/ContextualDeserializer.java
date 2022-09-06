package com.amazon.org.codehaus.jackson.map;
/* loaded from: classes13.dex */
public interface ContextualDeserializer<T> {
    JsonDeserializer<T> createContextual(DeserializationConfig deserializationConfig, BeanProperty beanProperty) throws JsonMappingException;
}

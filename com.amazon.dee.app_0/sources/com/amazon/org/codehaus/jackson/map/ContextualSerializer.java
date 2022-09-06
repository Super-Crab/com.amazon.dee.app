package com.amazon.org.codehaus.jackson.map;
/* loaded from: classes13.dex */
public interface ContextualSerializer<T> {
    JsonSerializer<T> createContextual(SerializationConfig serializationConfig, BeanProperty beanProperty) throws JsonMappingException;
}

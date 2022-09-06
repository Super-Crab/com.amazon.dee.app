package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public interface KeyDeserializers {
    KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty) throws JsonMappingException;
}

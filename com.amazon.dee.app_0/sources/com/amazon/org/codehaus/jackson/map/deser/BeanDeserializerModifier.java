package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
/* loaded from: classes13.dex */
public abstract class BeanDeserializerModifier {
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, JsonDeserializer<?> jsonDeserializer) {
        return jsonDeserializer;
    }

    public BeanDeserializerBuilder updateBuilder(DeserializationConfig deserializationConfig, BasicBeanDescription basicBeanDescription, BeanDeserializerBuilder beanDeserializerBuilder) {
        return beanDeserializerBuilder;
    }
}

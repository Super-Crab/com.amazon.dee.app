package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.introspect.BasicBeanDescription;
import java.util.List;
/* loaded from: classes13.dex */
public abstract class BeanSerializerModifier {
    public List<BeanPropertyWriter> changeProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    public JsonSerializer<?> modifySerializer(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, JsonSerializer<?> jsonSerializer) {
        return jsonSerializer;
    }

    public List<BeanPropertyWriter> orderProperties(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, List<BeanPropertyWriter> list) {
        return list;
    }

    public BeanSerializerBuilder updateBuilder(SerializationConfig serializationConfig, BasicBeanDescription basicBeanDescription, BeanSerializerBuilder beanSerializerBuilder) {
        return beanSerializerBuilder;
    }
}

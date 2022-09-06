package com.amazon.org.codehaus.jackson.map.module;

import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializers;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class SimpleKeyDeserializers implements KeyDeserializers {
    protected HashMap<ClassKey, KeyDeserializer> _classMappings = null;

    public SimpleKeyDeserializers addDeserializer(Class<?> cls, KeyDeserializer keyDeserializer) {
        if (this._classMappings == null) {
            this._classMappings = new HashMap<>();
        }
        this._classMappings.put(new ClassKey(cls), keyDeserializer);
        return this;
    }

    @Override // com.amazon.org.codehaus.jackson.map.KeyDeserializers
    public KeyDeserializer findKeyDeserializer(JavaType javaType, DeserializationConfig deserializationConfig, BeanDescription beanDescription, BeanProperty beanProperty) {
        HashMap<ClassKey, KeyDeserializer> hashMap = this._classMappings;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(new ClassKey(javaType.getRawClass()));
    }
}

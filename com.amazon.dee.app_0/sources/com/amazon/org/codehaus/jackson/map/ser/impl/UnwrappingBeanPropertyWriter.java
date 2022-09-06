package com.amazon.org.codehaus.jackson.map.ser.impl;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public class UnwrappingBeanPropertyWriter extends BeanPropertyWriter {
    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter) {
        super(beanPropertyWriter);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
    protected JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        JsonSerializer<Object> findValueSerializer;
        JavaType javaType = this._nonTrivialBaseType;
        if (javaType != null) {
            findValueSerializer = serializerProvider.findValueSerializer(serializerProvider.constructSpecializedType(javaType, cls), this);
        } else {
            findValueSerializer = serializerProvider.findValueSerializer(cls, this);
        }
        if (!findValueSerializer.isUnwrappingSerializer()) {
            findValueSerializer = findValueSerializer.unwrappingSerializer();
        }
        this._dynamicSerializers = this._dynamicSerializers.newWith(cls, findValueSerializer);
        return findValueSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
    public void serializeAsField(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws Exception {
        Object obj2 = get(obj);
        if (obj2 == null) {
            return;
        }
        if (obj2 == obj) {
            _reportSelfReference(obj);
        }
        Object obj3 = this._suppressableValue;
        if (obj3 != null && obj3.equals(obj2)) {
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._serializer;
        if (jsonSerializer == null) {
            Class<?> cls = obj2.getClass();
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
            jsonSerializer = serializerFor == null ? _findAndAddDynamic(propertySerializerMap, cls, serializerProvider) : serializerFor;
        }
        if (!jsonSerializer.isUnwrappingSerializer()) {
            jsonGenerator.writeFieldName(this._name);
        }
        TypeSerializer typeSerializer = this._typeSerializer;
        if (typeSerializer == null) {
            jsonSerializer.serialize(obj2, jsonGenerator, serializerProvider);
        } else {
            jsonSerializer.serializeWithType(obj2, jsonGenerator, serializerProvider, typeSerializer);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter
    public BeanPropertyWriter withSerializer(JsonSerializer<Object> jsonSerializer) {
        if (UnwrappingBeanPropertyWriter.class == UnwrappingBeanPropertyWriter.class) {
            if (!jsonSerializer.isUnwrappingSerializer()) {
                jsonSerializer = jsonSerializer.unwrappingSerializer();
            }
            return new UnwrappingBeanPropertyWriter(this, jsonSerializer);
        }
        throw new IllegalStateException("UnwrappingBeanPropertyWriter sub-class does not override 'withSerializer()'; needs to!");
    }

    public UnwrappingBeanPropertyWriter(BeanPropertyWriter beanPropertyWriter, JsonSerializer<Object> jsonSerializer) {
        super(beanPropertyWriter, jsonSerializer);
    }
}

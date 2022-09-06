package com.amazon.org.codehaus.jackson.map.util;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializableWithType;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
/* loaded from: classes13.dex */
public class JSONPObject implements JsonSerializableWithType {
    protected final String _function;
    protected final JavaType _serializationType;
    protected final Object _value;

    public JSONPObject(String str, Object obj) {
        this(str, obj, (JavaType) null);
    }

    public String getFunction() {
        return this._function;
    }

    public JavaType getSerializationType() {
        return this._serializationType;
    }

    public Object getValue() {
        return this._value;
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializable
    public void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeRaw(this._function);
        jsonGenerator.writeRaw('(');
        Object obj = this._value;
        if (obj == null) {
            serializerProvider.defaultSerializeNull(jsonGenerator);
        } else {
            JavaType javaType = this._serializationType;
            if (javaType != null) {
                serializerProvider.findTypedValueSerializer(javaType, true, (BeanProperty) null).serialize(this._value, jsonGenerator, serializerProvider);
            } else {
                serializerProvider.findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null).serialize(this._value, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeRaw(')');
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializableWithType
    public void serializeWithType(JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonProcessingException {
        serialize(jsonGenerator, serializerProvider);
    }

    public JSONPObject(String str, Object obj, JavaType javaType) {
        this._function = str;
        this._value = obj;
        this._serializationType = javaType;
    }

    @Deprecated
    public JSONPObject(String str, Object obj, Class<?> cls) {
        this._function = str;
        this._value = obj;
        this._serializationType = cls == null ? null : TypeFactory.defaultInstance().constructType(cls);
    }
}

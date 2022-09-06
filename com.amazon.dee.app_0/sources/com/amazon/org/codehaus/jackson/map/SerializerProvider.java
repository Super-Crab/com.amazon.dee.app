package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.ser.FilterProvider;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
/* loaded from: classes13.dex */
public abstract class SerializerProvider {
    protected static final JavaType TYPE_OBJECT = TypeFactory.defaultInstance().uncheckedSimpleType(Object.class);
    protected final SerializationConfig _config;
    protected final Class<?> _serializationView;

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializerProvider(SerializationConfig serializationConfig) {
        this._config = serializationConfig;
        this._serializationView = serializationConfig == null ? null : this._config.getSerializationView();
    }

    public abstract int cachedSerializersCount();

    public JavaType constructSpecializedType(JavaType javaType, Class<?> cls) {
        return this._config.constructSpecializedType(javaType, cls);
    }

    public JavaType constructType(Type type) {
        return this._config.getTypeFactory().constructType(type);
    }

    public abstract void defaultSerializeDateKey(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException;

    public abstract void defaultSerializeDateKey(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException;

    public abstract void defaultSerializeDateValue(long j, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException;

    public abstract void defaultSerializeDateValue(Date date, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException;

    public final void defaultSerializeField(String str, Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeFieldName(str);
        if (obj == null) {
            getNullValueSerializer().serialize(null, jsonGenerator, this);
        } else {
            findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
        }
    }

    public final void defaultSerializeNull(JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        getNullValueSerializer().serialize(null, jsonGenerator, this);
    }

    public final void defaultSerializeValue(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        if (obj == null) {
            getNullValueSerializer().serialize(null, jsonGenerator, this);
        } else {
            findTypedValueSerializer(obj.getClass(), true, (BeanProperty) null).serialize(obj, jsonGenerator, this);
        }
    }

    public abstract JsonSerializer<Object> findKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z, BeanProperty beanProperty) throws JsonMappingException;

    @Deprecated
    public final JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z) throws JsonMappingException {
        return findTypedValueSerializer(cls, z, (BeanProperty) null);
    }

    public abstract JsonSerializer<Object> findTypedValueSerializer(Class<?> cls, boolean z, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonSerializer<Object> findValueSerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    @Deprecated
    public final JsonSerializer<Object> findValueSerializer(Class<?> cls) throws JsonMappingException {
        return findValueSerializer(cls, (BeanProperty) null);
    }

    public abstract JsonSerializer<Object> findValueSerializer(Class<?> cls, BeanProperty beanProperty) throws JsonMappingException;

    public abstract void flushCachedSerializers();

    public abstract JsonSchema generateJsonSchema(Class<?> cls, SerializationConfig serializationConfig, SerializerFactory serializerFactory) throws JsonMappingException;

    public final SerializationConfig getConfig() {
        return this._config;
    }

    public final FilterProvider getFilterProvider() {
        return this._config.getFilterProvider();
    }

    @Deprecated
    public final JsonSerializer<Object> getKeySerializer() throws JsonMappingException {
        return findKeySerializer(TYPE_OBJECT, null);
    }

    public abstract JsonSerializer<Object> getNullKeySerializer();

    public abstract JsonSerializer<Object> getNullValueSerializer();

    public final Class<?> getSerializationView() {
        return this._serializationView;
    }

    public abstract JsonSerializer<Object> getUnknownTypeSerializer(Class<?> cls);

    public abstract boolean hasSerializerFor(SerializationConfig serializationConfig, Class<?> cls, SerializerFactory serializerFactory);

    public final boolean isEnabled(SerializationConfig.Feature feature) {
        return this._config.isEnabled(feature);
    }

    public abstract void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, SerializerFactory serializerFactory) throws IOException, JsonGenerationException;

    public abstract void serializeValue(SerializationConfig serializationConfig, JsonGenerator jsonGenerator, Object obj, JavaType javaType, SerializerFactory serializerFactory) throws IOException, JsonGenerationException;

    public abstract void setDefaultKeySerializer(JsonSerializer<Object> jsonSerializer);

    public abstract void setNullKeySerializer(JsonSerializer<Object> jsonSerializer);

    public abstract void setNullValueSerializer(JsonSerializer<Object> jsonSerializer);

    @Deprecated
    public final JsonSerializer<Object> findTypedValueSerializer(JavaType javaType, boolean z) throws JsonMappingException {
        return findTypedValueSerializer(javaType, z, (BeanProperty) null);
    }

    @Deprecated
    public final JsonSerializer<Object> findValueSerializer(JavaType javaType) throws JsonMappingException {
        return findValueSerializer(javaType, (BeanProperty) null);
    }

    @Deprecated
    public final JsonSerializer<Object> getKeySerializer(JavaType javaType, BeanProperty beanProperty) throws JsonMappingException {
        return findKeySerializer(javaType, beanProperty);
    }
}

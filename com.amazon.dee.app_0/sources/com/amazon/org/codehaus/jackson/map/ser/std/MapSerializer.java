package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializationConfig;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.amazon.org.codehaus.jackson.map.type.TypeFactory;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class MapSerializer extends ContainerSerializerBase<Map<?, ?>> implements ResolvableSerializer {
    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final HashSet<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    protected MapSerializer() {
        this(null, null, null, false, null, null, null, null);
    }

    @Deprecated
    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        return construct(strArr, javaType, z, typeSerializer, beanProperty, null, null);
    }

    private static HashSet<String> toSet(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        HashSet<String> hashSet = new HashSet<>(strArr.length);
        for (String str : strArr) {
            hashSet.add(str);
        }
        return hashSet;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicValueSerializers = propertySerializerMap2;
        }
        return findAndAddSerializer.serializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        MapSerializer mapSerializer = new MapSerializer(this._ignoredEntries, this._keyType, this._valueType, this._valueTypeIsStatic, typeSerializer, this._keySerializer, this._valueSerializer, this._property);
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer != null) {
            mapSerializer._valueSerializer = jsonSerializer;
        }
        return mapSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("object", true);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        if (this._valueTypeIsStatic && this._valueSerializer == null) {
            this._valueSerializer = serializerProvider.findValueSerializer(this._valueType, this._property);
        }
        if (this._keySerializer == null) {
            this._keySerializer = serializerProvider.findKeySerializer(this._keyType, this._property);
        }
    }

    public void serializeFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> _findAndAddDynamic;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(map, jsonGenerator, serializerProvider);
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        boolean z = !serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
        PropertySerializerMap propertySerializerMap = this._dynamicValueSerializers;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.getNullKeySerializer().serialize(null, jsonGenerator, serializerProvider);
            } else if (!z || value != null) {
                if (hashSet == null || !hashSet.contains(key)) {
                    jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
                }
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                Class<?> cls = value.getClass();
                JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                if (serializerFor == null) {
                    if (this._valueType.hasGenericTypes()) {
                        _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._valueType, cls), serializerProvider);
                    } else {
                        _findAndAddDynamic = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    serializerFor = _findAndAddDynamic;
                    propertySerializerMap = this._dynamicValueSerializers;
                }
                try {
                    serializerFor.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, map, GeneratedOutlineSupport1.outline70("", key));
                }
            }
        }
    }

    protected void serializeFieldsUsing(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer2 = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        boolean z = !serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.getNullKeySerializer().serialize(null, jsonGenerator, serializerProvider);
            } else if (!z || value != null) {
                if (hashSet == null || !hashSet.contains(key)) {
                    jsonSerializer2.serialize(key, jsonGenerator, serializerProvider);
                }
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else if (typeSerializer == null) {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, map, GeneratedOutlineSupport1.outline70("", key));
                }
            } else {
                jsonSerializer.serializeWithType(value, jsonGenerator, serializerProvider, typeSerializer);
            }
        }
    }

    protected void serializeTypedFields(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer = this._keySerializer;
        HashSet<String> hashSet = this._ignoredEntries;
        boolean z = !serializerProvider.isEnabled(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES);
        Class<?> cls = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            Object value = entry.getValue();
            Object key = entry.getKey();
            if (key == null) {
                serializerProvider.getNullKeySerializer().serialize(null, jsonGenerator, serializerProvider);
            } else if (!z || value != null) {
                if (hashSet == null || !hashSet.contains(key)) {
                    jsonSerializer.serialize(key, jsonGenerator, serializerProvider);
                }
            }
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                Class<?> cls2 = value.getClass();
                if (cls2 != cls) {
                    jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                    cls = cls2;
                }
                try {
                    jsonSerializer2.serializeWithType(value, jsonGenerator, serializerProvider, this._valueTypeSerializer);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, map, GeneratedOutlineSupport1.outline70("", key));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, BeanProperty beanProperty) {
        super(Map.class, false);
        this._property = beanProperty;
        this._ignoredEntries = hashSet;
        this._keyType = javaType;
        this._valueType = javaType2;
        this._valueTypeIsStatic = z;
        this._valueTypeSerializer = typeSerializer;
        this._keySerializer = jsonSerializer;
        this._valueSerializer = jsonSerializer2;
        this._dynamicValueSerializers = PropertySerializerMap.emptyMap();
    }

    public static MapSerializer construct(String[] strArr, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2) {
        JavaType keyType;
        JavaType contentType;
        HashSet<String> set = toSet(strArr);
        if (javaType == null) {
            keyType = UNSPECIFIED_TYPE;
            contentType = keyType;
        } else {
            keyType = javaType.getKeyType();
            contentType = javaType.getContentType();
        }
        if (!z) {
            z = contentType != null && contentType.isFinal();
        }
        return new MapSerializer(set, keyType, contentType, z, typeSerializer, jsonSerializer, jsonSerializer2, beanProperty);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (!map.isEmpty()) {
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, jsonSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Map<?, ?> map, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(map, jsonGenerator);
        if (!map.isEmpty()) {
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer != null) {
                serializeFieldsUsing(map, jsonGenerator, serializerProvider, jsonSerializer);
            } else {
                serializeFields(map, jsonGenerator, serializerProvider);
            }
        }
        typeSerializer.writeTypeSuffixForObject(map, jsonGenerator);
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicValueSerializers = propertySerializerMap2;
        }
        return findAndAddSerializer.serializer;
    }
}

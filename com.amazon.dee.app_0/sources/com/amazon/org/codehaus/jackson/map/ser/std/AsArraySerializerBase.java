package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.schema.SchemaAware;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public abstract class AsArraySerializerBase<T> extends ContainerSerializerBase<T> implements ResolvableSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    @Deprecated
    protected AsArraySerializerBase(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(cls, javaType, z, typeSerializer, beanProperty, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicSerializers = propertySerializerMap2;
        }
        return findAndAddSerializer.serializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        JavaType javaType;
        JavaType javaType2;
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        JsonNode jsonNode = null;
        if (type != null) {
            JavaType contentType = serializerProvider.constructType(type).getContentType();
            if (contentType == null && (type instanceof ParameterizedType)) {
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                if (actualTypeArguments.length == 1) {
                    javaType = serializerProvider.constructType(actualTypeArguments[0]);
                }
            }
            javaType = contentType;
        } else {
            javaType = null;
        }
        if (javaType == null && (javaType2 = this._elementType) != null) {
            javaType = javaType2;
        }
        if (javaType != null) {
            if (javaType.getRawClass() != Object.class) {
                JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(javaType, this._property);
                if (findValueSerializer instanceof SchemaAware) {
                    jsonNode = ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null);
                }
            }
            if (jsonNode == null) {
                jsonNode = JsonSchema.getDefaultSchemaNode();
            }
            createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, jsonNode);
        }
        return createSchemaNode;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        JavaType javaType;
        if (!this._staticTyping || (javaType = this._elementType) == null || this._elementSerializer != null) {
            return;
        }
        this._elementSerializer = serializerProvider.findValueSerializer(javaType, this._property);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartArray();
        serializeContents(t, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    protected abstract void serializeContents(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForArray(t, jsonGenerator);
        serializeContents(t, jsonGenerator, serializerProvider);
        typeSerializer.writeTypeSuffixForArray(t, jsonGenerator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsArraySerializerBase(Class<?> cls, JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(cls, false);
        boolean z2 = false;
        this._elementType = javaType;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._elementSerializer = jsonSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicSerializers = propertySerializerMap2;
        }
        return findAndAddSerializer.serializer;
    }
}

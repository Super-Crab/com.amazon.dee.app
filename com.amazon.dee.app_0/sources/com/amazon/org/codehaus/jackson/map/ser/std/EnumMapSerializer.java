package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.util.EnumValues;
import com.amazon.org.codehaus.jackson.node.JsonNodeFactory;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.schema.SchemaAware;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumMap;
import java.util.Map;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class EnumMapSerializer extends ContainerSerializerBase<EnumMap<? extends Enum<?>, ?>> implements ResolvableSerializer {
    protected final EnumValues _keyEnums;
    protected final BeanProperty _property;
    protected final boolean _staticTyping;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final TypeSerializer _valueTypeSerializer;

    @Deprecated
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(javaType, z, enumValues, typeSerializer, beanProperty, null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new EnumMapSerializer(this._valueType, this._staticTyping, this._keyEnums, typeSerializer, this._property, this._valueSerializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        Enum<?>[] enumArr;
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        if (type instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
            if (actualTypeArguments.length == 2) {
                JavaType constructType = serializerProvider.constructType(actualTypeArguments[0]);
                JavaType constructType2 = serializerProvider.constructType(actualTypeArguments[1]);
                ObjectNode objectNode = JsonNodeFactory.instance.objectNode();
                for (Enum<?> r5 : (Enum[]) constructType.getRawClass().getEnumConstants()) {
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(constructType2.getRawClass(), this._property);
                    objectNode.put(serializerProvider.getConfig().getAnnotationIntrospector().findEnumValue(r5), findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
                createSchemaNode.put("properties", objectNode);
            }
        }
        return createSchemaNode;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        if (!this._staticTyping || this._valueSerializer != null) {
            return;
        }
        this._valueSerializer = serializerProvider.findValueSerializer(this._valueType, this._property);
    }

    protected void serializeContents(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        JsonSerializer<Object> jsonSerializer = this._valueSerializer;
        if (jsonSerializer != null) {
            serializeContentsUsing(enumMap, jsonGenerator, serializerProvider, jsonSerializer);
            return;
        }
        EnumValues enumValues = this._keyEnums;
        Class<?> cls = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        for (Map.Entry<? extends Enum<?>, ?> entry : enumMap.entrySet()) {
            Enum<?> key = entry.getKey();
            if (enumValues == null) {
                enumValues = ((EnumSerializer) serializerProvider.findValueSerializer(key.getDeclaringClass(), this._property)).getEnumValues();
            }
            jsonGenerator.writeFieldName(enumValues.serializedValueFor(key));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                Class<?> cls2 = value.getClass();
                if (cls2 != cls) {
                    jsonSerializer2 = serializerProvider.findValueSerializer(cls2, this._property);
                    cls = cls2;
                }
                try {
                    jsonSerializer2.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, enumMap, entry.getKey().name());
                }
            }
        }
    }

    protected void serializeContentsUsing(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        EnumValues enumValues = this._keyEnums;
        for (Map.Entry<? extends Enum<?>, ?> entry : enumMap.entrySet()) {
            Enum<?> key = entry.getKey();
            if (enumValues == null) {
                enumValues = ((EnumSerializer) serializerProvider.findValueSerializer(key.getDeclaringClass(), this._property)).getEnumValues();
            }
            jsonGenerator.writeFieldName(enumValues.serializedValueFor(key));
            Object value = entry.getValue();
            if (value == null) {
                serializerProvider.defaultSerializeNull(jsonGenerator);
            } else {
                try {
                    jsonSerializer.serialize(value, jsonGenerator, serializerProvider);
                } catch (Exception e) {
                    wrapAndThrow(serializerProvider, e, enumMap, entry.getKey().name());
                }
            }
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EnumMapSerializer(JavaType javaType, boolean z, EnumValues enumValues, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(EnumMap.class, false);
        boolean z2 = false;
        if (z || (javaType != null && javaType.isFinal())) {
            z2 = true;
        }
        this._staticTyping = z2;
        this._valueType = javaType;
        this._keyEnums = enumValues;
        this._valueTypeSerializer = typeSerializer;
        this._property = beanProperty;
        this._valueSerializer = jsonSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serialize(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonGenerator.writeStartObject();
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        jsonGenerator.writeEndObject();
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(EnumMap<? extends Enum<?>, ?> enumMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(enumMap, jsonGenerator);
        if (!enumMap.isEmpty()) {
            serializeContents(enumMap, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(enumMap, jsonGenerator);
    }
}

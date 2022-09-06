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
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.map.ser.impl.PropertySerializerMap;
import com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.schema.SchemaAware;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
@JacksonStdImpl
/* loaded from: classes13.dex */
public class ObjectArraySerializer extends StdArraySerializers.ArraySerializerBase<Object[]> implements ResolvableSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final boolean _staticTyping;

    @Deprecated
    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty) {
        this(javaType, z, typeSerializer, beanProperty, null);
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(cls, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicSerializers = propertySerializerMap2;
        }
        return findAndAddSerializer.serializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.ContainerSerializerBase
    public ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new ObjectArraySerializer(this._elementType, this._staticTyping, typeSerializer, this._property, this._elementSerializer);
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        if (type != null) {
            JavaType constructType = serializerProvider.constructType(type);
            if (constructType.isArrayType()) {
                Class<?> rawClass = ((ArrayType) constructType).getContentType().getRawClass();
                if (rawClass == Object.class) {
                    createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, JsonSchema.getDefaultSchemaNode());
                } else {
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(rawClass, this._property);
                    createSchemaNode.put(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS, findValueSerializer instanceof SchemaAware ? ((SchemaAware) findValueSerializer).getSchema(serializerProvider, null) : JsonSchema.getDefaultSchemaNode());
                }
            }
        }
        return createSchemaNode;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        if (!this._staticTyping || this._elementSerializer != null) {
            return;
        }
        this._elementSerializer = serializerProvider.findValueSerializer(this._elementType, this._property);
    }

    public void serializeContentsUsing(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException, JsonGenerationException {
        int length = objArr.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        Object obj = null;
        for (int i = 0; i < length; i++) {
            try {
                obj = objArr[i];
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else if (typeSerializer == null) {
                    jsonSerializer.serialize(obj, jsonGenerator, serializerProvider);
                } else {
                    jsonSerializer.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                e = e2;
                while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                    e = e.getCause();
                }
                if (e instanceof Error) {
                    throw ((Error) e);
                }
                throw JsonMappingException.wrapWithPath(e, obj, i);
            }
        }
    }

    public void serializeTypedContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int length = objArr.length;
        TypeSerializer typeSerializer = this._valueTypeSerializer;
        int i = 0;
        Object obj = null;
        try {
            PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
            while (i < length) {
                obj = objArr[i];
                if (obj == null) {
                    serializerProvider.defaultSerializeNull(jsonGenerator);
                } else {
                    Class<?> cls = obj.getClass();
                    JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                    if (serializerFor == null) {
                        serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                    }
                    serializerFor.serializeWithType(obj, jsonGenerator, serializerProvider, typeSerializer);
                }
                i++;
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e2) {
            e = e2;
            while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                e = e.getCause();
            }
            if (e instanceof Error) {
                throw ((Error) e);
            }
            throw JsonMappingException.wrapWithPath(e, obj, i);
        }
    }

    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, BeanProperty beanProperty, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class, typeSerializer, beanProperty);
        this._elementType = javaType;
        this._staticTyping = z;
        this._dynamicSerializers = PropertySerializerMap.emptyMap();
        this._elementSerializer = jsonSerializer;
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.StdArraySerializers.ArraySerializerBase
    public void serializeContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        int length = objArr.length;
        if (length == 0) {
            return;
        }
        JsonSerializer<Object> jsonSerializer = this._elementSerializer;
        if (jsonSerializer != null) {
            serializeContentsUsing(objArr, jsonGenerator, serializerProvider, jsonSerializer);
        } else if (this._valueTypeSerializer != null) {
            serializeTypedContents(objArr, jsonGenerator, serializerProvider);
        } else {
            int i = 0;
            Object obj = null;
            try {
                PropertySerializerMap propertySerializerMap = this._dynamicSerializers;
                while (i < length) {
                    obj = objArr[i];
                    if (obj == null) {
                        serializerProvider.defaultSerializeNull(jsonGenerator);
                    } else {
                        Class<?> cls = obj.getClass();
                        JsonSerializer<Object> serializerFor = propertySerializerMap.serializerFor(cls);
                        if (serializerFor == null) {
                            if (this._elementType.hasGenericTypes()) {
                                serializerFor = _findAndAddDynamic(propertySerializerMap, serializerProvider.constructSpecializedType(this._elementType, cls), serializerProvider);
                            } else {
                                serializerFor = _findAndAddDynamic(propertySerializerMap, cls, serializerProvider);
                            }
                        }
                        serializerFor.serialize(obj, jsonGenerator, serializerProvider);
                    }
                    i++;
                }
            } catch (IOException e) {
                throw e;
            } catch (Exception e2) {
                e = e2;
                while ((e instanceof InvocationTargetException) && e.getCause() != null) {
                    e = e.getCause();
                }
                if (e instanceof Error) {
                    throw ((Error) e);
                }
                throw JsonMappingException.wrapWithPath(e, obj, i);
            }
        }
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSerializer = propertySerializerMap.findAndAddSerializer(javaType, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicSerializers = propertySerializerMap2;
        }
        return findAndAddSerializer.serializer;
    }
}

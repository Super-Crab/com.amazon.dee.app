package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.JsonGenerationException;
import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonNode;
import com.amazon.org.codehaus.jackson.map.JsonMappingException;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.ResolvableSerializer;
import com.amazon.org.codehaus.jackson.map.SerializerProvider;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.ser.AnyGetterWriter;
import com.amazon.org.codehaus.jackson.map.ser.BeanPropertyFilter;
import com.amazon.org.codehaus.jackson.map.ser.BeanPropertyWriter;
import com.amazon.org.codehaus.jackson.map.ser.FilterProvider;
import com.amazon.org.codehaus.jackson.node.ObjectNode;
import com.amazon.org.codehaus.jackson.schema.JsonSchema;
import com.amazon.org.codehaus.jackson.schema.SchemaAware;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.io.IOException;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public abstract class BeanSerializerBase extends SerializerBase<Object> implements ResolvableSerializer, SchemaAware {
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(JavaType javaType, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(javaType);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._anyGetterWriter = anyGetterWriter;
        this._propertyFilterId = obj;
    }

    protected BeanPropertyFilter findFilter(SerializerProvider serializerProvider) throws JsonMappingException {
        Object obj = this._propertyFilterId;
        FilterProvider filterProvider = serializerProvider.getFilterProvider();
        if (filterProvider != null) {
            return filterProvider.findFilter(obj);
        }
        throw new JsonMappingException("Can not resolve BeanPropertyFilter with id '" + obj + "'; no FilterProvider configured");
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        ObjectNode objectNode = createSchemaNode.objectNode();
        int i = 0;
        while (true) {
            BeanPropertyWriter[] beanPropertyWriterArr = this._props;
            if (i < beanPropertyWriterArr.length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                JavaType serializationType = beanPropertyWriter.getSerializationType();
                Type genericPropertyType = serializationType == null ? beanPropertyWriter.getGenericPropertyType() : serializationType.getRawClass();
                JsonSerializer<Object> serializer = beanPropertyWriter.getSerializer();
                if (serializer == null) {
                    Class<?> rawSerializationType = beanPropertyWriter.getRawSerializationType();
                    if (rawSerializationType == null) {
                        rawSerializationType = beanPropertyWriter.getPropertyType();
                    }
                    serializer = serializerProvider.findValueSerializer(rawSerializationType, beanPropertyWriter);
                }
                objectNode.put(beanPropertyWriter.getName(), serializer instanceof SchemaAware ? ((SchemaAware) serializer).getSchema(serializerProvider, genericPropertyType) : JsonSchema.getDefaultSchemaNode());
                i++;
            } else {
                createSchemaNode.put("properties", objectNode);
                return createSchemaNode;
            }
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        BeanPropertyWriter[] beanPropertyWriterArr2 = this._filteredProps;
        int length = beanPropertyWriterArr2 == null ? 0 : beanPropertyWriterArr2.length;
        int length2 = this._props.length;
        for (int i = 0; i < length2; i++) {
            BeanPropertyWriter beanPropertyWriter2 = this._props[i];
            if (!beanPropertyWriter2.hasSerializer()) {
                JavaType serializationType = beanPropertyWriter2.getSerializationType();
                if (serializationType == null) {
                    serializationType = serializerProvider.constructType(beanPropertyWriter2.getGenericPropertyType());
                    if (!serializationType.isFinal()) {
                        if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                            beanPropertyWriter2.setNonTrivialBaseType(serializationType);
                        }
                    }
                }
                JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(serializationType, beanPropertyWriter2);
                if (serializationType.isContainerType() && (typeSerializer = (TypeSerializer) serializationType.getContentType().getTypeHandler()) != null && (findValueSerializer instanceof ContainerSerializerBase)) {
                    findValueSerializer = ((ContainerSerializerBase) findValueSerializer).withValueTypeSerializer(typeSerializer);
                }
                this._props[i] = beanPropertyWriter2.withSerializer(findValueSerializer);
                if (i < length && (beanPropertyWriter = (beanPropertyWriterArr = this._filteredProps)[i]) != null) {
                    beanPropertyWriterArr[i] = beanPropertyWriter.withSerializer(findValueSerializer);
                }
            }
        }
        AnyGetterWriter anyGetterWriter = this._anyGetterWriter;
        if (anyGetterWriter != null) {
            anyGetterWriter.resolve(serializerProvider);
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase, com.amazon.org.codehaus.jackson.map.JsonSerializer
    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        String str = "[anySetter]";
        if (this._filteredProps != null && serializerProvider.getSerializationView() != null) {
            beanPropertyWriterArr = this._filteredProps;
        } else {
            beanPropertyWriterArr = this._props;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    beanPropertyWriter.serializeAsField(obj, jsonGenerator, serializerProvider);
                }
                i++;
            }
            if (this._anyGetterWriter == null) {
                return;
            }
            this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
        } catch (Exception e) {
            if (i != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[i].getName();
            }
            wrapAndThrow(serializerProvider, e, obj, str);
        } catch (StackOverflowError unused) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
            if (i != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[i].getName();
            }
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, str));
            throw jsonMappingException;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void serializeFieldsFiltered(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        String str = "[anySetter]";
        if (this._filteredProps != null && serializerProvider.getSerializationView() != null) {
            beanPropertyWriterArr = this._filteredProps;
        } else {
            beanPropertyWriterArr = this._props;
        }
        BeanPropertyFilter findFilter = findFilter(serializerProvider);
        if (findFilter == null) {
            serializeFields(obj, jsonGenerator, serializerProvider);
            return;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    findFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
                i++;
            }
            if (this._anyGetterWriter == null) {
                return;
            }
            this._anyGetterWriter.getAndSerialize(obj, jsonGenerator, serializerProvider);
        } catch (Exception e) {
            if (i != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[i].getName();
            }
            wrapAndThrow(serializerProvider, e, obj, str);
        } catch (StackOverflowError unused) {
            JsonMappingException jsonMappingException = new JsonMappingException("Infinite recursion (StackOverflowError)");
            if (i != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[i].getName();
            }
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, str));
            throw jsonMappingException;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        typeSerializer.writeTypePrefixForObject(obj, jsonGenerator);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffixForObject(obj, jsonGenerator);
    }

    public BeanSerializerBase(Class<?> cls, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2, AnyGetterWriter anyGetterWriter, Object obj) {
        super(cls);
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._anyGetterWriter = anyGetterWriter;
        this._propertyFilterId = obj;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        this((Class<?>) beanSerializerBase._handledType, beanSerializerBase._props, beanSerializerBase._filteredProps, beanSerializerBase._anyGetterWriter, beanSerializerBase._propertyFilterId);
    }
}

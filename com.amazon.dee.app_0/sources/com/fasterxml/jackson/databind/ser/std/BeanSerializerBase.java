package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.WritableTypeId;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.MapEntrySerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.ArrayBuilders;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.IgnorePropertiesUtil;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public abstract class BeanSerializerBase extends StdSerializer<Object> implements ContextualSerializer, ResolvableSerializer, JsonFormatVisitable, SchemaAware {
    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final JavaType _beanType;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final JsonFormat.Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    /* renamed from: com.fasterxml.jackson.databind.ser.std.BeanSerializerBase$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape = new int[JsonFormat.Shape.values().length];

        static {
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[JsonFormat.Shape.STRING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[JsonFormat.Shape.NUMBER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$fasterxml$jackson$annotation$JsonFormat$Shape[JsonFormat.Shape.NUMBER_INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(JavaType javaType, BeanSerializerBuilder beanSerializerBuilder, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(javaType);
        this._beanType = javaType;
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        if (beanSerializerBuilder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = beanSerializerBuilder.getTypeId();
        this._anyGetterWriter = beanSerializerBuilder.getAnyGetter();
        this._propertyFilterId = beanSerializerBuilder.getFilterId();
        this._objectIdWriter = beanSerializerBuilder.getObjectIdWriter();
        this._serializationShape = beanSerializerBuilder.getBeanDescription().findExpectedFormat(null).getShape();
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] beanPropertyWriterArr, NameTransformer nameTransformer) {
        if (beanPropertyWriterArr == null || beanPropertyWriterArr.length == 0 || nameTransformer == null || nameTransformer == NameTransformer.NOP) {
            return beanPropertyWriterArr;
        }
        int length = beanPropertyWriterArr.length;
        BeanPropertyWriter[] beanPropertyWriterArr2 = new BeanPropertyWriter[length];
        for (int i = 0; i < length; i++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
            if (beanPropertyWriter != null) {
                beanPropertyWriterArr2[i] = beanPropertyWriter.mo7205rename(nameTransformer);
            }
        }
        return beanPropertyWriterArr2;
    }

    @Deprecated
    protected final String _customTypeId(Object obj) {
        Object value = this._typeId.getValue(obj);
        return value == null ? "" : value instanceof String ? (String) value : value.toString();
    }

    protected void _serializeObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer, WritableObjectId writableObjectId) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableTypeId _typeIdDef = _typeIdDef(typeSerializer, obj, JsonToken.START_OBJECT);
        typeSerializer.writeTypePrefix(jsonGenerator, _typeIdDef);
        writableObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, _typeIdDef);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, boolean z) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            return;
        }
        Object generateId = findObjectId.generateId(obj);
        if (objectIdWriter.alwaysAsId) {
            objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
            return;
        }
        if (z) {
            jsonGenerator.writeStartObject(obj);
        }
        findObjectId.writeAsField(jsonGenerator, serializerProvider, objectIdWriter);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        if (!z) {
            return;
        }
        jsonGenerator.writeEndObject();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final WritableTypeId _typeIdDef(TypeSerializer typeSerializer, Object obj, JsonToken jsonToken) {
        AnnotatedMember annotatedMember = this._typeId;
        if (annotatedMember == null) {
            return typeSerializer.typeId(obj, jsonToken);
        }
        Object value = annotatedMember.getValue(obj);
        if (value == null) {
            value = "";
        }
        return typeSerializer.typeId(obj, jsonToken, value);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonObjectFormatVisitor expectObjectFormat;
        BeanPropertyWriter[] beanPropertyWriterArr;
        if (jsonFormatVisitorWrapper == null || (expectObjectFormat = jsonFormatVisitorWrapper.expectObjectFormat(javaType)) == null) {
            return;
        }
        SerializerProvider provider = jsonFormatVisitorWrapper.getProvider();
        int i = 0;
        Class<?> cls = null;
        if (this._propertyFilterId != null) {
            PropertyFilter findPropertyFilter = findPropertyFilter(jsonFormatVisitorWrapper.getProvider(), this._propertyFilterId, null);
            int length = this._props.length;
            while (i < length) {
                findPropertyFilter.depositSchemaProperty(this._props[i], expectObjectFormat, provider);
                i++;
            }
            return;
        }
        if (this._filteredProps != null && provider != null) {
            cls = provider.getActiveView();
        }
        if (cls != null) {
            beanPropertyWriterArr = this._filteredProps;
        } else {
            beanPropertyWriterArr = this._props;
        }
        int length2 = beanPropertyWriterArr.length;
        while (i < length2) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
            if (beanPropertyWriter != null) {
                beanPropertyWriter.depositSchemaProperty(expectObjectFormat, provider);
            }
            i++;
        }
    }

    protected abstract BeanSerializerBase asArraySerializer();

    @Override // com.fasterxml.jackson.databind.ser.ContextualSerializer
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        JsonFormat.Shape shape;
        BeanPropertyWriter[] beanPropertyWriterArr;
        int i;
        Set<String> set;
        Set<String> set2;
        BeanSerializerBase beanSerializerBase;
        ObjectIdWriter withSerializer;
        BeanPropertyWriter beanPropertyWriter;
        Object obj;
        ObjectIdInfo findObjectReferenceInfo;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        Object obj2 = null;
        AnnotatedMember member = (beanProperty == null || annotationIntrospector == null) ? null : beanProperty.getMember();
        SerializationConfig mo7051getConfig = serializerProvider.mo7051getConfig();
        JsonFormat.Value findFormatOverrides = findFormatOverrides(serializerProvider, beanProperty, this._handledType);
        char c = 1;
        if (findFormatOverrides == null || !findFormatOverrides.hasShape()) {
            shape = null;
        } else {
            shape = findFormatOverrides.getShape();
            if (shape != JsonFormat.Shape.ANY && shape != this._serializationShape) {
                if (this._beanType.isEnumType()) {
                    int ordinal = shape.ordinal();
                    if (ordinal == 5 || ordinal == 7 || ordinal == 8) {
                        return serializerProvider.handlePrimaryContextualization(EnumSerializer.construct(this._beanType.getRawClass(), serializerProvider.mo7051getConfig(), mo7051getConfig.introspectClassAnnotations(this._beanType), findFormatOverrides), beanProperty);
                    }
                } else if (shape == JsonFormat.Shape.NATURAL && ((!this._beanType.isMapLikeType() || !Map.class.isAssignableFrom(this._handledType)) && Map.Entry.class.isAssignableFrom(this._handledType))) {
                    JavaType findSuperType = this._beanType.findSuperType(Map.Entry.class);
                    return serializerProvider.handlePrimaryContextualization(new MapEntrySerializer(this._beanType, findSuperType.containedTypeOrUnknown(0), findSuperType.containedTypeOrUnknown(1), false, null, beanProperty), beanProperty);
                }
            }
        }
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        if (member != null) {
            set2 = annotationIntrospector.findPropertyIgnoralByName(mo7051getConfig, member).findIgnoredForSerialization();
            set = annotationIntrospector.findPropertyInclusionByName(mo7051getConfig, member).getIncluded();
            ObjectIdInfo findObjectIdInfo = annotationIntrospector.findObjectIdInfo(member);
            if (findObjectIdInfo == null) {
                if (objectIdWriter != null && (findObjectReferenceInfo = annotationIntrospector.findObjectReferenceInfo(member, null)) != null) {
                    objectIdWriter = this._objectIdWriter.withAlwaysAsId(findObjectReferenceInfo.getAlwaysAsId());
                }
                beanPropertyWriterArr = null;
            } else {
                ObjectIdInfo findObjectReferenceInfo2 = annotationIntrospector.findObjectReferenceInfo(member, findObjectIdInfo);
                Class<? extends ObjectIdGenerator<?>> generatorType = findObjectReferenceInfo2.getGeneratorType();
                JavaType javaType = serializerProvider.getTypeFactory().findTypeParameters(serializerProvider.constructType(generatorType), ObjectIdGenerator.class)[0];
                if (generatorType == ObjectIdGenerators.PropertyGenerator.class) {
                    String simpleName = findObjectReferenceInfo2.getPropertyName().getSimpleName();
                    int length = this._props.length;
                    i = 0;
                    while (true) {
                        if (i == length) {
                            JavaType javaType2 = this._beanType;
                            Object[] objArr = new Object[2];
                            objArr[0] = ClassUtil.nameOf(handledType());
                            objArr[c] = ClassUtil.name(simpleName);
                            serializerProvider.reportBadDefinition(javaType2, String.format("Invalid Object Id definition for %s: cannot find property with name %s", objArr));
                        }
                        beanPropertyWriter = this._props[i];
                        if (simpleName.equals(beanPropertyWriter.getName())) {
                            break;
                        }
                        i++;
                        c = 1;
                    }
                    beanPropertyWriterArr = null;
                    objectIdWriter = ObjectIdWriter.construct(beanPropertyWriter.getType(), null, new PropertyBasedObjectIdGenerator(findObjectReferenceInfo2, beanPropertyWriter), findObjectReferenceInfo2.getAlwaysAsId());
                    obj2 = annotationIntrospector.findFilterId(member);
                    if (obj2 != null || ((obj = this._propertyFilterId) != null && obj2.equals(obj))) {
                        obj2 = beanPropertyWriterArr;
                    }
                } else {
                    beanPropertyWriterArr = null;
                    objectIdWriter = ObjectIdWriter.construct(javaType, findObjectReferenceInfo2.getPropertyName(), serializerProvider.objectIdGeneratorInstance(member, findObjectReferenceInfo2), findObjectReferenceInfo2.getAlwaysAsId());
                }
            }
            i = 0;
            obj2 = annotationIntrospector.findFilterId(member);
            if (obj2 != null) {
            }
            obj2 = beanPropertyWriterArr;
        } else {
            beanPropertyWriterArr = null;
            i = 0;
            set = null;
            set2 = null;
        }
        if (i > 0) {
            BeanPropertyWriter[] beanPropertyWriterArr2 = this._props;
            BeanPropertyWriter[] beanPropertyWriterArr3 = (BeanPropertyWriter[]) Arrays.copyOf(beanPropertyWriterArr2, beanPropertyWriterArr2.length);
            BeanPropertyWriter beanPropertyWriter2 = beanPropertyWriterArr3[i];
            System.arraycopy(beanPropertyWriterArr3, 0, beanPropertyWriterArr3, 1, i);
            beanPropertyWriterArr3[0] = beanPropertyWriter2;
            BeanPropertyWriter[] beanPropertyWriterArr4 = this._filteredProps;
            if (beanPropertyWriterArr4 != null) {
                BeanPropertyWriter[] beanPropertyWriterArr5 = (BeanPropertyWriter[]) Arrays.copyOf(beanPropertyWriterArr4, beanPropertyWriterArr4.length);
                BeanPropertyWriter beanPropertyWriter3 = beanPropertyWriterArr5[i];
                System.arraycopy(beanPropertyWriterArr5, 0, beanPropertyWriterArr5, 1, i);
                beanPropertyWriterArr5[0] = beanPropertyWriter3;
                beanPropertyWriterArr = beanPropertyWriterArr5;
            }
            beanSerializerBase = withProperties(beanPropertyWriterArr3, beanPropertyWriterArr);
        } else {
            beanSerializerBase = this;
        }
        if (objectIdWriter != null && (withSerializer = objectIdWriter.withSerializer(serializerProvider.findValueSerializer(objectIdWriter.idType, beanProperty))) != this._objectIdWriter) {
            beanSerializerBase = beanSerializerBase.withObjectIdWriter(withSerializer);
        }
        if ((set2 != null && !set2.isEmpty()) || set != null) {
            beanSerializerBase = beanSerializerBase.mo7201withByNameInclusion(set2, set);
        }
        if (obj2 != null) {
            beanSerializerBase = beanSerializerBase.mo7210withFilterId(obj2);
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        return shape == JsonFormat.Shape.ARRAY ? beanSerializerBase.asArraySerializer() : beanSerializerBase;
    }

    protected JsonSerializer<Object> findConvertingSerializer(SerializerProvider serializerProvider, BeanPropertyWriter beanPropertyWriter) throws JsonMappingException {
        AnnotatedMember member;
        Object findSerializationConverter;
        AnnotationIntrospector annotationIntrospector = serializerProvider.getAnnotationIntrospector();
        JsonSerializer<Object> jsonSerializer = null;
        if (annotationIntrospector == null || (member = beanPropertyWriter.getMember()) == null || (findSerializationConverter = annotationIntrospector.findSerializationConverter(member)) == null) {
            return null;
        }
        Converter<Object, Object> converterInstance = serializerProvider.converterInstance(beanPropertyWriter.getMember(), findSerializationConverter);
        JavaType outputType = converterInstance.getOutputType(serializerProvider.getTypeFactory());
        if (!outputType.isJavaLangObject()) {
            jsonSerializer = serializerProvider.findValueSerializer(outputType, beanPropertyWriter);
        }
        return new StdDelegatingSerializer(converterInstance, outputType, jsonSerializer);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.jsonschema.SchemaAware
    @Deprecated
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) throws JsonMappingException {
        String id;
        ObjectNode createSchemaNode = createSchemaNode("object", true);
        JsonSerializableSchema jsonSerializableSchema = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (jsonSerializableSchema != null && (id = jsonSerializableSchema.id()) != null && !id.isEmpty()) {
            createSchemaNode.put("id", id);
        }
        ObjectNode objectNode = createSchemaNode.objectNode();
        Object obj = this._propertyFilterId;
        PropertyFilter propertyFilter = null;
        if (obj != null) {
            propertyFilter = findPropertyFilter(serializerProvider, obj, null);
        }
        int i = 0;
        while (true) {
            BeanPropertyWriter[] beanPropertyWriterArr = this._props;
            if (i < beanPropertyWriterArr.length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (propertyFilter == null) {
                    beanPropertyWriter.depositSchemaProperty(objectNode, serializerProvider);
                } else {
                    propertyFilter.depositSchemaProperty(beanPropertyWriter, objectNode, serializerProvider);
                }
                i++;
            } else {
                createSchemaNode.set("properties", objectNode);
                return createSchemaNode;
            }
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public Iterator<PropertyWriter> properties() {
        return Arrays.asList(this._props).iterator();
    }

    @Override // com.fasterxml.jackson.databind.ser.ResolvableSerializer
    public void resolve(SerializerProvider serializerProvider) throws JsonMappingException {
        BeanPropertyWriter beanPropertyWriter;
        TypeSerializer typeSerializer;
        JsonSerializer<Object> findNullValueSerializer;
        BeanPropertyWriter beanPropertyWriter2;
        BeanPropertyWriter[] beanPropertyWriterArr = this._filteredProps;
        int length = beanPropertyWriterArr == null ? 0 : beanPropertyWriterArr.length;
        int length2 = this._props.length;
        for (int i = 0; i < length2; i++) {
            BeanPropertyWriter beanPropertyWriter3 = this._props[i];
            if (!beanPropertyWriter3.willSuppressNulls() && !beanPropertyWriter3.hasNullSerializer() && (findNullValueSerializer = serializerProvider.findNullValueSerializer(beanPropertyWriter3)) != null) {
                beanPropertyWriter3.assignNullSerializer(findNullValueSerializer);
                if (i < length && (beanPropertyWriter2 = this._filteredProps[i]) != null) {
                    beanPropertyWriter2.assignNullSerializer(findNullValueSerializer);
                }
            }
            if (!beanPropertyWriter3.hasSerializer()) {
                JsonSerializer<Object> findConvertingSerializer = findConvertingSerializer(serializerProvider, beanPropertyWriter3);
                if (findConvertingSerializer == null) {
                    JavaType serializationType = beanPropertyWriter3.getSerializationType();
                    if (serializationType == null) {
                        serializationType = beanPropertyWriter3.getType();
                        if (!serializationType.isFinal()) {
                            if (serializationType.isContainerType() || serializationType.containedTypeCount() > 0) {
                                beanPropertyWriter3.setNonTrivialBaseType(serializationType);
                            }
                        }
                    }
                    JsonSerializer<Object> findValueSerializer = serializerProvider.findValueSerializer(serializationType, beanPropertyWriter3);
                    findConvertingSerializer = (!serializationType.isContainerType() || (typeSerializer = (TypeSerializer) serializationType.mo7243getContentType().getTypeHandler()) == null || !(findValueSerializer instanceof ContainerSerializer)) ? findValueSerializer : ((ContainerSerializer) findValueSerializer).withValueTypeSerializer(typeSerializer);
                }
                if (i < length && (beanPropertyWriter = this._filteredProps[i]) != null) {
                    beanPropertyWriter.assignSerializer(findConvertingSerializer);
                } else {
                    beanPropertyWriter3.assignSerializer(findConvertingSerializer);
                }
            }
        }
        AnyGetterWriter anyGetterWriter = this._anyGetterWriter;
        if (anyGetterWriter != null) {
            anyGetterWriter.resolve(serializerProvider);
        }
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public abstract void serialize(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    /* JADX INFO: Access modifiers changed from: protected */
    public void serializeFields(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        BeanPropertyWriter[] beanPropertyWriterArr;
        String str = "[anySetter]";
        if (this._filteredProps != null && serializerProvider.getActiveView() != null) {
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
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException(jsonGenerator, "Infinite recursion (StackOverflowError)", e2);
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
        if (this._filteredProps != null && serializerProvider.getActiveView() != null) {
            beanPropertyWriterArr = this._filteredProps;
        } else {
            beanPropertyWriterArr = this._props;
        }
        PropertyFilter findPropertyFilter = findPropertyFilter(serializerProvider, this._propertyFilterId, obj);
        if (findPropertyFilter == null) {
            serializeFields(obj, jsonGenerator, serializerProvider);
            return;
        }
        int i = 0;
        try {
            int length = beanPropertyWriterArr.length;
            while (i < length) {
                BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
                if (beanPropertyWriter != null) {
                    findPropertyFilter.serializeAsField(obj, jsonGenerator, serializerProvider, beanPropertyWriter);
                }
                i++;
            }
            if (this._anyGetterWriter == null) {
                return;
            }
            this._anyGetterWriter.getAndFilter(obj, jsonGenerator, serializerProvider, findPropertyFilter);
        } catch (Exception e) {
            if (i != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[i].getName();
            }
            wrapAndThrow(serializerProvider, e, obj, str);
        } catch (StackOverflowError e2) {
            JsonMappingException jsonMappingException = new JsonMappingException(jsonGenerator, "Infinite recursion (StackOverflowError)", e2);
            if (i != beanPropertyWriterArr.length) {
                str = beanPropertyWriterArr[i].getName();
            }
            jsonMappingException.prependPath(new JsonMappingException.Reference(obj, str));
            throw jsonMappingException;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public void serializeWithType(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        if (this._objectIdWriter != null) {
            jsonGenerator.setCurrentValue(obj);
            _serializeWithObjectId(obj, jsonGenerator, serializerProvider, typeSerializer);
            return;
        }
        jsonGenerator.setCurrentValue(obj);
        WritableTypeId _typeIdDef = _typeIdDef(typeSerializer, obj, JsonToken.START_OBJECT);
        typeSerializer.writeTypePrefix(jsonGenerator, _typeIdDef);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(obj, jsonGenerator, serializerProvider);
        } else {
            serializeFields(obj, jsonGenerator, serializerProvider);
        }
        typeSerializer.writeTypeSuffix(jsonGenerator, _typeIdDef);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    /* renamed from: withByNameInclusion */
    protected abstract BeanSerializerBase mo7201withByNameInclusion(Set<String> set, Set<String> set2);

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    /* renamed from: withFilterId  reason: collision with other method in class */
    public abstract BeanSerializerBase mo7210withFilterId(Object obj);

    @Deprecated
    protected BeanSerializerBase withIgnorals(Set<String> set) {
        return mo7201withByNameInclusion(set, null);
    }

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    protected abstract BeanSerializerBase withProperties(BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2);

    @Deprecated
    protected BeanSerializerBase withIgnorals(String[] strArr) {
        return withIgnorals(ArrayBuilders.arrayToSet(strArr));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object obj, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
        ObjectIdWriter objectIdWriter = this._objectIdWriter;
        WritableObjectId findObjectId = serializerProvider.findObjectId(obj, objectIdWriter.generator);
        if (findObjectId.writeAsId(jsonGenerator, serializerProvider, objectIdWriter)) {
            return;
        }
        Object generateId = findObjectId.generateId(obj);
        if (objectIdWriter.alwaysAsId) {
            objectIdWriter.serializer.serialize(generateId, jsonGenerator, serializerProvider);
        } else {
            _serializeObjectId(obj, jsonGenerator, serializerProvider, typeSerializer, findObjectId);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, BeanPropertyWriter[] beanPropertyWriterArr, BeanPropertyWriter[] beanPropertyWriterArr2) {
        super(beanSerializerBase._handledType);
        this._beanType = beanSerializerBase._beanType;
        this._props = beanPropertyWriterArr;
        this._filteredProps = beanPropertyWriterArr2;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = beanSerializerBase._objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter) {
        this(beanSerializerBase, objectIdWriter, beanSerializerBase._propertyFilterId);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, ObjectIdWriter objectIdWriter, Object obj) {
        super(beanSerializerBase._handledType);
        this._beanType = beanSerializerBase._beanType;
        this._props = beanSerializerBase._props;
        this._filteredProps = beanSerializerBase._filteredProps;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = obj;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    @Deprecated
    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, String[] strArr) {
        this(beanSerializerBase, ArrayBuilders.arrayToSet(strArr), (Set<String>) null);
    }

    @Deprecated
    protected BeanSerializerBase(BeanSerializerBase beanSerializerBase, Set<String> set) {
        this(beanSerializerBase, set, (Set<String>) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, Set<String> set, Set<String> set2) {
        super(beanSerializerBase._handledType);
        this._beanType = beanSerializerBase._beanType;
        BeanPropertyWriter[] beanPropertyWriterArr = beanSerializerBase._props;
        BeanPropertyWriter[] beanPropertyWriterArr2 = beanSerializerBase._filteredProps;
        int length = beanPropertyWriterArr.length;
        ArrayList arrayList = new ArrayList(length);
        BeanPropertyWriter[] beanPropertyWriterArr3 = null;
        ArrayList arrayList2 = beanPropertyWriterArr2 == null ? null : new ArrayList(length);
        for (int i = 0; i < length; i++) {
            BeanPropertyWriter beanPropertyWriter = beanPropertyWriterArr[i];
            if (!IgnorePropertiesUtil.shouldIgnore(beanPropertyWriter.getName(), set, set2)) {
                arrayList.add(beanPropertyWriter);
                if (beanPropertyWriterArr2 != null) {
                    arrayList2.add(beanPropertyWriterArr2[i]);
                }
            }
        }
        this._props = (BeanPropertyWriter[]) arrayList.toArray(new BeanPropertyWriter[arrayList.size()]);
        this._filteredProps = arrayList2 != null ? (BeanPropertyWriter[]) arrayList2.toArray(new BeanPropertyWriter[arrayList2.size()]) : beanPropertyWriterArr3;
        this._typeId = beanSerializerBase._typeId;
        this._anyGetterWriter = beanSerializerBase._anyGetterWriter;
        this._objectIdWriter = beanSerializerBase._objectIdWriter;
        this._propertyFilterId = beanSerializerBase._propertyFilterId;
        this._serializationShape = beanSerializerBase._serializationShape;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase) {
        this(beanSerializerBase, beanSerializerBase._props, beanSerializerBase._filteredProps);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public BeanSerializerBase(BeanSerializerBase beanSerializerBase, NameTransformer nameTransformer) {
        this(beanSerializerBase, rename(beanSerializerBase._props, nameTransformer), rename(beanSerializerBase._filteredProps, nameTransformer));
    }
}

package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import java.io.IOException;
import java.util.Objects;
@JacksonStdImpl
/* loaded from: classes2.dex */
public class ObjectArraySerializer extends ArraySerializerBase<Object[]> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicSerializers;
    protected JsonSerializer<Object> _elementSerializer;
    protected final JavaType _elementType;
    protected final boolean _staticTyping;
    protected final TypeSerializer _valueTypeSerializer;

    public ObjectArraySerializer(JavaType javaType, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer) {
        super(Object[].class);
        this._elementType = javaType;
        this._staticTyping = z;
        this._valueTypeSerializer = typeSerializer;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        this._elementSerializer = jsonSerializer;
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, Class<?> cls, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(cls, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSecondarySerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicSerializers = propertySerializerMap2;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public JsonSerializer<?> _withResolved(BeanProperty beanProperty, Boolean bool) {
        return new ObjectArraySerializer(this, beanProperty, this._valueTypeSerializer, this._elementSerializer, bool);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    /* renamed from: _withValueTypeSerializer */
    public ContainerSerializer<?> mo7209_withValueTypeSerializer(TypeSerializer typeSerializer) {
        return new ObjectArraySerializer(this._elementType, this._staticTyping, typeSerializer, this._elementSerializer);
    }

    @Override // com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable
    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        JsonArrayFormatVisitor expectArrayFormat = jsonFormatVisitorWrapper.expectArrayFormat(javaType);
        if (expectArrayFormat != null) {
            JavaType javaType2 = this._elementType;
            JsonSerializer<Object> jsonSerializer = this._elementSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = jsonFormatVisitorWrapper.getProvider().findContentValueSerializer(javaType2, this._property);
            }
            expectArrayFormat.itemsFormat(jsonSerializer, javaType2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:15:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0033  */
    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.ser.ContextualSerializer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.fasterxml.jackson.databind.JsonSerializer<?> createContextual(com.fasterxml.jackson.databind.SerializerProvider r6, com.fasterxml.jackson.databind.BeanProperty r7) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r5 = this;
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r5._valueTypeSerializer
            if (r0 == 0) goto L8
            com.fasterxml.jackson.databind.jsontype.TypeSerializer r0 = r0.mo7152forProperty(r7)
        L8:
            r1 = 0
            if (r7 == 0) goto L20
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r2 = r7.getMember()
            com.fasterxml.jackson.databind.AnnotationIntrospector r3 = r6.getAnnotationIntrospector()
            if (r2 == 0) goto L20
            java.lang.Object r3 = r3.findContentSerializer(r2)
            if (r3 == 0) goto L20
            com.fasterxml.jackson.databind.JsonSerializer r2 = r6.serializerInstance(r2, r3)
            goto L21
        L20:
            r2 = r1
        L21:
            java.lang.Class r3 = r5.handledType()
            com.fasterxml.jackson.annotation.JsonFormat$Value r3 = r5.findFormatOverrides(r6, r7, r3)
            if (r3 == 0) goto L31
            com.fasterxml.jackson.annotation.JsonFormat$Feature r1 = com.fasterxml.jackson.annotation.JsonFormat.Feature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED
            java.lang.Boolean r1 = r3.getFeature(r1)
        L31:
            if (r2 != 0) goto L35
            com.fasterxml.jackson.databind.JsonSerializer<java.lang.Object> r2 = r5._elementSerializer
        L35:
            com.fasterxml.jackson.databind.JsonSerializer r2 = r5.findContextualConvertingSerializer(r6, r7, r2)
            if (r2 != 0) goto L4f
            com.fasterxml.jackson.databind.JavaType r3 = r5._elementType
            if (r3 == 0) goto L4f
            boolean r4 = r5._staticTyping
            if (r4 == 0) goto L4f
            boolean r3 = r3.isJavaLangObject()
            if (r3 != 0) goto L4f
            com.fasterxml.jackson.databind.JavaType r2 = r5._elementType
            com.fasterxml.jackson.databind.JsonSerializer r2 = r6.findContentValueSerializer(r2, r7)
        L4f:
            com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer r6 = r5.withResolved(r7, r0, r2, r1)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.std.ObjectArraySerializer.createContextual(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.BeanProperty):com.fasterxml.jackson.databind.JsonSerializer");
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JsonSerializer<?> getContentSerializer() {
        return this._elementSerializer;
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public JavaType getContentType() {
        return this._elementType;
    }

    public void serializeContentsUsing(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, JsonSerializer<Object> jsonSerializer) throws IOException {
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
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, e, obj, i);
                return;
            }
        }
    }

    public void serializeTypedContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
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
        } catch (Exception e) {
            wrapAndThrow(serializerProvider, e, obj, i);
        }
    }

    public ObjectArraySerializer withResolved(BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        return (this._property == beanProperty && jsonSerializer == this._elementSerializer && this._valueTypeSerializer == typeSerializer && Objects.equals(this._unwrapSingle, bool)) ? this : new ObjectArraySerializer(this, beanProperty, typeSerializer, jsonSerializer, bool);
    }

    @Override // com.fasterxml.jackson.databind.ser.ContainerSerializer
    public boolean hasSingleElement(Object[] objArr) {
        return objArr.length == 1;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public boolean isEmpty(SerializerProvider serializerProvider, Object[] objArr) {
        return objArr.length == 0;
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase, com.fasterxml.jackson.databind.ser.std.StdSerializer, com.fasterxml.jackson.databind.JsonSerializer
    public final void serialize(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        int length = objArr.length;
        if (length == 1 && ((this._unwrapSingle == null && serializerProvider.isEnabled(SerializationFeature.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) || this._unwrapSingle == Boolean.TRUE)) {
            serializeContents(objArr, jsonGenerator, serializerProvider);
            return;
        }
        jsonGenerator.writeStartArray(objArr, length);
        serializeContents(objArr, jsonGenerator, serializerProvider);
        jsonGenerator.writeEndArray();
    }

    @Override // com.fasterxml.jackson.databind.ser.std.ArraySerializerBase
    public void serializeContents(Object[] objArr, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
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
            } catch (Exception e) {
                wrapAndThrow(serializerProvider, e, obj, i);
            }
        }
    }

    protected final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap propertySerializerMap, JavaType javaType, SerializerProvider serializerProvider) throws JsonMappingException {
        PropertySerializerMap.SerializerAndMapResult findAndAddSecondarySerializer = propertySerializerMap.findAndAddSecondarySerializer(javaType, serializerProvider, this._property);
        PropertySerializerMap propertySerializerMap2 = findAndAddSecondarySerializer.map;
        if (propertySerializerMap != propertySerializerMap2) {
            this._dynamicSerializers = propertySerializerMap2;
        }
        return findAndAddSecondarySerializer.serializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, TypeSerializer typeSerializer) {
        super(objectArraySerializer);
        this._elementType = objectArraySerializer._elementType;
        this._valueTypeSerializer = typeSerializer;
        this._staticTyping = objectArraySerializer._staticTyping;
        this._dynamicSerializers = objectArraySerializer._dynamicSerializers;
        this._elementSerializer = objectArraySerializer._elementSerializer;
    }

    public ObjectArraySerializer(ObjectArraySerializer objectArraySerializer, BeanProperty beanProperty, TypeSerializer typeSerializer, JsonSerializer<?> jsonSerializer, Boolean bool) {
        super(objectArraySerializer, beanProperty, bool);
        this._elementType = objectArraySerializer._elementType;
        this._valueTypeSerializer = typeSerializer;
        this._staticTyping = objectArraySerializer._staticTyping;
        this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        this._elementSerializer = jsonSerializer;
    }
}

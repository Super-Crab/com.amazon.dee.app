package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.JsonGenerator;
import com.amazon.org.codehaus.jackson.JsonProcessingException;
import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import java.io.IOException;
/* loaded from: classes13.dex */
public class AsExternalTypeSerializer extends TypeSerializerBase {
    protected final String _typePropertyName;

    public AsExternalTypeSerializer(TypeIdResolver typeIdResolver, BeanProperty beanProperty, String str) {
        super(typeIdResolver, beanProperty);
        this._typePropertyName = str;
    }

    protected final void _writePrefix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
    }

    protected final void _writeSuffix(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        jsonGenerator.writeEndObject();
        jsonGenerator.writeStringField(this._typePropertyName, this._idResolver.idFromValue(obj));
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, com.amazon.org.codehaus.jackson.map.TypeSerializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeSerializerBase, com.amazon.org.codehaus.jackson.map.TypeSerializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForArray(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeSuffix(obj, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForObject(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeSuffix(obj, jsonGenerator);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypeSuffixForScalar(Object obj, JsonGenerator jsonGenerator) throws IOException, JsonProcessingException {
        _writeSuffix(obj, jsonGenerator);
    }

    protected final void _writePrefix(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        jsonGenerator.writeStartObject();
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForArray(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForObject(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator, cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public void writeTypePrefixForScalar(Object obj, JsonGenerator jsonGenerator, Class<?> cls) throws IOException, JsonProcessingException {
        _writePrefix(obj, jsonGenerator, cls);
    }
}

package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public class AsExternalTypeDeserializer extends AsArrayTypeDeserializer {
    protected final String _typePropertyName;

    public AsExternalTypeDeserializer(JavaType javaType, TypeIdResolver typeIdResolver, BeanProperty beanProperty, Class<?> cls, String str) {
        super(javaType, typeIdResolver, beanProperty, cls);
        this._typePropertyName = str;
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public String getPropertyName() {
        return this._typePropertyName;
    }

    @Override // com.amazon.org.codehaus.jackson.map.jsontype.impl.AsArrayTypeDeserializer, com.amazon.org.codehaus.jackson.map.jsontype.impl.TypeDeserializerBase, com.amazon.org.codehaus.jackson.map.TypeDeserializer
    public JsonTypeInfo.As getTypeInclusion() {
        return JsonTypeInfo.As.EXTERNAL_PROPERTY;
    }
}

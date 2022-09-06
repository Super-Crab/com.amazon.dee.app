package com.amazon.org.codehaus.jackson.map.jsontype.impl;

import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
/* loaded from: classes13.dex */
public abstract class TypeSerializerBase extends TypeSerializer {
    protected final TypeIdResolver _idResolver;
    protected final BeanProperty _property;

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeSerializerBase(TypeIdResolver typeIdResolver, BeanProperty beanProperty) {
        this._idResolver = typeIdResolver;
        this._property = beanProperty;
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public String getPropertyName() {
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public TypeIdResolver getTypeIdResolver() {
        return this._idResolver;
    }

    @Override // com.amazon.org.codehaus.jackson.map.TypeSerializer
    public abstract JsonTypeInfo.As getTypeInclusion();
}

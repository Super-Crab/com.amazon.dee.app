package com.fasterxml.jackson.databind.jsontype.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
/* loaded from: classes2.dex */
public abstract class TypeIdResolverBase implements TypeIdResolver {
    protected final JavaType _baseType;
    protected final TypeFactory _typeFactory;

    protected TypeIdResolverBase() {
        this(null, null);
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public String getDescForKnownTypeIds() {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public String idFromBaseType() {
        return idFromValueAndType(null, this._baseType.getRawClass());
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public void init(JavaType javaType) {
    }

    @Override // com.fasterxml.jackson.databind.jsontype.TypeIdResolver
    public JavaType typeFromId(DatabindContext databindContext, String str) throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Sub-class ");
        outline107.append(getClass().getName());
        outline107.append(" MUST implement `typeFromId(DatabindContext,String)");
        throw new IllegalStateException(outline107.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TypeIdResolverBase(JavaType javaType, TypeFactory typeFactory) {
        this._baseType = javaType;
        this._typeFactory = typeFactory;
    }
}

package com.fasterxml.jackson.databind.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
/* loaded from: classes2.dex */
public class PlaceholderForType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _actualType;
    protected final int _ordinal;

    public PlaceholderForType(int i) {
        super(Object.class, TypeBindings.emptyBindings(), TypeFactory.unknownType(), null, 1, null, null, false);
        this._ordinal = i;
    }

    private <T> T _unsupported() {
        throw new UnsupportedOperationException(GeneratedOutlineSupport1.outline38(PlaceholderForType.class, GeneratedOutlineSupport1.outline107("Operation should not be attempted on ")));
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return (JavaType) _unsupported();
    }

    public JavaType actualType() {
        return this._actualType;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase
    protected String buildCanonicalName() {
        return toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(Object obj) {
        return obj == this;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append('$');
        sb.append(this._ordinal + 1);
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        return getErasedSignature(sb);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public boolean isContainerType() {
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return (JavaType) _unsupported();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        return getErasedSignature(new StringBuilder()).toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentType(JavaType javaType) {
        return (JavaType) _unsupported();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentTypeHandler */
    public JavaType mo7245withContentTypeHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentValueHandler */
    public JavaType mo7250withContentValueHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withStaticTyping */
    public JavaType mo7251withStaticTyping() {
        return (JavaType) _unsupported();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withTypeHandler */
    public JavaType mo7252withTypeHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withValueHandler */
    public JavaType mo7253withValueHandler(Object obj) {
        return (JavaType) _unsupported();
    }

    public void actualType(JavaType javaType) {
        this._actualType = javaType;
    }
}

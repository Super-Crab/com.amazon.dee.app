package com.fasterxml.jackson.databind.type;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
/* loaded from: classes2.dex */
public class ResolvedRecursiveType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected JavaType _referencedType;

    public ResolvedRecursiveType(Class<?> cls, TypeBindings typeBindings) {
        super(cls, typeBindings, null, null, 0, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == ResolvedRecursiveType.class) {
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public TypeBindings getBindings() {
        JavaType javaType = this._referencedType;
        if (javaType != null) {
            return javaType.getBindings();
        }
        return super.getBindings();
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        JavaType javaType = this._referencedType;
        return javaType != null ? javaType.getErasedSignature(sb) : sb;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        JavaType javaType = this._referencedType;
        if (javaType != null) {
            return javaType.getErasedSignature(sb);
        }
        sb.append(WebConstants.UriConstants.QUESTIONMARK_KEY);
        return sb;
    }

    public JavaType getSelfReferencedType() {
        return this._referencedType;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public JavaType getSuperClass() {
        JavaType javaType = this._referencedType;
        if (javaType != null) {
            return javaType.getSuperClass();
        }
        return super.getSuperClass();
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public boolean isContainerType() {
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return null;
    }

    public void setReference(JavaType javaType) {
        if (this._referencedType == null) {
            this._referencedType = javaType;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Trying to re-set self reference; old value = ");
        outline107.append(this._referencedType);
        outline107.append(", new = ");
        outline107.append(javaType);
        throw new IllegalStateException(outline107.toString());
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        StringBuilder outline105 = GeneratedOutlineSupport1.outline105(40, "[recursive type; ");
        JavaType javaType = this._referencedType;
        if (javaType == null) {
            outline105.append("UNRESOLVED");
        } else {
            outline105.append(javaType.getRawClass().getName());
        }
        return outline105.toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentType(JavaType javaType) {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentTypeHandler */
    public JavaType mo7245withContentTypeHandler(Object obj) {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentValueHandler */
    public JavaType mo7250withContentValueHandler(Object obj) {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withStaticTyping */
    public JavaType mo7251withStaticTyping() {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withTypeHandler */
    public JavaType mo7252withTypeHandler(Object obj) {
        return this;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withValueHandler */
    public JavaType mo7253withValueHandler(Object obj) {
        return this;
    }
}

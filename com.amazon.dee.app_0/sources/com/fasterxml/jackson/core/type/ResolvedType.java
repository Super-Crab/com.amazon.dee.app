package com.fasterxml.jackson.core.type;
/* loaded from: classes2.dex */
public abstract class ResolvedType {
    /* renamed from: containedType */
    public abstract ResolvedType mo7254containedType(int i);

    public abstract int containedTypeCount();

    public abstract String containedTypeName(int i);

    /* renamed from: getContentType */
    public abstract ResolvedType mo7243getContentType();

    /* renamed from: getKeyType */
    public abstract ResolvedType mo7229getKeyType();

    @Deprecated
    public Class<?> getParameterSource() {
        return null;
    }

    public abstract Class<?> getRawClass();

    /* renamed from: getReferencedType */
    public abstract ResolvedType mo7244getReferencedType();

    public abstract boolean hasGenericTypes();

    public abstract boolean hasRawClass(Class<?> cls);

    public abstract boolean isAbstract();

    public abstract boolean isArrayType();

    public abstract boolean isCollectionLikeType();

    public abstract boolean isConcrete();

    public abstract boolean isContainerType();

    public abstract boolean isEnumType();

    public abstract boolean isFinal();

    public abstract boolean isInterface();

    public abstract boolean isMapLikeType();

    public abstract boolean isPrimitive();

    public boolean isReferenceType() {
        return mo7244getReferencedType() != null;
    }

    public abstract boolean isThrowable();

    public abstract String toCanonical();
}

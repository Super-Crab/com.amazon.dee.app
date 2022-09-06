package com.fasterxml.jackson.databind.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.TypeVariable;
import java.util.Collection;
import kotlin.text.Typography;
/* loaded from: classes2.dex */
public class CollectionLikeType extends TypeBase {
    private static final long serialVersionUID = 1;
    protected final JavaType _elementType;

    /* JADX INFO: Access modifiers changed from: protected */
    public CollectionLikeType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2.hashCode(), obj, obj2, z);
        this._elementType = javaType2;
    }

    public static CollectionLikeType construct(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2) {
        return new CollectionLikeType(cls, typeBindings, javaType, javaTypeArr, javaType2, null, null, false);
    }

    public static CollectionLikeType upgradeFrom(JavaType javaType, JavaType javaType2) {
        if (javaType instanceof TypeBase) {
            return new CollectionLikeType((TypeBase) javaType, javaType2);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot upgrade from an instance of ");
        outline107.append(javaType.getClass());
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return new CollectionLikeType(cls, this._bindings, this._superClass, this._superInterfaces, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._elementType != null && _hasNTypeParameters(1)) {
            sb.append(Typography.less);
            sb.append(this._elementType.toCanonical());
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        CollectionLikeType collectionLikeType = (CollectionLikeType) obj;
        return this._class == collectionLikeType._class && this._elementType.equals(collectionLikeType._elementType);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public Object getContentTypeHandler() {
        return this._elementType.getTypeHandler();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public Object getContentValueHandler() {
        return this._elementType.getValueHandler();
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return TypeBase._classSignature(this._class, sb, true);
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        TypeBase._classSignature(this._class, sb, false);
        sb.append(Typography.less);
        this._elementType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean hasHandlers() {
        return super.hasHandlers() || this._elementType.hasHandlers();
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public boolean isCollectionLikeType() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public boolean isContainerType() {
        return true;
    }

    @Deprecated
    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new CollectionLikeType(cls, typeBindings, javaType, javaTypeArr, this._elementType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[collection-like type; class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", contains ");
        outline107.append(this._elementType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentType(JavaType javaType) {
        return this._elementType == javaType ? this : new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, javaType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withHandlersFrom(JavaType javaType) {
        JavaType withHandlersFrom;
        JavaType withHandlersFrom2 = super.withHandlersFrom(javaType);
        JavaType mo7243getContentType = javaType.mo7243getContentType();
        return (mo7243getContentType == null || (withHandlersFrom = this._elementType.withHandlersFrom(mo7243getContentType)) == this._elementType) ? withHandlersFrom2 : withHandlersFrom2.withContentType(withHandlersFrom);
    }

    @Deprecated
    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        TypeBindings emptyBindings;
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters != null && typeParameters.length == 1) {
            emptyBindings = TypeBindings.create(cls, javaType);
        } else {
            emptyBindings = TypeBindings.emptyBindings();
        }
        return new CollectionLikeType(cls, emptyBindings, TypeBase._bogusSuperClass(cls), null, javaType, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    /* renamed from: getContentType  reason: collision with other method in class */
    public JavaType mo7243getContentType() {
        return this._elementType;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentTypeHandler  reason: collision with other method in class */
    public CollectionLikeType mo7245withContentTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.mo7252withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentValueHandler  reason: collision with other method in class */
    public CollectionLikeType mo7250withContentValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.mo7253withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withStaticTyping  reason: collision with other method in class */
    public CollectionLikeType mo7251withStaticTyping() {
        return this._asStatic ? this : new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType.mo7251withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withTypeHandler  reason: collision with other method in class */
    public CollectionLikeType mo7252withTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, this._valueHandler, obj, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withValueHandler  reason: collision with other method in class */
    public CollectionLikeType mo7253withValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._bindings, this._superClass, this._superInterfaces, this._elementType, obj, this._typeHandler, this._asStatic);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CollectionLikeType(TypeBase typeBase, JavaType javaType) {
        super(typeBase);
        this._elementType = javaType;
    }
}

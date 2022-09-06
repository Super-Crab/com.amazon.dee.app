package com.fasterxml.jackson.databind.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
import java.lang.reflect.TypeVariable;
/* loaded from: classes2.dex */
public final class MapType extends MapLikeType {
    private static final long serialVersionUID = 1;

    private MapType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, JavaType javaType3, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, javaType2, javaType3, obj, obj2, z);
    }

    public static MapType construct(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, JavaType javaType2, JavaType javaType3) {
        return new MapType(cls, typeBindings, javaType, javaTypeArr, javaType2, javaType3, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        return new MapType(cls, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return new MapType(cls, typeBindings, javaType, javaTypeArr, this._keyType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[map type; class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", ");
        outline107.append(this._keyType);
        outline107.append(" -> ");
        outline107.append(this._valueType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    public JavaType withContentType(JavaType javaType) {
        return this._valueType == javaType ? this : new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, javaType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    protected MapType(TypeBase typeBase, JavaType javaType, JavaType javaType2) {
        super(typeBase, javaType, javaType2);
    }

    @Deprecated
    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        TypeBindings emptyBindings;
        TypeVariable<Class<?>>[] typeParameters = cls.getTypeParameters();
        if (typeParameters != null && typeParameters.length == 2) {
            emptyBindings = TypeBindings.create(cls, javaType, javaType2);
        } else {
            emptyBindings = TypeBindings.emptyBindings();
        }
        return new MapType(cls, emptyBindings, TypeBase._bogusSuperClass(cls), null, javaType, javaType2, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType
    /* renamed from: withKeyType  reason: collision with other method in class */
    public MapType mo7237withKeyType(JavaType javaType) {
        return javaType == this._keyType ? this : new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, javaType, this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType
    /* renamed from: withKeyTypeHandler  reason: collision with other method in class */
    public MapType mo7238withKeyTypeHandler(Object obj) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.mo7252withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType
    /* renamed from: withKeyValueHandler  reason: collision with other method in class */
    public MapType mo7239withKeyValueHandler(Object obj) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.mo7253withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentTypeHandler */
    public MapType mo7245withContentTypeHandler(Object obj) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.mo7252withTypeHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentValueHandler */
    public MapType mo7250withContentValueHandler(Object obj) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType.mo7253withValueHandler(obj), this._valueHandler, this._typeHandler, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    /* renamed from: withStaticTyping */
    public MapType mo7251withStaticTyping() {
        return this._asStatic ? this : new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType.mo7251withStaticTyping(), this._valueType.mo7251withStaticTyping(), this._valueHandler, this._typeHandler, true);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    /* renamed from: withTypeHandler */
    public MapType mo7252withTypeHandler(Object obj) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, this._valueHandler, obj, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.type.MapLikeType, com.fasterxml.jackson.databind.JavaType
    /* renamed from: withValueHandler */
    public MapType mo7253withValueHandler(Object obj) {
        return new MapType(this._class, this._bindings, this._superClass, this._superInterfaces, this._keyType, this._valueType, obj, this._typeHandler, this._asStatic);
    }
}

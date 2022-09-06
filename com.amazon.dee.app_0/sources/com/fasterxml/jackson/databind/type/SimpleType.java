package com.fasterxml.jackson.databind.type;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
import java.util.Collection;
import java.util.Map;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes2.dex */
public class SimpleType extends TypeBase {
    private static final long serialVersionUID = 1;

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls) {
        this(cls, TypeBindings.emptyBindings(), null, null);
    }

    private static JavaType _buildSuperClass(Class<?> cls, TypeBindings typeBindings) {
        if (cls == null) {
            return null;
        }
        if (cls == Object.class) {
            return TypeFactory.unknownType();
        }
        return new SimpleType(cls, typeBindings, _buildSuperClass(cls.getSuperclass(), typeBindings), null, null, null, false);
    }

    @Deprecated
    public static SimpleType construct(Class<?> cls) {
        if (!Map.class.isAssignableFrom(cls)) {
            if (!Collection.class.isAssignableFrom(cls)) {
                if (!cls.isArray()) {
                    TypeBindings emptyBindings = TypeBindings.emptyBindings();
                    return new SimpleType(cls, emptyBindings, _buildSuperClass(cls.getSuperclass(), emptyBindings), null, null, null, false);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Cannot construct SimpleType for an array (class: "), ")"));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Cannot construct SimpleType for a Collection (class: "), ")"));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Cannot construct SimpleType for a Map (class: "), ")"));
    }

    public static SimpleType constructUnsafe(Class<?> cls) {
        return new SimpleType(cls, null, null, null, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    @Deprecated
    protected JavaType _narrow(Class<?> cls) {
        Class<?>[] interfaces;
        Class<?> cls2 = this._class;
        if (cls2 == cls) {
            return this;
        }
        if (!cls2.isAssignableFrom(cls)) {
            return new SimpleType(cls, this._bindings, this, this._superInterfaces, this._valueHandler, this._typeHandler, this._asStatic);
        }
        Class<? super Object> superclass = cls.getSuperclass();
        Class<?> cls3 = this._class;
        if (superclass == cls3) {
            return new SimpleType(cls, this._bindings, this, this._superInterfaces, this._valueHandler, this._typeHandler, this._asStatic);
        }
        if (superclass != null && cls3.isAssignableFrom(superclass)) {
            return new SimpleType(cls, this._bindings, _narrow(superclass), null, this._valueHandler, this._typeHandler, this._asStatic);
        }
        for (Class<?> cls4 : cls.getInterfaces()) {
            Class<?> cls5 = this._class;
            if (cls4 == cls5) {
                return new SimpleType(cls, this._bindings, null, new JavaType[]{this}, this._valueHandler, this._typeHandler, this._asStatic);
            }
            if (cls5.isAssignableFrom(cls4)) {
                return new SimpleType(cls, this._bindings, null, new JavaType[]{_narrow(cls4)}, this._valueHandler, this._typeHandler, this._asStatic);
            }
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Internal error: Cannot resolve sub-type for Class ");
        GeneratedOutlineSupport1.outline146(cls, outline107, " to ");
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline38(this._class, outline107));
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        int size = this._bindings.size();
        if (size > 0 && _hasNTypeParameters(size)) {
            sb.append(Typography.less);
            for (int i = 0; i < size; i++) {
                JavaType mo7254containedType = mo7254containedType(i);
                if (i > 0) {
                    sb.append(JsonReaderKt.COMMA);
                }
                sb.append(mo7254containedType.toCanonical());
            }
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
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class == this._class) {
            return this._bindings.equals(simpleType._bindings);
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return TypeBase._classSignature(this._class, sb, true);
    }

    @Override // com.fasterxml.jackson.databind.type.TypeBase, com.fasterxml.jackson.databind.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        TypeBase._classSignature(this._class, sb, false);
        int size = this._bindings.size();
        if (size > 0) {
            sb.append(Typography.less);
            for (int i = 0; i < size; i++) {
                sb = mo7254containedType(i).getGenericSignature(sb);
            }
            sb.append(Typography.greater);
        }
        sb.append(';');
        return sb;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public boolean hasContentType() {
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.core.type.ResolvedType
    public boolean isContainerType() {
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType refine(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public String toString() {
        return GeneratedOutlineSupport1.outline89(GeneratedOutlineSupport1.outline105(40, "[simple type, class "), buildCanonicalName(), JsonReaderKt.END_LIST);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    public JavaType withContentType(JavaType javaType) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContentType()");
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentTypeHandler */
    public JavaType mo7245withContentTypeHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContenTypeHandler()");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr) {
        this(cls, typeBindings, javaType, javaTypeArr, null, null, false);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withContentValueHandler  reason: collision with other method in class */
    public SimpleType mo7250withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; cannot call withContenValueHandler()");
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withStaticTyping  reason: collision with other method in class */
    public SimpleType mo7251withStaticTyping() {
        return this._asStatic ? this : new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, this._typeHandler, true);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withTypeHandler  reason: collision with other method in class */
    public SimpleType mo7252withTypeHandler(Object obj) {
        return this._typeHandler == obj ? this : new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, obj, this._asStatic);
    }

    @Override // com.fasterxml.jackson.databind.JavaType
    /* renamed from: withValueHandler  reason: collision with other method in class */
    public SimpleType mo7253withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, obj, this._typeHandler, this._asStatic);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(TypeBase typeBase) {
        super(typeBase);
    }

    protected SimpleType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, 0, obj, obj2, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls, TypeBindings typeBindings, JavaType javaType, JavaType[] javaTypeArr, int i, Object obj, Object obj2, boolean z) {
        super(cls, typeBindings, javaType, javaTypeArr, i, obj, obj2, z);
    }
}

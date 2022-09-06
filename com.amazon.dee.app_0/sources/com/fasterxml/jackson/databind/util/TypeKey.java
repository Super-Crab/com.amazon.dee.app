package com.fasterxml.jackson.databind.util;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.JavaType;
/* loaded from: classes2.dex */
public class TypeKey {
    protected Class<?> _class;
    protected int _hashCode;
    protected boolean _isTyped;
    protected JavaType _type;

    public TypeKey() {
    }

    public static final int typedHash(Class<?> cls) {
        return cls.getName().hashCode() + 1;
    }

    public static final int untypedHash(Class<?> cls) {
        return cls.getName().hashCode();
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != TypeKey.class) {
            return false;
        }
        TypeKey typeKey = (TypeKey) obj;
        if (typeKey._isTyped != this._isTyped) {
            return false;
        }
        Class<?> cls = this._class;
        if (cls == null) {
            return this._type.equals(typeKey._type);
        }
        return typeKey._class == cls;
    }

    public Class<?> getRawType() {
        return this._class;
    }

    public JavaType getType() {
        return this._type;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public boolean isTyped() {
        return this._isTyped;
    }

    public final void resetTyped(Class<?> cls) {
        this._type = null;
        this._class = cls;
        this._isTyped = true;
        this._hashCode = typedHash(cls);
    }

    public final void resetUntyped(Class<?> cls) {
        this._type = null;
        this._class = cls;
        this._isTyped = false;
        this._hashCode = untypedHash(cls);
    }

    public final String toString() {
        if (this._class != null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("{class: ");
            GeneratedOutlineSupport1.outline146(this._class, outline107, ", typed? ");
            return GeneratedOutlineSupport1.outline97(outline107, this._isTyped, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        }
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("{type: ");
        outline1072.append(this._type);
        outline1072.append(", typed? ");
        return GeneratedOutlineSupport1.outline97(outline1072, this._isTyped, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }

    public TypeKey(TypeKey typeKey) {
        this._hashCode = typeKey._hashCode;
        this._class = typeKey._class;
        this._type = typeKey._type;
        this._isTyped = typeKey._isTyped;
    }

    public static final int typedHash(JavaType javaType) {
        return javaType.hashCode() - 2;
    }

    public static final int untypedHash(JavaType javaType) {
        return javaType.hashCode() - 1;
    }

    public final void resetTyped(JavaType javaType) {
        this._type = javaType;
        this._class = null;
        this._isTyped = true;
        this._hashCode = typedHash(javaType);
    }

    public final void resetUntyped(JavaType javaType) {
        this._type = javaType;
        this._class = null;
        this._isTyped = false;
        this._hashCode = untypedHash(javaType);
    }

    public TypeKey(Class<?> cls, boolean z) {
        this._class = cls;
        this._type = null;
        this._isTyped = z;
        this._hashCode = z ? typedHash(cls) : untypedHash(cls);
    }

    public TypeKey(JavaType javaType, boolean z) {
        this._type = javaType;
        this._class = null;
        this._isTyped = z;
        this._hashCode = z ? typedHash(javaType) : untypedHash(javaType);
    }
}

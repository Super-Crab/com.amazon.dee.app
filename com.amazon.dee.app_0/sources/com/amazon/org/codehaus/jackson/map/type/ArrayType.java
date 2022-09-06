package com.amazon.org.codehaus.jackson.map.type;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Array;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public final class ArrayType extends TypeBase {
    protected final JavaType _componentType;
    protected final Object _emptyArray;

    private ArrayType(JavaType javaType, Object obj, Object obj2, Object obj3) {
        super(obj.getClass(), javaType.hashCode(), obj2, obj3);
        this._componentType = javaType;
        this._emptyArray = obj;
    }

    @Deprecated
    public static ArrayType construct(JavaType javaType) {
        return construct(javaType, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        if (cls.isArray()) {
            return construct(TypeFactory.defaultInstance().constructType(cls.getComponentType()), this._valueHandler, this._typeHandler);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Incompatible narrowing operation: trying to narrow ");
        outline107.append(toString());
        outline107.append(" to class ");
        outline107.append(cls.getName());
        throw new IllegalArgumentException(outline107.toString());
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        return this._class.getName();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._componentType;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        return 1;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i == 0) {
            return ExifInterface.LONGITUDE_EAST;
        }
        return null;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != ArrayType.class) {
            return false;
        }
        return this._componentType.equals(((ArrayType) obj)._componentType);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._componentType;
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        return this._componentType.getErasedSignature(sb);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        sb.append(JsonReaderKt.BEGIN_LIST);
        return this._componentType.getGenericSignature(sb);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean hasGenericTypes() {
        return this._componentType.hasGenericTypes();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isAbstract() {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isArrayType() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isConcrete() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[array type, component type: ");
        outline107.append(this._componentType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._componentType.getRawClass() ? this : construct(this._componentType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public static ArrayType construct(JavaType javaType, Object obj, Object obj2) {
        return new ArrayType(javaType, Array.newInstance(javaType.getRawClass(), 0), null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentTypeHandler */
    public ArrayType mo4242withContentTypeHandler(Object obj) {
        return obj == this._componentType.getTypeHandler() ? this : new ArrayType(this._componentType.mo4249withTypeHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentValueHandler */
    public ArrayType mo4248withContentValueHandler(Object obj) {
        return obj == this._componentType.getValueHandler() ? this : new ArrayType(this._componentType.mo4250withValueHandler(obj), this._emptyArray, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withTypeHandler */
    public ArrayType mo4249withTypeHandler(Object obj) {
        return obj == this._typeHandler ? this : new ArrayType(this._componentType, this._emptyArray, this._valueHandler, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withValueHandler */
    public ArrayType mo4250withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new ArrayType(this._componentType, this._emptyArray, obj, this._typeHandler);
    }
}

package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import java.util.Map;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public final class SimpleType extends TypeBase {
    protected final String[] _typeNames;
    protected final JavaType[] _typeParameters;

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls) {
        this(cls, null, null, null, null);
    }

    public static SimpleType construct(Class<?> cls) {
        if (!Map.class.isAssignableFrom(cls)) {
            if (!Collection.class.isAssignableFrom(cls)) {
                if (!cls.isArray()) {
                    return new SimpleType(cls);
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not construct SimpleType for an array (class: "), ")"));
            }
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not construct SimpleType for a Collection (class: "), ")"));
        }
        throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not construct SimpleType for a Map (class: "), ")"));
    }

    public static SimpleType constructUnsafe(Class<?> cls) {
        return new SimpleType(cls, null, null, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        return new SimpleType(cls, this._typeNames, this._typeParameters, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        JavaType[] javaTypeArr;
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        JavaType[] javaTypeArr2 = this._typeParameters;
        if (javaTypeArr2 != null && javaTypeArr2.length > 0) {
            sb.append(Typography.less);
            boolean z = true;
            for (JavaType javaType : this._typeParameters) {
                if (z) {
                    z = false;
                } else {
                    sb.append(JsonReaderKt.COMMA);
                }
                sb.append(javaType.toCanonical());
            }
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        JavaType[] javaTypeArr;
        if (i < 0 || (javaTypeArr = this._typeParameters) == null || i >= javaTypeArr.length) {
            return null;
        }
        return javaTypeArr[i];
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        JavaType[] javaTypeArr = this._typeParameters;
        if (javaTypeArr == null) {
            return 0;
        }
        return javaTypeArr.length;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        String[] strArr;
        if (i < 0 || (strArr = this._typeNames) == null || i >= strArr.length) {
            return null;
        }
        return strArr[i];
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != SimpleType.class) {
            return false;
        }
        SimpleType simpleType = (SimpleType) obj;
        if (simpleType._class != this._class) {
            return false;
        }
        JavaType[] javaTypeArr = this._typeParameters;
        JavaType[] javaTypeArr2 = simpleType._typeParameters;
        if (javaTypeArr == null) {
            return javaTypeArr2 == null || javaTypeArr2.length == 0;
        } else if (javaTypeArr2 == null || javaTypeArr.length != javaTypeArr2.length) {
            return false;
        } else {
            int length = javaTypeArr.length;
            for (int i = 0; i < length; i++) {
                if (!javaTypeArr[i].equals(javaTypeArr2[i])) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return TypeBase._classSignature(this._class, sb, true);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        TypeBase._classSignature(this._class, sb, false);
        if (this._typeParameters != null) {
            sb.append(Typography.less);
            for (JavaType javaType : this._typeParameters) {
                sb = javaType.getGenericSignature(sb);
            }
            sb.append(Typography.greater);
        }
        sb.append(';');
        return sb;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return false;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.narrowContentsBy() should never be called");
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String toString() {
        return GeneratedOutlineSupport1.outline89(GeneratedOutlineSupport1.outline105(40, "[simple type, class "), buildCanonicalName(), JsonReaderKt.END_LIST);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        throw new IllegalArgumentException("Internal error: SimpleType.widenContentsBy() should never be called");
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentTypeHandler */
    public JavaType mo4242withContentTypeHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    @Deprecated
    protected SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr) {
        this(cls, strArr, javaTypeArr, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentValueHandler */
    public SimpleType mo4248withContentValueHandler(Object obj) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withTypeHandler */
    public SimpleType mo4249withTypeHandler(Object obj) {
        return new SimpleType(this._class, this._typeNames, this._typeParameters, this._valueHandler, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withValueHandler */
    public SimpleType mo4250withValueHandler(Object obj) {
        return obj == this._valueHandler ? this : new SimpleType(this._class, this._typeNames, this._typeParameters, obj, this._typeHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimpleType(Class<?> cls, String[] strArr, JavaType[] javaTypeArr, Object obj, Object obj2) {
        super(cls, 0, obj, obj2);
        if (strArr != null && strArr.length != 0) {
            this._typeNames = strArr;
            this._typeParameters = javaTypeArr;
            return;
        }
        this._typeNames = null;
        this._typeParameters = null;
    }
}

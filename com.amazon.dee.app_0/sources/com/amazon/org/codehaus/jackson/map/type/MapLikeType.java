package com.amazon.org.codehaus.jackson.map.type;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes13.dex */
public class MapLikeType extends TypeBase {
    protected final JavaType _keyType;
    protected final JavaType _valueType;

    @Deprecated
    protected MapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        super(cls, javaType.hashCode() ^ javaType2.hashCode(), null, null);
        this._keyType = javaType;
        this._valueType = javaType2;
    }

    public static MapLikeType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return new MapLikeType(cls, javaType, javaType2, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        return new MapLikeType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._keyType != null) {
            sb.append(Typography.less);
            sb.append(this._keyType.toCanonical());
            sb.append(JsonReaderKt.COMMA);
            sb.append(this._valueType.toCanonical());
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._keyType;
        }
        if (i != 1) {
            return null;
        }
        return this._valueType;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public int containedTypeCount() {
        return 2;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String containedTypeName(int i) {
        if (i == 0) {
            return "K";
        }
        if (i != 1) {
            return null;
        }
        return ExifInterface.GPS_MEASUREMENT_INTERRUPTED;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        MapLikeType mapLikeType = (MapLikeType) obj;
        return this._class == mapLikeType._class && this._keyType.equals(mapLikeType._keyType) && this._valueType.equals(mapLikeType._valueType);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._valueType;
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return TypeBase._classSignature(this._class, sb, true);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        TypeBase._classSignature(this._class, sb, false);
        sb.append(Typography.less);
        this._keyType.getGenericSignature(sb);
        this._valueType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType getKeyType() {
        return this._keyType;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isMapLikeType() {
        return true;
    }

    public boolean isTrueMapType() {
        return Map.class.isAssignableFrom(this._class);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapLikeType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    public JavaType narrowKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapLikeType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[map-like type; class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", ");
        outline107.append(this._keyType);
        outline107.append(" -> ");
        outline107.append(this._valueType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapLikeType(this._class, this._keyType, this._valueType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    public JavaType widenKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapLikeType(this._class, this._keyType.widenBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    /* renamed from: withKeyTypeHandler */
    public MapLikeType mo4244withKeyTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType.mo4249withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    /* renamed from: withKeyValueHandler */
    public MapLikeType mo4245withKeyValueHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType.mo4250withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentTypeHandler */
    public MapLikeType mo4242withContentTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType.mo4249withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentValueHandler */
    public MapLikeType mo4248withContentValueHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType.mo4250withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withTypeHandler */
    public MapLikeType mo4249withTypeHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType, this._valueHandler, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withValueHandler */
    public MapLikeType mo4250withValueHandler(Object obj) {
        return new MapLikeType(this._class, this._keyType, this._valueType, obj, this._typeHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public MapLikeType(Class<?> cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2) {
        super(cls, javaType.hashCode() ^ javaType2.hashCode(), obj, obj2);
        this._keyType = javaType;
        this._valueType = javaType2;
    }
}

package com.amazon.org.codehaus.jackson.map.type;

import androidx.exifinterface.media.ExifInterface;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collection;
import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class CollectionLikeType extends TypeBase {
    protected final JavaType _elementType;

    @Deprecated
    protected CollectionLikeType(Class<?> cls, JavaType javaType) {
        super(cls, javaType.hashCode(), null, null);
        this._elementType = javaType;
    }

    public static CollectionLikeType construct(Class<?> cls, JavaType javaType) {
        return new CollectionLikeType(cls, javaType, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        return new CollectionLikeType(cls, this._elementType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase
    protected String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        if (this._elementType != null) {
            sb.append(Typography.less);
            sb.append(this._elementType.toCanonical());
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType containedType(int i) {
        if (i == 0) {
            return this._elementType;
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
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        CollectionLikeType collectionLikeType = (CollectionLikeType) obj;
        return this._class == collectionLikeType._class && this._elementType.equals(collectionLikeType._elementType);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType getContentType() {
        return this._elementType;
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getErasedSignature(StringBuilder sb) {
        return TypeBase._classSignature(this._class, sb, true);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.TypeBase, com.amazon.org.codehaus.jackson.type.JavaType
    public StringBuilder getGenericSignature(StringBuilder sb) {
        TypeBase._classSignature(this._class, sb, false);
        sb.append(Typography.less);
        this._elementType.getGenericSignature(sb);
        sb.append(">;");
        return sb;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isCollectionLikeType() {
        return true;
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public boolean isContainerType() {
        return true;
    }

    public boolean isTrueCollectionType() {
        return Collection.class.isAssignableFrom(this._class);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionLikeType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[collection-like type; class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", contains ");
        outline107.append(this._elementType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionLikeType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentTypeHandler */
    public CollectionLikeType mo4242withContentTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType.mo4249withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentValueHandler */
    public CollectionLikeType mo4248withContentValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType.mo4250withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withTypeHandler */
    public CollectionLikeType mo4249withTypeHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType, this._valueHandler, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withValueHandler */
    public CollectionLikeType mo4250withValueHandler(Object obj) {
        return new CollectionLikeType(this._class, this._elementType, obj, this._typeHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CollectionLikeType(Class<?> cls, JavaType javaType, Object obj, Object obj2) {
        super(cls, javaType.hashCode(), obj, obj2);
        this._elementType = javaType;
    }
}

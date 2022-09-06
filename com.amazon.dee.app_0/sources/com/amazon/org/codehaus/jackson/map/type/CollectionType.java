package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class CollectionType extends CollectionLikeType {
    private CollectionType(Class<?> cls, JavaType javaType, Object obj, Object obj2) {
        super(cls, javaType, obj, obj2);
    }

    public static CollectionType construct(Class<?> cls, JavaType javaType) {
        return new CollectionType(cls, javaType, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        return new CollectionType(cls, this._elementType, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionType(this._class, this._elementType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[collection type; class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", contains ");
        outline107.append(this._elementType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._elementType.getRawClass() ? this : new CollectionType(this._class, this._elementType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentTypeHandler  reason: collision with other method in class */
    public CollectionType mo4242withContentTypeHandler(Object obj) {
        return new CollectionType(this._class, this._elementType.mo4249withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentValueHandler  reason: collision with other method in class */
    public CollectionType mo4248withContentValueHandler(Object obj) {
        return new CollectionType(this._class, this._elementType.mo4250withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withTypeHandler  reason: collision with other method in class */
    public CollectionType mo4249withTypeHandler(Object obj) {
        return new CollectionType(this._class, this._elementType, this._valueHandler, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.CollectionLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withValueHandler  reason: collision with other method in class */
    public CollectionType mo4250withValueHandler(Object obj) {
        return new CollectionType(this._class, this._elementType, obj, this._typeHandler);
    }
}

package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public final class MapType extends MapLikeType {
    @Deprecated
    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2) {
        this(cls, javaType, javaType2, null, null);
    }

    public static MapType construct(Class<?> cls, JavaType javaType, JavaType javaType2) {
        return new MapType(cls, javaType, javaType2, null, null);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    protected JavaType _narrow(Class<?> cls) {
        return new MapType(cls, this._keyType, this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType narrowContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.narrowBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType
    public JavaType narrowKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.narrowBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("[map type; class ");
        GeneratedOutlineSupport1.outline146(this._class, outline107, ", ");
        outline107.append(this._keyType);
        outline107.append(" -> ");
        outline107.append(this._valueType);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    public JavaType widenContentsBy(Class<?> cls) {
        return cls == this._valueType.getRawClass() ? this : new MapType(this._class, this._keyType, this._valueType.widenBy(cls), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType
    public JavaType widenKey(Class<?> cls) {
        return cls == this._keyType.getRawClass() ? this : new MapType(this._class, this._keyType.widenBy(cls), this._valueType, this._valueHandler, this._typeHandler);
    }

    private MapType(Class<?> cls, JavaType javaType, JavaType javaType2, Object obj, Object obj2) {
        super(cls, javaType, javaType2, obj, obj2);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType
    /* renamed from: withKeyTypeHandler  reason: collision with other method in class */
    public MapType mo4244withKeyTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType.mo4249withTypeHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType
    /* renamed from: withKeyValueHandler  reason: collision with other method in class */
    public MapType mo4245withKeyValueHandler(Object obj) {
        return new MapType(this._class, this._keyType.mo4250withValueHandler(obj), this._valueType, this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentTypeHandler  reason: collision with other method in class */
    public MapType mo4242withContentTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.mo4249withTypeHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withContentValueHandler  reason: collision with other method in class */
    public MapType mo4248withContentValueHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType.mo4250withValueHandler(obj), this._valueHandler, this._typeHandler);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withTypeHandler  reason: collision with other method in class */
    public MapType mo4249withTypeHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType, this._valueHandler, obj);
    }

    @Override // com.amazon.org.codehaus.jackson.map.type.MapLikeType, com.amazon.org.codehaus.jackson.type.JavaType
    /* renamed from: withValueHandler  reason: collision with other method in class */
    public MapType mo4250withValueHandler(Object obj) {
        return new MapType(this._class, this._keyType, this._valueType, obj, this._typeHandler);
    }
}

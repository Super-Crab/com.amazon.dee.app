package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.type.JavaType;
@Deprecated
/* loaded from: classes13.dex */
public abstract class SerializerBase<T> extends com.amazon.org.codehaus.jackson.map.ser.std.SerializerBase<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public SerializerBase(Class<T> cls) {
        super(cls);
    }

    protected SerializerBase(JavaType javaType) {
        super(javaType);
    }

    protected SerializerBase(Class<?> cls, boolean z) {
        super(cls, z);
    }
}

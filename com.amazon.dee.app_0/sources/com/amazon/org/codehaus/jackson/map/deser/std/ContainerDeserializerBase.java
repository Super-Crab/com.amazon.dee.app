package com.amazon.org.codehaus.jackson.map.deser.std;

import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public abstract class ContainerDeserializerBase<T> extends StdDeserializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerDeserializerBase(Class<?> cls) {
        super(cls);
    }

    public abstract JsonDeserializer<Object> getContentDeserializer();

    public abstract JavaType getContentType();
}

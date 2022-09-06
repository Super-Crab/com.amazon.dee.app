package com.amazon.org.codehaus.jackson.map.ser.std;

import com.amazon.org.codehaus.jackson.map.TypeSerializer;
/* loaded from: classes13.dex */
public abstract class ContainerSerializerBase<T> extends SerializerBase<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializerBase(Class<T> cls) {
        super(cls);
    }

    public abstract ContainerSerializerBase<?> _withValueTypeSerializer(TypeSerializer typeSerializer);

    /* JADX WARN: Multi-variable type inference failed */
    public ContainerSerializerBase<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : _withValueTypeSerializer(typeSerializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializerBase(Class<?> cls, boolean z) {
        super(cls, z);
    }
}

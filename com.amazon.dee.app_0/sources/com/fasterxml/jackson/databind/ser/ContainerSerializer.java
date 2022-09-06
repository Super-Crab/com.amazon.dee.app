package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
/* loaded from: classes2.dex */
public abstract class ContainerSerializer<T> extends StdSerializer<T> {
    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializer(Class<T> cls) {
        super(cls);
    }

    /* renamed from: _withValueTypeSerializer */
    protected abstract ContainerSerializer<?> mo7209_withValueTypeSerializer(TypeSerializer typeSerializer);

    public abstract JsonSerializer<?> getContentSerializer();

    public abstract JavaType getContentType();

    @Deprecated
    protected boolean hasContentTypeAnnotation(SerializerProvider serializerProvider, BeanProperty beanProperty) {
        return false;
    }

    public abstract boolean hasSingleElement(T t);

    /* JADX WARN: Multi-variable type inference failed */
    public ContainerSerializer<?> withValueTypeSerializer(TypeSerializer typeSerializer) {
        return typeSerializer == null ? this : mo7209_withValueTypeSerializer(typeSerializer);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializer(JavaType javaType) {
        super(javaType);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializer(Class<?> cls, boolean z) {
        super(cls, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerSerializer(ContainerSerializer<?> containerSerializer) {
        super(containerSerializer._handledType, false);
    }
}

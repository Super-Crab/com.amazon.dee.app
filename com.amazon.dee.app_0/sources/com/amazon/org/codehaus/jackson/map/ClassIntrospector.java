package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.BeanDescription;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public abstract class ClassIntrospector<T extends BeanDescription> {

    /* loaded from: classes13.dex */
    public interface MixInResolver {
        Class<?> findMixInClassFor(Class<?> cls);
    }

    public abstract T forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    @Deprecated
    public T forClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver) {
        return forClassAnnotations(mapperConfig, mapperConfig.constructType(cls), mixInResolver);
    }

    public abstract T forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    public abstract T forDirectClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    @Deprecated
    public T forDirectClassAnnotations(MapperConfig<?> mapperConfig, Class<?> cls, MixInResolver mixInResolver) {
        return forDirectClassAnnotations(mapperConfig, mapperConfig.constructType(cls), mixInResolver);
    }

    public abstract T forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver);
}

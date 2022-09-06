package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
/* loaded from: classes2.dex */
public abstract class ClassIntrospector {

    /* loaded from: classes2.dex */
    public interface MixInResolver {
        /* renamed from: copy */
        MixInResolver mo7135copy();

        Class<?> findMixInClassFor(Class<?> cls);
    }

    public abstract ClassIntrospector copy();

    /* renamed from: forClassAnnotations */
    public abstract BeanDescription mo7121forClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    /* renamed from: forCreation */
    public abstract BeanDescription mo7122forCreation(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    /* renamed from: forDeserialization */
    public abstract BeanDescription mo7123forDeserialization(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    @Deprecated
    /* renamed from: forDeserializationWithBuilder */
    public abstract BeanDescription mo7124forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver);

    /* renamed from: forDeserializationWithBuilder */
    public abstract BeanDescription mo7125forDeserializationWithBuilder(DeserializationConfig deserializationConfig, JavaType javaType, MixInResolver mixInResolver, BeanDescription beanDescription);

    /* renamed from: forDirectClassAnnotations */
    public abstract BeanDescription mo7126forDirectClassAnnotations(MapperConfig<?> mapperConfig, JavaType javaType, MixInResolver mixInResolver);

    /* renamed from: forSerialization */
    public abstract BeanDescription mo7127forSerialization(SerializationConfig serializationConfig, JavaType javaType, MixInResolver mixInResolver);
}

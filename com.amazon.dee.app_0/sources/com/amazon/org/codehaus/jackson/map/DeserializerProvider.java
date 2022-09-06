package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.io.SerializedString;
import com.amazon.org.codehaus.jackson.map.deser.BeanDeserializerModifier;
import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiators;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public abstract class DeserializerProvider {
    public abstract int cachedDeserializersCount();

    public abstract SerializedString findExpectedRootName(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException;

    public abstract KeyDeserializer findKeyDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<Object> findTypedValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract JsonDeserializer<Object> findValueDeserializer(DeserializationConfig deserializationConfig, JavaType javaType, BeanProperty beanProperty) throws JsonMappingException;

    public abstract void flushCachedDeserializers();

    public abstract boolean hasValueDeserializerFor(DeserializationConfig deserializationConfig, JavaType javaType);

    public abstract JavaType mapAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) throws JsonMappingException;

    public abstract DeserializerProvider withAbstractTypeResolver(AbstractTypeResolver abstractTypeResolver);

    public abstract DeserializerProvider withAdditionalDeserializers(Deserializers deserializers);

    public abstract DeserializerProvider withAdditionalKeyDeserializers(KeyDeserializers keyDeserializers);

    public abstract DeserializerProvider withDeserializerModifier(BeanDeserializerModifier beanDeserializerModifier);

    /* renamed from: withFactory */
    public abstract DeserializerProvider mo4138withFactory(DeserializerFactory deserializerFactory);

    public abstract DeserializerProvider withValueInstantiators(ValueInstantiators valueInstantiators);
}

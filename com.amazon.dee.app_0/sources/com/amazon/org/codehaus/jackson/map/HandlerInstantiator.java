package com.amazon.org.codehaus.jackson.map;

import com.amazon.org.codehaus.jackson.map.deser.ValueInstantiator;
import com.amazon.org.codehaus.jackson.map.introspect.Annotated;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeIdResolver;
import com.amazon.org.codehaus.jackson.map.jsontype.TypeResolverBuilder;
/* loaded from: classes13.dex */
public abstract class HandlerInstantiator {
    public abstract JsonDeserializer<?> deserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<? extends JsonDeserializer<?>> cls);

    public abstract KeyDeserializer keyDeserializerInstance(DeserializationConfig deserializationConfig, Annotated annotated, Class<? extends KeyDeserializer> cls);

    public abstract JsonSerializer<?> serializerInstance(SerializationConfig serializationConfig, Annotated annotated, Class<? extends JsonSerializer<?>> cls);

    public abstract TypeIdResolver typeIdResolverInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<? extends TypeIdResolver> cls);

    public abstract TypeResolverBuilder<?> typeResolverBuilderInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<? extends TypeResolverBuilder<?>> cls);

    public ValueInstantiator valueInstantiatorInstance(MapperConfig<?> mapperConfig, Annotated annotated, Class<? extends ValueInstantiator> cls) {
        return null;
    }
}

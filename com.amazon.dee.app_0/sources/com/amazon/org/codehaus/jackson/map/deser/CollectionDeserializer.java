package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.util.Collection;
@Deprecated
/* loaded from: classes13.dex */
public class CollectionDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.CollectionDeserializer {
    @Deprecated
    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, Constructor<Collection<Object>> constructor) {
        super(javaType, jsonDeserializer, typeDeserializer, constructor);
    }

    public CollectionDeserializer(JavaType javaType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer, ValueInstantiator valueInstantiator) {
        super(javaType, jsonDeserializer, typeDeserializer, valueInstantiator);
    }

    protected CollectionDeserializer(CollectionDeserializer collectionDeserializer) {
        super(collectionDeserializer);
    }
}

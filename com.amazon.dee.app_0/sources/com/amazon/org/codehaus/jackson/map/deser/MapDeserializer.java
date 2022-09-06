package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.KeyDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Constructor;
import java.util.Map;
@Deprecated
/* loaded from: classes13.dex */
public class MapDeserializer extends com.amazon.org.codehaus.jackson.map.deser.std.MapDeserializer {
    @Deprecated
    public MapDeserializer(JavaType javaType, Constructor<Map<Object, Object>> constructor, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType, constructor, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    public MapDeserializer(JavaType javaType, ValueInstantiator valueInstantiator, KeyDeserializer keyDeserializer, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(javaType, valueInstantiator, keyDeserializer, jsonDeserializer, typeDeserializer);
    }

    protected MapDeserializer(MapDeserializer mapDeserializer) {
        super(mapDeserializer);
    }
}

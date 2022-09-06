package com.amazon.org.codehaus.jackson.map.deser;

import com.amazon.org.codehaus.jackson.map.JsonDeserializer;
import com.amazon.org.codehaus.jackson.map.TypeDeserializer;
import com.amazon.org.codehaus.jackson.map.deser.std.ObjectArrayDeserializer;
import com.amazon.org.codehaus.jackson.map.type.ArrayType;
@Deprecated
/* loaded from: classes13.dex */
public class ArrayDeserializer extends ObjectArrayDeserializer {
    @Deprecated
    public ArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> jsonDeserializer) {
        this(arrayType, jsonDeserializer, null);
    }

    public ArrayDeserializer(ArrayType arrayType, JsonDeserializer<Object> jsonDeserializer, TypeDeserializer typeDeserializer) {
        super(arrayType, jsonDeserializer, typeDeserializer);
    }
}

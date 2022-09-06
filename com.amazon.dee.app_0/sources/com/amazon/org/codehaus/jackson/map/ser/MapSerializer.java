package com.amazon.org.codehaus.jackson.map.ser;

import com.amazon.org.codehaus.jackson.map.BeanProperty;
import com.amazon.org.codehaus.jackson.map.JsonSerializer;
import com.amazon.org.codehaus.jackson.map.TypeSerializer;
import com.amazon.org.codehaus.jackson.map.annotate.JacksonStdImpl;
import com.amazon.org.codehaus.jackson.type.JavaType;
import java.util.HashSet;
@JacksonStdImpl
@Deprecated
/* loaded from: classes13.dex */
public class MapSerializer extends com.amazon.org.codehaus.jackson.map.ser.std.MapSerializer {
    protected MapSerializer() {
        this(null, null, null, false, null, null, null, null);
    }

    @Deprecated
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, boolean z, TypeSerializer typeSerializer) {
        super(hashSet, com.amazon.org.codehaus.jackson.map.ser.std.MapSerializer.UNSPECIFIED_TYPE, javaType, z, typeSerializer, null, null, null);
    }

    @Deprecated
    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, BeanProperty beanProperty) {
        super(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, null, beanProperty);
    }

    protected MapSerializer(HashSet<String> hashSet, JavaType javaType, JavaType javaType2, boolean z, TypeSerializer typeSerializer, JsonSerializer<Object> jsonSerializer, JsonSerializer<Object> jsonSerializer2, BeanProperty beanProperty) {
        super(hashSet, javaType, javaType2, z, typeSerializer, jsonSerializer, jsonSerializer2, beanProperty);
    }
}

package com.amazon.org.codehaus.jackson.map.jsontype;

import com.amazon.org.codehaus.jackson.annotate.JsonTypeInfo;
import com.amazon.org.codehaus.jackson.type.JavaType;
/* loaded from: classes13.dex */
public interface TypeIdResolver {
    JsonTypeInfo.Id getMechanism();

    String idFromValue(Object obj);

    String idFromValueAndType(Object obj, Class<?> cls);

    void init(JavaType javaType);

    JavaType typeFromId(String str);
}

package com.amazon.org.codehaus.jackson.map.type;

import com.amazon.org.codehaus.jackson.type.JavaType;
import java.lang.reflect.Type;
/* loaded from: classes13.dex */
public abstract class TypeModifier {
    public abstract JavaType modifyType(JavaType javaType, Type type, TypeBindings typeBindings, TypeFactory typeFactory);
}

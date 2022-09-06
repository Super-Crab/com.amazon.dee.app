package com.amazon.org.codehaus.jackson.map.module;

import com.amazon.org.codehaus.jackson.map.AbstractTypeResolver;
import com.amazon.org.codehaus.jackson.map.DeserializationConfig;
import com.amazon.org.codehaus.jackson.map.type.ClassKey;
import com.amazon.org.codehaus.jackson.type.JavaType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.reflect.Modifier;
import java.util.HashMap;
/* loaded from: classes13.dex */
public class SimpleAbstractTypeResolver extends AbstractTypeResolver {
    protected final HashMap<ClassKey, Class<?>> _mappings = new HashMap<>();

    public <T> SimpleAbstractTypeResolver addMapping(Class<T> cls, Class<? extends T> cls2) {
        if (cls != cls2) {
            if (cls.isAssignableFrom(cls2)) {
                if (Modifier.isAbstract(cls.getModifiers())) {
                    this._mappings.put(new ClassKey(cls), cls2);
                    return this;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Can not add mapping from class "), " since it is not abstract"));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Can not add mapping from class ");
            outline107.append(cls.getName());
            outline107.append(" to ");
            outline107.append(cls2.getName());
            outline107.append(", as latter is not a subtype of former");
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("Can not add mapping from class to itself");
    }

    @Override // com.amazon.org.codehaus.jackson.map.AbstractTypeResolver
    public JavaType findTypeMapping(DeserializationConfig deserializationConfig, JavaType javaType) {
        Class<?> cls = this._mappings.get(new ClassKey(javaType.getRawClass()));
        if (cls == null) {
            return null;
        }
        return javaType.narrowBy(cls);
    }

    @Override // com.amazon.org.codehaus.jackson.map.AbstractTypeResolver
    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        return null;
    }
}

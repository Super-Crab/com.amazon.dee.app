package com.fasterxml.jackson.databind.module;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class SimpleAbstractTypeResolver extends AbstractTypeResolver implements Serializable {
    private static final long serialVersionUID = 1;
    protected final HashMap<ClassKey, Class<?>> _mappings = new HashMap<>();

    public <T> SimpleAbstractTypeResolver addMapping(Class<T> cls, Class<? extends T> cls2) {
        if (cls != cls2) {
            if (cls.isAssignableFrom(cls2)) {
                if (Modifier.isAbstract(cls.getModifiers())) {
                    this._mappings.put(new ClassKey(cls), cls2);
                    return this;
                }
                throw new IllegalArgumentException(GeneratedOutlineSupport1.outline40(cls, GeneratedOutlineSupport1.outline107("Cannot add mapping from class "), " since it is not abstract"));
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Cannot add mapping from class ");
            outline107.append(cls.getName());
            outline107.append(" to ");
            outline107.append(cls2.getName());
            outline107.append(", as latter is not a subtype of former");
            throw new IllegalArgumentException(outline107.toString());
        }
        throw new IllegalArgumentException("Cannot add mapping from class to itself");
    }

    @Override // com.fasterxml.jackson.databind.AbstractTypeResolver
    public JavaType findTypeMapping(DeserializationConfig deserializationConfig, JavaType javaType) {
        Class<?> cls = this._mappings.get(new ClassKey(javaType.getRawClass()));
        if (cls == null) {
            return null;
        }
        return deserializationConfig.getTypeFactory().constructSpecializedType(javaType, cls);
    }

    @Override // com.fasterxml.jackson.databind.AbstractTypeResolver
    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AbstractTypeResolver
    @Deprecated
    public JavaType resolveAbstractType(DeserializationConfig deserializationConfig, JavaType javaType) {
        return null;
    }
}

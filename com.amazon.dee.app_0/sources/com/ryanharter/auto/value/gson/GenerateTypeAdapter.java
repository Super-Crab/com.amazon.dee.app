package com.ryanharter.auto.value.gson;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
@Inherited
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface GenerateTypeAdapter {
    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() { // from class: com.ryanharter.auto.value.gson.GenerateTypeAdapter.1
        private final Class<?> typeArrayClass = Array.newInstance(Type.class, 0).getClass();
        private final Map<Class<?>, Constructor<? extends TypeAdapter>> adapters = Collections.synchronizedMap(new LinkedHashMap());

        private Constructor<? extends TypeAdapter> findConstructorForClass(Class<?> cls) {
            Constructor<? extends TypeAdapter> findConstructorForClass;
            Constructor<? extends TypeAdapter> constructor = this.adapters.get(cls);
            if (constructor != null) {
                return constructor;
            }
            String name = cls.getName();
            if (name.startsWith("android.") || name.startsWith("java.") || name.startsWith("kotlin.")) {
                return null;
            }
            try {
                try {
                    ClassLoader classLoader = cls.getClassLoader();
                    Class<?> loadClass = classLoader.loadClass(name + "_GsonTypeAdapter");
                    try {
                        findConstructorForClass = loadClass.getConstructor(Gson.class);
                    } catch (NoSuchMethodException unused) {
                        findConstructorForClass = loadClass.getConstructor(Gson.class, this.typeArrayClass);
                    }
                } catch (ClassNotFoundException unused2) {
                    findConstructorForClass = findConstructorForClass(cls.getSuperclass());
                }
                this.adapters.put(cls, findConstructorForClass);
                return findConstructorForClass;
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(GeneratedOutlineSupport1.outline72("Unable to find binding constructor for ", name), e);
            }
        }

        @Override // com.google.gson.TypeAdapterFactory
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            Constructor<? extends TypeAdapter> findConstructorForClass;
            Class<? super T> rawType = typeToken.getRawType();
            if (rawType.isAnnotationPresent(GenerateTypeAdapter.class) && (findConstructorForClass = findConstructorForClass(rawType)) != null) {
                try {
                    return findConstructorForClass.getParameterTypes().length == 1 ? findConstructorForClass.newInstance(gson) : findConstructorForClass.newInstance(gson, ((ParameterizedType) typeToken.getType()).getActualTypeArguments());
                } catch (IllegalAccessException e) {
                    throw new RuntimeException("Unable to invoke " + findConstructorForClass, e);
                } catch (InstantiationException e2) {
                    throw new RuntimeException("Unable to invoke " + findConstructorForClass, e2);
                } catch (InvocationTargetException e3) {
                    Throwable cause = e3.getCause();
                    if (!(cause instanceof RuntimeException)) {
                        if (cause instanceof Error) {
                            throw ((Error) cause);
                        }
                        throw new RuntimeException(GeneratedOutlineSupport1.outline66("Could not create generated TypeAdapter instance for type ", rawType), cause);
                    }
                    throw ((RuntimeException) cause);
                }
            }
            return null;
        }
    };
}

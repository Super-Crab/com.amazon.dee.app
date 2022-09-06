package com.google.gson.typeadapters;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.PostConstruct;
/* loaded from: classes3.dex */
public class PostConstructAdapterFactory implements TypeAdapterFactory {

    /* loaded from: classes3.dex */
    static final class PostConstructAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegate;
        private final Method method;

        public PostConstructAdapter(TypeAdapter<T> typeAdapter, Method method) {
            this.delegate = typeAdapter;
            this.method = method;
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public T mo8353read(JsonReader jsonReader) throws IOException {
            T mo8353read = this.delegate.mo8353read(jsonReader);
            if (mo8353read != null) {
                try {
                    this.method.invoke(mo8353read, new Object[0]);
                } catch (IllegalAccessException unused) {
                    throw new AssertionError();
                } catch (InvocationTargetException e) {
                    if (!(e.getCause() instanceof RuntimeException)) {
                        throw new RuntimeException(e.getCause());
                    }
                    throw ((RuntimeException) e.getCause());
                }
            }
            return mo8353read;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            this.delegate.write(jsonWriter, t);
        }
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Method[] declaredMethods;
        for (Class<? super T> rawType = typeToken.getRawType(); rawType != Object.class; rawType = rawType.getSuperclass()) {
            for (Method method : rawType.getDeclaredMethods()) {
                if (method.isAnnotationPresent(PostConstruct.class)) {
                    method.setAccessible(true);
                    return new PostConstructAdapter(gson.getDelegateAdapter(this, typeToken), method);
                }
            }
        }
        return null;
    }
}

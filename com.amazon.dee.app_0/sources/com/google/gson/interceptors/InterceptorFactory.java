package com.google.gson.interceptors;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes3.dex */
public final class InterceptorFactory implements TypeAdapterFactory {

    /* loaded from: classes3.dex */
    static class InterceptorAdapter<T> extends TypeAdapter<T> {
        private final TypeAdapter<T> delegate;
        private final JsonPostDeserializer<T> postDeserializer;

        public InterceptorAdapter(TypeAdapter<T> typeAdapter, Intercept intercept) {
            try {
                this.delegate = typeAdapter;
                this.postDeserializer = intercept.postDeserialize().newInstance();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public T mo8353read(JsonReader jsonReader) throws IOException {
            T mo8353read = this.delegate.mo8353read(jsonReader);
            this.postDeserializer.postDeserialize(mo8353read);
            return mo8353read;
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, T t) throws IOException {
            this.delegate.write(jsonWriter, t);
        }
    }

    @Override // com.google.gson.TypeAdapterFactory
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Intercept intercept = (Intercept) typeToken.getRawType().getAnnotation(Intercept.class);
        if (intercept == null) {
            return null;
        }
        return new InterceptorAdapter(gson.getDelegateAdapter(this, typeToken), intercept);
    }
}

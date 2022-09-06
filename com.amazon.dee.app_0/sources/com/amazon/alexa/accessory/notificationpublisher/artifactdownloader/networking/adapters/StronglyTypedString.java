package com.amazon.alexa.accessory.notificationpublisher.artifactdownloader.networking.adapters;

import androidx.annotation.Nullable;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes.dex */
public interface StronglyTypedString {

    /* loaded from: classes.dex */
    public static abstract class StronglyTypedStringAdapter<T extends StronglyTypedString> extends TypeAdapter<T> {
        /* renamed from: instantiate */
        protected abstract T mo328instantiate(String str);

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.gson.TypeAdapter
        public /* bridge */ /* synthetic */ void write(JsonWriter jsonWriter, @Nullable Object obj) throws IOException {
            write(jsonWriter, (JsonWriter) ((StronglyTypedString) obj));
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public T mo8353read(JsonReader jsonReader) throws IOException {
            return mo328instantiate(jsonReader.nextString());
        }

        public void write(JsonWriter jsonWriter, @Nullable T t) throws IOException {
            if (t == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(t.getValue());
            }
        }
    }

    String getValue();
}

package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.client.annotations.Nullable;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Locale;
/* loaded from: classes6.dex */
public class LocaleAdapter extends TypeAdapter<Locale> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, @Nullable Locale locale) throws IOException {
        if (locale == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(locale.toLanguageTag());
        }
    }

    @Override // com.google.gson.TypeAdapter
    /* renamed from: read  reason: collision with other method in class */
    public Locale mo8353read(JsonReader jsonReader) throws IOException {
        return Locale.forLanguageTag(jsonReader.nextString());
    }
}

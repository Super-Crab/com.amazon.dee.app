package com.amazon.alexa.mobilytics.event.metadata;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes9.dex */
class AMAMetadataValuesAdapter extends TypeAdapter<AMAValues> {
    AMAMetadataValuesAdapter() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public AMAValues mo8353read(JsonReader jsonReader) throws IOException {
        return new AMAValues(jsonReader);
    }

    @Override // com.google.gson.TypeAdapter
    public void write(JsonWriter jsonWriter, AMAValues aMAValues) throws IOException {
        jsonWriter.value(aMAValues.toString());
    }
}

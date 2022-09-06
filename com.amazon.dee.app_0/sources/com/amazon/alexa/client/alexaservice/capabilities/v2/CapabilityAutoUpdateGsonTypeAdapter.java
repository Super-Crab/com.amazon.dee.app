package com.amazon.alexa.client.alexaservice.capabilities.v2;

import com.amazon.alexa.EPu;
import com.amazon.alexa.GdN;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class CapabilityAutoUpdateGsonTypeAdapter extends TypeAdapter<EPu> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, EPu ePu) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.value(((GdN) ePu).zZm);
        jsonWriter.endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public EPu mo8353read(JsonReader jsonReader) throws IOException {
        return new GdN(jsonReader.nextBoolean());
    }
}

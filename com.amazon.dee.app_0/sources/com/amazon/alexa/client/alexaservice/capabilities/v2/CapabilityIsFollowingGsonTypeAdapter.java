package com.amazon.alexa.client.alexaservice.capabilities.v2;

import com.amazon.alexa.VTA;
import com.amazon.alexa.zjD;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public final class CapabilityIsFollowingGsonTypeAdapter extends TypeAdapter<zjD> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, zjD zjd) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("bool");
        jsonWriter.value(((VTA) zjd).zZm);
        jsonWriter.endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public zjD mo8353read(JsonReader jsonReader) throws IOException {
        jsonReader.beginObject();
        jsonReader.nextName();
        boolean nextBoolean = jsonReader.nextBoolean();
        jsonReader.endObject();
        return zjD.zZm(nextBoolean);
    }
}

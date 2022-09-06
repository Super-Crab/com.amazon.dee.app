package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.client.core.messages.RawStringPayload;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public class RawStringPayloadAdapter extends TypeAdapter<RawStringPayload> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, RawStringPayload rawStringPayload) throws IOException {
        jsonWriter.jsonValue(rawStringPayload.getValue());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public RawStringPayload mo8353read(JsonReader jsonReader) throws IOException {
        throw new UnsupportedOperationException("RawStringPayloads are not created through gson parsing");
    }
}

package com.amazon.alexa.client.alexaservice.networking.adapters;

import com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload.AlexaMediaPayload;
import com.amazon.alexa.vQe;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
/* loaded from: classes6.dex */
public class AlexaMediaPayloadAdapter extends TypeAdapter<AlexaMediaPayload> {
    @Override // com.google.gson.TypeAdapter
    /* renamed from: zZm */
    public void write(JsonWriter jsonWriter, AlexaMediaPayload alexaMediaPayload) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("playerId");
        if (alexaMediaPayload.getPlayerId() == null) {
            jsonWriter.value("");
        } else {
            jsonWriter.value(alexaMediaPayload.getPlayerId().getValue());
        }
        jsonWriter.endObject();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.google.gson.TypeAdapter
    /* renamed from: read */
    public AlexaMediaPayload mo8353read(JsonReader jsonReader) throws IOException {
        String nextString;
        jsonReader.beginObject();
        vQe vqe = vQe.zZm;
        if (jsonReader.hasNext() && "playerId".equals(jsonReader.nextName()) && (nextString = jsonReader.nextString()) != null && !"null".equals(nextString)) {
            vqe = vQe.zZm(nextString);
        }
        jsonReader.endObject();
        return AlexaMediaPayload.create(vqe);
    }
}

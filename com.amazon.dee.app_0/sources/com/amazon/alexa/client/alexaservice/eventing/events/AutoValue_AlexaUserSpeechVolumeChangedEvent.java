package com.amazon.alexa.client.alexaservice.eventing.events;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.YEO;
import com.amazon.alexa.uqh;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_AlexaUserSpeechVolumeChangedEvent extends YEO {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<uqh> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Float> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "scaledVolume");
            this.zQM = gson;
            this.BIo = Util.renameFields(YEO.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, uqh uqhVar) throws IOException {
            if (uqhVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("scaledVolume"));
            TypeAdapter<Float> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Float.class);
                this.zZm = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Float.valueOf(((YEO) uqhVar).BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public uqh mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            float f = 0.0f;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("scaledVolume").equals(nextName)) {
                        TypeAdapter<Float> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Float.class);
                            this.zZm = typeAdapter;
                        }
                        f = typeAdapter.mo8353read(jsonReader).floatValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AlexaUserSpeechVolumeChangedEvent(f);
        }
    }

    public AutoValue_AlexaUserSpeechVolumeChangedEvent(float f) {
        super(f);
    }
}

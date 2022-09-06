package com.amazon.alexa.client.alexaservice.speaker.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.RhW;
import com.amazon.alexa.jrX;
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
public final class AutoValue_SetMutePayload extends jrX {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<RhW> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Boolean> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "mute");
            this.zQM = gson;
            this.BIo = Util.renameFields(jrX.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, RhW rhW) throws IOException {
            if (rhW == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("mute"));
            TypeAdapter<Boolean> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Boolean.class);
                this.zZm = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Boolean.valueOf(((jrX) rhW).zZm));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public RhW mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("mute").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Boolean.class);
                            this.zZm = typeAdapter;
                        }
                        z = typeAdapter.mo8353read(jsonReader).booleanValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SetMutePayload(z);
        }
    }

    public AutoValue_SetMutePayload(boolean z) {
        super(z);
    }
}

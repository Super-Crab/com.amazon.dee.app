package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Puy;
import com.amazon.alexa.lCm;
import com.amazon.alexa.rcB;
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
public final class AutoValue_PlaybackEventPayload extends rcB {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<lCm> {
        public volatile TypeAdapter<Long> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Puy> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "token", (Object) "offsetInMilliseconds");
            this.zyO = gson;
            this.zQM = Util.renameFields(rcB.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, lCm lcm) throws IOException {
            if (lcm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("token"));
            rcB rcb = (rcB) lcm;
            if (rcb.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Puy> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Puy.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, rcb.zZm);
            }
            jsonWriter.name(this.zQM.get("offsetInMilliseconds"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zyO.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(rcb.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public lCm mo8353read(JsonReader jsonReader) throws IOException {
            Puy puy = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("token").equals(nextName)) {
                        TypeAdapter<Puy> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Puy.class);
                            this.zZm = typeAdapter;
                        }
                        puy = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("offsetInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlaybackEventPayload(puy, j);
        }
    }

    public AutoValue_PlaybackEventPayload(Puy puy, long j) {
        super(puy, j);
    }
}

package com.amazon.alexa.client.alexaservice.externalmediaplayer.mediacontroller.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.ErD;
import com.amazon.alexa.rYY;
import com.amazon.alexa.vQe;
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
public final class AutoValue_SetSeekPositionPayload extends rYY {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ErD> {
        public volatile TypeAdapter<vQe> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Long> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "positionMilliseconds", (Object) "playerId");
            this.zyO = gson;
            this.zQM = Util.renameFields(rYY.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, ErD erD) throws IOException {
            if (erD == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("positionMilliseconds"));
            TypeAdapter<Long> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zyO.getAdapter(Long.class);
                this.zZm = typeAdapter;
            }
            rYY ryy = (rYY) erD;
            typeAdapter.write(jsonWriter, Long.valueOf(ryy.zZm));
            jsonWriter.name(this.zQM.get("playerId"));
            if (ryy.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(vQe.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ryy.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ErD mo8353read(JsonReader jsonReader) throws IOException {
            vQe vqe = null;
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
                    if (this.zQM.get("positionMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        j = typeAdapter.mo8353read(jsonReader).longValue();
                    } else if (this.zQM.get("playerId").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(vQe.class);
                            this.BIo = typeAdapter2;
                        }
                        vqe = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SetSeekPositionPayload(j, vqe);
        }
    }

    public AutoValue_SetSeekPositionPayload(long j, vQe vqe) {
        super(j, vqe);
    }
}

package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.GWl;
import com.amazon.alexa.UGX;
import com.amazon.alexa.dEA;
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
public final class AutoValue_PlayerRuntimeState extends UGX {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<dEA> {
        public volatile TypeAdapter<GWl> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<vQe> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "playerId", (Object) "inactiveAvsPlaybackSessionId");
            this.zyO = gson;
            this.zQM = Util.renameFields(UGX.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, dEA dea) throws IOException {
            if (dea == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("playerId"));
            UGX ugx = (UGX) dea;
            if (ugx.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(vQe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ugx.zZm);
            }
            jsonWriter.name(this.zQM.get("inactiveAvsPlaybackSessionId"));
            if (ugx.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<GWl> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(GWl.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ugx.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public dEA mo8353read(JsonReader jsonReader) throws IOException {
            vQe vqe = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            GWl gWl = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("playerId").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(vQe.class);
                            this.zZm = typeAdapter;
                        }
                        vqe = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("inactiveAvsPlaybackSessionId").equals(nextName)) {
                        TypeAdapter<GWl> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(GWl.class);
                            this.BIo = typeAdapter2;
                        }
                        gWl = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerRuntimeState(vqe, gWl);
        }
    }

    public AutoValue_PlayerRuntimeState(vQe vqe, GWl gWl) {
        super(vqe, gWl);
    }
}

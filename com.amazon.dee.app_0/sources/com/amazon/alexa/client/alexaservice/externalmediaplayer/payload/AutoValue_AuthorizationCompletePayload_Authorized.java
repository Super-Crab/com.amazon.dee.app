package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.HHo;
import com.amazon.alexa.Hir;
import com.amazon.alexa.JGr;
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
public final class AutoValue_AuthorizationCompletePayload_Authorized extends HHo {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<JGr.zZm> {
        public volatile TypeAdapter<Hir> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<vQe> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "playerId", (Object) "skillToken");
            this.zyO = gson;
            this.zQM = Util.renameFields(HHo.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, JGr.zZm zzm) throws IOException {
            if (zzm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("playerId"));
            HHo hHo = (HHo) zzm;
            if (hHo.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(vQe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, hHo.zZm);
            }
            jsonWriter.name(this.zQM.get("skillToken"));
            if (hHo.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Hir> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(Hir.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, hHo.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public JGr.zZm mo8353read(JsonReader jsonReader) throws IOException {
            vQe vqe = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Hir hir = null;
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
                    } else if (this.zQM.get("skillToken").equals(nextName)) {
                        TypeAdapter<Hir> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(Hir.class);
                            this.BIo = typeAdapter2;
                        }
                        hir = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AuthorizationCompletePayload_Authorized(vqe, hir);
        }
    }

    public AutoValue_AuthorizationCompletePayload_Authorized(vQe vqe, Hir hir) {
        super(vqe, hir);
    }
}

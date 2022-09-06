package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Hir;
import com.amazon.alexa.Ims;
import com.amazon.alexa.VTW;
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
public final class AutoValue_AuthorizeDiscoveredPlayersPayload_Player_Metadata extends VTW {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Ims.zZm.AbstractC0011zZm> {
        public volatile TypeAdapter<Hir> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<vQe> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "playerId", (Object) "skillToken");
            this.zyO = gson;
            this.zQM = Util.renameFields(VTW.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Ims.zZm.AbstractC0011zZm abstractC0011zZm) throws IOException {
            if (abstractC0011zZm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("playerId"));
            VTW vtw = (VTW) abstractC0011zZm;
            if (vtw.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(vQe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, vtw.zZm);
            }
            jsonWriter.name(this.zQM.get("skillToken"));
            if (vtw.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Hir> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(Hir.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, vtw.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Ims.zZm.AbstractC0011zZm mo8353read(JsonReader jsonReader) throws IOException {
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
            return new AutoValue_AuthorizeDiscoveredPlayersPayload_Player_Metadata(vqe, hir);
        }
    }

    public AutoValue_AuthorizeDiscoveredPlayersPayload_Player_Metadata(vQe vqe, Hir hir) {
        super(vqe, hir);
    }
}

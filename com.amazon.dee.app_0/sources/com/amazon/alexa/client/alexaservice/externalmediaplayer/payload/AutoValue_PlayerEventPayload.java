package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.GWl;
import com.amazon.alexa.Hir;
import com.amazon.alexa.Qds;
import com.amazon.alexa.bXd;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
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
public final class AutoValue_PlayerEventPayload extends bXd {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Qds> {
        public volatile TypeAdapter<vQe> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<Hir> zQM;
        public volatile TypeAdapter<String> zZm;
        public volatile TypeAdapter<GWl> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(JsonFields.EVENT_NAME);
            arrayList.add("playerId");
            arrayList.add("skillToken");
            arrayList.add("playbackSessionId");
            this.Qle = gson;
            this.jiA = Util.renameFields(bXd.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Qds qds) throws IOException {
            if (qds == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get(JsonFields.EVENT_NAME));
            bXd bxd = (bXd) qds;
            if (bxd.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, bxd.zZm);
            }
            jsonWriter.name(this.jiA.get("playerId"));
            if (bxd.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(vQe.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bxd.BIo);
            }
            jsonWriter.name(this.jiA.get("skillToken"));
            if (bxd.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Hir> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(Hir.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, bxd.zQM);
            }
            jsonWriter.name(this.jiA.get("playbackSessionId"));
            if (bxd.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<GWl> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(GWl.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, bxd.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Qds mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            vQe vqe = null;
            Hir hir = null;
            GWl gWl = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get(JsonFields.EVENT_NAME).equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("playerId").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(vQe.class);
                            this.BIo = typeAdapter2;
                        }
                        vqe = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("skillToken").equals(nextName)) {
                        TypeAdapter<Hir> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(Hir.class);
                            this.zQM = typeAdapter3;
                        }
                        hir = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("playbackSessionId").equals(nextName)) {
                        TypeAdapter<GWl> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(GWl.class);
                            this.zyO = typeAdapter4;
                        }
                        gWl = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerEventPayload(str, vqe, hir, gWl);
        }
    }

    public AutoValue_PlayerEventPayload(String str, vQe vqe, Hir hir, @Nullable GWl gWl) {
        super(str, vqe, hir, gWl);
    }
}

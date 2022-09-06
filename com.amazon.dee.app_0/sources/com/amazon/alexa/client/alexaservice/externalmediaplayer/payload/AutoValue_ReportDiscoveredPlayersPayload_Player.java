package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.AbstractC0188bKf;
import com.amazon.alexa.FHI;
import com.amazon.alexa.GZF;
import com.amazon.alexa.vST;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AutoValue_ReportDiscoveredPlayersPayload_Player extends GZF {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<vST.BIo> {
        public volatile TypeAdapter<AbstractC0188bKf> BIo;
        public final Gson Qle;
        public final Map<String, String> jiA;
        public volatile TypeAdapter<Set<String>> zQM;
        public volatile TypeAdapter<FHI> zZm;
        public volatile TypeAdapter<vST.BIo.zZm> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("localPlayerId");
            arrayList.add("spiVersion");
            arrayList.add("validationData");
            arrayList.add("validationMethod");
            this.Qle = gson;
            this.jiA = Util.renameFields(GZF.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, vST.BIo bIo) throws IOException {
            if (bIo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.jiA.get("localPlayerId"));
            GZF gzf = (GZF) bIo;
            if (gzf.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<FHI> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Qle.getAdapter(FHI.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, gzf.zZm);
            }
            jsonWriter.name(this.jiA.get("spiVersion"));
            if (gzf.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0188bKf> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Qle.getAdapter(AbstractC0188bKf.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, gzf.BIo);
            }
            jsonWriter.name(this.jiA.get("validationData"));
            if (gzf.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<String>> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Qle.getAdapter(TypeToken.getParameterized(Set.class, String.class));
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, gzf.zQM);
            }
            jsonWriter.name(this.jiA.get("validationMethod"));
            if (gzf.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vST.BIo.zZm> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Qle.getAdapter(vST.BIo.zZm.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, gzf.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public vST.BIo mo8353read(JsonReader jsonReader) throws IOException {
            FHI fhi = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            AbstractC0188bKf abstractC0188bKf = null;
            Set<String> set = null;
            vST.BIo.zZm zzm = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.jiA.get("localPlayerId").equals(nextName)) {
                        TypeAdapter<FHI> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Qle.getAdapter(FHI.class);
                            this.zZm = typeAdapter;
                        }
                        fhi = typeAdapter.mo8353read(jsonReader);
                    } else if (this.jiA.get("spiVersion").equals(nextName)) {
                        TypeAdapter<AbstractC0188bKf> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Qle.getAdapter(AbstractC0188bKf.class);
                            this.BIo = typeAdapter2;
                        }
                        abstractC0188bKf = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.jiA.get("validationData").equals(nextName)) {
                        TypeAdapter<Set<String>> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Qle.getAdapter(TypeToken.getParameterized(Set.class, String.class));
                            this.zQM = typeAdapter3;
                        }
                        set = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.jiA.get("validationMethod").equals(nextName)) {
                        TypeAdapter<vST.BIo.zZm> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Qle.getAdapter(vST.BIo.zZm.class);
                            this.zyO = typeAdapter4;
                        }
                        zzm = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ReportDiscoveredPlayersPayload_Player(fhi, abstractC0188bKf, set, zzm);
        }
    }

    public AutoValue_ReportDiscoveredPlayersPayload_Player(FHI fhi, AbstractC0188bKf abstractC0188bKf, Set<String> set, vST.BIo.zZm zzm) {
        super(fhi, abstractC0188bKf, set, zzm);
    }
}

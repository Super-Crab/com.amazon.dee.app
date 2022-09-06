package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import androidx.core.app.NotificationCompat;
import com.amazon.alexa.GWl;
import com.amazon.alexa.Hir;
import com.amazon.alexa.UBM;
import com.amazon.alexa.pHD;
import com.amazon.alexa.uWW;
import com.amazon.alexa.vQe;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_PlayPayload extends UBM {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<uWW> {
        public volatile TypeAdapter<Long> BIo;
        public volatile TypeAdapter<Boolean> JTe;
        public final Map<String, String> LPk;
        public volatile TypeAdapter<uWW.zZm> Qle;
        public volatile TypeAdapter<GWl> jiA;
        public final Gson yPL;
        public volatile TypeAdapter<vQe> zQM;
        public volatile TypeAdapter<pHD> zZm;
        public volatile TypeAdapter<Hir> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("playbackContextToken", "index", "offsetInMilliseconds", "playerId", "skillToken");
            outline129.add("playbackSessionId");
            outline129.add(NotificationCompat.CATEGORY_NAVIGATION);
            outline129.add(LinkHeader.Rel.PreLoad);
            this.yPL = gson;
            this.LPk = Util.renameFields(UBM.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, uWW uww) throws IOException {
            if (uww == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.LPk.get("playbackContextToken"));
            UBM ubm = (UBM) uww;
            if (ubm.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<pHD> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.yPL.getAdapter(pHD.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ubm.zZm);
            }
            jsonWriter.name(this.LPk.get("index"));
            TypeAdapter<Long> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.yPL.getAdapter(Long.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(ubm.BIo));
            jsonWriter.name(this.LPk.get("offsetInMilliseconds"));
            TypeAdapter<Long> typeAdapter3 = this.BIo;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.yPL.getAdapter(Long.class);
                this.BIo = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Long.valueOf(ubm.zQM));
            jsonWriter.name(this.LPk.get("playerId"));
            if (ubm.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter4 = this.zQM;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.yPL.getAdapter(vQe.class);
                    this.zQM = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, ubm.zyO);
            }
            jsonWriter.name(this.LPk.get("skillToken"));
            if (ubm.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Hir> typeAdapter5 = this.zyO;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.yPL.getAdapter(Hir.class);
                    this.zyO = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, ubm.jiA);
            }
            jsonWriter.name(this.LPk.get("playbackSessionId"));
            if (ubm.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<GWl> typeAdapter6 = this.jiA;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.yPL.getAdapter(GWl.class);
                    this.jiA = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, ubm.Qle);
            }
            jsonWriter.name(this.LPk.get(NotificationCompat.CATEGORY_NAVIGATION));
            if (ubm.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<uWW.zZm> typeAdapter7 = this.Qle;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.yPL.getAdapter(uWW.zZm.class);
                    this.Qle = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, ubm.JTe);
            }
            jsonWriter.name(this.LPk.get(LinkHeader.Rel.PreLoad));
            TypeAdapter<Boolean> typeAdapter8 = this.JTe;
            if (typeAdapter8 == null) {
                typeAdapter8 = this.yPL.getAdapter(Boolean.class);
                this.JTe = typeAdapter8;
            }
            typeAdapter8.write(jsonWriter, Boolean.valueOf(ubm.LPk));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public uWW mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            boolean z = false;
            pHD phd = null;
            vQe vqe = null;
            Hir hir = null;
            GWl gWl = null;
            uWW.zZm zzm = null;
            long j = 0;
            long j2 = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.LPk.get("playbackContextToken").equals(nextName)) {
                        TypeAdapter<pHD> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.yPL.getAdapter(pHD.class);
                            this.zZm = typeAdapter;
                        }
                        phd = typeAdapter.mo8353read(jsonReader);
                    } else if (this.LPk.get("index").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.yPL.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        j = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else if (this.LPk.get("offsetInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter3 = this.BIo;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.yPL.getAdapter(Long.class);
                            this.BIo = typeAdapter3;
                        }
                        j2 = typeAdapter3.mo8353read(jsonReader).longValue();
                    } else if (this.LPk.get("playerId").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter4 = this.zQM;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.yPL.getAdapter(vQe.class);
                            this.zQM = typeAdapter4;
                        }
                        vqe = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.LPk.get("skillToken").equals(nextName)) {
                        TypeAdapter<Hir> typeAdapter5 = this.zyO;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.yPL.getAdapter(Hir.class);
                            this.zyO = typeAdapter5;
                        }
                        hir = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.LPk.get("playbackSessionId").equals(nextName)) {
                        TypeAdapter<GWl> typeAdapter6 = this.jiA;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.yPL.getAdapter(GWl.class);
                            this.jiA = typeAdapter6;
                        }
                        gWl = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.LPk.get(NotificationCompat.CATEGORY_NAVIGATION).equals(nextName)) {
                        TypeAdapter<uWW.zZm> typeAdapter7 = this.Qle;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.yPL.getAdapter(uWW.zZm.class);
                            this.Qle = typeAdapter7;
                        }
                        zzm = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.LPk.get(LinkHeader.Rel.PreLoad).equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter8 = this.JTe;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.yPL.getAdapter(Boolean.class);
                            this.JTe = typeAdapter8;
                        }
                        z = typeAdapter8.mo8353read(jsonReader).booleanValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayPayload(phd, j, j2, vqe, hir, gWl, zzm, z);
        }
    }

    public AutoValue_PlayPayload(pHD phd, long j, long j2, vQe vqe, Hir hir, GWl gWl, uWW.zZm zzm, boolean z) {
        super(phd, j, j2, vqe, hir, gWl, zzm, z);
    }
}

package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.GWl;
import com.amazon.alexa.Hir;
import com.amazon.alexa.Iof;
import com.amazon.alexa.Rgi;
import com.amazon.alexa.qQM;
import com.amazon.alexa.vQe;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
public final class AutoValue_PlayerErrorPayload extends qQM {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Rgi> {
        public volatile TypeAdapter<Long> BIo;
        public volatile TypeAdapter<GWl> JTe;
        public final Map<String, String> LPk;
        public volatile TypeAdapter<Hir> Qle;
        public volatile TypeAdapter<vQe> jiA;
        public final Gson yPL;
        public volatile TypeAdapter<String> zQM;
        public volatile TypeAdapter<Iof> zZm;
        public volatile TypeAdapter<Boolean> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("errorName", "code", "description", "fatal", "playerId");
            outline129.add("skillToken");
            outline129.add("playbackSessionId");
            this.yPL = gson;
            this.LPk = Util.renameFields(qQM.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Rgi rgi) throws IOException {
            if (rgi == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.LPk.get("errorName"));
            qQM qqm = (qQM) rgi;
            if (qqm.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Iof> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.yPL.getAdapter(Iof.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, qqm.zZm);
            }
            jsonWriter.name(this.LPk.get("code"));
            if (qqm.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.yPL.getAdapter(Long.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, qqm.BIo);
            }
            jsonWriter.name(this.LPk.get("description"));
            if (qqm.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.yPL.getAdapter(String.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, qqm.zQM);
            }
            jsonWriter.name(this.LPk.get("fatal"));
            if (qqm.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Boolean> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.yPL.getAdapter(Boolean.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, qqm.zyO);
            }
            jsonWriter.name(this.LPk.get("playerId"));
            if (qqm.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.yPL.getAdapter(vQe.class);
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, qqm.jiA);
            }
            jsonWriter.name(this.LPk.get("skillToken"));
            if (qqm.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Hir> typeAdapter6 = this.Qle;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.yPL.getAdapter(Hir.class);
                    this.Qle = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, qqm.Qle);
            }
            jsonWriter.name(this.LPk.get("playbackSessionId"));
            if (qqm.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<GWl> typeAdapter7 = this.JTe;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.yPL.getAdapter(GWl.class);
                    this.JTe = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, qqm.JTe);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Rgi mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Iof iof = null;
            Long l = null;
            String str = null;
            Boolean bool = null;
            vQe vqe = null;
            Hir hir = null;
            GWl gWl = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.LPk.get("errorName").equals(nextName)) {
                        TypeAdapter<Iof> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.yPL.getAdapter(Iof.class);
                            this.zZm = typeAdapter;
                        }
                        iof = typeAdapter.mo8353read(jsonReader);
                    } else if (this.LPk.get("code").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.yPL.getAdapter(Long.class);
                            this.BIo = typeAdapter2;
                        }
                        l = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.LPk.get("description").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.yPL.getAdapter(String.class);
                            this.zQM = typeAdapter3;
                        }
                        str = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.LPk.get("fatal").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.yPL.getAdapter(Boolean.class);
                            this.zyO = typeAdapter4;
                        }
                        bool = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.LPk.get("playerId").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.yPL.getAdapter(vQe.class);
                            this.jiA = typeAdapter5;
                        }
                        vqe = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.LPk.get("skillToken").equals(nextName)) {
                        TypeAdapter<Hir> typeAdapter6 = this.Qle;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.yPL.getAdapter(Hir.class);
                            this.Qle = typeAdapter6;
                        }
                        hir = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.LPk.get("playbackSessionId").equals(nextName)) {
                        TypeAdapter<GWl> typeAdapter7 = this.JTe;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.yPL.getAdapter(GWl.class);
                            this.JTe = typeAdapter7;
                        }
                        gWl = typeAdapter7.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerErrorPayload(iof, l, str, bool, vqe, hir, gWl);
        }
    }

    public AutoValue_PlayerErrorPayload(Iof iof, Long l, String str, Boolean bool, @Nullable vQe vqe, @Nullable Hir hir, @Nullable GWl gWl) {
        super(iof, l, str, bool, vqe, hir, gWl);
    }
}

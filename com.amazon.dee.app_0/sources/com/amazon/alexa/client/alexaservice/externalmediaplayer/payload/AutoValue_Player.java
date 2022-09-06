package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.AbstractC0188bKf;
import com.amazon.alexa.GWl;
import com.amazon.alexa.Hir;
import com.amazon.alexa.HkJ;
import com.amazon.alexa.Roh;
import com.amazon.alexa.ZYY;
import com.amazon.alexa.vQe;
import com.amazon.alexa.zYH;
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
public final class AutoValue_Player extends Roh {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<HkJ> {
        public volatile TypeAdapter<String> BIo;
        public volatile TypeAdapter<Hir> JTe;
        public volatile TypeAdapter<GWl> LPk;
        public final Gson Mlj;
        public volatile TypeAdapter<ZYY> Qle;
        public volatile TypeAdapter<zYH> jiA;
        public final Map<String, String> yPL;
        public volatile TypeAdapter<Boolean> zQM;
        public volatile TypeAdapter<vQe> zZm;
        public volatile TypeAdapter<AbstractC0188bKf> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("playerId", "endpointId", "loggedIn", "username", "isGuest");
            outline129.add("launched");
            outline129.add("active");
            outline129.add("spiVersion");
            outline129.add("playerCookie");
            outline129.add("playerVersion");
            outline129.add("skillToken");
            outline129.add("playbackSessionId");
            this.Mlj = gson;
            this.yPL = Util.renameFields(Roh.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, HkJ hkJ) throws IOException {
            if (hkJ == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.yPL.get("playerId"));
            Roh roh = (Roh) hkJ;
            if (roh.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Mlj.getAdapter(vQe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, roh.zZm);
            }
            jsonWriter.name(this.yPL.get("endpointId"));
            if (roh.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Mlj.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, roh.BIo);
            }
            jsonWriter.name(this.yPL.get("loggedIn"));
            TypeAdapter<Boolean> typeAdapter3 = this.zQM;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.Mlj.getAdapter(Boolean.class);
                this.zQM = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Boolean.valueOf(roh.zQM));
            jsonWriter.name(this.yPL.get("username"));
            if (roh.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.BIo;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Mlj.getAdapter(String.class);
                    this.BIo = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, roh.zyO);
            }
            jsonWriter.name(this.yPL.get("isGuest"));
            TypeAdapter<Boolean> typeAdapter5 = this.zQM;
            if (typeAdapter5 == null) {
                typeAdapter5 = this.Mlj.getAdapter(Boolean.class);
                this.zQM = typeAdapter5;
            }
            typeAdapter5.write(jsonWriter, Boolean.valueOf(roh.jiA));
            jsonWriter.name(this.yPL.get("launched"));
            TypeAdapter<Boolean> typeAdapter6 = this.zQM;
            if (typeAdapter6 == null) {
                typeAdapter6 = this.Mlj.getAdapter(Boolean.class);
                this.zQM = typeAdapter6;
            }
            typeAdapter6.write(jsonWriter, Boolean.valueOf(roh.Qle));
            jsonWriter.name(this.yPL.get("active"));
            TypeAdapter<Boolean> typeAdapter7 = this.zQM;
            if (typeAdapter7 == null) {
                typeAdapter7 = this.Mlj.getAdapter(Boolean.class);
                this.zQM = typeAdapter7;
            }
            typeAdapter7.write(jsonWriter, Boolean.valueOf(roh.JTe));
            jsonWriter.name(this.yPL.get("spiVersion"));
            if (roh.LPk == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0188bKf> typeAdapter8 = this.zyO;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.Mlj.getAdapter(AbstractC0188bKf.class);
                    this.zyO = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, roh.LPk);
            }
            jsonWriter.name(this.yPL.get("playerCookie"));
            if (roh.yPL == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<zYH> typeAdapter9 = this.jiA;
                if (typeAdapter9 == null) {
                    typeAdapter9 = this.Mlj.getAdapter(zYH.class);
                    this.jiA = typeAdapter9;
                }
                typeAdapter9.write(jsonWriter, roh.yPL);
            }
            jsonWriter.name(this.yPL.get("playerVersion"));
            if (roh.Mlj == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ZYY> typeAdapter10 = this.Qle;
                if (typeAdapter10 == null) {
                    typeAdapter10 = this.Mlj.getAdapter(ZYY.class);
                    this.Qle = typeAdapter10;
                }
                typeAdapter10.write(jsonWriter, roh.Mlj);
            }
            jsonWriter.name(this.yPL.get("skillToken"));
            if (roh.zzR == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Hir> typeAdapter11 = this.JTe;
                if (typeAdapter11 == null) {
                    typeAdapter11 = this.Mlj.getAdapter(Hir.class);
                    this.JTe = typeAdapter11;
                }
                typeAdapter11.write(jsonWriter, roh.zzR);
            }
            jsonWriter.name(this.yPL.get("playbackSessionId"));
            if (roh.lOf == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<GWl> typeAdapter12 = this.LPk;
                if (typeAdapter12 == null) {
                    typeAdapter12 = this.Mlj.getAdapter(GWl.class);
                    this.LPk = typeAdapter12;
                }
                typeAdapter12.write(jsonWriter, roh.lOf);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public HkJ mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            vQe vqe = null;
            String str = null;
            String str2 = null;
            AbstractC0188bKf abstractC0188bKf = null;
            zYH zyh = null;
            ZYY zyy = null;
            Hir hir = null;
            GWl gWl = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.yPL.get("playerId").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Mlj.getAdapter(vQe.class);
                            this.zZm = typeAdapter;
                        }
                        vqe = typeAdapter.mo8353read(jsonReader);
                    } else if (this.yPL.get("endpointId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Mlj.getAdapter(String.class);
                            this.BIo = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.yPL.get("loggedIn").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Mlj.getAdapter(Boolean.class);
                            this.zQM = typeAdapter3;
                        }
                        z = typeAdapter3.mo8353read(jsonReader).booleanValue();
                    } else if (this.yPL.get("username").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.BIo;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Mlj.getAdapter(String.class);
                            this.BIo = typeAdapter4;
                        }
                        str2 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.yPL.get("isGuest").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter5 = this.zQM;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Mlj.getAdapter(Boolean.class);
                            this.zQM = typeAdapter5;
                        }
                        z2 = typeAdapter5.mo8353read(jsonReader).booleanValue();
                    } else if (this.yPL.get("launched").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter6 = this.zQM;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.Mlj.getAdapter(Boolean.class);
                            this.zQM = typeAdapter6;
                        }
                        z3 = typeAdapter6.mo8353read(jsonReader).booleanValue();
                    } else if (this.yPL.get("active").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter7 = this.zQM;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.Mlj.getAdapter(Boolean.class);
                            this.zQM = typeAdapter7;
                        }
                        z4 = typeAdapter7.mo8353read(jsonReader).booleanValue();
                    } else if (this.yPL.get("spiVersion").equals(nextName)) {
                        TypeAdapter<AbstractC0188bKf> typeAdapter8 = this.zyO;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.Mlj.getAdapter(AbstractC0188bKf.class);
                            this.zyO = typeAdapter8;
                        }
                        abstractC0188bKf = typeAdapter8.mo8353read(jsonReader);
                    } else if (this.yPL.get("playerCookie").equals(nextName)) {
                        TypeAdapter<zYH> typeAdapter9 = this.jiA;
                        if (typeAdapter9 == null) {
                            typeAdapter9 = this.Mlj.getAdapter(zYH.class);
                            this.jiA = typeAdapter9;
                        }
                        zyh = typeAdapter9.mo8353read(jsonReader);
                    } else if (this.yPL.get("playerVersion").equals(nextName)) {
                        TypeAdapter<ZYY> typeAdapter10 = this.Qle;
                        if (typeAdapter10 == null) {
                            typeAdapter10 = this.Mlj.getAdapter(ZYY.class);
                            this.Qle = typeAdapter10;
                        }
                        zyy = typeAdapter10.mo8353read(jsonReader);
                    } else if (this.yPL.get("skillToken").equals(nextName)) {
                        TypeAdapter<Hir> typeAdapter11 = this.JTe;
                        if (typeAdapter11 == null) {
                            typeAdapter11 = this.Mlj.getAdapter(Hir.class);
                            this.JTe = typeAdapter11;
                        }
                        hir = typeAdapter11.mo8353read(jsonReader);
                    } else if (this.yPL.get("playbackSessionId").equals(nextName)) {
                        TypeAdapter<GWl> typeAdapter12 = this.LPk;
                        if (typeAdapter12 == null) {
                            typeAdapter12 = this.Mlj.getAdapter(GWl.class);
                            this.LPk = typeAdapter12;
                        }
                        gWl = typeAdapter12.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Player(vqe, str, z, str2, z2, z3, z4, abstractC0188bKf, zyh, zyy, hir, gWl);
        }
    }

    public AutoValue_Player(vQe vqe, String str, boolean z, String str2, boolean z2, boolean z3, boolean z4, AbstractC0188bKf abstractC0188bKf, zYH zyh, ZYY zyy, Hir hir, GWl gWl) {
        super(vqe, str, z, str2, z2, z3, z4, abstractC0188bKf, zyh, zyy, hir, gWl);
    }
}

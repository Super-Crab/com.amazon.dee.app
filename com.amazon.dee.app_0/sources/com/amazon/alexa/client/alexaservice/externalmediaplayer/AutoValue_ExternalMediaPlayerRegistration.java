package com.amazon.alexa.client.alexaservice.externalmediaplayer;

import android.content.ComponentName;
import com.amazon.alexa.AbstractC0188bKf;
import com.amazon.alexa.FHI;
import com.amazon.alexa.ZYY;
import com.amazon.alexa.bve;
import com.amazon.alexa.vQe;
import com.amazon.alexa.yfS;
import com.amazon.alexa.zYH;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
public final class AutoValue_ExternalMediaPlayerRegistration extends bve {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<yfS> {
        public volatile TypeAdapter<FHI> BIo;
        public volatile TypeAdapter<yfS.zZm> JTe;
        public volatile TypeAdapter<Set<String>> LPk;
        public final Gson Mlj;
        public volatile TypeAdapter<ZYY> Qle;
        public volatile TypeAdapter<zYH> jiA;
        public final Map<String, String> yPL;
        public volatile TypeAdapter<ComponentName> zQM;
        public volatile TypeAdapter<vQe> zZm;
        public volatile TypeAdapter<AbstractC0188bKf> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("playerID", "localPlayerIdentifier", "componentName", "spiVersion", "playerCookie");
            outline129.add("playerVersion");
            outline129.add("authorizedState");
            outline129.add("validationData");
            this.Mlj = gson;
            this.yPL = Util.renameFields(bve.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, yfS yfs) throws IOException {
            if (yfs == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.yPL.get("playerID"));
            bve bveVar = (bve) yfs;
            if (bveVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Mlj.getAdapter(vQe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, bveVar.BIo);
            }
            jsonWriter.name(this.yPL.get("localPlayerIdentifier"));
            if (bveVar.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<FHI> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Mlj.getAdapter(FHI.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bveVar.zQM);
            }
            jsonWriter.name(this.yPL.get("componentName"));
            if (bveVar.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ComponentName> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Mlj.getAdapter(ComponentName.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, bveVar.zyO);
            }
            jsonWriter.name(this.yPL.get("spiVersion"));
            if (bveVar.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0188bKf> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Mlj.getAdapter(AbstractC0188bKf.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, bveVar.jiA);
            }
            jsonWriter.name(this.yPL.get("playerCookie"));
            if (bveVar.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<zYH> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.Mlj.getAdapter(zYH.class);
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, bveVar.Qle);
            }
            jsonWriter.name(this.yPL.get("playerVersion"));
            if (bveVar.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<ZYY> typeAdapter6 = this.Qle;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.Mlj.getAdapter(ZYY.class);
                    this.Qle = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, bveVar.JTe);
            }
            jsonWriter.name(this.yPL.get("authorizedState"));
            if (bveVar.LPk == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<yfS.zZm> typeAdapter7 = this.JTe;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.Mlj.getAdapter(yfS.zZm.class);
                    this.JTe = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, bveVar.LPk);
            }
            jsonWriter.name(this.yPL.get("validationData"));
            if (bveVar.yPL == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<String>> typeAdapter8 = this.LPk;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, String.class));
                    this.LPk = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, bveVar.yPL);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public yfS mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            vQe vqe = null;
            FHI fhi = null;
            ComponentName componentName = null;
            AbstractC0188bKf abstractC0188bKf = null;
            zYH zyh = null;
            ZYY zyy = null;
            yfS.zZm zzm = null;
            Set<String> set = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.yPL.get("playerID").equals(nextName)) {
                        TypeAdapter<vQe> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Mlj.getAdapter(vQe.class);
                            this.zZm = typeAdapter;
                        }
                        vqe = typeAdapter.mo8353read(jsonReader);
                    } else if (this.yPL.get("localPlayerIdentifier").equals(nextName)) {
                        TypeAdapter<FHI> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Mlj.getAdapter(FHI.class);
                            this.BIo = typeAdapter2;
                        }
                        fhi = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.yPL.get("componentName").equals(nextName)) {
                        TypeAdapter<ComponentName> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Mlj.getAdapter(ComponentName.class);
                            this.zQM = typeAdapter3;
                        }
                        componentName = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.yPL.get("spiVersion").equals(nextName)) {
                        TypeAdapter<AbstractC0188bKf> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Mlj.getAdapter(AbstractC0188bKf.class);
                            this.zyO = typeAdapter4;
                        }
                        abstractC0188bKf = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.yPL.get("playerCookie").equals(nextName)) {
                        TypeAdapter<zYH> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Mlj.getAdapter(zYH.class);
                            this.jiA = typeAdapter5;
                        }
                        zyh = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.yPL.get("playerVersion").equals(nextName)) {
                        TypeAdapter<ZYY> typeAdapter6 = this.Qle;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.Mlj.getAdapter(ZYY.class);
                            this.Qle = typeAdapter6;
                        }
                        zyy = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.yPL.get("authorizedState").equals(nextName)) {
                        TypeAdapter<yfS.zZm> typeAdapter7 = this.JTe;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.Mlj.getAdapter(yfS.zZm.class);
                            this.JTe = typeAdapter7;
                        }
                        zzm = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.yPL.get("validationData").equals(nextName)) {
                        TypeAdapter<Set<String>> typeAdapter8 = this.LPk;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, String.class));
                            this.LPk = typeAdapter8;
                        }
                        set = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExternalMediaPlayerRegistration(vqe, fhi, componentName, abstractC0188bKf, zyh, zyy, zzm, set);
        }
    }

    public AutoValue_ExternalMediaPlayerRegistration(vQe vqe, FHI fhi, ComponentName componentName, AbstractC0188bKf abstractC0188bKf, zYH zyh, ZYY zyy, yfS.zZm zzm, Set<String> set) {
        super(vqe, fhi, componentName, abstractC0188bKf, zyh, zyy, zzm, set);
    }
}

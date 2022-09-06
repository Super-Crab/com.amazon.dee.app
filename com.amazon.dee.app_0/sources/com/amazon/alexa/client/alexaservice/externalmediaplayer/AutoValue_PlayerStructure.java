package com.amazon.alexa.client.alexaservice.externalmediaplayer;

import androidx.annotation.Nullable;
import com.amazon.alexa.AKJ;
import com.amazon.alexa.AbstractC0197ddD;
import com.amazon.alexa.Kyp;
import com.amazon.alexa.MAh;
import com.amazon.alexa.NdN;
import com.amazon.alexa.PYA;
import com.amazon.alexa.XSR;
import com.amazon.alexa.rjL;
import com.amazon.alexa.vQe;
import com.amazon.clouddrive.cdasdk.cds.common.PhotoSearchCategory;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import io.ktor.http.LinkHeader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
/* loaded from: classes6.dex */
public final class AutoValue_PlayerStructure extends PYA {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Kyp> {
        public volatile TypeAdapter<NdN> BIo;
        public volatile TypeAdapter<XSR> JTe;
        public volatile TypeAdapter<MAh> LPk;
        public final Gson Mlj;
        public volatile TypeAdapter<AKJ> Qle;
        public volatile TypeAdapter<Long> jiA;
        public final Map<String, String> yPL;
        public volatile TypeAdapter<Set<rjL>> zQM;
        public volatile TypeAdapter<vQe> zZm;
        public volatile TypeAdapter<AbstractC0197ddD> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("playerId", "state", "supportedOperations", LinkHeader.Parameters.Media, "positionMilliseconds");
            outline129.add("shuffle");
            outline129.add("repeat");
            outline129.add(PhotoSearchCategory.FAVORITE);
            this.Mlj = gson;
            this.yPL = Util.renameFields(PYA.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Kyp kyp) throws IOException {
            if (kyp == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.yPL.get("playerId"));
            PYA pya = (PYA) kyp;
            if (pya.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vQe> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Mlj.getAdapter(vQe.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, pya.zZm);
            }
            jsonWriter.name(this.yPL.get("state"));
            if (pya.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<NdN> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Mlj.getAdapter(NdN.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, pya.BIo);
            }
            jsonWriter.name(this.yPL.get("supportedOperations"));
            if (pya.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<rjL>> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, rjL.class));
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, pya.zQM);
            }
            jsonWriter.name(this.yPL.get(LinkHeader.Parameters.Media));
            if (pya.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0197ddD> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.Mlj.getAdapter(AbstractC0197ddD.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, pya.zyO);
            }
            jsonWriter.name(this.yPL.get("positionMilliseconds"));
            TypeAdapter<Long> typeAdapter5 = this.jiA;
            if (typeAdapter5 == null) {
                typeAdapter5 = this.Mlj.getAdapter(Long.class);
                this.jiA = typeAdapter5;
            }
            typeAdapter5.write(jsonWriter, Long.valueOf(pya.jiA));
            jsonWriter.name(this.yPL.get("shuffle"));
            if (pya.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AKJ> typeAdapter6 = this.Qle;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.Mlj.getAdapter(AKJ.class);
                    this.Qle = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, pya.Qle);
            }
            jsonWriter.name(this.yPL.get("repeat"));
            if (pya.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<XSR> typeAdapter7 = this.JTe;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.Mlj.getAdapter(XSR.class);
                    this.JTe = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, pya.JTe);
            }
            jsonWriter.name(this.yPL.get(PhotoSearchCategory.FAVORITE));
            if (pya.LPk == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<MAh> typeAdapter8 = this.LPk;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.Mlj.getAdapter(MAh.class);
                    this.LPk = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, pya.LPk);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Kyp mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            vQe vqe = null;
            NdN ndN = null;
            Set<rjL> set = null;
            AbstractC0197ddD abstractC0197ddD = null;
            AKJ akj = null;
            XSR xsr = null;
            MAh mAh = null;
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
                    } else if (this.yPL.get("state").equals(nextName)) {
                        TypeAdapter<NdN> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Mlj.getAdapter(NdN.class);
                            this.BIo = typeAdapter2;
                        }
                        ndN = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.yPL.get("supportedOperations").equals(nextName)) {
                        TypeAdapter<Set<rjL>> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, rjL.class));
                            this.zQM = typeAdapter3;
                        }
                        set = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.yPL.get(LinkHeader.Parameters.Media).equals(nextName)) {
                        TypeAdapter<AbstractC0197ddD> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Mlj.getAdapter(AbstractC0197ddD.class);
                            this.zyO = typeAdapter4;
                        }
                        abstractC0197ddD = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.yPL.get("positionMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Mlj.getAdapter(Long.class);
                            this.jiA = typeAdapter5;
                        }
                        j = typeAdapter5.mo8353read(jsonReader).longValue();
                    } else if (this.yPL.get("shuffle").equals(nextName)) {
                        TypeAdapter<AKJ> typeAdapter6 = this.Qle;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.Mlj.getAdapter(AKJ.class);
                            this.Qle = typeAdapter6;
                        }
                        akj = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.yPL.get("repeat").equals(nextName)) {
                        TypeAdapter<XSR> typeAdapter7 = this.JTe;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.Mlj.getAdapter(XSR.class);
                            this.JTe = typeAdapter7;
                        }
                        xsr = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.yPL.get(PhotoSearchCategory.FAVORITE).equals(nextName)) {
                        TypeAdapter<MAh> typeAdapter8 = this.LPk;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.Mlj.getAdapter(MAh.class);
                            this.LPk = typeAdapter8;
                        }
                        mAh = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerStructure(vqe, ndN, set, abstractC0197ddD, j, akj, xsr, mAh);
        }
    }

    public AutoValue_PlayerStructure(vQe vqe, @Nullable NdN ndN, @Nullable Set<rjL> set, @Nullable AbstractC0197ddD abstractC0197ddD, long j, @Nullable AKJ akj, @Nullable XSR xsr, @Nullable MAh mAh) {
        super(vqe, ndN, set, abstractC0197ddD, j, akj, xsr, mAh);
    }
}

package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.AKJ;
import com.amazon.alexa.AbstractC0197ddD;
import com.amazon.alexa.GkO;
import com.amazon.alexa.Kyp;
import com.amazon.alexa.MAh;
import com.amazon.alexa.NdN;
import com.amazon.alexa.XSR;
import com.amazon.alexa.rjL;
import com.amazon.alexa.xxR;
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
public final class AutoValue_PlaybackStatePayload extends xxR {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<GkO> {
        public volatile TypeAdapter<Set<rjL>> BIo;
        public volatile TypeAdapter<MAh> JTe;
        public volatile TypeAdapter<Set<Kyp>> LPk;
        public final Gson Mlj;
        public volatile TypeAdapter<XSR> Qle;
        public volatile TypeAdapter<AKJ> jiA;
        public final Map<String, String> yPL;
        public volatile TypeAdapter<AbstractC0197ddD> zQM;
        public volatile TypeAdapter<NdN> zZm;
        public volatile TypeAdapter<Long> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("state", "supportedOperations", LinkHeader.Parameters.Media, "positionMilliseconds", "shuffle");
            outline129.add("repeat");
            outline129.add(PhotoSearchCategory.FAVORITE);
            outline129.add("players");
            this.Mlj = gson;
            this.yPL = Util.renameFields(xxR.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, GkO gkO) throws IOException {
            if (gkO == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.yPL.get("state"));
            xxR xxr = (xxR) gkO;
            if (xxr.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<NdN> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.Mlj.getAdapter(NdN.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, xxr.zZm);
            }
            jsonWriter.name(this.yPL.get("supportedOperations"));
            if (xxr.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<rjL>> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, rjL.class));
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, xxr.BIo);
            }
            jsonWriter.name(this.yPL.get(LinkHeader.Parameters.Media));
            if (xxr.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0197ddD> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.Mlj.getAdapter(AbstractC0197ddD.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, xxr.zQM);
            }
            jsonWriter.name(this.yPL.get("positionMilliseconds"));
            TypeAdapter<Long> typeAdapter4 = this.zyO;
            if (typeAdapter4 == null) {
                typeAdapter4 = this.Mlj.getAdapter(Long.class);
                this.zyO = typeAdapter4;
            }
            typeAdapter4.write(jsonWriter, Long.valueOf(xxr.zyO));
            jsonWriter.name(this.yPL.get("shuffle"));
            if (xxr.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AKJ> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.Mlj.getAdapter(AKJ.class);
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, xxr.jiA);
            }
            jsonWriter.name(this.yPL.get("repeat"));
            if (xxr.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<XSR> typeAdapter6 = this.Qle;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.Mlj.getAdapter(XSR.class);
                    this.Qle = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, xxr.Qle);
            }
            jsonWriter.name(this.yPL.get(PhotoSearchCategory.FAVORITE));
            if (xxr.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<MAh> typeAdapter7 = this.JTe;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.Mlj.getAdapter(MAh.class);
                    this.JTe = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, xxr.JTe);
            }
            jsonWriter.name(this.yPL.get("players"));
            if (xxr.LPk == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<Kyp>> typeAdapter8 = this.LPk;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, Kyp.class));
                    this.LPk = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, xxr.LPk);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public GkO mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            NdN ndN = null;
            Set<rjL> set = null;
            AbstractC0197ddD abstractC0197ddD = null;
            AKJ akj = null;
            XSR xsr = null;
            MAh mAh = null;
            Set<Kyp> set2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.yPL.get("state").equals(nextName)) {
                        TypeAdapter<NdN> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.Mlj.getAdapter(NdN.class);
                            this.zZm = typeAdapter;
                        }
                        ndN = typeAdapter.mo8353read(jsonReader);
                    } else if (this.yPL.get("supportedOperations").equals(nextName)) {
                        TypeAdapter<Set<rjL>> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, rjL.class));
                            this.BIo = typeAdapter2;
                        }
                        set = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.yPL.get(LinkHeader.Parameters.Media).equals(nextName)) {
                        TypeAdapter<AbstractC0197ddD> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.Mlj.getAdapter(AbstractC0197ddD.class);
                            this.zQM = typeAdapter3;
                        }
                        abstractC0197ddD = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.yPL.get("positionMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.Mlj.getAdapter(Long.class);
                            this.zyO = typeAdapter4;
                        }
                        j = typeAdapter4.mo8353read(jsonReader).longValue();
                    } else if (this.yPL.get("shuffle").equals(nextName)) {
                        TypeAdapter<AKJ> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.Mlj.getAdapter(AKJ.class);
                            this.jiA = typeAdapter5;
                        }
                        akj = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.yPL.get("repeat").equals(nextName)) {
                        TypeAdapter<XSR> typeAdapter6 = this.Qle;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.Mlj.getAdapter(XSR.class);
                            this.Qle = typeAdapter6;
                        }
                        xsr = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.yPL.get(PhotoSearchCategory.FAVORITE).equals(nextName)) {
                        TypeAdapter<MAh> typeAdapter7 = this.JTe;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.Mlj.getAdapter(MAh.class);
                            this.JTe = typeAdapter7;
                        }
                        mAh = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.yPL.get("players").equals(nextName)) {
                        TypeAdapter<Set<Kyp>> typeAdapter8 = this.LPk;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.Mlj.getAdapter(TypeToken.getParameterized(Set.class, Kyp.class));
                            this.LPk = typeAdapter8;
                        }
                        set2 = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlaybackStatePayload(ndN, set, abstractC0197ddD, j, akj, xsr, mAh, set2);
        }
    }

    public AutoValue_PlaybackStatePayload(NdN ndN, Set<rjL> set, AbstractC0197ddD abstractC0197ddD, long j, AKJ akj, XSR xsr, MAh mAh, @Nullable Set<Kyp> set2) {
        super(ndN, set, abstractC0197ddD, j, akj, xsr, mAh, set2);
    }
}

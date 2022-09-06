package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.AAX;
import com.amazon.alexa.EnumC0198ddd;
import com.amazon.alexa.GhS;
import com.amazon.alexa.PMk;
import com.amazon.alexa.SZm;
import com.amazon.alexa.pUc;
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
import javax.annotation.Nullable;
/* loaded from: classes6.dex */
public final class AutoValue_Target extends AAX {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<pUc> {
        public volatile TypeAdapter<PMk> BIo;
        public final Gson JTe;
        public final Map<String, String> Qle;
        public volatile TypeAdapter<GhS> jiA;
        public volatile TypeAdapter<EnumC0198ddd> zQM;
        public volatile TypeAdapter<SZm> zZm;
        public volatile TypeAdapter<String> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("catalogType", "identifier", "identifierType", "token", "name");
            outline129.add("catalogId");
            outline129.add("launchConfig");
            this.JTe = gson;
            this.Qle = Util.renameFields(AAX.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, pUc puc) throws IOException {
            if (puc == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.Qle.get("catalogType"));
            AAX aax = (AAX) puc;
            if (aax.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.JTe.getAdapter(SZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, aax.zZm);
            }
            jsonWriter.name(this.Qle.get("identifier"));
            if (aax.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<PMk> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.JTe.getAdapter(PMk.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, aax.BIo);
            }
            jsonWriter.name(this.Qle.get("identifierType"));
            if (aax.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<EnumC0198ddd> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.JTe.getAdapter(EnumC0198ddd.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, aax.zQM);
            }
            jsonWriter.name(this.Qle.get("token"));
            if (aax.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.JTe.getAdapter(String.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, aax.zyO);
            }
            jsonWriter.name(this.Qle.get("name"));
            if (aax.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.zyO;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.JTe.getAdapter(String.class);
                    this.zyO = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, aax.jiA);
            }
            jsonWriter.name(this.Qle.get("catalogId"));
            if (aax.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.zyO;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.JTe.getAdapter(String.class);
                    this.zyO = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, aax.Qle);
            }
            jsonWriter.name(this.Qle.get("launchConfig"));
            if (aax.JTe == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<GhS> typeAdapter7 = this.jiA;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.JTe.getAdapter(GhS.class);
                    this.jiA = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, aax.JTe);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public pUc mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            SZm sZm = null;
            PMk pMk = null;
            EnumC0198ddd enumC0198ddd = null;
            String str = null;
            String str2 = null;
            String str3 = null;
            GhS ghS = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.Qle.get("catalogType").equals(nextName)) {
                        TypeAdapter<SZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.JTe.getAdapter(SZm.class);
                            this.zZm = typeAdapter;
                        }
                        sZm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.Qle.get("identifier").equals(nextName)) {
                        TypeAdapter<PMk> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.JTe.getAdapter(PMk.class);
                            this.BIo = typeAdapter2;
                        }
                        pMk = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.Qle.get("identifierType").equals(nextName)) {
                        TypeAdapter<EnumC0198ddd> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.JTe.getAdapter(EnumC0198ddd.class);
                            this.zQM = typeAdapter3;
                        }
                        enumC0198ddd = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.Qle.get("token").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.JTe.getAdapter(String.class);
                            this.zyO = typeAdapter4;
                        }
                        str = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.Qle.get("name").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.zyO;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.JTe.getAdapter(String.class);
                            this.zyO = typeAdapter5;
                        }
                        str2 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.Qle.get("catalogId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter6 = this.zyO;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.JTe.getAdapter(String.class);
                            this.zyO = typeAdapter6;
                        }
                        str3 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.Qle.get("launchConfig").equals(nextName)) {
                        TypeAdapter<GhS> typeAdapter7 = this.jiA;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.JTe.getAdapter(GhS.class);
                            this.jiA = typeAdapter7;
                        }
                        ghS = typeAdapter7.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Target(sZm, pMk, enumC0198ddd, str, str2, str3, ghS);
        }
    }

    public AutoValue_Target(SZm sZm, PMk pMk, EnumC0198ddd enumC0198ddd, String str, String str2, @Nullable String str3, @Nullable GhS ghS) {
        super(sZm, pMk, enumC0198ddd, str, str2, str3, ghS);
    }
}

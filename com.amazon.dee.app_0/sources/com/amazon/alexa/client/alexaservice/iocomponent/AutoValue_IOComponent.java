package com.amazon.alexa.client.alexaservice.iocomponent;

import com.amazon.alexa.LdP;
import com.amazon.alexa.URU;
import com.amazon.alexa.cVW;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.tWv;
import com.amazon.alexa.urO;
import com.amazon.alexa.vwO;
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
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_IOComponent extends LdP {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<tWv> {
        public volatile TypeAdapter<urO> BIo;
        public final Gson JTe;
        public final Map<String, String> Qle;
        public volatile TypeAdapter<List<URU>> jiA;
        public volatile TypeAdapter<cVW> zQM;
        public volatile TypeAdapter<tWv.zZm> zZm;
        public volatile TypeAdapter<vwO> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("type", "connection", "deviceInfo", "clusterDevice", "context");
            this.JTe = gson;
            this.Qle = Util.renameFields(LdP.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, tWv twv) throws IOException {
            if (twv == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.Qle.get("type"));
            LdP ldP = (LdP) twv;
            if (ldP.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<tWv.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.JTe.getAdapter(tWv.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ldP.zZm);
            }
            jsonWriter.name(this.Qle.get("connection"));
            if (ldP.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<urO> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.JTe.getAdapter(urO.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ldP.BIo);
            }
            jsonWriter.name(this.Qle.get("deviceInfo"));
            if (ldP.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<cVW> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.JTe.getAdapter(cVW.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ldP.zQM);
            }
            jsonWriter.name(this.Qle.get("clusterDevice"));
            if (ldP.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vwO> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.JTe.getAdapter(vwO.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, ldP.zyO);
            }
            jsonWriter.name(this.Qle.get("context"));
            if (ldP.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<URU>> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, URU.class));
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, ldP.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public tWv mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            tWv.zZm zzm = null;
            urO uro = null;
            cVW cvw = null;
            vwO vwo = null;
            List<URU> list = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.Qle.get("type").equals(nextName)) {
                        TypeAdapter<tWv.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.JTe.getAdapter(tWv.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.Qle.get("connection").equals(nextName)) {
                        TypeAdapter<urO> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.JTe.getAdapter(urO.class);
                            this.BIo = typeAdapter2;
                        }
                        uro = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.Qle.get("deviceInfo").equals(nextName)) {
                        TypeAdapter<cVW> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.JTe.getAdapter(cVW.class);
                            this.zQM = typeAdapter3;
                        }
                        cvw = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.Qle.get("clusterDevice").equals(nextName)) {
                        TypeAdapter<vwO> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.JTe.getAdapter(vwO.class);
                            this.zyO = typeAdapter4;
                        }
                        vwo = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.Qle.get("context").equals(nextName)) {
                        TypeAdapter<List<URU>> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, URU.class));
                            this.jiA = typeAdapter5;
                        }
                        list = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_IOComponent(zzm, uro, cvw, vwo, list);
        }
    }

    public AutoValue_IOComponent(tWv.zZm zzm, urO uro, cVW cvw, @Nullable vwO vwo, @Nullable List<URU> list) {
        super(zzm, uro, cvw, vwo, list);
    }
}

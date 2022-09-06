package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.FPq;
import com.amazon.alexa.IJL;
import com.amazon.alexa.IUU;
import com.amazon.alexa.StC;
import com.amazon.alexa.UTs;
import com.amazon.alexa.Zxk;
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
public final class AutoValue_SingleTargetResponseEventPayload extends FPq {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<UTs> {
        public volatile TypeAdapter<String> BIo;
        public final Gson JTe;
        public final Map<String, String> Qle;
        public volatile TypeAdapter<List<IJL>> jiA;
        public volatile TypeAdapter<StC> zQM;
        public volatile TypeAdapter<IUU> zZm;
        public volatile TypeAdapter<Zxk> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("token", "type", "target", "outcome", "reasons");
            this.JTe = gson;
            this.Qle = Util.renameFields(FPq.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, UTs uTs) throws IOException {
            if (uTs == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.Qle.get("token"));
            FPq fPq = (FPq) uTs;
            if (fPq.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IUU> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.JTe.getAdapter(IUU.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, fPq.zZm);
            }
            jsonWriter.name(this.Qle.get("type"));
            if (fPq.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.JTe.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, fPq.BIo);
            }
            jsonWriter.name(this.Qle.get("target"));
            if (fPq.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<StC> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.JTe.getAdapter(StC.class);
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, fPq.zQM);
            }
            jsonWriter.name(this.Qle.get("outcome"));
            if (fPq.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Zxk> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.JTe.getAdapter(Zxk.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, fPq.zyO);
            }
            jsonWriter.name(this.Qle.get("reasons"));
            if (fPq.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<IJL>> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, IJL.class));
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, fPq.jiA);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public UTs mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            IUU iuu = null;
            String str = null;
            StC stC = null;
            Zxk zxk = null;
            List<IJL> list = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.Qle.get("token").equals(nextName)) {
                        TypeAdapter<IUU> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.JTe.getAdapter(IUU.class);
                            this.zZm = typeAdapter;
                        }
                        iuu = typeAdapter.mo8353read(jsonReader);
                    } else if (this.Qle.get("type").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.JTe.getAdapter(String.class);
                            this.BIo = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.Qle.get("target").equals(nextName)) {
                        TypeAdapter<StC> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.JTe.getAdapter(StC.class);
                            this.zQM = typeAdapter3;
                        }
                        stC = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.Qle.get("outcome").equals(nextName)) {
                        TypeAdapter<Zxk> typeAdapter4 = this.zyO;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.JTe.getAdapter(Zxk.class);
                            this.zyO = typeAdapter4;
                        }
                        zxk = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.Qle.get("reasons").equals(nextName)) {
                        TypeAdapter<List<IJL>> typeAdapter5 = this.jiA;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, IJL.class));
                            this.jiA = typeAdapter5;
                        }
                        list = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SingleTargetResponseEventPayload(iuu, str, stC, zxk, list);
        }
    }

    public AutoValue_SingleTargetResponseEventPayload(IUU iuu, String str, StC stC, Zxk zxk, List<IJL> list) {
        super(iuu, str, stC, zxk, list);
    }
}

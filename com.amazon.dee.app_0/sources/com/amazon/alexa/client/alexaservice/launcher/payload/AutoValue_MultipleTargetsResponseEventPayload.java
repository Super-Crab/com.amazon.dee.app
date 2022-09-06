package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.IHD;
import com.amazon.alexa.IJL;
import com.amazon.alexa.IUU;
import com.amazon.alexa.Jqr;
import com.amazon.alexa.StC;
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
public final class AutoValue_MultipleTargetsResponseEventPayload extends IHD {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Jqr> {
        public volatile TypeAdapter<String> BIo;
        public final Gson JTe;
        public final Map<String, String> Qle;
        public volatile TypeAdapter<List<IJL>> jiA;
        public volatile TypeAdapter<List<StC>> zQM;
        public volatile TypeAdapter<IUU> zZm;
        public volatile TypeAdapter<Zxk> zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("token", "type", "targets", "outcome", "reasons");
            outline129.add("description");
            this.JTe = gson;
            this.Qle = Util.renameFields(IHD.class, outline129, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Jqr jqr) throws IOException {
            if (jqr == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.Qle.get("token"));
            IHD ihd = (IHD) jqr;
            if (ihd.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IUU> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.JTe.getAdapter(IUU.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ihd.zZm);
            }
            jsonWriter.name(this.Qle.get("type"));
            if (ihd.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.JTe.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ihd.BIo);
            }
            jsonWriter.name(this.Qle.get("targets"));
            if (ihd.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<StC>> typeAdapter3 = this.zQM;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, StC.class));
                    this.zQM = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, ihd.zQM);
            }
            jsonWriter.name(this.Qle.get("outcome"));
            if (ihd.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Zxk> typeAdapter4 = this.zyO;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.JTe.getAdapter(Zxk.class);
                    this.zyO = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, ihd.zyO);
            }
            jsonWriter.name(this.Qle.get("reasons"));
            if (ihd.jiA == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<IJL>> typeAdapter5 = this.jiA;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, IJL.class));
                    this.jiA = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, ihd.jiA);
            }
            jsonWriter.name(this.Qle.get("description"));
            if (ihd.Qle == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.BIo;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.JTe.getAdapter(String.class);
                    this.BIo = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, ihd.Qle);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Jqr mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            IUU iuu = null;
            String str = null;
            List<StC> list = null;
            Zxk zxk = null;
            List<IJL> list2 = null;
            String str2 = null;
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
                    } else if (this.Qle.get("targets").equals(nextName)) {
                        TypeAdapter<List<StC>> typeAdapter3 = this.zQM;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.JTe.getAdapter(TypeToken.getParameterized(List.class, StC.class));
                            this.zQM = typeAdapter3;
                        }
                        list = typeAdapter3.mo8353read(jsonReader);
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
                        list2 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.Qle.get("description").equals(nextName)) {
                        TypeAdapter<String> typeAdapter6 = this.BIo;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.JTe.getAdapter(String.class);
                            this.BIo = typeAdapter6;
                        }
                        str2 = typeAdapter6.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_MultipleTargetsResponseEventPayload(iuu, str, list, zxk, list2, str2);
        }
    }

    public AutoValue_MultipleTargetsResponseEventPayload(IUU iuu, String str, List<StC> list, Zxk zxk, List<IJL> list2, String str2) {
        super(iuu, str, list, zxk, list2, str2);
    }
}

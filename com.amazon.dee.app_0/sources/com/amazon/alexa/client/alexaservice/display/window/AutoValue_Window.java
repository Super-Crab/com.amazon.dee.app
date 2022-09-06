package com.amazon.alexa.client.alexaservice.display.window;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0229sSp;
import com.amazon.alexa.qWU;
import com.amazon.alexa.uEF;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
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
public final class AutoValue_Window extends qWU {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<uEF> {
        public volatile TypeAdapter<AbstractC0229sSp> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("id");
            arrayList.add("templateId");
            arrayList.add("token");
            arrayList.add(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY);
            this.zyO = gson;
            this.zQM = Util.renameFields(qWU.class, arrayList, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, uEF uef) throws IOException {
            if (uef == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("id"));
            qWU qwu = (qWU) uef;
            if (qwu.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, qwu.zZm);
            }
            jsonWriter.name(this.zQM.get("templateId"));
            if (qwu.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, qwu.BIo);
            }
            jsonWriter.name(this.zQM.get("token"));
            if (qwu.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.zZm;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, qwu.zQM);
            }
            jsonWriter.name(this.zQM.get(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY));
            if (qwu.zyO == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0229sSp> typeAdapter4 = this.BIo;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.zyO.getAdapter(AbstractC0229sSp.class);
                    this.BIo = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, qwu.zyO);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public uEF mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            String str3 = null;
            AbstractC0229sSp abstractC0229sSp = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("id").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("templateId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zQM.get("token").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.zZm;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.zQM.get(PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY).equals(nextName)) {
                        TypeAdapter<AbstractC0229sSp> typeAdapter4 = this.BIo;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.zyO.getAdapter(AbstractC0229sSp.class);
                            this.BIo = typeAdapter4;
                        }
                        abstractC0229sSp = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Window(str, str2, str3, abstractC0229sSp);
        }
    }

    public AutoValue_Window(String str, String str2, @Nullable String str3, AbstractC0229sSp abstractC0229sSp) {
        super(str, str2, str3, abstractC0229sSp);
    }
}

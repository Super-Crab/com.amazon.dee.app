package com.amazon.alexa.client.alexaservice.comms.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.HWY;
import com.amazon.alexa.dZc;
import com.amazon.alexa.vJW;
import com.amazon.alexa.vUA;
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
public final class AutoValue_PhoneCallControllerCallInfo extends HWY {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<vUA> {
        public volatile TypeAdapter<dZc> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<vJW> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "callId", (Object) "callState");
            this.zyO = gson;
            this.zQM = Util.renameFields(HWY.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, vUA vua) throws IOException {
            if (vua == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("callId"));
            HWY hwy = (HWY) vua;
            if (hwy.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vJW> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(vJW.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, hwy.zZm);
            }
            jsonWriter.name(this.zQM.get("callState"));
            if (hwy.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<dZc> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(dZc.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, hwy.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public vUA mo8353read(JsonReader jsonReader) throws IOException {
            vJW vjw = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            dZc dzc = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("callId").equals(nextName)) {
                        TypeAdapter<vJW> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(vJW.class);
                            this.zZm = typeAdapter;
                        }
                        vjw = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("callState").equals(nextName)) {
                        TypeAdapter<dZc> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(dZc.class);
                            this.BIo = typeAdapter2;
                        }
                        dzc = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PhoneCallControllerCallInfo(vjw, dzc);
        }
    }

    public AutoValue_PhoneCallControllerCallInfo(vJW vjw, dZc dzc) {
        super(vjw, dzc);
    }
}

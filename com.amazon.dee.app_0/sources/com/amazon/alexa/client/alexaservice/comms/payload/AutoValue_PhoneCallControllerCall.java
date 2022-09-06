package com.amazon.alexa.client.alexaservice.comms.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.HbJ;
import com.amazon.alexa.csl;
import com.amazon.alexa.vJW;
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
public final class AutoValue_PhoneCallControllerCall extends csl {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<HbJ> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<vJW> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "callId");
            this.zQM = gson;
            this.BIo = Util.renameFields(csl.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, HbJ hbJ) throws IOException {
            if (hbJ == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("callId"));
            csl cslVar = (csl) hbJ;
            if (cslVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<vJW> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(vJW.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, cslVar.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public HbJ mo8353read(JsonReader jsonReader) throws IOException {
            vJW vjw = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("callId").equals(nextName)) {
                        TypeAdapter<vJW> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(vJW.class);
                            this.zZm = typeAdapter;
                        }
                        vjw = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PhoneCallControllerCall(vjw);
        }
    }

    public AutoValue_PhoneCallControllerCall(vJW vjw) {
        super(vjw);
    }
}

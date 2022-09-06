package com.amazon.alexa.client.alexaservice.system.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.LWv;
import com.amazon.alexa.TTH;
import com.amazon.alexa.axq;
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
public final class AutoValue_ExceptionEncounteredPayload_Error extends axq {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<LWv.BIo> {
        public volatile TypeAdapter<String> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<TTH.zZm> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "type", (Object) "message");
            this.zyO = gson;
            this.zQM = Util.renameFields(axq.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, LWv.BIo bIo) throws IOException {
            if (bIo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("type"));
            axq axqVar = (axq) bIo;
            if (axqVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<TTH.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(TTH.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, axqVar.zZm);
            }
            jsonWriter.name(this.zQM.get("message"));
            if (axqVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, axqVar.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public LWv.BIo mo8353read(JsonReader jsonReader) throws IOException {
            TTH.zZm zzm = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("type").equals(nextName)) {
                        TypeAdapter<TTH.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(TTH.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("message").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(String.class);
                            this.BIo = typeAdapter2;
                        }
                        str = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExceptionEncounteredPayload_Error(zzm, str);
        }
    }

    public AutoValue_ExceptionEncounteredPayload_Error(TTH.zZm zzm, String str) {
        super(zzm, str);
    }
}

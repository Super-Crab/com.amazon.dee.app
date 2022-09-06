package com.amazon.alexa.client.alexaservice.launcher.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.IUU;
import com.amazon.alexa.mLq;
import com.amazon.alexa.pUc;
import com.amazon.alexa.sew;
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
public final class AutoValue_LaunchTargetPayload extends sew {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<mLq> {
        public volatile TypeAdapter<pUc> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<IUU> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "token", (Object) "target");
            this.zyO = gson;
            this.zQM = Util.renameFields(sew.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, mLq mlq) throws IOException {
            if (mlq == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("token"));
            sew sewVar = (sew) mlq;
            if (sewVar.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<IUU> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(IUU.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, sewVar.zZm);
            }
            jsonWriter.name(this.zQM.get("target"));
            if (sewVar.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<pUc> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(pUc.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, sewVar.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public mLq mo8353read(JsonReader jsonReader) throws IOException {
            IUU iuu = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            pUc puc = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("token").equals(nextName)) {
                        TypeAdapter<IUU> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(IUU.class);
                            this.zZm = typeAdapter;
                        }
                        iuu = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("target").equals(nextName)) {
                        TypeAdapter<pUc> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(pUc.class);
                            this.BIo = typeAdapter2;
                        }
                        puc = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_LaunchTargetPayload(iuu, puc);
        }
    }

    public AutoValue_LaunchTargetPayload(IUU iuu, pUc puc) {
        super(iuu, puc);
    }
}

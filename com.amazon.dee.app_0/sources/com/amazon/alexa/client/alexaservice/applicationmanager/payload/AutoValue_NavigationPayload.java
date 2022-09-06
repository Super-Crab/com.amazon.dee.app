package com.amazon.alexa.client.alexaservice.applicationmanager.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Csx;
import com.amazon.alexa.SFx;
import com.amazon.alexa.zAH;
import com.amazon.alexa.zEh;
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
public final class AutoValue_NavigationPayload extends zEh {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<zAH> {
        public volatile TypeAdapter<SFx> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Csx> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "identifierType", (Object) "identifier");
            this.zyO = gson;
            this.zQM = Util.renameFields(zEh.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, zAH zah) throws IOException {
            if (zah == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("identifierType"));
            zEh zeh = (zEh) zah;
            if (zeh.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Csx> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Csx.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, zeh.zZm);
            }
            jsonWriter.name(this.zQM.get("identifier"));
            if (zeh.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SFx> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(SFx.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, zeh.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public zAH mo8353read(JsonReader jsonReader) throws IOException {
            Csx csx = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            SFx sFx = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("identifierType").equals(nextName)) {
                        TypeAdapter<Csx> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Csx.class);
                            this.zZm = typeAdapter;
                        }
                        csx = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("identifier").equals(nextName)) {
                        TypeAdapter<SFx> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(SFx.class);
                            this.BIo = typeAdapter2;
                        }
                        sFx = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_NavigationPayload(csx, sFx);
        }
    }

    public AutoValue_NavigationPayload(Csx csx, SFx sFx) {
        super(csx, sFx);
    }
}

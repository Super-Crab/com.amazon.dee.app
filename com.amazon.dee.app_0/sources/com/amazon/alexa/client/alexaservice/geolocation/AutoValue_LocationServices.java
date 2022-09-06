package com.amazon.alexa.client.alexaservice.geolocation;

import com.amazon.alexa.AbstractC0203eaZ;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Xdr;
import com.amazon.alexa.gZN;
import com.amazon.alexa.kNm;
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
public final class AutoValue_LocationServices extends AbstractC0203eaZ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Xdr> {
        public volatile TypeAdapter<kNm> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<gZN> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "access", (Object) "status");
            this.zyO = gson;
            this.zQM = Util.renameFields(AbstractC0203eaZ.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Xdr xdr) throws IOException {
            if (xdr == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("access"));
            AbstractC0203eaZ abstractC0203eaZ = (AbstractC0203eaZ) xdr;
            if (abstractC0203eaZ.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<gZN> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(gZN.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, abstractC0203eaZ.zZm);
            }
            jsonWriter.name(this.zQM.get("status"));
            if (abstractC0203eaZ.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<kNm> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(kNm.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, abstractC0203eaZ.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Xdr mo8353read(JsonReader jsonReader) throws IOException {
            gZN gzn = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            kNm knm = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("access").equals(nextName)) {
                        TypeAdapter<gZN> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(gZN.class);
                            this.zZm = typeAdapter;
                        }
                        gzn = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("status").equals(nextName)) {
                        TypeAdapter<kNm> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(kNm.class);
                            this.BIo = typeAdapter2;
                        }
                        knm = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_LocationServices(gzn, knm);
        }
    }

    public AutoValue_LocationServices(gZN gzn, kNm knm) {
        super(gzn, knm);
    }
}

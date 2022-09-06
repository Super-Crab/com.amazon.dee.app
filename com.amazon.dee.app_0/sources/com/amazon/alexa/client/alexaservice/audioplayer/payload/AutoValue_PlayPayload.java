package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.BIn;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.UJB;
import com.amazon.alexa.xik;
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
public final class AutoValue_PlayPayload extends UJB {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<xik> {
        public volatile TypeAdapter<BIn> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<xik.zZm> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "playBehavior", (Object) "audioItem");
            this.zyO = gson;
            this.zQM = Util.renameFields(UJB.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, xik xikVar) throws IOException {
            if (xikVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("playBehavior"));
            UJB ujb = (UJB) xikVar;
            if (ujb.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<xik.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(xik.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, ujb.zZm);
            }
            jsonWriter.name(this.zQM.get("audioItem"));
            if (ujb.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<BIn> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(BIn.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, ujb.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public xik mo8353read(JsonReader jsonReader) throws IOException {
            xik.zZm zzm = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            BIn bIn = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("playBehavior").equals(nextName)) {
                        TypeAdapter<xik.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(xik.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("audioItem").equals(nextName)) {
                        TypeAdapter<BIn> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(BIn.class);
                            this.BIo = typeAdapter2;
                        }
                        bIn = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayPayload(zzm, bIn);
        }
    }

    public AutoValue_PlayPayload(xik.zZm zzm, BIn bIn) {
        super(zzm, bIn);
    }
}

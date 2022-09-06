package com.amazon.alexa.client.alexaservice.navigation;

import androidx.annotation.Nullable;
import com.amazon.alexa.Alc;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.PGo;
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
public final class AutoValue_SetDestinationPayload extends PGo {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Alc> {
        public volatile TypeAdapter<String> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Alc.zZm> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "destination", (Object) "transportationMode");
            this.zyO = gson;
            this.zQM = Util.renameFields(PGo.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Alc alc) throws IOException {
            if (alc == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("destination"));
            PGo pGo = (PGo) alc;
            if (pGo.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Alc.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Alc.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, pGo.zZm);
            }
            jsonWriter.name(this.zQM.get("transportationMode"));
            if (pGo.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, pGo.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Alc mo8353read(JsonReader jsonReader) throws IOException {
            Alc.zZm zzm = null;
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
                    if (this.zQM.get("destination").equals(nextName)) {
                        TypeAdapter<Alc.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Alc.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("transportationMode").equals(nextName)) {
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
            return new AutoValue_SetDestinationPayload(zzm, str);
        }
    }

    public AutoValue_SetDestinationPayload(Alc.zZm zzm, @Nullable String str) {
        super(zzm, str);
    }
}

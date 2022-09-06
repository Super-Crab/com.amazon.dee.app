package com.amazon.alexa.client.alexaservice.system.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Eev;
import com.amazon.alexa.Ejh;
import com.amazon.alexa.SOo;
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
public final class AutoValue_TimeZoneChangedPayload extends Eev {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Ejh> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<SOo> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "timeZone");
            this.zQM = gson;
            this.BIo = Util.renameFields(Eev.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Ejh ejh) throws IOException {
            if (ejh == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("timeZone"));
            Eev eev = (Eev) ejh;
            if (eev.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<SOo> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(SOo.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, eev.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Ejh mo8353read(JsonReader jsonReader) throws IOException {
            SOo sOo = null;
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
                    if (this.BIo.get("timeZone").equals(nextName)) {
                        TypeAdapter<SOo> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(SOo.class);
                            this.zZm = typeAdapter;
                        }
                        sOo = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_TimeZoneChangedPayload(sOo);
        }
    }

    public AutoValue_TimeZoneChangedPayload(SOo sOo) {
        super(sOo);
    }
}

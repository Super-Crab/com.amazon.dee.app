package com.amazon.alexa.client.alexaservice.externalmediaplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.FHI;
import com.amazon.alexa.JGr;
import com.amazon.alexa.Peh;
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
public final class AutoValue_AuthorizationCompletePayload_Deauthorized extends Peh {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<JGr.zQM> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<FHI> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "localPlayerId");
            this.zQM = gson;
            this.BIo = Util.renameFields(Peh.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, JGr.zQM zqm) throws IOException {
            if (zqm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("localPlayerId"));
            Peh peh = (Peh) zqm;
            if (peh.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<FHI> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(FHI.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, peh.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public JGr.zQM mo8353read(JsonReader jsonReader) throws IOException {
            FHI fhi = null;
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
                    if (this.BIo.get("localPlayerId").equals(nextName)) {
                        TypeAdapter<FHI> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(FHI.class);
                            this.zZm = typeAdapter;
                        }
                        fhi = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AuthorizationCompletePayload_Deauthorized(fhi);
        }
    }

    public AutoValue_AuthorizationCompletePayload_Deauthorized(FHI fhi) {
        super(fhi);
    }
}

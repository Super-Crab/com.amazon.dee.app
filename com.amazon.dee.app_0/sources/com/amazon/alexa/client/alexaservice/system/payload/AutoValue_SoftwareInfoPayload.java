package com.amazon.alexa.client.alexaservice.system.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.KWD;
import com.amazon.alexa.wOp;
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
public final class AutoValue_SoftwareInfoPayload extends KWD {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<wOp> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Integer> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "firmwareVersion");
            this.zQM = gson;
            this.BIo = Util.renameFields(KWD.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, wOp wop) throws IOException {
            if (wop == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("firmwareVersion"));
            TypeAdapter<Integer> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Integer.class);
                this.zZm = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Integer.valueOf(((KWD) wop).zZm));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public wOp mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            int i = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("firmwareVersion").equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Integer.class);
                            this.zZm = typeAdapter;
                        }
                        i = typeAdapter.mo8353read(jsonReader).intValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SoftwareInfoPayload(i);
        }
    }

    public AutoValue_SoftwareInfoPayload(int i) {
        super(i);
    }
}

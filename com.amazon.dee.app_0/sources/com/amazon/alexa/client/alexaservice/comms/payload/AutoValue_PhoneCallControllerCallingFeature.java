package com.amazon.alexa.client.alexaservice.comms.payload;

import com.amazon.alexa.HYG;
import com.amazon.alexa.rXH;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_PhoneCallControllerCallingFeature extends rXH {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<HYG> {
        public volatile TypeAdapter<Boolean> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, HYG hyg) throws IOException {
            if (hyg == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("name"));
            rXH rxh = (rXH) hyg;
            if (rxh.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, rxh.zZm);
            }
            jsonWriter.name(this.zQM.get("value"));
            TypeAdapter<Boolean> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zyO.getAdapter(Boolean.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Boolean.valueOf(rxh.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public HYG mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("name").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("value").equals(nextName)) {
                        TypeAdapter<Boolean> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(Boolean.class);
                            this.BIo = typeAdapter2;
                        }
                        z = typeAdapter2.mo8353read(jsonReader).booleanValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PhoneCallControllerCallingFeature(str, z);
        }
    }

    public AutoValue_PhoneCallControllerCallingFeature(String str, boolean z) {
        super(str, z);
    }
}

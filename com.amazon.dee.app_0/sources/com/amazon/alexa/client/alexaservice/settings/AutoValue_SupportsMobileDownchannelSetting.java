package com.amazon.alexa.client.alexaservice.settings;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.oBs;
import com.amazon.alexa.yqu;
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
public final class AutoValue_SupportsMobileDownchannelSetting extends oBs {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<yqu> {
        public volatile TypeAdapter<Boolean> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "key", (Object) "value");
            this.zyO = gson;
            this.zQM = Util.renameFields(oBs.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, yqu yquVar) throws IOException {
            if (yquVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("key"));
            oBs obs = (oBs) yquVar;
            if (obs.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, obs.zZm);
            }
            jsonWriter.name(this.zQM.get("value"));
            TypeAdapter<Boolean> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zyO.getAdapter(Boolean.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Boolean.valueOf(obs.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public yqu mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.zQM.get("key").equals(nextName)) {
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
            return new AutoValue_SupportsMobileDownchannelSetting(str, z);
        }
    }

    public AutoValue_SupportsMobileDownchannelSetting(String str, boolean z) {
        super(str, z);
    }
}

package com.amazon.alexa.client.alexaservice.display.window;

import com.amazon.alexa.AbstractC0229sSp;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Fjl;
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
public final class AutoValue_WindowConfiguration extends Fjl {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AbstractC0229sSp> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<String> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "interactionMode", (Object) "sizeConfigurationId");
            this.zQM = gson;
            this.BIo = Util.renameFields(Fjl.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, AbstractC0229sSp abstractC0229sSp) throws IOException {
            if (abstractC0229sSp == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("interactionMode"));
            Fjl fjl = (Fjl) abstractC0229sSp;
            if (fjl.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, fjl.zZm);
            }
            jsonWriter.name(this.BIo.get("sizeConfigurationId"));
            if (fjl.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zQM.getAdapter(String.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, fjl.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AbstractC0229sSp mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("interactionMode").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.BIo.get("sizeConfigurationId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(String.class);
                            this.zZm = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_WindowConfiguration(str, str2);
        }
    }

    public AutoValue_WindowConfiguration(String str, String str2) {
        super(str, str2);
    }
}

package com.amazon.alexa.client.alexaservice.speechrecognizer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.AbstractC0183Xff;
import com.amazon.alexa.cLd;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.Scopes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_RecognizePayload extends cLd {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AbstractC0183Xff> {
        public volatile TypeAdapter<JsonObject> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<String> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128(Scopes.PROFILE, "format", "initiator");
            this.zyO = gson;
            this.zQM = Util.renameFields(cLd.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, AbstractC0183Xff abstractC0183Xff) throws IOException {
            if (abstractC0183Xff == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get(Scopes.PROFILE));
            cLd cld = (cLd) abstractC0183Xff;
            if (cld.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, cld.zZm);
            }
            jsonWriter.name(this.zQM.get("format"));
            if (cld.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, cld.BIo);
            }
            jsonWriter.name(this.zQM.get("initiator"));
            if (cld.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<JsonObject> typeAdapter3 = this.BIo;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.zyO.getAdapter(JsonObject.class);
                    this.BIo = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, cld.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AbstractC0183Xff mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            JsonObject jsonObject = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get(Scopes.PROFILE).equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("format").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(String.class);
                            this.zZm = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.zQM.get("initiator").equals(nextName)) {
                        TypeAdapter<JsonObject> typeAdapter3 = this.BIo;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zyO.getAdapter(JsonObject.class);
                            this.BIo = typeAdapter3;
                        }
                        jsonObject = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_RecognizePayload(str, str2, jsonObject);
        }
    }

    public AutoValue_RecognizePayload(String str, String str2, @Nullable JsonObject jsonObject) {
        super(str, str2, jsonObject);
    }
}

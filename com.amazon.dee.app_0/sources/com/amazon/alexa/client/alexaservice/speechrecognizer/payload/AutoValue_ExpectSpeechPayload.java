package com.amazon.alexa.client.alexaservice.speechrecognizer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.Bhr;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.nNv;
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
public final class AutoValue_ExpectSpeechPayload extends Bhr {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<nNv> {
        public volatile TypeAdapter<JsonObject> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Long> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "timeoutInMilliseconds", (Object) "initiator");
            this.zyO = gson;
            this.zQM = Util.renameFields(Bhr.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, nNv nnv) throws IOException {
            if (nnv == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("timeoutInMilliseconds"));
            TypeAdapter<Long> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zyO.getAdapter(Long.class);
                this.zZm = typeAdapter;
            }
            Bhr bhr = (Bhr) nnv;
            typeAdapter.write(jsonWriter, Long.valueOf(bhr.zZm));
            jsonWriter.name(this.zQM.get("initiator"));
            if (bhr.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<JsonObject> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(JsonObject.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, bhr.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public nNv mo8353read(JsonReader jsonReader) throws IOException {
            JsonObject jsonObject = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("timeoutInMilliseconds").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        j = typeAdapter.mo8353read(jsonReader).longValue();
                    } else if (this.zQM.get("initiator").equals(nextName)) {
                        TypeAdapter<JsonObject> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(JsonObject.class);
                            this.BIo = typeAdapter2;
                        }
                        jsonObject = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ExpectSpeechPayload(j, jsonObject);
        }
    }

    public AutoValue_ExpectSpeechPayload(long j, @Nullable JsonObject jsonObject) {
        super(j, jsonObject);
    }
}

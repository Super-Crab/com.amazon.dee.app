package com.amazon.alexa.client.alexaservice.speechsynthesizer.payload;

import com.amazon.alexa.C0176Pce;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.XOY;
import com.amazon.alexa.yXU;
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
public final class AutoValue_SpeechEventPayload extends XOY {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<yXU> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<C0176Pce> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "token");
            this.zQM = gson;
            this.BIo = Util.renameFields(XOY.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, yXU yxu) throws IOException {
            if (yxu == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("token"));
            XOY xoy = (XOY) yxu;
            if (xoy.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<C0176Pce> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(C0176Pce.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, xoy.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public yXU mo8353read(JsonReader jsonReader) throws IOException {
            C0176Pce c0176Pce = null;
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
                    if (this.BIo.get("token").equals(nextName)) {
                        TypeAdapter<C0176Pce> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(C0176Pce.class);
                            this.zZm = typeAdapter;
                        }
                        c0176Pce = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SpeechEventPayload(c0176Pce);
        }
    }

    public AutoValue_SpeechEventPayload(C0176Pce c0176Pce) {
        super(c0176Pce);
    }
}

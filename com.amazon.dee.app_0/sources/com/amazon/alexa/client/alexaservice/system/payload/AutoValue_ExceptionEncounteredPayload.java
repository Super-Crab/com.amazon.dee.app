package com.amazon.alexa.client.alexaservice.system.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.AgQ;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.LWv;
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
public final class AutoValue_ExceptionEncounteredPayload extends AgQ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<LWv> {
        public volatile TypeAdapter<String> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<LWv.BIo> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "error", (Object) "unparsedDirective");
            this.zyO = gson;
            this.zQM = Util.renameFields(AgQ.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, LWv lWv) throws IOException {
            if (lWv == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("error"));
            AgQ agQ = (AgQ) lWv;
            if (agQ.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<LWv.BIo> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(LWv.BIo.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, agQ.zZm);
            }
            jsonWriter.name(this.zQM.get("unparsedDirective"));
            if (agQ.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, agQ.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public LWv mo8353read(JsonReader jsonReader) throws IOException {
            LWv.BIo bIo = null;
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
                    if (this.zQM.get("error").equals(nextName)) {
                        TypeAdapter<LWv.BIo> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(LWv.BIo.class);
                            this.zZm = typeAdapter;
                        }
                        bIo = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("unparsedDirective").equals(nextName)) {
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
            return new AutoValue_ExceptionEncounteredPayload(bIo, str);
        }
    }

    public AutoValue_ExceptionEncounteredPayload(LWv.BIo bIo, @Nullable String str) {
        super(bIo, str);
    }
}

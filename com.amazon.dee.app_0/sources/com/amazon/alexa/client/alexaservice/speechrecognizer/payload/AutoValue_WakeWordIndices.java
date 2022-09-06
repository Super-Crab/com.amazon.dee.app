package com.amazon.alexa.client.alexaservice.speechrecognizer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.DWt;
import com.amazon.alexa.dpr;
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
public final class AutoValue_WakeWordIndices extends dpr {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DWt> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Long> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "startIndexInSamples", (Object) "endIndexInSamples");
            this.zQM = gson;
            this.BIo = Util.renameFields(dpr.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, DWt dWt) throws IOException {
            if (dWt == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("startIndexInSamples"));
            TypeAdapter<Long> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Long.class);
                this.zZm = typeAdapter;
            }
            dpr dprVar = (dpr) dWt;
            typeAdapter.write(jsonWriter, Long.valueOf(dprVar.zZm));
            jsonWriter.name(this.BIo.get("endIndexInSamples"));
            TypeAdapter<Long> typeAdapter2 = this.zZm;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zQM.getAdapter(Long.class);
                this.zZm = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Long.valueOf(dprVar.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public DWt mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            long j2 = 0;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("startIndexInSamples").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        j = typeAdapter.mo8353read(jsonReader).longValue();
                    } else if (this.BIo.get("endIndexInSamples").equals(nextName)) {
                        TypeAdapter<Long> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(Long.class);
                            this.zZm = typeAdapter2;
                        }
                        j2 = typeAdapter2.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_WakeWordIndices(j, j2);
        }
    }

    public AutoValue_WakeWordIndices(long j, long j2) {
        super(j, j2);
    }
}

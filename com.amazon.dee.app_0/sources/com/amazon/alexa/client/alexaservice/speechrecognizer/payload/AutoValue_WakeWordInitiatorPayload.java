package com.amazon.alexa.client.alexaservice.speechrecognizer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.DWt;
import com.amazon.alexa.client.metrics.core.AlexaMetricsConstants;
import com.amazon.alexa.fXI;
import com.amazon.alexa.kbU;
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
public final class AutoValue_WakeWordInitiatorPayload extends fXI {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<kbU> {
        public volatile TypeAdapter<String> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<DWt> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "wakeWordIndices", (Object) AlexaMetricsConstants.EventConstants.WAKE_WORD);
            this.zyO = gson;
            this.zQM = Util.renameFields(fXI.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, kbU kbu) throws IOException {
            if (kbu == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("wakeWordIndices"));
            fXI fxi = (fXI) kbu;
            if (fxi.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<DWt> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(DWt.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, fxi.zZm);
            }
            jsonWriter.name(this.zQM.get(AlexaMetricsConstants.EventConstants.WAKE_WORD));
            if (fxi.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, fxi.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public kbU mo8353read(JsonReader jsonReader) throws IOException {
            DWt dWt = null;
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
                    if (this.zQM.get("wakeWordIndices").equals(nextName)) {
                        TypeAdapter<DWt> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(DWt.class);
                            this.zZm = typeAdapter;
                        }
                        dWt = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get(AlexaMetricsConstants.EventConstants.WAKE_WORD).equals(nextName)) {
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
            return new AutoValue_WakeWordInitiatorPayload(dWt, str);
        }
    }

    public AutoValue_WakeWordInitiatorPayload(@Nullable DWt dWt, @Nullable String str) {
        super(dWt, str);
    }
}

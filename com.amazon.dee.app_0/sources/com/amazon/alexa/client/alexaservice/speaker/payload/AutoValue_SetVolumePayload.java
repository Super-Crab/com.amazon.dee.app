package com.amazon.alexa.client.alexaservice.speaker.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.gHX;
import com.amazon.alexa.tRN;
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
public final class AutoValue_SetVolumePayload extends gHX {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<tRN> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Long> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) EventBusConstants.VOLUME_KEY);
            this.zQM = gson;
            this.BIo = Util.renameFields(gHX.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, tRN trn) throws IOException {
            if (trn == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get(EventBusConstants.VOLUME_KEY));
            TypeAdapter<Long> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Long.class);
                this.zZm = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Long.valueOf(((gHX) trn).zZm));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public tRN mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.BIo.get(EventBusConstants.VOLUME_KEY).equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        j = typeAdapter.mo8353read(jsonReader).longValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SetVolumePayload(j);
        }
    }

    public AutoValue_SetVolumePayload(long j) {
        super(j);
    }
}

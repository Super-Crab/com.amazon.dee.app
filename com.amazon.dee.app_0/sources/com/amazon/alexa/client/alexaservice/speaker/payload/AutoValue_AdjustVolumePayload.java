package com.amazon.alexa.client.alexaservice.speaker.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.jEt;
import com.amazon.alexa.sci;
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
public final class AutoValue_AdjustVolumePayload extends jEt {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<sci> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Long> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) EventBusConstants.VOLUME_KEY);
            this.zQM = gson;
            this.BIo = Util.renameFields(jEt.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, sci sciVar) throws IOException {
            if (sciVar == null) {
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
            typeAdapter.write(jsonWriter, Long.valueOf(((jEt) sciVar).zZm));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public sci mo8353read(JsonReader jsonReader) throws IOException {
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
            return new AutoValue_AdjustVolumePayload(j);
        }
    }

    public AutoValue_AdjustVolumePayload(long j) {
        super(j);
    }
}

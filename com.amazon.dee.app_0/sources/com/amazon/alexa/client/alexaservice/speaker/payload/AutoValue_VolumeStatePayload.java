package com.amazon.alexa.client.alexaservice.speaker.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.WlV;
import com.amazon.alexa.audiopersonalization.constants.EventBusConstants;
import com.amazon.alexa.vRe;
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
public final class AutoValue_VolumeStatePayload extends WlV {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<vRe> {
        public volatile TypeAdapter<Boolean> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Long> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) EventBusConstants.VOLUME_KEY, (Object) "muted");
            this.zyO = gson;
            this.zQM = Util.renameFields(WlV.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, vRe vre) throws IOException {
            if (vre == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get(EventBusConstants.VOLUME_KEY));
            TypeAdapter<Long> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zyO.getAdapter(Long.class);
                this.zZm = typeAdapter;
            }
            WlV wlV = (WlV) vre;
            typeAdapter.write(jsonWriter, Long.valueOf(wlV.zZm));
            jsonWriter.name(this.zQM.get("muted"));
            TypeAdapter<Boolean> typeAdapter2 = this.BIo;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zyO.getAdapter(Boolean.class);
                this.BIo = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Boolean.valueOf(wlV.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public vRe mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            long j = 0;
            boolean z = false;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get(EventBusConstants.VOLUME_KEY).equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        j = typeAdapter.mo8353read(jsonReader).longValue();
                    } else if (this.zQM.get("muted").equals(nextName)) {
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
            return new AutoValue_VolumeStatePayload(j, z);
        }
    }

    public AutoValue_VolumeStatePayload(long j, boolean z) {
        super(j, z);
    }
}

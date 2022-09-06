package com.amazon.alexa.client.alexaservice.speechrecognizer.payload;

import com.amazon.alexa.AbstractC0209ibG;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.VLZ;
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
public final class AutoValue_WakeWordInitiator extends VLZ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AbstractC0209ibG> {
        public volatile TypeAdapter<kbU> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<AbstractC0209ibG.zZm> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "type", (Object) "payload");
            this.zyO = gson;
            this.zQM = Util.renameFields(VLZ.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, AbstractC0209ibG abstractC0209ibG) throws IOException {
            if (abstractC0209ibG == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("type"));
            VLZ vlz = (VLZ) abstractC0209ibG;
            if (vlz.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AbstractC0209ibG.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(AbstractC0209ibG.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, vlz.zZm);
            }
            jsonWriter.name(this.zQM.get("payload"));
            if (vlz.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<kbU> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(kbU.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, vlz.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AbstractC0209ibG mo8353read(JsonReader jsonReader) throws IOException {
            AbstractC0209ibG.zZm zzm = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            kbU kbu = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get("type").equals(nextName)) {
                        TypeAdapter<AbstractC0209ibG.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(AbstractC0209ibG.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("payload").equals(nextName)) {
                        TypeAdapter<kbU> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(kbU.class);
                            this.BIo = typeAdapter2;
                        }
                        kbu = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_WakeWordInitiator(zzm, kbu);
        }
    }

    public AutoValue_WakeWordInitiator(AbstractC0209ibG.zZm zzm, kbU kbu) {
        super(zzm, kbu);
    }
}

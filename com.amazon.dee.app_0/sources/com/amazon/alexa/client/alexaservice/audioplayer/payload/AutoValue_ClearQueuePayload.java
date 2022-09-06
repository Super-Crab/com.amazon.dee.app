package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.UVo;
import com.amazon.alexa.kXG;
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
public final class AutoValue_ClearQueuePayload extends kXG {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<UVo> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<UVo.zZm> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "clearBehavior");
            this.zQM = gson;
            this.BIo = Util.renameFields(kXG.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, UVo uVo) throws IOException {
            if (uVo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("clearBehavior"));
            kXG kxg = (kXG) uVo;
            if (kxg.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<UVo.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(UVo.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, kxg.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public UVo mo8353read(JsonReader jsonReader) throws IOException {
            UVo.zZm zzm = null;
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
                    if (this.BIo.get("clearBehavior").equals(nextName)) {
                        TypeAdapter<UVo.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(UVo.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ClearQueuePayload(zzm);
        }
    }

    public AutoValue_ClearQueuePayload(UVo.zZm zzm) {
        super(zzm);
    }
}

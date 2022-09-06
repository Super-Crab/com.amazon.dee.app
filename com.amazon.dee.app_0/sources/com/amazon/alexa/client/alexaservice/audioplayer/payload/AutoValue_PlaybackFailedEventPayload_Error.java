package com.amazon.alexa.client.alexaservice.audioplayer.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.aZz;
import com.amazon.alexa.hFk;
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
public final class AutoValue_PlaybackFailedEventPayload_Error extends aZz {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<hFk.BIo> {
        public volatile TypeAdapter<String> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<hFk.zQM> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "type", (Object) "message");
            this.zyO = gson;
            this.zQM = Util.renameFields(aZz.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, hFk.BIo bIo) throws IOException {
            if (bIo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get("type"));
            aZz azz = (aZz) bIo;
            if (azz.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<hFk.zQM> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(hFk.zQM.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, azz.zZm);
            }
            jsonWriter.name(this.zQM.get("message"));
            if (azz.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(String.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, azz.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public hFk.BIo mo8353read(JsonReader jsonReader) throws IOException {
            hFk.zQM zqm = null;
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
                    if (this.zQM.get("type").equals(nextName)) {
                        TypeAdapter<hFk.zQM> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(hFk.zQM.class);
                            this.zZm = typeAdapter;
                        }
                        zqm = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get("message").equals(nextName)) {
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
            return new AutoValue_PlaybackFailedEventPayload_Error(zqm, str);
        }
    }

    public AutoValue_PlaybackFailedEventPayload_Error(hFk.zQM zqm, String str) {
        super(zqm, str);
    }
}

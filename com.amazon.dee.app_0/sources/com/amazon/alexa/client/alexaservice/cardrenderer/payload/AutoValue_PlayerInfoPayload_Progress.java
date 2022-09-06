package com.amazon.alexa.client.alexaservice.cardrenderer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
import com.amazon.alexa.zfK;
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
public final class AutoValue_PlayerInfoPayload_Progress extends zfK {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<qgo.BIo> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Long> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_LENGTH);
            this.zQM = gson;
            this.BIo = Util.renameFields(zfK.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, qgo.BIo bIo) throws IOException {
            if (bIo == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_LENGTH));
            zfK zfk = (zfK) bIo;
            if (zfk.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Long> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(Long.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, zfk.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public qgo.BIo mo8353read(JsonReader jsonReader) throws IOException {
            Long l = null;
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
                    if (this.BIo.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_MEDIA_LENGTH).equals(nextName)) {
                        TypeAdapter<Long> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Long.class);
                            this.zZm = typeAdapter;
                        }
                        l = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerInfoPayload_Progress(l);
        }
    }

    public AutoValue_PlayerInfoPayload_Progress(@Nullable Long l) {
        super(l);
    }
}

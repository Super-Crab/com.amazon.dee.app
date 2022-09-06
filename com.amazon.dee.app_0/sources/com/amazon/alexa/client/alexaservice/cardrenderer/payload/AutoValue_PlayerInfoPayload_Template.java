package com.amazon.alexa.client.alexaservice.cardrenderer.payload;

import android.net.Uri;
import androidx.annotation.Nullable;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.lsL;
import com.amazon.alexa.qgo;
import com.amazon.alexa.srS;
import com.amazon.alexa.voice.nowplaying.bridge.VoiceBridgePayloadUtil;
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
public final class AutoValue_PlayerInfoPayload_Template extends srS {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<qgo.zQM> {
        public volatile TypeAdapter<lsL> BIo;
        public final Map<String, String> zQM;
        public volatile TypeAdapter<Uri> zZm;
        public final Gson zyO;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_BACKGROUND_IMAGE_URL, (Object) EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_ART);
            this.zyO = gson;
            this.zQM = Util.renameFields(srS.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, qgo.zQM zqm) throws IOException {
            if (zqm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.zQM.get(VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_BACKGROUND_IMAGE_URL));
            srS srs = (srS) zqm;
            if (srs.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Uri> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zyO.getAdapter(Uri.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, srs.zZm);
            }
            jsonWriter.name(this.zQM.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_ART));
            if (srs.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<lsL> typeAdapter2 = this.BIo;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zyO.getAdapter(lsL.class);
                    this.BIo = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, srs.BIo);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public qgo.zQM mo8353read(JsonReader jsonReader) throws IOException {
            Uri uri = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            lsL lsl = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.zQM.get(VoiceBridgePayloadUtil.PayloadKey.PLAYER_INFO_BACKGROUND_IMAGE_URL).equals(nextName)) {
                        TypeAdapter<Uri> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zyO.getAdapter(Uri.class);
                            this.zZm = typeAdapter;
                        }
                        uri = typeAdapter.mo8353read(jsonReader);
                    } else if (this.zQM.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_ART).equals(nextName)) {
                        TypeAdapter<lsL> typeAdapter2 = this.BIo;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zyO.getAdapter(lsL.class);
                            this.BIo = typeAdapter2;
                        }
                        lsl = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerInfoPayload_Template(uri, lsl);
        }
    }

    public AutoValue_PlayerInfoPayload_Template(@Nullable Uri uri, @Nullable lsL lsl) {
        super(uri, lsl);
    }
}

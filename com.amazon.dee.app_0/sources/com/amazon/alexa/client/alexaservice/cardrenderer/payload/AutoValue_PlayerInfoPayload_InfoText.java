package com.amazon.alexa.client.alexaservice.cardrenderer.payload;

import androidx.annotation.Nullable;
import com.amazon.alexa.Knu;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.qgo;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
public final class AutoValue_PlayerInfoPayload_InfoText extends Knu {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<qgo.zZm> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<String> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("title", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_SUB_TEXT_1, "subText2");
            this.zQM = gson;
            this.BIo = Util.renameFields(Knu.class, outline128, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, qgo.zZm zzm) throws IOException {
            if (zzm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("title"));
            Knu knu = (Knu) zzm;
            if (knu.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(String.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, knu.zZm);
            }
            jsonWriter.name(this.BIo.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_SUB_TEXT_1));
            if (knu.BIo == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.zZm;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.zQM.getAdapter(String.class);
                    this.zZm = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, knu.BIo);
            }
            jsonWriter.name(this.BIo.get("subText2"));
            if (knu.zQM == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.zZm;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.zQM.getAdapter(String.class);
                    this.zZm = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, knu.zQM);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public qgo.zZm mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            String str3 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("title").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(String.class);
                            this.zZm = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.BIo.get(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_SUB_TEXT_1).equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(String.class);
                            this.zZm = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.BIo.get("subText2").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.zZm;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.zQM.getAdapter(String.class);
                            this.zZm = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PlayerInfoPayload_InfoText(str, str2, str3);
        }
    }

    public AutoValue_PlayerInfoPayload_InfoText(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        super(str, str2, str3);
    }
}

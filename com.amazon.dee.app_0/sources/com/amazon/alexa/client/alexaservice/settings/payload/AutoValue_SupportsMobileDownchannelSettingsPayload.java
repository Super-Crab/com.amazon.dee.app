package com.amazon.alexa.client.alexaservice.settings.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.MnM;
import com.amazon.alexa.tPB;
import com.amazon.alexa.yqu;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.ryanharter.auto.value.gson.internal.Util;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes6.dex */
public final class AutoValue_SupportsMobileDownchannelSettingsPayload extends MnM {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<tPB> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<List<yqu>> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "settings");
            this.zQM = gson;
            this.BIo = Util.renameFields(MnM.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, tPB tpb) throws IOException {
            if (tpb == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("settings"));
            MnM mnM = (MnM) tpb;
            if (mnM.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<yqu>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, yqu.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, mnM.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public tPB mo8353read(JsonReader jsonReader) throws IOException {
            List<yqu> list = null;
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
                    if (this.BIo.get("settings").equals(nextName)) {
                        TypeAdapter<List<yqu>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, yqu.class));
                            this.zZm = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SupportsMobileDownchannelSettingsPayload(list);
        }
    }

    public AutoValue_SupportsMobileDownchannelSettingsPayload(List<yqu> list) {
        super(list);
    }
}

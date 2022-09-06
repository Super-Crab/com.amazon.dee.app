package com.amazon.alexa.client.alexaservice.comms.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.DBg;
import com.amazon.alexa.HYG;
import com.amazon.alexa.rAH;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
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
public final class AutoValue_PhoneCallControllerConfiguration extends DBg {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<rAH> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<List<HYG>> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_KEY);
            this.zQM = gson;
            this.BIo = Util.renameFields(DBg.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, rAH rah) throws IOException {
            if (rah == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_KEY));
            DBg dBg = (DBg) rah;
            if (dBg.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<HYG>> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, HYG.class));
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, dBg.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public rAH mo8353read(JsonReader jsonReader) throws IOException {
            List<HYG> list = null;
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
                    if (this.BIo.get(PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_KEY).equals(nextName)) {
                        TypeAdapter<List<HYG>> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(TypeToken.getParameterized(List.class, HYG.class));
                            this.zZm = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PhoneCallControllerConfiguration(list);
        }
    }

    public AutoValue_PhoneCallControllerConfiguration(List<HYG> list) {
        super(list);
    }
}

package com.amazon.alexa.client.alexaservice.comms.payload;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.Sas;
import com.amazon.alexa.shY;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
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
public final class AutoValue_PhoneCallControllerDevice extends shY {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Sas> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Sas.zZm> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) PCCConstants.PHONE_CALL_CONTROLLER_CONNECTION_STATE_KEY);
            this.zQM = gson;
            this.BIo = Util.renameFields(shY.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Sas sas) throws IOException {
            if (sas == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get(PCCConstants.PHONE_CALL_CONTROLLER_CONNECTION_STATE_KEY));
            shY shy = (shY) sas;
            if (shy.zZm == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Sas.zZm> typeAdapter = this.zZm;
                if (typeAdapter == null) {
                    typeAdapter = this.zQM.getAdapter(Sas.zZm.class);
                    this.zZm = typeAdapter;
                }
                typeAdapter.write(jsonWriter, shy.zZm);
            }
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Sas mo8353read(JsonReader jsonReader) throws IOException {
            Sas.zZm zzm = null;
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
                    if (this.BIo.get(PCCConstants.PHONE_CALL_CONTROLLER_CONNECTION_STATE_KEY).equals(nextName)) {
                        TypeAdapter<Sas.zZm> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Sas.zZm.class);
                            this.zZm = typeAdapter;
                        }
                        zzm = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_PhoneCallControllerDevice(zzm);
        }
    }

    public AutoValue_PhoneCallControllerDevice(Sas.zZm zzm) {
        super(zzm);
    }
}

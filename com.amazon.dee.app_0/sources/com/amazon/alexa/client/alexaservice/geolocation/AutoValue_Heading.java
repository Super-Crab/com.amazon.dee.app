package com.amazon.alexa.client.alexaservice.geolocation;

import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.TWS;
import com.amazon.alexa.wze;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
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
public final class AutoValue_Heading extends TWS {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<wze> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Double> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "directionInDegrees");
            this.zQM = gson;
            this.BIo = Util.renameFields(TWS.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, wze wzeVar) throws IOException {
            if (wzeVar == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("directionInDegrees"));
            TypeAdapter<Double> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Double.class);
                this.zZm = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(((TWS) wzeVar).zZm));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public wze mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("directionInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Double.class);
                            this.zZm = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Heading(d);
        }
    }

    public AutoValue_Heading(double d) {
        super(d);
    }
}

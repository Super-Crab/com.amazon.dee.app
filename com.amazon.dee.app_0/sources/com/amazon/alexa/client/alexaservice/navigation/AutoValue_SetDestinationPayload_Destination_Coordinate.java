package com.amazon.alexa.client.alexaservice.navigation;

import com.amazon.alexa.Alc;
import com.amazon.alexa.C0179Pya;
import com.amazon.alexa.UrQ;
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
public final class AutoValue_SetDestinationPayload_Destination_Coordinate extends UrQ {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Alc.zZm.AbstractC0010zZm> {
        public final Map<String, String> BIo;
        public final Gson zQM;
        public volatile TypeAdapter<Double> zZm;

        public GsonTypeAdapter(Gson gson) {
            ArrayList zZm = C0179Pya.zZm((Object) "latitudeInDegrees", (Object) "longitudeInDegrees");
            this.zQM = gson;
            this.BIo = Util.renameFields(UrQ.class, zZm, gson.fieldNamingStrategy());
        }

        @Override // com.google.gson.TypeAdapter
        /* renamed from: zZm */
        public void write(JsonWriter jsonWriter, Alc.zZm.AbstractC0010zZm abstractC0010zZm) throws IOException {
            if (abstractC0010zZm == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.BIo.get("latitudeInDegrees"));
            TypeAdapter<Double> typeAdapter = this.zZm;
            if (typeAdapter == null) {
                typeAdapter = this.zQM.getAdapter(Double.class);
                this.zZm = typeAdapter;
            }
            UrQ urQ = (UrQ) abstractC0010zZm;
            typeAdapter.write(jsonWriter, Double.valueOf(urQ.zZm));
            jsonWriter.name(this.BIo.get("longitudeInDegrees"));
            TypeAdapter<Double> typeAdapter2 = this.zZm;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.zQM.getAdapter(Double.class);
                this.zZm = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(urQ.BIo));
            jsonWriter.endObject();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Alc.zZm.AbstractC0010zZm mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
            double d2 = 0.0d;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.BIo.get("latitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.zZm;
                        if (typeAdapter == null) {
                            typeAdapter = this.zQM.getAdapter(Double.class);
                            this.zZm = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else if (this.BIo.get("longitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter2 = this.zZm;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.zQM.getAdapter(Double.class);
                            this.zZm = typeAdapter2;
                        }
                        d2 = typeAdapter2.mo8353read(jsonReader).doubleValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SetDestinationPayload_Destination_Coordinate(d, d2);
        }
    }

    public AutoValue_SetDestinationPayload_Destination_Coordinate(double d, double d2) {
        super(d, d2);
    }
}

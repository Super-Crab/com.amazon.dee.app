package com.amazon.alexa.location.networking.alps.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.location.networking.alps.models.Altitude;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public final class AutoValue_Altitude extends C$AutoValue_Altitude {

    /* loaded from: classes9.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Altitude> {
        private volatile TypeAdapter<Double> double__adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("altitudeInMeters", "accuracyInMeters");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Altitude.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Altitude mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.realFieldNames.get("altitudeInMeters").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.double__adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else if (this.realFieldNames.get("accuracyInMeters").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter2 = this.double__adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter2;
                        }
                        d2 = typeAdapter2.mo8353read(jsonReader).doubleValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Altitude(d, d2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Altitude altitude) throws IOException {
            if (altitude == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("altitudeInMeters"));
            TypeAdapter<Double> typeAdapter = this.double__adapter;
            if (typeAdapter == null) {
                typeAdapter = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(altitude.getAltitudeInMeters()));
            jsonWriter.name(this.realFieldNames.get("accuracyInMeters"));
            TypeAdapter<Double> typeAdapter2 = this.double__adapter;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(altitude.getAccuracyInMeters()));
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Altitude(final double d, final double d2) {
        new Altitude(d, d2) { // from class: com.amazon.alexa.location.networking.alps.models.$AutoValue_Altitude
            private final double accuracyInMeters;
            private final double altitudeInMeters;

            /* renamed from: com.amazon.alexa.location.networking.alps.models.$AutoValue_Altitude$Builder */
            /* loaded from: classes9.dex */
            static final class Builder extends Altitude.Builder {
                private Double accuracyInMeters;
                private Double altitudeInMeters;

                @Override // com.amazon.alexa.location.networking.alps.models.Altitude.Builder
                Altitude build() {
                    String str = "";
                    if (this.altitudeInMeters == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " altitudeInMeters");
                    }
                    if (this.accuracyInMeters == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " accuracyInMeters");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Altitude(this.altitudeInMeters.doubleValue(), this.accuracyInMeters.doubleValue());
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.location.networking.alps.models.Altitude.Builder
                Altitude.Builder setAccuracyInMeters(double d) {
                    this.accuracyInMeters = Double.valueOf(d);
                    return this;
                }

                @Override // com.amazon.alexa.location.networking.alps.models.Altitude.Builder
                Altitude.Builder setAltitudeInMeters(double d) {
                    this.altitudeInMeters = Double.valueOf(d);
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.altitudeInMeters = d;
                this.accuracyInMeters = d2;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Altitude)) {
                    return false;
                }
                Altitude altitude = (Altitude) obj;
                return Double.doubleToLongBits(this.altitudeInMeters) == Double.doubleToLongBits(altitude.getAltitudeInMeters()) && Double.doubleToLongBits(this.accuracyInMeters) == Double.doubleToLongBits(altitude.getAccuracyInMeters());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.location.networking.alps.models.Altitude
            public double getAccuracyInMeters() {
                return this.accuracyInMeters;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.location.networking.alps.models.Altitude
            public double getAltitudeInMeters() {
                return this.altitudeInMeters;
            }

            public int hashCode() {
                return ((((int) ((Double.doubleToLongBits(this.altitudeInMeters) >>> 32) ^ Double.doubleToLongBits(this.altitudeInMeters))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.accuracyInMeters) >>> 32) ^ Double.doubleToLongBits(this.accuracyInMeters)));
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Altitude{altitudeInMeters=");
                outline107.append(this.altitudeInMeters);
                outline107.append(", accuracyInMeters=");
                return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInMeters, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

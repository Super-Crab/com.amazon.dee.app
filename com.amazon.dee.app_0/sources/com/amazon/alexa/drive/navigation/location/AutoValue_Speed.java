package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.location.Speed;
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
/* loaded from: classes7.dex */
public final class AutoValue_Speed extends C$AutoValue_Speed {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Speed> {
        private volatile TypeAdapter<Double> double__adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("speedInMetersPerSecond", "accuracyInMetersPerSecond");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Speed.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Speed mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.realFieldNames.get("speedInMetersPerSecond").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.double__adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else if (this.realFieldNames.get("accuracyInMetersPerSecond").equals(nextName)) {
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
            return new AutoValue_Speed(d, d2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Speed speed) throws IOException {
            if (speed == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("speedInMetersPerSecond"));
            TypeAdapter<Double> typeAdapter = this.double__adapter;
            if (typeAdapter == null) {
                typeAdapter = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(speed.getSpeedInMetersPerSecond()));
            jsonWriter.name(this.realFieldNames.get("accuracyInMetersPerSecond"));
            TypeAdapter<Double> typeAdapter2 = this.double__adapter;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(speed.getAccuracyInMetersPerSecond()));
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Speed(final double d, final double d2) {
        new Speed(d, d2) { // from class: com.amazon.alexa.drive.navigation.location.$AutoValue_Speed
            private final double accuracyInMetersPerSecond;
            private final double speedInMetersPerSecond;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.location.$AutoValue_Speed$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends Speed.Builder {
                private Double accuracyInMetersPerSecond;
                private Double speedInMetersPerSecond;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.Speed.Builder
                public Speed build() {
                    String str = "";
                    if (this.speedInMetersPerSecond == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " speedInMetersPerSecond");
                    }
                    if (this.accuracyInMetersPerSecond == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " accuracyInMetersPerSecond");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Speed(this.speedInMetersPerSecond.doubleValue(), this.accuracyInMetersPerSecond.doubleValue());
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.Speed.Builder
                public Speed.Builder setAccuracyInMetersPerSecond(double d) {
                    this.accuracyInMetersPerSecond = Double.valueOf(d);
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.Speed.Builder
                public Speed.Builder setSpeedInMetersPerSecond(double d) {
                    this.speedInMetersPerSecond = Double.valueOf(d);
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.speedInMetersPerSecond = d;
                this.accuracyInMetersPerSecond = d2;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Speed)) {
                    return false;
                }
                Speed speed = (Speed) obj;
                return Double.doubleToLongBits(this.speedInMetersPerSecond) == Double.doubleToLongBits(speed.getSpeedInMetersPerSecond()) && Double.doubleToLongBits(this.accuracyInMetersPerSecond) == Double.doubleToLongBits(speed.getAccuracyInMetersPerSecond());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.location.Speed
            public double getAccuracyInMetersPerSecond() {
                return this.accuracyInMetersPerSecond;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.location.Speed
            public double getSpeedInMetersPerSecond() {
                return this.speedInMetersPerSecond;
            }

            public int hashCode() {
                return ((((int) ((Double.doubleToLongBits(this.speedInMetersPerSecond) >>> 32) ^ Double.doubleToLongBits(this.speedInMetersPerSecond))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.accuracyInMetersPerSecond) >>> 32) ^ Double.doubleToLongBits(this.accuracyInMetersPerSecond)));
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Speed{speedInMetersPerSecond=");
                outline107.append(this.speedInMetersPerSecond);
                outline107.append(", accuracyInMetersPerSecond=");
                return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInMetersPerSecond, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

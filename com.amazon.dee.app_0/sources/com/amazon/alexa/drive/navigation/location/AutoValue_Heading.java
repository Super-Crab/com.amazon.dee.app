package com.amazon.alexa.drive.navigation.location;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.location.Heading;
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
public final class AutoValue_Heading extends C$AutoValue_Heading {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Heading> {
        private volatile TypeAdapter<Double> double__adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("directionInDegrees", "accuracyInDegrees");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Heading.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Heading mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.realFieldNames.get("directionInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.double__adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else if (this.realFieldNames.get("accuracyInDegrees").equals(nextName)) {
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
            return new AutoValue_Heading(d, d2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Heading heading) throws IOException {
            if (heading == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("directionInDegrees"));
            TypeAdapter<Double> typeAdapter = this.double__adapter;
            if (typeAdapter == null) {
                typeAdapter = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(heading.getDirectionInDegrees()));
            jsonWriter.name(this.realFieldNames.get("accuracyInDegrees"));
            TypeAdapter<Double> typeAdapter2 = this.double__adapter;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(heading.getAccuracyInDegrees()));
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Heading(final double d, final double d2) {
        new Heading(d, d2) { // from class: com.amazon.alexa.drive.navigation.location.$AutoValue_Heading
            private final double accuracyInDegrees;
            private final double directionInDegrees;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.location.$AutoValue_Heading$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends Heading.Builder {
                private Double accuracyInDegrees;
                private Double directionInDegrees;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.Heading.Builder
                public Heading build() {
                    String str = "";
                    if (this.directionInDegrees == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " directionInDegrees");
                    }
                    if (this.accuracyInDegrees == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " accuracyInDegrees");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Heading(this.directionInDegrees.doubleValue(), this.accuracyInDegrees.doubleValue());
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.Heading.Builder
                public Heading.Builder setAccuracyInDegrees(double d) {
                    this.accuracyInDegrees = Double.valueOf(d);
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.location.Heading.Builder
                public Heading.Builder setDirectionInDegrees(double d) {
                    this.directionInDegrees = Double.valueOf(d);
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.directionInDegrees = d;
                this.accuracyInDegrees = d2;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Heading)) {
                    return false;
                }
                Heading heading = (Heading) obj;
                return Double.doubleToLongBits(this.directionInDegrees) == Double.doubleToLongBits(heading.getDirectionInDegrees()) && Double.doubleToLongBits(this.accuracyInDegrees) == Double.doubleToLongBits(heading.getAccuracyInDegrees());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.location.Heading
            public double getAccuracyInDegrees() {
                return this.accuracyInDegrees;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.location.Heading
            public double getDirectionInDegrees() {
                return this.directionInDegrees;
            }

            public int hashCode() {
                return ((((int) ((Double.doubleToLongBits(this.directionInDegrees) >>> 32) ^ Double.doubleToLongBits(this.directionInDegrees))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.accuracyInDegrees) >>> 32) ^ Double.doubleToLongBits(this.accuracyInDegrees)));
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Heading{directionInDegrees=");
                outline107.append(this.directionInDegrees);
                outline107.append(", accuracyInDegrees=");
                return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInDegrees, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

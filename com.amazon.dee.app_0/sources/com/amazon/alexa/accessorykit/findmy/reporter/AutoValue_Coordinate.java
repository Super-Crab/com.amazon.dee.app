package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessorykit.findmy.reporter.Coordinate;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
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
/* loaded from: classes6.dex */
public final class AutoValue_Coordinate extends C$AutoValue_Coordinate {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Coordinate> {
        private volatile TypeAdapter<Double> double__adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("latitudeInDegrees", "longitudeInDegrees", "accuracyInMeters");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Coordinate.class, outline128, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Coordinate mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            double d = 0.0d;
            double d2 = 0.0d;
            double d3 = 0.0d;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("latitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter = this.double__adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter;
                        }
                        d = typeAdapter.mo8353read(jsonReader).doubleValue();
                    } else if (this.realFieldNames.get("longitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter2 = this.double__adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter2;
                        }
                        d2 = typeAdapter2.mo8353read(jsonReader).doubleValue();
                    } else if (this.realFieldNames.get("accuracyInMeters").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter3 = this.double__adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter3;
                        }
                        d3 = typeAdapter3.mo8353read(jsonReader).doubleValue();
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Coordinate(d, d2, d3);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Coordinate coordinate) throws IOException {
            if (coordinate == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("latitudeInDegrees"));
            TypeAdapter<Double> typeAdapter = this.double__adapter;
            if (typeAdapter == null) {
                typeAdapter = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter;
            }
            typeAdapter.write(jsonWriter, Double.valueOf(coordinate.getLatitudeInDegrees()));
            jsonWriter.name(this.realFieldNames.get("longitudeInDegrees"));
            TypeAdapter<Double> typeAdapter2 = this.double__adapter;
            if (typeAdapter2 == null) {
                typeAdapter2 = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter2;
            }
            typeAdapter2.write(jsonWriter, Double.valueOf(coordinate.getLongitudeInDegrees()));
            jsonWriter.name(this.realFieldNames.get("accuracyInMeters"));
            TypeAdapter<Double> typeAdapter3 = this.double__adapter;
            if (typeAdapter3 == null) {
                typeAdapter3 = this.gson.getAdapter(Double.class);
                this.double__adapter = typeAdapter3;
            }
            typeAdapter3.write(jsonWriter, Double.valueOf(coordinate.getAccuracyInMeters()));
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Coordinate(final double d, final double d2, final double d3) {
        new Coordinate(d, d2, d3) { // from class: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_Coordinate
            private final double accuracyInMeters;
            private final double latitudeInDegrees;
            private final double longitudeInDegrees;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_Coordinate$Builder */
            /* loaded from: classes6.dex */
            public static final class Builder extends Coordinate.Builder {
                private Double accuracyInMeters;
                private Double latitudeInDegrees;
                private Double longitudeInDegrees;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate.Builder
                public Coordinate build() {
                    String str = "";
                    if (this.latitudeInDegrees == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " latitudeInDegrees");
                    }
                    if (this.longitudeInDegrees == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " longitudeInDegrees");
                    }
                    if (this.accuracyInMeters == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " accuracyInMeters");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Coordinate(this.latitudeInDegrees.doubleValue(), this.longitudeInDegrees.doubleValue(), this.accuracyInMeters.doubleValue());
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate.Builder
                public Coordinate.Builder setAccuracyInMeters(double d) {
                    this.accuracyInMeters = Double.valueOf(d);
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate.Builder
                public Coordinate.Builder setLatitudeInDegrees(double d) {
                    this.latitudeInDegrees = Double.valueOf(d);
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate.Builder
                public Coordinate.Builder setLongitudeInDegrees(double d) {
                    this.longitudeInDegrees = Double.valueOf(d);
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.latitudeInDegrees = d;
                this.longitudeInDegrees = d2;
                this.accuracyInMeters = d3;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Coordinate)) {
                    return false;
                }
                Coordinate coordinate = (Coordinate) obj;
                return Double.doubleToLongBits(this.latitudeInDegrees) == Double.doubleToLongBits(coordinate.getLatitudeInDegrees()) && Double.doubleToLongBits(this.longitudeInDegrees) == Double.doubleToLongBits(coordinate.getLongitudeInDegrees()) && Double.doubleToLongBits(this.accuracyInMeters) == Double.doubleToLongBits(coordinate.getAccuracyInMeters());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate
            public double getAccuracyInMeters() {
                return this.accuracyInMeters;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate
            public double getLatitudeInDegrees() {
                return this.latitudeInDegrees;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Coordinate
            public double getLongitudeInDegrees() {
                return this.longitudeInDegrees;
            }

            public int hashCode() {
                return ((((((int) ((Double.doubleToLongBits(this.latitudeInDegrees) >>> 32) ^ Double.doubleToLongBits(this.latitudeInDegrees))) ^ 1000003) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.longitudeInDegrees) >>> 32) ^ Double.doubleToLongBits(this.longitudeInDegrees)))) * 1000003) ^ ((int) ((Double.doubleToLongBits(this.accuracyInMeters) >>> 32) ^ Double.doubleToLongBits(this.accuracyInMeters)));
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Coordinate{latitudeInDegrees=");
                outline107.append(this.latitudeInDegrees);
                outline107.append(", longitudeInDegrees=");
                outline107.append(this.longitudeInDegrees);
                outline107.append(", accuracyInMeters=");
                return GeneratedOutlineSupport1.outline84(outline107, this.accuracyInMeters, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

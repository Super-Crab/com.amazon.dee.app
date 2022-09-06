package com.amazon.alexa.drive.navigation;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.SavedLocations;
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
public final class AutoValue_SavedLocations_Item_Coordinate extends C$AutoValue_SavedLocations_Item_Coordinate {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SavedLocations.Item.Coordinate> {
        private volatile TypeAdapter<Double> double__adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("latitudeInDegrees", "longitudeInDegrees");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SavedLocations_Item_Coordinate.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SavedLocations.Item.Coordinate mo8353read(JsonReader jsonReader) throws IOException {
            Double d = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Double d2 = null;
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
                        d = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("longitudeInDegrees").equals(nextName)) {
                        TypeAdapter<Double> typeAdapter2 = this.double__adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Double.class);
                            this.double__adapter = typeAdapter2;
                        }
                        d2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SavedLocations_Item_Coordinate(d, d2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SavedLocations.Item.Coordinate coordinate) throws IOException {
            if (coordinate == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("latitudeInDegrees"));
            if (coordinate.getLatitudeInDegrees() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter = this.double__adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Double.class);
                    this.double__adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, coordinate.getLatitudeInDegrees());
            }
            jsonWriter.name(this.realFieldNames.get("longitudeInDegrees"));
            if (coordinate.getLongitudeInDegrees() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Double> typeAdapter2 = this.double__adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Double.class);
                    this.double__adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, coordinate.getLongitudeInDegrees());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SavedLocations_Item_Coordinate(final Double d, final Double d2) {
        new SavedLocations.Item.Coordinate(d, d2) { // from class: com.amazon.alexa.drive.navigation.$AutoValue_SavedLocations_Item_Coordinate
            private final Double latitudeInDegrees;
            private final Double longitudeInDegrees;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (d != null) {
                    this.latitudeInDegrees = d;
                    if (d2 != null) {
                        this.longitudeInDegrees = d2;
                        return;
                    }
                    throw new NullPointerException("Null longitudeInDegrees");
                }
                throw new NullPointerException("Null latitudeInDegrees");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SavedLocations.Item.Coordinate)) {
                    return false;
                }
                SavedLocations.Item.Coordinate coordinate = (SavedLocations.Item.Coordinate) obj;
                return this.latitudeInDegrees.equals(coordinate.getLatitudeInDegrees()) && this.longitudeInDegrees.equals(coordinate.getLongitudeInDegrees());
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Coordinate
            public Double getLatitudeInDegrees() {
                return this.latitudeInDegrees;
            }

            @Override // com.amazon.alexa.drive.navigation.SavedLocations.Item.Coordinate
            public Double getLongitudeInDegrees() {
                return this.longitudeInDegrees;
            }

            public int hashCode() {
                return ((this.latitudeInDegrees.hashCode() ^ 1000003) * 1000003) ^ this.longitudeInDegrees.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Coordinate{latitudeInDegrees=");
                outline107.append(this.latitudeInDegrees);
                outline107.append(", longitudeInDegrees=");
                outline107.append(this.longitudeInDegrees);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}

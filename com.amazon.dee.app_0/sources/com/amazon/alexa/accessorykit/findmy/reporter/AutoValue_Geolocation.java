package com.amazon.alexa.accessorykit.findmy.reporter;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessorykit.findmy.reporter.Geolocation;
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
public final class AutoValue_Geolocation extends C$AutoValue_Geolocation {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<Geolocation> {
        private volatile TypeAdapter<Altitude> altitude_adapter;
        private volatile TypeAdapter<Coordinate> coordinate_adapter;
        private final Gson gson;
        private volatile TypeAdapter<Heading> heading_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<Speed> speed_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("coordinate");
            arrayList.add("altitude");
            arrayList.add("heading");
            arrayList.add("speed");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_Geolocation.class, arrayList, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public Geolocation mo8353read(JsonReader jsonReader) throws IOException {
            Coordinate coordinate = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Altitude altitude = null;
            Heading heading = null;
            Speed speed = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("coordinate").equals(nextName)) {
                        TypeAdapter<Coordinate> typeAdapter = this.coordinate_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Coordinate.class);
                            this.coordinate_adapter = typeAdapter;
                        }
                        coordinate = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("altitude").equals(nextName)) {
                        TypeAdapter<Altitude> typeAdapter2 = this.altitude_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Altitude.class);
                            this.altitude_adapter = typeAdapter2;
                        }
                        altitude = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("heading").equals(nextName)) {
                        TypeAdapter<Heading> typeAdapter3 = this.heading_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(Heading.class);
                            this.heading_adapter = typeAdapter3;
                        }
                        heading = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("speed").equals(nextName)) {
                        TypeAdapter<Speed> typeAdapter4 = this.speed_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(Speed.class);
                            this.speed_adapter = typeAdapter4;
                        }
                        speed = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_Geolocation(coordinate, altitude, heading, speed);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, Geolocation geolocation) throws IOException {
            if (geolocation == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("coordinate"));
            if (geolocation.getCoordinate() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Coordinate> typeAdapter = this.coordinate_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Coordinate.class);
                    this.coordinate_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, geolocation.getCoordinate());
            }
            jsonWriter.name(this.realFieldNames.get("altitude"));
            if (geolocation.getAltitude() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Altitude> typeAdapter2 = this.altitude_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Altitude.class);
                    this.altitude_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, geolocation.getAltitude());
            }
            jsonWriter.name(this.realFieldNames.get("heading"));
            if (geolocation.getHeading() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Heading> typeAdapter3 = this.heading_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(Heading.class);
                    this.heading_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, geolocation.getHeading());
            }
            jsonWriter.name(this.realFieldNames.get("speed"));
            if (geolocation.getSpeed() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Speed> typeAdapter4 = this.speed_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(Speed.class);
                    this.speed_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, geolocation.getSpeed());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Geolocation(final Coordinate coordinate, @Nullable final Altitude altitude, @Nullable final Heading heading, @Nullable final Speed speed) {
        new Geolocation(coordinate, altitude, heading, speed) { // from class: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_Geolocation
            private final Altitude altitude;
            private final Coordinate coordinate;
            private final Heading heading;
            private final Speed speed;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_Geolocation$Builder */
            /* loaded from: classes6.dex */
            public static final class Builder extends Geolocation.Builder {
                private Altitude altitude;
                private Coordinate coordinate;
                private Heading heading;
                private Speed speed;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation.Builder
                public Geolocation build() {
                    String str = "";
                    if (this.coordinate == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " coordinate");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_Geolocation(this.coordinate, this.altitude, this.heading, this.speed);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation.Builder
                public Geolocation.Builder setAltitude(@Nullable Altitude altitude) {
                    this.altitude = altitude;
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation.Builder
                public Geolocation.Builder setCoordinate(Coordinate coordinate) {
                    if (coordinate != null) {
                        this.coordinate = coordinate;
                        return this;
                    }
                    throw new NullPointerException("Null coordinate");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation.Builder
                public Geolocation.Builder setHeading(@Nullable Heading heading) {
                    this.heading = heading;
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation.Builder
                public Geolocation.Builder setSpeed(@Nullable Speed speed) {
                    this.speed = speed;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (coordinate != null) {
                    this.coordinate = coordinate;
                    this.altitude = altitude;
                    this.heading = heading;
                    this.speed = speed;
                    return;
                }
                throw new NullPointerException("Null coordinate");
            }

            public boolean equals(Object obj) {
                Altitude altitude2;
                Heading heading2;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof Geolocation)) {
                    return false;
                }
                Geolocation geolocation = (Geolocation) obj;
                if (this.coordinate.equals(geolocation.getCoordinate()) && ((altitude2 = this.altitude) != null ? altitude2.equals(geolocation.getAltitude()) : geolocation.getAltitude() == null) && ((heading2 = this.heading) != null ? heading2.equals(geolocation.getHeading()) : geolocation.getHeading() == null)) {
                    Speed speed2 = this.speed;
                    if (speed2 == null) {
                        if (geolocation.getSpeed() == null) {
                            return true;
                        }
                    } else if (speed2.equals(geolocation.getSpeed())) {
                        return true;
                    }
                }
                return false;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation
            @Nullable
            public Altitude getAltitude() {
                return this.altitude;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation
            public Coordinate getCoordinate() {
                return this.coordinate;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation
            @Nullable
            public Heading getHeading() {
                return this.heading;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.Geolocation
            @Nullable
            public Speed getSpeed() {
                return this.speed;
            }

            public int hashCode() {
                int hashCode = (this.coordinate.hashCode() ^ 1000003) * 1000003;
                Altitude altitude2 = this.altitude;
                int i = 0;
                int hashCode2 = (hashCode ^ (altitude2 == null ? 0 : altitude2.hashCode())) * 1000003;
                Heading heading2 = this.heading;
                int hashCode3 = (hashCode2 ^ (heading2 == null ? 0 : heading2.hashCode())) * 1000003;
                Speed speed2 = this.speed;
                if (speed2 != null) {
                    i = speed2.hashCode();
                }
                return hashCode3 ^ i;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Geolocation{coordinate=");
                outline107.append(this.coordinate);
                outline107.append(", altitude=");
                outline107.append(this.altitude);
                outline107.append(", heading=");
                outline107.append(this.heading);
                outline107.append(", speed=");
                outline107.append(this.speed);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}

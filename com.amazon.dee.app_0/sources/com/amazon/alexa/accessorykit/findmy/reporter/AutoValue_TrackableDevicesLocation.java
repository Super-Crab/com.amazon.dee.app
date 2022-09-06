package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
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
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_TrackableDevicesLocation extends C$AutoValue_TrackableDevicesLocation {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<TrackableDevicesLocation> {
        private volatile TypeAdapter<Geolocation> geolocation_adapter;
        private final Gson gson;
        private volatile TypeAdapter<List<TrackableDevice>> list__trackableDevice_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline128 = GeneratedOutlineSupport1.outline128("geolocation", "trackableDevices", "timestamp");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_TrackableDevicesLocation.class, outline128, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public TrackableDevicesLocation mo8353read(JsonReader jsonReader) throws IOException {
            Geolocation geolocation = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            List<TrackableDevice> list = null;
            String str = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("geolocation").equals(nextName)) {
                        TypeAdapter<Geolocation> typeAdapter = this.geolocation_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(Geolocation.class);
                            this.geolocation_adapter = typeAdapter;
                        }
                        geolocation = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("trackableDevices").equals(nextName)) {
                        TypeAdapter<List<TrackableDevice>> typeAdapter2 = this.list__trackableDevice_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(TypeToken.getParameterized(List.class, TrackableDevice.class));
                            this.list__trackableDevice_adapter = typeAdapter2;
                        }
                        list = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("timestamp").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str = typeAdapter3.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_TrackableDevicesLocation(geolocation, list, str);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, TrackableDevicesLocation trackableDevicesLocation) throws IOException {
            if (trackableDevicesLocation == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("geolocation"));
            if (trackableDevicesLocation.getGeolocation() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Geolocation> typeAdapter = this.geolocation_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(Geolocation.class);
                    this.geolocation_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, trackableDevicesLocation.getGeolocation());
            }
            jsonWriter.name(this.realFieldNames.get("trackableDevices"));
            if (trackableDevicesLocation.getTrackableDevices() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<TrackableDevice>> typeAdapter2 = this.list__trackableDevice_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(TypeToken.getParameterized(List.class, TrackableDevice.class));
                    this.list__trackableDevice_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, trackableDevicesLocation.getTrackableDevices());
            }
            jsonWriter.name(this.realFieldNames.get("timestamp"));
            if (trackableDevicesLocation.getTimestamp() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, trackableDevicesLocation.getTimestamp());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_TrackableDevicesLocation(final Geolocation geolocation, final List<TrackableDevice> list, final String str) {
        new TrackableDevicesLocation(geolocation, list, str) { // from class: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_TrackableDevicesLocation
            private final Geolocation geolocation;
            private final String timestamp;
            private final List<TrackableDevice> trackableDevices;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_TrackableDevicesLocation$Builder */
            /* loaded from: classes6.dex */
            public static final class Builder extends TrackableDevicesLocation.Builder {
                private Geolocation geolocation;
                private String timestamp;
                private List<TrackableDevice> trackableDevices;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation.Builder
                public TrackableDevicesLocation build() {
                    String str = "";
                    if (this.geolocation == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " geolocation");
                    }
                    if (this.trackableDevices == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " trackableDevices");
                    }
                    if (this.timestamp == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " timestamp");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_TrackableDevicesLocation(this.geolocation, this.trackableDevices, this.timestamp);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation.Builder
                public TrackableDevicesLocation.Builder setGeolocation(Geolocation geolocation) {
                    if (geolocation != null) {
                        this.geolocation = geolocation;
                        return this;
                    }
                    throw new NullPointerException("Null geolocation");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation.Builder
                public TrackableDevicesLocation.Builder setTimestamp(String str) {
                    if (str != null) {
                        this.timestamp = str;
                        return this;
                    }
                    throw new NullPointerException("Null timestamp");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation.Builder
                public TrackableDevicesLocation.Builder setTrackableDevices(List<TrackableDevice> list) {
                    if (list != null) {
                        this.trackableDevices = list;
                        return this;
                    }
                    throw new NullPointerException("Null trackableDevices");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (geolocation != null) {
                    this.geolocation = geolocation;
                    if (list != null) {
                        this.trackableDevices = list;
                        if (str != null) {
                            this.timestamp = str;
                            return;
                        }
                        throw new NullPointerException("Null timestamp");
                    }
                    throw new NullPointerException("Null trackableDevices");
                }
                throw new NullPointerException("Null geolocation");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof TrackableDevicesLocation)) {
                    return false;
                }
                TrackableDevicesLocation trackableDevicesLocation = (TrackableDevicesLocation) obj;
                return this.geolocation.equals(trackableDevicesLocation.getGeolocation()) && this.trackableDevices.equals(trackableDevicesLocation.getTrackableDevices()) && this.timestamp.equals(trackableDevicesLocation.getTimestamp());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation
            public Geolocation getGeolocation() {
                return this.geolocation;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation
            public String getTimestamp() {
                return this.timestamp;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.accessorykit.findmy.reporter.TrackableDevicesLocation
            public List<TrackableDevice> getTrackableDevices() {
                return this.trackableDevices;
            }

            public int hashCode() {
                return ((((this.geolocation.hashCode() ^ 1000003) * 1000003) ^ this.trackableDevices.hashCode()) * 1000003) ^ this.timestamp.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrackableDevicesLocation{geolocation=");
                outline107.append(this.geolocation);
                outline107.append(", trackableDevices=");
                outline107.append(this.trackableDevices);
                outline107.append(", timestamp=");
                return GeneratedOutlineSupport1.outline91(outline107, this.timestamp, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

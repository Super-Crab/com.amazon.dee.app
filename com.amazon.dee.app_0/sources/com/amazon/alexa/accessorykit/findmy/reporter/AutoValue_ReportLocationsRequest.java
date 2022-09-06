package com.amazon.alexa.accessorykit.findmy.reporter;

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
public final class AutoValue_ReportLocationsRequest extends C$AutoValue_ReportLocationsRequest {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ReportLocationsRequest> {
        private final Gson gson;
        private volatile TypeAdapter<List<TrackableDevicesLocation>> list__trackableDevicesLocation_adapter;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("locations");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_ReportLocationsRequest.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ReportLocationsRequest mo8353read(JsonReader jsonReader) throws IOException {
            List<TrackableDevicesLocation> list = null;
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
                    if (this.realFieldNames.get("locations").equals(nextName)) {
                        TypeAdapter<List<TrackableDevicesLocation>> typeAdapter = this.list__trackableDevicesLocation_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, TrackableDevicesLocation.class));
                            this.list__trackableDevicesLocation_adapter = typeAdapter;
                        }
                        list = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ReportLocationsRequest(list);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ReportLocationsRequest reportLocationsRequest) throws IOException {
            if (reportLocationsRequest == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("locations"));
            if (reportLocationsRequest.getLocations() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<TrackableDevicesLocation>> typeAdapter = this.list__trackableDevicesLocation_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, TrackableDevicesLocation.class));
                    this.list__trackableDevicesLocation_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, reportLocationsRequest.getLocations());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ReportLocationsRequest(final List<TrackableDevicesLocation> list) {
        new ReportLocationsRequest(list) { // from class: com.amazon.alexa.accessorykit.findmy.reporter.$AutoValue_ReportLocationsRequest
            private final List<TrackableDevicesLocation> locations;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (list != null) {
                    this.locations = list;
                    return;
                }
                throw new NullPointerException("Null locations");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ReportLocationsRequest)) {
                    return false;
                }
                return this.locations.equals(((ReportLocationsRequest) obj).getLocations());
            }

            @Override // com.amazon.alexa.accessorykit.findmy.reporter.ReportLocationsRequest
            public List<TrackableDevicesLocation> getLocations() {
                return this.locations;
            }

            public int hashCode() {
                return this.locations.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("ReportLocationsRequest{locations="), this.locations, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

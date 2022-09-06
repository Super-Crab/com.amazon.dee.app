package com.amazon.alexa.location.networking.alps.models;

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
/* loaded from: classes9.dex */
public final class AutoValue_ReportLocationsRequestBody extends C$AutoValue_ReportLocationsRequestBody {

    /* loaded from: classes9.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ReportLocationsRequestBody> {
        private final Gson gson;
        private volatile TypeAdapter<List<TrackableDevicesLocation>> list__trackableDevicesLocation_adapter;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("locations");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_ReportLocationsRequestBody.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ReportLocationsRequestBody mo8353read(JsonReader jsonReader) throws IOException {
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
            return new AutoValue_ReportLocationsRequestBody(list);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ReportLocationsRequestBody reportLocationsRequestBody) throws IOException {
            if (reportLocationsRequestBody == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("locations"));
            if (reportLocationsRequestBody.getLocations() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<List<TrackableDevicesLocation>> typeAdapter = this.list__trackableDevicesLocation_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(List.class, TrackableDevicesLocation.class));
                    this.list__trackableDevicesLocation_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, reportLocationsRequestBody.getLocations());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ReportLocationsRequestBody(final List<TrackableDevicesLocation> list) {
        new ReportLocationsRequestBody(list) { // from class: com.amazon.alexa.location.networking.alps.models.$AutoValue_ReportLocationsRequestBody
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
                if (!(obj instanceof ReportLocationsRequestBody)) {
                    return false;
                }
                return this.locations.equals(((ReportLocationsRequestBody) obj).getLocations());
            }

            @Override // com.amazon.alexa.location.networking.alps.models.ReportLocationsRequestBody
            public List<TrackableDevicesLocation> getLocations() {
                return this.locations;
            }

            public int hashCode() {
                return this.locations.hashCode() ^ 1000003;
            }

            public String toString() {
                return GeneratedOutlineSupport1.outline95(GeneratedOutlineSupport1.outline107("ReportLocationsRequestBody{locations="), this.locations, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

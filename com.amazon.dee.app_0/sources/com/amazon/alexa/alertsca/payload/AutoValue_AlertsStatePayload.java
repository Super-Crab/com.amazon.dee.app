package com.amazon.alexa.alertsca.payload;

import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.payload.AlertsStatePayload;
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
import java.util.Map;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_AlertsStatePayload extends C$AutoValue_AlertsStatePayload {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AlertsStatePayload> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<Set<AlertRecord>> set__alertRecord_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("allAlerts", "activeAlerts");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_AlertsStatePayload.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AlertsStatePayload mo8353read(JsonReader jsonReader) throws IOException {
            Set<AlertRecord> set = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Set<AlertRecord> set2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("allAlerts").equals(nextName)) {
                        TypeAdapter<Set<AlertRecord>> typeAdapter = this.set__alertRecord_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(Set.class, AlertRecord.class));
                            this.set__alertRecord_adapter = typeAdapter;
                        }
                        set = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("activeAlerts").equals(nextName)) {
                        TypeAdapter<Set<AlertRecord>> typeAdapter2 = this.set__alertRecord_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(TypeToken.getParameterized(Set.class, AlertRecord.class));
                            this.set__alertRecord_adapter = typeAdapter2;
                        }
                        set2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AlertsStatePayload(set, set2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AlertsStatePayload alertsStatePayload) throws IOException {
            if (alertsStatePayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("allAlerts"));
            if (alertsStatePayload.getAllAlerts() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<AlertRecord>> typeAdapter = this.set__alertRecord_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(Set.class, AlertRecord.class));
                    this.set__alertRecord_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, alertsStatePayload.getAllAlerts());
            }
            jsonWriter.name(this.realFieldNames.get("activeAlerts"));
            if (alertsStatePayload.getActiveAlerts() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<AlertRecord>> typeAdapter2 = this.set__alertRecord_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(TypeToken.getParameterized(Set.class, AlertRecord.class));
                    this.set__alertRecord_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, alertsStatePayload.getActiveAlerts());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertsStatePayload(final Set<AlertRecord> set, final Set<AlertRecord> set2) {
        new AlertsStatePayload(set, set2) { // from class: com.amazon.alexa.alertsca.payload.$AutoValue_AlertsStatePayload
            private final Set<AlertRecord> activeAlerts;
            private final Set<AlertRecord> allAlerts;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.alertsca.payload.$AutoValue_AlertsStatePayload$Builder */
            /* loaded from: classes6.dex */
            public static final class Builder extends AlertsStatePayload.Builder {
                private Set<AlertRecord> activeAlerts;
                private Set<AlertRecord> allAlerts;

                @Override // com.amazon.alexa.alertsca.payload.AlertsStatePayload.Builder
                public AlertsStatePayload build() {
                    String str = "";
                    if (this.allAlerts == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " allAlerts");
                    }
                    if (this.activeAlerts == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " activeAlerts");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_AlertsStatePayload(this.allAlerts, this.activeAlerts);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.alertsca.payload.AlertsStatePayload.Builder
                public AlertsStatePayload.Builder setActiveAlerts(Set<AlertRecord> set) {
                    if (set != null) {
                        this.activeAlerts = set;
                        return this;
                    }
                    throw new NullPointerException("Null activeAlerts");
                }

                @Override // com.amazon.alexa.alertsca.payload.AlertsStatePayload.Builder
                public AlertsStatePayload.Builder setAllAlerts(Set<AlertRecord> set) {
                    if (set != null) {
                        this.allAlerts = set;
                        return this;
                    }
                    throw new NullPointerException("Null allAlerts");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (set != null) {
                    this.allAlerts = set;
                    if (set2 != null) {
                        this.activeAlerts = set2;
                        return;
                    }
                    throw new NullPointerException("Null activeAlerts");
                }
                throw new NullPointerException("Null allAlerts");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof AlertsStatePayload)) {
                    return false;
                }
                AlertsStatePayload alertsStatePayload = (AlertsStatePayload) obj;
                return this.allAlerts.equals(alertsStatePayload.getAllAlerts()) && this.activeAlerts.equals(alertsStatePayload.getActiveAlerts());
            }

            @Override // com.amazon.alexa.alertsca.payload.AlertsStatePayload
            public Set<AlertRecord> getActiveAlerts() {
                return this.activeAlerts;
            }

            @Override // com.amazon.alexa.alertsca.payload.AlertsStatePayload
            public Set<AlertRecord> getAllAlerts() {
                return this.allAlerts;
            }

            public int hashCode() {
                return ((this.allAlerts.hashCode() ^ 1000003) * 1000003) ^ this.activeAlerts.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertsStatePayload{allAlerts=");
                outline107.append(this.allAlerts);
                outline107.append(", activeAlerts=");
                outline107.append(this.activeAlerts);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}

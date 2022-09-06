package com.amazon.alexa.alertsca.payload;

import com.amazon.alexa.alertsca.AlertsToken;
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
public final class AutoValue_AlertsPayload extends C$AutoValue_AlertsPayload {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<AlertsPayload> {
        private volatile TypeAdapter<AlertsToken> alertsToken_adapter;
        private final Gson gson;
        private final Map<String, String> realFieldNames;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("token");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_AlertsPayload.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public AlertsPayload mo8353read(JsonReader jsonReader) throws IOException {
            AlertsToken alertsToken = null;
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
                    if (this.realFieldNames.get("token").equals(nextName)) {
                        TypeAdapter<AlertsToken> typeAdapter = this.alertsToken_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(AlertsToken.class);
                            this.alertsToken_adapter = typeAdapter;
                        }
                        alertsToken = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_AlertsPayload(alertsToken);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, AlertsPayload alertsPayload) throws IOException {
            if (alertsPayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("token"));
            if (alertsPayload.getToken() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<AlertsToken> typeAdapter = this.alertsToken_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(AlertsToken.class);
                    this.alertsToken_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, alertsPayload.getToken());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_AlertsPayload(final AlertsToken alertsToken) {
        new AlertsPayload(alertsToken) { // from class: com.amazon.alexa.alertsca.payload.$AutoValue_AlertsPayload
            private final AlertsToken token;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (alertsToken != null) {
                    this.token = alertsToken;
                    return;
                }
                throw new NullPointerException("Null token");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof AlertsPayload)) {
                    return false;
                }
                return this.token.equals(((AlertsPayload) obj).getToken());
            }

            @Override // com.amazon.alexa.alertsca.payload.AlertsPayload
            public AlertsToken getToken() {
                return this.token;
            }

            public int hashCode() {
                return this.token.hashCode() ^ 1000003;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlertsPayload{token=");
                outline107.append(this.token);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}

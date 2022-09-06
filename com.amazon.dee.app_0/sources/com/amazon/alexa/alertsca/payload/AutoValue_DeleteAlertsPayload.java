package com.amazon.alexa.alertsca.payload;

import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.payload.DeleteAlertsPayload;
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
public final class AutoValue_DeleteAlertsPayload extends C$AutoValue_DeleteAlertsPayload {

    /* loaded from: classes6.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<DeleteAlertsPayload> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<Set<AlertsToken>> set__alertsToken_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline126 = GeneratedOutlineSupport1.outline126("tokens");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_DeleteAlertsPayload.class, outline126, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public DeleteAlertsPayload mo8353read(JsonReader jsonReader) throws IOException {
            Set<AlertsToken> set = null;
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
                    if (this.realFieldNames.get("tokens").equals(nextName)) {
                        TypeAdapter<Set<AlertsToken>> typeAdapter = this.set__alertsToken_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(Set.class, AlertsToken.class));
                            this.set__alertsToken_adapter = typeAdapter;
                        }
                        set = typeAdapter.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_DeleteAlertsPayload(set);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, DeleteAlertsPayload deleteAlertsPayload) throws IOException {
            if (deleteAlertsPayload == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("tokens"));
            if (deleteAlertsPayload.getTokens() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Set<AlertsToken>> typeAdapter = this.set__alertsToken_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(TypeToken.getParameterized(Set.class, AlertsToken.class));
                    this.set__alertsToken_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, deleteAlertsPayload.getTokens());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_DeleteAlertsPayload(final Set<AlertsToken> set) {
        new DeleteAlertsPayload(set) { // from class: com.amazon.alexa.alertsca.payload.$AutoValue_DeleteAlertsPayload
            private final Set<AlertsToken> tokens;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.alertsca.payload.$AutoValue_DeleteAlertsPayload$Builder */
            /* loaded from: classes6.dex */
            public static final class Builder extends DeleteAlertsPayload.Builder {
                private Set<AlertsToken> tokens;

                @Override // com.amazon.alexa.alertsca.payload.DeleteAlertsPayload.Builder
                public DeleteAlertsPayload build() {
                    String str = "";
                    if (this.tokens == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " tokens");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_DeleteAlertsPayload(this.tokens);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.alertsca.payload.DeleteAlertsPayload.Builder
                public DeleteAlertsPayload.Builder setTokens(Set<AlertsToken> set) {
                    if (set != null) {
                        this.tokens = set;
                        return this;
                    }
                    throw new NullPointerException("Null tokens");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (set != null) {
                    this.tokens = set;
                    return;
                }
                throw new NullPointerException("Null tokens");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof DeleteAlertsPayload)) {
                    return false;
                }
                return this.tokens.equals(((DeleteAlertsPayload) obj).getTokens());
            }

            @Override // com.amazon.alexa.alertsca.payload.DeleteAlertsPayload
            public Set<AlertsToken> getTokens() {
                return this.tokens;
            }

            public int hashCode() {
                return this.tokens.hashCode() ^ 1000003;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeleteAlertsPayload{tokens=");
                outline107.append(this.tokens);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}

package com.amazon.alexa.location.networking.alps.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.location.networking.alps.models.ReportLocationsResponseBody;
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
/* loaded from: classes9.dex */
public final class AutoValue_ReportLocationsResponseBody extends C$AutoValue_ReportLocationsResponseBody {

    /* loaded from: classes9.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<ReportLocationsResponseBody> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline127 = GeneratedOutlineSupport1.outline127("type", "message");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_ReportLocationsResponseBody.class, outline127, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public ReportLocationsResponseBody mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("type").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("message").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_ReportLocationsResponseBody(str, str2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, ReportLocationsResponseBody reportLocationsResponseBody) throws IOException {
            if (reportLocationsResponseBody == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("type"));
            if (reportLocationsResponseBody.getType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, reportLocationsResponseBody.getType());
            }
            jsonWriter.name(this.realFieldNames.get("message"));
            if (reportLocationsResponseBody.getMessage() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, reportLocationsResponseBody.getMessage());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ReportLocationsResponseBody(@Nullable final String str, @Nullable final String str2) {
        new ReportLocationsResponseBody(str, str2) { // from class: com.amazon.alexa.location.networking.alps.models.$AutoValue_ReportLocationsResponseBody
            private final String message;
            private final String type;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.location.networking.alps.models.$AutoValue_ReportLocationsResponseBody$Builder */
            /* loaded from: classes9.dex */
            public static final class Builder extends ReportLocationsResponseBody.Builder {
                private String message;
                private String type;

                @Override // com.amazon.alexa.location.networking.alps.models.ReportLocationsResponseBody.Builder
                ReportLocationsResponseBody build() {
                    return new AutoValue_ReportLocationsResponseBody(this.type, this.message);
                }

                @Override // com.amazon.alexa.location.networking.alps.models.ReportLocationsResponseBody.Builder
                ReportLocationsResponseBody.Builder setMessage(@Nullable String str) {
                    this.message = str;
                    return this;
                }

                @Override // com.amazon.alexa.location.networking.alps.models.ReportLocationsResponseBody.Builder
                ReportLocationsResponseBody.Builder setType(@Nullable String str) {
                    this.type = str;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                this.type = str;
                this.message = str2;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof ReportLocationsResponseBody)) {
                    return false;
                }
                ReportLocationsResponseBody reportLocationsResponseBody = (ReportLocationsResponseBody) obj;
                String str3 = this.type;
                if (str3 != null ? str3.equals(reportLocationsResponseBody.getType()) : reportLocationsResponseBody.getType() == null) {
                    String str4 = this.message;
                    if (str4 == null) {
                        if (reportLocationsResponseBody.getMessage() == null) {
                            return true;
                        }
                    } else if (str4.equals(reportLocationsResponseBody.getMessage())) {
                        return true;
                    }
                }
                return false;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.location.networking.alps.models.ReportLocationsResponseBody
            @Nullable
            public String getMessage() {
                return this.message;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.location.networking.alps.models.ReportLocationsResponseBody
            @Nullable
            public String getType() {
                return this.type;
            }

            public int hashCode() {
                String str3 = this.type;
                int i = 0;
                int hashCode = ((str3 == null ? 0 : str3.hashCode()) ^ 1000003) * 1000003;
                String str4 = this.message;
                if (str4 != null) {
                    i = str4.hashCode();
                }
                return hashCode ^ i;
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReportLocationsResponseBody{type=");
                outline107.append(this.type);
                outline107.append(", message=");
                return GeneratedOutlineSupport1.outline91(outline107, this.message, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

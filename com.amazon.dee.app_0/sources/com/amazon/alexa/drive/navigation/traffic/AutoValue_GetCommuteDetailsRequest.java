package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
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
public final class AutoValue_GetCommuteDetailsRequest extends C$AutoValue_GetCommuteDetailsRequest {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<GetCommuteDetailsRequest> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("requestId", AuthorizationResponseParser.CLIENT_ID_STATE, "rankingCriteria", "transportMode", "correlationId");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_GetCommuteDetailsRequest.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public GetCommuteDetailsRequest mo8353read(JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            String str = null;
            String str2 = null;
            String str3 = null;
            String str4 = null;
            String str5 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("requestId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(AuthorizationResponseParser.CLIENT_ID_STATE).equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("rankingCriteria").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("transportMode").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("correlationId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_GetCommuteDetailsRequest(str, str2, str3, str4, str5);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, GetCommuteDetailsRequest getCommuteDetailsRequest) throws IOException {
            if (getCommuteDetailsRequest == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("requestId"));
            if (getCommuteDetailsRequest.getRequestId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, getCommuteDetailsRequest.getRequestId());
            }
            jsonWriter.name(this.realFieldNames.get(AuthorizationResponseParser.CLIENT_ID_STATE));
            if (getCommuteDetailsRequest.getClientId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, getCommuteDetailsRequest.getClientId());
            }
            jsonWriter.name(this.realFieldNames.get("rankingCriteria"));
            if (getCommuteDetailsRequest.getRankingCriteria() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, getCommuteDetailsRequest.getRankingCriteria());
            }
            jsonWriter.name(this.realFieldNames.get("transportMode"));
            if (getCommuteDetailsRequest.getTransportMode() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, getCommuteDetailsRequest.getTransportMode());
            }
            jsonWriter.name(this.realFieldNames.get("correlationId"));
            if (getCommuteDetailsRequest.getCorrelationId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, getCommuteDetailsRequest.getCorrelationId());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_GetCommuteDetailsRequest(final String str, final String str2, @Nullable final String str3, @Nullable final String str4, final String str5) {
        new GetCommuteDetailsRequest(str, str2, str3, str4, str5) { // from class: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDetailsRequest
            private final String clientId;
            private final String correlationId;
            private final String rankingCriteria;
            private final String requestId;
            private final String transportMode;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDetailsRequest$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends GetCommuteDetailsRequest.Builder {
                private String clientId;
                private String correlationId;
                private String rankingCriteria;
                private String requestId;
                private String transportMode;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest.Builder
                public GetCommuteDetailsRequest build() {
                    String str = "";
                    if (this.requestId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " requestId");
                    }
                    if (this.clientId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " clientId");
                    }
                    if (this.correlationId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " correlationId");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_GetCommuteDetailsRequest(this.requestId, this.clientId, this.rankingCriteria, this.transportMode, this.correlationId);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest.Builder
                public GetCommuteDetailsRequest.Builder setClientId(String str) {
                    if (str != null) {
                        this.clientId = str;
                        return this;
                    }
                    throw new NullPointerException("Null clientId");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest.Builder
                public GetCommuteDetailsRequest.Builder setCorrelationId(String str) {
                    if (str != null) {
                        this.correlationId = str;
                        return this;
                    }
                    throw new NullPointerException("Null correlationId");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest.Builder
                GetCommuteDetailsRequest.Builder setRankingCriteria(@Nullable String str) {
                    this.rankingCriteria = str;
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest.Builder
                public GetCommuteDetailsRequest.Builder setRequestId(String str) {
                    if (str != null) {
                        this.requestId = str;
                        return this;
                    }
                    throw new NullPointerException("Null requestId");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest.Builder
                GetCommuteDetailsRequest.Builder setTransportMode(@Nullable String str) {
                    this.transportMode = str;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.requestId = str;
                    if (str2 != null) {
                        this.clientId = str2;
                        this.rankingCriteria = str3;
                        this.transportMode = str4;
                        if (str5 != null) {
                            this.correlationId = str5;
                            return;
                        }
                        throw new NullPointerException("Null correlationId");
                    }
                    throw new NullPointerException("Null clientId");
                }
                throw new NullPointerException("Null requestId");
            }

            public boolean equals(Object obj) {
                String str6;
                String str7;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof GetCommuteDetailsRequest)) {
                    return false;
                }
                GetCommuteDetailsRequest getCommuteDetailsRequest = (GetCommuteDetailsRequest) obj;
                return this.requestId.equals(getCommuteDetailsRequest.getRequestId()) && this.clientId.equals(getCommuteDetailsRequest.getClientId()) && ((str6 = this.rankingCriteria) != null ? str6.equals(getCommuteDetailsRequest.getRankingCriteria()) : getCommuteDetailsRequest.getRankingCriteria() == null) && ((str7 = this.transportMode) != null ? str7.equals(getCommuteDetailsRequest.getTransportMode()) : getCommuteDetailsRequest.getTransportMode() == null) && this.correlationId.equals(getCommuteDetailsRequest.getCorrelationId());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest
            public String getClientId() {
                return this.clientId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest
            public String getCorrelationId() {
                return this.correlationId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest
            @Nullable
            public String getRankingCriteria() {
                return this.rankingCriteria;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest
            public String getRequestId() {
                return this.requestId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsRequest
            @Nullable
            public String getTransportMode() {
                return this.transportMode;
            }

            public int hashCode() {
                int hashCode = (((this.requestId.hashCode() ^ 1000003) * 1000003) ^ this.clientId.hashCode()) * 1000003;
                String str6 = this.rankingCriteria;
                int i = 0;
                int hashCode2 = (hashCode ^ (str6 == null ? 0 : str6.hashCode())) * 1000003;
                String str7 = this.transportMode;
                if (str7 != null) {
                    i = str7.hashCode();
                }
                return ((hashCode2 ^ i) * 1000003) ^ this.correlationId.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCommuteDetailsRequest{requestId=");
                outline107.append(this.requestId);
                outline107.append(", clientId=");
                outline107.append(this.clientId);
                outline107.append(", rankingCriteria=");
                outline107.append(this.rankingCriteria);
                outline107.append(", transportMode=");
                outline107.append(this.transportMode);
                outline107.append(", correlationId=");
                return GeneratedOutlineSupport1.outline91(outline107, this.correlationId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

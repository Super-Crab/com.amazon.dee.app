package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
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
public final class AutoValue_GetCommuteDestinationRequest extends C$AutoValue_GetCommuteDestinationRequest {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<GetCommuteDestinationRequest> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("requestId", AuthorizationResponseParser.CLIENT_ID_STATE, "customerId", "deviceSerialNumber", "zonedDateTime");
            outline129.add(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID);
            outline129.add("deviceType");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_GetCommuteDestinationRequest.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public GetCommuteDestinationRequest mo8353read(JsonReader jsonReader) throws IOException {
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
            String str6 = null;
            String str7 = null;
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
                    } else if (this.realFieldNames.get("customerId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("deviceSerialNumber").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("zonedDateTime").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID).equals(nextName)) {
                        TypeAdapter<String> typeAdapter6 = this.string_adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter6;
                        }
                        str6 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("deviceType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter7 = this.string_adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter7;
                        }
                        str7 = typeAdapter7.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_GetCommuteDestinationRequest(str, str2, str3, str4, str5, str6, str7);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, GetCommuteDestinationRequest getCommuteDestinationRequest) throws IOException {
            if (getCommuteDestinationRequest == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("requestId"));
            if (getCommuteDestinationRequest.getRequestId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, getCommuteDestinationRequest.getRequestId());
            }
            jsonWriter.name(this.realFieldNames.get(AuthorizationResponseParser.CLIENT_ID_STATE));
            if (getCommuteDestinationRequest.getClientId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, getCommuteDestinationRequest.getClientId());
            }
            jsonWriter.name(this.realFieldNames.get("customerId"));
            if (getCommuteDestinationRequest.getCustomerId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, getCommuteDestinationRequest.getCustomerId());
            }
            jsonWriter.name(this.realFieldNames.get("deviceSerialNumber"));
            if (getCommuteDestinationRequest.getDeviceSerialNumber() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, getCommuteDestinationRequest.getDeviceSerialNumber());
            }
            jsonWriter.name(this.realFieldNames.get("zonedDateTime"));
            if (getCommuteDestinationRequest.getZonedDateTime() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, getCommuteDestinationRequest.getZonedDateTime());
            }
            jsonWriter.name(this.realFieldNames.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID));
            if (getCommuteDestinationRequest.getPersonId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.string_adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, getCommuteDestinationRequest.getPersonId());
            }
            jsonWriter.name(this.realFieldNames.get("deviceType"));
            if (getCommuteDestinationRequest.getDeviceType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter7 = this.string_adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, getCommuteDestinationRequest.getDeviceType());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_GetCommuteDestinationRequest(final String str, final String str2, final String str3, final String str4, final String str5, @Nullable final String str6, final String str7) {
        new GetCommuteDestinationRequest(str, str2, str3, str4, str5, str6, str7) { // from class: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDestinationRequest
            private final String clientId;
            private final String customerId;
            private final String deviceSerialNumber;
            private final String deviceType;
            private final String personId;
            private final String requestId;
            private final String zonedDateTime;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDestinationRequest$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends GetCommuteDestinationRequest.Builder {
                private String clientId;
                private String customerId;
                private String deviceSerialNumber;
                private String deviceType;
                private String personId;
                private String requestId;
                private String zonedDateTime;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest build() {
                    String str = "";
                    if (this.requestId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " requestId");
                    }
                    if (this.clientId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " clientId");
                    }
                    if (this.customerId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " customerId");
                    }
                    if (this.deviceSerialNumber == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceSerialNumber");
                    }
                    if (this.zonedDateTime == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " zonedDateTime");
                    }
                    if (this.deviceType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceType");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_GetCommuteDestinationRequest(this.requestId, this.clientId, this.customerId, this.deviceSerialNumber, this.zonedDateTime, this.personId, this.deviceType);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setClientId(String str) {
                    if (str != null) {
                        this.clientId = str;
                        return this;
                    }
                    throw new NullPointerException("Null clientId");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setCustomerId(String str) {
                    if (str != null) {
                        this.customerId = str;
                        return this;
                    }
                    throw new NullPointerException("Null customerId");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setDeviceSerialNumber(String str) {
                    if (str != null) {
                        this.deviceSerialNumber = str;
                        return this;
                    }
                    throw new NullPointerException("Null deviceSerialNumber");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setDeviceType(String str) {
                    if (str != null) {
                        this.deviceType = str;
                        return this;
                    }
                    throw new NullPointerException("Null deviceType");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setPersonId(@Nullable String str) {
                    this.personId = str;
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setRequestId(String str) {
                    if (str != null) {
                        this.requestId = str;
                        return this;
                    }
                    throw new NullPointerException("Null requestId");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest.Builder
                public GetCommuteDestinationRequest.Builder setZonedDateTime(String str) {
                    if (str != null) {
                        this.zonedDateTime = str;
                        return this;
                    }
                    throw new NullPointerException("Null zonedDateTime");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.requestId = str;
                    if (str2 != null) {
                        this.clientId = str2;
                        if (str3 != null) {
                            this.customerId = str3;
                            if (str4 != null) {
                                this.deviceSerialNumber = str4;
                                if (str5 != null) {
                                    this.zonedDateTime = str5;
                                    this.personId = str6;
                                    if (str7 != null) {
                                        this.deviceType = str7;
                                        return;
                                    }
                                    throw new NullPointerException("Null deviceType");
                                }
                                throw new NullPointerException("Null zonedDateTime");
                            }
                            throw new NullPointerException("Null deviceSerialNumber");
                        }
                        throw new NullPointerException("Null customerId");
                    }
                    throw new NullPointerException("Null clientId");
                }
                throw new NullPointerException("Null requestId");
            }

            public boolean equals(Object obj) {
                String str8;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof GetCommuteDestinationRequest)) {
                    return false;
                }
                GetCommuteDestinationRequest getCommuteDestinationRequest = (GetCommuteDestinationRequest) obj;
                return this.requestId.equals(getCommuteDestinationRequest.getRequestId()) && this.clientId.equals(getCommuteDestinationRequest.getClientId()) && this.customerId.equals(getCommuteDestinationRequest.getCustomerId()) && this.deviceSerialNumber.equals(getCommuteDestinationRequest.getDeviceSerialNumber()) && this.zonedDateTime.equals(getCommuteDestinationRequest.getZonedDateTime()) && ((str8 = this.personId) != null ? str8.equals(getCommuteDestinationRequest.getPersonId()) : getCommuteDestinationRequest.getPersonId() == null) && this.deviceType.equals(getCommuteDestinationRequest.getDeviceType());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            public String getClientId() {
                return this.clientId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            public String getCustomerId() {
                return this.customerId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            public String getDeviceSerialNumber() {
                return this.deviceSerialNumber;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            public String getDeviceType() {
                return this.deviceType;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            @Nullable
            public String getPersonId() {
                return this.personId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            public String getRequestId() {
                return this.requestId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationRequest
            public String getZonedDateTime() {
                return this.zonedDateTime;
            }

            public int hashCode() {
                int hashCode = (((((((((this.requestId.hashCode() ^ 1000003) * 1000003) ^ this.clientId.hashCode()) * 1000003) ^ this.customerId.hashCode()) * 1000003) ^ this.deviceSerialNumber.hashCode()) * 1000003) ^ this.zonedDateTime.hashCode()) * 1000003;
                String str8 = this.personId;
                return ((hashCode ^ (str8 == null ? 0 : str8.hashCode())) * 1000003) ^ this.deviceType.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCommuteDestinationRequest{requestId=");
                outline107.append(this.requestId);
                outline107.append(", clientId=");
                outline107.append(this.clientId);
                outline107.append(", customerId=");
                outline107.append(this.customerId);
                outline107.append(", deviceSerialNumber=");
                outline107.append(this.deviceSerialNumber);
                outline107.append(", zonedDateTime=");
                outline107.append(this.zonedDateTime);
                outline107.append(", personId=");
                outline107.append(this.personId);
                outline107.append(", deviceType=");
                return GeneratedOutlineSupport1.outline91(outline107, this.deviceType, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

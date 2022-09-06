package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest;
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
public final class AutoValue_SendCommuteNotificationRequest extends C$AutoValue_SendCommuteNotificationRequest {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<SendCommuteNotificationRequest> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("requestId", AuthorizationResponseParser.CLIENT_ID_STATE, "customerId", SpeakerVerificationProfileProvider.COLUMN_PERSON_ID, "commuteDestinationLabel");
            outline129.add("commuteDestinationType");
            outline129.add("deviceSerialNumber");
            outline129.add("deviceType");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_SendCommuteNotificationRequest.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public SendCommuteNotificationRequest mo8353read(JsonReader jsonReader) throws IOException {
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
            String str8 = null;
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
                    } else if (this.realFieldNames.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID).equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("commuteDestinationLabel").equals(nextName)) {
                        TypeAdapter<String> typeAdapter5 = this.string_adapter;
                        if (typeAdapter5 == null) {
                            typeAdapter5 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter5;
                        }
                        str5 = typeAdapter5.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("commuteDestinationType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter6 = this.string_adapter;
                        if (typeAdapter6 == null) {
                            typeAdapter6 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter6;
                        }
                        str6 = typeAdapter6.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("deviceSerialNumber").equals(nextName)) {
                        TypeAdapter<String> typeAdapter7 = this.string_adapter;
                        if (typeAdapter7 == null) {
                            typeAdapter7 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter7;
                        }
                        str7 = typeAdapter7.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("deviceType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter8 = this.string_adapter;
                        if (typeAdapter8 == null) {
                            typeAdapter8 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter8;
                        }
                        str8 = typeAdapter8.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_SendCommuteNotificationRequest(str, str2, str3, str4, str5, str6, str7, str8);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, SendCommuteNotificationRequest sendCommuteNotificationRequest) throws IOException {
            if (sendCommuteNotificationRequest == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("requestId"));
            if (sendCommuteNotificationRequest.getRequestId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, sendCommuteNotificationRequest.getRequestId());
            }
            jsonWriter.name(this.realFieldNames.get(AuthorizationResponseParser.CLIENT_ID_STATE));
            if (sendCommuteNotificationRequest.getClientId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, sendCommuteNotificationRequest.getClientId());
            }
            jsonWriter.name(this.realFieldNames.get("customerId"));
            if (sendCommuteNotificationRequest.getCustomerId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, sendCommuteNotificationRequest.getCustomerId());
            }
            jsonWriter.name(this.realFieldNames.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID));
            if (sendCommuteNotificationRequest.getPersonId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, sendCommuteNotificationRequest.getPersonId());
            }
            jsonWriter.name(this.realFieldNames.get("commuteDestinationLabel"));
            if (sendCommuteNotificationRequest.getCommuteDestinationLabel() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, sendCommuteNotificationRequest.getCommuteDestinationLabel());
            }
            jsonWriter.name(this.realFieldNames.get("commuteDestinationType"));
            if (sendCommuteNotificationRequest.getCommuteDestinationType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter6 = this.string_adapter;
                if (typeAdapter6 == null) {
                    typeAdapter6 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter6;
                }
                typeAdapter6.write(jsonWriter, sendCommuteNotificationRequest.getCommuteDestinationType());
            }
            jsonWriter.name(this.realFieldNames.get("deviceSerialNumber"));
            if (sendCommuteNotificationRequest.getDeviceSerialNumber() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter7 = this.string_adapter;
                if (typeAdapter7 == null) {
                    typeAdapter7 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter7;
                }
                typeAdapter7.write(jsonWriter, sendCommuteNotificationRequest.getDeviceSerialNumber());
            }
            jsonWriter.name(this.realFieldNames.get("deviceType"));
            if (sendCommuteNotificationRequest.getDeviceType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter8 = this.string_adapter;
                if (typeAdapter8 == null) {
                    typeAdapter8 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter8;
                }
                typeAdapter8.write(jsonWriter, sendCommuteNotificationRequest.getDeviceType());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_SendCommuteNotificationRequest(final String str, final String str2, final String str3, @Nullable final String str4, final String str5, final String str6, final String str7, final String str8) {
        new SendCommuteNotificationRequest(str, str2, str3, str4, str5, str6, str7, str8) { // from class: com.amazon.alexa.drive.navigation.traffic.$AutoValue_SendCommuteNotificationRequest
            private final String clientId;
            private final String commuteDestinationLabel;
            private final String commuteDestinationType;
            private final String customerId;
            private final String deviceSerialNumber;
            private final String deviceType;
            private final String personId;
            private final String requestId;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.traffic.$AutoValue_SendCommuteNotificationRequest$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends SendCommuteNotificationRequest.Builder {
                private String clientId;
                private String commuteDestinationLabel;
                private String commuteDestinationType;
                private String customerId;
                private String deviceSerialNumber;
                private String deviceType;
                private String personId;
                private String requestId;

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest build() {
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
                    if (this.commuteDestinationLabel == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " commuteDestinationLabel");
                    }
                    if (this.commuteDestinationType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " commuteDestinationType");
                    }
                    if (this.deviceSerialNumber == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceSerialNumber");
                    }
                    if (this.deviceType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " deviceType");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_SendCommuteNotificationRequest(this.requestId, this.clientId, this.customerId, this.personId, this.commuteDestinationLabel, this.commuteDestinationType, this.deviceSerialNumber, this.deviceType);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setClientId(String str) {
                    if (str != null) {
                        this.clientId = str;
                        return this;
                    }
                    throw new NullPointerException("Null clientId");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setCommuteDestinationLabel(String str) {
                    if (str != null) {
                        this.commuteDestinationLabel = str;
                        return this;
                    }
                    throw new NullPointerException("Null commuteDestinationLabel");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setCommuteDestinationType(String str) {
                    if (str != null) {
                        this.commuteDestinationType = str;
                        return this;
                    }
                    throw new NullPointerException("Null commuteDestinationType");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setCustomerId(String str) {
                    if (str != null) {
                        this.customerId = str;
                        return this;
                    }
                    throw new NullPointerException("Null customerId");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setDeviceSerialNumber(String str) {
                    if (str != null) {
                        this.deviceSerialNumber = str;
                        return this;
                    }
                    throw new NullPointerException("Null deviceSerialNumber");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setDeviceType(String str) {
                    if (str != null) {
                        this.deviceType = str;
                        return this;
                    }
                    throw new NullPointerException("Null deviceType");
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setPersonId(@Nullable String str) {
                    this.personId = str;
                    return this;
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest.Builder
                public SendCommuteNotificationRequest.Builder setRequestId(String str) {
                    if (str != null) {
                        this.requestId = str;
                        return this;
                    }
                    throw new NullPointerException("Null requestId");
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
                            this.personId = str4;
                            if (str5 != null) {
                                this.commuteDestinationLabel = str5;
                                if (str6 != null) {
                                    this.commuteDestinationType = str6;
                                    if (str7 != null) {
                                        this.deviceSerialNumber = str7;
                                        if (str8 != null) {
                                            this.deviceType = str8;
                                            return;
                                        }
                                        throw new NullPointerException("Null deviceType");
                                    }
                                    throw new NullPointerException("Null deviceSerialNumber");
                                }
                                throw new NullPointerException("Null commuteDestinationType");
                            }
                            throw new NullPointerException("Null commuteDestinationLabel");
                        }
                        throw new NullPointerException("Null customerId");
                    }
                    throw new NullPointerException("Null clientId");
                }
                throw new NullPointerException("Null requestId");
            }

            public boolean equals(Object obj) {
                String str9;
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof SendCommuteNotificationRequest)) {
                    return false;
                }
                SendCommuteNotificationRequest sendCommuteNotificationRequest = (SendCommuteNotificationRequest) obj;
                return this.requestId.equals(sendCommuteNotificationRequest.getRequestId()) && this.clientId.equals(sendCommuteNotificationRequest.getClientId()) && this.customerId.equals(sendCommuteNotificationRequest.getCustomerId()) && ((str9 = this.personId) != null ? str9.equals(sendCommuteNotificationRequest.getPersonId()) : sendCommuteNotificationRequest.getPersonId() == null) && this.commuteDestinationLabel.equals(sendCommuteNotificationRequest.getCommuteDestinationLabel()) && this.commuteDestinationType.equals(sendCommuteNotificationRequest.getCommuteDestinationType()) && this.deviceSerialNumber.equals(sendCommuteNotificationRequest.getDeviceSerialNumber()) && this.deviceType.equals(sendCommuteNotificationRequest.getDeviceType());
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getClientId() {
                return this.clientId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getCommuteDestinationLabel() {
                return this.commuteDestinationLabel;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getCommuteDestinationType() {
                return this.commuteDestinationType;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getCustomerId() {
                return this.customerId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getDeviceSerialNumber() {
                return this.deviceSerialNumber;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getDeviceType() {
                return this.deviceType;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            @Nullable
            public String getPersonId() {
                return this.personId;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            @Override // com.amazon.alexa.drive.navigation.traffic.SendCommuteNotificationRequest
            public String getRequestId() {
                return this.requestId;
            }

            public int hashCode() {
                int hashCode = (((((this.requestId.hashCode() ^ 1000003) * 1000003) ^ this.clientId.hashCode()) * 1000003) ^ this.customerId.hashCode()) * 1000003;
                String str9 = this.personId;
                return ((((((((hashCode ^ (str9 == null ? 0 : str9.hashCode())) * 1000003) ^ this.commuteDestinationLabel.hashCode()) * 1000003) ^ this.commuteDestinationType.hashCode()) * 1000003) ^ this.deviceSerialNumber.hashCode()) * 1000003) ^ this.deviceType.hashCode();
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SendCommuteNotificationRequest{requestId=");
                outline107.append(this.requestId);
                outline107.append(", clientId=");
                outline107.append(this.clientId);
                outline107.append(", customerId=");
                outline107.append(this.customerId);
                outline107.append(", personId=");
                outline107.append(this.personId);
                outline107.append(", commuteDestinationLabel=");
                outline107.append(this.commuteDestinationLabel);
                outline107.append(", commuteDestinationType=");
                outline107.append(this.commuteDestinationType);
                outline107.append(", deviceSerialNumber=");
                outline107.append(this.deviceSerialNumber);
                outline107.append(", deviceType=");
                return GeneratedOutlineSupport1.outline91(outline107, this.deviceType, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

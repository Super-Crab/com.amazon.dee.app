package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
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
public final class AutoValue_GetCommuteDestinationResponse extends C$AutoValue_GetCommuteDestinationResponse {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<GetCommuteDestinationResponse> {
        private final Gson gson;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList outline129 = GeneratedOutlineSupport1.outline129("correlationId", "customerId", "destinationAddressLabel", "destinationAddressType", SpeakerVerificationProfileProvider.COLUMN_PERSON_ID);
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_GetCommuteDestinationResponse.class, outline129, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public GetCommuteDestinationResponse mo8353read(JsonReader jsonReader) throws IOException {
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
                    if (this.realFieldNames.get("correlationId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("customerId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter2 = this.string_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter2;
                        }
                        str2 = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("destinationAddressLabel").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str3 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("destinationAddressType").equals(nextName)) {
                        TypeAdapter<String> typeAdapter4 = this.string_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter4;
                        }
                        str4 = typeAdapter4.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID).equals(nextName)) {
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
            return new AutoValue_GetCommuteDestinationResponse(str, str2, str3, str4, str5);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, GetCommuteDestinationResponse getCommuteDestinationResponse) throws IOException {
            if (getCommuteDestinationResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("correlationId"));
            if (getCommuteDestinationResponse.getCorrelationId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, getCommuteDestinationResponse.getCorrelationId());
            }
            jsonWriter.name(this.realFieldNames.get("customerId"));
            if (getCommuteDestinationResponse.getCustomerId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter2 = this.string_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, getCommuteDestinationResponse.getCustomerId());
            }
            jsonWriter.name(this.realFieldNames.get("destinationAddressLabel"));
            if (getCommuteDestinationResponse.getDestinationAddressLabel() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, getCommuteDestinationResponse.getDestinationAddressLabel());
            }
            jsonWriter.name(this.realFieldNames.get("destinationAddressType"));
            if (getCommuteDestinationResponse.getDestinationAddressType() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter4 = this.string_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, getCommuteDestinationResponse.getDestinationAddressType());
            }
            jsonWriter.name(this.realFieldNames.get(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID));
            if (getCommuteDestinationResponse.getPersonId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter5 = this.string_adapter;
                if (typeAdapter5 == null) {
                    typeAdapter5 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter5;
                }
                typeAdapter5.write(jsonWriter, getCommuteDestinationResponse.getPersonId());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_GetCommuteDestinationResponse(final String str, final String str2, final String str3, final String str4, @Nullable final String str5) {
        new GetCommuteDestinationResponse(str, str2, str3, str4, str5) { // from class: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDestinationResponse
            private final String correlationId;
            private final String customerId;
            private final String destinationAddressLabel;
            private final String destinationAddressType;
            private final String personId;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDestinationResponse$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends GetCommuteDestinationResponse.Builder {
                private String correlationId;
                private String customerId;
                private String destinationAddressLabel;
                private String destinationAddressType;
                private String personId;

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse.Builder
                GetCommuteDestinationResponse build() {
                    String str = "";
                    if (this.correlationId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " correlationId");
                    }
                    if (this.customerId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " customerId");
                    }
                    if (this.destinationAddressLabel == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " destinationAddressLabel");
                    }
                    if (this.destinationAddressType == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " destinationAddressType");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_GetCommuteDestinationResponse(this.correlationId, this.customerId, this.destinationAddressLabel, this.destinationAddressType, this.personId);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse.Builder
                GetCommuteDestinationResponse.Builder setCorrelationId(String str) {
                    if (str != null) {
                        this.correlationId = str;
                        return this;
                    }
                    throw new NullPointerException("Null correlationId");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse.Builder
                GetCommuteDestinationResponse.Builder setCustomerId(String str) {
                    if (str != null) {
                        this.customerId = str;
                        return this;
                    }
                    throw new NullPointerException("Null customerId");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse.Builder
                GetCommuteDestinationResponse.Builder setDestinationAddressLabel(String str) {
                    if (str != null) {
                        this.destinationAddressLabel = str;
                        return this;
                    }
                    throw new NullPointerException("Null destinationAddressLabel");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse.Builder
                GetCommuteDestinationResponse.Builder setDestinationAddressType(String str) {
                    if (str != null) {
                        this.destinationAddressType = str;
                        return this;
                    }
                    throw new NullPointerException("Null destinationAddressType");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse.Builder
                GetCommuteDestinationResponse.Builder setPersonId(@Nullable String str) {
                    this.personId = str;
                    return this;
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.correlationId = str;
                    if (str2 != null) {
                        this.customerId = str2;
                        if (str3 != null) {
                            this.destinationAddressLabel = str3;
                            if (str4 != null) {
                                this.destinationAddressType = str4;
                                this.personId = str5;
                                return;
                            }
                            throw new NullPointerException("Null destinationAddressType");
                        }
                        throw new NullPointerException("Null destinationAddressLabel");
                    }
                    throw new NullPointerException("Null customerId");
                }
                throw new NullPointerException("Null correlationId");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof GetCommuteDestinationResponse)) {
                    return false;
                }
                GetCommuteDestinationResponse getCommuteDestinationResponse = (GetCommuteDestinationResponse) obj;
                if (this.correlationId.equals(getCommuteDestinationResponse.getCorrelationId()) && this.customerId.equals(getCommuteDestinationResponse.getCustomerId()) && this.destinationAddressLabel.equals(getCommuteDestinationResponse.getDestinationAddressLabel()) && this.destinationAddressType.equals(getCommuteDestinationResponse.getDestinationAddressType())) {
                    String str6 = this.personId;
                    if (str6 == null) {
                        if (getCommuteDestinationResponse.getPersonId() == null) {
                            return true;
                        }
                    } else if (str6.equals(getCommuteDestinationResponse.getPersonId())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse
            public String getCorrelationId() {
                return this.correlationId;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse
            public String getCustomerId() {
                return this.customerId;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse
            public String getDestinationAddressLabel() {
                return this.destinationAddressLabel;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse
            public String getDestinationAddressType() {
                return this.destinationAddressType;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDestinationResponse
            @Nullable
            public String getPersonId() {
                return this.personId;
            }

            public int hashCode() {
                int hashCode = (((((((this.correlationId.hashCode() ^ 1000003) * 1000003) ^ this.customerId.hashCode()) * 1000003) ^ this.destinationAddressLabel.hashCode()) * 1000003) ^ this.destinationAddressType.hashCode()) * 1000003;
                String str6 = this.personId;
                return hashCode ^ (str6 == null ? 0 : str6.hashCode());
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCommuteDestinationResponse{correlationId=");
                outline107.append(this.correlationId);
                outline107.append(", customerId=");
                outline107.append(this.customerId);
                outline107.append(", destinationAddressLabel=");
                outline107.append(this.destinationAddressLabel);
                outline107.append(", destinationAddressType=");
                outline107.append(this.destinationAddressType);
                outline107.append(", personId=");
                return GeneratedOutlineSupport1.outline91(outline107, this.personId, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
            }
        };
    }
}

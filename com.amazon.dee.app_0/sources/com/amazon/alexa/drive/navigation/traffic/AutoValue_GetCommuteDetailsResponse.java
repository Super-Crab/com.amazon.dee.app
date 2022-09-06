package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse;
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
public final class AutoValue_GetCommuteDetailsResponse extends C$AutoValue_GetCommuteDetailsResponse {

    /* loaded from: classes7.dex */
    public static final class GsonTypeAdapter extends TypeAdapter<GetCommuteDetailsResponse> {
        private final Gson gson;
        private volatile TypeAdapter<Integer> integer_adapter;
        private final Map<String, String> realFieldNames;
        private volatile TypeAdapter<String> string_adapter;

        public GsonTypeAdapter(Gson gson) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("customerId");
            arrayList.add("estimatedTravelTimeInSeconds");
            arrayList.add("trafficCondition");
            arrayList.add("estimatedDelayInSeconds");
            this.gson = gson;
            this.realFieldNames = Util.renameFields(C$AutoValue_GetCommuteDetailsResponse.class, arrayList, gson.fieldNamingStrategy());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.google.gson.TypeAdapter
        /* renamed from: read */
        public GetCommuteDetailsResponse mo8353read(JsonReader jsonReader) throws IOException {
            String str = null;
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }
            jsonReader.beginObject();
            Integer num = null;
            String str2 = null;
            Integer num2 = null;
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    nextName.hashCode();
                    if (this.realFieldNames.get("customerId").equals(nextName)) {
                        TypeAdapter<String> typeAdapter = this.string_adapter;
                        if (typeAdapter == null) {
                            typeAdapter = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter;
                        }
                        str = typeAdapter.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("estimatedTravelTimeInSeconds").equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter2 = this.integer_adapter;
                        if (typeAdapter2 == null) {
                            typeAdapter2 = this.gson.getAdapter(Integer.class);
                            this.integer_adapter = typeAdapter2;
                        }
                        num = typeAdapter2.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("trafficCondition").equals(nextName)) {
                        TypeAdapter<String> typeAdapter3 = this.string_adapter;
                        if (typeAdapter3 == null) {
                            typeAdapter3 = this.gson.getAdapter(String.class);
                            this.string_adapter = typeAdapter3;
                        }
                        str2 = typeAdapter3.mo8353read(jsonReader);
                    } else if (this.realFieldNames.get("estimatedDelayInSeconds").equals(nextName)) {
                        TypeAdapter<Integer> typeAdapter4 = this.integer_adapter;
                        if (typeAdapter4 == null) {
                            typeAdapter4 = this.gson.getAdapter(Integer.class);
                            this.integer_adapter = typeAdapter4;
                        }
                        num2 = typeAdapter4.mo8353read(jsonReader);
                    } else {
                        jsonReader.skipValue();
                    }
                }
            }
            jsonReader.endObject();
            return new AutoValue_GetCommuteDetailsResponse(str, num, str2, num2);
        }

        @Override // com.google.gson.TypeAdapter
        public void write(JsonWriter jsonWriter, GetCommuteDetailsResponse getCommuteDetailsResponse) throws IOException {
            if (getCommuteDetailsResponse == null) {
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginObject();
            jsonWriter.name(this.realFieldNames.get("customerId"));
            if (getCommuteDetailsResponse.getCustomerId() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter = this.string_adapter;
                if (typeAdapter == null) {
                    typeAdapter = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter;
                }
                typeAdapter.write(jsonWriter, getCommuteDetailsResponse.getCustomerId());
            }
            jsonWriter.name(this.realFieldNames.get("estimatedTravelTimeInSeconds"));
            if (getCommuteDetailsResponse.getEstimatedTravelTimeInSeconds() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter2 = this.integer_adapter;
                if (typeAdapter2 == null) {
                    typeAdapter2 = this.gson.getAdapter(Integer.class);
                    this.integer_adapter = typeAdapter2;
                }
                typeAdapter2.write(jsonWriter, getCommuteDetailsResponse.getEstimatedTravelTimeInSeconds());
            }
            jsonWriter.name(this.realFieldNames.get("trafficCondition"));
            if (getCommuteDetailsResponse.getTrafficCondition() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<String> typeAdapter3 = this.string_adapter;
                if (typeAdapter3 == null) {
                    typeAdapter3 = this.gson.getAdapter(String.class);
                    this.string_adapter = typeAdapter3;
                }
                typeAdapter3.write(jsonWriter, getCommuteDetailsResponse.getTrafficCondition());
            }
            jsonWriter.name(this.realFieldNames.get("estimatedDelayInSeconds"));
            if (getCommuteDetailsResponse.getEstimatedDelayInSeconds() == null) {
                jsonWriter.nullValue();
            } else {
                TypeAdapter<Integer> typeAdapter4 = this.integer_adapter;
                if (typeAdapter4 == null) {
                    typeAdapter4 = this.gson.getAdapter(Integer.class);
                    this.integer_adapter = typeAdapter4;
                }
                typeAdapter4.write(jsonWriter, getCommuteDetailsResponse.getEstimatedDelayInSeconds());
            }
            jsonWriter.endObject();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_GetCommuteDetailsResponse(final String str, final Integer num, final String str2, @Nullable final Integer num2) {
        new GetCommuteDetailsResponse(str, num, str2, num2) { // from class: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDetailsResponse
            private final String customerId;
            private final Integer estimatedDelayInSeconds;
            private final Integer estimatedTravelTimeInSeconds;
            private final String trafficCondition;

            /* JADX INFO: Access modifiers changed from: package-private */
            /* renamed from: com.amazon.alexa.drive.navigation.traffic.$AutoValue_GetCommuteDetailsResponse$Builder */
            /* loaded from: classes7.dex */
            public static final class Builder extends GetCommuteDetailsResponse.Builder {
                private String customerId;
                private Integer estimatedDelayInSeconds;
                private Integer estimatedTravelTimeInSeconds;
                private String trafficCondition;

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse.Builder
                GetCommuteDetailsResponse build() {
                    String str = "";
                    if (this.customerId == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " customerId");
                    }
                    if (this.estimatedTravelTimeInSeconds == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " estimatedTravelTimeInSeconds");
                    }
                    if (this.trafficCondition == null) {
                        str = GeneratedOutlineSupport1.outline72(str, " trafficCondition");
                    }
                    if (str.isEmpty()) {
                        return new AutoValue_GetCommuteDetailsResponse(this.customerId, this.estimatedTravelTimeInSeconds, this.trafficCondition, this.estimatedDelayInSeconds);
                    }
                    throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Missing required properties:", str));
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse.Builder
                GetCommuteDetailsResponse.Builder setCustomerId(String str) {
                    if (str != null) {
                        this.customerId = str;
                        return this;
                    }
                    throw new NullPointerException("Null customerId");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse.Builder
                GetCommuteDetailsResponse.Builder setEstimatedDelayInSeconds(@Nullable Integer num) {
                    this.estimatedDelayInSeconds = num;
                    return this;
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse.Builder
                GetCommuteDetailsResponse.Builder setEstimatedTravelTimeInSeconds(Integer num) {
                    if (num != null) {
                        this.estimatedTravelTimeInSeconds = num;
                        return this;
                    }
                    throw new NullPointerException("Null estimatedTravelTimeInSeconds");
                }

                @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse.Builder
                GetCommuteDetailsResponse.Builder setTrafficCondition(String str) {
                    if (str != null) {
                        this.trafficCondition = str;
                        return this;
                    }
                    throw new NullPointerException("Null trafficCondition");
                }
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                if (str != null) {
                    this.customerId = str;
                    if (num != null) {
                        this.estimatedTravelTimeInSeconds = num;
                        if (str2 != null) {
                            this.trafficCondition = str2;
                            this.estimatedDelayInSeconds = num2;
                            return;
                        }
                        throw new NullPointerException("Null trafficCondition");
                    }
                    throw new NullPointerException("Null estimatedTravelTimeInSeconds");
                }
                throw new NullPointerException("Null customerId");
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (!(obj instanceof GetCommuteDetailsResponse)) {
                    return false;
                }
                GetCommuteDetailsResponse getCommuteDetailsResponse = (GetCommuteDetailsResponse) obj;
                if (this.customerId.equals(getCommuteDetailsResponse.getCustomerId()) && this.estimatedTravelTimeInSeconds.equals(getCommuteDetailsResponse.getEstimatedTravelTimeInSeconds()) && this.trafficCondition.equals(getCommuteDetailsResponse.getTrafficCondition())) {
                    Integer num3 = this.estimatedDelayInSeconds;
                    if (num3 == null) {
                        if (getCommuteDetailsResponse.getEstimatedDelayInSeconds() == null) {
                            return true;
                        }
                    } else if (num3.equals(getCommuteDetailsResponse.getEstimatedDelayInSeconds())) {
                        return true;
                    }
                }
                return false;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse
            public String getCustomerId() {
                return this.customerId;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse
            @Nullable
            public Integer getEstimatedDelayInSeconds() {
                return this.estimatedDelayInSeconds;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse
            public Integer getEstimatedTravelTimeInSeconds() {
                return this.estimatedTravelTimeInSeconds;
            }

            @Override // com.amazon.alexa.drive.navigation.traffic.GetCommuteDetailsResponse
            public String getTrafficCondition() {
                return this.trafficCondition;
            }

            public int hashCode() {
                int hashCode = (((((this.customerId.hashCode() ^ 1000003) * 1000003) ^ this.estimatedTravelTimeInSeconds.hashCode()) * 1000003) ^ this.trafficCondition.hashCode()) * 1000003;
                Integer num3 = this.estimatedDelayInSeconds;
                return hashCode ^ (num3 == null ? 0 : num3.hashCode());
            }

            public String toString() {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetCommuteDetailsResponse{customerId=");
                outline107.append(this.customerId);
                outline107.append(", estimatedTravelTimeInSeconds=");
                outline107.append(this.estimatedTravelTimeInSeconds);
                outline107.append(", trafficCondition=");
                outline107.append(this.trafficCondition);
                outline107.append(", estimatedDelayInSeconds=");
                outline107.append(this.estimatedDelayInSeconds);
                outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
                return outline107.toString();
            }
        };
    }
}

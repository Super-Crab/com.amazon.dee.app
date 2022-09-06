package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.drive.navigation.AutoValueAdapterFactory;
import com.amazon.alexa.drive.navigation.traffic.AutoValue_GetCommuteDestinationResponse;
import com.amazon.alexa.drive.navigation.traffic.C$AutoValue_GetCommuteDestinationResponse;
import com.amazon.alexa.wakeword.speakerverification.profile.SpeakerVerificationProfileProvider;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes7.dex */
public abstract class GetCommuteDestinationResponse implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();

    /* JADX INFO: Access modifiers changed from: package-private */
    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        abstract GetCommuteDestinationResponse build();

        abstract Builder setCorrelationId(String str);

        abstract Builder setCustomerId(String str);

        abstract Builder setDestinationAddressLabel(String str);

        abstract Builder setDestinationAddressType(String str);

        abstract Builder setPersonId(String str);
    }

    /* loaded from: classes7.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<GetCommuteDestinationResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public GetCommuteDestinationResponse mo1239create(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString("correlationId");
            String string2 = jSONObject.getString("customerId");
            String string3 = jSONObject.has(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID) ? jSONObject.getString(SpeakerVerificationProfileProvider.COLUMN_PERSON_ID) : null;
            JSONObject jSONObject2 = jSONObject.getJSONObject("commuteDestination");
            return GetCommuteDestinationResponse.builder().setCorrelationId(string).setCustomerId(string2).setDestinationAddressLabel(jSONObject2.getString("label")).setDestinationAddressType(jSONObject2.getString("type")).setPersonId(string3).build();
        }
    }

    static Builder builder() {
        return new C$AutoValue_GetCommuteDestinationResponse.Builder();
    }

    public static TypeAdapter<GetCommuteDestinationResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetCommuteDestinationResponse.GsonTypeAdapter(gson);
    }

    public abstract String getCorrelationId();

    public abstract String getCustomerId();

    public abstract String getDestinationAddressLabel();

    public abstract String getDestinationAddressType();

    @Nullable
    public abstract String getPersonId();

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }
}

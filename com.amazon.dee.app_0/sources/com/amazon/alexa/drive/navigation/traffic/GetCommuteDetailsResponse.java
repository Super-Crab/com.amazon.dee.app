package com.amazon.alexa.drive.navigation.traffic;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.drive.navigation.AutoValueAdapterFactory;
import com.amazon.alexa.drive.navigation.traffic.AutoValue_GetCommuteDetailsResponse;
import com.amazon.alexa.drive.navigation.traffic.C$AutoValue_GetCommuteDetailsResponse;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes7.dex */
public abstract class GetCommuteDetailsResponse implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();

    /* JADX INFO: Access modifiers changed from: package-private */
    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        abstract GetCommuteDetailsResponse build();

        abstract Builder setCustomerId(String str);

        abstract Builder setEstimatedDelayInSeconds(Integer num);

        abstract Builder setEstimatedTravelTimeInSeconds(Integer num);

        abstract Builder setTrafficCondition(String str);
    }

    /* loaded from: classes7.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<GetCommuteDetailsResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public GetCommuteDetailsResponse mo1239create(JSONObject jSONObject) throws JSONException {
            String string = jSONObject.getString("customerId");
            Integer valueOf = Integer.valueOf(jSONObject.getInt("estimatedTravelTimeInSeconds"));
            return GetCommuteDetailsResponse.builder().setCustomerId(string).setEstimatedTravelTimeInSeconds(valueOf).setTrafficCondition(jSONObject.getString("trafficCondition")).setEstimatedDelayInSeconds(jSONObject.has("estimatedDelayInSeconds") ? Integer.valueOf(jSONObject.getInt("estimatedDelayInSeconds")) : null).build();
        }
    }

    static Builder builder() {
        return new C$AutoValue_GetCommuteDetailsResponse.Builder();
    }

    public static TypeAdapter<GetCommuteDetailsResponse> typeAdapter(Gson gson) {
        return new AutoValue_GetCommuteDetailsResponse.GsonTypeAdapter(gson);
    }

    public abstract String getCustomerId();

    @Nullable
    public abstract Integer getEstimatedDelayInSeconds();

    public abstract Integer getEstimatedTravelTimeInSeconds();

    public abstract String getTrafficCondition();

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }
}

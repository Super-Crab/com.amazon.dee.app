package com.amazon.alexa.drive.navigation.location;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.drive.navigation.location.AutoValue_ReportLocationsResponse;
import com.amazon.alexa.drive.navigation.location.C$AutoValue_ReportLocationsResponse;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes7.dex */
public abstract class ReportLocationsResponse implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();

    /* JADX INFO: Access modifiers changed from: package-private */
    @AutoValue.Builder
    /* loaded from: classes7.dex */
    public static abstract class Builder {
        abstract ReportLocationsResponse build();

        abstract Builder setMessage(String str);

        abstract Builder setType(String str);
    }

    /* loaded from: classes7.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<ReportLocationsResponse> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public ReportLocationsResponse mo1239create(JSONObject jSONObject) throws JSONException {
            String str = null;
            String string = jSONObject.has("type") ? jSONObject.getString("type") : null;
            if (jSONObject.has("message")) {
                str = jSONObject.getString("message");
            }
            return ReportLocationsResponse.builder().setType(string).setMessage(str).build();
        }
    }

    static Builder builder() {
        return new C$AutoValue_ReportLocationsResponse.Builder();
    }

    public static TypeAdapter<ReportLocationsResponse> typeAdapter(Gson gson) {
        return new AutoValue_ReportLocationsResponse.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getMessage();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getType();

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }
}

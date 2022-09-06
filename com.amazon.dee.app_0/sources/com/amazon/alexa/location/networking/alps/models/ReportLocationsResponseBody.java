package com.amazon.alexa.location.networking.alps.models;

import androidx.annotation.Nullable;
import com.amazon.alexa.location.networking.alps.models.AutoValue_ReportLocationsResponseBody;
import com.amazon.alexa.location.networking.alps.models.C$AutoValue_ReportLocationsResponseBody;
import com.amazon.alexa.location.networking.internal.JsonObjectSerializable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes9.dex */
public abstract class ReportLocationsResponseBody implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();

    /* JADX INFO: Access modifiers changed from: package-private */
    @AutoValue.Builder
    /* loaded from: classes9.dex */
    public static abstract class Builder {
        abstract ReportLocationsResponseBody build();

        abstract Builder setMessage(String str);

        abstract Builder setType(String str);
    }

    /* loaded from: classes9.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<ReportLocationsResponseBody> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.location.networking.internal.JsonObjectSerializable.Factory
        /* renamed from: create */
        public ReportLocationsResponseBody mo1662create(JSONObject jSONObject) throws JSONException {
            String str = null;
            String string = jSONObject.has("type") ? jSONObject.getString("type") : null;
            if (jSONObject.has("message")) {
                str = jSONObject.getString("message");
            }
            return ReportLocationsResponseBody.builder().setType(string).setMessage(str).build();
        }
    }

    static Builder builder() {
        return new C$AutoValue_ReportLocationsResponseBody.Builder();
    }

    public static TypeAdapter<ReportLocationsResponseBody> typeAdapter(Gson gson) {
        return new AutoValue_ReportLocationsResponseBody.GsonTypeAdapter(gson);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getMessage();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public abstract String getType();

    @Override // com.amazon.alexa.location.networking.internal.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }
}

package com.amazon.alexa.location.networking.alps.models;

import com.amazon.alexa.location.networking.alps.models.AutoValue_ReportLocationsRequestBody;
import com.amazon.alexa.location.networking.internal.JsonObjectSerializable;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes9.dex */
public abstract class ReportLocationsRequestBody implements JsonObjectSerializable {
    public static ReportLocationsRequestBody create(List<TrackableDevicesLocation> list) {
        return new AutoValue_ReportLocationsRequestBody(list);
    }

    public static TypeAdapter<ReportLocationsRequestBody> typeAdapter(Gson gson) {
        return new AutoValue_ReportLocationsRequestBody.GsonTypeAdapter(gson);
    }

    public abstract List<TrackableDevicesLocation> getLocations();

    @Override // com.amazon.alexa.location.networking.internal.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }
}

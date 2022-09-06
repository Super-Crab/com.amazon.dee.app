package com.amazon.alexa.accessorykit.findmy.reporter;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessorykit.findmy.reporter.AutoValue_ReportLocationsRequest;
import com.amazon.alexa.accessorykit.utils.AutoValueAdapterFactory;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
@AutoValue
/* loaded from: classes6.dex */
public abstract class ReportLocationsRequest implements JsonObjectSerializable {
    public static ReportLocationsRequest create(List<TrackableDevicesLocation> list) {
        return new AutoValue_ReportLocationsRequest(list);
    }

    public static TypeAdapter<ReportLocationsRequest> typeAdapter(Gson gson) {
        return new AutoValue_ReportLocationsRequest.GsonTypeAdapter(gson);
    }

    public abstract List<TrackableDevicesLocation> getLocations();

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject(new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().toJson(this));
    }
}

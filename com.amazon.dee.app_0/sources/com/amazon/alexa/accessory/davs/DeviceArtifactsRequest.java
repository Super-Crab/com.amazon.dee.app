package com.amazon.alexa.accessory.davs;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class DeviceArtifactsRequest implements JsonObjectSerializable {
    private static final String ARTIFACT_FILTERS_JSON_KEY = "filters";
    private static final String ARTIFACT_KEY_JSON_KEY = "artifactKey";
    private static final String ARTIFACT_TYPE_JSON_KEY = "artifactType";
    public static final Factory FACTORY = new Factory();
    private static final Type MAP_TYPE = new TypeToken<Map<String, List<String>>>() { // from class: com.amazon.alexa.accessory.davs.DeviceArtifactsRequest.1
    }.getType();
    private final String artifactKey;
    private final String artifactType;
    private final Map<String, List<String>> filters;
    private final Gson gson = new Gson();

    /* loaded from: classes.dex */
    public static final class Builder {
        String artifactKey;
        String artifactType;
        Map<String, List<String>> filters;

        public Builder artifactKey(String str) {
            this.artifactKey = str;
            return this;
        }

        public Builder artifactType(String str) {
            this.artifactType = str;
            return this;
        }

        public DeviceArtifactsRequest build() {
            return new DeviceArtifactsRequest(this);
        }

        public Builder filters(Map<String, List<String>> map) {
            this.filters = map;
            return this;
        }
    }

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DeviceArtifactsRequest> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DeviceArtifactsRequest mo1239create(JSONObject jSONObject) throws JSONException {
            return new Builder().artifactType(jSONObject.getString(DeviceArtifactsRequest.ARTIFACT_TYPE_JSON_KEY)).artifactKey(jSONObject.getString(DeviceArtifactsRequest.ARTIFACT_KEY_JSON_KEY)).filters((Map) new Gson().fromJson(jSONObject.get(DeviceArtifactsRequest.ARTIFACT_FILTERS_JSON_KEY).toString(), DeviceArtifactsRequest.MAP_TYPE)).build();
        }
    }

    DeviceArtifactsRequest(Builder builder) {
        this.artifactType = builder.artifactType;
        this.artifactKey = builder.artifactKey;
        this.filters = builder.filters;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DeviceArtifactsRequest.class != obj.getClass()) {
            return false;
        }
        DeviceArtifactsRequest deviceArtifactsRequest = (DeviceArtifactsRequest) obj;
        return Objects.equals(this.artifactType, deviceArtifactsRequest.artifactType) && Objects.equals(this.artifactKey, deviceArtifactsRequest.artifactKey) && Objects.equals(this.filters, deviceArtifactsRequest.filters);
    }

    public String getArtifactKey() {
        return this.artifactKey;
    }

    public String getArtifactType() {
        return this.artifactType;
    }

    public Map<String, List<String>> getFilters() {
        return this.filters;
    }

    public int hashCode() {
        return Objects.hash(this.artifactType, this.artifactKey, this.filters);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(ARTIFACT_TYPE_JSON_KEY, this.artifactType).put(ARTIFACT_KEY_JSON_KEY, this.artifactKey).put(ARTIFACT_FILTERS_JSON_KEY, new JSONObject(this.gson.toJson(this.filters, MAP_TYPE)));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DeviceArtifactsRequest{artifactType=");
        outline107.append(this.artifactType);
        outline107.append(", artifactKey=");
        outline107.append(this.artifactKey);
        outline107.append(", filters=");
        outline107.append(this.filters);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}

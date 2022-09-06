package com.amazon.alexa.accessory.davs.i18n;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.davs.DavsI18nConfig;
import com.amazon.alexa.accessory.davs.DeviceArtifactsRequest;
import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DavsI18nConfigData implements JsonObjectSerializable {
    public static final Factory FACTORY = new Factory();
    private static final String I18N_CONFIG_REQUEST_KEY = "i18nConfigRequest";
    private static final String I18N_CONFIG_RESPONSE_KEY = "i18nConfigResponse";
    private static final String I18N_CONFIG_TIME_LAST_UPDATED_KEY = "i18nConfigLastUpdatedTime";
    private final DavsI18nConfig davsI18nConfig;
    private final DeviceArtifactsRequest deviceArtifactsRequest;
    private final long timeLastUpdated;

    /* loaded from: classes.dex */
    public static final class Factory implements JsonObjectSerializable.Factory<DavsI18nConfigData> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable.Factory
        /* renamed from: create */
        public DavsI18nConfigData mo1239create(JSONObject jSONObject) throws JSONException {
            return new DavsI18nConfigData(DeviceArtifactsRequest.FACTORY.mo1239create(jSONObject.getJSONObject(DavsI18nConfigData.I18N_CONFIG_REQUEST_KEY)), DavsI18nConfig.FACTORY.mo1239create(jSONObject.getJSONObject(DavsI18nConfigData.I18N_CONFIG_RESPONSE_KEY)), jSONObject.getLong(DavsI18nConfigData.I18N_CONFIG_TIME_LAST_UPDATED_KEY));
        }
    }

    public DavsI18nConfigData(DeviceArtifactsRequest deviceArtifactsRequest, DavsI18nConfig davsI18nConfig, long j) {
        Preconditions.notNull(deviceArtifactsRequest, "deviceArtifactsRequest");
        Preconditions.notNull(davsI18nConfig, "davsI18nConfig");
        this.deviceArtifactsRequest = deviceArtifactsRequest;
        this.davsI18nConfig = davsI18nConfig;
        this.timeLastUpdated = j;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && DavsI18nConfigData.class == obj.getClass()) {
            return this.deviceArtifactsRequest.equals(((DavsI18nConfigData) obj).deviceArtifactsRequest);
        }
        return false;
    }

    public DavsI18nConfig getDavsi18nConfig() {
        return this.davsI18nConfig;
    }

    public DeviceArtifactsRequest getDeviceArtifactsRequest() {
        return this.deviceArtifactsRequest;
    }

    public long getTimeLastUpdated() {
        return this.timeLastUpdated;
    }

    public int hashCode() {
        return Objects.hash(this.deviceArtifactsRequest, this.davsI18nConfig);
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put(I18N_CONFIG_REQUEST_KEY, this.deviceArtifactsRequest.toJsonObject()).put(I18N_CONFIG_RESPONSE_KEY, this.davsI18nConfig.toJsonObject()).put(I18N_CONFIG_TIME_LAST_UPDATED_KEY, this.timeLastUpdated);
    }
}

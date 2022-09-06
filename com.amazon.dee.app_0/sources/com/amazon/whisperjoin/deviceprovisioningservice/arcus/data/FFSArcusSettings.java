package com.amazon.whisperjoin.deviceprovisioningservice.arcus.data;

import android.content.SharedPreferences;
import android.os.Bundle;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class FFSArcusSettings {
    private static final String JSON_STRING_KEY = "FFSArcusSettingsJSON";
    private static final String TAG = "FFSArcusSettings";
    private final ArcusSettings mArcusSettings;
    private final FFRSettings mFFRSettings;
    private final ThrottleSettings mThrottleSettings;
    private final ZeroTouchProvisioningSettings mZeroTouchProvisioningSettings;
    private final ZigbeeArcusSettings mZigbeeArcusSettings;

    public FFSArcusSettings(ZigbeeArcusSettings zigbeeArcusSettings, ZeroTouchProvisioningSettings zeroTouchProvisioningSettings, ThrottleSettings throttleSettings, FFRSettings fFRSettings, String str) {
        this.mZigbeeArcusSettings = zigbeeArcusSettings;
        this.mZeroTouchProvisioningSettings = zeroTouchProvisioningSettings;
        this.mThrottleSettings = throttleSettings;
        this.mFFRSettings = fFRSettings;
        this.mArcusSettings = new ArcusSettings(str);
    }

    public static FFSArcusSettings fromJSONObject(JSONObject jSONObject) throws JSONException {
        return new FFSArcusSettings(ZigbeeArcusSettings.fromJSONObject(getJsonObjectOrEmptyObject(jSONObject, "zigbeeSettings")), ZeroTouchProvisioningSettings.fromJSONObject(getJsonObjectOrEmptyObject(jSONObject, "zeroTouchProvisioningSettings")), ThrottleSettings.fromJSONObject(getJsonObjectOrEmptyObject(jSONObject, "throttleSettings")), FFRSettings.fromJSONObject(getJsonObjectOrEmptyObject(jSONObject, "frustrationFreeReconnectSettings")), ArcusSettings.fromJSONObject(getJsonObjectOrEmptyObject(jSONObject, "arcusSettings")).getSyncPeriod());
    }

    private static JSONObject getJsonObjectOrEmptyObject(JSONObject jSONObject, String str) {
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        return optJSONObject != null ? optJSONObject : new JSONObject();
    }

    public static FFSArcusSettings readFromBundle(Bundle bundle) {
        try {
            if (bundle.containsKey(JSON_STRING_KEY)) {
                return fromJSONObject(new JSONObject(bundle.getString(JSON_STRING_KEY)));
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static FFSArcusSettings readFromSharedPreferences(SharedPreferences sharedPreferences) {
        try {
            if (sharedPreferences.contains(JSON_STRING_KEY)) {
                return fromJSONObject(new JSONObject(sharedPreferences.getString(JSON_STRING_KEY, "{}")));
            }
            return null;
        } catch (JSONException unused) {
            return null;
        }
    }

    public static void writeToBundle(FFSArcusSettings fFSArcusSettings, Bundle bundle) {
        try {
            bundle.putString(JSON_STRING_KEY, fFSArcusSettings.toJSONObject().toString());
        } catch (JSONException e) {
            throw new RuntimeException("Failed writing to bundle", e);
        }
    }

    public static void writeToSharedPreferences(FFSArcusSettings fFSArcusSettings, SharedPreferences sharedPreferences) {
        try {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString(JSON_STRING_KEY, fFSArcusSettings.toJSONObject().toString());
            edit.apply();
        } catch (JSONException e) {
            throw new RuntimeException("Failed to persist FFSArcusSettings", e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FFSArcusSettings)) {
            return false;
        }
        FFSArcusSettings fFSArcusSettings = (FFSArcusSettings) obj;
        return Objects.equal(this.mZigbeeArcusSettings, fFSArcusSettings.mZigbeeArcusSettings) && Objects.equal(this.mZeroTouchProvisioningSettings, fFSArcusSettings.mZeroTouchProvisioningSettings) && Objects.equal(this.mThrottleSettings, fFSArcusSettings.mThrottleSettings) && Objects.equal(this.mFFRSettings, fFSArcusSettings.mFFRSettings) && Objects.equal(this.mArcusSettings, fFSArcusSettings.mArcusSettings);
    }

    public String getArcusSyncPeriod() {
        return this.mArcusSettings.getSyncPeriod();
    }

    public FFRSettings getFFRSettings() {
        return this.mFFRSettings;
    }

    public ThrottleSettings getThrottleSettings() {
        return this.mThrottleSettings;
    }

    public ZeroTouchProvisioningSettings getZeroTouchProvisioningSettings() {
        return this.mZeroTouchProvisioningSettings;
    }

    public ZigbeeArcusSettings getZigbeeArcusSettings() {
        return this.mZigbeeArcusSettings;
    }

    public int hashCode() {
        return Objects.hashCode(this.mZigbeeArcusSettings, this.mZeroTouchProvisioningSettings, this.mThrottleSettings, this.mArcusSettings, this.mFFRSettings);
    }

    public JSONObject toJSONObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("arcusSettings", this.mArcusSettings.toJSONObject());
        jSONObject.put("zigbeeSettings", this.mZigbeeArcusSettings.toJSONObject());
        jSONObject.put("zeroTouchProvisioningSettings", this.mZeroTouchProvisioningSettings.toJSONObject());
        jSONObject.put("throttleSettings", this.mThrottleSettings.toJSONObject());
        jSONObject.put("frustrationFreeReconnectSettings", this.mFFRSettings.toJSONObject());
        return jSONObject;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("mZigbeeArcusSettings", this.mZigbeeArcusSettings).add("mZeroTouchProvisioningSettings", this.mZeroTouchProvisioningSettings).add("mThrottleSettings", this.mThrottleSettings).add("mFFRSettings", this.mFFRSettings).add("mArcusSyncPeriod", this.mArcusSettings).toString();
    }

    public FFSArcusSettings() {
        this.mZeroTouchProvisioningSettings = new ZeroTouchProvisioningSettings();
        this.mZigbeeArcusSettings = new ZigbeeArcusSettings();
        this.mThrottleSettings = new ThrottleSettings();
        this.mFFRSettings = new FFRSettings();
        this.mArcusSettings = new ArcusSettings();
    }
}

package com.amazon.alexa.smarthomecameras.model.camerpreferences;

import com.amazon.alexa.smarthomecameras.constants.NetworkConstants;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Map;
/* loaded from: classes10.dex */
public class CameraPreferences {
    private final Gson gson = new Gson();
    @SerializedName("cameras")
    Map<String, Map<String, Object>> preferences;

    public Map<String, Object> getSmartAlertsPreferencesForEntityId(String str) {
        Map<String, Object> map;
        Map map2;
        if (this.preferences.containsKey(str) && (map = this.preferences.get(str)) != null && map.containsKey(NetworkConstants.FEATURES_KEY) && (map2 = (Map) map.get(NetworkConstants.FEATURES_KEY)) != null && map2.containsKey(NetworkConstants.SMART_ALERTS_KEY)) {
            return (Map) map2.get(NetworkConstants.SMART_ALERTS_KEY);
        }
        return null;
    }

    public boolean isSubfeatureEnabled(Map<String, Object> map, String str) {
        Map map2;
        if (map == null || !map.containsKey(NetworkConstants.FEATURES_KEY) || map.get(NetworkConstants.FEATURES_KEY) == null || (map2 = (Map) map.get(NetworkConstants.FEATURES_KEY)) == null || !map2.containsKey(str) || map2.get(str) == null) {
            return false;
        }
        return ((Boolean) ((BooleanFeature) this.gson.fromJson(map2.get(str).toString(), (Class<Object>) BooleanFeature.class)).properties.enabled.get("b")).booleanValue();
    }
}

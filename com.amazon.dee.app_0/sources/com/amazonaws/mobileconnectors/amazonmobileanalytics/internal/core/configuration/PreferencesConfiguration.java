package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.system.Preferences;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.Preconditions;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class PreferencesConfiguration implements Configuration {
    private static final String CONFIG_KEY = "configuration";
    private static final String TAG = "PreferencesConfiguration";
    private final AnalyticsContext context;
    private Map<String, String> properties = new ConcurrentHashMap();

    PreferencesConfiguration(AnalyticsContext analyticsContext) {
        String string;
        Preconditions.checkNotNull(analyticsContext);
        this.context = analyticsContext;
        Preferences preferences = getContext().getSystem().getPreferences();
        JSONObject jSONObject = null;
        if (preferences != null && (string = preferences.getString("configuration", null)) != null) {
            try {
                jSONObject = new JSONObject(string);
            } catch (JSONException e) {
                Log.e(TAG, "could not create Json object of Config", e);
            }
        }
        updateMappings(jSONObject);
    }

    private AnalyticsContext getContext() {
        return this.context;
    }

    public static PreferencesConfiguration newInstance(AnalyticsContext analyticsContext) {
        return new PreferencesConfiguration(analyticsContext);
    }

    private void updateMappings(JSONObject jSONObject) {
        HashMap hashMap = new HashMap();
        if (jSONObject != null) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                try {
                    hashMap.put(str, jSONObject.getString(str));
                } catch (JSONException e) {
                    Log.e(TAG, "could not update property mappings", e);
                }
            }
        }
        this.properties.putAll(hashMap);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Boolean getBoolean(String str) {
        String str2 = this.properties.get(str);
        if (str2 != null) {
            try {
                return Boolean.valueOf(Boolean.parseBoolean(str2));
            } catch (Exception e) {
                Log.e(TAG, String.format("Could not get Boolean for propertyName: %s", str), e);
            }
        }
        return null;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Double getDouble(String str) {
        String str2 = this.properties.get(str);
        if (str2 != null) {
            try {
                return Double.valueOf(Double.parseDouble(str2));
            } catch (Exception e) {
                Log.e(TAG, String.format("Could not get Double for propertyName: %s", str), e);
            }
        }
        return null;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Integer getInt(String str) {
        String str2 = this.properties.get(str);
        if (str2 != null) {
            try {
                return Integer.decode(str2);
            } catch (Exception e) {
                Log.e(TAG, String.format("Could not get Integer for propertyName: %s", str), e);
            }
        }
        return null;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Long getLong(String str) {
        String str2 = this.properties.get(str);
        if (str2 != null) {
            try {
                return Long.decode(str2);
            } catch (Exception e) {
                Log.e(TAG, String.format("Could not get Long for propertyName: %s", str), e);
            }
        }
        return null;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Short getShort(String str) {
        String str2 = this.properties.get(str);
        if (str2 != null) {
            try {
                if (!this.properties.containsKey(str)) {
                    return null;
                }
                return Short.decode(str2);
            } catch (Exception e) {
                Log.e(TAG, String.format("Could not get Short for propertyName: %s", str), e);
                return null;
            }
        }
        return null;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public String getString(String str) {
        return this.properties.get(str);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Boolean optBoolean(String str, Boolean bool) {
        Boolean bool2 = getBoolean(str);
        return bool2 != null ? bool2 : bool;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Double optDouble(String str, Double d) {
        Double d2 = getDouble(str);
        return d2 != null ? d2 : d;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Integer optInt(String str, Integer num) {
        Integer num2 = getInt(str);
        return num2 != null ? num2 : num;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Long optLong(String str, Long l) {
        Long l2 = getLong(str);
        return l2 != null ? l2 : l;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public Short optShort(String str, Short sh) {
        Short sh2 = getShort(str);
        return sh2 != null ? sh2 : sh;
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.configuration.Configuration
    public String optString(String str, String str2) {
        String string = getString(str);
        return string != null ? string : str2;
    }
}

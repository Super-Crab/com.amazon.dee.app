package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event;

import android.util.Log;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.Preconditions;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class ClientContext {
    public static final String APP_ID_KEY = "app_id";
    private static final String CLIENT_OBJECT_KEY = "client";
    private static final String CUSTOM_OBJECT_KEY = "custom";
    private static final String ENVIRONMENT_OBJECT_KEY = "env";
    private static final String MOBILE_ANALYTICS_KEY = "mobile_analytics";
    private static final String SERVICES_OBJECT_KEY = "services";
    private static final String TAG = "ClientContext";
    private String appId;
    private String appPackageName;
    private String appTitle;
    private String appVersionCode;
    private String appVersionName;
    private String carrier;
    private Map<String, String> custom;
    private String locale;
    private String make;
    private String model;
    private String networkType;
    private String platform;
    private String platformVersion;
    private String uniqueId;

    /* loaded from: classes13.dex */
    public static class ClientContextBuilder {
        private String appPackageName = "";
        private String appTitle = "";
        private String appVersionName = "";
        private String appVersionCode = "";
        private String uniqueId = "";
        private String model = "";
        private String make = "";
        private String platform = "ANDROID";
        private String platformVersion = "";
        private String locale = "";
        private String networkType = "";
        private String carrier = "";
        private Map<String, String> custom = new HashMap();
        private String appId = "";

        public ClientContext build() {
            ClientContext clientContext = new ClientContext();
            clientContext.setAppPackageName(this.appPackageName);
            clientContext.setAppTitle(this.appTitle);
            clientContext.setAppVersionName(this.appVersionName);
            clientContext.setAppVersionCode(this.appVersionCode);
            clientContext.setUniqueId(this.uniqueId);
            clientContext.setMake(this.make);
            clientContext.setModel(this.model);
            clientContext.setPlatform(this.platform);
            clientContext.setPlatformVersion(this.platformVersion);
            clientContext.setLocale(this.locale);
            clientContext.setNetworkType(this.networkType);
            clientContext.setCarrier(this.carrier);
            clientContext.setCustom(this.custom);
            clientContext.setAppId(this.appId);
            return clientContext;
        }

        public ClientContextBuilder withAppId(String str) {
            this.appId = str;
            return this;
        }

        public ClientContextBuilder withAppPackageName(String str) {
            this.appPackageName = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withAppTitle(String str) {
            this.appTitle = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withAppVersionCode(String str) {
            this.appVersionCode = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withAppVersionName(String str) {
            this.appVersionName = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withCarrier(String str) {
            this.carrier = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withCustomFields(Map<String, String> map) {
            this.custom = (Map) Preconditions.checkNotNull(map);
            return this;
        }

        public ClientContextBuilder withLocale(String str) {
            this.locale = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withMake(String str) {
            this.make = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withModel(String str) {
            this.model = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withNetworkType(String str) {
            this.networkType = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withPlatformVersion(String str) {
            this.platformVersion = (String) Preconditions.checkNotNull(str);
            return this;
        }

        public ClientContextBuilder withUniqueId(String str) {
            this.uniqueId = (String) Preconditions.checkNotNull(str);
            return this;
        }
    }

    public String getAppPackageName() {
        return this.appPackageName;
    }

    public String getAppTitle() {
        return this.appTitle;
    }

    public String getAppVersionCode() {
        return this.appVersionCode;
    }

    public String getAppVersionName() {
        return this.appVersionName;
    }

    public String getCarrier() {
        return this.carrier;
    }

    public Map<String, String> getCustom() {
        return this.custom;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }

    public String getNetworkType() {
        return this.networkType;
    }

    public String getPlatform() {
        return this.platform;
    }

    public String getPlatformVersion() {
        return this.platformVersion;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public void setAppId(String str) {
        this.appId = str;
    }

    public void setAppPackageName(String str) {
        this.appPackageName = str;
    }

    public void setAppTitle(String str) {
        this.appTitle = str;
    }

    public void setAppVersionCode(String str) {
        this.appVersionCode = str;
    }

    public void setAppVersionName(String str) {
        this.appVersionName = str;
    }

    public void setCarrier(String str) {
        this.carrier = str;
    }

    public void setCustom(Map<String, String> map) {
        this.custom = map;
    }

    public void setLocale(String str) {
        this.locale = str;
    }

    public void setMake(String str) {
        this.make = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setNetworkType(String str) {
        this.networkType = str;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public void setPlatformVersion(String str) {
        this.platformVersion = str;
    }

    public void setUniqueId(String str) {
        this.uniqueId = str;
    }

    public JSONObject toJSONObject() {
        HashMap hashMap = new HashMap();
        hashMap.put("app_package_name", this.appPackageName);
        hashMap.put("app_title", this.appTitle);
        hashMap.put("app_version_name", this.appVersionName);
        hashMap.put("app_version_code", this.appVersionCode);
        hashMap.put("client_id", this.uniqueId);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("model", this.model);
        hashMap2.put("make", this.make);
        hashMap2.put(MetricsConfiguration.PLATFORM, this.platform);
        hashMap2.put("platform_version", this.platformVersion);
        hashMap2.put("locale", this.locale);
        hashMap2.put(AMPDInformationProvider.DEVICE_CARRIER_KEY, this.carrier);
        hashMap2.put("networkType", this.networkType);
        HashMap hashMap3 = new HashMap();
        HashMap hashMap4 = new HashMap();
        hashMap4.put(APP_ID_KEY, this.appId);
        hashMap3.put(MOBILE_ANALYTICS_KEY, new JSONObject((Map) hashMap4));
        JSONObject jSONObject = new JSONObject((Map) hashMap);
        JSONObject jSONObject2 = new JSONObject((Map) hashMap2);
        JSONObject jSONObject3 = new JSONObject((Map) this.custom);
        JSONObject jSONObject4 = new JSONObject((Map) hashMap3);
        JSONObject jSONObject5 = new JSONObject();
        try {
            jSONObject5.put(CLIENT_OBJECT_KEY, jSONObject);
            jSONObject5.put(ENVIRONMENT_OBJECT_KEY, jSONObject2);
            jSONObject5.put("custom", jSONObject3);
            jSONObject5.put(SERVICES_OBJECT_KEY, jSONObject4);
            return jSONObject5;
        } catch (JSONException e) {
            Log.e(TAG, "Error creating clientContextJSON", e);
            return jSONObject5;
        }
    }

    private ClientContext() {
        this.appPackageName = "";
        this.appTitle = "";
        this.appVersionName = "";
        this.appVersionCode = "";
        this.uniqueId = "";
        this.model = "";
        this.make = "";
        this.platform = "ANDROID";
        this.platformVersion = "";
        this.locale = "en-US";
        this.networkType = "";
        this.carrier = "";
        this.custom = new HashMap();
        this.appId = "";
    }
}

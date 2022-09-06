package com.amazon.alexa.devicesetup.bridge;

import com.facebook.react.bridge.ReadableMap;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
/* loaded from: classes7.dex */
public class ProvisionDeviceConfiguration {
    public static final String DEVICE_LOCALE = "deviceLocale";
    public static final String KEY = "key";
    public static final String KEY_MANAGEMENT = "keyManagement";
    public static final String OVERRIDE_LOCALE = "overrideLocale";
    public static final String SAVE_TO_WIFI_LOCKER = "saveToWifiLocker";
    public static final String SSID = "ssid";
    private String deviceLocale;
    private String key;
    private String keyManagement;
    private boolean overrideLocale;
    private boolean saveToWifiLocker;
    private String ssid;

    public ProvisionDeviceConfiguration(String str, String str2, String str3, boolean z, boolean z2, String str4) {
        this.ssid = str;
        this.key = str2;
        this.keyManagement = str3;
        this.saveToWifiLocker = z;
        this.overrideLocale = z2;
        this.deviceLocale = str4;
    }

    public static ProvisionDeviceConfiguration fromReadableMap(ReadableMap readableMap) {
        return new ProvisionDeviceConfiguration(readableMap.getString("ssid"), sanitizeString(readableMap, "key"), readableMap.getString("keyManagement"), readableMap.getBoolean("saveToWifiLocker"), readableMap.getBoolean("overrideLocale"), sanitizeString(readableMap, "deviceLocale"));
    }

    public static String sanitizeString(ReadableMap readableMap, String str) {
        if (readableMap.hasKey(str)) {
            return readableMap.getString(str);
        }
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ProvisionDeviceConfiguration.class != obj.getClass()) {
            return false;
        }
        ProvisionDeviceConfiguration provisionDeviceConfiguration = (ProvisionDeviceConfiguration) obj;
        return Objects.equal(this.ssid, provisionDeviceConfiguration.ssid) && Objects.equal(this.key, provisionDeviceConfiguration.key) && Objects.equal(this.keyManagement, provisionDeviceConfiguration.keyManagement) && Objects.equal(Boolean.valueOf(this.saveToWifiLocker), Boolean.valueOf(provisionDeviceConfiguration.saveToWifiLocker)) && Objects.equal(Boolean.valueOf(this.overrideLocale), Boolean.valueOf(provisionDeviceConfiguration.overrideLocale)) && Objects.equal(this.deviceLocale, provisionDeviceConfiguration.deviceLocale);
    }

    public String getDeviceLocale() {
        return this.deviceLocale;
    }

    public String getKey() {
        return this.key;
    }

    public String getKeyManagement() {
        return this.keyManagement;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        return Objects.hashCode(this.ssid, this.key, this.keyManagement, Boolean.valueOf(this.saveToWifiLocker), Boolean.valueOf(this.overrideLocale), this.deviceLocale);
    }

    public boolean isOverrideLocale() {
        return this.overrideLocale;
    }

    public boolean isSaveToWifiLocker() {
        return this.saveToWifiLocker;
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("ssid", this.ssid).add("key", this.key).add("keyManagement", this.keyManagement).add("saveToWifiLocker", this.saveToWifiLocker).add("overrideLocale", this.overrideLocale).add("deviceLocale", this.deviceLocale).toString();
    }
}

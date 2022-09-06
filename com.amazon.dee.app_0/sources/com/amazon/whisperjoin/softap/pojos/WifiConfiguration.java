package com.amazon.whisperjoin.softap.pojos;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes13.dex */
public class WifiConfiguration {
    @SerializedName("key")
    private String key;
    @SerializedName("keyMgmt")
    private WifiKeyManagement keyMgmt;
    @SerializedName("ssid")
    private String ssid;

    /* loaded from: classes13.dex */
    public static class WifiConfigurationBuilder {
        private String key;
        private WifiKeyManagement keyMgmt;
        private String ssid;

        WifiConfigurationBuilder() {
        }

        public WifiConfiguration build() {
            return new WifiConfiguration(this.ssid, this.keyMgmt, this.key);
        }

        public WifiConfigurationBuilder key(String str) {
            this.key = str;
            return this;
        }

        public WifiConfigurationBuilder keyMgmt(WifiKeyManagement wifiKeyManagement) {
            this.keyMgmt = wifiKeyManagement;
            return this;
        }

        public WifiConfigurationBuilder ssid(String str) {
            this.ssid = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfiguration.WifiConfigurationBuilder(ssid=");
            outline107.append(this.ssid);
            outline107.append(", keyMgmt=");
            outline107.append(this.keyMgmt);
            outline107.append(", key=");
            return GeneratedOutlineSupport1.outline91(outline107, this.key, ")");
        }
    }

    /* loaded from: classes13.dex */
    public enum WifiKeyManagement {
        NONE,
        WPA_PSK,
        WEP,
        OTHER
    }

    WifiConfiguration(String str, WifiKeyManagement wifiKeyManagement, String str2) {
        this.ssid = str;
        this.keyMgmt = wifiKeyManagement;
        this.key = str2;
    }

    public static WifiConfigurationBuilder builder() {
        return new WifiConfigurationBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof WifiConfiguration;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiConfiguration)) {
            return false;
        }
        WifiConfiguration wifiConfiguration = (WifiConfiguration) obj;
        if (!wifiConfiguration.canEqual(this)) {
            return false;
        }
        String ssid = getSsid();
        String ssid2 = wifiConfiguration.getSsid();
        if (ssid != null ? !ssid.equals(ssid2) : ssid2 != null) {
            return false;
        }
        WifiKeyManagement keyMgmt = getKeyMgmt();
        WifiKeyManagement keyMgmt2 = wifiConfiguration.getKeyMgmt();
        if (keyMgmt != null ? !keyMgmt.equals(keyMgmt2) : keyMgmt2 != null) {
            return false;
        }
        String key = getKey();
        String key2 = wifiConfiguration.getKey();
        return key != null ? key.equals(key2) : key2 == null;
    }

    public String getKey() {
        return this.key;
    }

    public WifiKeyManagement getKeyMgmt() {
        return this.keyMgmt;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        String ssid = getSsid();
        int i = 43;
        int hashCode = ssid == null ? 43 : ssid.hashCode();
        WifiKeyManagement keyMgmt = getKeyMgmt();
        int hashCode2 = ((hashCode + 59) * 59) + (keyMgmt == null ? 43 : keyMgmt.hashCode());
        String key = getKey();
        int i2 = hashCode2 * 59;
        if (key != null) {
            i = key.hashCode();
        }
        return i2 + i;
    }

    public void setKey(String str) {
        this.key = str;
    }

    public void setKeyMgmt(WifiKeyManagement wifiKeyManagement) {
        this.keyMgmt = wifiKeyManagement;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfiguration(ssid=");
        outline107.append(getSsid());
        outline107.append(", keyMgmt=");
        outline107.append(getKeyMgmt());
        outline107.append(", key=");
        outline107.append(getKey());
        outline107.append(")");
        return outline107.toString();
    }
}

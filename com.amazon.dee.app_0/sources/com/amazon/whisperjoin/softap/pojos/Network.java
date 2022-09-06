package com.amazon.whisperjoin.softap.pojos;

import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes13.dex */
public class Network {
    @SerializedName("bssid")
    private String bssid;
    @SerializedName("security")
    private WifiConfiguration.WifiKeyManagement keyMgmt;
    @SerializedName("rssi")
    private int rssi;
    @SerializedName("ssid")
    private String ssid;

    /* loaded from: classes13.dex */
    public static class NetworkBuilder {
        private String bssid;
        private WifiConfiguration.WifiKeyManagement keyMgmt;
        private int rssi;
        private String ssid;

        NetworkBuilder() {
        }

        public NetworkBuilder bssid(String str) {
            this.bssid = str;
            return this;
        }

        public Network build() {
            return new Network(this.ssid, this.bssid, this.keyMgmt, this.rssi);
        }

        public NetworkBuilder keyMgmt(WifiConfiguration.WifiKeyManagement wifiKeyManagement) {
            this.keyMgmt = wifiKeyManagement;
            return this;
        }

        public NetworkBuilder rssi(int i) {
            this.rssi = i;
            return this;
        }

        public NetworkBuilder ssid(String str) {
            this.ssid = str;
            return this;
        }

        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network.NetworkBuilder(ssid=");
            outline107.append(this.ssid);
            outline107.append(", bssid=");
            outline107.append(this.bssid);
            outline107.append(", keyMgmt=");
            outline107.append(this.keyMgmt);
            outline107.append(", rssi=");
            return GeneratedOutlineSupport1.outline86(outline107, this.rssi, ")");
        }
    }

    Network(String str, String str2, WifiConfiguration.WifiKeyManagement wifiKeyManagement, int i) {
        this.ssid = str;
        this.bssid = str2;
        this.keyMgmt = wifiKeyManagement;
        this.rssi = i;
    }

    public static NetworkBuilder builder() {
        return new NetworkBuilder();
    }

    protected boolean canEqual(Object obj) {
        return obj instanceof Network;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Network)) {
            return false;
        }
        Network network = (Network) obj;
        if (!network.canEqual(this)) {
            return false;
        }
        String ssid = getSsid();
        String ssid2 = network.getSsid();
        if (ssid != null ? !ssid.equals(ssid2) : ssid2 != null) {
            return false;
        }
        String bssid = getBssid();
        String bssid2 = network.getBssid();
        if (bssid != null ? !bssid.equals(bssid2) : bssid2 != null) {
            return false;
        }
        WifiConfiguration.WifiKeyManagement keyMgmt = getKeyMgmt();
        WifiConfiguration.WifiKeyManagement keyMgmt2 = network.getKeyMgmt();
        if (keyMgmt != null ? !keyMgmt.equals(keyMgmt2) : keyMgmt2 != null) {
            return false;
        }
        return getRssi() == network.getRssi();
    }

    public String getBssid() {
        return this.bssid;
    }

    public WifiConfiguration.WifiKeyManagement getKeyMgmt() {
        return this.keyMgmt;
    }

    public int getRssi() {
        return this.rssi;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        String ssid = getSsid();
        int i = 43;
        int hashCode = ssid == null ? 43 : ssid.hashCode();
        String bssid = getBssid();
        int hashCode2 = ((hashCode + 59) * 59) + (bssid == null ? 43 : bssid.hashCode());
        WifiConfiguration.WifiKeyManagement keyMgmt = getKeyMgmt();
        int i2 = hashCode2 * 59;
        if (keyMgmt != null) {
            i = keyMgmt.hashCode();
        }
        return getRssi() + ((i2 + i) * 59);
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public void setKeyMgmt(WifiConfiguration.WifiKeyManagement wifiKeyManagement) {
        this.keyMgmt = wifiKeyManagement;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Network(ssid=");
        outline107.append(getSsid());
        outline107.append(", bssid=");
        outline107.append(getBssid());
        outline107.append(", keyMgmt=");
        outline107.append(getKeyMgmt());
        outline107.append(", rssi=");
        outline107.append(getRssi());
        outline107.append(")");
        return outline107.toString();
    }
}

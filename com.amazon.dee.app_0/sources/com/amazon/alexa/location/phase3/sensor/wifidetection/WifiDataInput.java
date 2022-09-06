package com.amazon.alexa.location.phase3.sensor.wifidetection;

import android.net.wifi.WifiInfo;
import com.amazon.alexa.location.phase3.LocationSignal;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes9.dex */
public class WifiDataInput extends LocationSignal {
    public static final String DATA_TYPE = "WIFI_DETAIL_INFO";
    public static final int WIFI_STATUS_CONNECTED = 1;
    public static final int WIFI_STATUS_DISCONNECTED = 0;
    public final String bssid;
    public final int rssi;
    public final String ssid;
    public final int wifiStatus;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface WifiStatus {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WifiDataInput(long j, WifiInfo wifiInfo) {
        super(DATA_TYPE, 1, j);
        int i = 1;
        this.ssid = wifiInfo.getSSID();
        this.bssid = wifiInfo.getBSSID();
        this.rssi = wifiInfo.getRssi();
        this.wifiStatus = this.bssid == null ? 0 : i;
    }

    public WifiDataInput(long j) {
        super(DATA_TYPE, 1, j);
        this.wifiStatus = 0;
        this.ssid = "";
        this.bssid = "";
        this.rssi = 0;
    }
}

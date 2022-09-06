package com.amazon.whisperjoin.wifi;

import android.net.wifi.ScanResult;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable;
import com.amazon.whisperjoin.utils.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public final class WifiScanResult extends WifiNetwork implements Serializable, Validatable {
    private int freqMhz;
    private int rssi;

    public WifiScanResult(String str, WifiKeyManagement wifiKeyManagement) {
        this(str, wifiKeyManagement, 0, -100);
    }

    public static WifiScanResult convertFromAndroidScanResult(ScanResult scanResult) {
        return new WifiScanResult(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), scanResult.SSID, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), WifiKeyManagement.getWifiKeyManagement(scanResult), scanResult.frequency, scanResult.level);
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    protected boolean canEqual(Object obj) {
        return obj instanceof WifiScanResult;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof WifiScanResult)) {
            return false;
        }
        WifiScanResult wifiScanResult = (WifiScanResult) obj;
        return wifiScanResult.canEqual(this) && super.equals(obj) && getFreqMhz() == wifiScanResult.getFreqMhz() && getRssi() == wifiScanResult.getRssi();
    }

    public int getFreqMhz() {
        return this.freqMhz;
    }

    public int getRssi() {
        return this.rssi;
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork
    public int hashCode() {
        int freqMhz = getFreqMhz();
        return getRssi() + ((freqMhz + (super.hashCode() * 59)) * 59);
    }

    @Override // com.amazon.whisperjoin.wifi.WifiNetwork, com.amazon.whisperjoin.provisioning.bluetooth.request.serializers.Validatable
    public void validate() {
        super.validate();
        if (InputValidator.isValidFrequency(this.freqMhz)) {
            return;
        }
        throw new IllegalArgumentException("Frequency of Wifi network cannot be negative. Please use InputValidator methods to validate parameters.");
    }

    public WifiScanResult(String str, WifiKeyManagement wifiKeyManagement, int i, int i2) {
        super(str, wifiKeyManagement);
        this.freqMhz = i;
        this.rssi = InputValidator.getValidRssi(i2);
        validate();
    }
}

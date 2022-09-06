package com.amazon.devicesetup.provisioning.data.wifi;

import com.amazon.devicesetup.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
/* loaded from: classes12.dex */
public class WifiScanResult extends WifiNetwork implements Comparable<WifiScanResult> {
    protected int frequencyBand;
    protected int signalStrength;

    public WifiScanResult(String str, WifiKeyManagement wifiKeyManagement) {
        this(str, wifiKeyManagement, 0, -100);
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj instanceof WifiScanResult) {
            return super.equals(obj) && this.frequencyBand == ((WifiScanResult) obj).frequencyBand;
        }
        return false;
    }

    public int getFrequencyBand() {
        return this.frequencyBand;
    }

    public int getSignalStrength() {
        return this.signalStrength;
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        return ((super.hashCode() + 41) * 41) + this.frequencyBand;
    }

    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiScanResult [ssid=");
        outline107.append(this.ssid);
        outline107.append(", key-mgmt=");
        outline107.append(this.wifiKeyManagement);
        outline107.append(", freq=");
        outline107.append(this.frequencyBand);
        outline107.append(", rssi=");
        return GeneratedOutlineSupport1.outline86(outline107, this.signalStrength, "]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.devicesetup.provisioning.data.wifi.WifiNetwork
    public void validate() {
        super.validate();
        if (InputValidator.isValidFrequency(this.frequencyBand)) {
            return;
        }
        throw new IllegalArgumentException("Frequency of Wifi network cannot be negative. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator methods to validate parameters.");
    }

    public WifiScanResult(String str, WifiKeyManagement wifiKeyManagement, int i, int i2) {
        super(str, wifiKeyManagement);
        this.frequencyBand = i;
        this.signalStrength = InputValidator.getValidRssi(i2);
        validate();
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nonnull WifiScanResult wifiScanResult) {
        int i = this.signalStrength;
        int i2 = wifiScanResult.signalStrength;
        int i3 = i < i2 ? 1 : i > i2 ? -1 : 0;
        if (i3 == 0) {
            i3 = this.ssid.compareTo(wifiScanResult.ssid);
        }
        return i3 == 0 ? this.wifiKeyManagement.compareTo(wifiScanResult.wifiKeyManagement) : i3;
    }
}

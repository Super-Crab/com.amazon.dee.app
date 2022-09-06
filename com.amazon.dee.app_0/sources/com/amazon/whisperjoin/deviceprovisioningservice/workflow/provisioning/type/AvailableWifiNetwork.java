package com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResult;
import java.util.Locale;
/* loaded from: classes13.dex */
public class AvailableWifiNetwork extends WifiNetwork {
    public static final Parcelable.Creator<AvailableWifiNetwork> CREATOR = new Parcelable.Creator<AvailableWifiNetwork>() { // from class: com.amazon.whisperjoin.deviceprovisioningservice.workflow.provisioning.type.AvailableWifiNetwork.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AvailableWifiNetwork mo5683createFromParcel(Parcel parcel) {
            return AvailableWifiNetwork.fromParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AvailableWifiNetwork[] mo5684newArray(int i) {
            return new AvailableWifiNetwork[i];
        }
    };
    private boolean mHasConfiguration;
    private WifiScanResult mScanResult;
    private WifiConfiguration mWifiConfiguration;

    public AvailableWifiNetwork(WifiScanResult wifiScanResult, WifiConfiguration wifiConfiguration) {
        super(wifiScanResult.getSsid(), wifiScanResult.getKeyManagement());
        this.mScanResult = wifiScanResult;
        this.mWifiConfiguration = wifiConfiguration;
        this.mHasConfiguration = this.mWifiConfiguration != null;
        validateProperties();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AvailableWifiNetwork fromParcel(Parcel parcel) {
        WifiScanResult wifiScanResult = (WifiScanResult) parcel.readParcelable(WifiScanResult.class.getClassLoader());
        boolean z = true;
        if (parcel.readInt() != 1) {
            z = false;
        }
        return new AvailableWifiNetwork(wifiScanResult, z ? (WifiConfiguration) parcel.readParcelable(WifiConfiguration.class.getClassLoader()) : null);
    }

    private void validateProperties() {
        WifiScanResult wifiScanResult = this.mScanResult;
        if (wifiScanResult != null) {
            if (this.mWifiConfiguration == null) {
                return;
            }
            if (wifiScanResult.getSsid().equals(this.mWifiConfiguration.getSsid()) && this.mScanResult.getKeyManagement().equals(this.mWifiConfiguration.getKeyManagement())) {
                return;
            }
            throw new IllegalStateException("Invalid network configuration");
        }
        throw new IllegalStateException("Scan WJResult can't be null");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || AvailableWifiNetwork.class != obj.getClass()) {
            return false;
        }
        AvailableWifiNetwork availableWifiNetwork = (AvailableWifiNetwork) obj;
        if (!this.mScanResult.equals(availableWifiNetwork.mScanResult)) {
            return false;
        }
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        WifiConfiguration wifiConfiguration2 = availableWifiNetwork.mWifiConfiguration;
        return wifiConfiguration != null ? wifiConfiguration.equals(wifiConfiguration2) : wifiConfiguration2 == null;
    }

    public WifiScanResult getScanResult() {
        return this.mScanResult;
    }

    public WifiConfiguration getWifiConfiguration() {
        return this.mWifiConfiguration;
    }

    public boolean hasConfiguration() {
        return this.mHasConfiguration;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        int hashCode = this.mScanResult.hashCode() * 31;
        WifiConfiguration wifiConfiguration = this.mWifiConfiguration;
        return hashCode + (wifiConfiguration != null ? wifiConfiguration.hashCode() : 0);
    }

    public void setWifiConfiguration(WifiConfiguration wifiConfiguration) {
        this.mWifiConfiguration = wifiConfiguration;
        this.mHasConfiguration = this.mWifiConfiguration != null;
        validateProperties();
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public String toString() {
        return String.format(Locale.US, "SSID: %s, KeyMgmt: %s, RSSI: %d, HasConfig: %b", this.mScanResult.getSsid(), this.mScanResult.getKeyManagement().name(), Integer.valueOf(this.mScanResult.getSignalStrength()), Boolean.valueOf(this.mHasConfiguration));
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mScanResult, i);
        parcel.writeInt(this.mHasConfiguration ? 1 : 0);
        if (this.mHasConfiguration) {
            parcel.writeParcelable(this.mWifiConfiguration, i);
        }
    }

    public AvailableWifiNetwork(WifiScanResult wifiScanResult) {
        this(wifiScanResult, null);
    }
}

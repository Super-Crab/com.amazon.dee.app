package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes13.dex */
public class WifiBaseConfiguration extends WifiNetwork {
    public static final Parcelable.ClassLoaderCreator<WifiBaseConfiguration> CREATOR = new Parcelable.ClassLoaderCreator<WifiBaseConfiguration>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiBaseConfiguration.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiBaseConfiguration[] mo5453newArray(int i) {
            if (i >= 0) {
                return new WifiBaseConfiguration[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public WifiBaseConfiguration mo5452createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new WifiBaseConfiguration(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiBaseConfiguration mo5451createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new WifiBaseConfiguration(parcel, WifiBaseConfiguration.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    protected boolean mIsHiddenNetwork;
    protected int mNetworkPriority;

    /* JADX INFO: Access modifiers changed from: protected */
    public WifiBaseConfiguration(String str, WifiKeyManagement wifiKeyManagement, int i, boolean z) {
        super(str, wifiKeyManagement);
        this.mNetworkPriority = i;
        this.mIsHiddenNetwork = z;
    }

    public static WifiBaseConfiguration convertFromAndroidWifiConfiguration(android.net.wifi.WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration.allowedKeyManagement.get(0)) {
            String[] strArr = wifiConfiguration.wepKeys;
            if (strArr != null && strArr.length > 0 && strArr[0] != null) {
                return createWepWifiConfiguration(wifiConfiguration.SSID, wifiConfiguration.priority, wifiConfiguration.hiddenSSID);
            }
            return createOpenWifiConfiguration(wifiConfiguration.SSID, wifiConfiguration.priority, wifiConfiguration.hiddenSSID);
        } else if (!wifiConfiguration.allowedKeyManagement.get(1)) {
            return null;
        } else {
            return createWpaWifiConfiguration(wifiConfiguration.SSID, wifiConfiguration.priority, wifiConfiguration.hiddenSSID);
        }
    }

    public static WifiBaseConfiguration createOpenWifiConfiguration(String str) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.NONE, 0, false);
    }

    public static WifiBaseConfiguration createWepWifiConfiguration(String str) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WEP, 0, false);
    }

    public static WifiBaseConfiguration createWpaWifiConfiguration(String str) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WPA_PSK, 0, false);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj instanceof WifiBaseConfiguration) {
            WifiBaseConfiguration wifiBaseConfiguration = (WifiBaseConfiguration) obj;
            return super.equals(this) && this.mNetworkPriority == wifiBaseConfiguration.mNetworkPriority && this.mIsHiddenNetwork == wifiBaseConfiguration.mIsHiddenNetwork;
        }
        return false;
    }

    public int getNetworkPriority() {
        return this.mNetworkPriority;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        return ((((super.hashCode() + 41) * 41) + this.mNetworkPriority) * 41) + (this.mIsHiddenNetwork ? 1 : 0);
    }

    public boolean isHiddenNetwork() {
        return this.mIsHiddenNetwork;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiBaseConfiguration [ssid=--REDACTED--, key-mgmt=");
        outline107.append(this.mWifiKeyManagement);
        outline107.append(", priority=");
        outline107.append(this.mNetworkPriority);
        outline107.append(", hidden=");
        return GeneratedOutlineSupport1.outline97(outline107, this.mIsHiddenNetwork, "]");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mNetworkPriority);
            parcel.writeByte(this.mIsHiddenNetwork ? (byte) 1 : (byte) 0);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public static WifiBaseConfiguration createOpenWifiConfiguration(String str, int i, boolean z) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.NONE, i, z);
    }

    public static WifiBaseConfiguration createWepWifiConfiguration(String str, int i, boolean z) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WEP, i, z);
    }

    public static WifiBaseConfiguration createWpaWifiConfiguration(String str, int i, boolean z) {
        return new WifiBaseConfiguration(str, WifiKeyManagement.WPA_PSK, i, z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public WifiBaseConfiguration(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.mNetworkPriority = parcel.readInt();
        this.mIsHiddenNetwork = parcel.readByte() != 1 ? false : true;
    }
}

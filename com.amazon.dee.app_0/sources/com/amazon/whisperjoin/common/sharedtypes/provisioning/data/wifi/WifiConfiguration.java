package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.BitSet;
/* loaded from: classes13.dex */
public class WifiConfiguration extends WifiBaseConfiguration {
    public static final Parcelable.ClassLoaderCreator<WifiConfiguration> CREATOR = new Parcelable.ClassLoaderCreator<WifiConfiguration>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiConfiguration[] mo5456newArray(int i) {
            if (i >= 0) {
                return new WifiConfiguration[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public WifiConfiguration mo5455createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new WifiConfiguration(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiConfiguration mo5454createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new WifiConfiguration(parcel, WifiConfiguration.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    private String mPsk;
    private String mWepKey;

    /* renamed from: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiConfiguration$2  reason: invalid class name */
    /* loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement = new int[WifiKeyManagement.values().length];

        static {
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WEP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.WPA_PSK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$whisperjoin$common$sharedtypes$provisioning$data$wifi$WifiKeyManagement[WifiKeyManagement.OTHER.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    private WifiConfiguration(String str, WifiKeyManagement wifiKeyManagement, String str2, String str3, int i, boolean z) {
        super(str, wifiKeyManagement, i, z);
        if (wifiKeyManagement == WifiKeyManagement.WEP) {
            this.mWepKey = str2;
        } else if (wifiKeyManagement == WifiKeyManagement.WPA_PSK) {
            this.mPsk = str3;
        }
        validate();
    }

    public static WifiConfiguration createOpenWifiConfiguration(String str) {
        return new WifiConfiguration(str, WifiKeyManagement.NONE, null, null, 0, false);
    }

    public static WifiConfiguration createWepWifiConfiguration(String str, String str2) {
        return new WifiConfiguration(str, WifiKeyManagement.WEP, str2, null, 0, false);
    }

    public static WifiConfiguration createWpaWifiConfiguration(String str, String str2) {
        return new WifiConfiguration(str, WifiKeyManagement.WPA_PSK, null, str2, 0, false);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj instanceof WifiConfiguration) {
            WifiConfiguration wifiConfiguration = (WifiConfiguration) obj;
            if (this.mPsk != null) {
                return super.equals(obj) && this.mPsk.equals(wifiConfiguration.mPsk);
            } else if (this.mWepKey == null) {
                return super.equals(obj);
            } else {
                return super.equals(obj) && this.mWepKey.equals(wifiConfiguration.mWepKey);
            }
        }
        return false;
    }

    public String getPsk() {
        return this.mPsk;
    }

    public String getWepKey() {
        return this.mWepKey;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        int hashCode = (super.hashCode() + 41) * 41;
        String str = this.mWepKey;
        int i = 0;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 41;
        String str2 = this.mPsk;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public android.net.wifi.WifiConfiguration toAndroidConfiguration() throws UnsupportedOperationException {
        android.net.wifi.WifiConfiguration wifiConfiguration = new android.net.wifi.WifiConfiguration();
        wifiConfiguration.SSID = getSsid();
        wifiConfiguration.hiddenSSID = this.mIsHiddenNetwork;
        wifiConfiguration.priority = this.mNetworkPriority;
        wifiConfiguration.allowedKeyManagement = new BitSet();
        wifiConfiguration.allowedAuthAlgorithms = new BitSet();
        wifiConfiguration.allowedGroupCiphers = new BitSet();
        int ordinal = this.mWifiKeyManagement.ordinal();
        if (ordinal == 0) {
            wifiConfiguration.allowedKeyManagement.set(0);
        } else if (ordinal == 1) {
            wifiConfiguration.preSharedKey = getPsk();
            wifiConfiguration.allowedKeyManagement.set(1);
        } else if (ordinal == 2) {
            wifiConfiguration.allowedKeyManagement.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(0);
            wifiConfiguration.allowedAuthAlgorithms.set(1);
            wifiConfiguration.allowedGroupCiphers.set(0);
            wifiConfiguration.allowedGroupCiphers.set(1);
            String str = this.mWepKey;
            if (str != null) {
                wifiConfiguration.wepTxKeyIndex = 0;
                wifiConfiguration.wepKeys[0] = str;
            }
        } else if (ordinal == 3) {
            throw new UnsupportedOperationException("Network type of OTHER key management not supported");
        }
        return wifiConfiguration;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiConfiguration [ssid=--REDACTED--, key-mgmt=");
        outline107.append(this.mWifiKeyManagement);
        outline107.append("]");
        return outline107.toString();
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    protected void validate() {
        super.validate();
        if (this.mPsk != null && this.mWepKey != null) {
            throw new IllegalArgumentException("Wifi configuration cannot have both WEP-key and PSK. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        if (this.mPsk != null && !InputValidator.isValidWPAWifiConfiguration(this.mWifiKeyManagement, getPsk())) {
            throw new IllegalArgumentException("Invalid PSK in wifi configuration. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        String str = this.mWepKey;
        if (str != null && !InputValidator.isValidWEPWifiConfiguration(this.mWifiKeyManagement, str)) {
            throw new IllegalArgumentException("Invalid WEP-key in wifi configuration. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
        }
        if (this.mPsk != null || this.mWepKey != null || InputValidator.isValidOpenWifiConfiguration(this.mWifiKeyManagement)) {
            return;
        }
        throw new IllegalArgumentException("Invalid open wifi configuration. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator to validate wifi parameters.");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiBaseConfiguration, com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.mWepKey);
            parcel.writeString(this.mPsk);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public static WifiConfiguration createOpenWifiConfiguration(String str, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.NONE, null, null, i, z);
    }

    public static WifiConfiguration createWepWifiConfiguration(String str, String str2, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.WEP, str2, null, i, z);
    }

    public static WifiConfiguration createWpaWifiConfiguration(String str, String str2, int i, boolean z) {
        return new WifiConfiguration(str, WifiKeyManagement.WPA_PSK, null, str2, i, z);
    }

    protected WifiConfiguration(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.mWepKey = parcel.readString();
        this.mPsk = parcel.readString();
        validate();
    }
}

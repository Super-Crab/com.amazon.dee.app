package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi;

import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.BitSet;
/* loaded from: classes13.dex */
public enum WifiKeyManagement implements Parcelable {
    NONE,
    WPA_PSK,
    WEP,
    OTHER;
    
    public static final Parcelable.ClassLoaderCreator<WifiKeyManagement> CREATOR = new Parcelable.ClassLoaderCreator<WifiKeyManagement>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiKeyManagement[] mo5465newArray(int i) {
            if (i >= 0) {
                return new WifiKeyManagement[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public WifiKeyManagement mo5464createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return WifiKeyManagement.values()[parcel.readInt()];
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiKeyManagement mo5463createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return WifiKeyManagement.values()[parcel.readInt()];
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };

    /* renamed from: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiKeyManagement$2  reason: invalid class name */
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

    public static WifiKeyManagement getWifiKeyManagement(ScanResult scanResult) {
        if (scanResult.capabilities.contains("WEP")) {
            return WEP;
        }
        if (scanResult.capabilities.contains("PSK")) {
            return WPA_PSK;
        }
        if (scanResult.capabilities.contains("EAP")) {
            return OTHER;
        }
        return NONE;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BitSet toAndroidKeyManagement() {
        BitSet bitSet = new BitSet();
        int ordinal = ordinal();
        if (ordinal == 0) {
            bitSet.set(0);
        } else if (ordinal == 1) {
            bitSet.set(1);
        } else if (ordinal == 2) {
            bitSet.set(0);
        } else if (ordinal == 3) {
            throw new IllegalArgumentException("Network type of OTHER key management not supported.");
        }
        return bitSet;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            parcel.writeInt(ordinal());
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public static WifiKeyManagement getWifiKeyManagement(android.net.wifi.WifiConfiguration wifiConfiguration) {
        if (wifiConfiguration != null) {
            if (wifiConfiguration.allowedKeyManagement.get(1)) {
                return WPA_PSK;
            }
            if (wifiConfiguration.allowedKeyManagement.get(0)) {
                String[] strArr = wifiConfiguration.wepKeys;
                if (strArr != null && strArr.length > 0 && strArr[0] != null) {
                    return WEP;
                }
                return NONE;
            }
            return OTHER;
        }
        throw new IllegalArgumentException("Cannot convert a null wifi-configuration's key-management.");
    }
}

package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi;

import android.net.wifi.ScanResult;
import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.annotation.Nonnull;
/* loaded from: classes13.dex */
public class WifiScanResult extends WifiNetwork implements Comparable<WifiScanResult> {
    public static final Parcelable.ClassLoaderCreator<WifiScanResult> CREATOR = new Parcelable.ClassLoaderCreator<WifiScanResult>() { // from class: com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiScanResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public WifiScanResult[] mo5471newArray(int i) {
            if (i >= 0) {
                return new WifiScanResult[i];
            }
            throw new IllegalArgumentException("size cannot be negative.");
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.ClassLoaderCreator
        /* renamed from: createFromParcel */
        public WifiScanResult mo5470createFromParcel(Parcel parcel, ClassLoader classLoader) {
            if (parcel != null) {
                return new WifiScanResult(parcel, classLoader);
            }
            throw new IllegalArgumentException("source cannot be null.");
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public WifiScanResult mo5469createFromParcel(Parcel parcel) {
            if (parcel != null) {
                return new WifiScanResult(parcel, WifiScanResult.class.getClassLoader());
            }
            throw new IllegalArgumentException("source cannot be null.");
        }
    };
    protected int mFrequencyBand;
    protected int mSignalStrength;

    public WifiScanResult(String str, WifiKeyManagement wifiKeyManagement) {
        this(str, wifiKeyManagement, 0, -100);
    }

    public static WifiScanResult convertFromAndroidScanResult(ScanResult scanResult) {
        return new WifiScanResult(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), scanResult.SSID, EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED), WifiKeyManagement.getWifiKeyManagement(scanResult), scanResult.frequency, scanResult.level);
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public boolean equals(Object obj) {
        if (obj instanceof WifiScanResult) {
            return super.equals(obj) && this.mFrequencyBand == ((WifiScanResult) obj).mFrequencyBand;
        }
        return false;
    }

    public int getFrequencyBand() {
        return this.mFrequencyBand;
    }

    public int getSignalStrength() {
        return this.mSignalStrength;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public int hashCode() {
        return ((super.hashCode() + 41) * 41) + this.mFrequencyBand;
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WifiScanResult [ssid=--REDACTED--, key-mgmt=");
        outline107.append(this.mWifiKeyManagement);
        outline107.append(", freq=");
        outline107.append(this.mFrequencyBand);
        outline107.append(", rssi=");
        return GeneratedOutlineSupport1.outline86(outline107, this.mSignalStrength, "]");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork
    public void validate() {
        super.validate();
        if (InputValidator.isValidFrequency(this.mFrequencyBand)) {
            return;
        }
        throw new IllegalArgumentException("Frequency of Wifi network cannot be negative. Please use com.amazon.whisperjoin.common.sharedtypes.utility.InputValidator methods to validate parameters.");
    }

    @Override // com.amazon.whisperjoin.common.sharedtypes.provisioning.data.wifi.WifiNetwork, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel != null) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.mFrequencyBand);
            parcel.writeInt(this.mSignalStrength);
            return;
        }
        throw new IllegalArgumentException("dest cannot be null.");
    }

    public WifiScanResult(String str, WifiKeyManagement wifiKeyManagement, int i, int i2) {
        super(str, wifiKeyManagement);
        this.mFrequencyBand = i;
        this.mSignalStrength = InputValidator.getValidRssi(i2);
        validate();
    }

    @Override // java.lang.Comparable
    public int compareTo(@Nonnull WifiScanResult wifiScanResult) {
        int i = this.mSignalStrength;
        int i2 = wifiScanResult.mSignalStrength;
        int i3 = i < i2 ? 1 : i > i2 ? -1 : 0;
        if (i3 == 0) {
            i3 = this.mSsid.compareTo(wifiScanResult.mSsid);
        }
        return i3 == 0 ? this.mWifiKeyManagement.compareTo(wifiScanResult.mWifiKeyManagement) : i3;
    }

    protected WifiScanResult(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.mFrequencyBand = parcel.readInt();
        this.mSignalStrength = parcel.readInt();
        validate();
    }
}
